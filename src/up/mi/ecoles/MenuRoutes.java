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

            System.out.println("1 - Ajouter une Route / 2 - Quitter");

            String request = sc.nextLine();
            if (request.equals("2")) break;

            switch (request) {
                case "1":
                    System.out.println("Choisir les villes à relier");
                    this.agglomeration.displayVille();
                    Scanner routeSc = new Scanner(System.in);
                    System.out.println("(x,y)");

                    String[] coords = routeSc.nextLine().split(",");
                    this.agglomeration.setMatrixXY(Integer.parseInt(coords[0]) - 1, Integer.parseInt(coords[1]) - 1);

                    this.agglomeration.printMatrix();


                    break;

                default:
                    System.out.println("Mauvais numéro");
                    break;
            }

        }
        sc.close();

    }

}
