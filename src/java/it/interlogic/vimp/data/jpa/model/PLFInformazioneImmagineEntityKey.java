package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


@Embeddable
public class PLFInformazioneImmagineEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY KEY ATTRIBUTES
	// ----------------------------------------------------------------------
	@Column(name = "ID_INFORMAZIONE", nullable = false)
	private BigDecimal idInformazione;

	@Column(name = "ID_PLF_TIPO_INFORMAZIONE", nullable = false)
	private BigDecimal idTipoInformazione;

	// ----------------------------------------------------------------------
	// CONSTRUCTORS
	// ----------------------------------------------------------------------
	public PLFInformazioneImmagineEntityKey()
	{
		super();
	}

	public PLFInformazioneImmagineEntityKey(BigDecimal idInformazione, BigDecimal idTipoInformazione)
	{
		super();
		this.idInformazione = idInformazione;
		this.idTipoInformazione = idTipoInformazione;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR KEY FIELDS
	// ----------------------------------------------------------------------

	public BigDecimal getIdInformazione()
	{
		return idInformazione;
	}

	public void setIdInformazione(BigDecimal idInformazione)
	{
		this.idInformazione = idInformazione;
	}

	public BigDecimal getIdTipoInformazione()
	{
		return idTipoInformazione;
	}

	public void setIdTipoInformazione(BigDecimal idTipoInformazione)
	{
		this.idTipoInformazione = idTipoInformazione;
	}

	// ----------------------------------------------------------------------
	// equals METHOD
	// ----------------------------------------------------------------------
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		PLFInformazioneImmagineEntityKey other = (PLFInformazioneImmagineEntityKey) obj;
		// --- Attribute idInformazione
		if (idInformazione == null)
		{
			if (other.idInformazione != null)
				return false;
		}
		else if (!idInformazione.equals(other.idInformazione))
			return false;
		// --- Attribute idTipoInformazione
		if (idTipoInformazione == null)
		{
			if (other.idTipoInformazione != null)
				return false;
		}
		else if (!idTipoInformazione.equals(other.idTipoInformazione))
			return false;

		return true;
	}

	// ----------------------------------------------------------------------
	// hashCode METHOD
	// ----------------------------------------------------------------------
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;

		// --- Attribute idAffidamento
		result = prime * result + ((idInformazione == null) ? 0 : idInformazione.hashCode());
		// --- Attribute idImpresa
		result = prime * result + ((idTipoInformazione == null) ? 0 : idTipoInformazione.hashCode());

		return result;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(idInformazione);
		sb.append("|");
		sb.append(idTipoInformazione);
		return sb.toString();
	}

}
