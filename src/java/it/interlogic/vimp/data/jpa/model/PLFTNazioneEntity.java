package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PLF_T_NAZIONE database table.
 * 
 */
@Entity
@Table(name = "PLF_T_NAZIONE")
@NamedQuery(name = "PLFTNazioneEntity.findAll", query = "SELECT p FROM PLFTNazioneEntity p")
public class PLFTNazioneEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PLF_T_NAZIONE")
	private BigDecimal idPlfTNazione;

	@Column(name = "COD_NAZIONE")
	private String codNazione;

	@Column(name = "CODICE_ISTAT")
	private String codiceIstat;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	// bi-directional many-to-one association to PLFCollaborazioni
	@OneToMany(mappedBy = "plfTNazione")
	private List<PLFCollaborazioniEntity> plfCollaborazionis;

	public PLFTNazioneEntity()
	{
	}

	public BigDecimal getIdPlfTNazione()
	{
		return this.idPlfTNazione;
	}

	public void setIdPlfTNazione(BigDecimal idPlfTNazione)
	{
		this.idPlfTNazione = idPlfTNazione;
	}

	public String getCodNazione()
	{
		return this.codNazione;
	}

	public void setCodNazione(String codNazione)
	{
		this.codNazione = codNazione;
	}

	public String getCodiceIstat()
	{
		return this.codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat)
	{
		this.codiceIstat = codiceIstat;
	}

	public Date getDataFine()
	{
		return this.dataFine;
	}

	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
	}

	public Date getDataInizio()
	{
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public String getDescrizione()
	{
		return this.descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public List<PLFCollaborazioniEntity> getPlfCollaborazionis()
	{
		return this.plfCollaborazionis;
	}

	public void setPlfCollaborazionis(List<PLFCollaborazioniEntity> plfCollaborazionis)
	{
		this.plfCollaborazionis = plfCollaborazionis;
	}

	public PLFCollaborazioniEntity addPlfCollaborazioni(PLFCollaborazioniEntity plfCollaborazioni)
	{
		getPlfCollaborazionis().add(plfCollaborazioni);
		plfCollaborazioni.setPlfTNazione(this);

		return plfCollaborazioni;
	}

	public PLFCollaborazioniEntity removePlfCollaborazioni(PLFCollaborazioniEntity plfCollaborazioni)
	{
		getPlfCollaborazionis().remove(plfCollaborazioni);
		plfCollaborazioni.setPlfTNazione(null);

		return plfCollaborazioni;
	}

}