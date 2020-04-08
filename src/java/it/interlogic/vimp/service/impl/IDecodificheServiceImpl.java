package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.data.dao.DecodificaRestHelper;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAreeCompetenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAtecoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseAddettiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseCapitaleEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseProduzioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTControlliRichiestaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTDenominazioneServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTLogMessaggiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMacroareaServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMercatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazionePacchettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTNaturaGiuridicaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTNazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTOrigineImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTPrevalenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTProvinciaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRegioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRuoloAziendaleEntity;
import it.interlogic.vimp.data.jpa.model.PLFTRuoloEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreProgettiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreTecnologieEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSezioneSpecialeImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSoggAmmissibiliEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoProgettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoRichiestaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoAllegatoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoNewsEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoOrganizzazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoProgettoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoProgrammaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipologiaProgettoEntity;
import it.interlogic.vimp.data.jpa.model.TranslatableCodifica;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTAreeCompetenzaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTAteco2007JpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTAtecoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTClasseAddettiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTClasseCapitaleJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTClasseProduzioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTComuneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTControlliRichiestaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTDenominazioneServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTInnovazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTLogMessaggiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTMacroareaServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTMercatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTModalitaErogazionePacchettoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTModalitaErogazioneServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTNaturaGiuridicaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTNazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTOrigineImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTPrevalenzaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTProvinciaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTRegioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTRuoloAziendaleJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTRuoloJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreProgettiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSettoreTecnologieJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSezioneSpecialeImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTSoggAmmissibiliJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTStatoImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTStatoProgettoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTStatoRichiestaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTagJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoAllegatoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoErogazioneServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoNewsJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoOrganizzazioneJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoProgettoJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoProgrammaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipoServizioJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFTTipologiaProgettoJpaRepository;
import it.interlogic.vimp.service.IDecodificheService;
import it.interlogic.vimp.utils.LoggerUtility;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

@Service("iDecodificheService")
public class IDecodificheServiceImpl implements IDecodificheService
{
	@Autowired
	PLFTControlliRichiestaJpaRepository controlliRichiestaRepository;

	@Autowired
	PLFTLogMessaggiJpaRepository logMessaggiRepository;

	@Autowired
	PLFTOrigineImpresaJpaRepository origineImpresaRepository;

	@Autowired
	PLFTSezioneSpecialeImpresaJpaRepository sezioneSpecialeImpresaRepository;

	@Autowired
	PLFTStatoImpresaJpaRepository statoImpresaRepository;

	@Autowired
	PLFTStatoRichiestaJpaRepository statoRichiestaRepository;

	@Autowired
	PLFTTipoAllegatoJpaRepository tipoAllegatoRepository;

	@Autowired
	PLFTTipoImpresaJpaRepository tipoImpresaRepository;

	@Autowired
	PLFTTipoProgettiProdottiJpaRepository tipoProgettiProdottiRepository;

	@Autowired
	PLFTTipologiaProgettoJpaRepository tipologiaProgettoRepository;

	@Autowired
	PLFTTipoProgrammaJpaRepository tipoProgrammaRepository;

	@Autowired
	PLFTTipoProgettoJpaRepository tipoProgettoRepository;

	@Autowired
	PLFTTipoOrganizzazioneJpaRepository tipoOrganizzazioneRepository;

	@Autowired
	PLFTStatoProgettoJpaRepository statoProgettoRepository;

	@Autowired
	PLFTSoggAmmissibiliJpaRepository soggAmmissibiliRepository;

	@Autowired
	PLFTSettoreImpresaJpaRepository settoreImpresaRepository;

	@Autowired
	PLFTSettoreProgettiJpaRepository settoreProgettiRepository;

	@Autowired
	PLFTAteco2007JpaRepository ateco2007Repository;

	@Autowired
	PLFTAtecoJpaRepository atecoRepository;

	@Autowired
	PLFTRegioneJpaRepository regioneRepository;

	@Autowired
	PLFTProvinciaJpaRepository provinciaRepository;

	@Autowired
	PLFTComuneJpaRepository comuneRepository;

	@Autowired
	PLFTNazioneJpaRepository nazioneRepository;

	@Autowired
	PLFTRuoloJpaRepository ruoloRepository;

	@Autowired
	PLFTClasseAddettiJpaRepository classeAddettiRepository;

	@Autowired
	PLFTClasseProduzioneJpaRepository classeProduzioneRepository;

	@Autowired
	PLFTClasseCapitaleJpaRepository classeCapitaleRepository;

	@Autowired
	PLFTPrevalenzaJpaRepository prevalenzaRepository;

	@Autowired
	PLFTAreeCompetenzaJpaRepository areeCompetenzaRepository;

	@Autowired
	PLFTTipoNewsJpaRepository tipoNewsRepository;

	@Autowired
	PLFTNaturaGiuridicaJpaRepository naturaGiuridicaRepository;

