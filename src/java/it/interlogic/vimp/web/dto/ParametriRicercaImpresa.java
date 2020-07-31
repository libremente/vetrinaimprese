package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ParametriRicercaImpresa implements Serializable
{
	private static final long serialVersionUID = 1L;

	Integer tipoInformazione = 0;
	String ragioneSociale;
	String settore;
	String provincia;
	String comune;
	String statoImpresa;
	String elementiInnovazione;
	String mercatiRiferimento;
	String brevetti;
	String iscrizioneRegistro;
	String iscrizioneSpeciale;
	String stakeholder;
	String accreditata;

	String paginaCorrente;
	String numeroRecord;

	List<Colonna> colonne;

	List<String> colonneResult;

	BigDecimal selectedIdAImpresa;

	boolean collapse;

	public static final int DEFAULT_NUMEROPAGINA = 1;
	public static final int DEFAULT_NUMERORECORD = 10;

	public boolean isFilterActivate()
	{
		return !StringUtils.isEmpty(ragioneSociale) || !StringUtils.isEmpty(settore) || !StringUtils.isEmpty(provincia) || !StringUtils.isEmpty(comune) || !StringUtils.isEmpty(statoImpresa)
				|| !StringUtils.isEmpty(elementiInnovazione) || !StringUtils.isEmpty(mercatiRiferimento) || !StringUtils.isEmpty(brevetti)
				|| !StringUtils.isEmpty(iscrizioneRegistro) || !StringUtils.isEmpty(iscrizioneSpeciale) || !StringUtils.isEmpty(stakeholder) || !StringUtils.isEmpty(accreditata);
	}

	public boolean isNull()
	{
		return (ragioneSociale == null && settore == null && colonneResult == null && provincia == null && comune == null && statoImpresa == null && numeroRecord == null && paginaCorrente == null
				&& elementiInnovazione == null && selectedIdAImpresa == null && mercatiRiferimento == null && brevetti == null && iscrizioneRegistro == null
				&& iscrizioneSpeciale == null && stakeholder == null  && accreditata == null);
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

	public String getRagioneSociale()
	{
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public String getSettore()
	{
		return settore;
	}

	public void setSettore(String settore)
	{
		this.settore = settore;
	}

	public String getComune()
	{
		return comune;
	}

	public void setComune(String comune)
	{
		this.comune = comune;
	}

	public String getStatoImpresa()
	{
		return statoImpresa;
	}

	public void setStatoImpresa(String statoImpresa)
	{
		this.statoImpresa = statoImpresa;
	}

	public String getElementiInnovazione()
	{
		return elementiInnovazione;
	}

	public void setElementiInnovazione(String elementiInnovazione)
	{
		this.elementiInnovazione = elementiInnovazione;
	}

	public String getMercatiRiferimento()
	{
		return mercatiRiferimento;
	}

	public void setMercatiRiferimento(String mercatiRiferimento)
	{
		this.mercatiRiferimento = mercatiRiferimento;
	}

	public String getBrevetti()
	{
		return brevetti;
	}

	public void setBrevetti(String brevetti)
	{
		this.brevetti = brevetti;
	}

	public String getIscrizioneRegistro()
	{
		return iscrizioneRegistro;
	}

	public void setIscrizioneRegistro(String iscrizioneRegistro)
	{
		this.iscrizioneRegistro = iscrizioneRegistro;
	}

	public String getIscrizioneSpeciale()
	{
		return iscrizioneSpeciale;
	}

	public void setIscrizioneSpeciale(String iscrizioneSpeciale)
	{
		this.iscrizioneSpeciale = iscrizioneSpeciale;
	}

	public String getStakeholder()
	{
		return stakeholder;
	}

	public void setStakeholder(String stakeholder)
	{
		this.stakeholder = stakeholder;
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

	public BigDecimal getSelectedIdAImpresa()
	{
		return selectedIdAImpresa;
	}

	public void setSelectedIdAImpresa(BigDecimal selectedIdAImpresa)
	{
		this.selectedIdAImpresa = selectedIdAImpresa;
	}

	public boolean isCollapse()
	{
		return collapse;
	}

	public void setCollapse(boolean collapse)
	{
		this.collapse = collapse;
	}

	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}

	public Integer getTipoInformazione()
	{
		return tipoInformazione;
	}

	public void setTipoInformazione(Integer tipoInformazione)
	{
		this.tipoInformazione = tipoInformazione;
	}

	public String getAccreditata()
	{
		return accreditata;
	}

	public void setAccreditata(String accreditata)
	{
		this.accreditata = accreditata;
	}
	
	

}
