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
 * The persistent class for the PLF_NEWS_IMPRESA database table.
 * 
 */
@Entity
@Table(name = "PLF_NEWS_IMPRESA")
@NamedQuery(name = "PLFNewsImpresaEntity.findAll", query = "SELECT p FROM PLFNewsImpresaEntity p")
public class PLFNewsImpresaEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_NEWS_IMPRESA")
	private BigDecimal idNewsImpresa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "LINK_APPROFONDIMENTO")
	private String linkApprofondimento;

	@Column(name = "EVIDENZA_PORTALE")
	private boolean evidenzaPortale;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	/*@Column(name = "DATA_TESTO")
	private String dataTesto;

	@Column(name = "DESCRIZIONE")
	private String descrizione;*/

	// bi-directional many-to-one association to PLFImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	// bi-directional many-to-one association to PLFProgettiProdotti
	@ManyToOne
	@JoinColumn(name = "ID_PLF_PROGETTI_PRODOTTI")
	private PLFProgettiProdottiEntity plfProgettiProdotti;

	// bi-directional many-to-one association to PLFProgettiProdotti
	@ManyToOne
	@JoinColumn(name = "ID_SERVIZI_IMPRESA")
	private PLFServiziEntity plfServizi;

	// bi-directional many-to-one association to PLFTTipoNew
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_TIPO_NEWS")
	private PLFTTipoNewsEntity plfTTipoNew;
	
	@Transient
	private String radioNewsInerente;

	@OneToOne
	@JoinColumn(name="ID_NEWS_IMPRESA")
	private PLFNewsImpresaTranslationEntity newsImpresaTranslation;
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;
	
	

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;

	public PLFNewsImpresaEntity()
	{
	}

	public BigDecimal getIdNewsImpresa()
	{
		return this.idNewsImpresa;
	}

	public void setIdNewsImpresa(BigDecimal idNewsImpresa)
	{
		this.idNewsImpresa = idNewsImpresa;
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

	public PLFImpresaEntity getPlfImpresa()
	{
		return this.plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa)
	{
		this.plfImpresa = plfImpresa;
	}

	public PLFProgettiProdottiEntity getPlfProgettiProdotti()
	{
		return this.plfProgettiProdotti;
	}

	public void setPlfProgettiProdotti(PLFProgettiProdottiEntity plfProgettiProdotti)
	{
		this.plfProgettiProdotti = plfProgettiProdotti;
	}
	
	public PLFTTipoNewsEntity getPlfTTipoNew()
	{
		return this.plfTTipoNew;
	}

	public void setPlfTTipoNew(PLFTTipoNewsEntity plfTTipoNew)
	{
		this.plfTTipoNew = plfTTipoNew;
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

	public String getDescTag()
	{
		return newsImpresaTranslation == null ? null : newsImpresaTranslation.getDescTag();
	}

	public String getRadioNewsInerente()
	{
		return radioNewsInerente;
	}

	public void setRadioNewsInerente(String radioNewsInerente)
	{
		this.radioNewsInerente = radioNewsInerente;
	}

	public PLFNewsImpresaTranslationEntity getNewsImpresaTranslation() {
		return newsImpresaTranslation;
	}

	public void setNewsImpresaTranslation(PLFNewsImpresaTranslationEntity newsImpresaTranslation) {
		this.newsImpresaTranslation = newsImpresaTranslation;
	}

	public String getLinkApprofondimento() {
		return linkApprofondimento;
	}

	public void setLinkApprofondimento(String linkApprofondimento) {
		this.linkApprofondimento = linkApprofondimento;
	}

	public boolean getEvidenzaPortale() {
		return evidenzaPortale;
	}

	public void setEvidenzaPortale(boolean evidenzaPortale) {
		this.evidenzaPortale = evidenzaPortale;
	}

	public boolean isPubblicato() {
		return pubblicato;
	}

	public void setPubblicato(boolean pubblicato) {
		this.pubblicato = pubblicato;
	}

	public boolean isEvidenzaPortale() {
		return evidenzaPortale;
	}

	public PLFServiziEntity getPlfServizi() {
		return plfServizi;
	}

	public void setPlfServizi(PLFServiziEntity plfServizi) {
		this.plfServizi = plfServizi;
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

	public Date getDataCancellazione()
	{
		return dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
	}
	
	public boolean isScaduto()
	{
		return (dataFine != null && dataFine.before(new Date()));
	}
	
}