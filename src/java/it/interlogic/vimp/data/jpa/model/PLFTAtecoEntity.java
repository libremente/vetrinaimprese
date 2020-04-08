package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLF_T_ATECO")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTAtecoEntity.countAll", query = "SELECT COUNT(x) FROM PLFTAtecoEntity x") })
public class PLFTAtecoEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID_PLF_T_ATECO", nullable = false)
	private BigDecimal idAteco;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "C_ATTIVITA", nullable = false)
	private BigDecimal attivita;

	@Column(name = "T_CODIFICA", nullable = false)
	private BigDecimal codifica;

	@Column(name = "C_FONTE", nullable = false, length = 2)
	private String fonte;

	@Column(name = "SEZIONE", nullable = false, length = 1)
	private String sezione;

	@Column(name = "SOTTOSEZIONE", length = 30)
	private String sottosezione;

	@Column(name = "DESCRIZIONE", nullable = false, length = 400)
	private String descrizione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_MODIFICA")
	private Date dataModifica;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTAtecoEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	public BigDecimal getIdAteco()
	{
		return idAteco;
	}

	public void setIdAteco(BigDecimal idAteco)
	{
		this.idAteco = idAteco;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public BigDecimal getAttivita()
	{
		return attivita;
	}

	public void setAttivita(BigDecimal attivita)
	{
		this.attivita = attivita;
	}

	public BigDecimal getCodifica()
	{
		return codifica;
	}

	public void setCodifica(BigDecimal codifica)
	{
		this.codifica = codifica;
	}

	public String getFonte()
	{
		return fonte;
	}

	public void setFonte(String fonte)
	{
		this.fonte = fonte;
	}

	public String getSezione()
	{
		return sezione;
	}

	public void setSezione(String sezione)
	{
		this.sezione = sezione;
	}

	public String getSottosezione()
	{
		return sottosezione;
	}

	public void setSottosezione(String sottosezione)
	{
		this.sottosezione = sottosezione;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public Date getDataModifica()
	{
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica)
	{
		this.dataModifica = dataModifica;
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
		return "PLFTAtecoEntity [idAteco=" + idAteco + ", attivita=" + attivita + ", codifica=" + codifica + ", fonte=" + fonte + ", sezione=" + sezione + ", sottosezione="
				+ sottosezione + ", descrizione=" + descrizione + ", dataModifica=" + dataModifica + "]";
	}

}
