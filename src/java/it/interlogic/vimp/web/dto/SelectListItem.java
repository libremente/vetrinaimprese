package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SelectListItem implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static SelectListItem getInstance(BigDecimal value, String text)
	{
		SelectListItem ret = new SelectListItem();
		if (value != null)
			ret.setId(value.toString());
		ret.setText(text);
		return ret;
	}

	public static SelectListItem getInstance(String id, String text)
	{
		SelectListItem ret = new SelectListItem();
		ret.setId(id);
		ret.setText(text);
		return ret;
	}

	private String id;
	private String text;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

}
