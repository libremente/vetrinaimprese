package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFInformazioneAttributiEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneAttributiEntityKey;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntityKey;
import it.interlogic.vimp.data.jpa.repository.PLFInformazioneAttributiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFInformazioneImmagineJpaRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class IAbstractServiceImpl
{
	public static final String COD_NAZIONE_ITALIA = "Z000";
	public static final String COD_REGIONE_LIGURIA = "07";
	public static final String COD_COMUNE_GENOVA = "010025";
	
	
	public static final int SEZIONE_PMI_INNOVATIVA = 1;
	public static final int SEZIONE_STARTUP_INNOVATIVA = 2;
	
	public static final int ORIGINE_SPINOFF_UNIVERSITARIA = 1;
	public static final int ORIGINE_SPINOFF_AZIENDALE = 2;
	

	public static final int TIPO_IMPRESA_AZIENDA = 1;
	public static final int TIPO_IMPRESA_STAKEHOLDER = 2;
	public static final int TIPO_IMPRESA_PMI = 1;
	public static final int TIPO_IMPRESA_PMI_SERVIZI = 3;

	public static final int TIPO_INFO_IMPRESA = 1;
	public static final int TIPO_INFO_STAKEHOLDER = 2;
	public static final int TIPO_INFO_SERVIZIO = 3;
	public static final int TIPO_INFO_PROGETTO_PRODOTTO = 4;
	public static final int TIPO_INFO_OPPORTUNITA = 5;
	public static final int TIPO_INFO_NEWS = 6;
	public static final int TIPO_INFO_PACCHETTO_SERVIZI = 7;

	public static final int STATO_IMPRESA_SPIN_OFF_UNIVERSITARIA = 1;
	public static final int STATO_IMPRESA_SPIN_OFF_AZIENDALE = 2;
	public static final int STATO_IMPRESA_START_UP_INNOVATIVA = 3;
	public static final int STATO_IMPRESA_PMI_INNOVATIVA = 4;
	public static final int STATO_IMPRESA_PMI = 5;
	public static final int STATO_IMPRESA_START_UP = 6;
	public static final int STATO_IMPRESA_GRANDI_IMPRESE = 7;
	public static final int STATO_IMPRESA_INCUBATORE = 8;
	public static final int STATO_IMPRESA_STAKEHOLDER = 9;
	
	
	public static final int STAKEHOLDER_INCUBATORE = 1;

	public static final int TIPO_PROGETTO = 1;
	public static final int TIPO_PRODOTTO = 2;
	public static final int TIPO_TECNOLOGIA = 3;
	public static final int TIPO_INNOVAZIONE = 4;

	public static final int TIPO_ALLEGATO_GENERICO = 6;

	public static final int STATO_RICHIESTA_VALIDATA_AUTO = 1;
	public static final int STATO_RICHIESTA_ATTESA = 2;
	public static final int STATO_RICHIESTA_NON_VALIDATO = 3;
	public static final int STATO_RICHIESTA_VALIDATA = 4;

	public static final int RICHIESTA_CONTROLLO_PRESENTE_ARIS = 1;
	public static final int RICHIESTA_CONTROLLO_NON_PRESENTE = 2;
	public static final int RICHIESTA_CONTROLLO_PRESENTE_VIMP = 3;

	public static final int STATO_NEWS_EVIDENZA = 1;
	

	public static final int LOG_IMPRESA_CANCELLATA = 1;
	public static final int LOG_IMPRESA_AGGIORNATA = 2;
	public static final int LOG_IMPRESA_ISCRITTA_REGISTRO = 3;
	public static final int LOG_PASSAGGIO_PMI = 4;
	public static final int LOG_IMPRESA_NUOVA = 5;
	public static final int LOG_IMPRESA_PRESENTE_VERTINA_CANCELLATA_ARIS = 6;
	public static final int LOG_IMPRESA_AGGIORNATA_DENOMINAZIONE = 7;
	public static final int LOG_IMPRESA_PRESENTE_ARIS_ISCRITTA = 8;
	public static final int LOG_IMPRESA_PRESENTE_ARIS_NON_ISCRITTA = 9;
	public static final int LOG_IMPRESA_DENOMINAZIONE_AGGIORNATA = 10;
	public static final int LOG_IMPRESA_PRSENTE_VETRINA_NO_ARIS = 11;
	public static final int LOG_IMPRESA_DENOMINAZIONE_SEZIONE = 12;
	


	@Autowired
	PLFInformazioneImmagineJpaRepository immagineRepository;

	@Autowired
	PLFInformazioneAttributiJpaRepository attributiRepository;

	protected byte[] getImage(BigDecimal idInformazione, int tipo)
	{
		PLFInformazioneImmagineEntityKey key = new PLFInformazioneImmagineEntityKey(idInformazione, new BigDecimal(tipo));
		PLFInformazioneImmagineEntity entity = immagineRepository.findOne(key);
		if (entity != null)
		{
			return entity.getImmagine();
		}
		return null;
	}
	
	
	protected void updateImage(BigDecimal idInformazione, int tipo, String imageString)
	{
		if (imageString != null && imageString.length() > 0)
		{
			int index = 23;
			if (!imageString.startsWith("data:image/jpeg"))
				index = 22;
			
			byte[] image = Base64.decodeBase64(imageString.substring(index).getBytes());
			
			PLFInformazioneImmagineEntityKey key = new PLFInformazioneImmagineEntityKey(idInformazione, new BigDecimal(tipo));
			PLFInformazioneImmagineEntity entity = immagineRepository.findOne(key);
			if (entity != null)
			{
				entity.setImmagine(image);
			}
			else
			{
				entity = new PLFInformazioneImmagineEntity();
				entity.setIdInformazione(idInformazione);
				entity.setIdTipoInformazione(new BigDecimal(tipo));
				entity.setImmagine(image);
			}
			immagineRepository.save(entity);
		}
	}


	protected void deleteImage(BigDecimal idInformazione, int tipo)
	{
		PLFInformazioneImmagineEntityKey key = new PLFInformazioneImmagineEntityKey(idInformazione, new BigDecimal(tipo));
		PLFInformazioneImmagineEntity entity = immagineRepository.findOne(key);
		if (entity != null)
		{
			entity.setDataCancellazione(new Date());
			immagineRepository.save(entity);
		}
	}

	protected void updateAttibuti(BigDecimal idInformazione, int tipo)
	{
		PLFInformazioneAttributiEntityKey key = new PLFInformazioneAttributiEntityKey(idInformazione, new BigDecimal(tipo));
		PLFInformazioneAttributiEntity entity = attributiRepository.findOne(key);
		if (entity != null)
		{

			BigDecimal current = null;
			Object obj = attributiRepository.numeroVisiteNextval();
			if (obj != null)
			{
				if (obj instanceof BigInteger)
					current = new BigDecimal(((BigInteger) obj).intValue());
				else
				{
					Integer val = (Integer) obj;

					current = BigDecimal.valueOf(val);
				}
			}

			if (current != null)
				current = new BigDecimal(current.intValue() + 1);
			else
				current = new BigDecimal(1);

			entity.setNumeroVisite(current);
			entity.setDataUltimaVisita(new Date());
		}
		else
		{
			entity = new PLFInformazioneAttributiEntity();
			entity.setIdInformazione(idInformazione);
			entity.setIdTipoInformazione(new BigDecimal(tipo));
			entity.setDataUltimaVisita(new Date());
			entity.setNumeroVisite(new BigDecimal(1));
		}
		attributiRepository.save(entity);
	}
}
