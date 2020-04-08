package it.interlogic.vimp.web.security;

import java.io.Serializable;

public class InfoUtente implements Serializable
{

	private static final long serialVersionUID = 1L;

	String codiceFiscale;
	String cognome;
	String nome;

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

}
