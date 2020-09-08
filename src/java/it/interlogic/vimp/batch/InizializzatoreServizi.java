package it.interlogic.vimp.batch;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAreeCompetenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTMacroareaServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoServizioEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziMacroareaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRServiziTipoErogazioneEntity;
import it.interlogic.vimp.utils.Debug;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InizializzatoreServizi
{
	private BatchPersistent persistent;

	public static void main(String[] args)
	{
		String pathFile = null;
		if (args == null || args.length <= 0)
			pathFile = "/Users/Data/job/prologic/genova/doc/batch/pacchetti_servizi.xlsx";
		else
			pathFile = args[0];

		InizializzatoreServizi engine = new InizializzatoreServizi();
		engine.start(pathFile);
	}

	private void start(String pathFile)
	{
		Debug.printInfo("Inizio elaborazione servizi first load ", InizializzatoreServizi.class.getName());
		persistent = new BatchPersistent();
		int exitCode = Constants.EXIT_OK;
		try
		{
			Workbook workbook = WorkbookFactory.create(new File(pathFile));
			Sheet datatypeSheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = datatypeSheet.iterator();
			int row = 0;

			while (iterator.hasNext())
			{
				Row currentRow = iterator.next();
				row++;

				if (row == 1)
					continue;

				ServizioObj obj = getServizioFromXSL(currentRow);
				
				if (obj != null && obj.servizio != null)
				{
					obj.servizio.setServiziStandard("N");
					obj.servizio.setPubblicato(true);
					
					try
					{
						obj.servizio = persistent.getServiziService().update(obj.servizio);
						
						if (obj.macroaree != null && obj.macroaree.size()>0)
						{
							for (PLFTMacroareaServiziEntity ma : obj.macroaree)
							{
								PLFRServiziMacroareaEntity e = new PLFRServiziMacroareaEntity(obj.servizio.getIdServizi(), ma.getId());
								persistent.getServiziService().updateMacroarea(e);
							}
						}
						
						if (obj.tipoErogazione != null)
						{
							PLFRServiziTipoErogazioneEntity e = new PLFRServiziTipoErogazioneEntity(obj.servizio.getIdServizi(), obj.tipoErogazione.getId());
							persistent.getServiziService().updateTipoErogazione(e);
						}
					}
					catch (Exception err)
					{
						err.printStackTrace();
					}
					
				}
				
				// TODO
				//break;
			}

		}
		catch (Exception err)
		{
			exitCode = Constants.EXIT_KO;
			err.printStackTrace();
		}
		finally
		{
			if (persistent != null)
				persistent.closeContext();
			Debug.printInfo("Fine elaborazione servizi first load ", InizializzatoreServizi.class.getName());
		}
		System.exit(exitCode);
	}

	private ServizioObj getServizioFromXSL(Row currentRow)
	{
		PLFServiziEntity servizio = new PLFServiziEntity();
		servizio.setServiziTranslation(new PLFServiziTranslationEntity());

		String stakeholder = null;

		Iterator<Cell> cellIterator = currentRow.iterator();
		
		
		List<PLFTMacroareaServiziEntity> macroaree = new ArrayList<PLFTMacroareaServiziEntity>();
		PLFTTipoErogazioneServizioEntity tipoErogazione = null;

		int cell = 0;
		PLFTMacroareaServiziEntity macroarea = null;
		while (cellIterator.hasNext())
		{

			Cell currentCell = cellIterator.next();
			switch (cell)
			{
				case 0:
					stakeholder = getValue(currentCell);
					break;
					
				// Macroarea1
				case 2:
					macroarea = getMacroarea(getValue(currentCell));
					if (macroarea != null) macroaree.add(macroarea);
					break;
				// Macroarea2
				case 3:
					macroarea = getMacroarea(getValue(currentCell));
					if (macroarea != null) macroaree.add(macroarea);
					break;
				// Macroarea3
				case 4:
					macroarea = getMacroarea(getValue(currentCell));
					if (macroarea != null) macroaree.add(macroarea);
					break;
				// Macroarea4
				case 5:
					macroarea = getMacroarea(getValue(currentCell));
					if (macroarea != null) macroaree.add(macroarea);
					break;

				// Aree di Competenza
				case 6:
					servizio.setPlfTAreeCompetenza(getAreaCompetenza(getValue(currentCell)));
					break;

				// Descrizione
				case 7:
					servizio.getServiziTranslation().setDescrizione(getValue(currentCell));
					break;

				// Titolo del Servizio Specifico
				case 8:
					servizio.getServiziTranslation().setTitolo(getValue(currentCell));
					break;

				// Tipo servizio
				case 9:
					servizio.setPlfTTipoServizio(getTipoServizio(getValue(currentCell)));
					break;

				// Tipologia Erogazione
				case 10:
					tipoErogazione = getTipoErogazione(getValue(currentCell));
					break;

				// Link esterno al servizio
				case 11:
					servizio.setLinkApprofondimento(getValue(currentCell));
					break;

				// Riferimenti
				case 12:
					servizio.getServiziTranslation().setRiferimenti(getValue(currentCell));
					break;

				// Data inizio
				case 13:
					Date dataInizio = getDate(currentCell);
					if (dataInizio != null)
						servizio.setDataInizio(dataInizio);
					else
						servizio.setDataInizio(new Date());
					break;

				// Data fine
				case 14:
					Date dataFine = getDate(currentCell);
					if (dataFine != null)
						servizio.setDataFine(dataFine);
					break;

				// Flag modalità erogazione (orari / prenotazione)
				case 15:
					servizio.setModalitaErogazioneServizio(getModalitaErogazione(getValue(currentCell)));
					break;

				// Orari (campo descrittivo)
				case 16:
					servizio.getServiziTranslation().setOrari(getValue(currentCell));
					break;

				// Indirizzo luogo erogazione del servizio
				case 17:
					servizio.setIndirizzoErogazione(getValue(currentCell));
					break;

				// N. civico Ind. erogazione del servizio
				case 18:
					servizio.setCivico(getValue(currentCell));
					break;

			}
			cell++;
		}

		if (persistent != null && stakeholder != null && stakeholder.trim().length() > 0)
		{
			PLFImpresaEntity entity = persistent.getImpresaService().find(stakeholder, stakeholder);
			if (entity != null && entity.getIdPlfImpresa() != null && entity.getIdPlfImpresa().intValue() > 0)
			{
				PLFImpresaEntity impresa = new PLFImpresaEntity();
				impresa.setIdPlfImpresa(entity.getIdPlfImpresa());
				servizio.setPlfImpresa(impresa);
				
				
				ServizioObj obj = new ServizioObj();
				obj.macroaree = macroaree;
				obj.tipoErogazione = tipoErogazione;
				obj.servizio = servizio;
				return obj;
			}
		}
		return null;
	}
	
	private class ServizioObj
	{
		PLFServiziEntity servizio;
		List<PLFTMacroareaServiziEntity> macroaree;
		PLFTTipoErogazioneServizioEntity tipoErogazione;
	}

	@SuppressWarnings("deprecation")
	private String getValue(Cell currentCell)
	{
		currentCell.setCellType(Cell.CELL_TYPE_STRING);
		return getString(currentCell.getStringCellValue());
	}
	
	
	private String getString(String s)
	{
		if (s != null)
		{
			s = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8); 
			s = s.replaceAll("’", "'");
			s = s.trim();
			return s;
		}
		return null;
	}

	private Date getDate(Cell currentCell)
	{
		try
		{
			return currentCell.getDateCellValue();
		}
		catch (Exception err)
		{
		}
		return null;
	}

	
	private Map<String, PLFTModalitaErogazioneServizioEntity> getModalitaErogazione = new HashMap<String, PLFTModalitaErogazioneServizioEntity>();
	private PLFTModalitaErogazioneServizioEntity getModalitaErogazione(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				System.out.print("getModalitaErogazione:" + descrizione + " --> ");
				
				PLFTModalitaErogazioneServizioEntity ret = getModalitaErogazione.get(descrizione);
				if (ret != null)
				{
					System.out.println(" --> trovata corrispondenza");
					return ret;
				}
				
				List<PLFTModalitaErogazioneServizioEntity> list = persistent.getDecodificaService().getModalitaErogazioneServizioPerDescrizione(descrizione);
				if (list != null && list.size() > 0)
				{
					ret = new PLFTModalitaErogazioneServizioEntity();
					ret.setId(list.get(0).getId());
					System.out.println(" --> trovata corrispondenza");
					getModalitaErogazione.put(descrizione, ret);
					return ret;
				}
				System.err.println(" --> non trovata corrispondenza");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Modalita erogazione ", err, Importer.class.getName());
		}
		return null;
	}
	
	private Map<String, PLFTTipoErogazioneServizioEntity> getTipoErogazione = new HashMap<String, PLFTTipoErogazioneServizioEntity>();
	private PLFTTipoErogazioneServizioEntity getTipoErogazione(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				System.out.print("getTipoErogazione:" + descrizione + " --> ");
				
				PLFTTipoErogazioneServizioEntity ret = getTipoErogazione.get(descrizione);
				if (ret != null)
				{
					System.out.println(" --> trovata corrispondenza");
					return ret;
				}
				
				List<PLFTTipoErogazioneServizioEntity> list = persistent.getDecodificaService().getTipiErogazioneServizioPerDescrizione(descrizione.trim());
				if (list != null && list.size() > 0)
				{
					ret = new PLFTTipoErogazioneServizioEntity();
					ret.setId(list.get(0).getId());
					System.out.println(" --> trovata corrispondenza");
					getTipoErogazione.put(descrizione, ret);
					return ret;
				}
				System.err.println(" --> non trovata corrispondenza");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Tipo erogazione ", err, Importer.class.getName());
		}
		return null;
	}

	private Map<String, PLFTAreeCompetenzaEntity> getAreaCompetenza = new HashMap<String, PLFTAreeCompetenzaEntity>();
	private PLFTAreeCompetenzaEntity getAreaCompetenza(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				System.out.print("getAreaCompetenza:" + descrizione + " --> ");
				
				PLFTAreeCompetenzaEntity ret = getAreaCompetenza.get(descrizione);
				if (ret != null)
				{
					System.out.println(" --> trovata corrispondenza");
					return ret;
				}
				
				List<PLFTAreeCompetenzaEntity> list = persistent.getDecodificaService().getAreeCompetenzaByDescrizione(descrizione.trim());
				if (list != null && list.size() > 0)
				{
					ret = new PLFTAreeCompetenzaEntity();
					ret.setId(list.get(0).getId());
					System.out.println(" --> trovata corrispondenza");
					getAreaCompetenza.put(descrizione, ret);
					return ret;
				}
				System.err.println(" --> non trovata corrispondenza");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Area di competenza ", err, Importer.class.getName());
		}
		return null;
	}
	
	
	private Map<String, PLFTMacroareaServiziEntity> getMacroarea = new HashMap<String, PLFTMacroareaServiziEntity>();
	private PLFTMacroareaServiziEntity getMacroarea(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				System.out.print("getMacroarea:" +  descrizione + " --> ");
				
				PLFTMacroareaServiziEntity ret = getMacroarea.get(descrizione);
				if (ret != null)
				{
					System.out.println(" --> trovata corrispondenza");
					return ret;
				}
				
				List<PLFTMacroareaServiziEntity> list = persistent.getDecodificaService().getMacroareeServizioByDescrizione(descrizione.trim());
				if (list != null && list.size() > 0)
				{
					ret = new PLFTMacroareaServiziEntity();
					ret.setId(list.get(0).getId());
					System.out.println(" --> trovata corrispondenza");
					getMacroarea.put(descrizione, ret);
					return ret;
				}
				System.err.println(" --> non trovata corrispondenza");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Macroarea ", err, Importer.class.getName());
		}
		return null;
	}

	private Map<String, PLFTTipoServizioEntity> getTipoServizio = new HashMap<String, PLFTTipoServizioEntity>();
	private PLFTTipoServizioEntity getTipoServizio(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				System.out.println("getTipoServizio:" +  descrizione + " --> ");
				
				PLFTTipoServizioEntity ret = getTipoServizio.get(descrizione);
				if (ret != null)
				{
					System.out.println(" --> trovata corrispondenza");
					return ret;
				}
				
				List<PLFTTipoServizioEntity> list = persistent.getDecodificaService().getTipoServizioByDescrizione(descrizione.trim());
				if (list != null && list.size() > 0)
				{
					ret = new PLFTTipoServizioEntity();
					ret.setId(list.get(0).getId());
					System.out.println(" --> trovata corrispondenza");
					getTipoServizio.put(descrizione, ret);
					return ret;
				}
				System.err.println(" --> non trovata corrispondenza");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
			Debug.printError("Tipo servizio ", err, Importer.class.getName());
		}
		return null;
	}

}
