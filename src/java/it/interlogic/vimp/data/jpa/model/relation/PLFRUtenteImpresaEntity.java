package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;
	
	

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
	

	public Date getDataCancellazione()
	{
		return dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
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
