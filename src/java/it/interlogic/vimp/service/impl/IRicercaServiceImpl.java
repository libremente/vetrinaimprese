package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntityKey;
import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFVImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFVInformazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFVRichiestaAccreditamentoEntity;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFInformazioneImmagineJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFLogJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoErogazioneServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTVInformazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFVImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFVRichiestaAccreditamentoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.specs.CriteriQuery;
import it.interlogic.vimp.data.jpa.repository.specs.QueryBuilder;
import it.interlogic.vimp.service.INewsImpresaService;
import it.interlogic.vimp.service.IPacchettoServiziService;
import it.interlogic.vimp.service.IRicercaService;
import it.interlogic.vimp.service.IServiziService;
import it.interlogic.vimp.web.dto.ParametriRicercaMyInfo;
import it.interlogic.vimp.web.security.UtenteContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service("iRicercaService")
public class IRicercaServiceImpl implements IRicercaService
{
	@Autowired
	PLFTVInformazioneJpaRepository informazioneRepository;

	@Autowired
	PLFImpresaJpaRepository impresaRepository;

	@Autowired
	PLFProgettiProdottiJpaRepository progettiProdottiRepository;

	@Autowired
	PLFVImpresaJpaRepository vistaImpresaRepository;

	@Autowired
	PLFVRichiestaAccreditamentoJpaRepository vistaRichiestaAccreditamentoRepository;

	@Autowired
	PLFLogJpaRepository batchlogRepository;

	@Autowired
	IServiziService serviziStandardRepository;

	@Autowired
	IPacchettoServiziService pacchettoServiziRepository;

	@Autowired
	INewsImpresaService newsRepository;

	@Autowired
	PLFInformazioneImmagineJpaRepository immagineRepository;

	@Autowired
	PLFTTipoErogazioneServizioJpaRepository tipoErogazioneServizioRepository;

	@Override
	public Page<PLFVInformazioneEntity> findInformazioni(int numPage, int pageSize, String ricerca, boolean onlyPublic)
	{
		return findInformazioni(numPage, pageSize, null, ricerca, onlyPublic);
	}

	@Override
	public Page<PLFVInformazioneEntity> findInformazioni(int numPage, int pageSize, BigDecimal tipoInformazione, String ricerca, boolean onlyPublic)
	{
		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
				"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));

		CriteriQuery filtri = new CriteriQuery();

		if (tipoInformazione != null && IAbstractServiceImpl.TIPO_INFO_SERVIZIO == tipoInformazione.intValue())
			filtri.addEmbeddedFieldIn("compositePrimaryKey", "idTipoInformazione", new BigDecimal[] { tipoInformazione, new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO) });
		else if (tipoInformazione != null)
			filtri.addEmbeddedFieldEquals("compositePrimaryKey", "idTipoInformazione", tipoInformazione);

