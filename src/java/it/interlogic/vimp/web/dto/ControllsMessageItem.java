package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class ControllsMessageItem implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final int ERROR = 0;
	public static final int WARNING = 1;
	public static final int INFO = 2;
	
	
	

	public static ControllsMessageItem getInstance(String message, int typeMessage)
	{
		ControllsMessageItem ret = new ControllsMessageItem();
		ret.setMessage(message);
		ret.setTypeMessage(typeMessage);
		return ret;
	}

	private String message;
	private int typeMessage = ERROR;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public int getTypeMessage()
	{
		return typeMessage;
	}

	public void setTypeMessage(int typeMessage)
	{
		this.typeMessage = typeMessage;
	}

}
