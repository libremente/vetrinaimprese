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
@Table(name="PLF_T_TIPO_ALLEGATO" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PLFTTipoAllegatoEntity.countAll", query="SELECT COUNT(x) FROM PLFTTipoAllegatoEntity x" )
} )
public class PLFTTipoAllegatoEntity implements Serializable, TranslatableCodifica {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
	@Column(name = "ID", nullable = false)
	private BigDecimal id;
    
    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------
    
    @Column(name="CODICE", nullable=false, length=10)
    private String  codice     ;
    
    @Column(name="DESCRIZIONE", nullable=false, length=255)
    private String     descrizione     ;
    
    @Column(name="ESTENSIONE", nullable=false, length=10)
    private String     estensione     ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INIZIO", nullable=false)
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
    public PLFTTipoAllegatoEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal idTipoAllegato)
	{
		this.id = idTipoAllegato;
	}

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
	

	

   
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    
	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codTipoAllegato)
	{
		this.codice = codTipoAllegato;
	}
	
	
	public String getEstensione()
	{
		return estensione;
	}

	public void setEstensione(String estensione)
	{
		this.estensione = estensione;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public Date getDataInizio()
	{
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public Date getDataFine()
	{
		return dataFine;
	}

	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
	}



	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    

	@Override
	public String toString()
	{
		return "PLFTTipoAllegatoEntity [idTipoAllegato=" + id + ", codTipoAllegato=" + codice + ", descrizione=" + descrizione + ", estensione=" + estensione + ", dataInizio=" + dataInizio
				+ ", dataFine=" + dataFine + "]";
	}


}
