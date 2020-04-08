package it.interlogic.vimp.web.tags;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InfoPulsante implements Serializable
{
	public final String label; 


	public final String link;
	public final boolean corrente;

	public InfoPulsante(String label, String link, boolean corrente) {
		super();
		this.label = label;
		this.link = link;
		this.corrente = corrente;
	}

	public String getLabel() {
		return label;
	}

	public String getLink() {
		return link;
	}

	public boolean isCorrente() {
		return corrente;
	}

}
