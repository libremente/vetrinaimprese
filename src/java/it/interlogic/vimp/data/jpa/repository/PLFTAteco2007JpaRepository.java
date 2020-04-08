package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTAteco2007Entity;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFTAteco2007JpaRepository extends PagingAndSortingRepository<PLFTAteco2007Entity, BigDecimal>
{

	// @Query(value =
	// "SELECT * from PLF_T_ATECO2007 tipo where tipo.DATA_FINE IS NULL",
	// nativeQuery = true)
	// public abstract List<PLFTAteco2007Entity> findAteco2007();
	//
	// @Query(value =
	// "SELECT tipo from PLFTAteco2007Entity tipo where tipo.codice = :codAteco2007")
	// public abstract List<PLFTAteco2007Entity>
	// findAteco2007PerCodice(@Param("codAteco2007")String codAteco2007);
	//
	//
	// @Query(value =
	// "SELECT tipo from PLFTAteco2007Entity tipo where tipo.descrizione like :ricerca")
	// public abstract List<PLFTAteco2007Entity>
	// getAteco2007PerDescrizione(@Param("ricerca")String ricerca);

}
