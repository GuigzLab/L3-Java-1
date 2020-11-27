package up.mi.ecoles;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scannerVille = new Scanner(System.in);
        Agglomeration agglomeration = new Agglomeration();
        if (args.length > 0) {
            agglomeration.readFromFile(args[0]);
            agglomeration.welshPowell();

        } else {
            System.out.println("_________________________________________________\n");
            System.out.println("\t    CONSTRUCTIONS D'ECOLES\n");

            while (true) {

                System.out.print("Nom de la ville (laisser vide pour finir) : ");

                String request = scannerVille.nextLine();
                if (request.equals("")) break;

                agglomeration.add(new Ville(request));
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

        for (int i = 0; i < agglomeration.size(); i++) {
            if (!agglomeration.checkIfCityHasNearbySchool(i)){
                System.out.println("\u001B[31m Attention /!\\ " + agglomeration.get(i).getName() + " n'a pas d'école dans son voisinage\u001B[0m");
            }
        }

        System.out.println();
        System.out.println("_________________________________________________\n");
        System.out.println("\t\t GESTION DES ECOLES\n");

        MenuEcoles menuEcoles = new MenuEcoles(agglomeration);
        menuEcoles.getMenu();


    }
}
