package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_IMPRESA_STAKEHOLDER")
public class PLFRImpresaStakeholderEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFRImpresaStakeholderEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRImpresaStakeholderEntity()
	{
		super();
		this.compositePrimaryKey = new PLFRImpresaStakeholderEntityKey();
	}

	public PLFRImpresaStakeholderEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public void setIdStakeholder(BigDecimal idStakeholder)
	{
		this.compositePrimaryKey.setIdStakeholder(idStakeholder);
	}

	public BigDecimal getIdStakeholder()
	{
		return this.compositePrimaryKey.getIdStakeholder();
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.compositePrimaryKey.setIdImpresa(idImpresa);
	}

	public BigDecimal getIdImpresa()
	{
		return this.compositePrimaryKey.getIdImpresa();
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (compositePrimaryKey != null)
		{
			sb.append(compositePrimaryKey.toString());
		}
		else
		{
			sb.append("(null-key)");
		}
		sb.append("]:");
		return sb.toString();
	}

}
