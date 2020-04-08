package it.interlogic.vimp.service.ws.toponomastica;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Strada implements Serializable
{

	private static final long serialVersionUID = 1L;

	@SerializedName("CODICE_STRADA")
	private String codiceStrada;

	@SerializedName("NOME_VIA")
	private String nomeVia;

	public String getCodiceStrada()
	{
		return codiceStrada;
	}

	public void setCodiceStrada(String codiceStrada)
	{
		this.codiceStrada = codiceStrada;
	}

	public String getNomeVia()
	{
		return nomeVia;
	}

	public void setNomeVia(String nomeVia)
	{
		this.nomeVia = nomeVia;
	}

	@Override
	public String toString()
	{
		return "Strada [codiceStrada=" + codiceStrada + ", nomeVia=" + nomeVia + "]";
	}

}
