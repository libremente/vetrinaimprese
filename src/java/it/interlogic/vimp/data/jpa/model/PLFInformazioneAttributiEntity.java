package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLF_INFORMAZIONE_ATTRIBUTI")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFInformazioneAttributiEntity.countAll", query = "SELECT COUNT(x) FROM PLFInformazioneAttributiEntity x") })
public class PLFInformazioneAttributiEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFInformazioneAttributiEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "NUMERO_VISITE")
	private BigDecimal numeroVisite;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ULTIMA_VISITA")
	private Date dataUltimaVisita;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFInformazioneAttributiEntity()
	{
		super();
		this.compositePrimaryKey = new PLFInformazioneAttributiEntityKey();
	}

	public PLFInformazioneAttributiEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	public void setIdInformazione(BigDecimal idInformazione)
	{
		this.compositePrimaryKey.setIdInformazione(idInformazione);
	}

	public BigDecimal getIdInformazione()
	{
		return this.compositePrimaryKey.getIdInformazione();
	}

	public void setIdTipoInformazione(BigDecimal idTipoInformazione)
	{
		this.compositePrimaryKey.setIdTipoInformazione(idTipoInformazione);
	}

	public BigDecimal getIdTipoInformazione()
	{
		return this.compositePrimaryKey.getIdTipoInformazione();
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public BigDecimal getNumeroVisite()
	{
		return numeroVisite;
	}

	public void setNumeroVisite(BigDecimal numeroVisite)
	{
		this.numeroVisite = numeroVisite;
	}

	public Date getDataUltimaVisita()
	{
		return dataUltimaVisita;
	}

	public void setDataUltimaVisita(Date dataUltimaVisita)
	{
		this.dataUltimaVisita = dataUltimaVisita;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

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
		sb.append(numeroVisite);
		sb.append("|");
		sb.append(dataUltimaVisita);
		return sb.toString();
	}

}
