package it.interlogic.vimp.service.exception;

public class InformazioneDuplicataException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public InformazioneDuplicataException()
	{
		super("Informazione duplicata");
	}

	public InformazioneDuplicataException(String message)
	{
		super(message);
	}
}
