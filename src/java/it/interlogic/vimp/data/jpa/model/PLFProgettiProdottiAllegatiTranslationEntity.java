package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_PROGETTI_PRODOTTI_ALLEGATI_TRANSLATION")
@NamedQuery(name = "PLFProgettiProdottiAllegatiTranslationEntity.findAll", query = "SELECT p FROM PLFProgettiProdottiAllegatiTranslationEntity p")
public class PLFProgettiProdottiAllegatiTranslationEntity implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "ID_PROGETTI_PRODOTTI_ALLEGATI")
    private BigDecimal idProgettiProdottiAllegati;

    @Column(name = "DESCRIZIONE")
    private String descrizione;
    
	public BigDecimal getIdProgettiProdottiAllegati()
	{
		return idProgettiProdottiAllegati;
	}

	public void setIdProgettiProdottiAllegati(BigDecimal idProgettiProdottiAllegati)
	{
		this.idProgettiProdottiAllegati = idProgettiProdottiAllegati;
	}

	public String getDescrizione()
	{
		return this.descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}


}
