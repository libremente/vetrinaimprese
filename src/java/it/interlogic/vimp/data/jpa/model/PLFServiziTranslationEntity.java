package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_SERVIZI_STANDARD translated fields.
 *
 */
@Entity
@Table(name = "PLF_SERVIZI_TRANSLATION")
public class PLFServiziTranslationEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_SERVIZI")
	private BigDecimal idServizi;

	@Column(name = "TITOLO")
	private String titolo;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Column(name = "DESC_ORARI")
	private String orari;

	@Column(name = "DESC_TAG")
	private String descTag;

	@Column(name = "DESC_RIFERIMENTI")
	private String riferimenti;

	public BigDecimal getIdServizi()
	{
		return idServizi;
	}

	public void setIdServizi(BigDecimal idServizi)
	{
		this.idServizi = idServizi;
	}

	public String getTitolo()
	{
		return titolo;
	}

	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public String getOrari()
	{
		return orari;
	}

	public void setOrari(String orari)
	{
		this.orari = orari;
	}

	@Override
	public String getDescTag()
	{
		return descTag;
	}

	public void setDescTag(String descTag)
	{
		this.descTag = descTag;
	}

	public String getRiferimenti() {
		return riferimenti;
	}

	public void setRiferimenti(String riferimenti) {
		this.riferimenti = riferimenti;
	}
}
