package up.mi.ecoles;

import up.mi.ecoles.menus.MenuEcoles;
import up.mi.ecoles.menus.MenuManuel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Agglomeration agglomeration = new Agglomeration();
        Scanner sc = new Scanner(System.in);

        boolean isAgglomerationDefined = false;

        while (true) {
            System.out.println("_________________________________________________\n");
            System.out.println("\t    CONSTRUCTIONS D'ECOLES\n");
            System.out.print("\t1 - Resolution Manuelle\n");
            System.out.print("\t2 - Resolution Automatique\n");
            System.out.print("\t3 - Sauvegarder\n");
            System.out.println("\t4 - Fin\n");
            System.out.print("Votre choix : ");

            String request = sc.nextLine();

            switch (request) {
                case "1":
                    if (!isAgglomerationDefined) {
                        if (args.length > 0) {
                            agglomeration.readFromFile(args[0]);
                        } else {
                            MenuManuel menuManuel = new MenuManuel(agglomeration);
                            menuManuel.getMenu();
                        }
                    }
                    isAgglomerationDefined = true;

                    boolean isAgglomerationOk = true;

                    for (int i = 0; i < agglomeration.size(); i++) {
                        if (!agglomeration.checkIfCityHasNearbySchool(i)) {
                            isAgglomerationOk = false;
                        }
                    }

                    if (!isAgglomerationOk) {

                        System.out.println("\n\u001B[31m Attention /!\\ Votre agglomération ne respectait pas les containtes d'accessibilité, ajout d'une école dans chaque ville.\u001B[0m");

                        for (Ville ville : agglomeration) {
                            ville.setSchool(true);
                        }
                    }

                    System.out.println();
                    System.out.println("_________________________________________________\n");
                    System.out.println("\t\t GESTION DES ECOLES\n");

                    MenuEcoles menuEcoles = new MenuEcoles(agglomeration);
                    menuEcoles.getMenu();

                    break;

                case "2":
                    if (!isAgglomerationDefined) {
                        if (args.length > 0) {
                            agglomeration.readFromFile(args[0]);
                        } else {
                            MenuManuel menuManuel = new MenuManuel(agglomeration);
                            menuManuel.getMenu();
                        }
                    }
                    agglomeration.welshPowell();
                    isAgglomerationDefined = true;

                    System.out.print("\n\u001B[32m La construction d'écoles est finalisée, des écoles sont présentes dans les villes suivantes :");
                    for (Ville ville : agglomeration) {
                        if (ville.hasSchool()) {
                            System.out.print(" " + ville.getName());
                        }
                    }
                    System.out.println(".\u001B[0m");

                    break;

                case "3":

                    if (agglomeration.size() > 0) {
                        Scanner save = new Scanner(System.in);
                        System.out.println("Dans quel fichier souhaitez-vous enregistrer l'agglomération ?");
                        String fileName = save.nextLine();
                        System.out.println(fileName);

                        File file = new File(fileName);
                        file.createNewFile();
                        FileWriter fileWriter = new FileWriter(file);

                        for (Ville ville : agglomeration) {
                            fileWriter.write("ville(" + ville.getName() + ").\n");
                        }

                        boolean[][] matrice = agglomeration.getMatrice();

                        for (int i = 0; i < matrice.length; i++) {
                            for (int j = 0; j < matrice.length; j++) {
                                if (matrice[i][j] && i < j) {
                                    fileWriter.write("route(" + agglomeration.get(i).getName() + "," + agglomeration.get(j).getName() + ").\n");
                                }

                            }
                        }

                        for (Ville ville : agglomeration) {
                            if (ville.hasSchool())
                                fileWriter.write("ecole(" + ville.getName() + ").\n");
                        }

                        fileWriter.close();

                        System.out.println("Sauvegarde Effectué");
                    } else {
                        System.out.println("La sauvegarde n'a pas pu être effectuée, l'agglomération est vide.");
                    }

                    break;

                case "4":
                    System.out.println("Programme fini");
                    sc.close();
                    return;

                default:
                    System.out.println("Mauvais numéro\n");
                    break;
            }
        }

    }
}
