package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

import it.interlogic.vimp.service.impl.IDecodificheServiceImpl;

/**
 * The persistent class for the PLF_SERVIZI database table.
 * 
 */
@Entity
@Table(name = "PLF_SERVIZI")
@NamedQuery(name = "PLFServiziEntity.findAll", query = "SELECT p FROM PLFServiziEntity p")
public class PLFServiziEntity implements Serializable, TagEntityInterface
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_SERVIZI")
	private BigDecimal idServizi;

	@Column(name = "SERVIZI_STANDARD")
	private String serviziStandard;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_SERVIZIO")
	private PLFTTipoServizioEntity plfTTipoServizio;

	@Column(name = "DESC_LINK")
	private String linkCollegamentoImpresa;

	@Column(name = "INDIRIZZO_EROGAZIONE")
	private String indirizzoErogazione;

	@Column(name = "NUMERO_CIVICO")
	private String civico;

	@Column(name = "LINK_APPROFONDIMENTO")
	private String linkApprofondimento;

	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_AREE_COMPETENZA")
	private PLFTAreeCompetenzaEntity plfTAreeCompetenza;

	@ManyToOne
	@JoinColumn(name = "ID_DENOMINAZIONE_SERVIZIO")
	private PLFTDenominazioneServiziEntity denominazioneServizio;

	@ManyToOne
	@JoinColumn(name = "ID_MODALITA_EROGAZIONE_SERVIZIO")
	private PLFTModalitaErogazioneServizioEntity modalitaErogazioneServizio;

	@OneToOne
	@JoinColumn(name = "ID_SERVIZI")
	private PLFServiziTranslationEntity serviziTranslation;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Transient
	private List<PLFImpresaEntity> plfImpresas;

	@Transient
	private PLFImpresaEntity plfImpresa;

	@Transient
	private String imageData;

	@Transient
	private List<BigInteger> elencoTipoErogazione;

	@Transient
	private List<BigInteger> elencoMacroarea;

	@Transient
	private List<PLFTTipoErogazioneServizioEntity> tipiErogazione;

	@Transient
	private List<PLFTMacroareaServiziEntity> macroaree;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	@Transient
	private List<PLFTTagEntity> tags;

	@Transient
	private List<BigDecimal> elencoIdTags;

	public PLFServiziEntity()
	{
	}

	public String getDenominazioneCalcolata()
	{

		if (serviziTranslation != null)
		{
			if ("S".equals(serviziStandard))
			{
				if (getPlfImpresas() != null && getPlfImpresas().size()>0)
					return serviziTranslation.getDescrizione() + " - " +  getPlfImpresas().get(0).getImpresaTranslation().getDescImpresa() + " (STANDARD)";
				
				return serviziTranslation.getDescrizione() + " - STANDARD";
			}
			else
			{
				
				if (getPlfImpresas() != null && getPlfImpresas().size()>0)
					return serviziTranslation.getTitolo() + " - " +  getPlfImpresas().get(0).getImpresaTranslation().getDescImpresa();
				
				return serviziTranslation.getTitolo();

			}
		}
		return "";
	}

	/**
	 * Necessario al funzionamento di {@link IDecodificheServiceImpl#toMap}
	 * */
	public void setDenominazioneCalcolata(String d)
	{

	}

	public String getDescrizione()
	{
		return serviziTranslation.getDescrizione();
	}

	public void setDescrizione(String descrizione)
	{
	}

	public BigDecimal getIdServizi()
	{
		return this.idServizi;
	}

	public void setIdServizi(BigDecimal idServizi)
	{
		this.idServizi = idServizi;
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

	public PLFTTipoServizioEntity getPlfTTipoServizio()
	{
		return this.plfTTipoServizio;
	}

	public void setPlfTTipoServizio(PLFTTipoServizioEntity plfTTipoServizio)
	{
		this.plfTTipoServizio = plfTTipoServizio;
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e
	 * simili.
	 */
	public String getTitolo()
	{
		return serviziTranslation == null ? null : serviziTranslation.getTitolo();
	}

	/**
	 * Mantenuto per compatibilità con {@link IDecodificheServiceImpl#toMap} e
	 * simili.
	 */
	public void setTitolo(String titolo)
	{

	}

	public PLFTAreeCompetenzaEntity getPlfTAreeCompetenza()
	{
		return this.plfTAreeCompetenza;
	}

	public void setPlfTAreeCompetenza(PLFTAreeCompetenzaEntity plfTAreeCompetenza)
	{
		this.plfTAreeCompetenza = plfTAreeCompetenza;
	}

	public String getServiziStandard()
	{
		return serviziStandard;
	}

	public void setServiziStandard(String serviziStandard)
	{
		this.serviziStandard = serviziStandard;
	}

	public PLFTModalitaErogazioneServizioEntity getModalitaErogazioneServizio()
	{
		return modalitaErogazioneServizio;
	}

	public void setModalitaErogazioneServizio(PLFTModalitaErogazioneServizioEntity modalitaErogazioneServizio)
	{
		this.modalitaErogazioneServizio = modalitaErogazioneServizio;
	}

	@Override
	public String getDescTag()
	{
		return serviziTranslation == null ? null : serviziTranslation.getDescTag();
	}

	public PLFServiziTranslationEntity getServiziTranslation()
	{
		return serviziTranslation;
	}

	public void setServiziTranslation(PLFServiziTranslationEntity serviziTranslation)
	{
		this.serviziTranslation = serviziTranslation;
	}

	public String getImageData()
	{
		return imageData;
	}

	public void setImageData(String imageData)
	{
		this.imageData = imageData;
	}

	public List<PLFImpresaEntity> getPlfImpresas()
	{
		return this.plfImpresas;
	}

	public void setPlfImpresas(List<PLFImpresaEntity> plfImpresas)
	{
		this.plfImpresas = plfImpresas;
	}

	public String getLinkCollegamentoImpresa()
	{
		return linkCollegamentoImpresa;
	}

	public void setLinkCollegamentoImpresa(String linkCollegamentoImpresa)
	{
		this.linkCollegamentoImpresa = linkCollegamentoImpresa;
	}

	public PLFTDenominazioneServiziEntity getDenominazioneServizio()
	{
		return denominazioneServizio;
	}

	public void setDenominazioneServizio(PLFTDenominazioneServiziEntity denominazioneServizio)
	{
		this.denominazioneServizio = denominazioneServizio;
	}

	public String getIndirizzoErogazione()
	{
		return indirizzoErogazione;
	}

	public void setIndirizzoErogazione(String indirizzoErogazione)
	{
		this.indirizzoErogazione = indirizzoErogazione;
	}

	public String getCivico()
	{
		return civico;
	}

	public void setCivico(String civico)
	{
		this.civico = civico;
	}

	public List<BigInteger> getElencoTipoErogazione()
	{
		return elencoTipoErogazione;
	}

	public void setElencoTipoErogazione(List<BigInteger> elencoTipoErogazione)
	{
		this.elencoTipoErogazione = elencoTipoErogazione;
	}

	public List<BigInteger> getElencoMacroarea()
	{
		return elencoMacroarea;
	}

	public void setElencoMacroarea(List<BigInteger> elencoMacroarea)
	{
		this.elencoMacroarea = elencoMacroarea;
	}

	public List<PLFTTipoErogazioneServizioEntity> getTipiErogazione()
	{
		return tipiErogazione;
	}

	public void setTipiErogazione(List<PLFTTipoErogazioneServizioEntity> tipiErogazione)
	{
		this.tipiErogazione = tipiErogazione;
	}

	public List<PLFTMacroareaServiziEntity> getMacroaree()
	{
		return macroaree;
	}

	public void setMacroaree(List<PLFTMacroareaServiziEntity> macroaree)
	{
		this.macroaree = macroaree;
	}

	public boolean isPubblicato()
	{
		return pubblicato;
	}

	public void setPubblicato(boolean pubblicato)
	{
		this.pubblicato = pubblicato;
	}

	public String getLinkApprofondimento()
	{
		return linkApprofondimento;
	}

	public void setLinkApprofondimento(String linkApprofondimento)
	{
		this.linkApprofondimento = linkApprofondimento;
	}

	public PLFImpresaEntity getPlfImpresa()
	{
		return plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa)
	{
		this.plfImpresa = plfImpresa;
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

	public Date getDataCancellazione()
	{
		return dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
	}

}