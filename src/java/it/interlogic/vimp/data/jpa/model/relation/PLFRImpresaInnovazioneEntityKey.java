package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFRImpresaInnovazioneEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_INNOVAZIONE", nullable = false)
	private BigDecimal idInnovazione;

	@Column(name = "ID_PLF_IMPRESA", nullable = false)
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRImpresaInnovazioneEntityKey()
	{
		super();
	}

	public PLFRImpresaInnovazioneEntityKey(BigDecimal idImpresa, BigDecimal idInnovazione)
	{
		super();
		this.idImpresa = idImpresa;
		this.idInnovazione = idInnovazione;
	}

	public BigDecimal getIdInnovazione()
	{
		return idInnovazione;
	}

	public void setIdInnovazione(BigDecimal idInnovazione)
	{
		this.idInnovazione = idInnovazione;
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
		result = prime * result + ((idInnovazione == null) ? 0 : idInnovazione.hashCode());
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
		PLFRImpresaInnovazioneEntityKey other = (PLFRImpresaInnovazioneEntityKey) obj;
		if (idImpresa == null)
		{
			if (other.idImpresa != null)
				return false;
		}
		else if (!idImpresa.equals(other.idImpresa))
			return false;
		if (idInnovazione == null)
		{
			if (other.idInnovazione != null)
				return false;
		}
		else if (!idInnovazione.equals(other.idInnovazione))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PLFRImpresaInnovazioneEntityKey [idInnovazione=" + idInnovazione + ", idImpresa=" + idImpresa + "]";
	}

}
