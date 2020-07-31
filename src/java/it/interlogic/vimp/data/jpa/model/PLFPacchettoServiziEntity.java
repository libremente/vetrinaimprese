package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the PLF_PACCHETTO_SERVIZI database table.
 * 
 */
@Entity
@Table(name = "PLF_PACCHETTO_SERVIZI")
@NamedQuery(name = "PLFPacchettoServiziEntity.findAll", query = "SELECT p FROM PLFPacchettoServiziEntity p")
public class PLFPacchettoServiziEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PACCHETTO_SERVIZI")
	private BigDecimal idPacchettoServizi;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;
	
	@Column(name= "LUOGO_EROGAZIONE")
	private String luogoErogazione;
	
	@Column(name= "DESC_CONTATTI")
	private String contatti;
	
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	@ManyToOne
	@JoinColumn(name = "ID_MACROAREA")
	private PLFTMacroareaServiziEntity macroarea;
	
	@ManyToOne
	@JoinColumn(name = "ID_MODALITA_EROGAZIONE_PACCHETTO")
	private PLFTModalitaErogazionePacchettoEntity modalitaErogazione;
	
	@ManyToOne
    @JoinColumn(name = "ID_UTENTE_CREAZIONE")
    private PLFTUtenteEntity utenteCreazione;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CREAZIONE")
	private Date dataCreazione;
	
	@ManyToOne
    @JoinColumn(name = "ID_UTENTE_ULTIMA_MODIFICA")
    private PLFTUtenteEntity utenteUltimaModifica;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ULTIMA_MODIFICA")
	private Date dataUltimaModifica;
	
	@OneToOne
	@JoinColumn(name = "ID_PACCHETTO_SERVIZI")
	private PLFPacchettoServiziTranslationEntity pacchettoServiziTranslation;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Transient
	private String imageData;

	@Transient
	private List<PLFServiziEntity> servizi = new ArrayList<PLFServiziEntity>();

	@Transient
	private List<BigInteger> elencoServizi;

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;
	
	@Override
	public String getDescTag() {
		return pacchettoServiziTranslation == null ? null : pacchettoServiziTranslation.getDescTag();
	}

	public BigDecimal getIdPacchettoServizi()
	{
		return idPacchettoServizi;
	}

	public void setIdPacchettoServizi(BigDecimal idPacchettoServizi)
	{
		this.idPacchettoServizi = idPacchettoServizi;
	}

	public Date getDataFine()
	{
		return dataFine;
	}

	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
	}

	public Date getDataInizio()
	{
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public String getLuogoErogazione()
	{
		return luogoErogazione;
	}

	public void setLuogoErogazione(String luogoErogazione)
	{
		this.luogoErogazione = luogoErogazione;
	}

	public String getContatti()
	{
		return contatti;
	}

	public void setContatti(String contatti)
	{
		this.contatti = contatti;
	}

	public PLFImpresaEntity getPlfImpresa()
	{
		return plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa) 
	{
		this.plfImpresa = plfImpresa;
	}

	public PLFTMacroareaServiziEntity getMacroarea()
	{
		return macroarea;
	}

	public void setMacroarea(PLFTMacroareaServiziEntity macroarea)
	{
		this.macroarea = macroarea;
	}

	public PLFTModalitaErogazionePacchettoEntity getModalitaErogazione()
	{
		return modalitaErogazione;
	}

	public void setModalitaErogazione(PLFTModalitaErogazionePacchettoEntity modalitaErogazione) 
	{
		this.modalitaErogazione = modalitaErogazione;
	}

	public PLFTUtenteEntity getUtenteCreazione() 
	{
		return utenteCreazione;
	}

	public void setUtenteCreazione(PLFTUtenteEntity utenteCreazione) 
	{
		this.utenteCreazione = utenteCreazione;
	}

	public Date getDataCreazione() 
	{
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) 
	{
		this.dataCreazione = dataCreazione;
	}

	public PLFTUtenteEntity getUtenteUltimaModifica() 
	{
		return utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica(PLFTUtenteEntity utenteUltimaModifica) 
	{
		this.utenteUltimaModifica = utenteUltimaModifica;
	}

	public Date getDataUltimaModifica() 
	{
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) 
	{
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public PLFPacchettoServiziTranslationEntity getPacchettoServiziTranslation() 
	{
		return pacchettoServiziTranslation;
	}

	public void setPacchettoServiziTranslation(PLFPacchettoServiziTranslationEntity pacchettoServiziTranslation) 
	{
		this.pacchettoServiziTranslation = pacchettoServiziTranslation;
	}

	public String getImageData()
	{
		return imageData;
	}

	public void setImageData(String imageData)
	{
		this.imageData = imageData;
	}

	public List<PLFServiziEntity> getServizi() {
		return servizi;
	}

	public void setServizi(List<PLFServiziEntity> servizi) {
		this.servizi = servizi;
	}

	public List<BigInteger> getElencoServizi() {
		return elencoServizi;
	}

	public void setElencoServizi(List<BigInteger> elencoServizi) {
		this.elencoServizi = elencoServizi;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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
