package it.interlogic.vimp.service.exception;

import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;

public class PasswordTemporanyException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	
	private final PLFTUtenteEntity utente;

	public PasswordTemporanyException(PLFTUtenteEntity utente,String message)
	{
		super(message);
		this.utente = utente;
	}
	
	
	public PLFTUtenteEntity getUtente()
	{
		return utente;
	}
}
