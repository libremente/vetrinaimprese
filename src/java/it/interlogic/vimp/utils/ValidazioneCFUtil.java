package it.interlogic.vimp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calcola il Codice Fiscale di una persona fisica dati: il cognome,il nome,il
 * sesso,la data di nascita,il codice comune
 **/

public class ValidazioneCFUtil
{

	/**
	 * Caratteri che corrispondono al mese di nascita (il carattere i-esimo
	 * corrisponde al mese i-esimo)
	 */
	
	private static final String idTipologiaItalia = "000";
	
	private static final char[] mese = new char[] { 'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T' };

	private static final String vocaliAccentate = "àèéìòù";
	private static final String vocaliNormalizzate = "AEEIOU";

	public ValidazioneCFUtil()
	{
	}
	
	public boolean eseguiValidazioneCodiceAlfanumericoItaliano(String codice) {
		if (! validate(codice)) {
			return false;
		}
		
		
		
		char chControlloCalcolato = calcolaControlCrt(codice);
		char chControllo = codice.charAt(15);
		return chControllo == chControlloCalcolato;
	}
	
	public boolean isItaliana(String idTipologia) {
		return idTipologiaItalia.equals(idTipologia);
	}
	
	public String normalizzaCodiceCfItaliano(String codice) {
		return codice.toUpperCase();
	}
	
	public boolean eseguiValidazioneCodiceUndiciCifreItaliano(String codice) {
		Pattern p = Pattern.compile("[0-9]{11}");
		Matcher m = p.matcher(codice);
		if (! m.matches()) {
			return false;
		}
		return soddisfaFormulaDiLuhn(codice); 
	}
	private boolean soddisfaFormulaDiLuhn(String codice) {
		final int cifraControlloPrevista = calcolaCifraControllo(codice, 10);
		final int cifraControllo = estraiCifra(codice, 10);
		return cifraControllo == cifraControlloPrevista;
	}
	
	private int calcolaCifraControllo(String codice, final int limitePosCifre) {
		final int sommaCifrePosPari = sommaCifreInPosizioniAlternate(codice, 1, limitePosCifre);
		final int sommaCifrePosDispari = sommaCifreInPosizioniAlternate(codice, 0, limitePosCifre);
		final int contaCifrePosPariMaggDi5 = quanteCifreInPosAlternateMaggUgualiDi5(codice, 1, limitePosCifre);
		final int T = (sommaCifrePosDispari + 2 * sommaCifrePosPari + contaCifrePosPariMaggDi5) % 10;
		final int cifraControllo = (10 - T) % 10;
		return cifraControllo;
	}
	
	private int quanteCifreInPosAlternateMaggUgualiDi5(String codice, int posPrimaCifra, int posCifraLimite) {
		int conto = 0;
		for (int i = posPrimaCifra; i < posCifraLimite; i += 2) {
			int iCifra = estraiCifra(codice, i);
			if (iCifra > 4) {
				conto++;
			}
		}
		return conto;
	}

	private int estraiCifra(String codice, int pos) {
		char chCifra = codice.charAt(pos);
		int iCifra = Character.digit(chCifra, 10);
		return iCifra;
	}
	
	private int sommaCifreInPosizioniAlternate(String codice,
			final int posPrimaCifra, int posCifraLimite) {
		int somma = 0;
		for (int i = posPrimaCifra; i < posCifraLimite; i += 2) {
			int iCifra = estraiCifra(codice, i);
			somma += iCifra;
		}
		return somma;
	}




	/**
	 * Calcola il Codice Fiscale
	 * 
	 * @param cognome
	 *            cognome della persona (il case è irrilevante)
	 * @param nome
	 *            nome della persona (il case è irrilevate)
	 * @param sesso
	 *            carattere a scelta tra "M" e "F"
	 * @param dataNascita
	 *            data di nascita nel formato 'dd-mm-aaaa' (i separatori non
	 *            devono necessariamente essere '-' !)
	 * @param codComune
	 *            codice del comune per codici fiscali di 4 caratteri.
	 * @return il Codice Fiscale calcolato (caratteri alfabetici maiuscoli)
	 */
	public static String calcolaCodice(String cognome, String nome, String sesso, String dataNascita, String codComune)
	{

		String tmpCodiceFiscale;
		// R.Ru. - 13/12/07 - come da richiesta di Pavese
		if (cognome == null || nome == null || sesso == null || dataNascita == null || codComune == null)
		{
			
		}
		// calcolo del CF (CRT escluso)
		tmpCodiceFiscale = (calcolaCognome(cognome)) + (calcolaNome(nome)) + (calcolaDataNasc(dataNascita, sesso))
				+ codComune.toUpperCase();
		// calcolo del CRT
		tmpCodiceFiscale = tmpCodiceFiscale + (calcolaControlCrt(tmpCodiceFiscale));

		return tmpCodiceFiscale;

	}

