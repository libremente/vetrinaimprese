package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class ParametriRicerca implements Serializable
{
	private static final long serialVersionUID = 1L;

	Integer tipoInformazione = 0;

	String textRicerca;
	Integer numeroInformazione = -1;

	String paginaCorrente;
	String numeroRecord;

	String findStartup = "S";
	String findPmi = "S";
	String findSpinoff = "S";
	String findGrandi = "S";

	String findIncubatori = "N";

	String findServizi = "S";
	String findPacchettiServizi = "S";

	String findProdotti = "S";
	String findTecnologie = "S";
	String findInnovazione = "S";
	String findProgetti = "S";

	String findNewsEvidenza = "N";

	boolean collapse;
	public static final int DEFAULT_NUMEROPAGINA = 1;
	public static final int DEFAULT_NUMERORECORD = 8;

	public boolean isNull()
	{
		return (textRicerca == null && numeroInformazione == null);
	}

	public String getPaginaCorrente()
	{
		return Integer.toString(getValoreNumeroPagina());
	}

	public void setPaginaCorrente(String paginaCorrente)
	{
		this.paginaCorrente = paginaCorrente;
	}

	public String getNumeroRecord()
	{
		return Integer.toString(getValoreNumeroRecord());
	}

	public void setNumeroRecord(String numeroRecord)
	{
		this.numeroRecord = numeroRecord;
	}

	public void reset()
	{
		this.paginaCorrente = "" + DEFAULT_NUMEROPAGINA;
	}

	/**
	 * Parsifica il parametro numeroPagina. Se non è impostato o non è valido
	 * restituisco 1
	 * 
	 * @return
	 */
	public int getValoreNumeroPagina()
	{
		int returnValue = DEFAULT_NUMEROPAGINA;
		try
		{
			int parametro = Integer.parseInt(paginaCorrente);
			if (parametro > 0)
				returnValue = parametro;
		}
		catch (Exception e)
		{
		}
		return returnValue;
	}

	public int getValoreNumeroRecord()
	{
		int returnValue = DEFAULT_NUMERORECORD;
		try
		{
			int parametro = Integer.parseInt(numeroRecord);
			if (parametro > 0)
				returnValue = parametro;
		}
		catch (Exception e)
		{
		}
		return returnValue;
	}

	public boolean isCollapse()
	{
		return collapse;
	}

	public void setCollapse(boolean collapse)
	{
		this.collapse = collapse;
	}

	public String getTextRicerca()
	{
		return textRicerca;
	}

	public void setTextRicerca(String textRicerca)
	{
		this.textRicerca = textRicerca;
	}

	public Integer getNumeroInformazione()
	{
		return numeroInformazione;
	}

	public void setNumeroInformazione(Integer numeroInformazione)
	{
		this.numeroInformazione = numeroInformazione;
	}

	public Integer getTipoInformazione()
	{
		return tipoInformazione;
	}

	public void setTipoInformazione(Integer tipoInformazione)
	{
		this.tipoInformazione = tipoInformazione;
	}

	public String getFindStartup()
	{
		return findStartup;
	}

	public void setFindStartup(String findStartup)
	{
		this.findStartup = findStartup;
	}

	public String getFindPmi()
	{
		return findPmi;
	}

	public void setFindPmi(String findPmi)
	{
		this.findPmi = findPmi;
	}

	public String getFindSpinoff()
	{
		return findSpinoff;
	}

	public void setFindSpinoff(String findSpinoff)
	{
		this.findSpinoff = findSpinoff;
	}

	public String getFindGrandi()
	{
		return findGrandi;
	}

	public void setFindGrandi(String findGrandi)
	{
		this.findGrandi = findGrandi;
	}

	public String getFindProdotti()
	{
		return findProdotti;
	}

	public void setFindProdotti(String findProdotti)
	{
		this.findProdotti = findProdotti;
	}

	public String getFindTecnologie()
	{
		return findTecnologie;
	}

	public void setFindTecnologie(String findTecnologie)
	{
		this.findTecnologie = findTecnologie;
	}

	public String getFindInnovazione()
	{
		return findInnovazione;
	}

	public void setFindInnovazione(String findInnovazione)
	{
		this.findInnovazione = findInnovazione;
	}

	public String getFindProgetti()
	{
		return findProgetti;
	}

	public void setFindProgetti(String findProgetti)
	{
		this.findProgetti = findProgetti;
	}

	public String getFindServizi()
	{
		return findServizi;
	}

	public void setFindServizi(String findServizi)
	{
		this.findServizi = findServizi;
	}

	public String getFindPacchettiServizi()
	{
		return findPacchettiServizi;
	}

	public void setFindPacchettiServizi(String findPacchettiServizi)
	{
		this.findPacchettiServizi = findPacchettiServizi;
	}

	public String getFindNewsEvidenza()
	{
		return findNewsEvidenza;
	}

	public void setFindNewsEvidenza(String findNewsEvidenza)
	{
		this.findNewsEvidenza = findNewsEvidenza;
	}

	public String getFindIncubatori()
	{
		return findIncubatori;
	}

	public void setFindIncubatori(String findIncubatori)
	{
		this.findIncubatori = findIncubatori;
	}

}