	@Autowired
	PLFTRuoloAziendaleJpaRepository ruoloAziendaleRepository;

	@Autowired
	PLFTInnovazioneJpaRepository innovazioneRepository;

	@Autowired
	PLFTMercatiJpaRepository mercatiRepository;

	@Autowired
	PLFImpresaJpaRepository impresaRepository;

	@Autowired
	PLFTTipoServizioJpaRepository tipoServizioRepository;

	@Autowired
	PLFTTagJpaRepository tagRepository;

	@Autowired
	PLFTSettoreProgettiProdottiJpaRepository settoreProgettiProdottiRepository;

	@Autowired
	PLFTSettoreTecnologieJpaRepository settoreTecnologieJpaRepository;

	@Autowired
	DecodificaRestHelper drh;

	@Autowired
	PLFTDenominazioneServiziJpaRepository denominazioneServiziRepository;

	@Autowired
	PLFTModalitaErogazioneServizioJpaRepository modalitaErogazioneServizioRepository;

	@Autowired
	PLFTTipoErogazioneServizioJpaRepository tipoErogazioneRepository;

	@Autowired
	PLFTMacroareaServiziJpaRepository macroareaRepository;

	@Autowired
	PLFTModalitaErogazionePacchettoJpaRepository modalitaErogazionePacchettoRepository;

	@Override
	public List<PLFTTipoServizioEntity> getTipoServizio()
	{
		List<PLFTTipoServizioEntity> ret = (List<PLFTTipoServizioEntity>) tipoServizioRepository.findTipoServizio();

		return ret;
	}
	
	@Override
	public List<PLFTTipoServizioEntity> getTipoServizioByDescrizione(String descrizione)
	{
		List<PLFTTipoServizioEntity> ret = (List<PLFTTipoServizioEntity>) tipoServizioRepository.findTipoServizioPerDescrizione(descrizione);
		return ret;
	}
	

	@Override
	public List<PLFTTipoProgrammaEntity> getTipoProgramma()
	{
		return (List<PLFTTipoProgrammaEntity>) tipoProgrammaRepository.findTipoProgramma();
	}

	@Override
	public List<PLFTTipoProgrammaEntity> getTipoProgrammaByCodice(String codice)
	{
		return (List<PLFTTipoProgrammaEntity>) tipoProgrammaRepository.findTipoProgrammaByCodice(codice);
	}

	@Override
	public PLFTTipoProgrammaEntity getTipoProgramma(BigDecimal idTipoProgramma)
	{
		return (PLFTTipoProgrammaEntity) tipoProgrammaRepository.findOne(idTipoProgramma);
	}

	@Override
	public List<PLFTTipoProgettoEntity> getTipoProgetto()
	{
		return (List<PLFTTipoProgettoEntity>) tipoProgettoRepository.findTipoProgetto();
	}

	@Override
	public List<PLFTTipoProgettoEntity> getTipoProgettoByCodice(String codProgetto)
	{
		return (List<PLFTTipoProgettoEntity>) tipoProgettoRepository.findTipoProgettoByCodProgetto(codProgetto);
	}

	@Override
	public PLFTTipoProgettoEntity getTipoProgetto(BigDecimal idTipoProgetto)
	{
		return (PLFTTipoProgettoEntity) tipoProgettoRepository.findOne(idTipoProgetto);
	}

	@Override
	public List<PLFTTipoOrganizzazioneEntity> getTipoOrganizzazione()
	{
		return (List<PLFTTipoOrganizzazioneEntity>) tipoOrganizzazioneRepository.findTipoOrganizzazione();
	}

	@Override
	public PLFTTipoOrganizzazioneEntity getTipoOrganizzazione(BigDecimal idTipoOrganizzazione)
	{
		return (PLFTTipoOrganizzazioneEntity) tipoOrganizzazioneRepository.findOne(idTipoOrganizzazione);
	}

	@Override
	public List<PLFTTipoOrganizzazioneEntity> getTipoOrganizzazionePerCodice(String codice)
	{
		return (List<PLFTTipoOrganizzazioneEntity>) tipoOrganizzazioneRepository.findTipoOrganizzazionePerCodice(codice);
	}

	@Override
	public List<PLFTStatoProgettoEntity> getStatoProgetto()
	{
		return (List<PLFTStatoProgettoEntity>) statoProgettoRepository.findStatoProgetto();
	}

	@Override
	public List<PLFTStatoProgettoEntity> getStatoProgettoByCodice(String codice)
	{
		return (List<PLFTStatoProgettoEntity>) statoProgettoRepository.findStatoProgettoByCodice(codice);
	}

	@Override
	public PLFTStatoProgettoEntity getStatoProgetto(BigDecimal idStatoProgetto)
	{
		return (PLFTStatoProgettoEntity) statoProgettoRepository.findOne(idStatoProgetto);
	}

