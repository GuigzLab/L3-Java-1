package up.mi.ecoles.menus;

import up.mi.ecoles.Agglomeration;
import up.mi.ecoles.Ville;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represente le menu pour la gestion des écoles
 */
public class MenuEcoles {

    /**
     * L'agglomération dont on souhaite gérer les écoles
     */
    private final Agglomeration agglomeration;

    public MenuEcoles(Agglomeration agglomeration) {
        this.agglomeration = agglomeration;
    }

    /**
     * Affiche le menu pour la gestion des écoles
     */
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
                // Ajout d'écoles
                case "1":
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLES SANS ECOLE\n");
                    this.agglomeration.displayCitiesWithoutSchool();
                    System.out.println("_________________________________________________\n");

                    Scanner addSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");
                    // Si l'utilisateur rentre un entier
                    if (addSchoolSc.hasNextInt()) {
                        int cityNumber = addSchoolSc.nextInt();
                        System.out.println();
                        // Si cet entier correspond bien à une ville
                        if (cityNumber <= this.agglomeration.size() && cityNumber > 0) {
                            Ville city = this.agglomeration.get(cityNumber - 1);
                            // Si cette ville n'a pas déjà une école
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
                // Suppression d'écoles
                case "2":
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\tVILLES AVEC UNE ECOLE\n");
                    this.agglomeration.displayCitiesWithSchool();
                    System.out.println("_________________________________________________\n");

                    Scanner removeSchoolSc = new Scanner(System.in);
                    System.out.print("Votre choix : ");

                    // Si l'utilisateur rentre un entier
                    if (removeSchoolSc.hasNextInt()) {
                        int cityNumber = removeSchoolSc.nextInt();
                        System.out.println();
                        // Si cet entier correspond bien à une ville
                        if (cityNumber <= this.agglomeration.size()) {
                            Ville city = this.agglomeration.get(cityNumber - 1);

                            // Si la ville n'a pas d'école à retirer, on s'arrête là
                            if (!city.hasSchool()) {
                                System.out.println(city.getName() + " ne possède pas d'école !\n");
                                break;
                            }

                            // Sinon, on récupère la liste des villes adjacentes à celle dont ou souhaite retirer la ville
                            boolean[] adjacencyList = this.agglomeration.getCityAdjacency(cityNumber - 1);

                            // On va vérifier si, en retirant cette école, les villes adjacentes ont toujours une école à proximité
                            city.setSchool(false);
                            boolean isOk = true;
                            ArrayList<Integer> cities = new ArrayList<>();

                            // Pour chaque ville
                            for (int i = 0; i < this.agglomeration.size(); i++) {
                                // Si elle est adjacente et si elle n'a plus d'école dans son voisinage
                                if (adjacencyList[i] && !this.agglomeration.checkIfCityHasNearbySchool(i)) {
                                    isOk = false;
                                    // On récupère les numéros des villes qui posent problème
                                    cities.add(i);
                                }
                            }

                            // Si tout va bien
                            if (isOk) {
                                System.out.println("Vous avez bien supprimé l'école de " + city.getName() + ".\n");
                            } else {
                                // On remet l'école.
                                city.setSchool(true);
                                System.out.println("Vous ne pouvez pas supprimer l'école de " + city.getName() + ".");
                                System.out.println("Liste des villes qui se retrouveraient sans école dans le voisinage si vous supprimiez l'école de " + city.getName());
                                // On affiche les villes qui n'auraient pas d'école.
                                cities.forEach((i) -> System.out.println(this.agglomeration.get(i).getName()));
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
