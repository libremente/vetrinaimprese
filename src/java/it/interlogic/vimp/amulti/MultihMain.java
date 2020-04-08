package it.interlogic.vimp.amulti;

import it.interlogic.vimp.data.jpa.model.PLFTTipoProgrammaEntity;

import java.util.List;

public class MultihMain
{

	public static void main(String[] args)
	{
		System.out.println("INIZIO");

		BatchPersistent persistent = new BatchPersistent();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println("DEFAULT LANGUAGE");
		//LanguageContext.getInstance().setDefaultLanguage();
		List<PLFTTipoProgrammaEntity> list = persistent.getDecodificaService().getTipoProgramma();
		for (PLFTTipoProgrammaEntity t : list)
			System.out.println(t.toSmallString());

		System.out.println("");
		System.out.println("EN LANGUAGE");
		LanguageContext.getInstance().setLanguage("en");
		list = persistent.getDecodificaService().getTipoProgramma();
		for (PLFTTipoProgrammaEntity t : list)
			System.out.println(t.toSmallString());

		System.out.println("");
		System.out.println("DEFAULT LANGUAGE");
		LanguageContext.getInstance().setDefaultLanguage();
		list = persistent.getDecodificaService().getTipoProgramma();
		for (PLFTTipoProgrammaEntity t : list)
			System.out.println(t.toSmallString());
		
		System.out.println("");
		System.out.println("EN LANGUAGE");
		LanguageContext.getInstance().setLanguage("en");
		list = persistent.getDecodificaService().getTipoProgramma();
		for (PLFTTipoProgrammaEntity t : list)
			System.out.println(t.toSmallString());

		System.out.println("FINE");
	}

}