	@Override
	public List<PLFTSoggAmmissibiliEntity> getSoggAmmissibili()
	{
		return (List<PLFTSoggAmmissibiliEntity>) soggAmmissibiliRepository.findSoggAmmissibili();
	}

	@Override
	public List<PLFTSoggAmmissibiliEntity> getSoggAmmissibiliByCodice(String codice)
	{
		return (List<PLFTSoggAmmissibiliEntity>) soggAmmissibiliRepository.findSoggAmmissibiliByCodice(codice);
	}

	@Override
	public PLFTSoggAmmissibiliEntity getSoggAmmissibili(BigDecimal idSoggAmmissibili)
	{
		return (PLFTSoggAmmissibiliEntity) soggAmmissibiliRepository.findOne(idSoggAmmissibili);
	}

	@Override
	public List<PLFTSettoreImpresaEntity> getSettoreImpresa()
	{
		return (List<PLFTSettoreImpresaEntity>) settoreImpresaRepository.findSettoreImpresa();
	}

	@Override
	public List<PLFTSettoreImpresaEntity> getSettoreImpresaPerCodice(String codSettore)
	{
		return (List<PLFTSettoreImpresaEntity>) settoreImpresaRepository.findSettoreImpresaPerCodice(codSettore);
	}

	@Override
	public List<PLFTSettoreImpresaEntity> getSettoreImpresaPerDescrizione(String descrizione)
	{
		if (descrizione != null && descrizione.trim().length() > 0)
		{
			descrizione = descrizione.toUpperCase();
			return settoreImpresaRepository.findSettoreImpresaPerDescrizione(descrizione.trim().toUpperCase());
		}
		return null;
	}

	@Override
	public PLFTSettoreImpresaEntity getSettoreImpresa(BigDecimal idSettore)
	{
		return (PLFTSettoreImpresaEntity) settoreImpresaRepository.findOne(idSettore);
	}

	@Override
	public List<PLFTSettoreProgettiEntity> getSettoreProgetti()
	{
		return (List<PLFTSettoreProgettiEntity>) settoreProgettiRepository.findSettoreProgetti();
	}

	@Override
	public List<PLFTSettoreProgettiEntity> getSettoreProgettiPerCodice(String codSettore)
	{
		return (List<PLFTSettoreProgettiEntity>) settoreProgettiRepository.findSettoreProgettiPerCodice(codSettore);
	}

	@Override
	public PLFTSettoreProgettiEntity getSettoreProgetti(BigDecimal idSettore)
	{
		return (PLFTSettoreProgettiEntity) settoreProgettiRepository.findOne(idSettore);
	}

	// @Override
	// public List<PLFTAteco2007Entity> getAteco2007()
	// {
	// return (List<PLFTAteco2007Entity>) ateco2007Repository.findAteco2007();
	// }
	//
	// @Override
	// public List<PLFTAteco2007Entity> getAteco2007PerCodice(String codice)
	// {
	// return (List<PLFTAteco2007Entity>)
	// ateco2007Repository.findAteco2007PerCodice(codice);
	// }
	//
	// @Override
	// public List<PLFTAteco2007Entity> getAteco2007PerDescrizione(String
	// descrizione)
	// {
	// return (List<PLFTAteco2007Entity>)
	// ateco2007Repository.getAteco2007PerDescrizione("%" + descrizione + "%");
	// }
	//
	// @Override
	// public PLFTAteco2007Entity getAteco2007(BigDecimal idAteco2007)
	// {
	// return (PLFTAteco2007Entity) ateco2007Repository.findOne(idAteco2007);
	// }

	@Override
	public List<PLFTAtecoEntity> getAteco()
	{
		return (List<PLFTAtecoEntity>) atecoRepository.findAteco();
	}

	@Override
	public List<PLFTAtecoEntity> getAtecoPerAttivita(BigDecimal attivita)
	{
		return (List<PLFTAtecoEntity>) atecoRepository.findAtecoPerAttivita(attivita);
	}

	@Override
	public List<PLFTAtecoEntity> getAtecoPerDescrizione(BigDecimal codifica)
	{
		return (List<PLFTAtecoEntity>) atecoRepository.getAtecoPerCodifica(codifica);
	}

	@Override
	public PLFTAtecoEntity getAteco(BigDecimal idAteco)
	{
		return (PLFTAtecoEntity) atecoRepository.findOne(idAteco);
	}

	// ============= luoghi
	@Override
	public List<PLFTRegioneEntity> getRegione()
	{
		return (List<PLFTRegioneEntity>) regioneRepository.findRegione();
	}

	@Override
	public PLFTRegioneEntity getRegione(BigDecimal idRegione)
	{
		return (PLFTRegioneEntity) regioneRepository.findOne(idRegione);
	}

	@Override
	public List<PLFTProvinciaEntity> getProvinciaByRegione(BigDecimal idRegione)
	{
		return (List<PLFTProvinciaEntity>) provinciaRepository.findProvincia(idRegione);
	}

