package it.interlogic.vimp.batch;

import java.text.SimpleDateFormat;

public interface Constants
{

	public static final int EXIT_OK = 0;
	public static final int EXIT_KO = 1;

	public static final String DESC_FONTE_ACCREDITAMENTO = "ACCREDITAMENTO";

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	// ========================================================================================
	// INIZIALIZZATORE
	// ========================================================================================
	public static final String INI_DESC_FONTE = "INIZIALIZZAZIONE";
	public static final String PMI_DESC_FONTE = "IMPORT_PMI";
	public static final String STARTUP_DESC_FONTE = "IMPORT_STARTUP";
	public static final int PMI_SHEET_DATA_NUMBER = 1;
	public static final int STARTUP_SHEET_DATA_NUMBER = 1;

	// ========================================================================================
	// PMI
	// =======================================================================================
	public static final int PMI_DESC_IMPRESA = 0;
	public static final int PMI_NATURA_GIURIDICA = 1;
	public static final int PMI_COD_FISCALE_PARTITA_IVA = 2;
	public static final int PMI_PROVINCIA = 3;
	public static final int PMI_COMUNE = 4;

	public static final int PMI_DATA_ISCRIZIONE_SEZIONE_SPECIALE = 5;
	public static final int PMI_DATA_ISCRIZIONE_REGISTRO_IMPRESE = 6;
	public static final int PMI_DATA_INIZIO_EFFETTIVA_ATTIVITA = 7;

	public static final int PMI_ATECO = 8;
	public static final int PMI_ID_PLF_T_SETTORE_IMPRESA = 9;

	public static final int PMI_ID_PLF_T_CLASSE_PRODUZIONE = 11;
	public static final int PMI_ID_PLF_T_CLASSE_ADDETTI = 12;

	public static final int PMI_ID_PLF_T_CLASSE_CAPITALE = 13;

	public static final int PMI_SITO_INTERNET = 14;

	public static final int PMI_PRIMO_REQUISITO_PMI = 15;
	public static final int PMI_SECONDO_REQUISITO_PMI = 16;
	public static final int PMI_TERZO_REQUISITO_PMI = 17;

	public static final int PMI_ID_PLF_T_PREVALENZA_FEMMINILE = 19;
	public static final int PMI_ID_PLF_T_PREVALENZA_GIOVANILE = 20;
	public static final int PMI_ID_PLF_T_PREVALENZA_STRANIERA = 21;

	// ========================================================================================
	// STARTUP
	// =======================================================================================
	public static final int STARTUP_DESC_IMPRESA = 0;
	public static final int STARTUP_NATURA_GIURIDICA = 1;
	public static final int STARTUP_COD_FISCALE_PARTITA_IVA = 2;
	public static final int STARTUP_PROVINCIA = 3;
	public static final int STARTUP_COMUNE = 4;

	public static final int STARTUP_DATA_ISCRIZIONE_SEZIONE_SPECIALE = 5;
	public static final int STARTUP_DATA_ISCRIZIONE_REGISTRO_IMPRESE = 6;
	public static final int STARTUP_DATA_INIZIO_EFFETTIVA_ATTIVITA = 7;

	public static final int STARTUP_ATECO = 8;
	public static final int STARTUP_ID_PLF_T_SETTORE_IMPRESA = 9;

	public static final int STARTUP_ID_PLF_T_CLASSE_PRODUZIONE = 11;
	public static final int STARTUP_ID_PLF_T_CLASSE_ADDETTI = 12;
	public static final int STARTUP_IMPRESA_VOCAZIONE_SOCIALE = 13;
	public static final int STARTUP_IMPRESA_ALTO_VALORE_TECNOLOGICO = 14;
	
	public static final int STARTUP_ID_PLF_T_CLASSE_CAPITALE = 15;
	public static final int STARTUP_SITO_INTERNET = 16;

	public static final int STARTUP_PRIMO_REQUISITO_PMI = 17;
	public static final int STARTUP_SECONDO_REQUISITO_PMI = 18;
	public static final int STARTUP_TERZO_REQUISITO_PMI = 19;

	public static final int STARTUP_ID_PLF_T_PREVALENZA_FEMMINILE = 21;
	public static final int STARTUP_ID_PLF_T_PREVALENZA_GIOVANILE = 22;
	public static final int STARTUP_ID_PLF_T_PREVALENZA_STRANIERA = 23;

	// ========================================================================================
	// INIZIALIZZATORE SERVIZI
	// ========================================================================================

	public static final int INI_SHEET_DATA_NUMBER_SERVIZI = 2;

	public static final int INI_SERVIZI_CF_IMPRESA = 1;
	public static final int INI_SERVIZI_TITOLO = 2;
	public static final int INI_SERVIZI_DESCRIZIONE = 3;
	public static final int INI_SERVIZI_TIPO = 4;
	public static final int INI_SERVIZI_AREE = 5;

	// ========================================================================================
	// INIZIALIZZATORE PROGETTI
	// ========================================================================================

	public static final int INI_SHEET_DATA_NUMBER_PROGETTI = 3;

	public static final int INI_PROGETTO_CF_IMPRESA = 1;
	public static final int INI_PROGETTO_TIPO_IMPRESA = 2;
	public static final int INI_PROGETTO_TIPO = 3;
	public static final int INI_PROGETTO_NOME = 4;
	public static final int INI_PROGETTO_DESCRIZIONE_PRODOTTO = 5;
	public static final int INI_PROGETTO_DESCRIZIONE_PROGETTO = 6;
	public static final int INI_PROGETTO_TIPOLOGIA = 7;
	public static final int INI_PROGETTO_FINALITA = 8;
	public static final int INI_PROGETTO_STATO = 9;
	public static final int INI_PROGETTO_SETTORE = 10;
	public static final int INI_PROGETTO_RISULTATI = 11;
	public static final int INI_PROGETTO_DURATA = 12;
	public static final int INI_PROGETTO_SCADENZA = 13;
	public static final int INI_PROGETTO_CONTATTI = 14;
	public static final int INI_PROGETTO_LINK = 15;
	public static final int INI_PROGETTO_TAG = 16;
	public static final int INI_PROGETTO_FONTE = 17;
	public static final int INI_PROGETTO_ORIGINE = 18;
	public static final int INI_PROGETTO_COLLABORAZIONI = 19;
	public static final int INI_PROGETTO_PARTNER = 20;
	public static final int INI_PROGETTO_PARTNER_ALTRO = 21;

	// ========================================================================================
	// INCUBATORI
	// ========================================================================================
	public static final String INCUBATORI_DESC_FONTE = "IMPORT_INCUBATORI";

}