package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;
import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;
import it.interlogic.vimp.data.jpa.model.PLFVUtenteEntity;
import it.interlogic.vimp.service.exception.AuthenticationException;
import it.interlogic.vimp.service.exception.PasswordExpiredException;
import it.interlogic.vimp.service.exception.PasswordTemporanyException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IUtenteService
{
	/**
	 * @param id
	 * @return
	 */
	PLFTUtenteEntity findUtente(BigDecimal id);

	/**
	 * @param email
	 * @return
	 */
	PLFTUtenteEntity findUtenteByEmail(String email);

	/**
	 * @param codiceFiscale
	 * @return
	 */
	PLFTUtenteEntity findUtenteByCodiceFiscale(String codiceFiscale);

	/**
	 * @param email
	 * @param passwordStr
	 * @return
	 * @throws AuthenticationException
	 * @throws PasswordExpiredException
	 * @throws PasswordTemporanyException
	 */
	PLFTUtenteEntity login(String email, String passwordStr) throws AuthenticationException, PasswordExpiredException, PasswordTemporanyException;

	/**
	 * @param numPage
	 * @param pageSize
	 * @param ricerca
	 * @return
	 */
	Page<PLFVUtenteEntity> findUtenti(int numPage, int pageSize, String ricerca);

	/**
	 * @param codiceFiscale
	 * @param email
	 * @param idImpresa
	 * @param nome
	 * @param cognome
	 * @return
	 */
	PLFTUtenteEntity accreditaUtente(String codiceFiscale, String email, BigDecimal idImpresa, String nome, String cognome);
	
	/**
	 * @param log
	 */
	void lodAudit(PLFTLogAuditEntity log);

	
	/**
	 * @param name
	 * @return
	 */
	String getParameter(String name);
	
	List<PLFTUtenteEntity> findAllUtenti();
	
	
	PLFTUtenteEntity update(PLFTUtenteEntity utente, BigDecimal idImpresa);
	
	PLFTUtenteEntity deleteUtenteImpresa(PLFTUtenteEntity utente, BigDecimal idImpresa,Date dataCancellazione);
	
}
