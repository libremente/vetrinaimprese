package it.interlogic.vimp.batch;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;
import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTTipoImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;
import it.interlogic.vimp.utils.Debug;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

public class InizializzatoreStakeholder
{
	private BatchPersistent persistent;

	public static void main(String[] args)
	{
		String pathFile = null;
		if (args == null || args.length <= 0)
			pathFile = "/Users/Data/job/prologic/genova/doc/batch/stakeholder.xlsx";
		else
			pathFile = args[0];

		InizializzatoreStakeholder engine = new InizializzatoreStakeholder();
		engine.start(pathFile);
	}

	private void start(String pathFile)
	{
		Debug.printInfo("Inizio elaborazione stakeholder first load ", InizializzatoreStakeholder.class.getName());
		persistent = new BatchPersistent();
		int exitCode = Constants.EXIT_OK;
		try
		{
			Workbook workbook = WorkbookFactory.create(new File(pathFile));
			Sheet datatypeSheet = workbook.getSheetAt(0);

			Row row = CellUtil.getRow(2, datatypeSheet);
			int nStake = 0;
			for (nStake = 2; nStake < 100; nStake++)
			{
				Cell cell = CellUtil.getCell(row, nStake);
				String title = getString(cell.getStringCellValue());
				if (title == null || title.trim().length() <= 0)
					break;
			}
			
			// TODO TEST
			//nStake = 3;

			for (int i = 2; i < nStake; i++)
			{
				PLFTUtenteEntity delegato = null;

				PLFImpresaEntity stakeholder = new PLFImpresaEntity();
				stakeholder.setImpresaTranslation(new PLFImpresaTranslationEntity());

				String partitaIva = get(datatypeSheet, 3, i);
				stakeholder.setPartitaIva(partitaIva);
				stakeholder.setCodFiscale(partitaIva);

				String ragioneSociale = get(datatypeSheet, 4, i);
				stakeholder.getImpresaTranslation().setDescImpresa(ragioneSociale);

				String cognomeLegaleRappresentante = get(datatypeSheet, 5, i);
				stakeholder.setCodiceFiscaleLegaleRappresentante(cognomeLegaleRappresentante);

				String nomeLegaleRappresentante = get(datatypeSheet, 6, i);
				stakeholder.setNomeLegaleRappresentante(nomeLegaleRappresentante);

				String codiceFiscaleLegaleRappresentante = get(datatypeSheet, 7, i);
				stakeholder.setCodiceFiscaleLegaleRappresentante(codiceFiscaleLegaleRappresentante);
		

				String cognomeDelegato = get(datatypeSheet, 9, i);
				String nomeDelegato = get(datatypeSheet, 10, i);
				String codiceFiscaleDelegato = get(datatypeSheet, 11, i);
				String emailDelegato = get(datatypeSheet, 12, i);
				
				if (codiceFiscaleDelegato != null && codiceFiscaleDelegato.trim().length() > 0)
				{
					delegato = new PLFTUtenteEntity();
					delegato.setCodiceFiscale(codiceFiscaleDelegato);
					delegato.setCognome(cognomeDelegato);
					delegato.setDataCreazione(new Date());
					delegato.setEmail(emailDelegato);
					delegato.setIdRuolo(new BigDecimal(4));
					delegato.setNome(nomeDelegato);
				}
				
				String flagIncubatoreCertificato = get(datatypeSheet, 13, i);
				if ("SI".equalsIgnoreCase(flagIncubatoreCertificato))
					stakeholder = addNecessaryField(stakeholder, IAbstractServiceImpl.STATO_IMPRESA_INCUBATORE, "INIT STAKEHOLDER");
				else
					stakeholder = addNecessaryField(stakeholder, IAbstractServiceImpl.STATO_IMPRESA_STAKEHOLDER, "INIT STAKEHOLDER");


				String missione = get(datatypeSheet, 14, i);
				stakeholder.getImpresaTranslation().setDescMissione(missione);

				String attivitaSvolte = get(datatypeSheet, 15, i);
				stakeholder.getImpresaTranslation().setDescAttivita(attivitaSvolte);

				String settori = get(datatypeSheet, 16, i);
				
				// TODO
				settori = "Servizi";
				PLFTSettoreImpresaEntity settore = getSettoreImpresa(settori);
				stakeholder.setPlfTSettoreImpresa(settore);
				
				String indirizzo = get(datatypeSheet, 17, i);
				stakeholder.setDescIndirizzo(indirizzo);

				String numeroCivico = get(datatypeSheet, 18, i);
				stakeholder.setNumeroCivico(numeroCivico);

				String coordX = get(datatypeSheet, 19, i);
				if (coordX != null && coordX.trim().length() > 0)
					stakeholder.setCoordX(new BigDecimal(coordX.trim()));

				String coordY = get(datatypeSheet, 20, i);
				if (coordY != null && coordY.trim().length() > 0)
					stakeholder.setCoordY(new BigDecimal(coordY.trim()));

				String codiceComune = get(datatypeSheet, 21, i);
				stakeholder.setPlfTComune(getComune(codiceComune));

				String emailContatto = get(datatypeSheet, 22, i);
				stakeholder.setEmailContatto(emailContatto);

				String sito = get(datatypeSheet, 23, i);
				stakeholder.setDescSito(sito);

				String facebook = get(datatypeSheet, 24, i);
				stakeholder.setFacebook(facebook);

				String youtube = get(datatypeSheet, 25, i);
				stakeholder.setYoutube(youtube);

				String twitter = get(datatypeSheet, 26, i);
				stakeholder.setTwitter(twitter);

				String linkedin = get(datatypeSheet, 27, i);
				stakeholder.setLinkedin(linkedin);

				String flickr = get(datatypeSheet, 28, i);
				stakeholder.setFlickr(flickr);

				String instagram = get(datatypeSheet, 29, i);
				stakeholder.setIntragram(instagram);
				
				salvaStakeholder(stakeholder, delegato);
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
			Debug.printInfo("Fine elaborazione stakeholder first load ", InizializzatoreStakeholder.class.getName());
		}
		System.exit(exitCode);
	}

	@SuppressWarnings("deprecation")
	private String get(Sheet datatypeSheet, int row, int column)
	{
		Row rowCol = CellUtil.getRow(row, datatypeSheet);
		Cell cell = CellUtil.getCell(rowCol, column);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return getString(cell.getStringCellValue());
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

	private void salvaStakeholder(PLFImpresaEntity stakeholder, PLFTUtenteEntity delegato)
	{
		try
		{
			if (stakeholder != null)
			{
				if (stakeholder.getImpresaTranslation().getDescImpresa() != null
						&& stakeholder.getImpresaTranslation().getDescImpresa().trim().length() > 0
						&& ((stakeholder.getPartitaIva() != null && stakeholder.getPartitaIva().trim().length() > 0) || (stakeholder.getCodFiscale() != null && stakeholder
								.getCodFiscale().trim().length() > 0)))
				{
					PLFImpresaEntity db = persistent.getImpresaService().find(stakeholder.getPartitaIva(), stakeholder.getCodFiscale());
					if (db != null && db.getIdPlfImpresa() != null && db.getIdPlfImpresa().intValue() > 0)
					{
						Debug.printInfo("Stakeholder gia' presente. " + stakeholder.toStringIndentificativo(), Importer.class.getName());
						stakeholder.setIdPlfImpresa(db.getIdPlfImpresa());

						if (db.getImpresaTranslation() != null)
							stakeholder.getImpresaTranslation().setIdPlfImpresa(db.getIdPlfImpresa());

						stakeholder = persistent.getImpresaService().update(stakeholder);
					}
					else
					{
						Debug.printInfo("Inserimento stakeholder. " + stakeholder.toStringIndentificativo(), Importer.class.getName());
						stakeholder = persistent.getImpresaService().update(stakeholder);
					}

					/*
					if (delegato != null && delegato.getCodiceFiscale() != null && delegato.getCodiceFiscale().trim().length() > 0)
						delegato = persistent.getUtenteService().update(delegato, stakeholder.getIdPlfImpresa());
					*/
				}
				else
				{
					Debug.printError("Inserimento stakeholder non riuscita: campi mancanti. " + stakeholder.toStringIndentificativo(), Importer.class.getName());
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

	private PLFImpresaEntity addNecessaryField(PLFImpresaEntity stakeholder, int stato, String fonte)
	{
		if (stakeholder != null)
		{
			PLFTTipoImpresaEntity tipoImpresa = new PLFTTipoImpresaEntity();
			tipoImpresa.setId(new BigDecimal(2));
			stakeholder.setPlfTTipoImpresa(tipoImpresa);

			if (stato > 0)
			{
				PLFTStatoImpresaEntity statoImpresa = new PLFTStatoImpresaEntity();
				statoImpresa.setId(new BigDecimal(stato));
				stakeholder.setPlfTStatoImpresa(statoImpresa);
			}

			if (stakeholder.getImpresaTranslation() == null)
				stakeholder.setImpresaTranslation(new PLFImpresaTranslationEntity());
			stakeholder.getImpresaTranslation().setDescFonte(fonte);


			stakeholder.setPubblicato(true);
		}
		return stakeholder;
	}

	

	private PLFTComuneEntity getComune(String codiceIstat)
	{
		try
		{
			if (persistent != null && codiceIstat != null && codiceIstat.trim().length() > 0)
			{
				List<PLFTComuneEntity> list = persistent.getDecodificaService().getComuneByCodice(codiceIstat.trim());
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

	private PLFTSettoreImpresaEntity getSettoreImpresa(String descrizione)
	{
		try
		{
			if (descrizione != null && descrizione.trim().length() > 0)
			{
				List<PLFTSettoreImpresaEntity> list = persistent.getDecodificaService().getSettoreImpresaPerDescrizione(descrizione.trim());
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
	
	
	private void addSettori(PLFImpresaEntity stakeholder, String settori)
	{
		if (settori != null && settori.trim().length() > 0)
		{
			List<PLFTSettoreImpresaEntity> settoriImpresa = new ArrayList<PLFTSettoreImpresaEntity>();
			StringTokenizer st = new StringTokenizer(settori, "\n");
			while (st.hasMoreTokens())
			{
				PLFTSettoreImpresaEntity s = getSettoreImpresa(st.nextToken());
				if (s != null)
					settoriImpresa.add(s);
			}
			
			System.out.println(settoriImpresa);
		}
	}

}