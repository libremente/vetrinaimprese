package it.interlogic.vimp.constants;

import it.interlogic.vimp.amulti.MultilanguageInterceptor;
import it.interlogic.vimp.data.jpa.model.TranslatableCodifica;

/**
 * Costanti che governano i meccanismi di internazionalizzazione applicativa.
 * */
public class I18nConstants
{
	
	private I18nConstants() {}
	
	/**
	 * Codice della lingua di sistema di default, corrisponde alle tabelle senza suffisso.
	 * */
	public final static String DEFAULT_LANGUAGE_CODE = "it_IT";
	public final static String DEFAULT_LANGUAGE_CODE_SMALL = "IT";
	
	/**
	 * Le tabelle in questo elenco devono essere conformi all'interfaccia {@link TranslatableCodifica}
	 * e sono modificabili tramite il backoffice delle codifiche
	 * */
	public static final String[] CODIFICA_TABLES = {
			"PLF_T_AREE_COMPETENZA",
			"PLF_T_ATECO",
			// "PLF_T_ATECO2007",
			"PLF_T_CLASSE_ADDETTI",
			"PLF_T_CLASSE_CAPITALE",
			"PLF_T_CLASSE_PRODUZIONE",
			"PLF_T_CONTROLLI_RICHIESTA",
			"PLF_T_DENOMINAZIONE_SERVIZI",
			"PLF_T_INNOVAZIONE",
			"PLF_T_LOG_MESSAGGI",
			"PLF_T_MACROAREA_SERVIZI",
			"PLF_T_MERCATI",
			"PLF_T_MODALITA_EROGAZIONE_PACCHETTO",
			"PLF_T_MODALITA_EROGAZIONE_SERVIZIO",
			"PLF_T_NATURA_GIURIDICA",
			"PLF_T_ORIGINE_IMPRESA",
			"PLF_T_PREVALENZA",
			"PLF_T_RUOLO_AZIENDALE",
			"PLF_T_SETTORE_IMPRESA",
			"PLF_T_SETTORE_PROGETTI",
			"PLF_T_SETTORE_PROGETTI_PRODOTTI",
			"PLF_T_SETTORE_TECNOLOGIE",
			"PLF_T_SEZIONE_SPECIALE_IMPRESA",
			"PLF_T_SOGG_AMMISSIBILI",
			//"PLF_T_STATO_IMPRESA",
			"PLF_T_STATO_PROGETTO",
			"PLF_T_STATO_RICHIESTA",
			"PLF_T_TIPOLOGIA_PROGETTO",
			"PLF_T_TIPO_ALLEGATO",
			"PLF_T_TIPO_EROGAZIONE_SERVIZIO",
			"PLF_T_TIPO_IMPRESA",
			//"PLF_T_TIPO_INFORMAZIONE",
			"PLF_T_TIPO_NEWS",
			"PLF_T_TIPO_ORGANIZZAZIONE",
			//"PLF_T_TIPO_PROGETTI_PRODOTTI",
			"PLF_T_TIPO_PROGRAMMA",
			"PLF_T_TIPO_SERVIZIO",
			"PLF_T_TAG"
	};
	

	
	public static final String[] CODE_FIELD = {
		"PLF_T_ATECO",
		"PLF_T_CLASSE_ADDETTI",
		"PLF_T_CLASSE_CAPITALE",
		"PLF_T_CLASSE_PRODUZIONE",
		"PLF_T_PREVALENZA",
		"PLF_T_NATURA_GIURIDICA"
	};
	
	
	public static final String[] CODIFICA_TABLES_IMPRESE = {
		"PLF_T_TAG"
	};
	
	
	public static final String[] CODIFICA_TABLES_STAKEHOLDER = {
		"PLF_T_DENOMINAZIONE_SERVIZI",
		"PLF_T_TAG"
	};
	
	
	/**
	 * Le tabelle in questo array vengono processate con la logica multilanguage:
	 * Il {@link MultilanguageInterceptor} sotituirà a runtime il nome della tabella in base
	 * alla lingua di sistema.
	 * */
	public static final String[] MULTILANGUAGE_TABLES = {
			"PLF_IMPRESA_TRANSLATION", 
			"PLF_NEWS_IMPRESA_TRANSLATION", 
			"PLF_IMPRESA_ALLEGATI_TRANSLATION",
			"PLF_PACCHETTO_SERVIZI_TRANSLATION",
			"PLF_PROGETTI_PRODOTTI_TRANSLATION",
			"PLF_PROGETTI_PRODOTTI_ALLEGATI_TRANSLATION",
			"PLF_OPPORTUNITA_TRANSLATION",
			"PLF_SERVIZI_TRANSLATION",
			"PLF_T_AREE_COMPETENZA", 
			"PLF_T_ATECO", 
			"PLF_T_ATECO2007", 
			"PLF_T_CLASSE_ADDETTI", 
			"PLF_T_CLASSE_CAPITALE", 
			"PLF_T_CLASSE_PRODUZIONE",
			"PLF_T_CONTROLLI_RICHIESTA", 
			"PLF_T_DENOMINAZIONE_SERVIZI",
			"PLF_T_INNOVAZIONE", 
			"PLF_T_LOG_MESSAGGI", 
			"PLF_T_MACROAREA_SERVIZI",
			"PLF_T_MERCATI", 
			"PLF_T_MODALITA_EROGAZIONE_PACCHETTO",
			"PLF_T_MODALITA_EROGAZIONE_SERVIZIO",
			"PLF_T_NATURA_GIURIDICA", 
			"PLF_T_ORIGINE_IMPRESA",
			"PLF_T_PREVALENZA", 
			"PLF_T_RUOLO_AZIENDALE", 
			"PLF_T_SETTORE_IMPRESA", 
			"PLF_T_SETTORE_PROGETTI",
			"PLF_T_SETTORE_PROGETTI_PRODOTTI",
			"PLF_T_SETTORE_TECNOLOGIE",
			"PLF_T_SEZIONE_SPECIALE_IMPRESA",
			"PLF_T_SOGG_AMMISSIBILI", 
			"PLF_T_STATO_IMPRESA", 
			"PLF_T_STATO_PROGETTO", 
			"PLF_T_STATO_RICHIESTA", 
			"PLF_T_TIPOLOGIA_PROGETTO", 
			"PLF_T_TIPO_ALLEGATO",
			"PLF_T_TIPO_EROGAZIONE_SERVIZIO",
			"PLF_T_TIPO_IMPRESA", 
			"PLF_T_TIPO_INFORMAZIONE", 
			"PLF_T_TIPO_NEWS", 
			"PLF_T_TIPO_ORGANIZZAZIONE",
			"PLF_T_TIPO_PROGETTI_PRODOTTI",
			"PLF_T_TIPO_PROGRAMMA", 
			"PLF_T_TIPO_SERVIZIO",
			"PLF_T_TAG",
			"PLF_V_INFORMAZIONE", 
			"PLF_V_IMPRESA"
	};

}
