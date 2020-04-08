package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="PLF_T_PROVINCIA" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PLFTProvinciaEntity.countAll", query="SELECT COUNT(x) FROM PLFTProvinciaEntity x" )
} )
public class PLFTProvinciaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="ID_PLF_T_PROVINCIA", nullable=false)
    private BigDecimal     idProvincia     ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    
    @Column(name="COD_PROVINCIA", nullable=false,length=3)
    private String     codProvincia     ;
    
    @Column(name="DESC_PROVINCIA", nullable=false, length=30)
    private String     descProvincia     ;
    
    @Column(name="SIGLA_PROVINCIA", nullable=false, length=2)
    private String     siglaProvincia     ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INI_PROVINCIA", nullable=false)
    private Date     dataIniProvincia       ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_FINE_PROVINCIA")
    private Date     dataFineProvincia       ;


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    
    @ManyToOne
    @JoinColumn(name="COD_REGIONE", referencedColumnName="COD_REGIONE", insertable=false, updatable=false)
    private PLFTRegioneEntity regione   ;
    
  

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PLFTProvinciaEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------

    public BigDecimal getIdProvincia()
	{
		return idProvincia;
	}

	public void setIdProvincia(BigDecimal idProvincia)
	{
		this.idProvincia = idProvincia;
	}

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	
	public String getCodProvincia()
	{
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia)
	{
		this.codProvincia = codProvincia;
	}
	

	public String getDescProvincia()
	{
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia)
	{
		this.descProvincia = descProvincia;
	}

	public String getSiglaProvincia()
	{
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia)
	{
		this.siglaProvincia = siglaProvincia;
	}

	public Date getDataIniProvincia()
	{
		return dataIniProvincia;
	}

	public void setDataIniProvincia(Date dataIniProvincia)
	{
		this.dataIniProvincia = dataIniProvincia;
	}

	public Date getDataFineProvincia()
	{
		return dataFineProvincia;
	}

	public void setDataFineProvincia(Date dataFineProvincia)
	{
		this.dataFineProvincia = dataFineProvincia;
	}


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
	
	  
    public void setRegione( PLFTRegioneEntity regione ) {
        this.regione = regione;
    }
    public PLFTRegioneEntity getRegione() {
        return this.regione;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idProvincia);
        sb.append("]:"); 
        sb.append(codProvincia);
        sb.append("|");
        sb.append(descProvincia);
        sb.append("|");
        sb.append(siglaProvincia);
        sb.append("|");
        sb.append(dataIniProvincia);
        sb.append("|");
        sb.append(dataFineProvincia);
        sb.append("|");
        sb.append(regione!= null ?regione.toString():"");
        return sb.toString(); 
    }


	

}
