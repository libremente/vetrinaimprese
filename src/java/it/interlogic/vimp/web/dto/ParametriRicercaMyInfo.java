package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class ParametriRicercaMyInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	Integer tipoInformazione = 0;

	String textRicerca;

	String paginaCorrente;
	String numeroRecord = "8";

	String findServizi = "S";
	String findPacchetti = "S";
	String findProgetti = "S";
	String findProdotti = "S";
	String findTecnologie = "S";
	String findNews = "S";
	String findScadute = "N";

	boolean collapse;
	public static final int DEFAULT_NUMEROPAGINA = 1;
	public static final int DEFAULT_NUMERORECORD = 8;

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

	public String getFindServizi()
	{
		return findServizi;
	}

	public void setFindServizi(String findServizi)
	{
		this.findServizi = findServizi;
	}

	public String getFindPacchetti()
	{
		return findPacchetti;
	}

	public void setFindPacchetti(String findPacchetti)
	{
		this.findPacchetti = findPacchetti;
	}

	

	public String getFindNews()
	{
		return findNews;
	}

	public void setFindNews(String findNews)
	{
		this.findNews = findNews;
	}

	public Integer getTipoInformazione()
	{
		return tipoInformazione;
	}

	public void setTipoInformazione(Integer tipoInformazione)
	{
		this.tipoInformazione = tipoInformazione;
	}

	public String getFindScadute()
	{
		return findScadute;
	}

	public void setFindScadute(String findScadute)
	{
		this.findScadute = findScadute;
	}

	public String getFindProgetti()
	{
		return findProgetti;
	}

	public void setFindProgetti(String findProgetti)
	{
		this.findProgetti = findProgetti;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (collapse ? 1231 : 1237);
		result = prime * result + ((findNews == null) ? 0 : findNews.hashCode());
		result = prime * result + ((findPacchetti == null) ? 0 : findPacchetti.hashCode());
		result = prime * result + ((findProdotti == null) ? 0 : findProdotti.hashCode());
		result = prime * result + ((findProgetti == null) ? 0 : findProgetti.hashCode());
		result = prime * result + ((findScadute == null) ? 0 : findScadute.hashCode());
		result = prime * result + ((findServizi == null) ? 0 : findServizi.hashCode());
		result = prime * result + ((findTecnologie == null) ? 0 : findTecnologie.hashCode());
		result = prime * result + ((numeroRecord == null) ? 0 : numeroRecord.hashCode());
		result = prime * result + ((paginaCorrente == null) ? 0 : paginaCorrente.hashCode());
		result = prime * result + ((textRicerca == null) ? 0 : textRicerca.hashCode());
		result = prime * result + ((tipoInformazione == null) ? 0 : tipoInformazione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametriRicercaMyInfo other = (ParametriRicercaMyInfo) obj;
		if (collapse != other.collapse)
			return false;
		if (findNews == null)
		{
			if (other.findNews != null)
				return false;
		}
		else if (!findNews.equals(other.findNews))
			return false;
		if (findPacchetti == null)
		{
			if (other.findPacchetti != null)
				return false;
		}
		else if (!findPacchetti.equals(other.findPacchetti))
			return false;
		if (findProdotti == null)
		{
			if (other.findProdotti != null)
				return false;
		}
		else if (!findProdotti.equals(other.findProdotti))
			return false;
		if (findProgetti == null)
		{
			if (other.findProgetti != null)
				return false;
		}
		else if (!findProgetti.equals(other.findProgetti))
			return false;
		if (findScadute == null)
		{
			if (other.findScadute != null)
				return false;
		}
		else if (!findScadute.equals(other.findScadute))
			return false;
		if (findServizi == null)
		{
			if (other.findServizi != null)
				return false;
		}
		else if (!findServizi.equals(other.findServizi))
			return false;
		if (findTecnologie == null)
		{
			if (other.findTecnologie != null)
				return false;
		}
		else if (!findTecnologie.equals(other.findTecnologie))
			return false;
		if (numeroRecord == null)
		{
			if (other.numeroRecord != null)
				return false;
		}
		else if (!numeroRecord.equals(other.numeroRecord))
			return false;
		if (paginaCorrente == null)
		{
			if (other.paginaCorrente != null)
				return false;
		}
		else if (!paginaCorrente.equals(other.paginaCorrente))
			return false;
		if (textRicerca == null)
		{
			if (other.textRicerca != null)
				return false;
		}
		else if (!textRicerca.equals(other.textRicerca))
			return false;
		if (tipoInformazione == null)
		{
			if (other.tipoInformazione != null)
				return false;
		}
		else if (!tipoInformazione.equals(other.tipoInformazione))
			return false;
		return true;
	}

	
}
