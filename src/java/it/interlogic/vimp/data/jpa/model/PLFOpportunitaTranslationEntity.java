package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_OPPORTUNITA translated fields.
 *
 */
@Entity
@Table(name = "PLF_OPPORTUNITA_TRANSLATION")
public class PLFOpportunitaTranslationEntity implements Serializable, TagEntityInterface
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PLF_OPPORTUNITA")
    private BigDecimal idPlfOpportunita;

    @Column(name = "DESC_NOME")
    private String descNome;

    @Column(name = "DESC_PERIODO")
    private String descPeriodo;

    @Column(name = "DESC_REQUISITI")
    private String descRequisiti;

    @Column(name = "DESC_TAG")
    private String descTag;

    @Column(name = "DESC_FONTE")
    private String descFonte;
    
    @Column(name = "DESC_CONTATTI")
	private String descContatti;

    public BigDecimal getIdPlfOpportunita()
    {
        return idPlfOpportunita;
    }

    public void setIdPlfOpportunita(BigDecimal idPlfOpportunita)
    {
        this.idPlfOpportunita = idPlfOpportunita;
    }

    public String getDescNome()
    {
        return descNome;
    }

    public void setDescNome(String descNome)
    {
        this.descNome = descNome;
    }

    public String getDescPeriodo()
    {
        return descPeriodo;
    }

    public void setDescPeriodo(String descPeriodo)
    {
        this.descPeriodo = descPeriodo;
    }

    public String getDescRequisiti()
    {
        return descRequisiti;
    }

    public void setDescRequisiti(String descRequisiti)
    {
        this.descRequisiti = descRequisiti;
    }

    @Override
    public String getDescTag()
    {
        return descTag;
    }

    public void setDescTag(String descTag)
    {
        this.descTag = descTag;
    }

    public String getDescFonte()
    {
        return descFonte;
    }

    public void setDescFonte(String descFonte)
    {
        this.descFonte = descFonte;
    }

	public String getDescContatti()
	{
		return descContatti;
	}

	public void setDescContatti(String descContatti)
	{
		this.descContatti = descContatti;
	}
}
