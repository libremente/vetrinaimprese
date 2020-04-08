package it.interlogic.vimp.batch;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFTAreeCompetenzaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoServizioEntity;
import it.interlogic.vimp.utils.Debug;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
			pathFile = "/Users/Data/job/prologic/genova/doc/batch/stakeholder.xlsx";
		else
			pathFile = args[0];

		InizializzatoreServizi engine = new InizializzatoreServizi();
		engine.start(pathFile);
	}

	private void start(String pathFile)
	{
		Debug.printInfo("Inizio elaborazione servizi first load ", InizializzatoreServizi.class.getName());
		// TODO
		persistent = new BatchPersistent();
		int exitCode = Constants.EXIT_OK;
		try
		{
			Workbook workbook = WorkbookFactory.create(new File(pathFile));
			Sheet datatypeSheet = workbook.getSheetAt(1);

			Iterator<Row> iterator = datatypeSheet.iterator();
			int row = 0;

			while (iterator.hasNext())
			{
				Row currentRow = iterator.next();
				row++;

				if (row == 1)
					continue;

				PLFServiziEntity servizio = getServizioFromXSL(currentRow);
				if (servizio != null)
				{
					servizio.setServiziStandard("N");
					servizio.setPubblicato(true);
					
					
					try
					{
						persistent.getServiziService().update(servizio);
					}
					catch (Exception err)
					{
						err.printStackTrace();
					}
				}

				// TODO
				// break;
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

	private PLFServiziEntity getServizioFromXSL(Row currentRow)
	{
		PLFServiziEntity servizio = new PLFServiziEntity();
		servizio.setServiziTranslation(new PLFServiziTranslationEntity());

		String stakeholder = null;

		Iterator<Cell> cellIterator = currentRow.iterator();

		int cell = 0;
		while (cellIterator.hasNext())
		{

			Cell currentCell = cellIterator.next();
			switch (cell)
			{
				case 0:
					stakeholder = getValue(currentCell);
					break;

				// Aree di Competenza
				case 2:
					servizio.setPlfTAreeCompetenza(getAreaCompetenza(getValue(currentCell)));
					break;

				// Descrizione
				case 3:
					servizio.getServiziTranslation().setDescrizione(getValue(currentCell));
					break;

				// Titolo del Servizio Specifico
				case 4:
					servizio.getServiziTranslation().setTitolo(getValue(currentCell));
					break;

				// Tipo servizio
				case 5:
					servizio.setPlfTTipoServizio(getTipoServizio(getValue(currentCell)));
					break;

				// Tipologia Erogazione
				case 6:
					// TODO
					System.out.println(getValue(currentCell));
					break;

				// Link esterno al servizio
				case 7:
					servizio.setLinkApprofondimento(getValue(currentCell));
					break;

				// Riferimenti
				case 8:
					servizio.getServiziTranslation().setRiferimenti(getValue(currentCell));
					break;

				// Data inizio
				case 9:
					Date dataInizio = getDate(currentCell);
					if (dataInizio != null)
						servizio.setDataInizio(dataInizio);
					else
						servizio.setDataInizio(new Date());
					break;

				// Data fine
				case 10:
					Date dataFine = getDate(currentCell);
					if (dataFine != null)
						servizio.setDataFine(dataFine);
					break;

				// Flag modalità erogazione (orari / prenotazione)
				case 11:
					// TODO
					servizio.setModalitaErogazioneServizio(getModalitaErogazione(getValue(currentCell)));
					break;

				// Orari (campo descrittivo)
				case 12:
					servizio.getServiziTranslation().setOrari(getValue(currentCell));
					break;

				// Indirizzo luogo erogazione del servizio
				case 13:
					servizio.setIndirizzoErogazione(getValue(currentCell));
					break;

				// N. civico Ind. erogazione del servizio
				case 14:
					servizio.setCivico(getValue(currentCell));
					break;

				// X luogo erogazione del servizio
				case 15:
					break;

				// Y luogo erogazione del servizio
				case 16:
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
				return servizio;
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	private String getValue(Cell currentCell)
	{
		currentCell.setCellType(Cell.CELL_TYPE_STRING);
		return currentCell.getStringCellValue();
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

	private PLFTModalitaErogazioneServizioEntity getModalitaErogazione(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTModalitaErogazioneServizioEntity> list = persistent.getDecodificaService().getModalitaErogazioneServizioPerDescrizione(descrizione);
				if (list != null && list.size() > 0)
				{
					PLFTModalitaErogazioneServizioEntity ret = new PLFTModalitaErogazioneServizioEntity();
					ret.setId(list.get(0).getId());
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

	private PLFTAreeCompetenzaEntity getAreaCompetenza(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTAreeCompetenzaEntity> list = persistent.getDecodificaService().getAreeCompetenzaByDescrizione(descrizione);
				if (list != null && list.size() > 0)
				{
					PLFTAreeCompetenzaEntity ret = new PLFTAreeCompetenzaEntity();
					ret.setId(list.get(0).getId());
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

	private PLFTTipoServizioEntity getTipoServizio(String descrizione)
	{
		try
		{
			if (persistent != null && descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTTipoServizioEntity> list = persistent.getDecodificaService().getTipoServizioByDescrizione(descrizione);
				if (list != null && list.size() > 0)
				{
					PLFTTipoServizioEntity ret = new PLFTTipoServizioEntity();
					ret.setId(list.get(0).getId());
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

}
