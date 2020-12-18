package up.mi.ecoles;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Represente une agglomération comme une ArrayList<Ville>
 */
public class Agglomeration extends ArrayList<Ville> {

    /**
     * La matride d'adjacence de l'agglomération
     */
    private boolean[][] matrice;

    /**
     * Ajoute des villes, des routes et des écoles depuis un fichier {@param path}
     *
     * @param path chemin vers le fichier
     */
    public void readFromFile(String path) {

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            boolean schoolReset = false;
            while ((line = bufferedReader.readLine()) != null) {
                String cityName, matrixNumbers;
                if (line.startsWith("ville(") && line.endsWith(").") && this.matrice == null) {
                    cityName = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    this.add(new Ville(cityName));
                } else if (line.startsWith("route(") && line.endsWith(").")) {
                    if (this.matrice == null)
                        initMatrix();

                    matrixNumbers = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    String[] splited = matrixNumbers.split(",");
                    if (splited.length == 2) {
                        if (this.contains(new Ville(splited[0])) && this.contains(new Ville(splited[1])))
                            setMatrixXY(this.indexOf(new Ville(splited[0])), this.indexOf(new Ville(splited[1])));
                    }
                } else if (line.startsWith("ecole(") && line.endsWith(").")) {

                    cityName = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    if (this.contains(new Ville(cityName))) {
                        if (!schoolReset) {
                            for (Ville ville : this) {
                                ville.setSchool(false);
                            }
                            schoolReset = true;
                        }

                        this.get(indexOf(new Ville(cityName))).setSchool(true);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
    public void initMatrix() {
        this.matrice = new boolean[this.size()][this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                this.matrice[i][j] = false;
            }
        }
    }

    /**
     * Affiche la matrice d'adjacence
     */
    public void printMatrix() {
        for (boolean[] booleans : this.matrice) {
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

    /**
     * @return le nombre d'écoles de l'agglomération
     */
    public int score() {
        int score = 0;
        for (Ville ville : this) {
            if (ville.hasSchool())
                score += 1;

        }
        return score;
    }

    public void naiveAlgorithm(int k) {
        int i = 0;
        int scoreCourant = this.score();
        Random random = new Random();
        while (i < k) {
            int randomInt = random.nextInt(this.size());
            this.get(randomInt).setSchool(!this.get(randomInt).hasSchool());

            if (this.score() < scoreCourant) {
                i = 0;
                scoreCourant = this.score();
            } else {
                i++;
            }
        }

        for (int j = 0; j < this.size(); j++) {
            if (!this.checkIfCityHasNearbySchool(j))
                this.naiveAlgorithm(k);
        }
    }

    public void welshPowell() {
        // TODO - HashMap avec le numéro de chaque sommet et son degré
        // TODO - Trier cette liste en fonction du degré des sommets
        // TODO - Attribuer au premier sommet (A) de la liste une couleur.
        // TODO - Suivre la liste en attribuant la même couleur au premier sommet (B) qui ne soit pas adjacent à (A).
        // TODO - Suivre (si possible) la liste jusqu'au prochain sommet (C) qui ne soit adjacent ni à A ni à B.
        // TODO - Continuer jusqu'à ce que la liste soit finie.
        // TODO - Prendre une deuxième couleur pour le premier sommet (D) non encore coloré de la liste.
        // TODO - Répéter les opérations 3 à 7.
        // TODO - Continuer jusqu'à avoir coloré tous les sommets.

        List<Ville> villes = new ArrayList<>(this);
        HashMap<Ville, Integer> map = new HashMap<>();

        int nBcolors = 0;

        villes.sort(new SortVilles(this));

        while (!villes.isEmpty()) {
            Ville v = villes.get(0);
            nBcolors++;
            map.put(v, nBcolors);
            villes.remove(v);

            List<Ville> villesCopy = new ArrayList<>(villes);

            for (Ville v2 : villes) {
                if (!map.containsKey(v2)) {
                    if (!this.hasASameColoredNeighbor(this.indexOf(v2), nBcolors, map)) {
                        map.put(v2, nBcolors);
                        villesCopy.remove(v2);
                    }
                }
            }
            villes = villesCopy;
        }

        for (Ville ville: map.keySet()){
            ville.setSchool(map.get(ville) % 2 != 0);
        }

    }

    public int getDegree(int cityId) {
        int degree = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.matrice[cityId][i])
                degree += 1;
        }

        return degree;
    }

    public boolean hasASameColoredNeighbor(int cityId, int colorNumber, HashMap<Ville, Integer> map) {
        boolean[] adjacencyList = this.getCityAdjacency(cityId);
        for (int i = 0; i < adjacencyList.length; i++) {
            if (adjacencyList[i]) {
                if (map.containsKey(this.get(i)) && map.get(this.get(i)) == colorNumber)
                    return true;
            }
        }
        return false;
    }

    public boolean[][] getMatrice() {
        return matrice;
    }
}