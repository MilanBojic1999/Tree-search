package Model.Search;

import Model.Stanja.Stanje;

import java.util.Comparator;
import java.util.Stack;

public class HeuristicFunction implements Comparator {

    private Object endGoal;

    public HeuristicFunction(Object endGoal) {
        this.endGoal = endGoal;
    }

    <Item> double heuristic(Item curr, Item end){
        if(curr instanceof Stanje){
            return ((((Stanje) curr).heuristic((Stanje)end)));
        }
        return 1;
    }


    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Stanje && o2 instanceof Stanje){
            return ((((Stanje) o1).heuristic((Stanje)endGoal))-(((Stanje) o2).heuristic((Stanje)endGoal)));
        }
        return 1;
    }
}
