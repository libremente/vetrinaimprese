package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ParametriRicercaAccreditamenti implements Serializable
{
	private static final long serialVersionUID = 1L;

	Integer tipoInformazione = 0;
	
	String findValidate = "S";
	String findValidateAuto = "S";
	String findNonValidate = "S";
	String findAttesa = "S";
	String testo;
	String dateRange;

	String paginaCorrente;
	String numeroRecord;

	List<Colonna> colonne;

	List<String> colonneResult;

	BigDecimal selectedIdRichiesta;

	boolean collapse;

	public static final int DEFAULT_NUMEROPAGINA = 1;
	public static final int DEFAULT_NUMERORECORD = 10;

	public boolean isFilterActivate()
	{
		boolean checkActivate = true;
		if ("S".equalsIgnoreCase(findValidate) &&
				"S".equalsIgnoreCase(findValidateAuto) &&
				"S".equalsIgnoreCase(findNonValidate) &&
				"S".equalsIgnoreCase(findAttesa))
			checkActivate = false;
		else if ("N".equalsIgnoreCase(findValidate) &&
				"N".equalsIgnoreCase(findValidateAuto) &&
				"N".equalsIgnoreCase(findNonValidate) &&
				"N".equalsIgnoreCase(findAttesa))
			checkActivate = false;
		
		
		
		return !StringUtils.isEmpty(testo) || !StringUtils.isEmpty(dateRange) || checkActivate;
	}

	public boolean isNull()
	{
		return (findValidate == null && findValidateAuto == null && colonneResult == null && findNonValidate == null && findAttesa == null && testo == null && numeroRecord == null
				&& paginaCorrente == null && dateRange == null);
	}

	public boolean isColonnaVisualizzabile(String nomeDato)
	{
		for (Colonna colonna : colonne)
		{
			if (colonna.getNomeDato().equals(nomeDato))
				return colonna.isChecked();
		}
		return false;
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

	public List<Colonna> getColonne()
	{
		return colonne;
	}

	public void setColonne(List<Colonna> colonne)
	{
		this.colonne = colonne;
	}

	public List<String> getColonneResult()
	{
		return colonneResult;
	}

	public void setColonneResult(List<String> colonneResult)
	{
		this.colonneResult = colonneResult;
	}

	public boolean isCollapse()
	{
		return collapse;
	}

	public void setCollapse(boolean collapse)
	{
		this.collapse = collapse;
	}

	public String getFindValidate()
	{
		return findValidate;
	}

	public void setFindValidate(String findValidate)
	{
		this.findValidate = findValidate;
	}

	public String getFindValidateAuto()
	{
		return findValidateAuto;
	}

	public void setFindValidateAuto(String findValidateAuto)
	{
		this.findValidateAuto = findValidateAuto;
	}

	public String getFindNonValidate()
	{
		return findNonValidate;
	}

	public void setFindNonValidate(String findNonValidate)
	{
		this.findNonValidate = findNonValidate;
	}

	public String getFindAttesa()
	{
		return findAttesa;
	}

	public void setFindAttesa(String findAttesa)
	{
		this.findAttesa = findAttesa;
	}

	public String getTesto()
	{
		return testo;
	}

	public void setTesto(String testo)
	{
		this.testo = testo;
	}

	public String getDateRange()
	{
		return dateRange;
	}

	public void setDateRange(String dateRange)
	{
		this.dateRange = dateRange;
	}

	public BigDecimal getSelectedIdRichiesta()
	{
		return selectedIdRichiesta;
	}

	public void setSelectedIdRichiesta(BigDecimal selectedIdRichiesta)
	{
		this.selectedIdRichiesta = selectedIdRichiesta;
	}

	public Integer getTipoInformazione()
	{
		return tipoInformazione;
	}

	public void setTipoInformazione(Integer tipoInformazione)
	{
		this.tipoInformazione = tipoInformazione;
	}
	
	

}
