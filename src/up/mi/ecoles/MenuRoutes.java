package up.mi.ecoles;

import java.util.Scanner;

public class MenuRoutes {

    private final Agglomeration agglomeration;

    public MenuRoutes(Agglomeration agglomeration) {
        this.agglomeration = agglomeration;
    }

    public Agglomeration getAgglomeration() {
        return agglomeration;
    }

    public void getMenu() {

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.print("\t1 - Ajouter");
            System.out.println("\t2 - Suite\n");
            System.out.print("Votre choix : ");

            String request = sc.nextLine();
            if (request.equals("2")) break;

            switch (request) {
                case "1" -> {
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLES A RELIER\n");
                    this.agglomeration.displayVille();
                    System.out.println("_________________________________________________\n");

                    Scanner routeSc = new Scanner(System.in);
                    System.out.print("Exemple : x,y \nVotre choix : ");
                    String[] coords = routeSc.nextLine().split(",");
                    this.agglomeration.setMatrixXY(Integer.parseInt(coords[0]) - 1, Integer.parseInt(coords[1]) - 1);
                    System.out.println();
                    this.agglomeration.printMatrix();
                    System.out.println();
                }
                default -> System.out.println("Mauvais numéro");
            }

        }

    }

}
