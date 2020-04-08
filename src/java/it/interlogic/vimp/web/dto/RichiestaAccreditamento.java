package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RichiestaAccreditamento implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String partitaIvaInput;
	private String codiceFiscaleInput;
	private String emailInput;
	private BigDecimal idStatoImpresaInput;
	private List<ImpresaAccreditamento> impresaAccreditamento;

	private String rappresentante = "N";
	private String incaricato = "N";

	public RichiestaAccreditamento()
	{
		impresaAccreditamento = new ArrayList<ImpresaAccreditamento>();
	}

	public RichiestaAccreditamento(List<ImpresaAccreditamento> impresaAccreditamento)
	{
		this.impresaAccreditamento = impresaAccreditamento;
	}

	public List<ImpresaAccreditamento> getImpresaAccreditamento()
	{
		return impresaAccreditamento;
	}

	public void setImpresaAccreditamento(List<ImpresaAccreditamento> impresaAccreditamento)
	{
		this.impresaAccreditamento = impresaAccreditamento;
	}

	public String getPartitaIvaInput()
	{
		return partitaIvaInput;
	}

	public void setPartitaIvaInput(String partitaIvaInput)
	{
		this.partitaIvaInput = partitaIvaInput;
	}

	public String getCodiceFiscaleInput()
	{
		return codiceFiscaleInput;
	}

	public void setCodiceFiscaleInput(String codiceFiscaleInput)
	{
		this.codiceFiscaleInput = codiceFiscaleInput;
	}

	public BigDecimal getIdStatoImpresaInput()
	{
		return idStatoImpresaInput;
	}

	public void setIdStatoImpresaInput(BigDecimal idStatoImpresaInput)
	{
		this.idStatoImpresaInput = idStatoImpresaInput;
	}

	public String getEmailInput()
	{
		return emailInput;
	}

	public void setEmailInput(String emailInput)
	{
		this.emailInput = emailInput;
	}

	public String getRappresentante()
	{
		return rappresentante;
	}

	public void setRappresentante(String rappresentante)
	{
		this.rappresentante = rappresentante;
	}

	public String getIncaricato()
	{
		return incaricato;
	}

	public void setIncaricato(String incaricato)
	{
		this.incaricato = incaricato;
	}

	@Override
	public String toString()
	{
		return "RichiestaAccreditamento [partitaIvaInput=" + partitaIvaInput + ", codiceFiscaleInput=" + codiceFiscaleInput + ", emailInput=" + emailInput
				+ ", idStatoImpresaInput=" + idStatoImpresaInput + ", impresaAccreditamento=" + impresaAccreditamento + "]";
	}

}
