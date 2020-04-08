package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_LOG database table.
 * 
 */
@Entity
@Table(name = "PLF_S_PARAMETER")
@NamedQuery(name = "PLFParameter.findAll", query = "SELECT p FROM PLFParameter p")
public class PLFParameter implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PARAMETER")
	private BigDecimal idLog;

	@Column(name = "PARAM_NAME")
	private String name;

	@Column(name = "PARAM_VALUE")
	private String value;

	public PLFParameter()
	{
	}

	public BigDecimal getIdLog()
	{
		return idLog;
	}

	public void setIdLog(BigDecimal idLog)
	{
		this.idLog = idLog;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}