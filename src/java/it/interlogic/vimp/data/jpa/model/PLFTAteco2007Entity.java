
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
@Table(name="PLF_T_ATECO2007" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PLFTAteco2007Entity.countAll", query="SELECT COUNT(x) FROM PLFTAteco2007Entity x" )
} )
public class PLFTAteco2007Entity implements Serializable, TranslatableCodifica {

    private static final long serialVersionUID = 1L;
    

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ID", nullable=false)
    private BigDecimal id     ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="CODICE", nullable=false, length=8)
    private String     codice     ;
    
    @Column(name="DESCRIZIONE", nullable=false, length=4000)
    private String     descrizione     ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INIZIO")
    private Date     dataInizio       ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_FINE")
    private Date     dataFine       ;

   


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PLFTAteco2007Entity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------

    public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal idAteco2007)
	{
		this.id = idAteco2007;
	}
    

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------


	

	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codAteco2007)
	{
		this.codice = codAteco2007;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descAteco2007)
	{
		this.descrizione = descAteco2007;
	}

	public Date getDataInizio()
	{
		return dataInizio;
	}

	public void setDataInizio(Date dataIniAteco2007)
	{
		this.dataInizio = dataIniAteco2007;
	}

	public Date getDataFine()
	{
		return dataFine;
	}

	public void setDataFine(Date dataFineAteco2007)
	{
		this.dataFine = dataFineAteco2007;
	}

	
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(codice);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(dataInizio);
        sb.append("|");
        sb.append(dataFine);
        return sb.toString(); 
    }

	

}
