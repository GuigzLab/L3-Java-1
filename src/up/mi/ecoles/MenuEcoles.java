package up.mi.ecoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuEcoles {

    private final Agglomeration agglomeration;

    public MenuEcoles(Agglomeration agglomeration) {
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
                case "1":
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLES SANS ECOLE\n");
                    this.agglomeration.displayCityWithoutSchool();
                    System.out.println("_________________________________________________\n");

                    Scanner addSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");
                    if (addSchoolSc.hasNextInt()) {
                        int cityNumber = addSchoolSc.nextInt();
                        System.out.println();
                        if (cityNumber <= this.agglomeration.size()) {
                            Ville city = this.agglomeration.get(cityNumber - 1);
                            if (!city.hasSchool()) {
                                city.setSchool(true);
                                System.out.println("Vous avez bien ajouté une école à " + city.getName() + ".\n");
                            } else {
                                System.out.println(city.getName() + " possède déjà une école !\n");
                            }
                        } else {
                            System.out.println("Cette ville n'existe pas.\n");
                        }
                    } else {
                        System.out.println("Veuillez entrer un numéro de ville.\n");
                    }
                    break;
                case "2":
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLES AVEC UNE ECOLE\n");
                    this.agglomeration.displayCityWithSchool();
                    System.out.println("_________________________________________________\n");

                    Scanner removeSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");

                    if (removeSchoolSc.hasNextInt()) {
                        int cityNumber = removeSchoolSc.nextInt();
                        System.out.println();
                        if (cityNumber <= this.agglomeration.size()) {
                            Ville city = this.agglomeration.get(cityNumber - 1);

                            if (!city.hasSchool()) {
                                System.out.println(city.getName() + " ne possède pas d'école !\n");
                                break;
                            }

                            boolean[] adjacencyList = this.agglomeration.getCityAdjacency(cityNumber - 1);

                            city.setSchool(false);
                            ArrayList<Boolean> schools = new ArrayList<Boolean>();
                            ArrayList<Integer> cities = new ArrayList<Integer>();

                            for (int i = 0; i < this.agglomeration.size(); i++) {
                                if (adjacencyList[i]){
                                    if (!this.agglomeration.checkIfCityHasNearbySchool(i)){
                                        schools.add(false);
                                        cities.add(i);
                                    } else {
                                        schools.add(true);
                                    }
                                }
                            }

                            if (!schools.contains(false)) {
                                System.out.println("Vous avez bien supprimé l'école de " + city.getName() + ".\n");
                            } else {
                                city.setSchool(true);
                                System.out.println("Vous ne pouvez pas supprimer l'école de " + city.getName() + ".");
                                System.out.println("Liste des villes qui se retrouveraient sans école dans le voisinage si vous supprimiez l'école de " + city.getName());
                                cities.forEach((i) -> {
                                    System.out.println(this.agglomeration.get(i).getName());
                                });
                                System.out.println();
                            }

                        } else {
                            System.out.println("Cette ville n'existe pas.\n");
                        }
                    } else {
                        System.out.println("Veuillez entrer un numéro de ville valide.\n");
                    }
                    break;

                default:
                    System.out.println("Mauvais numéro\n");
                    break;
            }

        }

    }

}
