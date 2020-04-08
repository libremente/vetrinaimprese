package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="PLF_T_REGIONE" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PLFTRegioneEntity.countAll", query="SELECT COUNT(x) FROM PLFTRegioneEntity x" )
} )
public class PLFTRegioneEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ID_PLF_T_REGIONE", nullable=false)
    private BigDecimal idRegione     ;
    

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------
    
    @Column(name="COD_REGIONE", nullable=false, length=2)
    private String  codRegione     ;
    
    @Column(name="DESC_REGIONE", nullable=false, length=30)
    private String     descRegione     ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INI_REGIONE", nullable=false)
    private Date     dataIniRegione       ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_FINE_REGIONE")
    private Date     dataFineRegione       ;

   


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PLFTRegioneEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------

    public BigDecimal getIdRegione()
   	{
   		return idRegione;
   	}

   	public void setIdRegione(BigDecimal idRegione)
   	{
   		this.idRegione = idRegione;
   	}

    

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
   	
   	public String getCodRegione()
	{
		return codRegione;
	}

	public void setCodRegione(String codRegione)
	{
		this.codRegione = codRegione;
	}

   	public String getDescRegione()
	{
		return descRegione;
	}

	public void setDescRegione(String descRegione)
	{
		this.descRegione = descRegione;
	}

	public Date getDataIniRegione()
	{
		return dataIniRegione;
	}

	public void setDataIniRegione(Date dataIniRegione)
	{
		this.dataIniRegione = dataIniRegione;
	}

	public Date getDataFineRegione()
	{
		return dataFineRegione;
	}

	public void setDataFineRegione(Date dataFineRegione)
	{
		this.dataFineRegione = dataFineRegione;
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
        sb.append(idRegione);
        sb.append("]:"); 
        sb.append(codRegione);
        sb.append("|");
        sb.append(descRegione);
        sb.append("|");
        sb.append(dataIniRegione);
        sb.append("|");
        sb.append(dataFineRegione);
        return sb.toString(); 
    }

	

}
