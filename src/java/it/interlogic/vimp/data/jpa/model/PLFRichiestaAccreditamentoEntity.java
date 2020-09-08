package it.interlogic.vimp.data.jpa.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PLF_RICHIESTA_ACCREDITAMENTO database table.
 * 
 */
@Entity
@Table(name = "PLF_RICHIESTA_ACCREDITAMENTO")
@NamedQuery(name = "PLFRichiestaAccreditamentoEntity.findAll", query = "SELECT p FROM PLFRichiestaAccreditamentoEntity p")
public class PLFRichiestaAccreditamentoEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_RICHIESTA_ACCREDITAMENTO")
	private BigDecimal idRichiestaAccreditamento;

	@Column(name = "COD_CAP")
	private String codCap;

	@Column(name = "COD_FISCALE")
	private String codFiscale;

	@Column(name = "COD_FISCALE_RICHIEDENTE")
	private String codFiscaleRichiedente;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RICHIESTA")
	private Date dataRichiesta;

	@Column(name = "DESC_IMPRESA")
	private String descImpresa;

	@Column(name = "DESC_INDIRIZZO")
	private String descIndirizzo;

	@Column(name = "ELEMENTI_INNOVAZIONE_ALTRO")
	private String elementiInnovazioneAltro;

	@Column(name = "EMAIL_CONTATTO")
	private String emailContatto;

	@Column(name = "FLAG_ACCREDITAMENTO")
	private String flagAccreditamento;

	@Column(name = "FLAG_PERCORSO_INNOVAZIONE")
	private String flagPercorsoInnovazione;

	@Column(name = "ID_CONTROLLI_RICHIESTA")
	private BigDecimal idControlliRichiesta;

	@Transient
	private String descControlliRichiesta;

	@Column(name = "ID_STATO_RICHIESTA")
	private BigDecimal idStatoRichiesta;

	@Transient
	private String descStatoRichiesta;

	@Column(name = "NUMERO_CIVICO")
	private String numeroCivico;

	@Column(name = "PARERE_ACCREDITAMENTO")
	private String parereAccreditamento;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	@Column(name = "RAGIONE_SOCIALE")
	private String ragioneSociale;

	@Column(name = "TEL_CONTATTO")
	private String telContatto;

	// bi-directional many-to-one association to PLFImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	// bi-directional many-to-one association to PLFTComune
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_COMUNE")
	private PLFTComuneEntity plfTComune;

	// bi-directional many-to-one association to PLFTStatoImpresa
	@ManyToOne
	@JoinColumn(name = "ID_STATO_IMPRESA")
	private PLFTStatoImpresaEntity plfTStatoImpresa;

	// bi-directional many-to-many association to PLFTInnovazione
	@ManyToMany(mappedBy = "plfRichiestaAccreditamentos")
	private List<PLFTInnovazioneEntity> plfTInnovaziones;

	@Transient
	private String azione;
	
	@Column(name = "RAPPRESENTANTE")
	private boolean rappresentante;
	
	@Column(name = "INCARICATO")
	private boolean incaricato;

	public PLFRichiestaAccreditamentoEntity()
	{
	}

	public BigDecimal getIdRichiestaAccreditamento()
	{
		return this.idRichiestaAccreditamento;
	}

	public void setIdRichiestaAccreditamento(BigDecimal idRichiestaAccreditamento)
	{
		this.idRichiestaAccreditamento = idRichiestaAccreditamento;
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

	public Date getDataRichiesta()
	{
		return this.dataRichiesta;
	}

	public void setDataRichiesta(Date dataRichiesta)
	{
		this.dataRichiesta = dataRichiesta;
	}

	public String getDescImpresa()
	{
		return this.descImpresa;
	}

	public void setDescImpresa(String descImpresa)
	{
		this.descImpresa = descImpresa;
	}

	public String getDescIndirizzo()
	{
		return this.descIndirizzo;
	}

	public void setDescIndirizzo(String descIndirizzo)
	{
		this.descIndirizzo = descIndirizzo;
	}

	public String getElementiInnovazioneAltro()
	{
		return this.elementiInnovazioneAltro;
	}

	public void setElementiInnovazioneAltro(String elementiInnovazioneAltro)
	{
		this.elementiInnovazioneAltro = elementiInnovazioneAltro;
	}

	public String getEmailContatto()
	{
		return this.emailContatto;
	}

	public void setEmailContatto(String emailContatto)
	{
		this.emailContatto = emailContatto;
	}

	public String getFlagAccreditamento()
	{
		return this.flagAccreditamento;
	}

	public void setFlagAccreditamento(String flagAccreditamento)
	{
		this.flagAccreditamento = flagAccreditamento;
	}

	public String getFlagPercorsoInnovazione()
	{
		return this.flagPercorsoInnovazione;
	}

	public void setFlagPercorsoInnovazione(String flagPercorsoInnovazione)
	{
		this.flagPercorsoInnovazione = flagPercorsoInnovazione;
	}

	public BigDecimal getIdControlliRichiesta()
	{
		return this.idControlliRichiesta;
	}

	public void setIdControlliRichiesta(BigDecimal idControlliRichiesta)
	{
		this.idControlliRichiesta = idControlliRichiesta;
	}

	public BigDecimal getIdStatoRichiesta()
	{
		return this.idStatoRichiesta;
	}

	public void setIdStatoRichiesta(BigDecimal idStatoRichiesta)
	{
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public String getNumeroCivico()
	{
		return this.numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico)
	{
		this.numeroCivico = numeroCivico;
	}

	public String getParereAccreditamento()
	{
		return this.parereAccreditamento;
	}

	public void setParereAccreditamento(String parereAccreditamento)
	{
		this.parereAccreditamento = parereAccreditamento;
	}

	public String getPartitaIva()
	{
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public String getRagioneSociale()
	{
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public String getTelContatto()
	{
		return this.telContatto;
	}

	public void setTelContatto(String telContatto)
	{
		this.telContatto = telContatto;
	}

	public PLFImpresaEntity getPlfImpresa()
	{
		return this.plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa)
	{
		this.plfImpresa = plfImpresa;
	}

	public PLFTComuneEntity getPlfTComune()
	{
		return this.plfTComune;
	}

	public void setPlfTComune(PLFTComuneEntity plfTComune)
	{
		this.plfTComune = plfTComune;
	}

	public PLFTStatoImpresaEntity getPlfTStatoImpresa()
	{
		return this.plfTStatoImpresa;
	}

	public void setPlfTStatoImpresa(PLFTStatoImpresaEntity plfTStatoImpresa)
	{
		this.plfTStatoImpresa = plfTStatoImpresa;
	}

	public List<PLFTInnovazioneEntity> getPlfTInnovaziones()
	{
		return this.plfTInnovaziones;
	}

	public void setPlfTInnovaziones(List<PLFTInnovazioneEntity> plfTInnovaziones)
	{
		this.plfTInnovaziones = plfTInnovaziones;
	}

	public String getCodFiscaleRichiedente()
	{
		return codFiscaleRichiedente;
	}

	public void setCodFiscaleRichiedente(String codFiscaleRichiedente)
	{
		this.codFiscaleRichiedente = codFiscaleRichiedente;
	}

	public String getDescControlliRichiesta()
	{
		return descControlliRichiesta;
	}

	public void setDescControlliRichiesta(String descControlliRichiesta)
	{
		this.descControlliRichiesta = descControlliRichiesta;
	}

	public String getDescStatoRichiesta()
	{
		return descStatoRichiesta;
	}

	public void setDescStatoRichiesta(String descStatoRichiesta)
	{
		this.descStatoRichiesta = descStatoRichiesta;
	}

	public String getAzione()
	{
		return azione;
	}

	public void setAzione(String azione)
	{
		this.azione = azione;
	}

	public boolean isRappresentante()
	{
		return rappresentante;
	}

	public void setRappresentante(boolean rappresentante)
	{
		this.rappresentante = rappresentante;
	}

	public boolean isIncaricato()
	{
		return incaricato;
	}

	public void setIncaricato(boolean incaricato)
	{
		this.incaricato = incaricato;
	}
	
	

}