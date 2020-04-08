package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_SERVIZI_IMPRESA")
public class PLFRServiziImpresaEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFRServiziImpresaEntityKey compositePrimaryKey;

	@Column(name="DESC_LINK")
	private String linkCollegamentoImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRServiziImpresaEntity()
	{
		super();
		this.compositePrimaryKey = new PLFRServiziImpresaEntityKey();
	}

	public PLFRServiziImpresaEntity(BigDecimal idServizi, BigDecimal idPlfImpresa)
	{
		this.compositePrimaryKey = new PLFRServiziImpresaEntityKey(idServizi,idPlfImpresa);
	}

	public PLFRServiziImpresaEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	public void setIdServizi(BigDecimal idServizi)
	{
		this.compositePrimaryKey.setIdServizi(idServizi);
	}

	public BigDecimal getIdServizi()
	{
		return this.compositePrimaryKey.getIdServizi();
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.compositePrimaryKey.setIdImpresa(idImpresa);
	}

	public BigDecimal getIdImpresa()
	{
		return this.compositePrimaryKey.getIdImpresa();
	}

	public String getLinkCollegamentoImpresa() {
		return linkCollegamentoImpresa;
	}

	public void setLinkCollegamentoImpresa(String linkCollegamentoImpresa) {
		this.linkCollegamentoImpresa = linkCollegamentoImpresa;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (compositePrimaryKey != null)
		{
			sb.append(compositePrimaryKey.toString());
		}
		else
		{
			sb.append("(null-key)");
		}
		sb.append("]:");
		return sb.toString();
	}

}
