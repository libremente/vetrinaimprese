package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFRImpresaStakeholderEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_STAKEHOLDER", nullable = false)
	private BigDecimal idStakeholder;

	@Column(name = "ID_PLF_IMPRESA", nullable = false)
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRImpresaStakeholderEntityKey()
	{
		super();
	}

	public PLFRImpresaStakeholderEntityKey(BigDecimal idStakeholder, BigDecimal idImpresa)
	{
		super();
		this.idStakeholder = idStakeholder;
		this.idImpresa = idImpresa;
	}

	public BigDecimal getIdStakeholder()
	{
		return idStakeholder;
	}

	public void setIdStakeholder(BigDecimal idStakeholder)
	{
		this.idStakeholder = idStakeholder;
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
		result = prime * result + ((idStakeholder == null) ? 0 : idStakeholder.hashCode());
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
		PLFRImpresaStakeholderEntityKey other = (PLFRImpresaStakeholderEntityKey) obj;
		if (idImpresa == null)
		{
			if (other.idImpresa != null)
				return false;
		}
		else if (!idImpresa.equals(other.idImpresa))
			return false;
		if (idStakeholder == null)
		{
			if (other.idStakeholder != null)
				return false;
		}
		else if (!idStakeholder.equals(other.idStakeholder))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PLFRImpresaStakeholderEntityKey [idStakeholder=" + idStakeholder + ", idImpresa=" + idImpresa + "]";
	}

}
