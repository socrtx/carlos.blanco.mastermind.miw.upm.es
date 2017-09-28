package carlos.blanco.mastermind.miw.upm.es;

import java.util.Scanner;

public class Controller {

	private View view;
	private Model model;

	public static Scanner scan = new Scanner(System.in);

	public Controller(Model model, View view) {
		this.view = view;
		this.model = model;

	}

	public static void main(String[] args) {
		Model model = new Model(4, 6);
		View view = new View(model);
		Controller controller = new Controller(model, view);

		controller.starteSpiel();
	}

	public void starteSpiel() {

		while (true) {
			System.out.println(model.getGeheimcode());
			view.denSpielerBegruessen();
			
			while (!model.spielGewonnen()) {
				view.spielerZumVersuchAuffordern();
				int versuch = scan.nextInt();
				model.speichereNaechstenVersuch(versuch);
				model.bewerteVersuch(versuch);
				view.druckeSpielverlauf();
			}
			
			view.demSpielerGratulieren();

			view.spielerNachAbbruchFragen();
			String eingabeAbbruch = scan.next();
			if (view.spielerBrichtAb(eingabeAbbruch)) {
				break;
			}
			model.spielZuruecksetzen();

		}

	}

}
