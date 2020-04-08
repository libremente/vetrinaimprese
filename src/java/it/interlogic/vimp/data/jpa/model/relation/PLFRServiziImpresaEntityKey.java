package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import it.interlogic.vimp.utils.EntityUtility;

@Embeddable
public class PLFRServiziImpresaEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_SERVIZI", nullable = false)
	private BigDecimal idServizi;

	@Column(name = "ID_PLF_IMPRESA", nullable = false)
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRServiziImpresaEntityKey()
	{
		super();
	}

	public PLFRServiziImpresaEntityKey(BigDecimal idServizi, BigDecimal idImpresa)
	{
		super();
		this.idServizi = idServizi;
		this.idImpresa = idImpresa;
	}

	public BigDecimal getIdServizi()
	{
		return idServizi;
	}

	public void setIdServizi(BigDecimal idServizi)
	{
		this.idServizi = idServizi;
	}

	public BigDecimal getIdImpresa()
	{
		return idImpresa;
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.idImpresa = idImpresa;
	}

	@Override
	public int hashCode()
	{
		return EntityUtility.hashCode(idServizi,idImpresa);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof  PLFRServiziImpresaEntityKey)
		{
			PLFRServiziImpresaEntityKey o = (PLFRServiziImpresaEntityKey) obj;
			return 	EntityUtility.equals(idServizi, o.idServizi)
					&& EntityUtility.equals(idImpresa,o.idImpresa);
		}
		return  false;
	}

	@Override
	public String toString()
	{
		return "PLFRServiziStandardEntityKey [idServiziStandard=" + idServizi + ", idImpresa=" + idImpresa + "]";
	}

}
