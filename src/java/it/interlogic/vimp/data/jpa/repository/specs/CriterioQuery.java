package it.interlogic.vimp.data.jpa.repository.specs;

import java.math.BigDecimal;

public class CriterioQuery 
{
	
	public static enum Tipo {EQUAL,NOT_EQUAL,LIKE,BETWEEN,IN_DECIMAL,IN_STRING};
	
	final Object valore;
	
	final Object valore2;   //Usato per il between
	final String nome;
	final String nomeEmbeddedKey;
	final Tipo tipoCriterio;
	
	final Object[] valori;  //Usato per l'IN
	
	
	public CriterioQuery(String nome,String valore,  Tipo tipoCriterio)
	{
		this(null,nome,valore,null,null,tipoCriterio);
	}
	
	public CriterioQuery(String nome,BigDecimal valore,  Tipo tipoCriterio)
	{
		this(null,nome,valore,null,null,tipoCriterio);
	}
	
	public CriterioQuery(String nome,BigDecimal valori[])
	{
		this(null,nome,null,null,valori,Tipo.IN_DECIMAL);
	}
	
	public CriterioQuery(String nomeEmbeddedKey,String nome,BigDecimal valori[])
	{
		this(nomeEmbeddedKey,nome,null,null,valori,Tipo.IN_DECIMAL);
	}
	
	public CriterioQuery(String nome,String valori[])
	{
		this(null,nome,null,null,valori,Tipo.IN_STRING);
	}
	
	public CriterioQuery(String nome, Object valoreDa,Object valoreA, Tipo tipoCriterio) 
	{
		this(null,nome,valoreDa,valoreA,null,tipoCriterio);
	}
	
	public CriterioQuery(String nomeEmbeddedKey,String nome,Object valore,  Object valore2, Object valori[],Tipo tipoCriterio)
	{
		super();
		this.valore = valore;
		this.valore2=valore2;
		this.nomeEmbeddedKey = nomeEmbeddedKey;
		this.nome = nome;
		this.tipoCriterio = tipoCriterio;
		this.valori=valori;
	}
	
	
	

	public Object getValore() {
		return valore;
	}

	public String getNome() {
		return nome;
	}

	public Tipo getTipoCriterio() {
		return tipoCriterio;
	}

}
