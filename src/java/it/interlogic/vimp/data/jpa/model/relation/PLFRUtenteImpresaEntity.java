package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_UTENTE_IMPRESA")
public class PLFRUtenteImpresaEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFRUtenteImpresaEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRUtenteImpresaEntity()
	{
		super();
		this.compositePrimaryKey = new PLFRUtenteImpresaEntityKey();
	}

	public PLFRUtenteImpresaEntityKey getCompositePrimaryKey()
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

	public void setIdUtente(BigDecimal idUtente)
	{
		this.compositePrimaryKey.setIdUtente(idUtente);
	}

	public BigDecimal getIdUtente()
	{
		return this.compositePrimaryKey.getIdUtente();
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