	// LM 30/04/10S NAO-3296
	/**
	 * Restituisce il carattere non accentato se quello specificato lo è.
	 * 
	 * @param car
	 *            il carattere da controllare.
	 * @return char il carattere non accentato.
	 */
	private static char replaceCarAccentato(char car)
	{
		char rtn = car;
		int p = vocaliAccentate.toUpperCase().indexOf(String.valueOf(car));
		if (p > -1)
		{
			rtn = vocaliNormalizzate.charAt(p);
		}
		return rtn;
	}

	// LM 30/04/10E NAO-3296
	/**
	 * Calcola i caratteri derivanti dal cognome: - tutte le vocali presenti nel
	 * cognome vengono memorizzate in una variabile - tutte le consonanti
	 * presenti nel cognome vengono memorizzate in una variabile La stringa di
	 * ritorno sarà costituita da:
	 * <ul>
	 * <li>TRE CONSONANTI (le prime tre): nel caso in cui le consonanti sono
	 * almeno 3
	 * <li>DUE CONSONANTI E UNA VOCALE : nel caso in cui le consonanti sono 2 ed
	 * è stata trovata almeno 1 vocale
	 * <li>DUE CONSONANTI E X : nel caso in cui le consonanti sono 2 e non sono
	 * state trovate vocali
	 * <li>UNA CONSUNANTE E DUE VOCALI : nel caso in cui c'è 1 sola consonante e
	 * le vocali sono almeno 2
	 * <li>UNA CONSONANTE,UNA VOCALE E X: nel caso un cui c'è 1 sola consonante
	 * e 1 sola vocale
	 * <li>DUE VOCALI E X nel caso in cui non ci sono consonanti
	 * </ul>
	 * 
	 * @param cognome
	 *            Cognome da cui devono derivare i primi 3 caratteri del CF
	 * @return stringa con i primi 3 caratteri del codice fiscale
	 */
	private static String calcolaCognome(String cognome)
	{
		String tmpText = new String();
		String tmpConsonanti = new String();
		String tmpVocali = new String();

		String cognomeU = cognome.toUpperCase();
		char currChar;
		int lungh = cognomeU.length();
		for (int i = 0; i < lungh; i++)
		{
			// LM 30/04/10S NAO-3296
			// currChar = cognomeU.charAt(i);
			currChar = replaceCarAccentato(cognomeU.charAt(i));
			// LM 30/04/10E NAO-3296
			if (currChar == 'A' || currChar == 'E' || currChar == 'I' || currChar == 'O' || currChar == 'U')
				tmpVocali = tmpVocali + (currChar);
			else if ((currChar != ' ') && (currChar != ',') && (currChar != '-') && (currChar != '\'')
					&& (currChar != '.') && (currChar != '"')) // Ribes RR
																// vengono
																// escluse le
																// virgole Rif
																// Anomalia 6753
				tmpConsonanti = tmpConsonanti + (currChar);

		}
		if (tmpConsonanti.length() > 2)
			tmpText = tmpText + tmpConsonanti.charAt(0) + tmpConsonanti.charAt(1) + tmpConsonanti.charAt(2);
		else if (tmpConsonanti.length() == 2)
		{
			tmpText = tmpText + tmpConsonanti.charAt(0) + tmpConsonanti.charAt(1);

			// nel caso di cognome con sole due consonanti non calcolo
			// esattamente il codice fiscale
			if (tmpVocali.length() > 1 || tmpVocali.length() == 1)
				tmpText = tmpText + tmpVocali.charAt(0);
			else
				tmpText = tmpText + 'X';
		}
		else if (tmpConsonanti.length() == 1)
		{
			tmpText = tmpText + tmpConsonanti.charAt(0);
			// Inizio - R.Ru. - Ribes - 12/12/06
			if (tmpVocali.length() > 1)
			{
				tmpText = tmpText + tmpVocali.charAt(0) + tmpVocali.charAt(1);
			}
			else if (tmpVocali.length() == 1)
			{
				tmpText = tmpText + tmpVocali.charAt(0);
				tmpText = tmpText + 'X';
			}
			else
			{
				tmpText = tmpText + 'X';
				tmpText = tmpText + 'X';
			}
			// Fine - R.Ru. - Ribes - 12/12/06
		}
		else
		{
			// Ribes - ticket 11907
			tmpText = componiSoloVocali(tmpVocali);
		}
		return tmpText;
	}

