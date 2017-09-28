package carlos.blanco.mastermind.miw.upm.es;

public class Model {

	private final int maxVersuche = 1000;

	public int geheimCode;
	private int codeLaenge;
	private int anzahlFarben;
	private int[] versucheSpeicher = new int[maxVersuche];
	private int anzahlVersuche;

	/**
	 * Setzt Codelänge auf 4 und Farbanzahl auf 6 und ruft
	 * generiereZufallscode() auf.
	 * 
	 */
	public Model() {
		codeLaenge = 4;
		anzahlFarben = 6;
		generiereZufallscode(this.codeLaenge, this.anzahlFarben);
	}

	/**
	 * Setzt Codelänge und Farbanzahl auf die übergebenen Werte und ruft
	 * generiereZufallscode() auf.
	 * 
	 * Wenn die übergebene Codelänge ungültig ist wird codeLaenge auf 4 gesetzt.
	 * Wenn die übergebene Farbanzahl ungültig ist wird anzahlFarben auf 6
	 * gesetzt.
	 * 
	 * @param codeLength
	 * @param numberOfColors
	 */
	public Model(int codeLength, int numberOfColors) {
		codeLaenge = codeLength;
		anzahlFarben = numberOfColors;

		if ((codeLength > 9 || codeLength < 1)) {
			codeLaenge = 4;
		}
		if ((numberOfColors > 9 || numberOfColors < 1)) {
			anzahlFarben = 6;
		}
		generiereZufallscode(codeLaenge, anzahlFarben);
	}

	/**
	 * Nimmt einen gültigen Versuch entgegen, speichert diesen ab und gibt true
	 * zurück.
	 * 
	 * Bei ungültiger Eingabe wird false zurückgegeben.
	 * 
	 * @param versuch
	 * @return
	 */
	public boolean speichereNaechstenVersuch(int versuch) {
		if (!versuchIstGueltig(versuch)) {
			return false;
		}
		anzahlVersuche++;
		versucheSpeicher[anzahlVersuche] = versuch;

		return true;
	}

	/**
	 * Gibt Anzahl der bisherigen Versuche zurück.
	 * 
	 * @return
	 */
	public int rufeAnzahlVersucheAb() {
		return anzahlVersuche;
	}

	/**
	 * Gibt den index-ten Versuch zurück.
	 * 
	 * Ungültiger Index gibt -1 zurück.
	 * 
	 * @param index
	 * @return
	 */
	public int rufeVersucheAb(int index) {
		if (index > 0 && index <= anzahlVersuche) {
			return versucheSpeicher[index];
		}
		return -1;
	}

	/**
	 * Ermittelt Anzahl der schwarzen und weißen Stifte für übergebenen Versuch
	 * und gibt diese zurück.
	 *
	 * Der Algorithmus zur Bewertung des Versuches stammt von Prof. Dr. Heiko Körner.
	 * 
	 * @param versuch
	 * @return
	 */
	public int bewerteVersuch(int versuch) {
		if (!versuchIstGueltig(versuch)) {
			return -1;
		}

		int schwarz = 0;
		int weiss = 0;

		int geheimCodeKopie = geheimCode;
		int versuchKopie = versuch;
		int aktuelleZifferVersuch;
		int aktuelleZifferCode;
		int [] gezaehlteZiffernVersuch = new int[10];
		int [] gezaehlteZiffernCode = new int[10];
		
		int bewertung;

		for (int i = 0; i < codeLaenge; i++) {
			aktuelleZifferCode = geheimCodeKopie % 10;
			aktuelleZifferVersuch = versuchKopie % 10;
			geheimCodeKopie /= 10;
			versuchKopie /= 10;
			
			if(aktuelleZifferCode == aktuelleZifferVersuch){
				schwarz++;
				continue;
			}
			
			gezaehlteZiffernCode[aktuelleZifferCode]++;
			gezaehlteZiffernVersuch[aktuelleZifferVersuch]++;
		}
		
		for (int i = 0; i < 10; i++) {
			weiss += (Integer.min(gezaehlteZiffernCode[i], gezaehlteZiffernVersuch[i]));
		}

		bewertung = (10 * schwarz) + weiss;
		return bewertung;
	}

	/**
	 * Prüft ob der zuletzt abgegebene Versuch erfolgreich war.
	 * 
	 * Falls noch kein Versuch stattgefunden hat, soll false zurückgegeben
	 * werden.
	 * 
	 * @return
	 */
	public boolean spielGewonnen() {
		if (anzahlVersuche == 0) {
			return false;
		}
		return bewerteVersuch(rufeVersucheAb(anzahlVersuche)) == (codeLaenge * 10);
	}

	/**
	 * Löscht alle bisherigen Versuche und generiert einen neuen Zufallscode.
	 */
	public void spielZuruecksetzen() {
		anzahlVersuche = 0;
		generiereZufallscode(codeLaenge, anzahlFarben);
	}

	/**
	 * Generiert Zufallscode mit den übergebenen Parametern.
	 * 
	 * @param codeLength
	 * @param numberOfColors
	 */
	private void generiereZufallscode(int codeLength, int numberOfColors) {
		geheimCode = 0;
		for (int i = 1; i <= codeLength; i++) {
			geheimCode *= 10;
			geheimCode += (int) (1 + Math.random() * numberOfColors);
		}
	}

	/**
	 * Überprüft ob eingegebener Versuch gültig ist.
	 * 
	 * @param versuch
	 * @return
	 */
	private boolean versuchIstGueltig(int versuch) {
		int zaehler = 0;
		while (versuch > 0) {
			if (versuch % 10 > anzahlFarben || versuch % 10 < 1) {
				return false;
			}
			versuch /= 10;
			zaehler++;
		}
		return zaehler == codeLaenge;
	}

	/**
	 * Getter-Methode zu Testzwecken
	 * 
	 * @return
	 */
	public int getGeheimcode() {
		return geheimCode;
	}

	public int getCodeLaenge() {
		return codeLaenge;
	}

	public int getAnzahlFarben() {
		return anzahlFarben;
	}

}
