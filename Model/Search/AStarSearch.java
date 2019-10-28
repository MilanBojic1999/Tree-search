package Model.Search;

import Model.Edge;
import Model.SymbolGraph;

import java.util.List;

public class AStarSearch extends Search{
    private static final AStarSearch INSTANCE=new AStarSearch();

    @Override
    public List<Edge> search(SymbolGraph g, int s, int e) {
        return null;
    }

    @Override
    protected void walk(int s) {

    }
}
