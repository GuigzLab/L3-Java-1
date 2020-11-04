package up.mi.ecoles;

public class Ville {
    private final String name;
    private boolean school = true;

    Ville ( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSchool(boolean school) {
        this.school = school;
    }

    public boolean hasSchool() {
        return school;
    }
}