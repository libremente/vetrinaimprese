package it.interlogic.vimp.data.jpa.repository.specs;

import it.interlogic.vimp.data.jpa.repository.specs.CriterioQuery.Tipo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class QueryBuilder
{
	
	
	public static <T> Specification<T> buildQuery(final CriteriQuery criteri,final List<OrdinamentoQuery> ordinamenti, final Class<T> entityClass)
	{
		return new Specification<T>()
		{
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb)
			{
				ArrayList<CriterioQuery> elencoCriteri = criteri.criteri;
				ArrayList<Predicate> elencoPredicati = new ArrayList<Predicate>();
				for (CriterioQuery criterio : elencoCriteri)
				{
					if (criterio.nomeEmbeddedKey != null && criterio.nomeEmbeddedKey.trim().length() > 0)
					{
						if (criterio.valori != null && criterio.valori instanceof BigDecimal[])
						{
							elencoPredicati.add( root.get(criterio.nomeEmbeddedKey).<BigDecimal> get("idTipoInformazione").in(criterio.valori));
						}
						else elencoPredicati.add(cb.equal(root.get(criterio.nomeEmbeddedKey).<BigDecimal> get("idTipoInformazione"),  (BigDecimal) criterio.valore));
					}
					else
						elencoPredicati.add(buildPredicate(criterio, entityClass, root, criteriaQuery, cb));
				}

				if (elencoPredicati.size() == 0)
					return null;
				if (elencoPredicati.size() == 1)
					return elencoPredicati.get(0);
				
				
				if (ordinamenti != null && ordinamenti.size()>0)
				{
					for (OrdinamentoQuery ordinamento:  ordinamenti)
					{
						if (OrdinamentoQuery.Tipo.ASC == ordinamento.tipoOrdinamento)
							criteriaQuery.orderBy(cb.asc(root.get(ordinamento.nome)));
						else if (OrdinamentoQuery.Tipo.DESC == ordinamento.tipoOrdinamento)
							criteriaQuery.orderBy(cb.desc(root.get(ordinamento.nome)));
					}
				}

				return cb.and(elencoPredicati.toArray(new Predicate[0]));

			}
		};
	}

	public static <T> Specification<T> buildQuery(final CriteriQuery criteri, final Class<T> entityClass)
	{
		return new Specification<T>()
		{
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb)
			{
				ArrayList<CriterioQuery> elencoCriteri = criteri.criteri;
				ArrayList<Predicate> elencoPredicati = new ArrayList<Predicate>();
				for (CriterioQuery criterio : elencoCriteri)
				{
					if (criterio.nomeEmbeddedKey != null && criterio.nomeEmbeddedKey.trim().length() > 0)
					{
						if (criterio.valori != null && criterio.valori instanceof BigDecimal[])
						{
							elencoPredicati.add( root.get(criterio.nomeEmbeddedKey).<BigDecimal> get("idTipoInformazione").in(criterio.valori));
						}
						else elencoPredicati.add(cb.equal(root.get(criterio.nomeEmbeddedKey).<BigDecimal> get("idTipoInformazione"),  (BigDecimal) criterio.valore));
					}
					else
						elencoPredicati.add(buildPredicate(criterio, entityClass, root, criteriaQuery, cb));
				}

				if (elencoPredicati.size() == 0)
					return null;
				if (elencoPredicati.size() == 1)
					return elencoPredicati.get(0);

				return cb.and(elencoPredicati.toArray(new Predicate[0]));

			}
		};
	}

	private static <T> Predicate buildPredicate(CriterioQuery criterio, Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb)
	{
		Tipo tipo = criterio.tipoCriterio;
		switch (tipo)
		{
			case EQUAL:
				if (criterio.valore instanceof BigDecimal)
				{
					return buildEqualPredicateNumeric(entityClass, root, criteriaQuery, cb, criterio.nome, (BigDecimal) criterio.valore);
				}
				return buildEqualPredicateString(entityClass, root, criteriaQuery, cb, criterio.nome, (String) criterio.valore);
				
			case NOT_EQUAL:
				if (criterio.valore instanceof BigDecimal)
				{
					return buildNotEqualPredicateNumeric(entityClass, root, criteriaQuery, cb, criterio.nome, (BigDecimal) criterio.valore);
				}
				return buildNotEqualPredicateString(entityClass, root, criteriaQuery, cb, criterio.nome, (String) criterio.valore);
			case LIKE:
				return buildLikePredicate(entityClass, root, criteriaQuery, cb, criterio.nome, (String) criterio.valore);
			case BETWEEN:
				return builDateBetween(entityClass, root, criteriaQuery, cb, criterio.nome, (Date) criterio.valore, (Date) criterio.valore2);
			case IN_DECIMAL:
				return buildInDecimal(entityClass, root, criteriaQuery, cb, criterio.nome, criterio.valori);
			case IN_STRING:
				return buildInString(entityClass, root, criteriaQuery, cb, criterio.nome, criterio.valori);
			default:
				throw new RuntimeException("Tipo criterio di ricerca non supportato " + tipo.toString());
		}
	}

	private static <T> Predicate buildEqualPredicateString(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, String valore)
	{
		return cb.equal(root.<String> get(nomeColonna), valore);
	}
	
	private static <T> Predicate buildNotEqualPredicateString(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, String valore)
	{
		return cb.notEqual(root.<String> get(nomeColonna), valore);
	}

	private static <T> Predicate buildEqualPredicateNumeric(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna,
			BigDecimal valore)
	{
		return cb.equal(root.<BigDecimal> get(nomeColonna), valore);
	}
	
	private static <T> Predicate buildNotEqualPredicateNumeric(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna,
			BigDecimal valore)
	{
		return cb.notEqual(root.<BigDecimal> get(nomeColonna), valore);
	}

	private static <T> Predicate buildLikePredicate(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, String valore)
	{
		return cb.like(cb.upper(root.<String> get(nomeColonna)), valore.toUpperCase());
	}

	private static <T> Predicate builDateBetween(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, Date dataInizio,
			Date dataFine)
	{
		return cb.between(root.<Date> get(nomeColonna), dataInizio, dataFine);
	}

	private static <T> Predicate buildInDecimal(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, Object valori[])
	{
		return root.<BigDecimal> get(nomeColonna).in(valori);
	}

	private static <T> Predicate buildInString(Class<T> entityClass, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb, String nomeColonna, Object valori[])
	{
		return root.<String> get(nomeColonna).in(valori);
	}

}
