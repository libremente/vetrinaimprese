package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class MailContattaci implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String cognome;
	public String nome;
	public String email;
	public String oggetto;
	public String messaggio;

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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getOggetto()
	{
		return oggetto;
	}

	public void setOggetto(String oggetto)
	{
		this.oggetto = oggetto;
	}

	public String getMessaggio()
	{
		return messaggio;
	}

	public void setMessaggio(String messaggio)
	{
		this.messaggio = messaggio;
	}

}