	/**
	 * Calcola i caratteri derivanti dal nome: - tutte le vocali presenti nel
	 * nome vengono memorizzate in una variabile - tutte le consonanti presenti
	 * nel nome vengono memorizzate in una variabile La stringa di ritorno sarà
	 * costituita da:
	 * <ul>
	 * <li>TRE CONSONANTI (la prima,la terza e la quarta): nel caso in cui le
	 * consonanti sono più di 3
	 * <li>TRE CONSONANTI (la prima,la seconda,la terza) nel caso in cui le
	 * consonanti sono 3
	 * <li>DUE CONSONANTI E UNA VOCALE : nel caso in cui le consonanti sono 2 ed
	 * è stata trovata almeno 1 vocale
	 * <li>DUE CONSONANTI E X : nel caso in cui le consonanti sono 2 e non sono
	 * state trovate vocali
	 * <li>UNA CONSUNANTE E DUE VOCALI : nel caso in cui c'è 1 sola consonante e
	 * le vocali sono almeno 2
	 * <li>UNA CONSONANTE,UNA VOCALE E X: nel caso un cui c'è 1 sola consonante
	 * e 1 sola vocale
	 * <li>DUE VOCALI E X nel caso in cui non ci sono consonanti
	 * </ul>
	 * 
	 * @param nome
	 *            Nome da cui devono derivare i secondi 3 caratteri del CF
	 * @return stringa con i secondi 3 caratteri del codice fiscale
	 */
	private static String calcolaNome(String nome)
	{
		String tmpText = new String();
		String tmpConsonanti = new String();
		String tmpVocali = new String();

		String nomeU = nome.toUpperCase();
		int lungh = nomeU.length();
		char currChar;
		for (int i = 0; i < lungh; i++)
		{
			// LM 30/04/10S NAO-3296
			// currChar = nomeU.charAt(i);
			currChar = replaceCarAccentato(nomeU.charAt(i));
			// LM 30/04/10E NAO-3296
			if (currChar == 'A' || currChar == 'E' || currChar == 'I' || currChar == 'O' || currChar == 'U')
				tmpVocali = tmpVocali + (currChar);
			else if ((currChar != ' ') && (currChar != ',') && (currChar != '-') && (currChar != '\'')
					&& (currChar != '.') && (currChar != '"')) // Ribes RR
																// vengono
																// escluse le
																// virgole Rif
																// Anomalia 6753
				tmpConsonanti = tmpConsonanti + (currChar);
		}
		if (tmpConsonanti.length() > 3)
			tmpText = tmpText + tmpConsonanti.charAt(0) + tmpConsonanti.charAt(2) + tmpConsonanti.charAt(3);
		else if (tmpConsonanti.length() == 3)
			tmpText = tmpText + tmpConsonanti.charAt(0) + tmpConsonanti.charAt(1) + tmpConsonanti.charAt(2);
		else if (tmpConsonanti.length() == 2)
		{
			tmpText = tmpText + tmpConsonanti.charAt(0) + tmpConsonanti.charAt(1);
			// nel caso di nome con due sole consonanti non calcolo esattamente
			// il codice fiscale
			if (tmpVocali.length() > 1 || tmpVocali.length() == 1)
				tmpText = tmpText + tmpVocali.charAt(0);
			else
				tmpText = tmpText + 'X';
		}
		else if (tmpConsonanti.length() == 1)
		{
			tmpText = tmpText + tmpConsonanti.charAt(0);
			// Inizio - R.Ru. - Ribes - 12/12/06
			if (tmpVocali.length() > 1)
			{
				tmpText = tmpText + tmpVocali.charAt(0) + tmpVocali.charAt(1);
			}
			else if (tmpVocali.length() == 1)
			{
				tmpText = tmpText + tmpVocali.charAt(0);
				tmpText = tmpText + 'X';
			}
			else
			{
				tmpText = tmpText + 'X';
				tmpText = tmpText + 'X';
			}
			// Fine - R.Ru. - Ribes - 12/12/06
		}
		else
		{
			// Ribes - ticket 11907
			tmpText = componiSoloVocali(tmpVocali);
		}
		return tmpText;
	}

