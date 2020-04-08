package it.interlogic.vimp.data.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<E> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int number;
	private int totalPages;
	private int totalElements;
	private List<E> content = new ArrayList<E>();

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public int getTotalElements()
	{
		return totalElements;
	}

	public void setTotalElements(int totalElements)
	{
		this.totalElements = totalElements;
	}

	public List<E> getContent()
	{
		return content;
	}

	public void setContent(List<E> content)
	{
		this.content = content;
	}

}
