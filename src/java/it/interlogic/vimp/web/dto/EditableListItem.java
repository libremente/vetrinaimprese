package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EditableListItem implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static EditableListItem getInstance(BigDecimal value, String text)
	{
		EditableListItem ret = new EditableListItem();
		if (value != null)
			ret.setValue(value.toString());
		ret.setText(text);
		return ret;
	}

	public static EditableListItem getInstance(String value, String text)
	{
		EditableListItem ret = new EditableListItem();
		ret.setValue(value);
		ret.setText(text);
		return ret;
	}

	private String value;
	private String text;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
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
