package carlos.blanco.mastermind.miw.upm.es;

public class View {
	public Model model;

	public View(Model model) {
		this.model = model;
	}

	public void denSpielerBegruessen() {
		System.out.println("Willkommen zu Mastermind - und los geht's!");
		System.out.println("Die Codel�nge betr�gt " + model.getCodeLaenge() + " und die Anzahl der Farben betr�gt " + model.getAnzahlFarben());
	}

	public void druckeSpielverlauf() {
		System.out.println("Bislang hast du gespielt:");
		for (int i = 1; i <= model.rufeAnzahlVersucheAb(); i++) {
			int versuch = model.rufeVersucheAb(i);
			int bewertung = model.bewerteVersuch(versuch);
			System.out.println(
					"Dein Versuch #" + i + ": " + versuch + " Bewertung: " + (bewertung / 10) + "-" + (bewertung % 10));
		}
	}

	public void spielerZumVersuchAuffordern() {
		System.out.println("Gib bitte deinen Versuch #" + (model.rufeAnzahlVersucheAb() + 1) + " ein: ");
	}

	public void demSpielerGratulieren() {
		if (model.spielGewonnen()) {
			System.out.println("Gl�ckwunsch! - Du hast gewonnen!");
		}
	}

	public boolean spielerBrichtAb(String eingabe) {
		return !eingabe.equals("j");
	}

	public void spielerNachAbbruchFragen() {
		System.out.println("M�chten Sie noch eine Runde spielen? j/n");
	}
}
