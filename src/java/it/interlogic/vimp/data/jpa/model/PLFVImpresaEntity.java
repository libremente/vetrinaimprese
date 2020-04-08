package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_V_IMPRESA")
public class PLFVImpresaEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID_IMPRESA")
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "RAGIONE_SOCIALE")
	private String ragioneSociale;

	@Column(name = "CODICE_FISCALE")
	private String codiceFiscale;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	@Column(name = "ID_SETTORE")
	private BigDecimal idSettore;

	@Column(name = "SETTORE")
	private String settore;

	@Column(name = "ID_PROVINCIA")
	private BigDecimal idProvincia;

	@Column(name = "PROVINCIA")
	private String provincia;

	@Column(name = "ID_COMUNE")
	private BigDecimal idComune;

	@Column(name = "COMUNE")
	private String comune;

	@Column(name = "ID_STATO_IMPRESA")
	private BigDecimal idStatoImpresa;

	@Column(name = "STATO_IMPRESA")
	private String statoImpresa;

	@Column(name = "BREVETTO")
	private String brevetto;

	@Column(name = "ISCRIZIONE_REGISTRO")
	private String iscrizioneRegistro;

	@Column(name = "ISCRIZIONE_SPECIALE")
	private String iscrizioneSpeciale;

	@Column(name = "MERCATI")
	private String mercati;

	@Column(name = "INNOVAZIONE")
	private String innovazione;

	@Column(name = "STAKEHOLDER")
	private String stakeholder;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFVImpresaEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public BigDecimal getIdImpresa()
	{
		return idImpresa;
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.idImpresa = idImpresa;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public String getRagioneSociale()
	{
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public String getPartitaIva()
	{
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public BigDecimal getIdSettore()
	{
		return idSettore;
	}

	public void setIdSettore(BigDecimal idSettore)
	{
		this.idSettore = idSettore;
	}

	public String getSettore()
	{
		return settore;
	}

	public void setSettore(String settore)
	{
		this.settore = settore;
	}

	public BigDecimal getIdProvincia()
	{
		return idProvincia;
	}

	public void setIdProvincia(BigDecimal idProvincia)
	{
		this.idProvincia = idProvincia;
	}

	public String getProvincia()
	{
		return provincia;
	}

	public void setProvincia(String provincia)
	{
		this.provincia = provincia;
	}

	public BigDecimal getIdComune()
	{
		return idComune;
	}

	public void setIdComune(BigDecimal idComune)
	{
		this.idComune = idComune;
	}

	public String getComune()
	{
		return comune;
	}

	public void setComune(String comune)
	{
		this.comune = comune;
	}

	public BigDecimal getIdStatoImpresa()
	{
		return idStatoImpresa;
	}

	public void setIdStatoImpresa(BigDecimal idStatoImpresa)
	{
		this.idStatoImpresa = idStatoImpresa;
	}

	public String getStatoImpresa()
	{
		return statoImpresa;
	}

	public void setStatoImpresa(String statoImpresa)
	{
		this.statoImpresa = statoImpresa;
	}

	public String getBrevetto()
	{
		return brevetto;
	}

	public void setBrevetto(String brevetto)
	{
		this.brevetto = brevetto;
	}

	public String getIscrizioneRegistro()
	{
		return iscrizioneRegistro;
	}

	public void setIscrizioneRegistro(String iscrizioneRegistro)
	{
		this.iscrizioneRegistro = iscrizioneRegistro;
	}

	public String getIscrizioneSpeciale()
	{
		return iscrizioneSpeciale;
	}

	public void setIscrizioneSpeciale(String iscrizioneSpeciale)
	{
		this.iscrizioneSpeciale = iscrizioneSpeciale;
	}

	public String getMercati()
	{
		return mercati;
	}

	public void setMercati(String mercati)
	{
		this.mercati = mercati;
	}

	public String getInnovazione()
	{
		return innovazione;
	}

	public void setInnovazione(String innovazione)
	{
		this.innovazione = innovazione;
	}

	public String getStakeholder()
	{
		return stakeholder;
	}

	public void setStakeholder(String stakeholder)
	{
		this.stakeholder = stakeholder;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------

	@Override
	public String toString()
	{
		return "PLFVImpresaEntity [idImpresa=" + idImpresa + ", ragioneSociale=" + ragioneSociale + ", codiceFiscale=" + codiceFiscale + ", partitaIva=" + partitaIva
				+ ", idSettore=" + idSettore + ", settore=" + settore + ", idProvincia=" + idProvincia + ", provincia=" + provincia + ", idComune=" + idComune + ", comune="
				+ comune + ", idStatoImpresa=" + idStatoImpresa + ", statoImpresa=" + statoImpresa + ", brevetto=" + brevetto + ", iscrizioneRegistro=" + iscrizioneRegistro
				+ ", iscrizioneSpeciale=" + iscrizioneSpeciale + ", mercati=" + mercati + ", innovazione=" + innovazione + ", stakeholder=" + stakeholder + "]";
	}

}
