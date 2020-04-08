package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_IMPRESA_INNOVAZIONE")
public class PLFRImpresaInnovazioneEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFRImpresaInnovazioneEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRImpresaInnovazioneEntity()
	{
		super();
		this.compositePrimaryKey = new PLFRImpresaInnovazioneEntityKey();
	}

	public PLFRImpresaInnovazioneEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.compositePrimaryKey.setIdImpresa(idImpresa);
	}

	public BigDecimal getIdImpresa()
	{
		return this.compositePrimaryKey.getIdImpresa();
	}

	public void setIdInnovazione(BigDecimal idInnovazione)
	{
		this.compositePrimaryKey.setIdInnovazione(idInnovazione);
	}

	public BigDecimal getIdInnovazione()
	{
		return this.compositePrimaryKey.getIdInnovazione();
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
