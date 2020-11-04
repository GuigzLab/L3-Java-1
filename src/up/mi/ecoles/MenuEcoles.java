package up.mi.ecoles;

import java.util.Scanner;

public class MenuEcoles {

    private final Agglomeration agglomeration;

    public MenuEcoles (Agglomeration agglomeration){
        this.agglomeration = agglomeration;
    }

    public void getMenu() {

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.print("\t1 - Ajouter");
            System.out.print("\t2 - Retirer");
            System.out.println("\t3 - Fin\n");
            System.out.print("Votre choix : ");

            String request = sc.nextLine();
            if (request.equals("3")) {
                System.out.println("\nListe des villes possédant une école");
                for (Ville ville : this.agglomeration) {
                    if (ville.hasSchool()) {
                        System.out.println(ville.getName());
                    }
                }
                break;
            }

            switch (request) {
                case "1" -> {
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLE A SELECTIONNER\n");
                    this.agglomeration.displayVille();
                    System.out.println("_________________________________________________\n");

                    Scanner addSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");
                    if (addSchoolSc.hasNextInt()){
                        int cityNumber = addSchoolSc.nextInt();
                        System.out.println();
                        if (cityNumber <= this.agglomeration.size()) {
                            Ville city = this.agglomeration.get(cityNumber - 1);
                            if (!city.hasSchool()){
                                city.setSchool(true);
                                System.out.println("Vous avez bien ajouté une école à " + city.getName() + ".");
                            } else {
                                System.out.println(city.getName() + " possède déjà une école !");
                            }
                        } else {
                            System.out.println("Cette ville n'existe pas.");
                        }
                    } else {
                        System.out.println("Veuillez entrer un numéro de ville.");
                    }
                    System.out.println();


                }
                case "2" -> {
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLE A SELECTIONNER\n");
                    this.agglomeration.displayVille();
                    System.out.println("_________________________________________________\n");

                    Scanner removeSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");
                    if (removeSchoolSc.hasNextInt()){
                        int cityNumber = removeSchoolSc.nextInt();
                        System.out.println();
                        if (cityNumber <= this.agglomeration.size()) {
                            Ville city = this.agglomeration.get(cityNumber - 1);

                            if (!city.hasSchool()) {
                                System.out.println(city.getName() + " ne possède pas d'école !");
                                break;
                            }

                            boolean[] adjacencyList = this.agglomeration.getCityAdjacency(cityNumber - 1);
                            boolean removed = false;
                            for (int i = 0; i < this.agglomeration.size(); i++) {
                                if (city.hasSchool() && this.agglomeration.get(i).hasSchool() && adjacencyList[i]) {
                                    city.setSchool(false);
                                    removed = true;
                                    break;
                                }
                            }
                            if (removed){
                                System.out.println("Vous avez bien supprimé l'école de " + city.getName() + ".");
                            } else {
                                System.out.println("Vous ne pouvez pas supprimer l'école de " + city.getName() + ".");
                            }

                        } else {
                            System.out.println("Cette ville n'existe pas.");
                        }
                    } else {
                        System.out.println("Veuillez entrer un numéro de ville.");
                    }
                    System.out.println();

                }
                default -> System.out.println("Mauvais numéro");
            }

        }

    }

}