	/**
	 * Ribes - ticket 11907 come da Decreto Ministeriale del 23/12/1976, n.
	 * 13813 Il cognome è composto da meno di tre lettere: in tal caso si
	 * considereranno, nell'ordine, le eventuali consonanti, le eventuali vocali
	 * e tante X quante ne occorrono per avere tre caratteri (ovviamente, a meno
	 * di cognomi d'una sola lettera - ipotesi abbastanza scartabile - di X ne
	 * occorrerà una sola). Ad esempio, RE diviene REX, IH diviene HIX, etc.
	 * 
	 * @param tmpVocali
	 * @return
	 */
	private static String componiSoloVocali(String tmpVocali)
	{
		String result = "";

		for (int i = 0; i < 3; i++)
		{
			if (i < tmpVocali.length())
			{
				result += tmpVocali.charAt(i);
			}
			else
			{
				result += "X";
			}
		}

		return result;
	}

	/**
	 * Calcola i caratteri derivanti dalla data di nascita e sesso: La stringa
	 * di ritorno sarà costituita da:
	 * <ul>
	 * <li>ANNO : dato dall'ultimo e dal penultimo carattere della data di
	 * nascita
	 * <li>MESE : si memorizza in una variabile il quarto e il quinto carattere
	 * della data di nascita, a seconda del numero trovato si avrà una lettera
	 * dell'alfabeto corrispondente
	 * <li>GIORNO: nel caso in cui il sesso sia 'M' è dato dal primo e secondo
	 * carattere della data di nascita nel caso in cui il sesso sia 'F' è dato dal
	 * numero prima ricavato + 40
	 * </ul>
	 * 
	 * @param dataNascita
	 *            Data da cui devono derivare i caratteri 7 - 12 del CF
	 * @return stringa con i caratteri 7 - 12 del codice fiscale
	 */
	private static String calcolaDataNasc(String dataNascita, String sesso)
	{
		String tmpMese = new String();
		String tmpGiorno = new String();
		String tmpTextGG = new String();
		String tmpText = new String();
		int length = dataNascita.length();

		// calcolo della porzione di codice che descrive la data di nascita a
		// partire
		// da una data nel formato dd-mm-aaaa:
		// Anno: gli ultimi due caratteri
		tmpText = tmpText + dataNascita.charAt(length - 2) + dataNascita.charAt(length - 1);

		// Mese: il terzo e il quarto carattere
		tmpMese = tmpMese + dataNascita.charAt(3) + dataNascita.charAt(4);
		tmpText += mese[((Integer.parseInt(tmpMese)) - 1)];

		// Giorno: i primi due caratteri (il numero deve essere aumentato di 40
		// se il sesso è femminile
		tmpGiorno = tmpGiorno + dataNascita.charAt(0) + dataNascita.charAt(1);
		if (sesso.charAt(0) == 'F')
			tmpTextGG += ((Integer.parseInt(tmpGiorno)) + 40);
		else
			tmpTextGG += tmpGiorno;

		// riempio se necessario con uno zero a sinistra
		if (tmpTextGG.length() == 1)
			tmpText += '0';

		tmpText += tmpTextGG;
		return tmpText;
	}

