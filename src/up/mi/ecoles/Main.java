package up.mi.ecoles;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scannerVille = new Scanner(System.in);
        Agglomeration agglomeration = new Agglomeration();

        while (true) {

            System.out.println("Ajouter une ville (laisser vider pour terminer)");

            String request = scannerVille.nextLine();
            if (request.equals("")) break;

            agglomeration.add(new Ville(request));

        }

        agglomeration.displayVille();
        agglomeration.initMatrice();
        agglomeration.printMatrix();

        MenuRoutes menuRoutes = new MenuRoutes(agglomeration);
        menuRoutes.getMenu();

    }
}
