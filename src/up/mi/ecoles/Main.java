package up.mi.ecoles;

import java.awt.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main {

    @SuppressWarnings("resource")
	public static void main(String[] args) {

        Scanner scannerVille = new Scanner(System.in);
        Agglomeration agglomeration = new Agglomeration();

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

        agglomeration.displayVille();
        
        System.out.println();
        System.out.println("_________________________________________________\n");
        System.out.println("\t\t LA MATRICE\n");
        
        agglomeration.initMatrice();
        agglomeration.printMatrix();
        
        System.out.println();
        System.out.println("_________________________________________________\n");
        System.out.println("\t\t CREATION DES ROUTES\n");

        MenuRoutes menuRoutes = new MenuRoutes(agglomeration);
        menuRoutes.getMenu();

        System.out.println();
        System.out.println("_________________________________________________\n");
        System.out.println("\t\t GESTION DES ECOLES\n");

        MenuEcoles menuEcoles = new MenuEcoles(agglomeration);
        menuEcoles.getMenu();


    }
}