	/**
	 * Calcola il carattere di controllo: La stringa di ritorno sarà costituita
	 * da una lettera dell'alfabeto ottenuta dai controlli che devono essere
	 * effettuati sul codice fiscale che viene passato. Il calcolo è realizzato
	 * secondo una mappa associativa propria dell'algoritmo di calcolo.
	 * 
	 * @param codFisc
	 *            Codice Fiscale calcolato con cognome,nome,data di nascita e
	 *            codice comune
	 * @return stringa con il carattere di controllo del CF
	 */
	public static char calcolaControlCrt(String codFisc)
	{
		int counter = 0; // serve per accumulare il codice numerico
		int offset = 0;
		// I primi 8 caratteri dispari...
		for (int i = 0; i < 8; i++, offset += 2)
		{
			char tmpChar = codFisc.charAt(offset);

			if (tmpChar == 'A' || tmpChar == '0')
				counter += 1;
			else if (tmpChar == 'B' || tmpChar == '1')
				counter += 0;
			else if (tmpChar == 'C' || tmpChar == '2')
				counter += 5;
			else if (tmpChar == 'D' || tmpChar == '3')
				counter += 7;
			else if (tmpChar == 'E' || tmpChar == '4')
				counter += 9;
			else if (tmpChar == 'F' || tmpChar == '5')
				counter += 13;
			else if (tmpChar == 'G' || tmpChar == '6')
				counter += 15;
			else if (tmpChar == 'H' || tmpChar == '7')
				counter += 17;
			else if (tmpChar == 'I' || tmpChar == '8')
				counter += 19;
			else if (tmpChar == 'J' || tmpChar == '9')
				counter += 21;
			else if (tmpChar == 'K')
				counter += 2;
			else if (tmpChar == 'L')
				counter += 4;
			else if (tmpChar == 'M')
				counter += 18;
			else if (tmpChar == 'N')
				counter += 20;
			else if (tmpChar == 'O')
				counter += 11;
			else if (tmpChar == 'P')
				counter += 3;
			else if (tmpChar == 'Q')
				counter += 6;
			else if (tmpChar == 'R')
				counter += 8;
			else if (tmpChar == 'S')
				counter += 12;
			else if (tmpChar == 'T')
				counter += 14;
			else if (tmpChar == 'U')
				counter += 16;
			else if (tmpChar == 'V')
				counter += 10;
			else if (tmpChar == 'W')
				counter += 22;
			else if (tmpChar == 'X')
				counter += 25;
			else if (tmpChar == 'Y')
				counter += 24;
			else if (tmpChar == 'Z')
				counter += 23;
		}

		// I primi 7 caratteri pari
		offset = 1;
		for (int j = 0; j < 7; j++, offset += 2)
		{
			char tmpChar = codFisc.charAt(offset);
			if (Character.isDigit(tmpChar))
				counter += tmpChar - '0';

			else if (tmpChar == 'A')
				counter += 0;
			else if (tmpChar == 'B')
				counter += 1;
			else if (tmpChar == 'C')
				counter += 2;
			else if (tmpChar == 'D')
				counter += 3;
			else if (tmpChar == 'E')
				counter += 4;
			else if (tmpChar == 'F')
				counter += 5;
			else if (tmpChar == 'G')
				counter += 6;
			else if (tmpChar == 'H')
				counter += 7;
			else if (tmpChar == 'I')
				counter += 8;
			else if (tmpChar == 'J')
				counter += 9;
			else if (tmpChar == 'K')
				counter += 10;
			else if (tmpChar == 'L')
				counter += 11;
			else if (tmpChar == 'M')
				counter += 12;
			else if (tmpChar == 'N')
				counter += 13;
			else if (tmpChar == 'O')
				counter += 14;
			else if (tmpChar == 'P')
				counter += 15;
			else if (tmpChar == 'Q')
				counter += 16;
			else if (tmpChar == 'R')
				counter += 17;
			else if (tmpChar == 'S')
				counter += 18;
			else if (tmpChar == 'T')
				counter += 19;
			else if (tmpChar == 'U')
				counter += 20;
			else if (tmpChar == 'V')
				counter += 21;
			else if (tmpChar == 'W')
				counter += 22;
			else if (tmpChar == 'X')
				counter += 23;
			else if (tmpChar == 'Y')
				counter += 24;
			else if (tmpChar == 'Z')
				counter += 25;
		}
		// il codiceCrt è un numero tra 0 e 25
		int codiceCrt = counter % 26;

		// il codice risultante è il (codiceCrt+1)-esimo carattere dell'alfabeto
		return (char) ('A' + codiceCrt);
	}

	/**
	 * Controlla se il CRT del codice fiscale è corretto
	 * 
	 * @param tmpCodiceFiscale
	 *            Codice Fiscale
	 * @return true se il CRT è corretto rispetto al codice fornito
	 */
	public static boolean validate(String tmpCodiceFiscale)
	{
		if (tmpCodiceFiscale == null || tmpCodiceFiscale.length() != 16)
		{
			// il codice fiscale passato non è valorizzato o contiene un numero
			// diverso di caratteri
			return false;
		}
		char tmpChar = (tmpCodiceFiscale.charAt(15));
		char tmpChar00 = calcolaControlCrt(tmpCodiceFiscale);
		// LM 30/04/10S NAO-3296
		for (int i = 0; i < tmpCodiceFiscale.length(); i++)
		{
			if (vocaliAccentate.toUpperCase().indexOf(String.valueOf(tmpCodiceFiscale.charAt(i))) > -1)
			{
				// il codice fiscale passato contiene una lettera accentata
				// quindi
				// restituisco FALSE
				return false;
			}
		}
		// LM 30/04/10E NAO-3296

		if (!(tmpChar == tmpChar00))
			return false;
		else
			return true;
	}

	/**
	 * Il calcolo della funzione può essere anche realizzato da linea di
	 * comando: java CSI.procedure.gsa.modcds.CFUtil <cognome> <nome> <sesso>
	 * <datanascita> <cod. comune> In questo caso viene stampato a video il
	 * codice calcolato.
	 */
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Codice Fiscale =" + calcolaCodice(args[0], args[1], args[2], args[3], args[4]));
		}
		catch (Exception e)
		{
			System.out.println("Errore! " + e.getMessage());
		}

	}

}