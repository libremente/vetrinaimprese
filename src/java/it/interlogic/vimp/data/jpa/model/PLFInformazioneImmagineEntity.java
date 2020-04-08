package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PLF_INFORMAZIONE_IMMAGINE")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFInformazioneImmagineEntity.countAll", query = "SELECT COUNT(x) FROM PLFInformazioneImmagineEntity x") })
public class PLFInformazioneImmagineEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFInformazioneImmagineEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Lob
	@Column(name = "IMMAGINE", length = 100000)
	private byte[] immagine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFInformazioneImmagineEntity()
	{
		super();
		this.compositePrimaryKey = new PLFInformazioneImmagineEntityKey();
	}

	public PLFInformazioneImmagineEntityKey getCompositePrimaryKey()
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

	public byte[] getImmagine()
	{
		return immagine;
	}

	public void setImmagine(byte[] immagine)
	{
		this.immagine = immagine;
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
		sb.append(immagine);
		sb.append("|");
		sb.append(dataCancellazione);
		return sb.toString();
	}

}
