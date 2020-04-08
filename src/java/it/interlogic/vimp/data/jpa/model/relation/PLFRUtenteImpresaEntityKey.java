package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFRUtenteImpresaEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_UTENTE", nullable = false)
	private BigDecimal idUtente;

	@Column(name = "ID_PLF_IMPRESA", nullable = false)
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRUtenteImpresaEntityKey()
	{
		super();
	}

	public PLFRUtenteImpresaEntityKey(BigDecimal idUtente, BigDecimal idImpresa)
	{
		super();
		this.idUtente = idUtente;
		this.idImpresa = idImpresa;
	}

	public BigDecimal getIdUtente()
	{
		return idUtente;
	}

	public void setIdUtente(BigDecimal idUtente)
	{
		this.idUtente = idUtente;
	}

	public BigDecimal getIdImpresa()
	{
		return idImpresa;
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.idImpresa = idImpresa;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImpresa == null) ? 0 : idImpresa.hashCode());
		result = prime * result + ((idUtente == null) ? 0 : idUtente.hashCode());
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
		PLFRUtenteImpresaEntityKey other = (PLFRUtenteImpresaEntityKey) obj;
		if (idImpresa == null)
		{
			if (other.idImpresa != null)
				return false;
		}
		else if (!idImpresa.equals(other.idImpresa))
			return false;
		if (idUtente == null)
		{
			if (other.idUtente != null)
				return false;
		}
		else if (!idUtente.equals(other.idUtente))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PLFRUtenteImpresaEntityKey [idUtente=" + idUtente + ", idImpresa=" + idImpresa + "]";
	}

}
