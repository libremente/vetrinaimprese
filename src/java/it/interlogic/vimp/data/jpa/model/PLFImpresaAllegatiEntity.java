package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_IMPRESA_ALLEGATI database table.
 * 
 */
@Entity
@Table(name = "PLF_IMPRESA_ALLEGATI")
@NamedQuery(name = "PLFImpresaAllegatiEntity.findAll", query = "SELECT p FROM PLFImpresaAllegatiEntity p")
public class PLFImpresaAllegatiEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_IMPRESA_ALLEGATI")
	private BigDecimal idImpresaAllegati;

	@Lob
	@Column(name = "ALLEGATO", length = 100000)
	private byte[] allegato;

	@Column(name = "CONTENT_TYPE")
	private String contentType;

	// private String descrizione;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "ID_PLF_IMPRESA")
	private BigDecimal idPlfImpresa;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "PATH_NAME")
	private String pathName;

	// bi-directional many-to-one association to PLFTTipoAllegato
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_ALLEGATO")
	private PLFTTipoAllegatoEntity plfTTipoAllegato;
	
	@OneToOne
	@JoinColumn(name = "ID_IMPRESA_ALLEGATI")
	private PLFImpresaAllegatiTranslationEntity impresaAllegatiTranslation;

	public PLFImpresaAllegatiEntity()
	{
	}

	public BigDecimal getIdImpresaAllegati()
	{
		return this.idImpresaAllegati;
	}

	public void setIdImpresaAllegati(BigDecimal idImpresaAllegati)
	{
		this.idImpresaAllegati = idImpresaAllegati;
	}

	public byte[] getAllegato()
	{
		return allegato;
	}

	public void setAllegato(byte[] allegato)
	{
		this.allegato = allegato;
	}

	public String getContentType()
	{
		return this.contentType;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public BigDecimal getIdPlfImpresa()
	{
		return this.idPlfImpresa;
	}

	public void setIdPlfImpresa(BigDecimal idPlfImpresa)
	{
		this.idPlfImpresa = idPlfImpresa;
	}

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getPathName()
	{
		return this.pathName;
	}

	public void setPathName(String pathName)
	{
		this.pathName = pathName;
	}

	public PLFTTipoAllegatoEntity getPlfTTipoAllegato()
	{
		return this.plfTTipoAllegato;
	}

	public void setPlfTTipoAllegato(PLFTTipoAllegatoEntity plfTTipoAllegato)
	{
		this.plfTTipoAllegato = plfTTipoAllegato;
	}

	public PLFImpresaAllegatiTranslationEntity getImpresaAllegatiTranslation()
	{
		return impresaAllegatiTranslation;
	}

	public void setImpresaAllegatiTranslation(PLFImpresaAllegatiTranslationEntity impresaAllegatiTranslation)
	{
		this.impresaAllegatiTranslation = impresaAllegatiTranslation;
	}
	

}