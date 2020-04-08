package it.interlogic.vimp.domain;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.service.impl.IAbstractServiceImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class UtenteDto implements Serializable
{
	public static final int RUOLO_ADMIN = 1;
	public static final int RUOLO_BACKOFFICE = 2;
	public static final int RUOLO_IMPRESA = 3;
	public static final int RUOLO_STAKEHOLDER = 4;

	public static final int RUOLO_VISITOR = 6;
	public static final int RUOLO_VISITOR_LOGIN = 7;

	private static final long serialVersionUID = 1L;

	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String email;
	private int ruolo;
	private BigDecimal idUtente;
	private List<PLFImpresaEntity> plfImpresas;

	public UtenteDto()
	{
	}

	public UtenteDto(PLFTUtenteEntity utente)
	{
		this.idUtente = utente.getIdUtente();
		this.cognome = utente.getCognome();
		this.nome = utente.getNome();
		this.email = utente.getEmail();
		this.ruolo = utente.getRuolo().getIdRuolo().intValue();
		this.plfImpresas = utente.getPlfImpresas();
		this.codiceFiscale = utente.getCodiceFiscale();
	}
	
	public String getAlias()
	{
		if (this.cognome != null && this.cognome.trim().length()>0 && !".".equals(this.cognome.trim()))
			return this.cognome;
		if (this.nome != null && this.nome.trim().length()>0 && !".".equals(this.nome.trim()))
			return this.nome;
		return this.codiceFiscale;
	}

	public BigDecimal getIdUtente()
	{
		return idUtente;
	}
	
	public void setIdUtente(BigDecimal idUtente)
	{
		this.idUtente = idUtente;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
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

	public boolean isBackoffice()
	{
		return ruolo == RUOLO_BACKOFFICE;
	}

	public boolean isAdministrator()
	{
		return ruolo == RUOLO_ADMIN;
	}

	public boolean isImpresa()
	{
		return ruolo == RUOLO_IMPRESA;
	}
	
	public boolean isStakeholder()
	{
		return ruolo == RUOLO_STAKEHOLDER;
	}

	public boolean isVisitor()
	{
		return ruolo == RUOLO_VISITOR;
	}
	
	
	public boolean isVisitorLogin()
	{
		return ruolo == RUOLO_VISITOR_LOGIN;
	}
	
	
	
	
	public boolean isWriter(int tipoInformazione)
	{
		switch (tipoInformazione)
		{
			case IAbstractServiceImpl.TIPO_INFO_IMPRESA:
			case IAbstractServiceImpl.TIPO_INFO_STAKEHOLDER:
				return false;
			case IAbstractServiceImpl.TIPO_INFO_PROGETTO_PRODOTTO:
				return ruolo == RUOLO_STAKEHOLDER || ruolo == RUOLO_IMPRESA;
			case IAbstractServiceImpl.TIPO_INFO_OPPORTUNITA:
				return ruolo == RUOLO_BACKOFFICE;
			case IAbstractServiceImpl.TIPO_INFO_NEWS:
				return ruolo == RUOLO_STAKEHOLDER || ruolo == RUOLO_IMPRESA;
			case IAbstractServiceImpl.TIPO_INFO_SERVIZIO:
				return ruolo == RUOLO_STAKEHOLDER;
			case IAbstractServiceImpl.TIPO_INFO_PACCHETTO_SERVIZI:
				return ruolo == RUOLO_STAKEHOLDER || ruolo == RUOLO_BACKOFFICE;
			default : return false;
		}
	}
	
	
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public int getRuolo()
	{
		return ruolo;
	}

	public void setRuolo(int ruolo)
	{
		this.ruolo = ruolo;
	}

	public List<PLFImpresaEntity> getPlfImpresas()
	{
		return plfImpresas;
	}

	public void setPlfImpresas(List<PLFImpresaEntity> plfImpresas)
	{
		this.plfImpresas = plfImpresas;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(idUtente);
		sb.append("]-");
		sb.append("[");
		sb.append(codiceFiscale);
		sb.append("]:");
		sb.append(nome);
		sb.append("|");
		sb.append(cognome);
		sb.append("|");
		sb.append(ruolo);
		return sb.toString();
	}

}
