package up.mi.ecoles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Agglomeration extends ArrayList<Ville> {
    /**
     * TODO Matrice ou liste d'adjacence
     */
    boolean[][] matrice;
    boolean[] ecoles;

    public void displayVille() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(i + 1 + " - " + this.get(i).getName());
        }
    }

    public void initMatrice () {
        matrice = new boolean[this.size()][this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                matrice[i][j] = false;
            }
        }

    }

    public void printMatrix() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
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

}
