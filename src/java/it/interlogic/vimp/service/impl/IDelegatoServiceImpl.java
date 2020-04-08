package it.interlogic.vimp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.interlogic.vimp.data.jpa.model.*;
import it.interlogic.vimp.data.jpa.repository.PLFServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.data.jpa.repository.specs.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntity;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTUtenteJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFVDelegatoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRUtenteImpresaJpaRepository;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.IDelegatoService;

@Service
public class IDelegatoServiceImpl implements IDelegatoService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PLFVDelegatoJpaRepository delegatoRepository;

    @Autowired
    private PLFImpresaJpaRepository impresaRespository;

    @Autowired
    private PLFTUtenteJpaRepository utenteRepository;

    @Autowired
    private PLFRUtenteImpresaJpaRepository utenteImpresaRepository;

    @Autowired
    private PLFServiziJpaRepository serviziJpaRepository;

    @Override
    public PLFVDelegatoEntity findDelegato(BigDecimal idUtente, BigDecimal idPlfImpresa) {
        return delegatoRepository.findUtenteDelegato(idUtente,idPlfImpresa);
    }

    @Override
    public List<PLFImpresaEntity> findImpreseDelegato(BigDecimal idUtente) {
        return impresaRespository.findImpreseDelegato(idUtente);
    }

    @Override
    public List<PLFVDelegatoEntity> findUtentiDelegati(BigDecimal idImpresa) {
        return delegatoRepository.findUtentiDelegati(idImpresa);
    }

    @Override
    public void save(PLFTUtenteEntity delegato, BigDecimal idImpresa) {

        delegato.setDataCreazione(new Date());
        delegato.setIdRuolo(new BigDecimal(UtenteDto.RUOLO_IMPRESA));
        utenteRepository.save(delegato);

        PLFRUtenteImpresaEntity ass = new PLFRUtenteImpresaEntity();
        ass.setIdImpresa(idImpresa);
        ass.setIdUtente(delegato.getIdUtente());

        utenteImpresaRepository.save(ass);
    }

    @Override
    public void saveAssociazione(BigDecimal idUtente, BigDecimal idPlfImpresa) {
        PLFRUtenteImpresaEntity ass = new PLFRUtenteImpresaEntity();
        ass.setIdUtente(idUtente);
        ass.setIdImpresa(idPlfImpresa);
        utenteImpresaRepository.save(ass);
    }

    @Override
    public void revocaDelegato(BigDecimal idPlfImpresa, BigDecimal idUtente) {
        //TODO se l'utente rimane senza imprese associate deve cambiare ruolo?
        PLFRUtenteImpresaEntity ass = new PLFRUtenteImpresaEntity();
        ass.setIdImpresa(idPlfImpresa);
        ass.setIdUtente(idUtente);

        utenteImpresaRepository.delete(ass);
    }

    @Override
    public List<PLFTUtenteEntity> searchDelegati(Map<String, String> parametri) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PLFTUtenteEntity> query = cb.createQuery(PLFTUtenteEntity.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<PLFTUtenteEntity> from = query.from(PLFTUtenteEntity.class);

        if(parametri.containsKey("nome"))
        {
            Expression<String> nome = cb.lower(from.<String>get("nome"));
            predicates.add(cb.like(nome,"%"+parametri.get("nome").toLowerCase()+"%"));
        }

        if(parametri.containsKey("cognome"))
        {
            Expression<String> cognome = cb.lower(from.<String>get("cognome"));
            predicates.add(cb.like(cognome,"%"+parametri.get("cognome").toLowerCase()+"%"));
        }

        if(parametri.containsKey("codiceFiscale"))
        {
            Expression<String> codiceFiscale = cb.lower(from.<String>get("codiceFiscale"));
            predicates.add(cb.like(codiceFiscale,"%"+parametri.get("codiceFiscale").toLowerCase()+"%"));
        }

        if(parametri.containsKey("email"))
        {
            Expression<String> email = cb.lower(from.<String>get("email"));
            predicates.add(cb.like(email,"%"+parametri.get("email").toLowerCase()+"%"));
        }

        query.select(from).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Page<PLFVDelegatoEntity> elencoDelegati(int numPage, int pageSize, CriteriQuery filtri) {
        Pageable pageable = new PageRequest(numPage, pageSize);
        Page<PLFVDelegatoEntity> result = delegatoRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVDelegatoEntity.class), pageable);
        return result;
    }

    @Override
    public List<PLFServiziEntity> findServiziImpreseDelegato(BigDecimal idUtente) {
        return serviziJpaRepository.findByDelegato(idUtente);
    }
}
