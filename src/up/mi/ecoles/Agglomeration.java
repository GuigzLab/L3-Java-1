package up.mi.ecoles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings({"serial", "unused"})
public class Agglomeration extends ArrayList<Ville> {

    boolean[][] matrice;
    boolean[] ecoles;

    public void displayCity() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
        }
    }

    public void displayCityWithSchool() {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).hasSchool()) {
                System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
            }
        }
    }

    public void displayCityWithoutSchool() {
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).hasSchool()) {
                System.out.println("\t\t   " + (i + 1) + " - " + this.get(i).getName());
            }
        }
    }

    public void initMatrice() {
        matrice = new boolean[this.size()][this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                matrice[i][j] = false;
            }
        }
    }

    public void printMatrix() {
        for (boolean[] booleans : matrice) {
            for (boolean aBoolean : booleans) {
                System.out.print("\t" + aBoolean + " ");
            }
            System.out.println();
        }
    }

    public void setMatrixXY(int x, int y) {
        this.matrice[x][y] = true;
        this.matrice[y][x] = true;
    }

    public void initEcoles() {
        ecoles = new boolean[this.size()];
        Arrays.fill(ecoles, true);
    }

    public boolean[] getCityAdjacency(int cityId) {

        boolean[] adj = new boolean[this.size()];

        System.arraycopy(this.matrice[cityId], 0, adj, 0, this.size());

        return adj;
    }

}

