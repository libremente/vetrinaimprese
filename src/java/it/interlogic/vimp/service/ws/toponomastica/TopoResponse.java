package it.interlogic.vimp.service.ws.toponomastica;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class TopoResponse implements Serializable
{

	private static final long serialVersionUID = 1L;

	@SerializedName("Risposta")
	private Risposta risposta;

	public Risposta getRisposta()
	{
		return risposta;
	}

	public void setRisposta(Risposta risposta)
	{
		this.risposta = risposta;
	}

	@Override
	public String toString()
	{
		return risposta.toString();
	}

}
