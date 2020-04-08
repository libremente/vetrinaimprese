package it.interlogic.vimp.data.jpa.model;

import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PLF_PROGETTI_PRODOTTI database table.
 * 
 */
@Entity
@Table(name = "PLF_PROGETTI_PRODOTTI")
@NamedQuery(name = "PLFProgettiProdottiEntity.findAll", query = "SELECT p FROM PLFProgettiProdottiEntity p")
public class PLFProgettiProdottiEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PLF_PROGETTI_PRODOTTI")
	private BigDecimal idPlfProgettiProdotti;

	@Column(name = "COLLABORAZIONI_PARTNERS")
	private String collaborazioniPartners;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_SCADENZA")
	private Date dataScadenza;

//	@Column(name = "DESC_CONTATTI")
//	private String descContatti;

//	@Column(name = "DESC_FINALITA")
//	private String descFinalita;

//	@Column(name = "DESC_FONTE")
//	private String descFonte;

//	@Column(name = "DESC_PROGETTO")
//	private String descProgetto;

//	@Column(name = "DESC_REQUISITI")
//	private String descRequisiti;

//	@Column(name = "DESC_RISULTATI")
//	private String descRisultati;

	@Column(name = "DESC_SITO")
	private String descSito;

//	@Column(name = "DESC_TAG")
//	private String descTag;

//	@Column(name = "DESCRIZIONE")
//	private String descrizione;

//	@Column(name = "NOME_PROGETTO_PRODOTTO")
//	private String nomeProgettoProdotto;

	@Column(name = "NUM_DURATA")
	private BigDecimal numDurata;

	@Column(name = "NUM_VALORE_ECONOMICO")
	private BigDecimal numValoreEconomico;

