package it.interlogic.vimp.web.dto;

import it.interlogic.vimp.utils.I18nUtility;

import java.io.Serializable;
import java.util.Properties;

public class Colonna implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static Properties colonnaProperties = I18nUtility.getLanguageProperties("columns", "columns_labels");

	public static Colonna getInstance(String label, String nomeDato, boolean defaultChecked, boolean checked)
	{
		Colonna ret = new Colonna();
		ret.setLabel(label);
		ret.setNomeDato(nomeDato);
		ret.setDefaultChecked(defaultChecked);
		ret.setChecked(checked);
		return ret;
	}

	private String label;
	private String nomeDato;
	private boolean defaultChecked;
	private boolean checked;

	public String getLabel()
	{
		colonnaProperties = I18nUtility.getLanguageProperties("columns", "columns_labels");
		//System.out.println(colonnaProperties); // DEBUG
		return Colonna.colonnaProperties.getProperty(label);
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getNomeDato()
	{
		return nomeDato;
	}

	public void setNomeDato(String nomeDato)
	{
		this.nomeDato = nomeDato;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public boolean isDefaultChecked()
	{
		return defaultChecked;
	}

	public void setDefaultChecked(boolean defaultChecked)
	{
		this.defaultChecked = defaultChecked;
	}

}
