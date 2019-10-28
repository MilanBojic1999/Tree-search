package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.SymbolGraph;
import View.NodeStates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestFirstSearch extends Search{

    public static final BestFirstSearch INSTANCE=new BestFirstSearch();

    @Override
    public List<Edge> search(SymbolGraph g, int s, int e) {
        List<Comparable> list=new ArrayList<>();
        list.add((Comparable) g.getItem(s));
        NodeControler controler=NodeControler.getInstance();
        controler.setNodeState(s, NodeStates.CHECKING);
        boolean[] marked=new boolean[g.N()];
        int[] edgeTo=new int[g.N()];
        while(!list.isEmpty() && !list.contains(g.getItem(e))){
            Comparable item=list.get(0);
            list.remove(item);
            int v=g.getIndex(item);
            controler.setNodeState(v,NodeStates.CHECKED);
            sleep(400);
            marked[v]=true;
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    list.add((Comparable) g.getItem(w));
                    controler.setNodeState(w,NodeStates.CHECKING);
                    sleep(300);
                    if(w==e)
                        return edgeTo(g,edgeTo,s,e);
                }
            }

            Collections.sort(list,new HeuristicFunction(g.getItem(e)));
        }


        return edgeTo(g,edgeTo,s,e);
    }

    @Override
    protected void walk(int s) {

    }
}