	@Override
	public List<PLFTProvinciaEntity> getProvinciaByCodRegione(String codRegione)
	{
		return (List<PLFTProvinciaEntity>) provinciaRepository.findProvincia(codRegione);
	}

	@Override
	public PLFTProvinciaEntity getProvincia(BigDecimal idProvincia)
	{
		return (PLFTProvinciaEntity) provinciaRepository.findOne(idProvincia);
	}

	@Override
	public List<PLFTComuneEntity> getComuneByProvincia(BigDecimal idProvincia)
	{
		return (List<PLFTComuneEntity>) comuneRepository.findComune(idProvincia);
	}

	@Override
	public List<PLFTComuneEntity> getComuneByNome(String nomeComune)
	{
		if (nomeComune != null)
		{
			nomeComune = nomeComune.toUpperCase();
			return (List<PLFTComuneEntity>) comuneRepository.findComuneByNome(nomeComune);
		}
		return null;
	}

	@Override
	public List<PLFTComuneEntity> getComuneByCodice(String codiceComune)
	{
		if (codiceComune != null)
		{
			return (List<PLFTComuneEntity>) comuneRepository.findComuneByCodice(codiceComune);
		}
		return null;
	}
	
	
	@Override
	public PLFTComuneEntity getComuneByCodiceIstat(String codiceComune)
	{
		if (codiceComune != null)
		{
			List<PLFTComuneEntity> list = (List<PLFTComuneEntity>) comuneRepository.findComuneByCodice(codiceComune);
			if (list != null && list.size()>0)
				return list.get(0);
		}
		return null;
	}

	@Override
	public List<PLFTComuneEntity> getComuneByCodProvincia(String codProvincia)
	{
		return (List<PLFTComuneEntity>) comuneRepository.findComune(codProvincia);
	}

	@Override
	public PLFTComuneEntity getComune(BigDecimal idComune)
	{
		return (PLFTComuneEntity) comuneRepository.findOne(idComune);
	}

	@Override
	public List<PLFTComuneEntity> getComune()
	{
		return (List<PLFTComuneEntity>) comuneRepository.findAll();
	}

	@Override
	public List<PLFTNazioneEntity> getNazione()
	{
		return (List<PLFTNazioneEntity>) nazioneRepository.findAll();
	}

	@Override
	public PLFTNazioneEntity getNazioneByCodice(String codiceNazione)
	{
		List<PLFTNazioneEntity> list = nazioneRepository.findByCodiceNazione(codiceNazione);
		if (list != null && list.size() > 0)
			return list.get(0);

		return null;
	}

	@Override
	public List<PLFTComuneEntity> getComuneStatiEsteri()
	{
		return (List<PLFTComuneEntity>) comuneRepository.findComuneStatiEsteri();
	}

	@Override
	public PLFTNazioneEntity getNazioneItalia()
	{
		List<PLFTNazioneEntity> list = nazioneRepository.findByCodiceNazione(IAbstractServiceImpl.COD_NAZIONE_ITALIA);
		if (list != null && list.size() > 0)
			return list.get(0);

		return null;
	}

	public static <T> Map<String, String> toMap(Class<T> clazz, List<T> list, String propertyKey, String propertyValue, String propertyCod, boolean voidElement)
	{
		return toMap(clazz, list, propertyKey, propertyValue, propertyCod, voidElement, null);
	}

	public static <T> Map<String, String> toMap(Class<T> clazz, List<T> list, String propertyKey, String propertyValue, String propertyCod, boolean voidElement, String messageCode)
	{
		Map<String, String> result = new TreeMap<String, String>();

		if (voidElement)
		{
			if (messageCode != null && messageCode.trim().length() > 0)
				result.put("", ContextLoader.getCurrentWebApplicationContext().getMessage(messageCode, new String[] {}, LocaleContextHolder.getLocale()));
			else
				result.put("", ContextLoader.getCurrentWebApplicationContext().getMessage("empty.option", new String[] {}, LocaleContextHolder.getLocale()));
		}

		if (list != null && list.size() > 0)
		{

			Object key = null;
			Object value = null;
			Object cod = null;

			for (T entity : list)
			{
				try
				{
					key = new PropertyDescriptor(propertyKey, clazz).getReadMethod().invoke(entity);

					value = new PropertyDescriptor(propertyValue, clazz).getReadMethod().invoke(entity);
					if (propertyCod != null)
						cod = new PropertyDescriptor(propertyCod, clazz).getReadMethod().invoke(entity);

					if (propertyCod != null)
						result.put(String.valueOf(key), String.valueOf(value) + " " + String.valueOf(cod));
					else
						result.put(String.valueOf(key), String.valueOf(value));
				}
				catch (Exception err)
				{
					LoggerUtility.error(err.getMessage(), err);
				}
			}
		}

		if (result != null)
			result = sortByValue(result);

		return result;
	}