//	@Column(name = "PARTNER_ALTRO")
//	private String partnerAltro;

	@Column(name = "PROGETTO_ORIGINE")
	private String progettoOrigine;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	@Transient
	private List<PLFCollaborazioniEntity> plfCollaborazionis;
	
	@Transient
	private List<PLFProgettiProdottiAllegatiEntity> allegati;

	// bi-directional many-to-one association to PLFNewsImpresa
	@OneToMany(mappedBy = "plfProgettiProdotti")
	private List<PLFNewsImpresaEntity> plfNewsImpresas;

	// bi-directional many-to-one association to PLFImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	// bi-directional many-to-one association to PLFTSettoreProgetti
	/*@ManyToOne
	@JoinColumn(name = "ID_PLF_T_SETTORE_PROGETTI")
	private PLFTSettoreProgettiEntity plfTSettoreProgetti;*/

	// bi-directional many-to-one association to PLFTStatoProgetto
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_STATO_PROGETTO")
	private PLFTStatoProgettoEntity plfTStatoProgetto;

	// bi-directional many-to-one association to PLFTTipologiaProgetto
	@ManyToOne
	@JoinColumn(name = "ID_TIPOLOGIA_PROGETTO")
	private PLFTTipologiaProgettoEntity plfTTipologiaProgetto;

	// bi-directional many-to-one association to PLFTTipoImpresa
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_IMPRESA")
	private PLFTTipoImpresaEntity plfTTipoImpresa;

	// bi-directional many-to-one association to PLFTTipoProgettiProdotti
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PROGETTI_PRODOTTI")
	private PLFTTipoProgettiProdottiEntity plfTTipoProgettiProdotti;

	@OneToOne
	@JoinColumn(name="ID_PLF_PROGETTI_PRODOTTI")
	private PLFProgettiProdottiTranslationEntity progettiProdottiTranslation;

	@Transient
	private List<PLFTSettoreProgettiProdottiEntity> settoriProgettiProdotti;

	@Transient
	private List<PLFTSettoreTecnologieEntity> settoriTecnologie;

	@Transient
	private List<BigDecimal> elencoSettoreProgettiProdotti;

	@Transient
	private List<BigDecimal> elencoSettoreTecnologie;

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;

	public PLFProgettiProdottiEntity()
	{
	}

	public BigDecimal getIdPlfProgettiProdotti()
	{
		return this.idPlfProgettiProdotti;
	}

	public void setIdPlfProgettiProdotti(BigDecimal idPlfProgettiProdotti)
	{
		this.idPlfProgettiProdotti = idPlfProgettiProdotti;
	}

	public String getCollaborazioniPartners()
	{
		return this.collaborazioniPartners;
	}

	public void setCollaborazioniPartners(String collaborazioniPartners)
	{
		this.collaborazioniPartners = collaborazioniPartners;
	}

	public Date getDataAggiornamento()
	{
		return this.dataAggiornamento;
	}

	public void setDataAggiornamento(Date dataAggiornamento)
	{
		this.dataAggiornamento = dataAggiornamento;
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

	public String getDescSito()
	{
		return this.descSito;
	}

	public void setDescSito(String descSito)
	{
		this.descSito = descSito;
	}

	@Override
	public String getDescTag()
	{
		return progettiProdottiTranslation == null ? null : progettiProdottiTranslation.getDescTag();
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e simili.
	 * */
	public String getNomeProgettoProdotto()
	{
		return progettiProdottiTranslation == null ? null : progettiProdottiTranslation.getNomeProgettoProdotto();
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e simili.
	 * */
	public void setNomeProgettoProdotto(String nomeProgettoProdotto)
	{

	}

	public BigDecimal getNumDurata()
	{
		return this.numDurata;
	}

	public void setNumDurata(BigDecimal numDurata)
	{
		this.numDurata = numDurata;
	}

	public BigDecimal getNumValoreEconomico()
	{
		return this.numValoreEconomico;
	}

	public void setNumValoreEconomico(BigDecimal numValoreEconomico)
	{
		this.numValoreEconomico = numValoreEconomico;
	}

//	public String getPartnerAltro()
//	{
//		return this.partnerAltro;
//	}
//
//	public void setPartnerAltro(String partnerAltro)
//	{
//		this.partnerAltro = partnerAltro;
//	}

	public String getProgettoOrigine()
	{
		return this.progettoOrigine;
	}

	public void setProgettoOrigine(String progettoOrigine)
	{
		this.progettoOrigine = progettoOrigine;
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
		plfCollaborazioni.setPlfProgettiProdotti(this);

		return plfCollaborazioni;
	}

	public PLFCollaborazioniEntity removePlfCollaborazioni(PLFCollaborazioniEntity plfCollaborazioni)
	{
		getPlfCollaborazionis().remove(plfCollaborazioni);
		plfCollaborazioni.setPlfProgettiProdotti(null);

		return plfCollaborazioni;
	}

	public List<PLFNewsImpresaEntity> getPlfNewsImpresas()
	{
		return this.plfNewsImpresas;
	}

	public void setPlfNewsImpresas(List<PLFNewsImpresaEntity> plfNewsImpresas)
	{
		this.plfNewsImpresas = plfNewsImpresas;
	}

	public PLFNewsImpresaEntity addPlfNewsImpresa(PLFNewsImpresaEntity plfNewsImpresa)
	{
		getPlfNewsImpresas().add(plfNewsImpresa);
		plfNewsImpresa.setPlfProgettiProdotti(this);

		return plfNewsImpresa;
	}

	public PLFNewsImpresaEntity removePlfNewsImpresa(PLFNewsImpresaEntity plfNewsImpresa)
	{
		getPlfNewsImpresas().remove(plfNewsImpresa);
		plfNewsImpresa.setPlfProgettiProdotti(null);

		return plfNewsImpresa;
	}

	public PLFImpresaEntity getPlfImpresa()
	{
		return this.plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa)
	{
		this.plfImpresa = plfImpresa;
	}

	/*public PLFTSettoreProgettiEntity getPlfTSettoreProgetti()
	{
		return this.plfTSettoreProgetti;
	}

	public void setPlfTSettoreProgetti(PLFTSettoreProgettiEntity plfTSettoreProgetti)
	{
		this.plfTSettoreProgetti = plfTSettoreProgetti;
	}*/

	public PLFTStatoProgettoEntity getPlfTStatoProgetto()
	{
		return this.plfTStatoProgetto;
	}

	public void setPlfTStatoProgetto(PLFTStatoProgettoEntity plfTStatoProgetto)
	{
		this.plfTStatoProgetto = plfTStatoProgetto;
	}

	public PLFTTipologiaProgettoEntity getPlfTTipologiaProgetto()
	{
		return this.plfTTipologiaProgetto;
	}

	public void setPlfTTipologiaProgetto(PLFTTipologiaProgettoEntity plfTTipologiaProgetto)
	{
		this.plfTTipologiaProgetto = plfTTipologiaProgetto;
	}

	public PLFTTipoImpresaEntity getPlfTTipoImpresa()
	{
		return this.plfTTipoImpresa;
	}

	public void setPlfTTipoImpresa(PLFTTipoImpresaEntity plfTTipoImpresa)
	{
		this.plfTTipoImpresa = plfTTipoImpresa;
	}

	public PLFTTipoProgettiProdottiEntity getPlfTTipoProgettiProdotti()
	{
		return this.plfTTipoProgettiProdotti;
	}

	public void setPlfTTipoProgettiProdotti(PLFTTipoProgettiProdottiEntity plfTTipoProgettiProdotti)
	{
		this.plfTTipoProgettiProdotti = plfTTipoProgettiProdotti;
	}

	public PLFProgettiProdottiTranslationEntity getProgettiProdottiTranslation()
	{
		return progettiProdottiTranslation;
	}

	public void setProgettiProdottiTranslation(PLFProgettiProdottiTranslationEntity progettiProdottiTranslation)
	{
		this.progettiProdottiTranslation = progettiProdottiTranslation;
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
	
	public List<PLFProgettiProdottiAllegatiEntity> getAllegati()
	{
		return allegati;
	}

	public void setAllegati(List<PLFProgettiProdottiAllegatiEntity> allegati)
	{
		this.allegati = allegati;
	}

	public BigDecimal getIdPlfProgettoProdotto()
	{
		return this.idPlfProgettiProdotti;
	}
	
	public void setIdPlfProgettoProdotto(BigDecimal idPlfProgettoProdotto)
	{
		this.idPlfProgettiProdotti = idPlfProgettoProdotto;
	}

	public List<BigDecimal> getElencoSettoreProgettiProdotti() {
		return elencoSettoreProgettiProdotti;
	}

	public void setElencoSettoreProgettiProdotti(List<BigDecimal> elencoSettoreProgettiProdotti) {
		this.elencoSettoreProgettiProdotti = elencoSettoreProgettiProdotti;
	}

	public List<BigDecimal> getElencoSettoreTecnologie() {
		return elencoSettoreTecnologie;
	}

	public void setElencoSettoreTecnologie(List<BigDecimal> elencoSettoreTecnologie) {
		this.elencoSettoreTecnologie = elencoSettoreTecnologie;
	}

	public List<PLFTSettoreProgettiProdottiEntity> getSettoriProgettiProdotti() {
		return settoriProgettiProdotti;
	}

	public void setSettoriProgettiProdotti(List<PLFTSettoreProgettiProdottiEntity> settoriProgettiProdotti) {
		this.settoriProgettiProdotti = settoriProgettiProdotti;
	}

	public List<PLFTSettoreTecnologieEntity> getSettoriTecnologie() {
		return settoriTecnologie;
	}

	public void setSettoriTecnologie(List<PLFTSettoreTecnologieEntity> settoriTecnologie) {
		this.settoriTecnologie = settoriTecnologie;
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
}