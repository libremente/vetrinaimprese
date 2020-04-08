package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_IMPRESA_ALLEGATI_TRANSLATION")
@NamedQuery(name = "PLFImpresaAllegatiTranslationEntity.findAll", query = "SELECT p FROM PLFImpresaAllegatiTranslationEntity p")
public class PLFImpresaAllegatiTranslationEntity implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "ID_IMPRESA_ALLEGATI")
    private BigDecimal idImpresaAllegati;

    @Column(name = "DESCRIZIONE")
    private String descrizione;
    
	public BigDecimal getIdImpresaAllegati()
	{
		return idImpresaAllegati;
	}

	public void setIdImpresaAllegati(BigDecimal idImpresaAllegati)
	{
		this.idImpresaAllegati = idImpresaAllegati;
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