	/**
	 * Short for {@link #toMap(Class, List, String, String, String, boolean)}
	 * for {@link TranslatableCodifica}
	 * */
	public static <T extends TranslatableCodifica> Map<String, String> toMap(Class<T> clazz, List<T> list, boolean voidElement)
	{
		return toMap(clazz, list, "id", "descrizione", null, voidElement);
	}

	/**
	 * Short for toMap(clazz, list, false)}
	 * */
	public static <T extends TranslatableCodifica> Map<String, String> toMap(Class<T> clazz, List<T> list)
	{
		return toMap(clazz, list, false);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map)
	{
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>()
		{
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
			{
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	@Override
	public List<PLFTRuoloEntity> getRuolo()
	{
		return (List<PLFTRuoloEntity>) ruoloRepository.findRuoli();
	}

	@Override
	public int updateRuolo(BigDecimal id, String descrizione)
	{
		return ruoloRepository.updateRuolo(id, descrizione);
	}

	@Override
	public List<PLFTControlliRichiestaEntity> getControlliRichiesta()
	{
		return (List<PLFTControlliRichiestaEntity>) controlliRichiestaRepository.findControlliRichiesta();
	}

	@Override
	public List<PLFTControlliRichiestaEntity> getControlliRichiestaByCodice(String codice)
	{
		return (List<PLFTControlliRichiestaEntity>) controlliRichiestaRepository.findControlliRichiestaByCodice(codice);
	}

	@Override
	public PLFTControlliRichiestaEntity getControlliRichiesta(BigDecimal idControlliRichiesta)
	{
		return (PLFTControlliRichiestaEntity) controlliRichiestaRepository.findOne(idControlliRichiesta);
	}

	@Override
	public List<PLFTLogMessaggiEntity> getLogMessaggi()
	{
		return logMessaggiRepository.findLogMessaggi();
	}

	@Override
	public List<PLFTLogMessaggiEntity> getLogMessaggiByCodice(String codice)
	{
		return logMessaggiRepository.findLogMessaggiByCodice(codice);
	}

	@Override
	public PLFTLogMessaggiEntity getLogMessaggi(BigDecimal idLogMessaggi)
	{
		return logMessaggiRepository.findOne(idLogMessaggi);
	}

	@Override
	public List<PLFTOrigineImpresaEntity> getOrigineImpresa()
	{
		return origineImpresaRepository.findOrigineImpresa();
	}

	@Override
	public List<PLFTOrigineImpresaEntity> getOrigineImpresaByCodice(String codice)
	{
		return origineImpresaRepository.findOrigineImpresaByCodice(codice);
	}

	@Override
	public PLFTOrigineImpresaEntity getOrigineImpresa(BigDecimal idOrigineImpresa)
	{
		return origineImpresaRepository.findOne(idOrigineImpresa);
	}

	@Override
	public List<PLFTSezioneSpecialeImpresaEntity> getSezioneSpecialeImpresa()
	{
		return sezioneSpecialeImpresaRepository.findSezioneSpecialeImpresa();
	}

	@Override
	public List<PLFTSezioneSpecialeImpresaEntity> getSezioneSpecialeImpresaByCodice(String codice)
	{
		return sezioneSpecialeImpresaRepository.findSezioneSpecialeImpresaByCodice(codice);
	}

	@Override
	public PLFTSezioneSpecialeImpresaEntity getSezioneSpecialeImpresa(BigDecimal idSezioneSpecialeImpresa)
	{
		return sezioneSpecialeImpresaRepository.findOne(idSezioneSpecialeImpresa);
	}

	// ===================================================================================================

	@Override
	public List<PLFTStatoImpresaEntity> getStatoImpresa()
	{
		return statoImpresaRepository.findStatoImpresa();
	}

	@Override
	public List<PLFTStatoImpresaEntity> getStatoImpresaByCodice(String codice)
	{
		return statoImpresaRepository.findStatoImpresaByCodice(codice);
	}

	@Override
	public PLFTStatoImpresaEntity getStatoImpresa(BigDecimal idStatoImpresa)
	{
		return statoImpresaRepository.findOne(idStatoImpresa);
	}

	@Override
	public List<PLFTStatoRichiestaEntity> getStatoRichiesta()
	{
		return statoRichiestaRepository.findStatoRichiesta();
	}

	@Override
	public List<PLFTStatoRichiestaEntity> getStatoRichiestaByCodice(String codice)
	{
		return statoRichiestaRepository.findStatoRichiestaByCodice(codice);
	}

	@Override
	public PLFTStatoRichiestaEntity getStatoRichiesta(BigDecimal idStatoRichiestaa)
	{
		return statoRichiestaRepository.findOne(idStatoRichiestaa);
	}

	@Override
	public List<PLFTTipoAllegatoEntity> getTipoAllegato()
	{
		return tipoAllegatoRepository.findTipoAllegato();
	}

	@Override
	public List<PLFTTipoAllegatoEntity> getTipoAllegatoByCodice(String codice)
	{
		return tipoAllegatoRepository.findTipoAllegatoByCodice(codice);
	}

	@Override
	public PLFTTipoAllegatoEntity getTipoAllegato(BigDecimal idTipoAllegato)
	{
		return tipoAllegatoRepository.findOne(idTipoAllegato);
	}

	@Override
	public List<PLFTTipoImpresaEntity> getTipoImpresa()
	{
		return tipoImpresaRepository.findTipoImpresa();
	}

	@Override
	public List<PLFTTipoImpresaEntity> getTipoImpresaByCodice(String codice)
	{
		return tipoImpresaRepository.findTipoImpresaByCodice(codice);
	}

	@Override
	public PLFTTipoImpresaEntity getTipoImpresa(BigDecimal idTipoImpresa)
	{
		return tipoImpresaRepository.findOne(idTipoImpresa);
	}

	@Override
	public List<PLFTTipoProgettiProdottiEntity> getTipoProgettiProdotti()
	{
		return tipoProgettiProdottiRepository.findTipoProgettiProdotti();
	}

	@Override
	public List<PLFTTipoProgettiProdottiEntity> getTipoProgettiProdottiByCodice(String codice)
	{
		return tipoProgettiProdottiRepository.findTipoProgettiProdottiByCodice(codice);
	}

	@Override
	public PLFTTipoProgettiProdottiEntity getTipoProgettiProdotti(BigDecimal idTipoProgettiProdotti)
	{
		return tipoProgettiProdottiRepository.findOne(idTipoProgettiProdotti);
	}

	@Override
	public List<PLFTTipologiaProgettoEntity> getTipologiaProgetto()
	{
		return tipologiaProgettoRepository.findTipologiaProgetto();
	}

	@Override
	public List<PLFTTipologiaProgettoEntity> getTipologiaProgettoByCodice(String codice)
	{
		return tipologiaProgettoRepository.findTipologiaProgettoByCodice(codice);
	}

	@Override
	public PLFTTipologiaProgettoEntity getTipologiaProgetto(BigDecimal idTipologiaProgetto)
	{
		return tipologiaProgettoRepository.findOne(idTipologiaProgetto);
	}

	@Override
	public List<PLFTClasseAddettiEntity> getClasseAddetti()
	{
		return classeAddettiRepository.findClasseAddetti();
	}

	@Override
	public List<PLFTClasseAddettiEntity> getClasseAddettiByCodice(String codice)
	{
		if (codice != null && codice.trim().length() > 0)
		{
			codice = codice.toUpperCase();
			return classeAddettiRepository.findClasseAddettiPerCodice(codice);
		}
		return null;
	}

	@Override
	public PLFTClasseAddettiEntity getClasseAddetti(BigDecimal idClasseAddetti)
	{
		return classeAddettiRepository.findOne(idClasseAddetti);
	}

	@Override
	public List<PLFTClasseProduzioneEntity> getClasseProduzione()
	{
		return classeProduzioneRepository.findClasseProduzione();
	}

	@Override
	public List<PLFTClasseProduzioneEntity> getProduzioneByCodice(String codice)
	{
		if (codice != null && codice.trim().length() > 0)
		{
			codice = codice.toUpperCase();
			return classeProduzioneRepository.findClasseProduzionePerCodice(codice);
		}
		return null;
	}

	@Override
	public PLFTClasseProduzioneEntity getClasseProduzione(BigDecimal idClasseProduzione)
	{
		return classeProduzioneRepository.findOne(idClasseProduzione);
	}

	@Override
	public List<PLFTClasseCapitaleEntity> getClasseCapitale()
	{
		return classeCapitaleRepository.findClasseCapitale();
	}

	@Override
	public List<PLFTClasseCapitaleEntity> getClasseCapitaleByCodice(String codice)
	{
		if (codice != null && codice.trim().length() > 0)
		{
			codice = codice.toUpperCase();
			return classeCapitaleRepository.findClasseCapitalePerCodice(codice);
		}
		return null;
	}

	@Override
	public PLFTClasseCapitaleEntity getClasseCapitale(BigDecimal idClasseCapitale)
	{
		return classeCapitaleRepository.findOne(idClasseCapitale);
	}

	@Override
	public List<PLFTPrevalenzaEntity> getPrevalenza()
	{
		return prevalenzaRepository.findPrevalenza();
	}

	@Override
	public List<PLFTPrevalenzaEntity> getPrevalenzaByCodice(String codice)
	{
		if (codice != null && codice.trim().length() > 0)
		{
			codice = codice.toUpperCase();
			return prevalenzaRepository.findPrevalenzaPerCodice(codice.trim().toUpperCase());
		}
		return null;
	}

	@Override
	public PLFTPrevalenzaEntity getPrevalenza(BigDecimal idPrevalenza)
	{
		return prevalenzaRepository.findOne(idPrevalenza);
	}

	@Override
	public List<PLFTAreeCompetenzaEntity> getAreeCompetenza()
	{
		return areeCompetenzaRepository.findAreeCompetenza();
	}

	@Override
	public List<PLFTAreeCompetenzaEntity> getAreeCompetenzaByCodice(String codice)
	{
		return areeCompetenzaRepository.findAreeCompetenzaPerCodice(codice);
	}
	
	@Override
	public List<PLFTAreeCompetenzaEntity> getAreeCompetenzaByDescrizione(String descrizione)
	{
		return areeCompetenzaRepository.findAreeCompetenzaPerDescrizione(descrizione);
	}
	
	

	@Override
	public PLFTAreeCompetenzaEntity getAreeCompetenza(BigDecimal idPrevalenza)
	{
		return areeCompetenzaRepository.findOne(idPrevalenza);
	}

	@Override
	public List<PLFTTipoNewsEntity> getTipoNews()
	{
		return tipoNewsRepository.findTipoNews();
	}

	@Override
	public List<PLFTTipoNewsEntity> getTipoNewsByCodice(String codice)
	{
		return tipoNewsRepository.findTipoNewsPerCodice(codice);
	}

	@Override
	public PLFTTipoNewsEntity getTipoNews(BigDecimal idTipoNews)
	{
		return tipoNewsRepository.findOne(idTipoNews);
	}

	@Override
	public List<PLFTNaturaGiuridicaEntity> getNaturaGiuridica()
	{
		return naturaGiuridicaRepository.findNaturaGiuridica();
	}

	@Override
	public List<PLFTNaturaGiuridicaEntity> getNaturaGiuridicaByCodice(String codice)
	{
		return naturaGiuridicaRepository.findNaturaGiuridicaPerCodice(codice);
	}

	@Override
	public List<PLFTNaturaGiuridicaEntity> getNaturaGiuridicaByDescrizione(String descrizione)
	{
		if (descrizione != null)
			return naturaGiuridicaRepository.findNaturaGiuridicaPerDescrizione(descrizione.toUpperCase());
		return null;
	}

	@Override
	public PLFTNaturaGiuridicaEntity getNaturaGiuridica(BigDecimal idNaturaGiuridica)
	{
		return naturaGiuridicaRepository.findOne(idNaturaGiuridica);
	}

	@Override
	public List<PLFTRuoloAziendaleEntity> getRuoloAziendale()
	{
		return ruoloAziendaleRepository.findRuoloAziendale();
	}

	@Override
	public List<PLFTRuoloAziendaleEntity> getRuoloAziendaleByCodice(String codice)
	{
		return ruoloAziendaleRepository.findRuoloAziendalePerCodice(codice);
	}

	@Override
	public PLFTRuoloAziendaleEntity getRuoloAziendale(BigDecimal idRuoloAziendale)
	{
		return ruoloAziendaleRepository.findOne(idRuoloAziendale);
	}

	@Override
	public List<PLFTInnovazioneEntity> getInnovazione()
	{
		return innovazioneRepository.findInnovazione();
	}

	@Override
	public List<PLFTInnovazioneEntity> getInnovazioneByCodice(String codice)
	{
		return innovazioneRepository.findInnovazionePerCodice(codice);
	}

	@Override
	public PLFTInnovazioneEntity getInnovazione(BigDecimal idInnovazione)
	{
		return innovazioneRepository.findOne(idInnovazione);
	}

	@Override
	public List<PLFTMercatiEntity> getMercati()
	{
		return mercatiRepository.findMercati();
	}

	@Override
	public List<PLFTMercatiEntity> getMercatiByCodice(String codice)
	{
		return mercatiRepository.findMercatiPerCodice(codice);
	}

	@Override
	public PLFTMercatiEntity getMercati(BigDecimal idMercati)
	{
		return mercatiRepository.findOne(idMercati);
	}

	@Override
	public List<PLFImpresaEntity> getStakeholder()
	{
		return impresaRepository.findStakeholder();
	}

	@Override
	public PLFTTipoServizioEntity getTipoServizio(BigDecimal idTipoServizio)
	{
		return tipoServizioRepository.findOne(idTipoServizio);
	}

	@Override
	public List<PLFTSettoreProgettiProdottiEntity> getSettoriProgettiProdotti()
	{
		return (List<PLFTSettoreProgettiProdottiEntity>) settoreProgettiProdottiRepository.findAll();
	}

	@Override
	public List<PLFTSettoreTecnologieEntity> getSettoriTecnologie()
	{
		return (List<PLFTSettoreTecnologieEntity>) settoreTecnologieJpaRepository.findAll();
	}

	@Override
	public List<PLFTTagEntity> getTags()
	{

		List<PLFTTagEntity> allTags = (List<PLFTTagEntity>) tagRepository.findAll();
		List<PLFTTagEntity> res = new ArrayList<PLFTTagEntity>();
		Date now = new Date();

		for (PLFTTagEntity tag : allTags)
		{
			if (tag.getDataFine() == null || tag.getDataFine().after(now))
			{
				res.add(tag);
			}
		}

		return res;
	}

	@Override
	public List<Map<String, Object>> getCodificheByTable(String tableName)
	{
		return drh.getActiveByTableName(tableName);
	}

	private boolean isFieldCodeVisible(String[] fieldCodes, String table)
	{
		for (String field : fieldCodes)
		{
			if (field.equalsIgnoreCase(table))
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<String>> checkDuplicates(String table, List<Map<String, Object>> codifiche)
	{

		String[] fieldCodes = I18nConstants.CODE_FIELD;

		String columnNameId = table.equals("PLF_T_ATECO") ? "ID_PLF_T_ATECO" : "ID";
		String columnNameCodice = table.equals("PLF_T_ATECO") ? "T_CODIFICA" : "CODICE";
		String columnNameDescrizione = "DESCRIZIONE";

		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		List<String> errorDetails = new ArrayList<String>();

		List<Map<String, Object>> allTable = getCodificheByTable(table);
		Map<String, Object> localCodifica = new HashMap<String, Object>();

		String selectedLocale = LocaleContextHolder.getLocale().getLanguage().toUpperCase() + "_" + LocaleContextHolder.getLocale().getCountry();

		for (Map<String, Object> savingCode : codifiche)
		{
			if (savingCode.get("locale").equals(selectedLocale))
			{
				localCodifica = (Map<String, Object>) savingCode.get("codifica");
				break;
			}
		}

		for (Map<String, Object> tableCode : allTable)
		{

			BigDecimal tabId = new BigDecimal((Long) tableCode.get(columnNameId));
			String tabDescr = (String) tableCode.get(columnNameDescrizione);

			String localeIdStr = (String) localCodifica.get(columnNameId);
			String localCodeCod = (String) localCodifica.get(columnNameCodice);
			String localCodeDescr = (String) localCodifica.get(columnNameDescrizione);

			BigDecimal localeCodeId = !localeIdStr.equals("") ? new BigDecimal(localeIdStr) : null;

			boolean duplicateFound = false;

			if (!tabId.equals(localeCodeId))
			{

				if (!table.equals("PLF_T_ATECO") && isFieldCodeVisible(fieldCodes, table))
				{
					String tabCodice = (String) tableCode.get(columnNameCodice);

					if (tabCodice.equalsIgnoreCase(localCodeCod))
					{
						errorDetails.add(columnNameCodice);
						duplicateFound = true;
					}
				}

				if (tabDescr.equalsIgnoreCase(localCodeDescr))
				{
					errorDetails.add(columnNameDescrizione);
					duplicateFound = true;
				}

				if (duplicateFound)
				{
					break;
				}
			}
		}

		errors.put("errors", errorDetails);
		return errors;
	}

	@Override
	public Map<String, Object> getTranslatedCodificheByid(String tableName, String id)
	{
		return drh.getAllTranslationsById(tableName, id);
	}

	@Override
	public int saveCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck)
	{
		return drh.saveCodifiche(tableName, codifiche, countCheck);
	}

	@Override
	public int updateCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck)
	{
		return drh.updateCodifiche(tableName, codifiche, countCheck);
	}

	@Override
	public int disableCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck)
	{
		return drh.disableCodifiche(tableName, codifiche, countCheck);
	}

	@Override
	public List<PLFTDenominazioneServiziEntity> getDenominazioniServizi()
	{
		return denominazioneServiziRepository.findModalitaErogazione();
	}

	@Override
	public List<PLFTModalitaErogazioneServizioEntity> getModalitaErogazioneServizio()
	{
		return modalitaErogazioneServizioRepository.findModalitaErogazioneServizio();
	}
	
	
	@Override
	public List<PLFTModalitaErogazioneServizioEntity> getModalitaErogazioneServizioPerDescrizione(String codSettore)
	{
		return (List<PLFTModalitaErogazioneServizioEntity>) modalitaErogazioneServizioRepository.findModalitaErogazioneServizioPerDescrizione(codSettore);
	}
	

	@Override
	public List<PLFTTipoErogazioneServizioEntity> getTipiErogazioneServizio()
	{
		return tipoErogazioneRepository.findTipiErogazioneServizio();
	}

	@Override
	public List<PLFTMacroareaServiziEntity> getMacroareeServizio()
	{
		return macroareaRepository.findMacroareeServizi();
	}

	@Override
	public List<PLFTModalitaErogazionePacchettoEntity> getModalitaErogazionePacchetto()
	{
		return modalitaErogazionePacchettoRepository.findModalitaErogazionePacchetto();
	}

	@Override
	public PLFTModalitaErogazionePacchettoEntity getModalitaErogazionePacchettoById(BigDecimal id)
	{
		return modalitaErogazionePacchettoRepository.findOne(id);
	}
}
