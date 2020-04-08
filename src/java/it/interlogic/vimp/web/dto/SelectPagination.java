package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class SelectPagination implements Serializable
{
	private static final long serialVersionUID = 1L;

	private boolean more;
	
	public SelectPagination()
	{
	}
	
	public SelectPagination(boolean more)
	{
		this.more = more;
	}

	public boolean isMore()
	{
		return more;
	}


	public void setMore(boolean more)
	{
		this.more = more;
	}

}
