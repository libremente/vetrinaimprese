package it.interlogic.vimp.data.jpa.repository.specs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class CriteriQuery
{
	ArrayList<CriterioQuery> criteri = new ArrayList<CriterioQuery>();

	public boolean isEmpty()
	{
		return (criteri == null || criteri.size() == 0);
	}

	public void and(CriterioQuery c)
	{
		criteri.add(c);

	}

	public void addEmbeddedFieldEquals(String nomeEmbeddedKey, String nome, BigDecimal valore)
	{
		if (valore == null)
			return;
		CriterioQuery cq = new CriterioQuery(nomeEmbeddedKey, nome, valore, null, null, CriterioQuery.Tipo.EQUAL);
		criteri.add(cq);
	}

	public void addParametroEqual(String nome, String valore)
	{
		if (StringUtils.isBlank(valore))
			return;
		CriterioQuery cq = new CriterioQuery(nome, valore, CriterioQuery.Tipo.EQUAL);
		criteri.add(cq);
	}

	public void addParametroNotEqual(String nome, String valore)
	{
		if (StringUtils.isBlank(valore))
			return;
		CriterioQuery cq = new CriterioQuery(nome, valore, CriterioQuery.Tipo.NOT_EQUAL);
		criteri.add(cq);
	}

	public void addParametroEqual(String nome, BigDecimal valore)
	{
		if (valore == null)
			return;
		CriterioQuery cq = new CriterioQuery(nome, valore, CriterioQuery.Tipo.EQUAL);
		criteri.add(cq);
	}

	public void addParametroLike(String nome, String valore)
	{
		if (StringUtils.isBlank(valore))
			return;
		CriterioQuery cq = new CriterioQuery(nome, valore, CriterioQuery.Tipo.LIKE);
		criteri.add(cq);
	}

	public void addParametroBetween(String nome, Date intervalloDateDa, Date intervalloDateA)
	{
		if (intervalloDateDa != null && intervalloDateA != null)
		{
			CriterioQuery cq = new CriterioQuery(nome, intervalloDateDa, intervalloDateA, CriterioQuery.Tipo.BETWEEN);
			criteri.add(cq);
		}
		return;
	}

	public void addParametroIn(String nome, BigDecimal valori[])
	{
		if (valori == null)
			return;
		CriterioQuery cq = new CriterioQuery(nome, valori);
		criteri.add(cq);
	}

	public void addEmbeddedFieldIn(String nomeEmbeddedKey, String nome, BigDecimal valori[])
	{
		if (valori == null || valori.length <= 0)
			return;
		CriterioQuery cq = new CriterioQuery(nomeEmbeddedKey,nome, valori);
		criteri.add(cq);
	}
}
