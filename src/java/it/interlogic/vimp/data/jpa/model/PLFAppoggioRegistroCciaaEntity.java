package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PLF_APPOGGIO_REGISTRO_CCIAA database table.
 * 
 */
@Entity
@Table(name="PLF_APPOGGIO_REGISTRO_CCIAA")
@NamedQuery(name="PLFAppoggioRegistroCciaaEntity.findAll", query="SELECT p FROM PLFAppoggioRegistroCciaaEntity p")
public class PLFAppoggioRegistroCciaaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PLF_IMPRESA")
	private BigDecimal idPlfImpresa;

	private String ateco;

	@Column(name="CLASSE_ADDETTI")
	private String classeAddetti;

	@Column(name="CLASSE_CAPITALE")
	private String classeCapitale;

	@Column(name="CLASSE_PRODUZIONE")
	private String classeProduzione;

	@Column(name="CODICE_FISCALE")
	private String codiceFiscale;

	private String comune;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO_EFFETTIVO")
	private Date dataInizioEffettivo;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ISCRIZIONE_PMI_INNOVATIVE")
	private Date dataIscrizionePmiInnovative;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ISCRIZIONE_REGISTRO")
	private Date dataIscrizioneRegistro;

	@Column(name="EMAIL_RIFERIMENTO")
	private String emailRiferimento;

	private String link;

	@Column(name="NATURA_GIURIDICA")
	private String naturaGiuridica;

	@Column(name="PARTITA_IVA")
	private String partitaIva;

	@Column(name="PREVALENZA_FEMMINILE")
	private String prevalenzaFemminile;

	@Column(name="PREVALENZA_GIOVANILE")
	private String prevalenzaGiovanile;

	@Column(name="PREVALENZA_STRANIERA")
	private String prevalenzaStraniera;

	@Column(name="PRIMO_REQUISITO_PMI")
	private String primoRequisitoPmi;

	private String provincia;

	@Column(name="RAGIONE_SOCIALE")
	private String ragioneSociale;

	@Column(name="SECONDO_REQUISITO_PMI")
	private String secondoRequisitoPmi;

	@Column(name="SETTORE_IMPRESA")
	private String settoreImpresa;

	@Column(name="SITO_INTERNET")
	private String sitoInternet;

	@Column(name="STATO_IMPRESA")
	private String statoImpresa;

	@Column(name="TERZO_REQUISITO_PMI")
	private String terzoRequisitoPmi;

	public PLFAppoggioRegistroCciaaEntity() {
	}

	public BigDecimal getIdPlfImpresa() {
		return this.idPlfImpresa;
	}

	public void setIdPlfImpresa(BigDecimal idPlfImpresa) {
		this.idPlfImpresa = idPlfImpresa;
	}

	public String getAteco() {
		return this.ateco;
	}

	public void setAteco(String ateco) {
		this.ateco = ateco;
	}

	public String getClasseAddetti() {
		return this.classeAddetti;
	}

	public void setClasseAddetti(String classeAddetti) {
		this.classeAddetti = classeAddetti;
	}

	public String getClasseCapitale() {
		return this.classeCapitale;
	}

	public void setClasseCapitale(String classeCapitale) {
		this.classeCapitale = classeCapitale;
	}

	public String getClasseProduzione() {
		return this.classeProduzione;
	}

	public void setClasseProduzione(String classeProduzione) {
		this.classeProduzione = classeProduzione;
	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public Date getDataInizioEffettivo() {
		return this.dataInizioEffettivo;
	}

	public void setDataInizioEffettivo(Date dataInizioEffettivo) {
		this.dataInizioEffettivo = dataInizioEffettivo;
	}

	public Date getDataIscrizionePmiInnovative() {
		return this.dataIscrizionePmiInnovative;
	}

	public void setDataIscrizionePmiInnovative(Date dataIscrizionePmiInnovative) {
		this.dataIscrizionePmiInnovative = dataIscrizionePmiInnovative;
	}

	public Date getDataIscrizioneRegistro() {
		return this.dataIscrizioneRegistro;
	}

	public void setDataIscrizioneRegistro(Date dataIscrizioneRegistro) {
		this.dataIscrizioneRegistro = dataIscrizioneRegistro;
	}

	public String getEmailRiferimento() {
		return this.emailRiferimento;
	}

	public void setEmailRiferimento(String emailRiferimento) {
		this.emailRiferimento = emailRiferimento;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNaturaGiuridica() {
		return this.naturaGiuridica;
	}

	public void setNaturaGiuridica(String naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getPrevalenzaFemminile() {
		return this.prevalenzaFemminile;
	}

	public void setPrevalenzaFemminile(String prevalenzaFemminile) {
		this.prevalenzaFemminile = prevalenzaFemminile;
	}

	public String getPrevalenzaGiovanile() {
		return this.prevalenzaGiovanile;
	}

	public void setPrevalenzaGiovanile(String prevalenzaGiovanile) {
		this.prevalenzaGiovanile = prevalenzaGiovanile;
	}

	public String getPrevalenzaStraniera() {
		return this.prevalenzaStraniera;
	}

	public void setPrevalenzaStraniera(String prevalenzaStraniera) {
		this.prevalenzaStraniera = prevalenzaStraniera;
	}

	public String getPrimoRequisitoPmi() {
		return this.primoRequisitoPmi;
	}

	public void setPrimoRequisitoPmi(String primoRequisitoPmi) {
		this.primoRequisitoPmi = primoRequisitoPmi;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getSecondoRequisitoPmi() {
		return this.secondoRequisitoPmi;
	}

	public void setSecondoRequisitoPmi(String secondoRequisitoPmi) {
		this.secondoRequisitoPmi = secondoRequisitoPmi;
	}

	public String getSettoreImpresa() {
		return this.settoreImpresa;
	}

	public void setSettoreImpresa(String settoreImpresa) {
		this.settoreImpresa = settoreImpresa;
	}

	public String getSitoInternet() {
		return this.sitoInternet;
	}

	public void setSitoInternet(String sitoInternet) {
		this.sitoInternet = sitoInternet;
	}

	public String getStatoImpresa() {
		return this.statoImpresa;
	}

	public void setStatoImpresa(String statoImpresa) {
		this.statoImpresa = statoImpresa;
	}

	public String getTerzoRequisitoPmi() {
		return this.terzoRequisitoPmi;
	}

	public void setTerzoRequisitoPmi(String terzoRequisitoPmi) {
		this.terzoRequisitoPmi = terzoRequisitoPmi;
	}

}