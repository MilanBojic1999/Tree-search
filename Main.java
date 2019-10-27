import Model.Edge;
import Model.Graph;
import Model.GraphBuilder;
import Model.Search.BreadthFirstSearch;
import Model.Search.IterativeDeepeningDFS;
import Model.Search.Search;
import Model.Stanja.Stanje;
import Model.Stanja.ThreeDices;
import Model.SymbolGraph;
import View.App;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /*Stanje poc=new ThreeDices(1,2,3);
        Stanje kraj=new ThreeDices(1,1,1);
        SymbolGraph<Stanje> graph=GraphBuilder.buildGraphStanje(poc,kraj);
        BreadthFirstSearch bfs=new BreadthFirstSearch(graph,graph.getIndex(poc));
        bfs.walk(graph.getIndex(poc));
        for(Edge e:bfs.edgeTo(graph.getIndex(kraj))){
            int v=e.ether();
            int w=e.other(v);
            System.out.print(graph.getItem(v)+"->"+graph.getItem(w)+" ");
        }*/
        Graph graph=GraphBuilder.buildGraph("BinaryTree1");
    }
}
