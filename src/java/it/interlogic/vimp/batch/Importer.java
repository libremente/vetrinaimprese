package it.interlogic.vimp.batch;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAtecoEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseAddettiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseCapitaleEntity;
import it.interlogic.vimp.data.jpa.model.PLFTClasseProduzioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTLogMessaggiEntity;
import it.interlogic.vimp.data.jpa.model.PLFTNaturaGiuridicaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTPrevalenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSezioneSpecialeImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.service.ws.aris.uisearchall.ResultElement;
import it.interlogic.vimp.service.ws.aris.uisearchall.UlSearchAllResult;
import it.interlogic.vimp.utils.Debug;
import it.interlogic.vimp.utils.LoggerUtility;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Importer
{

	private static final int TIPO_SCARICO_PMI = 0;
	private static final int TIPO_SCARICO_STARTUP = 1;

	private static final String ARIS_STATO_ATTIVITA_ATTIVO = "A";
	private static final String ARIS_STATO_ATTIVITA_INATTIVO = "I";

	private BatchPersistent persistent;
	private Map<String, Integer> impreseFile = null;

	public void start(String pathFilePMI, String pathFileStartup)
	{
		Debug.printInfo("Inizio elaborazione ", Importer.class.getName());
		persistent = new BatchPersistent();
		int exitCode = Constants.EXIT_OK;
		try
		{
			String codiceFiscalePartitaIva = null;
			impreseFile = new HashMap<String, Integer>();

			Workbook workbook = WorkbookFactory.create(new File(pathFilePMI));
			Sheet datatypeSheet = workbook.getSheetAt(Constants.PMI_SHEET_DATA_NUMBER);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int row = 0;

			while (iterator.hasNext())
			{
				Row currentRow = iterator.next();
				row++;

				if (row == 1)
					continue;

				PLFImpresaEntity impresa = getImpresaPMIFromXSL(currentRow);
				if (impresa != null)
				{
					codiceFiscalePartitaIva = getCodiceFiscalePartitaIva(impresa);
					if (codiceFiscalePartitaIva != null)
						impreseFile.put(codiceFiscalePartitaIva, TIPO_SCARICO_PMI);
					impresa = addNecessaryField(impresa, IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA, IAbstractServiceImpl.TIPO_IMPRESA_PMI, Constants.PMI_DESC_FONTE);

					salvaImpresa(impresa, true);
				}
			}

			workbook = WorkbookFactory.create(new File(pathFileStartup));
			datatypeSheet = workbook.getSheetAt(Constants.STARTUP_SHEET_DATA_NUMBER);
			iterator = datatypeSheet.iterator();
			row = 0;
			while (iterator.hasNext())
			{
				Row currentRow = iterator.next();
				row++;

				if (row == 1)
					continue;

				PLFImpresaEntity impresa = getImpresaStartupFromXSL(currentRow);
				if (impresa != null)
				{
					codiceFiscalePartitaIva = getCodiceFiscalePartitaIva(impresa);
					if (codiceFiscalePartitaIva != null)
						impreseFile.put(codiceFiscalePartitaIva, TIPO_SCARICO_STARTUP);
					impresa = addNecessaryField(impresa, IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA, IAbstractServiceImpl.TIPO_IMPRESA_AZIENDA,
							Constants.STARTUP_DESC_FONTE);

					salvaImpresa(impresa, false);
				}
			}

			impresePresenti();
		}
		catch (Exception err)
		{
			exitCode = Constants.EXIT_KO;
			err.printStackTrace();
		}
		finally
		{
			persistent.closeContext();
			Debug.printInfo("Fine elaborazione ", Importer.class.getName());
		}
		System.exit(exitCode);
	}

	private void salvaImpresa(PLFImpresaEntity impresa, boolean isPmi)
	{
		try
		{
			if (impresa != null)
			{
				if (impresa.getImpresaTranslation().getDescImpresa() != null
						&& impresa.getImpresaTranslation().getDescImpresa().trim().length() > 0
						&& ((impresa.getPartitaIva() != null && impresa.getPartitaIva().trim().length() > 0) || (impresa.getCodFiscale() != null && impresa.getCodFiscale().trim()
								.length() > 0)))
				{
					PLFImpresaEntity db = persistent.getImpresaService().find(impresa.getPartitaIva(), impresa.getCodFiscale());
					if (db != null && db.getIdPlfImpresa() != null && db.getIdPlfImpresa().intValue() > 0)
					{
						Debug.printInfo("Impresa gia' presente. " + impresa.toStringIndentificativo(), Importer.class.getName());
						impresa.setIdPlfImpresa(db.getIdPlfImpresa());

						if (db.getImpresaTranslation() != null)
							impresa.getImpresaTranslation().setIdPlfImpresa(db.getIdPlfImpresa());

						impresaPresente(db, impresa, isPmi);
					}
					else
					{
						Debug.printInfo("Inserimento impresa. " + impresa.toStringIndentificativo(), Importer.class.getName());
						
						impresa = addAtecoFromAris(impresa);
						impresa = persistent.getImpresaService().update(impresa);
						impresa = persistent.getImpresaService().find(impresa.getIdPlfImpresa());
						saveLog(impresa, IAbstractServiceImpl.LOG_IMPRESA_NUOVA, new Date());
					}
				}
				else
				{
					Debug.printError("Inserimento impresa non riuscita: campi mancanti. " + impresa.toStringIndentificativo(), Importer.class.getName());
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Inserimento impresa ", err, Importer.class.getName());
		}
		finally
		{

		}
	}

	private PLFImpresaEntity addNecessaryField(PLFImpresaEntity impresa, int stato, int tipo, String fonte)
	{
		if (impresa != null)
		{
			PLFTStatoImpresaEntity statoImpresa = new PLFTStatoImpresaEntity();
			statoImpresa.setId(new BigDecimal(stato));
			impresa.setPlfTStatoImpresa(statoImpresa);

			PLFTTipoImpresaEntity tipoImpresa = new PLFTTipoImpresaEntity();
			tipoImpresa.setId(new BigDecimal(tipo));
			impresa.setPlfTTipoImpresa(tipoImpresa);

			if (impresa.getImpresaTranslation() == null)
				impresa.setImpresaTranslation(new PLFImpresaTranslationEntity());
			impresa.getImpresaTranslation().setDescFonte(fonte);

			impresa.setDataAggiornamento(new Date());

			impresa.setPubblicato(false);
		}
		return impresa;
	}

	private PLFImpresaEntity addAtecoFromAris(PLFImpresaEntity impresa)
	{
		if (impresa != null && impresa.getPlfTAteco() != null && impresa.getPlfTAteco().getIdAteco() != null && impresa.getPlfTAteco().getIdAteco().intValue()>0)
			return impresa;
		
		String cfpi = getCodiceFiscalePartitaIva(impresa);
		
		Debug.printInfo("Ricerca ATECO su ARIS con partita iva : " + cfpi, Importer.class.getName());
		
		if (cfpi != null)
		{
			try
			{
				UlSearchAllResult ul = persistent.getULAll(cfpi);
				if (ul != null && ul.getC_fiscale_impresa() != null && ul.getC_fiscale_impresa().trim().length() > 0)
				{
					ResultElement[] sedi = ul.getResultUnitaLocali();
					if (sedi != null && sedi.length > 0)
					{
						String attivitaAteco = sedi[0].getC_ateco();
						PLFTAtecoEntity ateco = this.getAtecoAris(attivitaAteco);
						if (ateco != null && ateco.getIdAteco() != null && ateco.getIdAteco().intValue() > 0)
							impresa.setPlfTAteco(ateco);
						
						Debug.printInfo("ATECO PARTITA_IVA:" + cfpi + " RITORNO: " + attivitaAteco , Importer.class.getName());
					}
				}
				else
				{
					Debug.printInfo("ATECO PARTITA_IVA:" + cfpi + " RITORNO NIENTE" , Importer.class.getName());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return impresa;
	}

	private PLFImpresaEntity getImpresaPMIFromXSL(Row currentRow)
	{
		PLFImpresaEntity impresa = new PLFImpresaEntity();
		impresa.setImpresaTranslation(new PLFImpresaTranslationEntity());

		Iterator<Cell> cellIterator = currentRow.iterator();

		int cell = 0;
		while (cellIterator.hasNext())
		{

			Cell currentCell = cellIterator.next();
			switch (cell)
			{
				case Constants.PMI_DESC_IMPRESA:
					impresa.getImpresaTranslation().setDescImpresa(currentCell.getStringCellValue());
					break;
				case Constants.PMI_NATURA_GIURIDICA:
					impresa.setPlfTNaturaGiuridica(getNaturaGiuridica(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_COD_FISCALE_PARTITA_IVA:
					impresa.setPartitaIva(currentCell.getStringCellValue());
					impresa.setCodFiscale(currentCell.getStringCellValue());
					break;
				case Constants.PMI_PROVINCIA:
					if (!inLiguria(currentCell.getStringCellValue()))
						return null;
					break;
				case Constants.PMI_COMUNE:
					impresa.setPlfTComune(getComune(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_DATA_ISCRIZIONE_SEZIONE_SPECIALE:
					impresa.setDataIscrizioneSezioneSpeciale(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_DATA_ISCRIZIONE_REGISTRO_IMPRESE:
					impresa.setDataIscrizioneRegistroImprese(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_DATA_INIZIO_EFFETTIVA_ATTIVITA:
					impresa.setDataInizioEffettivaAttivita(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ATECO:
					impresa.setPlfTAteco(getAteco(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_SETTORE_IMPRESA:
					impresa.setPlfTSettoreImpresa(getSettore(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_CLASSE_PRODUZIONE:
					impresa.setPlfTClasseProduzione(getClasseProduzione(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_CLASSE_ADDETTI:
					impresa.setPlfTClasseAddetti(getClasseAddetti(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_CLASSE_CAPITALE:
					impresa.setPlfTClasseCapitale(getClasseCapitale(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_SITO_INTERNET:
					impresa.setDescSito(getSitoWeb(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_PRIMO_REQUISITO_PMI:
					impresa.setPrimoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_SECONDO_REQUISITO_PMI:
					impresa.setSecondoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_TERZO_REQUISITO_PMI:
					impresa.setTerzoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_PREVALENZA_FEMMINILE:
					impresa.setPlfTPrevalenzaFemminile(getPrevalenza(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_PREVALENZA_GIOVANILE:
					impresa.setPlfTPrevalenzaGiovanile(getPrevalenza(currentCell.getStringCellValue()));
					break;
				case Constants.PMI_ID_PLF_T_PREVALENZA_STRANIERA:
					impresa.setPlfTPrevalenzaStraniera(getPrevalenza(currentCell.getStringCellValue()));
					break;
			}
			cell++;
		}
		return impresa;
	}

	private PLFImpresaEntity getImpresaStartupFromXSL(Row currentRow)
	{
		PLFImpresaEntity impresa = new PLFImpresaEntity();
		PLFImpresaTranslationEntity translation = new PLFImpresaTranslationEntity();
		impresa.setImpresaTranslation(translation);

		Iterator<Cell> cellIterator = currentRow.iterator();

		int cell = 0;
		while (cellIterator.hasNext())
		{

			Cell currentCell = cellIterator.next();
			switch (cell)
			{
				case Constants.STARTUP_DESC_IMPRESA:
					impresa.getImpresaTranslation().setDescImpresa(currentCell.getStringCellValue());
					break;
				case Constants.STARTUP_NATURA_GIURIDICA:
					impresa.setPlfTNaturaGiuridica(getNaturaGiuridica(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_COD_FISCALE_PARTITA_IVA:
					impresa.setPartitaIva(currentCell.getStringCellValue());
					break;
				case Constants.STARTUP_PROVINCIA:
					if (!inLiguria(currentCell.getStringCellValue()))
						return null;
					break;
				case Constants.STARTUP_COMUNE:
					impresa.setPlfTComune(getComune(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_DATA_ISCRIZIONE_SEZIONE_SPECIALE:
					impresa.setDataIscrizioneSezioneSpeciale(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_DATA_ISCRIZIONE_REGISTRO_IMPRESE:
					impresa.setDataIscrizioneRegistroImprese(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_DATA_INIZIO_EFFETTIVA_ATTIVITA:
					impresa.setDataInizioEffettivaAttivita(parseDate(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ATECO:
					impresa.setPlfTAteco(getAteco(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_SETTORE_IMPRESA:
					impresa.setPlfTSettoreImpresa(getSettore(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_CLASSE_PRODUZIONE:
					impresa.setPlfTClasseProduzione(getClasseProduzione(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_CLASSE_ADDETTI:
					impresa.setPlfTClasseAddetti(getClasseAddetti(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_CLASSE_CAPITALE:
					impresa.setPlfTClasseCapitale(getClasseCapitale(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_SITO_INTERNET:
					impresa.setDescSito(getSitoWeb(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_PRIMO_REQUISITO_PMI:
					impresa.setPrimoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_SECONDO_REQUISITO_PMI:
					impresa.setSecondoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_TERZO_REQUISITO_PMI:
					impresa.setTerzoRequisitoPmi(parseSino(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_PREVALENZA_FEMMINILE:
					impresa.setPlfTPrevalenzaFemminile(getPrevalenza(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_PREVALENZA_GIOVANILE:
					impresa.setPlfTPrevalenzaGiovanile(getPrevalenza(currentCell.getStringCellValue()));
					break;
				case Constants.STARTUP_ID_PLF_T_PREVALENZA_STRANIERA:
					impresa.setPlfTPrevalenzaStraniera(getPrevalenza(currentCell.getStringCellValue()));
					break;
			}
			cell++;
		}
		return impresa;
	}

	private String getCodiceFiscalePartitaIva(PLFImpresaEntity impresa)
	{
		if (impresa.getPartitaIva() != null && impresa.getPartitaIva().trim().length() > 0)
			return impresa.getPartitaIva();
		if (impresa.getCodFiscale() != null && impresa.getCodFiscale().trim().length() > 0)
			return impresa.getCodFiscale();
		return null;
	}

	private boolean inLiguria(String provincia)
	{
		if (provincia != null)
		{
			provincia = provincia.trim();
			return ("GE".equalsIgnoreCase(provincia) || "SV".equalsIgnoreCase(provincia) || "IM".equalsIgnoreCase(provincia) || "SP".equalsIgnoreCase(provincia));
		}
		return false;
	}

	private String parseSino(String sino)
	{
		try
		{
			if ("SI".equalsIgnoreCase(sino))
				return "S";
			if ("NO".equalsIgnoreCase(sino))
				return "N";

		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("SI/NO ", err, Importer.class.getName());
		}
		return null;
	}

	private boolean equalsString(String a, String b)
	{
		if (a == null)
		{
			if (b != null)
				return false;
		}
		else if (!a.equals(b))
			return false;
		return true;
	}

	private Date parseDate(String date)
	{
		try
		{
			if (date != null && date.trim().length() > 0)
				return Constants.dateFormat.parse(date);
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Date ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTNaturaGiuridicaEntity getNaturaGiuridica(String descrizione)
	{
		try
		{
			if (descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTNaturaGiuridicaEntity> list = persistent.getDecodificaService().getNaturaGiuridicaByDescrizione(descrizione.trim());
				if (list != null && list.size() > 0)
				{
					PLFTNaturaGiuridicaEntity ret = new PLFTNaturaGiuridicaEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Natura giuridica ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTComuneEntity getComune(String comune)
	{
		try
		{
			if (comune != null && comune.trim().length() > 0)
			{
				List<PLFTComuneEntity> list = persistent.getDecodificaService().getComuneByNome(comune.trim());
				if (list != null && list.size() > 0)
				{
					PLFTComuneEntity ret = new PLFTComuneEntity();
					ret.setIdComune(list.get(0).getIdComune());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Comune ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTClasseProduzioneEntity getClasseProduzione(String codice)
	{
		try
		{
			if (codice != null && codice.trim().length() > 0)
			{
				List<PLFTClasseProduzioneEntity> list = persistent.getDecodificaService().getProduzioneByCodice(codice.trim().toUpperCase());
				if (list != null && list.size() > 0)
				{
					PLFTClasseProduzioneEntity ret = new PLFTClasseProduzioneEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Classe produzione ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTClasseCapitaleEntity getClasseCapitale(String codice)
	{
		try
		{
			if (codice != null && codice.trim().length() > 0)
			{
				List<PLFTClasseCapitaleEntity> list = persistent.getDecodificaService().getClasseCapitaleByCodice(codice.trim().toUpperCase());
				if (list != null && list.size() > 0)
				{
					PLFTClasseCapitaleEntity ret = new PLFTClasseCapitaleEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Classe capitale ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTClasseAddettiEntity getClasseAddetti(String codice)
	{
		try
		{
			if (codice != null && codice.trim().length() > 0)
			{
				List<PLFTClasseAddettiEntity> list = persistent.getDecodificaService().getClasseAddettiByCodice(codice.trim().toUpperCase());
				if (list != null && list.size() > 0)
				{
					PLFTClasseAddettiEntity ret = new PLFTClasseAddettiEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Classe addetti ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTPrevalenzaEntity getPrevalenza(String codice)
	{
		try
		{
			if (codice != null && codice.trim().length() > 0)
			{
				List<PLFTPrevalenzaEntity> list = persistent.getDecodificaService().getPrevalenzaByCodice(codice.trim().toUpperCase());
				if (list != null && list.size() > 0)
				{
					PLFTPrevalenzaEntity ret = new PLFTPrevalenzaEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Prevalenza ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTSettoreImpresaEntity getSettore(String descrizione)
	{
		try
		{
			if (descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTSettoreImpresaEntity> list = persistent.getDecodificaService().getSettoreImpresaPerDescrizione(descrizione.trim().toUpperCase());
				if (list != null && list.size() > 0)
				{
					PLFTSettoreImpresaEntity ret = new PLFTSettoreImpresaEntity();
					ret.setId(list.get(0).getId());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Settore impresa ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTAtecoEntity getAteco(String attivita)
	{
		try
		{
			if (attivita != null && attivita.trim().length() > 0)
			{
				List<PLFTAtecoEntity> list = persistent.getDecodificaService().getAtecoPerAttivita(new BigDecimal(Integer.parseInt(attivita.trim())));
				if (list != null && list.size() > 0)
				{
					PLFTAtecoEntity ret = new PLFTAtecoEntity();
					ret.setIdAteco(list.get(0).getIdAteco());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Ateco ", err, Importer.class.getName());
		}
		return null;
	}

	private PLFTAtecoEntity getAtecoAris(String attivita)
	{
		try
		{
			if (attivita != null && attivita.trim().length() > 0)
			{
				List<PLFTAtecoEntity> list = persistent.getDecodificaService().getAtecoPerAttivita(new BigDecimal(Integer.parseInt(attivita.trim())));
				if (list != null && list.size() > 0)
				{
					PLFTAtecoEntity ret = new PLFTAtecoEntity();
					ret.setIdAteco(list.get(0).getIdAteco());
					return ret;
				}
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Ateco ", err, Importer.class.getName());
		}
		return null;
	}

	private String getSitoWeb(String sito)
	{
		try
		{
			if (sito != null && sito.trim().length() > 0)
			{
				String tmp = sito.toLowerCase();
				if (!tmp.startsWith("http"))
					return "http://" + sito;

				return sito;
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Ateco ", err, Importer.class.getName());
		}
		return null;
	}

	private void saveLog(PLFImpresaEntity impresa, int idMessaggio, Date dataUltimoAggCciaRegistro)
	{
		if (impresa != null && impresa.getImpresaTranslation() != null && impresa.getImpresaTranslation().getDescImpresa() != null)
		{
			PLFLogEntity log = persistent.getLogService().getLogImpresa(impresa.getIdPlfImpresa());
			if (log == null)
			{
				log = new PLFLogEntity();
				PLFImpresaEntity key = new PLFImpresaEntity();
				key.setIdPlfImpresa(impresa.getIdPlfImpresa());
				log.setPlfImpresa(key);
			}

			PLFTLogMessaggiEntity messaggio = new PLFTLogMessaggiEntity();
			messaggio.setId(new BigDecimal(idMessaggio));
			log.setLogMessaggi(messaggio);

			log.setCodiceFiscale(impresa.getCodFiscale());
			log.setPartitaIva(impresa.getPartitaIva());

			log.setRagioneSociale(impresa.getImpresaTranslation().getDescImpresa());

			if (dataUltimoAggCciaRegistro != null)
			{
				log.setDataUltimoAggCciaAnagrafe(dataUltimoAggCciaRegistro);
				log.setDataUltimoAggCciaRegistro(dataUltimoAggCciaRegistro);
			}

			persistent.getLogService().salvaLog(log);
		}
	}

	private void impresaPresente(PLFImpresaEntity db, PLFImpresaEntity impresa, boolean isPmi)
	{
		if (db.getDataIscrizioneSezioneSpeciale() != null)
		{
			int idMessagio = -1;
			if (!equalsString(db.getImpresaTranslation().getDescImpresa(), impresa.getImpresaTranslation().getDescImpresa()))
			{
				db.getImpresaTranslation().setDescImpresa(impresa.getImpresaTranslation().getDescImpresa());
				idMessagio = IAbstractServiceImpl.LOG_IMPRESA_AGGIORNATA_DENOMINAZIONE;
			}

			if (db.getPlfTStatoImpresa() != null && db.getPlfTStatoImpresa().getId() != null
					&& db.getPlfTStatoImpresa().getId().intValue() == IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA
					&& impresa.getPlfTStatoImpresa().getId().intValue() == IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA)
			{
				db.setDataPassaggioPmiCciaa(new Date());
				db.setPlfTStatoImpresa(impresa.getPlfTStatoImpresa());
				PLFTSezioneSpecialeImpresaEntity sezione = new PLFTSezioneSpecialeImpresaEntity();
				sezione.setId(new BigDecimal(IAbstractServiceImpl.SEZIONE_PMI_INNOVATIVA));
				db.setPlfTSezioneSpecialeImpresa(sezione);

				if (idMessagio == -1)
					idMessagio = IAbstractServiceImpl.LOG_PASSAGGIO_PMI;
				else
					idMessagio = IAbstractServiceImpl.LOG_IMPRESA_DENOMINAZIONE_SEZIONE;
			}

			if (idMessagio == -1)
			{
				idMessagio = IAbstractServiceImpl.LOG_IMPRESA_AGGIORNATA;
				// TODO cosa aggiornare
				// db.setDescSito(impresa.getDescSito());
				// db = persistent.getImpresaService().updateImpresa(db);
				// db =
				// persistent.getImpresaService().find(db.getIdPlfImpresa());
				// TODO da specifiche nulla???????
			}
			else
			{
				db = addAtecoFromAris(db);
				db = persistent.getImpresaService().updateImpresa(db);
				db = persistent.getImpresaService().find(db.getIdPlfImpresa());
			}
			saveLog(db, idMessagio, new Date());
		}
		else
		{
			int idMessagio = -1;

			db.setDataIscrizioneSezioneSpeciale(impresa.getDataIscrizioneSezioneSpeciale());

			if (impresa.getDataIscrizioneSezioneSpeciale() != null)
			{
				LoggerUtility.error("CAMBIO STATO:" + db.getPlfTStatoImpresa().getId());
				
				if (db.getPlfTStatoImpresa() != null && db.getPlfTStatoImpresa().getId() != null
						&& db.getPlfTStatoImpresa().getId().intValue() == IAbstractServiceImpl.STATO_IMPRESA_PMI)
				{
					PLFTStatoImpresaEntity stato = new PLFTStatoImpresaEntity();
					stato.setId(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA));
					db.setPlfTStatoImpresa(stato);
					
					Debug.printInfo("CAMBIO STATO:" + db.getPlfTStatoImpresa().getId() , Importer.class.getName());
					
					idMessagio = IAbstractServiceImpl.LOG_IMPRESA_STATO_NO_DENOMINAZIONE_AGGIORNATO;
				}
				else if (db.getPlfTStatoImpresa() != null && db.getPlfTStatoImpresa().getId() != null
						&& db.getPlfTStatoImpresa().getId().intValue() == IAbstractServiceImpl.STATO_IMPRESA_START_UP)
				{
					PLFTStatoImpresaEntity stato = new PLFTStatoImpresaEntity();
					stato.setId(new BigDecimal(IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA));
					db.setPlfTStatoImpresa(stato);
					
					Debug.printInfo("CAMBIO STATO:" + db.getPlfTStatoImpresa().getId() , Importer.class.getName());
					
					idMessagio = IAbstractServiceImpl.LOG_IMPRESA_STATO_NO_DENOMINAZIONE_AGGIORNATO;
				}
			}

			if (!equalsString(db.getImpresaTranslation().getDescImpresa(), impresa.getImpresaTranslation().getDescImpresa()))
			{
				db.getImpresaTranslation().setDescImpresa(impresa.getImpresaTranslation().getDescImpresa());

				if (idMessagio > 0)
					idMessagio = IAbstractServiceImpl.LOG_IMPRESA_STATO_E_DENOMINAZIONE_AGGIORNATO;
				else
					idMessagio = IAbstractServiceImpl.LOG_IMPRESA_DENOMINAZIONE_AGGIORNATA;
			}

			if (idMessagio < 0)
				idMessagio = IAbstractServiceImpl.LOG_IMPRESA_STATO_NON_AGGIORNATO;

			
			
			db = persistent.getImpresaService().updateImpresa(db);
			db = persistent.getImpresaService().find(db.getIdPlfImpresa());
			saveLog(db, idMessagio, new Date());
		}
		
		
		// AGGIORNAMENTO ATECO DA ARIS SEMPRE
		db = addAtecoFromAris(db);
		db = persistent.getImpresaService().updateImpresa(db);
		

	}

	private void impresePresenti()
	{
		List<PLFImpresaEntity> imprese = persistent.getImpresaService().getImprese();
		if (imprese != null && imprese.size() > 0)
		{
			for (PLFImpresaEntity impresa : imprese)
			{
				if (impresa != null)
				{

					String textImpresa = " < INDEFINITA > ";

					if (impresa.getImpresaTranslation() != null)
						textImpresa = impresa.getImpresaTranslation().getDescImpresa();
					else if (impresa.getPartitaIva() != null)
						textImpresa = impresa.getPartitaIva();
					else if (impresa.getCodFiscale() != null)
						textImpresa = impresa.getCodFiscale();

					String cfpi = getCodiceFiscalePartitaIva(impresa);
					if (cfpi != null)
					{
						Integer tipo = impreseFile.get(cfpi);
						if (tipo == null)
						{
							LoggerUtility.error("----------------- cfpi:" + cfpi);

							// non c'e' nello scarico
							int idMessaggio = -1;
							// UlSearchResult ul = persistent.getUL(cfpi);
							UlSearchAllResult ul = persistent.getULAll(cfpi);
							try
							{
								if (ul != null && ul.getC_fiscale_impresa() != null && ul.getC_fiscale_impresa().trim().length() > 0)
								{
									ResultElement[] sedi = ul.getResultUnitaLocali();
									if (sedi != null && sedi.length > 0)
									{
										String attivitaAteco = sedi[0].getC_ateco();
										PLFTAtecoEntity ateco = this.getAtecoAris(attivitaAteco);
										if (ateco != null && ateco.getIdAteco() != null && ateco.getIdAteco().intValue() > 0)
											impresa.setPlfTAteco(ateco);
										persistent.getImpresaService().update(impresa);
									}
								}
							}
							catch (InformazioneDuplicataException e)
							{
								e.printStackTrace();
							}

							if (ul != null && ul.getC_fiscale_impresa() != null && ul.getC_fiscale_impresa().trim().length() > 0)
							{
								// presente su aris
								if (ARIS_STATO_ATTIVITA_INATTIVO.equalsIgnoreCase(ul.getI_stato_attivita()))
								{
									// inattiva su aris
									email("L’impresa denominata " + impresa.getImpresaTranslation().getDescImpresa() + " con codice fiscale " + cfpi + " in data "
											+ Constants.dateFormat.format(new Date()) + " non risulta più attiva nella banca dati delle imprese – ARIS");
									idMessaggio = IAbstractServiceImpl.LOG_IMPRESA_PRESENTE_VERTINA_CANCELLATA_ARIS;
								}
								else if (ARIS_STATO_ATTIVITA_ATTIVO.equalsIgnoreCase(ul.getI_stato_attivita()))
								{
									if (impresa.getDataIscrizioneSezioneSpeciale() != null)
									{

										if (impresa.getPlfTStatoImpresa() != null && impresa.getPlfTStatoImpresa().getId() != null
												&& impresa.getPlfTStatoImpresa().getId().intValue() > 0)
										{
											int newStato = -1;
											if (IAbstractServiceImpl.STATO_IMPRESA_START_UP_INNOVATIVA == impresa.getPlfTStatoImpresa().getId().intValue())
											{
												newStato = IAbstractServiceImpl.STATO_IMPRESA_PMI;
												impresa.setDataIscrizioneSezioneSpeciale(null);
											}
											else if (IAbstractServiceImpl.STATO_IMPRESA_PMI_INNOVATIVA == impresa.getPlfTStatoImpresa().getId().intValue())
											{
												newStato = IAbstractServiceImpl.STATO_IMPRESA_PMI;
												impresa.setDataIscrizioneSezioneSpeciale(null);
											}

											if (newStato > 0)
											{
												impresa.setPlfTStatoImpresa(new PLFTStatoImpresaEntity());
												impresa.getPlfTStatoImpresa().setId(new BigDecimal(newStato));
												try
												{
													impresa = this.addAtecoFromAris(impresa);
													persistent.getImpresaService().update(impresa);
												}
												catch (InformazioneDuplicataException e)
												{
												}
											}
										}

										email("L’impresa denominata " + impresa.getImpresaTranslation().getDescImpresa() + " con codice fiscale " + cfpi + " in data "
												+ Constants.dateFormat.format(new Date())
												+ " non risulta più iscritta al Registro Imprese sezione speciale start up / PMI innovative");
										idMessaggio = IAbstractServiceImpl.LOG_IMPRESA_CANCELLATA;
									}
									else
									{
										idMessaggio = IAbstractServiceImpl.LOG_IMPRESA_PRESENTE_ARIS_NON_ISCRITTA;
									}
								}
							}
							else
							{
								// non presente su aris
								email("L'impresa denominata " + textImpresa + " con codice fiscale " + cfpi + " in data " + Constants.dateFormat.format(new Date())
										+ " non risulta iscritta alla banca dati delle imprese – ARIS");
								idMessaggio = IAbstractServiceImpl.LOG_IMPRESA_PRSENTE_VETRINA_NO_ARIS;
							}
							saveLog(impresa, idMessaggio, new Date());
						}
					}
				}
			}
		}
	}

	private void email(String body)
	{
		LoggerUtility.error("----------------- Send mail begin");
		try
		{
			if (persistent.getMailHost() != null && persistent.getMailHost().trim().length() > 0)
			{
				Properties props = new Properties();
				props.put("mail.smtp.host", persistent.getMailHost());
				props.put("mail.smtp.port", persistent.getMailPort());
				props.put("mail.smtp.auth", "false");
				props.put("mail.smtp.starttls.enable", "true");

				Session session = Session.getInstance(props);

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(persistent.getAddressMailFrom()));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(persistent.getAddressMailTo()));
				message.setSubject("Vetrina imprese - Allineamento CCIAA-Registro");
				message.setText(body);

				// TODO SEND MAIL
				Transport.send(message);
			}

		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		LoggerUtility.error("----------------- Send mail end");
	}

}
