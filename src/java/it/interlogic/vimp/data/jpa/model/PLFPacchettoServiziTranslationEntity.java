package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_PACCHETTO_SERVIZI_TRANSLATION translated fields.
 *
 */
@Entity
@Table(name="PLF_PACCHETTO_SERVIZI_TRANSLATION")
public class PLFPacchettoServiziTranslationEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="ID_PACCHETTO_SERVIZI")
    private BigDecimal idPacchettoServizi;

    @Column(name="TITOLO")
    private String titolo;

    @Column(name="DESCRIZIONE")
    private String descrizione;
    
    @Column(name="NOTE1")
    private String note1;
    
    @Column(name="NOTE2")
    private String note2;
    
    @Column(name = "DESC_TAG")
    private String descTag;

	public BigDecimal getIdPacchettoServizi()
	{
		return idPacchettoServizi;
	}

	public void setIdPacchettoServizi(BigDecimal idPacchettoServizi)
	{
		this.idPacchettoServizi = idPacchettoServizi;
	}

	public String getTitolo() 
	{
		return titolo;
	}

	public void setTitolo(String titolo) 
	{
		this.titolo = titolo;
	}

	public String getDescrizione() 
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione) 
	{
		this.descrizione = descrizione;
	}

	public String getNote1() 
	{
		return note1;
	}

	public void setNote1(String note1) 
	{
		this.note1 = note1;
	}

	public String getNote2() 
	{
		return note2;
	}

	public void setNote2(String note2) 
	{
		this.note2 = note2;
	}

	public String getDescTag() 
	{
		return descTag;
	}

	public void setDescTag(String descTag) 
	{
		this.descTag = descTag;
	}
}
