package up.mi.ecoles;

import java.util.Objects;

/**
 * Représente une ville
 */
public class Ville {
    /**
     * Le nom de la ville
     */
    private final String name;

    /**
     * Si elle possède une école
     */
    private boolean school = true;

    Ville ( String name) {
        this.name = name;
    }

    /**
     * Permet d'obtenir le nom de la ville
     *
     * @return le nom de la ville
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de fixer si la ville possède une école ou non
     *
     * @param school si la ville possède une école ou non
     */
    public void setSchool(boolean school) {
        this.school = school;
    }

    /**
     * Permet de déterminer si une ville possède une école
     *
     * @return si la ville possède une école
     */
    public boolean hasSchool() {
        return school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return name.equals(ville.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}