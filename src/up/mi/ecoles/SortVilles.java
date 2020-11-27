package up.mi.ecoles;

import java.util.Comparator;

public class SortVilles implements Comparator<Ville> {

    private final Agglomeration agglomeration;

    public SortVilles(Agglomeration agglomeration) {
        this.agglomeration = agglomeration;
    }

    @Override
    public int compare(Ville s1, Ville s2) {
        if (this.agglomeration.getDegree(this.agglomeration.indexOf(s1)) > this.agglomeration.getDegree(this.agglomeration.indexOf(s2))){
            return -1;
        }
        else if (this.agglomeration.getDegree(this.agglomeration.indexOf(s1)) < this.agglomeration.getDegree(this.agglomeration.indexOf(s2))) {
            return 1;
        }

        return 0;
    }

}
