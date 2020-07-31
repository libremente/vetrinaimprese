package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the PLF_OPPORTUNITA database table.
 * 
 */
@Entity
@Table(name = "PLF_OPPORTUNITA")
@NamedQuery(name = "PLFOpportunitaEntity.findAll", query = "SELECT p FROM PLFOpportunitaEntity p")
public class PLFOpportunitaEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PLF_OPPORTUNITA")
	private BigDecimal idPlfOpportunita;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_APERTURA")
	private Date dataApertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_SCADENZA")
	private Date dataScadenza;

//	@Column(name = "DESC_CONTATTI")
//	private String descContatti;

//	@Column(name = "DESC_FONTE")
//	private String descFonte;

	@Column(name = "DESC_LINK")
	private String descLink;

/*	@Column(name = "DESC_NOME")
	private String descNome;

	@Column(name = "DESC_PERIODO")
	private String descPeriodo;

	@Column(name = "DESC_REQUISITI")
	private String descRequisiti;

	@Column(name = "DESC_TAG")
	private String descTag;*/

	@Column(name = "NUM_VAL_ECONOMICO")
	private BigDecimal numValEconomico;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	// bi-directional many-to-one association to PLFTSettoreProgetti
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_SETTORE_PROGETTI")
	private PLFTSettoreProgettiEntity plfTSettoreProgetti;

	// bi-directional many-to-one association to PLFTSoggAmmissibili
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_SOGG_AMMISSIBILI")
	private PLFTSoggAmmissibiliEntity plfTSoggAmmissibili;

	// bi-directional many-to-one association to PLFTTipoProgramma
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_TIPO_PROGRAMMA")
	private PLFTTipoProgrammaEntity plfTTipoProgramma;
	
	@OneToOne
	@JoinColumn(name = "ID_PLF_OPPORTUNITA")
	private PLFOpportunitaTranslationEntity opportunitaTranslation;

	@Transient
	private List<PLFTStatoImpresaEntity> statoImpresas;

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;

	public PLFOpportunitaEntity()
	{
	}

	public BigDecimal getIdPlfOpportunita()
	{
		return this.idPlfOpportunita;
	}

	public void setIdPlfOpportunita(BigDecimal idPlfOpportunita)
	{
		this.idPlfOpportunita = idPlfOpportunita;
	}

	public Date getDataAggiornamento()
	{
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Date dataAggiornamento)
	{
		this.dataAggiornamento = dataAggiornamento;
	}

	public Date getDataApertura()
	{
		return this.dataApertura;
	}

	public void setDataApertura(Date dataApertura)
	{
		this.dataApertura = dataApertura;
	}

	public Date getDataCancellazione()
	{
		return this.dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
	}

	public Date getDataScadenza()
	{
		return this.dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza)
	{
		this.dataScadenza = dataScadenza;
	}

//	public String getDescContatti()
//	{
//		return this.descContatti;
//	}
//
//	public void setDescContatti(String descContatti)
//	{
//		this.descContatti = descContatti;
//	}
	
	public String getDescLink()
	{
		return this.descLink;
	}

	public void setDescLink(String descLink)
	{
		this.descLink = descLink;
	}

	@Override
	public String getDescTag()
	{
		return opportunitaTranslation == null ? null : opportunitaTranslation.getDescTag();
	}

	public BigDecimal getNumValEconomico()
	{
		return this.numValEconomico;
	}

	public void setNumValEconomico(BigDecimal numValEconomico)
	{
		this.numValEconomico = numValEconomico;
	}

	public PLFTSettoreProgettiEntity getPlfTSettoreProgetti()
	{
		return this.plfTSettoreProgetti;
	}

	public void setPlfTSettoreProgetti(PLFTSettoreProgettiEntity plfTSettoreProgetti)
	{
		this.plfTSettoreProgetti = plfTSettoreProgetti;
	}

	public PLFTSoggAmmissibiliEntity getPlfTSoggAmmissibili()
	{
		return this.plfTSoggAmmissibili;
	}

	public void setPlfTSoggAmmissibili(PLFTSoggAmmissibiliEntity plfTSoggAmmissibili)
	{
		this.plfTSoggAmmissibili = plfTSoggAmmissibili;
	}

	public PLFTTipoProgrammaEntity getPlfTTipoProgramma()
	{
		return this.plfTTipoProgramma;
	}

	public void setPlfTTipoProgramma(PLFTTipoProgrammaEntity plfTTipoProgramma)
	{
		this.plfTTipoProgramma = plfTTipoProgramma;
	}

	public PLFOpportunitaTranslationEntity getOpportunitaTranslation()
	{
		return opportunitaTranslation;
	}

	public void setOpportunitaTranslation(PLFOpportunitaTranslationEntity opportunitaTranslation)
	{
		this.opportunitaTranslation = opportunitaTranslation;
	}

	public List<PLFTStatoImpresaEntity> getStatoImpresas()
	{
		return statoImpresas;
	}

	public void setStatoImpresas(List<PLFTStatoImpresaEntity> statoImpresas)
	{
		this.statoImpresas = statoImpresas;
	}

	@Transient
	private String imageData;

	public String getImageData()
	{
		return imageData;
	}

	public void setImageData(String imageData)
	{
		this.imageData = imageData;
	}

	public boolean isPubblicato() {
		return pubblicato;
	}

	public void setPubblicato(boolean pubblicato) {
		this.pubblicato = pubblicato;
	}

	public List<PLFTTagEntity> getTags() {
		return tags;
	}

	public void setTags(List<PLFTTagEntity> tags) {
		this.tags = tags;
	}

	public List<BigDecimal> getElencoIdTags() {
		return elencoIdTags;
	}

	public void setElencoIdTags(List<BigDecimal> elencoIdTags) {
		this.elencoIdTags = elencoIdTags;
	}
	
	public boolean isScaduto()
	{
		return (dataScadenza != null && dataScadenza.before(new Date()));
	}
	
}