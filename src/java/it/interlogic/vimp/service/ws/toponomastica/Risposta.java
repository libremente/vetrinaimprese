package it.interlogic.vimp.service.ws.toponomastica;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Risposta implements Serializable
{

	private static final long serialVersionUID = 1L;

	@SerializedName("Strada")
	private List<Strada> strade;

	@SerializedName("Civico")
	private List<Civico> civici;

	public List<Strada> getStrade()
	{
		return strade;
	}

	public void setStrade(List<Strada> strade)
	{
		this.strade = strade;
	}

	public List<Civico> getCivici()
	{
		return civici;
	}

	public void setCivici(List<Civico> civici)
	{
		this.civici = civici;
	}

	@Override
	public String toString()
	{
		return "Risposta [strade=" + strade + ", civici=" + civici + "]";
	}

}
