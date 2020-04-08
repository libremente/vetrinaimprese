package it.interlogic.vimp.web.dto;

import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class CollaborazioneResult implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static CollaborazioneResult getInstance(String error)
	{
		CollaborazioneResult ret = new CollaborazioneResult();
		ret.setError(error);
		return ret;
	}

	public static CollaborazioneResult getInstance(PLFCollaborazioniEntity collaborazione)
	{
		CollaborazioneResult ret = new CollaborazioneResult();

		ret.setId(collaborazione.getIdPlfCollaborazioni().toString());

		if (collaborazione.getCodiceFiscale() != null && collaborazione.getCodiceFiscale().trim().length() > 0)
			ret.setCodiceFiscale(collaborazione.getCodiceFiscale());
		else
			ret.setCodiceFiscale("");

		if (collaborazione.getPartitaIva() != null && collaborazione.getPartitaIva().trim().length() > 0)
			ret.setPartitaIva(collaborazione.getPartitaIva());
		else
			ret.setPartitaIva("");

		if (collaborazione.getRagioneSociale() != null && collaborazione.getRagioneSociale().trim().length() > 0)
			ret.setRagioneSociale(collaborazione.getRagioneSociale());
		else
			ret.setRagioneSociale("");

		if (collaborazione.getDataInizioCollaborazione() != null)
			ret.setDataInizio(dateFormat.format(collaborazione.getDataInizioCollaborazione()));
		else
			ret.setDataInizio("");

		if (collaborazione.getDataFineCollaborazione() != null)
			ret.setDataFine(dateFormat.format(collaborazione.getDataFineCollaborazione()));
		else
			ret.setDataFine("");

		if (collaborazione.getPlfTNazione() != null && collaborazione.getPlfTNazione().getIdPlfTNazione() != null
				&& collaborazione.getPlfTNazione().getIdPlfTNazione().intValue() > 0)
			ret.setNazione(collaborazione.getPlfTNazione().getDescrizione());
		else
			ret.setNazione("");

		if (collaborazione.getPlfTComune() != null && collaborazione.getPlfTComune().getIdComune() != null && collaborazione.getPlfTComune().getIdComune().intValue() > 0)
			ret.setComune(collaborazione.getPlfTComune().getDescComune());
		else
			ret.setComune("");

		return ret;
	}

	private String error;
	private String id;
	private String codiceFiscale;
	private String dataFine;
	private String dataInizio;
	private String partitaIva;
	private String ragioneSociale;
	private String nazione;
	private String comune;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataFine()
	{
		return dataFine;
	}

	public void setDataFine(String dataFine)
	{
		this.dataFine = dataFine;
	}

	public String getDataInizio()
	{
		return dataInizio;
	}

	public void setDataInizio(String dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public String getPartitaIva()
	{
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public String getRagioneSociale()
	{
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public String getNazione()
	{
		return nazione;
	}

	public void setNazione(String nazione)
	{
		this.nazione = nazione;
	}

	public String getComune()
	{
		return comune;
	}

	public void setComune(String comune)
	{
		this.comune = comune;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

}
