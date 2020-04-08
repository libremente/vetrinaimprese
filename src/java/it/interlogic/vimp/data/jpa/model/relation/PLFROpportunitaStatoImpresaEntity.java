package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_OPPORTUNITA_STATO_IMPRESA")
public class PLFROpportunitaStatoImpresaEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFROpportunitaStatoImpresaEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFROpportunitaStatoImpresaEntity()
	{
		super();
		this.compositePrimaryKey = new PLFROpportunitaStatoImpresaEntityKey();
	}

	public PLFROpportunitaStatoImpresaEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public void setIdOpportunita(BigDecimal idOpportunita)
	{
		this.compositePrimaryKey.setIdOpportunita(idOpportunita);
	}

	public BigDecimal getIdOpportunita()
	{
		return this.compositePrimaryKey.getIdOpportunita();
	}

	public void setIdStatoImpresa(BigDecimal idStatoImpresa)
	{
		this.compositePrimaryKey.setIdStatoImpresa(idStatoImpresa);
	}

	public BigDecimal getIdStatoImpresa()
	{
		return this.compositePrimaryKey.getIdStatoImpresa();
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
