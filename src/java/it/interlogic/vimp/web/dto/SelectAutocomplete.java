package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.util.List;

public class SelectAutocomplete implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<SelectListItem> results;
	private SelectPagination pagination;

	public SelectAutocomplete()
	{
		this.pagination = new SelectPagination(false);
	}

	public SelectAutocomplete(boolean pagination, List<SelectListItem> results)
	{
		this.pagination = new SelectPagination(pagination);
		this.results = results;
	}

	public List<SelectListItem> getResults()
	{
		return results;
	}

	public void setResults(List<SelectListItem> results)
	{
		this.results = results;
	}

	public SelectPagination getPagination()
	{
		return pagination;
	}

	public void setPagination(SelectPagination pagination)
	{
		this.pagination = pagination;
	}

}
