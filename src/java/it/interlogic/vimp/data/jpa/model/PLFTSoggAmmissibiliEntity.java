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
@Table(name="PLF_T_SOGG_AMMISSIBILI" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PLFTSoggAmmissibiliEntity.countAll", query="SELECT COUNT(x) FROM PLFTSoggAmmissibiliEntity x" )
} )
public class PLFTSoggAmmissibiliEntity implements Serializable, TranslatableCodifica {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ID", nullable=false)
    private BigDecimal id;
    

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------
    
    @Column(name="CODICE", nullable=false, length=10)
    private String codice;
    
    @Column(name="DESCRIZIONE", nullable=false, length=255)
    private String descrizione;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INIZIO", nullable=false)
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_FINE")
    private Date dataFine;

 
  
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PLFTSoggAmmissibiliEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
       
	@Override
	public BigDecimal getId()
	{
		return id;
	}

	@Override
	public void setId(BigDecimal id)
	{
		this.id = id;		
	}

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
		
	@Override
	public String getCodice()
	{
		return codice;
	}

	@Override
	public void setCodice(String codice)
	{
		this.codice = codice;
	}
	
	@Override
	public String getDescrizione()
	{
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	@Override
	public Date getDataInizio()
	{
		return dataInizio;
	}

	@Override
	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}
	
	@Override
	public Date getDataFine()
	{
		return dataFine;
	}

	@Override
	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
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
