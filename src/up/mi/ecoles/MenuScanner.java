package up.mi.ecoles;

public class MenuScanner {
    public String name;
    public String menuClass;
    public String[] actions;

    public MenuScanner(String name, String menuClass, String[] actions) {
        this.name = name;
        this.menuClass = menuClass;
        this.actions = actions;
    }
}
