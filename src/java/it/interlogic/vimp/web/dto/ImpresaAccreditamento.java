package it.interlogic.vimp.web.dto;

import it.interlogic.vimp.data.jpa.model.PLFTStatoImpresaEntity;

import java.io.Serializable;

public class ImpresaAccreditamento implements Serializable
{
	private static final long serialVersionUID = 1L;

	String partitaIva;
	String codiceFiscale;
	PLFTStatoImpresaEntity statoImpresa;

	public ImpresaAccreditamento()
	{
		statoImpresa = new PLFTStatoImpresaEntity();
	}

	public ImpresaAccreditamento(String partitaIva, String codiceFiscale)
	{
		this.partitaIva = partitaIva;
		this.codiceFiscale = codiceFiscale;
	}

	public ImpresaAccreditamento(String partitaIva, String codiceFiscale, PLFTStatoImpresaEntity statoImpresa)
	{
		this.partitaIva = partitaIva;
		this.codiceFiscale = codiceFiscale;
		this.statoImpresa = statoImpresa;
	}

	public String getPartitaIva()
	{
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public PLFTStatoImpresaEntity getStatoImpresa()
	{
		return statoImpresa;
	}

	public void setStatoImpresa(PLFTStatoImpresaEntity statoImpresa)
	{
		this.statoImpresa = statoImpresa;
	}

	@Override
	public String toString()
	{
		return "ImpresaAccreditamento [partitaIva=" + partitaIva + ", codiceFiscale=" + codiceFiscale + ", statoImpresa=" + statoImpresa + "]";
	}
	
	

}
