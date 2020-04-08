package it.interlogic.vimp.data.jpa.model;

import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the PLF_IMPRESA database table.
 * 
 */
@Entity
@Table(name = "PLF_IMPRESA")
@NamedQuery(name = "PLFImpresaEntity.findAll", query = "SELECT p FROM PLFImpresaEntity p")
public class PLFImpresaEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PLF_IMPRESA")
	private BigDecimal idPlfImpresa;

	// @Column(name = "BREVETTO")
	// private String brevetto;

	@Column(name = "COD_CAP")
	private String codCap;

	@Column(name = "COD_FISCALE")
	private String codFiscale;

	@Column(name = "CODICE_FISCALE_LEGALE_RAPPRESENTANTE")
	private String codiceFiscaleLegaleRappresentante;

	@Column(name = "COGNOME_CONTATTO")
	private String cognomeContatto;

	@Column(name = "COGNOME_LEGALE_RAPPRESENTANTE")
	private String cognomeLegaleRappresentante;

	@Column(name = "NUM_ADDETTI_SUBORDINATI")
	private BigDecimal numAddettiSubordinati;

	@Column(name = "NUM_ULTIMO_FATTURATO")
	private BigDecimal numUltimoFatturato;

	@Column(name = "COORD_X")
	private BigDecimal coordX;

	@Column(name = "COORD_Y")
	private BigDecimal coordY;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ACCREDITAMENTO")
	private Date dataAccreditamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO_EFFETTIVA_ATTIVITA")
	private Date dataInizioEffettivaAttivita;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ISCRIZIONE_REGISTRO_IMPRESE")
	private Date dataIscrizioneRegistroImprese;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ISCRIZIONE_SEZIONE_SPECIALE")
	private Date dataIscrizioneSezioneSpeciale;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PASSAGGIO_PMI_CCIAA")
	private Date dataPassaggioPmiCciaa;

	@Column(name = "INCUBATORE_CERTIFICATO")
	private BigDecimal incubatoreCertificato;

	// @Deprecated
	// @Column(name = "DESC_ATTIVITA")
	// private String descAttivita;
	//
	// @Deprecated
	// @Column(name = "DESC_BREVE_IMPRESA")
	// private String descBreveImpresa;

	// @Deprecated
	// @Column(name = "DESC_FONTE")
	// private String descFonte;

	// @Deprecated
	// @Column(name = "DESC_IMPRESA")
	// private String descImpresa;

	@Column(name = "DESC_INDIRIZZO")
	private String descIndirizzo;

	// @Deprecated
	// @Column(name = "DESC_MISSIONE")
	// private String descMissione;

	@Column(name = "DESC_SITO")
	private String descSito;

	// @Deprecated
	// @Column(name = "DESC_TAG")
	// private String descTag;

	@Column(name = "DESC_TELEFONO")
	private String descTelefono;

	// @Deprecated
	// @Column(name = "ELEMENTI_INNOVAZIONE_ALTRO")
	// private String elementiInnovazioneAltro;

	@Column(name = "EMAIL_CONTATTO")
	private String emailContatto;

	@Column(name = "FACEBOOK")
	private String facebook;

	@Column(name = "FLICKR")
	private String flickr;

	@Column(name = "INTRAGRAM")
	private String intragram;

	@Column(name = "LINK_REGISTRO_SPECIALE_CAMERA_COMMERCIO")
	private String linkRegistroSpecialeCameraCommercio;

	@Column(name = "LINKEDIN")
	private String linkedin;

	// @Deprecated
	// @Column(name = "MERCATI_ALTRO")
	// private String mercatiAltro;

	@Column(name = "NOME_CONTATTO")
	private String nomeContatto;

	@Column(name = "NOME_LEGALE_RAPPRESENTANTE")
	private String nomeLegaleRappresentante;

	@Column(name = "NUM_ANNO")
	private BigDecimal numAnno;

	@Column(name = "NUMERO_CIVICO")
	private String numeroCivico;

	@Transient
	private String numeroCivicoTopo;
	@Transient
	private String indirizzoTopo;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	@Column(name = "PRIMO_REQUISITO_PMI")
	private String primoRequisitoPmi;

	// @Deprecated
	// @Column(name = "RUOLO_CONTATTO_ALTRO")
	// private String ruoloContattoAltro;

	@Column(name = "SECONDO_REQUISITO_PMI")
	private String secondoRequisitoPmi;

	@Column(name = "TERZO_REQUISITO_PMI")
	private String terzoRequisitoPmi;

	@Column(name = "TWITTER")
	private String twitter;

	@Column(name = "VISIBILITA_VETRINA")
	private String visibilitaVetrina;

	@Column(name = "YOUTUBE")
	private String youtube;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	@Column(name = "SEZIONE_CENSIMENTO_2011")
	private BigDecimal sezioneCensimento2011;
	@Column(name = "SEZIONE_ELETTORALE")
	private BigDecimal sezioneElettorale;
	@Column(name = "CODICE_MUNICIPIO")
	private String codiceMunicipio;
	@Column(name = "NOME_MUNICIPIO")
	private String nomeMunicipio;
	@Column(name = "CODICE_CIRCOSCRIZIONE")
	private String codiceCircoscrizione;
	@Column(name = "NOME_CIRCOSCRIZIONE")
	private String nomeCircoscrizione;
	@Column(name = "CODICE_UNITA_URBANISTICA")
	private String codiceUnitaUrbanistica;
	@Column(name = "NOME_UNITA_URBANISTICA")
	private String nomeUnitaUrbanistica;

	// bi-directional many-to-one association to PLFCollaborazioni
	@OneToMany(mappedBy = "plfImpresa")
	private List<PLFCollaborazioniEntity> plfCollaborazionis;

	// bi-directional many-to-one association to PLFTPrevalenza
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_PREVALENZA_FEMMINILE")
	private PLFTPrevalenzaEntity plfTPrevalenzaFemminile;

	// bi-directional many-to-one association to PLFTPrevalenza
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_PREVALENZA_GIOVANILE")
	private PLFTPrevalenzaEntity plfTPrevalenzaGiovanile;

	// bi-directional many-to-one association to PLFTPrevalenza
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_PREVALENZA_STRANIERA")
	private PLFTPrevalenzaEntity plfTPrevalenzaStraniera;

	// bi-directional many-to-one association to PLFTAteco
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_ATECO")
	private PLFTAtecoEntity plfTAteco;

	// bi-directional many-to-one association to PLFTClasseAddetti
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_CLASSE_ADDETTI")
	private PLFTClasseAddettiEntity plfTClasseAddetti;

	// bi-directional many-to-one association to PLFTClasseCapitale
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_CLASSE_CAPITALE")
	private PLFTClasseCapitaleEntity plfTClasseCapitale;

	// bi-directional many-to-one association to PLFTClasseProduzione
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_CLASSE_PRODUZIONE")
	private PLFTClasseProduzioneEntity plfTClasseProduzione;

	// bi-directional many-to-one association to PLFTComune
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_COMUNE")
	private PLFTComuneEntity plfTComune;

	// bi-directional many-to-one association to PLFTNaturaGiuridica
	@ManyToOne
	@JoinColumn(name = "ID_NATURA_GIURIDICA")
	private PLFTNaturaGiuridicaEntity plfTNaturaGiuridica;

	// bi-directional many-to-one association to PLFTOrigineImpresa
	@ManyToOne
	@JoinColumn(name = "ID_ORIGINE_IMPRESA")
	private PLFTOrigineImpresaEntity plfTOrigineImpresa;

	// bi-directional many-to-one association to PLFTRuoloAziendale
	@ManyToOne
	@JoinColumn(name = "ID_RUOLO_AZIENDALE")
	private PLFTRuoloAziendaleEntity plfTRuoloAziendale;

	// bi-directional many-to-one association to PLFTSettoreImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_SETTORE_IMPRESA")
	private PLFTSettoreImpresaEntity plfTSettoreImpresa;

	// bi-directional many-to-one association to PLFTSezioneSpecialeImpresa
	@ManyToOne
	@JoinColumn(name = "ID_SEZIONE_SPECIALE_IMPRESA")
	private PLFTSezioneSpecialeImpresaEntity plfTSezioneSpecialeImpresa;

	// bi-directional many-to-one association to PLFTStatoImpresa
	@ManyToOne
	@JoinColumn(name = "ID_STATO_IMPRESA")
	private PLFTStatoImpresaEntity plfTStatoImpresa;

	// bi-directional many-to-one association to PLFTTipoImpresa
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_IMPRESA")
	private PLFTTipoImpresaEntity plfTTipoImpresa;

	// bi-directional many-to-one association to PLFLog
	@OneToMany(mappedBy = "plfImpresa")
	private List<PLFLogEntity> plfLogs;

	@Transient
	private List<PLFNewsImpresaEntity> plfNewsImpresas;

	@Transient
	private List<PLFProgettiProdottiEntity> plfProgettiProdottis;

	// bi-directional many-to-one association to PLFRichiestaAccreditamento
	@OneToMany(mappedBy = "plfImpresa")
	private List<PLFRichiestaAccreditamentoEntity> plfRichiestaAccreditamentos;

	@Transient
	private List<PLFTInnovazioneEntity> plfTInnovaziones;

	@Transient
	private List<PLFTMercatiEntity> plfTMercatis;

	@Transient
	private List<PLFImpresaEntity> stakeholders;

	@Transient
	private List<PLFImpresaEntity> impreseCollegate;

	@Transient
	private List<PLFServiziEntity> serviziStandard;

	@Transient
	private String linkServizioStandard;

	@Transient
	private List<PLFImpresaAllegatiEntity> allegati;

	@Transient
	private int tipoInformazione;

	@Transient
	private String codProvincia;

	@Transient
	private String provincia;

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;

	@OneToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaTranslationEntity impresaTranslation;

	public PLFImpresaEntity()
	{
	}

	public BigDecimal getIdPlfImpresa()
	{
		return this.idPlfImpresa;
	}

	public void setIdPlfImpresa(BigDecimal idPlfImpresa)
	{
		this.idPlfImpresa = idPlfImpresa;
	}

	public String getCodCap()
	{
		return this.codCap;
	}

	public void setCodCap(String codCap)
	{
		this.codCap = codCap;
	}

	public String getCodFiscale()
	{
		return this.codFiscale;
	}

	public void setCodFiscale(String codFiscale)
	{
		this.codFiscale = codFiscale;
	}

	public String getCodiceFiscaleLegaleRappresentante()
	{
		return this.codiceFiscaleLegaleRappresentante;
	}

	public void setCodiceFiscaleLegaleRappresentante(String codiceFiscaleLegaleRappresentante)
	{
		this.codiceFiscaleLegaleRappresentante = codiceFiscaleLegaleRappresentante;
	}

	public String getCognomeContatto()
	{
		return this.cognomeContatto;
	}

	public void setCognomeContatto(String cognomeContatto)
	{
		this.cognomeContatto = cognomeContatto;
	}

	public String getCognomeLegaleRappresentante()
	{
		return this.cognomeLegaleRappresentante;
	}

	public void setCognomeLegaleRappresentante(String cognomeLegaleRappresentante)
	{
		this.cognomeLegaleRappresentante = cognomeLegaleRappresentante;
	}

	public BigDecimal getCoordX()
	{
		return this.coordX;
	}

	public void setCoordX(BigDecimal coordX)
	{
		this.coordX = coordX;
	}

	public BigDecimal getCoordY()
	{
		return this.coordY;
	}

	public void setCoordY(BigDecimal coordY)
	{
		this.coordY = coordY;
	}

	public Date getDataAccreditamento()
	{
		return this.dataAccreditamento;
	}

	public void setDataAccreditamento(Date dataAccreditamento)
	{
		this.dataAccreditamento = dataAccreditamento;
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

	public Date getDataInizioEffettivaAttivita()
	{
		return this.dataInizioEffettivaAttivita;
	}

	public void setDataInizioEffettivaAttivita(Date dataInizioEffettivaAttivita)
	{
		this.dataInizioEffettivaAttivita = dataInizioEffettivaAttivita;
	}

	public Date getDataIscrizioneRegistroImprese()
	{
		return this.dataIscrizioneRegistroImprese;
	}

	public void setDataIscrizioneRegistroImprese(Date dataIscrizioneRegistroImprese)
	{
		this.dataIscrizioneRegistroImprese = dataIscrizioneRegistroImprese;
	}

	public Date getDataIscrizioneSezioneSpeciale()
	{
		return this.dataIscrizioneSezioneSpeciale;
	}

	public void setDataIscrizioneSezioneSpeciale(Date dataIscrizioneSezioneSpeciale)
	{
		this.dataIscrizioneSezioneSpeciale = dataIscrizioneSezioneSpeciale;
	}

	public Date getDataPassaggioPmiCciaa()
	{
		return this.dataPassaggioPmiCciaa;
	}

	public void setDataPassaggioPmiCciaa(Date dataPassaggioPmiCciaa)
	{
		this.dataPassaggioPmiCciaa = dataPassaggioPmiCciaa;
	}

	public String getDescIndirizzo()
	{
		return this.descIndirizzo;
	}

	public void setDescIndirizzo(String descIndirizzo)
	{
		this.descIndirizzo = descIndirizzo;
	}

	public String getDescSito()
	{
		return this.descSito;
	}

	public void setDescSito(String descSito)
	{
		this.descSito = descSito;
	}

	public String getDescTag()
	{
		return impresaTranslation == null ? null : impresaTranslation.getDescTag();
	}

	public String getDescTelefono()
	{
		return this.descTelefono;
	}

	public void setDescTelefono(String descTelefono)
	{
		this.descTelefono = descTelefono;
	}

	public String getEmailContatto()
	{
		return this.emailContatto;
	}

	public void setEmailContatto(String emailContatto)
	{
		this.emailContatto = emailContatto;
	}

	public String getFacebook()
	{
		return this.facebook;
	}

	public void setFacebook(String facebook)
	{
		this.facebook = facebook;
	}

	public String getFlickr()
	{
		return this.flickr;
	}

	public void setFlickr(String flickr)
	{
		this.flickr = flickr;
	}

	public String getIntragram()
	{
		return this.intragram;
	}

	public void setIntragram(String intragram)
	{
		this.intragram = intragram;
	}

	public String getLinkRegistroSpecialeCameraCommercio()
	{
		return this.linkRegistroSpecialeCameraCommercio;
	}

	public void setLinkRegistroSpecialeCameraCommercio(String linkRegistroSpecialeCameraCommercio)
	{
		this.linkRegistroSpecialeCameraCommercio = linkRegistroSpecialeCameraCommercio;
	}

	public String getLinkedin()
	{
		return this.linkedin;
	}

	public void setLinkedin(String linkedin)
	{
		this.linkedin = linkedin;
	}

	public String getNomeContatto()
	{
		return this.nomeContatto;
	}

	public void setNomeContatto(String nomeContatto)
	{
		this.nomeContatto = nomeContatto;
	}

	public String getNomeLegaleRappresentante()
	{
		return this.nomeLegaleRappresentante;
	}

	public void setNomeLegaleRappresentante(String nomeLegaleRappresentante)
	{
		this.nomeLegaleRappresentante = nomeLegaleRappresentante;
	}

	public BigDecimal getNumAnno()
	{
		return this.numAnno;
	}

	public void setNumAnno(BigDecimal numAnno)
	{
		this.numAnno = numAnno;
	}

	public String getNumeroCivico()
	{
		return this.numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico)
	{
		this.numeroCivico = numeroCivico;
	}

	public String getPartitaIva()
	{
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public String getPrimoRequisitoPmi()
	{
		return this.primoRequisitoPmi;
	}

	public void setPrimoRequisitoPmi(String primoRequisitoPmi)
	{
		this.primoRequisitoPmi = primoRequisitoPmi;
	}

	public String getSecondoRequisitoPmi()
	{
		return this.secondoRequisitoPmi;
	}

	public void setSecondoRequisitoPmi(String secondoRequisitoPmi)
	{
		this.secondoRequisitoPmi = secondoRequisitoPmi;
	}

	public String getTerzoRequisitoPmi()
	{
		return this.terzoRequisitoPmi;
	}

	public void setTerzoRequisitoPmi(String terzoRequisitoPmi)
	{
		this.terzoRequisitoPmi = terzoRequisitoPmi;
	}

	public String getTwitter()
	{
		return this.twitter;
	}

	public void setTwitter(String twitter)
	{
		this.twitter = twitter;
	}

	public String getVisibilitaVetrina()
	{
		return this.visibilitaVetrina;
	}

	public void setVisibilitaVetrina(String visibilitaVetrina)
	{
		this.visibilitaVetrina = visibilitaVetrina;
	}

	public String getYoutube()
	{
		return this.youtube;
	}

	public void setYoutube(String youtube)
	{
		this.youtube = youtube;
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
		plfCollaborazioni.setPlfImpresa(this);

		return plfCollaborazioni;
	}

	public PLFCollaborazioniEntity removePlfCollaborazioni(PLFCollaborazioniEntity plfCollaborazioni)
	{
		getPlfCollaborazionis().remove(plfCollaborazioni);
		plfCollaborazioni.setPlfImpresa(null);

		return plfCollaborazioni;
	}

	public PLFTPrevalenzaEntity getPlfTPrevalenzaFemminile()
	{
		return this.plfTPrevalenzaFemminile;
	}

	public void setPlfTPrevalenzaFemminile(PLFTPrevalenzaEntity plfTPrevalenzaFemminile)
	{
		this.plfTPrevalenzaFemminile = plfTPrevalenzaFemminile;
	}

	public PLFTPrevalenzaEntity getPlfTPrevalenzaGiovanile()
	{
		return this.plfTPrevalenzaGiovanile;
	}

	public void setPlfTPrevalenzaGiovanile(PLFTPrevalenzaEntity plfTPrevalenzaGiovanile)
	{
		this.plfTPrevalenzaGiovanile = plfTPrevalenzaGiovanile;
	}

	public PLFTPrevalenzaEntity getPlfTPrevalenzaStraniera()
	{
		return this.plfTPrevalenzaStraniera;
	}

	public void setPlfTPrevalenzaStraniera(PLFTPrevalenzaEntity plfTPrevalenzaStraniera)
	{
		this.plfTPrevalenzaStraniera = plfTPrevalenzaStraniera;
	}

	public PLFTAtecoEntity getPlfTAteco()
	{
		return this.plfTAteco;
	}

	public void setPlfTAteco(PLFTAtecoEntity plfTAteco)
	{
		this.plfTAteco = plfTAteco;
	}

	public PLFTClasseAddettiEntity getPlfTClasseAddetti()
	{
		return this.plfTClasseAddetti;
	}

	public void setPlfTClasseAddetti(PLFTClasseAddettiEntity plfTClasseAddetti)
	{
		this.plfTClasseAddetti = plfTClasseAddetti;
	}

	public PLFTClasseCapitaleEntity getPlfTClasseCapitale()
	{
		return this.plfTClasseCapitale;
	}

	public void setPlfTClasseCapitale(PLFTClasseCapitaleEntity plfTClasseCapitale)
	{
		this.plfTClasseCapitale = plfTClasseCapitale;
	}

	public PLFTClasseProduzioneEntity getPlfTClasseProduzione()
	{
		return this.plfTClasseProduzione;
	}

	public void setPlfTClasseProduzione(PLFTClasseProduzioneEntity plfTClasseProduzione)
	{
		this.plfTClasseProduzione = plfTClasseProduzione;
	}

	public PLFTComuneEntity getPlfTComune()
	{
		return this.plfTComune;
	}

	public void setPlfTComune(PLFTComuneEntity plfTComune)
	{
		this.plfTComune = plfTComune;
	}

	public PLFTNaturaGiuridicaEntity getPlfTNaturaGiuridica()
	{
		return this.plfTNaturaGiuridica;
	}

	public void setPlfTNaturaGiuridica(PLFTNaturaGiuridicaEntity plfTNaturaGiuridica)
	{
		this.plfTNaturaGiuridica = plfTNaturaGiuridica;
	}

	public PLFTOrigineImpresaEntity getPlfTOrigineImpresa()
	{
		return this.plfTOrigineImpresa;
	}

	public void setPlfTOrigineImpresa(PLFTOrigineImpresaEntity plfTOrigineImpresa)
	{
		this.plfTOrigineImpresa = plfTOrigineImpresa;
	}

	public PLFTRuoloAziendaleEntity getPlfTRuoloAziendale()
	{
		return this.plfTRuoloAziendale;
	}

	public void setPlfTRuoloAziendale(PLFTRuoloAziendaleEntity plfTRuoloAziendale)
	{
		this.plfTRuoloAziendale = plfTRuoloAziendale;
	}

	public PLFTSettoreImpresaEntity getPlfTSettoreImpresa()
	{
		return this.plfTSettoreImpresa;
	}

	public void setPlfTSettoreImpresa(PLFTSettoreImpresaEntity plfTSettoreImpresa)
	{
		this.plfTSettoreImpresa = plfTSettoreImpresa;
	}

	public PLFTSezioneSpecialeImpresaEntity getPlfTSezioneSpecialeImpresa()
	{
		return this.plfTSezioneSpecialeImpresa;
	}

	public void setPlfTSezioneSpecialeImpresa(PLFTSezioneSpecialeImpresaEntity plfTSezioneSpecialeImpresa)
	{
		this.plfTSezioneSpecialeImpresa = plfTSezioneSpecialeImpresa;
	}

	public PLFTStatoImpresaEntity getPlfTStatoImpresa()
	{
		return this.plfTStatoImpresa;
	}

	public void setPlfTStatoImpresa(PLFTStatoImpresaEntity plfTStatoImpresa)
	{
		this.plfTStatoImpresa = plfTStatoImpresa;
	}

	public PLFTTipoImpresaEntity getPlfTTipoImpresa()
	{
		return this.plfTTipoImpresa;
	}

	public void setPlfTTipoImpresa(PLFTTipoImpresaEntity plfTTipoImpresa)
	{
		this.plfTTipoImpresa = plfTTipoImpresa;
	}

	public List<PLFLogEntity> getPlfLogs()
	{
		return this.plfLogs;
	}

	public void setPlfLogs(List<PLFLogEntity> plfLogs)
	{
		this.plfLogs = plfLogs;
	}

	public PLFLogEntity addPlfLog(PLFLogEntity plfLog)
	{
		getPlfLogs().add(plfLog);
		plfLog.setPlfImpresa(this);

		return plfLog;
	}

	public PLFLogEntity removePlfLog(PLFLogEntity plfLog)
	{
		getPlfLogs().remove(plfLog);
		plfLog.setPlfImpresa(null);

		return plfLog;
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
		plfNewsImpresa.setPlfImpresa(this);

		return plfNewsImpresa;
	}

	public PLFNewsImpresaEntity removePlfNewsImpresa(PLFNewsImpresaEntity plfNewsImpresa)
	{
		getPlfNewsImpresas().remove(plfNewsImpresa);
		plfNewsImpresa.setPlfImpresa(null);

		return plfNewsImpresa;
	}

	public List<PLFProgettiProdottiEntity> getPlfProgettiProdottis()
	{
		return this.plfProgettiProdottis;
	}

	public void setPlfProgettiProdottis(List<PLFProgettiProdottiEntity> plfProgettiProdottis)
	{
		this.plfProgettiProdottis = plfProgettiProdottis;
	}

	public PLFProgettiProdottiEntity addPlfProgettiProdotti(PLFProgettiProdottiEntity plfProgettiProdotti)
	{
		getPlfProgettiProdottis().add(plfProgettiProdotti);
		plfProgettiProdotti.setPlfImpresa(this);

		return plfProgettiProdotti;
	}

	public PLFProgettiProdottiEntity removePlfProgettiProdotti(PLFProgettiProdottiEntity plfProgettiProdotti)
	{
		getPlfProgettiProdottis().remove(plfProgettiProdotti);
		plfProgettiProdotti.setPlfImpresa(null);

		return plfProgettiProdotti;
	}

	public List<PLFRichiestaAccreditamentoEntity> getPlfRichiestaAccreditamentos()
	{
		return this.plfRichiestaAccreditamentos;
	}

	public void setPlfRichiestaAccreditamentos(List<PLFRichiestaAccreditamentoEntity> plfRichiestaAccreditamentos)
	{
		this.plfRichiestaAccreditamentos = plfRichiestaAccreditamentos;
	}

	public PLFRichiestaAccreditamentoEntity addPlfRichiestaAccreditamento(PLFRichiestaAccreditamentoEntity plfRichiestaAccreditamento)
	{
		getPlfRichiestaAccreditamentos().add(plfRichiestaAccreditamento);
		plfRichiestaAccreditamento.setPlfImpresa(this);

		return plfRichiestaAccreditamento;
	}

	public PLFRichiestaAccreditamentoEntity removePlfRichiestaAccreditamento(PLFRichiestaAccreditamentoEntity plfRichiestaAccreditamento)
	{
		getPlfRichiestaAccreditamentos().remove(plfRichiestaAccreditamento);
		plfRichiestaAccreditamento.setPlfImpresa(null);

		return plfRichiestaAccreditamento;
	}

	public List<PLFTInnovazioneEntity> getPlfTInnovaziones()
	{
		return this.plfTInnovaziones;
	}

	public void setPlfTInnovaziones(List<PLFTInnovazioneEntity> plfTInnovaziones)
	{
		this.plfTInnovaziones = plfTInnovaziones;
	}

	public List<PLFTMercatiEntity> getPlfTMercatis()
	{
		return this.plfTMercatis;
	}

	public void setPlfTMercatis(List<PLFTMercatiEntity> plfTMercatis)
	{
		this.plfTMercatis = plfTMercatis;
	}

	public List<PLFImpresaEntity> getStakeholders()
	{
		return stakeholders;
	}

	public void setStakeholders(List<PLFImpresaEntity> stakeholders)
	{
		this.stakeholders = stakeholders;
	}

	public List<PLFServiziEntity> getServiziStandard()
	{
		return serviziStandard;
	}

	public void setServiziStandard(List<PLFServiziEntity> serviziStandard)
	{
		this.serviziStandard = serviziStandard;
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

	public String getLinkServizioStandard()
	{
		return linkServizioStandard;
	}

	public void setLinkServizioStandard(String linkServizioStandard)
	{
		this.linkServizioStandard = linkServizioStandard;
	}

	public List<PLFImpresaAllegatiEntity> getAllegati()
	{
		return allegati;
	}

	public void setAllegati(List<PLFImpresaAllegatiEntity> allegati)
	{
		this.allegati = allegati;
	}

	public int getTipoInformazione()
	{
		return tipoInformazione;
	}

	public void setTipoInformazione(int tipoInformazione)
	{
		this.tipoInformazione = tipoInformazione;
	}

	public BigDecimal getNumAddettiSubordinati()
	{
		return numAddettiSubordinati;
	}

	public void setNumAddettiSubordinati(BigDecimal numAddettiSubordinati)
	{
		this.numAddettiSubordinati = numAddettiSubordinati;
	}

	public BigDecimal getNumUltimoFatturato()
	{
		return numUltimoFatturato;
	}

	public void setNumUltimoFatturato(BigDecimal numUltimoFatturato)
	{
		this.numUltimoFatturato = numUltimoFatturato;
	}

	public List<PLFImpresaEntity> getImpreseCollegate()
	{
		return impreseCollegate;
	}

	public void setImpreseCollegate(List<PLFImpresaEntity> impreseCollegate)
	{
		this.impreseCollegate = impreseCollegate;
	}

	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}

	public String getCodProvincia()
	{
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia)
	{
		this.codProvincia = codProvincia;
	}

	public PLFImpresaTranslationEntity getImpresaTranslation()
	{
		return impresaTranslation;
	}

	public void setImpresaTranslation(PLFImpresaTranslationEntity impresaTranslation)
	{
		this.impresaTranslation = impresaTranslation;
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e
	 * simili.
	 * */
	public String getDescImpresa()
	{
		return impresaTranslation == null ? null : impresaTranslation.getDescImpresa();
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e
	 * simili.
	 * */
	public void setDescImpresa(String descImpresa)
	{

	}

	public boolean isPubblicato()
	{
		return pubblicato;
	}

	public void setPubblicato(boolean pubblicato)
	{
		this.pubblicato = pubblicato;
	}

	@Override
	public String toString()
	{
		return "PLFImpresaEntity [idPlfImpresa=" + idPlfImpresa + "]";
	}

	public String toStringIndentificativo()
	{
		return "Impresa " + getImpresaTranslation().getDescAttivita() + " Partita Iva:" + this.partitaIva + "; Codice Fiscale:" + this.codFiscale;
	}

	public List<PLFTTagEntity> getTags()
	{
		return tags;
	}

	public void setTags(List<PLFTTagEntity> tags)
	{
		this.tags = tags;
	}

	public List<BigDecimal> getElencoIdTags()
	{
		return elencoIdTags;
	}

	public void setElencoIdTags(List<BigDecimal> elencoIdTags)
	{
		this.elencoIdTags = elencoIdTags;
	}

	public String getNumeroCivicoTopo()
	{
		return numeroCivicoTopo;
	}

	public void setNumeroCivicoTopo(String numeroCivicoTopo)
	{
		this.numeroCivicoTopo = numeroCivicoTopo;
	}

	public String getIndirizzoTopo()
	{
		return indirizzoTopo;
	}

	public void setIndirizzoTopo(String indirizzoTopo)
	{
		this.indirizzoTopo = indirizzoTopo;
	}

	public BigDecimal getSezioneCensimento2011()
	{
		return sezioneCensimento2011;
	}

	public void setSezioneCensimento2011(BigDecimal sezioneCensimento2011)
	{
		this.sezioneCensimento2011 = sezioneCensimento2011;
	}

	public BigDecimal getSezioneElettorale()
	{
		return sezioneElettorale;
	}

	public void setSezioneElettorale(BigDecimal sezioneElettorale)
	{
		this.sezioneElettorale = sezioneElettorale;
	}

	public String getCodiceMunicipio()
	{
		return codiceMunicipio;
	}

	public void setCodiceMunicipio(String codiceMunicipio)
	{
		this.codiceMunicipio = codiceMunicipio;
	}

	public String getNomeMunicipio()
	{
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio)
	{
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getCodiceCircoscrizione()
	{
		return codiceCircoscrizione;
	}

	public void setCodiceCircoscrizione(String codiceCircoscrizione)
	{
		this.codiceCircoscrizione = codiceCircoscrizione;
	}

	public String getNomeCircoscrizione()
	{
		return nomeCircoscrizione;
	}

	public void setNomeCircoscrizione(String nomeCircoscrizione)
	{
		this.nomeCircoscrizione = nomeCircoscrizione;
	}

	public String getCodiceUnitaUrbanistica()
	{
		return codiceUnitaUrbanistica;
	}

	public void setCodiceUnitaUrbanistica(String codiceUnitaUrbanistica)
	{
		this.codiceUnitaUrbanistica = codiceUnitaUrbanistica;
	}

	public String getNomeUnitaUrbanistica()
	{
		return nomeUnitaUrbanistica;
	}

	public void setNomeUnitaUrbanistica(String nomeUnitaUrbanistica)
	{
		this.nomeUnitaUrbanistica = nomeUnitaUrbanistica;
	}

	public BigDecimal getIncubatoreCertificato()
	{
		return incubatoreCertificato;
	}

	public void setIncubatoreCertificato(BigDecimal incubatoreCertificato)
	{
		this.incubatoreCertificato = incubatoreCertificato;
	}

}