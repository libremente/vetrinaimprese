package it.interlogic.vimp.service.exception;

public class AccreditamentoException extends Exception
{
	private static final long serialVersionUID = 1L;
	private boolean isWarning;
	private boolean isInfo;

	public AccreditamentoException(String message)
	{
		this(message, false, false);
	}

	public AccreditamentoException(String message, boolean isWarning, boolean isInfo)
	{
		super(message);
		this.isWarning = isWarning;
		this.isInfo = isInfo;
	}

	public boolean isWarning()
	{
		return isWarning;
	}

	public boolean isInfo()
	{
		return isInfo;
	}
}