		if (ricerca != null && ricerca.trim().length() > 0)
			filtri.addParametroLike("ricerca", "%" + ricerca + "%");
		else
		{
			pageable = new PageRequest(numPage, 8, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
					"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));
		}

		Page<PLFVInformazioneEntity> result;

		if (onlyPublic)
		{
			filtri.addParametroEqual("pubblicato", new BigDecimal(1));
		}
		
		if (!UtenteContext.getCurrentUser().isBackoffice())
		{
			filtri.addParametroIsNull("dataCancellazione");
		}

		result = informazioneRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVInformazioneEntity.class), pageable);

		return result;
	}

	@Override
	public Page<PLFVInformazioneEntity> findInformazioniByStato(int numPage, int pageSize, BigDecimal tipoInformazione, String ricerca, BigDecimal[] stato, boolean onlyPublic)
	{

		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
				"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));

		CriteriQuery filtri = new CriteriQuery();

		if (tipoInformazione != null && IAbstractServiceImpl.TIPO_INFO_SERVIZIO == tipoInformazione.intValue())
			filtri.addEmbeddedFieldIn("compositePrimaryKey", "idTipoInformazione", new BigDecimal[] { tipoInformazione, new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO) });
		else
			filtri.addEmbeddedFieldEquals("compositePrimaryKey", "idTipoInformazione", tipoInformazione);

		if (stato != null && stato.length > 0)
		{
			filtri.addParametroIn("stato", stato);
		}
		else
		{
			filtri.addParametroIn("stato", new BigDecimal[] { new BigDecimal(-1) });
		}

		if (ricerca != null && ricerca.trim().length() > 0)
			filtri.addParametroLike("ricerca", "%" + ricerca + "%");
		else
		{
			// LIMIT 8
			pageable = new PageRequest(numPage, 8, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
					"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));
		}

		if (onlyPublic)
		{
			filtri.addParametroEqual("pubblicato", new BigDecimal(1));
		}
		
		if (!UtenteContext.getCurrentUser().isBackoffice())
		{
			filtri.addParametroIsNull("dataCancellazione");
		}

		Page<PLFVInformazioneEntity> result = informazioneRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVInformazioneEntity.class), pageable);
		return result;

	}

	@Override
	public Page<PLFVInformazioneEntity> findInformazioniByTipo(int numPage, int pageSize, BigDecimal[] tipoInformazione, String ricerca, boolean onlyPublic)
	{

		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
				"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));

		CriteriQuery filtri = new CriteriQuery();

		if (tipoInformazione != null && tipoInformazione.length > 0)
		{

			filtri.addEmbeddedFieldIn("compositePrimaryKey", "idTipoInformazione", tipoInformazione);

			if (ricerca != null && ricerca.trim().length() > 0)
				filtri.addParametroLike("ricerca", "%" + ricerca + "%");
			else
			{
				// LIMIT 8
				pageable = new PageRequest(numPage, 8, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
						"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));
			}

			if (onlyPublic)
			{
				filtri.addParametroEqual("pubblicato", new BigDecimal(1));
			}
			
			if (!UtenteContext.getCurrentUser().isBackoffice())
			{
				filtri.addParametroIsNull("dataCancellazione");
			}

			Page<PLFVInformazioneEntity> result = informazioneRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVInformazioneEntity.class), pageable);

			return result;
		}
		return null;

	}

	@Override
	public Page<PLFVInformazioneEntity> findInformazioniByTipoStato(int numPage, int pageSize, BigDecimal[] tipoInformazione, String ricerca, BigDecimal[] stato, boolean onlyPublic)
	{
		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
				"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));

		CriteriQuery filtri = new CriteriQuery();

		if (tipoInformazione != null && tipoInformazione.length > 0)
		{

			filtri.addEmbeddedFieldIn("compositePrimaryKey", "idTipoInformazione", tipoInformazione);

			if (stato != null && stato.length > 0)
			{
				filtri.addParametroIn("stato", stato);
			}

			if (ricerca != null && ricerca.trim().length() > 0)
				filtri.addParametroLike("ricerca", "%" + ricerca + "%");
			else
			{
				// LIMIT 8
				pageable = new PageRequest(numPage, 8, new Sort(new Order(Direction.DESC, "sortScadenza"), new Order(Direction.DESC, "scadenza"), new Order(Direction.DESC,
						"dataAggiornamento"), new Order(Direction.DESC, "numeroVisite"), new Order(Direction.DESC, "dataUltimaVisita")));
			}

			if (onlyPublic)
			{
				filtri.addParametroEqual("pubblicato", new BigDecimal(1));
			}
			
			if (!UtenteContext.getCurrentUser().isBackoffice())
			{
				filtri.addParametroIsNull("dataCancellazione");
			}

			Page<PLFVInformazioneEntity> result = informazioneRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVInformazioneEntity.class), pageable);

			return result;
		}
		return null;
	}

	@Override
	public long countProdotti()
	{
		return progettiProdottiRepository.countAll(new BigDecimal(1));
	}

	@Override
	public long countProdottiTecnologie()
	{
		return progettiProdottiRepository.countProdottiTecnologie();
	}

	@Override
	public long countStartup()
	{
		return impresaRepository.countAll(new BigDecimal(3), new BigDecimal(6));
	}

	@Override
	public long countPmi()
	{
		return impresaRepository.countAll(new BigDecimal(4), new BigDecimal(5));
	}

	@Override
	public long countProgetti()
	{
		return progettiProdottiRepository.countAll(new BigDecimal(1));
	}

	@Override
	public long countSpinoff()
	{
		return impresaRepository.countAll(new BigDecimal(1), new BigDecimal(2));
	}

	@Override
	public long countGrandi()
	{
		return impresaRepository.countAll(new BigDecimal(7), new BigDecimal(7));
	}

	@Override
	public Page<PLFVImpresaEntity> elencoImprese(int numPage, int pageSize, CriteriQuery filtri)
	{
		Pageable pageable = new PageRequest(numPage, pageSize);
		Page<PLFVImpresaEntity> result = vistaImpresaRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVImpresaEntity.class), pageable);
		return result;
	}

	@Override
	public Page<PLFLogEntity> elencoBatchLog(int numPage, int pageSize)
	{
		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(new Order(Direction.DESC, "idLog")));
		Page<PLFLogEntity> result = batchlogRepository.findAll(pageable);
		return result;
	}

	@Override
	public Page<PLFVRichiestaAccreditamentoEntity> elencoRichiesteAccreditamento(int numPage, int pageSize, CriteriQuery filtri)
	{
		Pageable pageable = new PageRequest(numPage, pageSize, new Sort(Direction.DESC, "dataRichiesta"));
		Page<PLFVRichiestaAccreditamentoEntity> result = vistaRichiestaAccreditamentoRepository.findAll(QueryBuilder.buildQuery(filtri, PLFVRichiestaAccreditamentoEntity.class),
				pageable);
		return result;
	}

	// ==============================================================================================================
	// MY INFO
	// ==============================================================================================================

	@Override
	public List<PLFVInformazioneEntity> findInformazioniPersonal(ParametriRicercaMyInfo parametri)
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();

		if (UtenteContext.getCurrentUser() != null && (UtenteContext.getCurrentUser().isImpresa() || UtenteContext.getCurrentUser().isStakeholder())
				&& UtenteContext.getCurrentUser().getPlfImpresas() != null && UtenteContext.getCurrentUser().getPlfImpresas().size() > 0)
		{

			List<PLFVInformazioneEntity> imprese = getMyInfoImpresa();

			List<PLFVInformazioneEntity> sortedList = new ArrayList<PLFVInformazioneEntity>();

			boolean scadute = "S".equalsIgnoreCase(parametri.getFindScadute());
			
			List<PLFVInformazioneEntity> servizi = getMyInfoServizi(parametri.getTextRicerca(), scadute);
			if ("S".equalsIgnoreCase(parametri.getFindServizi()))
				sortedList.addAll(servizi);
			if ("S".equalsIgnoreCase(parametri.getFindPacchetti()))
				sortedList.addAll(getMyInfoPacchettiServizi(servizi, parametri.getTextRicerca(),scadute));


			if ("S".equalsIgnoreCase(parametri.getFindProgetti()))
				sortedList.addAll(getMyInfoProgettoProdotti(parametri.getTextRicerca(),scadute, IAbstractServiceImpl.TIPO_PROGETTO));
			if ("S".equalsIgnoreCase(parametri.getFindProdotti()))
				sortedList.addAll(getMyInfoProgettoProdotti(parametri.getTextRicerca(),scadute, IAbstractServiceImpl.TIPO_PRODOTTO));
			if ("S".equalsIgnoreCase(parametri.getFindTecnologie()))
				sortedList.addAll(getMyInfoProgettoProdotti(parametri.getTextRicerca(),scadute, IAbstractServiceImpl.TIPO_TECNOLOGIA));
			
			

			if ("S".equalsIgnoreCase(parametri.getFindNews()))
				sortedList.addAll(getMyInfoNews(parametri.getTextRicerca(),scadute));

			ret.addAll(imprese);
			Collections.sort(sortedList, new InformazioneComparator());
			ret.addAll(sortedList);
		}
		return ret;
	}

	public class InformazioneComparator implements Comparator<PLFVInformazioneEntity>
	{
		@Override
		public int compare(PLFVInformazioneEntity o1, PLFVInformazioneEntity o2)
		{
			int ret = 0;

			if (o1.isScaduto() && !o2.isScaduto())
				ret = 1;
			else if (!o1.isScaduto() && o2.isScaduto())
				ret = -1;
			else
			{
				Date date1 = o1.getDataAggiornamento();
				if (o1.getDataUltimaVisita() != null)
					date1 = o1.getDataUltimaVisita();
				
				Date date2 = o2.getDataAggiornamento();
				if (o2.getDataUltimaVisita() != null)
					date2 = o2.getDataUltimaVisita();
				
				if (date1 != null && date2 != null)
					ret = date2.compareTo(date1);
				
				if (date1 != null && date2 != null)
					ret = date2.compareTo(date1);
				if (date1 != null && date2 == null)
					return -1;
				if (date1 == null && date2 != null)
					return 1;
			}
			return ret;
		}
	}

	private List<PLFVInformazioneEntity> getMyInfoImpresa()
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();
		for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
		{
			List<PLFVInformazioneEntity> list = null;
			if (UtenteContext.getCurrentUser().isImpresa())
				list = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_IMPRESA), impresa.getIdPlfImpresa());
			else if (UtenteContext.getCurrentUser().isStakeholder())
				list = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER), impresa.getIdPlfImpresa());
			if (list != null && list.size() > 0)
				ret.addAll(list);
		}
		return ret;
	}

	private List<PLFVInformazioneEntity> getMyInfoServizi(String ricerca, boolean scadute)
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();
		for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
		{
			List<PLFServiziEntity> servizi = null;
			if (ricerca != null && ricerca.trim().length() > 0)
				servizi = serviziStandardRepository.findAllByImpresa(impresa.getIdPlfImpresa(), "%" + ricerca.toUpperCase() + "%");
			else
				servizi = serviziStandardRepository.findAllByImpresa(impresa.getIdPlfImpresa());
			if (servizi != null && servizi.size() > 0)
			{
				List<BigDecimal> ids = new ArrayList<BigDecimal>();

				for (PLFServiziEntity servizio : servizi)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO), servizio.getIdServizi(),
								"%" + ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO), servizio.getIdServizi());

					if (listInfo != null && listInfo.size() > 0)
						ret.addAll(listInfo);
					else
					{
						// QUESTA EVOLUTIVA PER VEDERE I SERVIZI SCADUTI
						// NELLE MIE INFO

						// non presente nella vista quindi lo carico a mano
						PLFVInformazioneEntity e = new PLFVInformazioneEntity();
						e.setIdInformazione(servizio.getIdServizi());
						e.setIdTipoInformazione(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO));

						PLFInformazioneImmagineEntityKey key = new PLFInformazioneImmagineEntityKey(servizio.getIdServizi(),
								new BigDecimal(IAbstractServiceImpl.TIPO_INFO_SERVIZIO));
						PLFInformazioneImmagineEntity entity = immagineRepository.findOne(key);
						if (entity != null)
							e.setImmagine(entity.getImmagine());

						if ("S".equalsIgnoreCase(servizio.getServiziStandard()))
						{
							if (servizio.getDenominazioneServizio() != null)
								e.setTitolo(servizio.getDenominazioneServizio().getDescrizione());
							else
								e.setTitolo(servizio.getDescrizione());
						}
						else
							e.setTitolo(servizio.getTitolo());

						e.setPrima(servizio.getDescrizione());

						if (servizio.getPlfTAreeCompetenza() != null && servizio.getPlfTAreeCompetenza().getDescrizione() != null)
							e.setSeconda(servizio.getPlfTAreeCompetenza().getDescrizione());

						e.setScadenza(servizio.getDataFine());
						e.setDataAggiornamento(servizio.getDataInizio());

						List<PLFTTipoErogazioneServizioEntity> erogazioni = tipoErogazioneServizioRepository.findByServizio(servizio.getIdServizi());
						if (erogazioni != null)
						{
							String erogazioneStr = "";

							for (PLFTTipoErogazioneServizioEntity erogazione : erogazioni)
								erogazioneStr += erogazione.getDescrizione() + ", ";
							if (erogazioneStr.length() > 2)
								erogazioneStr = erogazioneStr.substring(0, erogazioneStr.length() - 2);
							e.setTerza(erogazioneStr);
						}

						ret.add(e);
					}

					ids.add(servizio.getIdServizi());
				}
			}
		}
		
		
		if (!scadute)
		{
			List<PLFVInformazioneEntity> newRet = new ArrayList<PLFVInformazioneEntity>();
			for (PLFVInformazioneEntity e: ret)
				if (!e.isScaduto())
					newRet.add(e);
			return newRet;
		}
		return ret;
	}

	private List<PLFVInformazioneEntity> getMyInfoPacchettiServizi(List<PLFVInformazioneEntity> servizi, String ricerca, boolean scadute)
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();
		if (servizi != null && servizi.size() > 0)
		{
			List<BigDecimal> ids = new ArrayList<BigDecimal>();
			for (PLFVInformazioneEntity servizio : servizi)
				ids.add(servizio.getIdInformazione());

			List<PLFPacchettoServiziEntity> pacchetti = pacchettoServiziRepository.findByIdsServiziCompleto(ids);
			if (pacchetti != null && pacchetti.size() > 0)
			{
				for (PLFPacchettoServiziEntity pacchetto : pacchetti)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI), pacchetto.getIdPacchettoServizi(), "%"
								+ ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI), pacchetto.getIdPacchettoServizi());
					if (listInfo != null && listInfo.size() > 0)
						ret.addAll(listInfo);
				}
			}
		}
		if (!scadute)
		{
			List<PLFVInformazioneEntity> newRet = new ArrayList<PLFVInformazioneEntity>();
			for (PLFVInformazioneEntity e: ret)
				if (!e.isScaduto())
					newRet.add(e);
			return newRet;
		}
		return ret;
	}

	private List<PLFVInformazioneEntity> getMyInfoProgettoProdotti(String ricerca, boolean scadute, int tipoProgettoProdotto)
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();
		for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
		{
			List<PLFProgettiProdottiEntity> progettiProdotti = progettiProdottiRepository.findProgettiByIdImpresaTipo(impresa.getIdPlfImpresa(), new BigDecimal(tipoProgettoProdotto));
			if (progettiProdotti != null && progettiProdotti.size() > 0)
			{
				for (PLFProgettiProdottiEntity progettoProdotto : progettiProdotti)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO),
								progettoProdotto.getIdPlfProgettiProdotti(), "%" + ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO),
								progettoProdotto.getIdPlfProgettiProdotti());
					if (listInfo != null && listInfo.size() > 0)
						ret.addAll(listInfo);
				}
			}
		}
		if (!scadute)
		{
			List<PLFVInformazioneEntity> newRet = new ArrayList<PLFVInformazioneEntity>();
			for (PLFVInformazioneEntity e: ret)
				if (!e.isScaduto())
					newRet.add(e);
			return newRet;
		}
		return ret;
	}

	private List<PLFVInformazioneEntity> getMyInfoNews(String ricerca, boolean scadute)
	{
		List<PLFVInformazioneEntity> ret = new ArrayList<PLFVInformazioneEntity>();
		for (PLFImpresaEntity impresa : UtenteContext.getCurrentUser().getPlfImpresas())
		{
			// news by impresa
			List<PLFNewsImpresaEntity> news = newsRepository.findByImpresa(impresa.getIdPlfImpresa());
			if (news != null && news.size() > 0)
			{
				for (PLFNewsImpresaEntity n : news)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa(), "%" + ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa());
					
					if (listInfo != null && listInfo.size() > 0)
					{
						addOnlyNew(ret, listInfo);
					}
				}
			}

			// news by progetto
			List<PLFNewsImpresaEntity> newsProj = newsRepository.findByProgettoImpresa(impresa.getIdPlfImpresa());
			if (newsProj != null && newsProj.size() > 0)
			{
				for (PLFNewsImpresaEntity n : newsProj)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa(), "%" + ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa());
					if (listInfo != null && listInfo.size() > 0)
					{
						addOnlyNew(ret, listInfo);
					}
				}
			}

			// news by servizio
			List<PLFNewsImpresaEntity> newServizi = newsRepository.findByServizi(impresa.getIdPlfImpresa());
			if (newServizi != null && newServizi.size() > 0)
			{
				for (PLFNewsImpresaEntity n : newServizi)
				{
					List<PLFVInformazioneEntity> listInfo = null;
					if (ricerca != null && ricerca.trim().length() > 0)
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa(), "%" + ricerca.toUpperCase() + "%");
					else
						listInfo = informazioneRepository.findInformazioni(new BigDecimal(IAbstractServiceImpl.TIPO_INFO_NEWS), n.getIdNewsImpresa());
					if (listInfo != null && listInfo.size() > 0)
					{
						addOnlyNew(ret, listInfo);
					}
				}
			}
		}
		if (!scadute)
		{
			List<PLFVInformazioneEntity> newRet = new ArrayList<PLFVInformazioneEntity>();
			for (PLFVInformazioneEntity e: ret)
				if (!e.isScaduto())
					newRet.add(e);
			return newRet;
		}
		return ret;
	}

	private void addOnlyNew(List<PLFVInformazioneEntity> listaInfos, List<PLFVInformazioneEntity> toAddList)
	{

		for (PLFVInformazioneEntity objToAdd : toAddList)
		{
			boolean found = false;
			for (PLFVInformazioneEntity informazione : listaInfos)
			{
				if (informazione.getIdInformazione().equals(objToAdd.getIdInformazione()) && informazione.getIdTipoInformazione().equals(objToAdd.getIdTipoInformazione()))
				{
					found = true;
				}
			}
			if (!found)
			{
				listaInfos.add(objToAdd);
			}
		}
	}

}
