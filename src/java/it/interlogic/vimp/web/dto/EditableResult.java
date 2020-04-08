package it.interlogic.vimp.web.dto;

import java.io.Serializable;

public class EditableResult implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static EditableResult getInstance(String pk, String value)
	{
		EditableResult ret = new EditableResult();
		ret.setPk(pk);
		ret.setValue(value);
		return ret;
	}

	public static EditableResult getInstance(String pk, String value, String[] fields)
	{
		EditableResult ret = new EditableResult();
		ret.setPk(pk);
		ret.setValue(value);
		ret.setFields(fields);
		return ret;
	}

	private String pk;
	private String value;
	private String[] fields;

	public String getPk()
	{
		return pk;
	}

	public void setPk(String pk)
	{
		this.pk = pk;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String[] getFields()
	{
		return fields;
	}

	public void setFields(String[] fields)
	{
		this.fields = fields;
	}
}
