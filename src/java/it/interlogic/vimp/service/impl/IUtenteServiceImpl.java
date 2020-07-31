package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFParameter;
import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVUtenteEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntityKey;
import it.interlogic.vimp.data.jpa.repository.PLFParameterJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTLogAuditJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTUtenteJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTVUtenteJpaRepository;
import it.interlogic.vimp.data.jpa.repository.relation.PLFRUtenteImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.data.jpa.repository.specs.QueryBuilder;
import it.interlogic.vimp.domain.UtenteDto;
import it.interlogic.vimp.service.IUtenteService;
import it.interlogic.vimp.service.exception.AuthenticationException;
import it.interlogic.vimp.service.exception.PasswordExpiredException;
import it.interlogic.vimp.service.exception.PasswordTemporanyException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("iUtenteService")
public class IUtenteServiceImpl implements IUtenteService
{

	@Autowired
	PLFTUtenteJpaRepository utenteRepository;

	@Autowired
	PLFTVUtenteJpaRepository utenteRicercaRepository;

	@Autowired
	PLFRUtenteImpresaJpaRepository utenteImpresaRepository;
	
	@Autowired
	PLFTLogAuditJpaRepository logRepository;
	
	@Autowired
	PLFParameterJpaRepository parameterRepository;

	@Override
	public PLFTUtenteEntity accreditaUtente(String codiceFiscale, String email, BigDecimal idImpresa, String nome, String cognome)
	{
		// TODO DELEGATI INVECE DI R
		try
		{
			PLFTUtenteEntity utente = utenteRepository.findUtenteByCodiceFiscale(codiceFiscale);
			if (utente == null)
			{
				utente = new PLFTUtenteEntity();
				
				if (nome != null && nome.trim().length()>0)
					utente.setNome(nome);
				else
					utente.setNome(".");
				
				if (cognome != null && cognome.trim().length()>0)
					utente.setCognome(cognome);
				else
					utente.setCognome(".");
				
				utente.setCodiceFiscale(codiceFiscale);
				utente.setEmail(email);
				utente.setIdRuolo(new BigDecimal(UtenteDto.RUOLO_IMPRESA));
				utente.setDataCreazione(new Date());

			}


			PLFTUtenteEntity entity = utenteRepository.save(utente);
			if (entity != null && entity.getIdUtente() != null && entity.getIdUtente().intValue() > 0)
			{
				PLFRUtenteImpresaEntity utenteImpresa = new PLFRUtenteImpresaEntity();
				utenteImpresa.setIdUtente(entity.getIdUtente());
				utenteImpresa.setIdImpresa(idImpresa);
				utenteImpresaRepository.save(utenteImpresa);
				return findUtente(entity.getIdUtente());
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		return null;
	}

	@Override
	public PLFTUtenteEntity findUtente(BigDecimal id)
	{
		PLFTUtenteEntity utente = utenteRepository.findOne(id);
		return fillerUtente(utente);
	}

	@Override
	public PLFTUtenteEntity findUtenteByCodiceFiscale(String codiceFiscale)
	{
		PLFTUtenteEntity utente = utenteRepository.findUtenteByCodiceFiscale(codiceFiscale);
		return fillerUtente(utente);
	}

	@Override
	public PLFTUtenteEntity findUtenteByEmail(String email)
	{
		PLFTUtenteEntity utente = utenteRepository.findUtenteByEmail(email);
		return fillerUtente(utente);
	}

	public PLFTUtenteEntity fillerUtente(PLFTUtenteEntity utente)
	{
		if (utente != null && utente.getIdUtente() != null && utente.getIdUtente().intValue() > 0)
		{
			List<Object[]> list = utenteRepository.findImpresa(utente.getIdUtente());
			if (list != null && list.size() > 0)
			{
				List<PLFImpresaEntity> imprese = new ArrayList<PLFImpresaEntity>();
				for (int i = 0; i < list.size(); i++)
				{
					Object[] obj = list.get(i);
					PLFImpresaEntity impresa = new PLFImpresaEntity();
					impresa.setIdPlfImpresa(new BigDecimal(((BigInteger) obj[0]).intValue()));
					impresa.setImpresaTranslation(new PLFImpresaTranslationEntity());
					impresa.getImpresaTranslation().setDescImpresa((String) obj[1]);
					
					PLFTTipoImpresaEntity tipo = new PLFTTipoImpresaEntity();
					tipo.setId(new BigDecimal(((BigInteger) obj[2]).intValue()));
					impresa.setPlfTTipoImpresa(tipo);

					imprese.add(impresa);
				}
				utente.setPlfImpresas(imprese);
			}
		}
		return utente;
	}
	
	

	@Override
	public List<PLFTUtenteEntity> findAllUtenti()
	{
		return utenteRepository.findAllUtenti();
	}

	@Override
	public Page<PLFVUtenteEntity> findUtenti(int numPage, int pageSize, String ricerca)
	{
		Pageable pageable = new PageRequest(numPage, pageSize);
		CriteriQuery filtri = new CriteriQuery();

		// filtri.addParametroNotEqual("email", "admin");

		if (ricerca != null && ricerca.trim().length() > 0)
			filtri.addParametroLike("ricerca", "%" + ricerca + "%");
		else
		{
			pageable = new PageRequest(numPage, pageSize);
		}

		Page<PLFVUtenteEntity> result = utenteRicercaRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVUtenteEntity.class), pageable);
		return result;
	}

	@Override
	public PLFTUtenteEntity login(String email, String passwordStr) throws AuthenticationException, PasswordExpiredException, PasswordTemporanyException
	{
		PLFTUtenteEntity utente = findUtenteByEmail(email);
		if (utente != null && utente.getIdUtente() != null && utente.getIdUtente().intValue() > 0)
		{
			return utente;
		}
		throw new AuthenticationException("Login errato");
	}

	
	@Override
	public void lodAudit(PLFTLogAuditEntity log)
	{
		log = logRepository.save(log);
	}
	
	
	@Override
	public String getParameter(String name)
	{
		List<PLFParameter> list = parameterRepository.findParamByName(name);
		if (list != null && list.size()>0)
			return list.get(0).getValue();
		
		return null;
	}
	
	
	@Override
	public PLFTUtenteEntity update(PLFTUtenteEntity utente, BigDecimal idImpresa)
	{		
		PLFTUtenteEntity entity = utenteRepository.save(utente);
		if (entity != null && entity.getIdUtente() != null && entity.getIdUtente().intValue() > 0)
		{
			PLFRUtenteImpresaEntity utenteImpresa = new PLFRUtenteImpresaEntity();
			utenteImpresa.setIdUtente(entity.getIdUtente());
			utenteImpresa.setIdImpresa(idImpresa);
			utenteImpresaRepository.save(utenteImpresa);
			return findUtente(entity.getIdUtente());
		}
		
		return utente;
	}
	
	
	@Override
	public PLFTUtenteEntity deleteUtenteImpresa(PLFTUtenteEntity utente, BigDecimal idImpresa,Date dataCancellazione)
	{	
		PLFRUtenteImpresaEntityKey utenteImpresaKey = new PLFRUtenteImpresaEntityKey();
		utenteImpresaKey.setIdUtente(utente.getIdUtente());
		utenteImpresaKey.setIdImpresa(idImpresa);
		PLFRUtenteImpresaEntity utenteImpresa = utenteImpresaRepository.findOne(utenteImpresaKey);
		if (utenteImpresa != null && utenteImpresa.getIdImpresa()!= null && utenteImpresa.getIdImpresa().intValue()>0)
		{
			utenteImpresa.setDataCancellazione(dataCancellazione);
		}
		return utente;
	}
}
