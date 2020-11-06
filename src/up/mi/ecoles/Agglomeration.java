package up.mi.ecoles;

import java.util.ArrayList;

/**
 * Represente une agglomération comme une ArrayList<Ville>
 */
public class Agglomeration extends ArrayList<Ville> {

    /**
     * La matride d'adjacence de l'agglomération
     */
    boolean[][] matrice;

    /**
     * Affiche toutes les villes
     */
    public void displayCities() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
        }
    }

    /**
     * Affiche toutes les villes avec une école
     */
    public void displayCitiesWithSchool() {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).hasSchool()) {
                System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
            }
        }
    }

    /**
     * Affiche toutes les villes sans école
     */
    public void displayCitiesWithoutSchool() {
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).hasSchool()) {
                System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
            }
        }
    }

    /**
     * Initialise la matrice d'adjacence de la taille de l'agglomération, avec toutes les routes à false
     */
    public void initMatrice() {
        matrice = new boolean[this.size()][this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                matrice[i][j] = false;
            }
        }
    }

    /**
     * Affiche la matrice d'adjacence
     */
    public void printMatrix() {
        for (boolean[] booleans : matrice) {
            for (boolean aBoolean : booleans) {
                System.out.print("\t" + aBoolean + " ");
            }
            System.out.println();
        }
    }

    /**
     * Crée une  non orientée entre deux villes x et y
     *
     * @param x Première ville
     * @param y Seconde ville
     */
    public void setMatrixXY(int x, int y) {
        this.matrice[x][y] = true;
        this.matrice[y][x] = true;
    }

    /**
     * Renvoi une liste d'adjacence avec true si la ville i est reliée à la ville cityId et false sinon
     *
     * @param cityId ville dont on souhaite avoir la liste d'adjacence
     * @return une liste d'adjacence sous forme de booléen
     */
    public boolean[] getCityAdjacency(int cityId) {

        boolean[] adj = new boolean[this.size()];

        System.arraycopy(this.matrice[cityId], 0, adj, 0, this.size());

        return adj;
    }

    /**
     * Permet de savoir si une ville a au moins une école à proximité
     *
     * @param cityId ville de référence
     * @return si la ville a une école à proximité
     */
    public boolean checkIfCityHasNearbySchool(int cityId) {

        // Si la ville cityId possède elle-même une école
        if (this.get(cityId).hasSchool()) {
            return true;
        }
        // Si ses l'une de ses villes adjacente possède une école
        boolean[] adjacencyList = this.getCityAdjacency(cityId);
        for (int i = 0; i < this.size(); i++) {
            if (i != cityId && this.get(i).hasSchool() && adjacencyList[i]) {
                return true;
            }
        }
        // Sinon, elle n'a pas d'école environante
        return false;
    }

}

