package it.interlogic.vimp.domain;

import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AutenticazioneDto implements Serializable
{

	private static final long serialVersionUID = 1L;

	private BigDecimal idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String repassword;
	private String oldpassword;
	private String currentCall;
	
	

	private boolean funzioneImpresa;
	private boolean funzioneImpresaRichiesta;
	private boolean funzioneFormatore;
	private boolean funzioneFormatoreRichiesta;
	private boolean funzioneOpportunita;
	private boolean funzioneOpportunitaRichiesta;
	private boolean funzioneProgettoRicerca;
	private boolean funzioneProgettoRicercaRichiesta;

	
	
	public AutenticazioneDto()
	{}
	
	public AutenticazioneDto(PLFTUtenteEntity utente)
	{
		setIdUtente(utente.getIdUtente());
		setCognome(utente.getCognome());
		setNome(utente.getNome());
		setEmail(utente.getEmail());
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRepassword()
	{
		return repassword;
	}

	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}

	public String getCurrentCall()
	{
		return currentCall;
	}

	public void setCurrentCall(String currentCall)
	{
		this.currentCall = currentCall;
	}

	public String getOldpassword()
	{
		return oldpassword;
	}

	public void setOldpassword(String oldpassword)
	{
		this.oldpassword = oldpassword;
	}

	public BigDecimal getIdUtente()
	{
		return idUtente;
	}

	public void setIdUtente(BigDecimal idUtente)
	{
		this.idUtente = idUtente;
	}

	public boolean isFunzioneImpresa()
	{
		return funzioneImpresa;
	}

	public void setFunzioneImpresa(boolean funzioneImpresa)
	{
		this.funzioneImpresa = funzioneImpresa;
	}

	public boolean isFunzioneImpresaRichiesta()
	{
		return funzioneImpresaRichiesta;
	}

	public void setFunzioneImpresaRichiesta(boolean funzioneImpresaRichiesta)
	{
		this.funzioneImpresaRichiesta = funzioneImpresaRichiesta;
	}

	public boolean isFunzioneFormatore()
	{
		return funzioneFormatore;
	}

	public void setFunzioneFormatore(boolean funzioneFormatore)
	{
		this.funzioneFormatore = funzioneFormatore;
	}

	public boolean isFunzioneFormatoreRichiesta()
	{
		return funzioneFormatoreRichiesta;
	}

	public void setFunzioneFormatoreRichiesta(boolean funzioneFormatoreRichiesta)
	{
		this.funzioneFormatoreRichiesta = funzioneFormatoreRichiesta;
	}

	public boolean isFunzioneOpportunita()
	{
		return funzioneOpportunita;
	}

	public void setFunzioneOpportunita(boolean funzioneOpportunita)
	{
		this.funzioneOpportunita = funzioneOpportunita;
	}

	public boolean isFunzioneOpportunitaRichiesta()
	{
		return funzioneOpportunitaRichiesta;
	}

	public void setFunzioneOpportunitaRichiesta(boolean funzioneOpportunitaRichiesta)
	{
		this.funzioneOpportunitaRichiesta = funzioneOpportunitaRichiesta;
	}

	public boolean isFunzioneProgettoRicerca()
	{
		return funzioneProgettoRicerca;
	}

	public void setFunzioneProgettoRicerca(boolean funzioneProgettoRicerca)
	{
		this.funzioneProgettoRicerca = funzioneProgettoRicerca;
	}

	public boolean isFunzioneProgettoRicercaRichiesta()
	{
		return funzioneProgettoRicercaRichiesta;
	}

	public void setFunzioneProgettoRicercaRichiesta(boolean funzioneProgettoRicercaRichiesta)
	{
		this.funzioneProgettoRicercaRichiesta = funzioneProgettoRicercaRichiesta;
	}
	
	

}
