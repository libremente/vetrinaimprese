package it.interlogic.vimp.data.jpa.repository.specs;

public class OrdinamentoQuery
{

	public static enum Tipo
	{
		ASC, DESC
	};

	final Tipo tipoOrdinamento;

	final String nome;

	public OrdinamentoQuery(String nome, Tipo tipoOrdinamento)
	{
		super();
		this.nome = nome;
		this.tipoOrdinamento = tipoOrdinamento;
	}

	public String getNome()
	{
		return nome;
	}

	public Tipo getTipoOrdinamento()
	{
		return tipoOrdinamento;
	}

}
