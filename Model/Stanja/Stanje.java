package Model.Stanja;

import java.util.List;

public interface Stanje extends Comparable<Stanje>{

    int getMaxCombination();

    List<Stanje> generisiStanja();

    int heuristic(Stanje stanje);

    int heuristic();

    boolean isGoal(Stanje stanje);

}
