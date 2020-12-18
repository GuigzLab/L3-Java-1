package up.mi.ecoles.menus;

import up.mi.ecoles.Agglomeration;
import up.mi.ecoles.Ville;

import java.util.Scanner;

public class MenuManuel {
	private final Agglomeration agglomeration;

	public MenuManuel(Agglomeration agglomeration) {
		this.agglomeration = agglomeration;
	}

	/**
	 * Affiche le menu pour la gestion manuel
	 */
	public void getMenu() {

		Scanner scannerVille = new Scanner(System.in);

		while (true) {

			System.out.print("Nom de la ville (laisser vide pour finir) : ");

			String request1 = scannerVille.nextLine();
			if (request1.equals(""))
				break;

			agglomeration.add(new Ville(request1));
		}

		System.out.println("_________________________________________________\n");
		System.out.println("\t\t LES VILLES\n");

		agglomeration.displayCities();

		System.out.println();
		System.out.println("_________________________________________________\n");
		System.out.println("\t\t LA MATRICE\n");

		agglomeration.initMatrix();
		agglomeration.printMatrix();

		System.out.println();
		System.out.println("_________________________________________________\n");
		System.out.println("\t\t CREATION DES ROUTES\n");

		MenuRoutes menuRoutes = new MenuRoutes(agglomeration);
		menuRoutes.getMenu();
	}
}
