package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import Model.Stanja.Stanje;
import Model.SymbolGraph;
import View.NodeStates;

import java.util.*;

public class HillClimbingSearch extends Search{

    public final static HillClimbingSearch INSTANCE=new HillClimbingSearch();

    @Override
    public List<Edge> search(SymbolGraph g, int s, int e) {
        Stack<Comparable> list=new Stack<>();
        list.add((Comparable) g.getItem(s));
        List<Comparable> help=new ArrayList<>();
        NodeControler controler=NodeControler.getInstance();
        controler.setNodeState(s, NodeStates.CHECKING);
        boolean[] marked=new boolean[g.N()];
        int[] edgeTo=new int[g.N()];
        while (!list.isEmpty() && !list.contains(g.getItem(e))){
            Comparable stanje=list.pop();
            int v=g.getIndex(stanje);
            controler.setNodeState(v, NodeStates.CHECKED);
            sleep(400);
            marked[v]=true;
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    help.add((Comparable) g.getItem(w));
                    controler.setNodeState(w, NodeStates.CHECKING);
                    sleep(300);
                    if(w==e)
                        return edgeTo(g,edgeTo,s,e);
                }
            }
            Collections.sort(help,new HeuristicFunction(g.getItem(e)));
            Collections.reverse(help);
            for(Comparable c:help)
                list.push(c);
            help.clear();
        }

        return edgeTo(g,edgeTo,s,e);
    }

    @Override
    protected void walk(int s) {

    }
}
