package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Stanja.Stanje;
import Model.SymbolGraph;
import View.NodeStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AStarSearch extends Search{
    public static final AStarSearch INSTANCE=new AStarSearch();

    @Override
    public List<Edge> search(SymbolGraph g, int s, int e) {
        boolean[] marked=new boolean[g.N()];
        int[] edgeTo=new int[g.N()];
        double[] dist=new double[g.N()];
        Arrays.fill(marked,false);
        Arrays.fill(dist,Double.MAX_VALUE);
        List<Comparable> list=new ArrayList<>();
        NodeControler controler=NodeControler.getInstance();

        dist[s]=0;
        Comparable end=(Comparable) g.getItem(e);

        Comparable start=(Comparable) g.getItem(s);
        list.add(start);
        controler.setNodeState(s, NodeStates.CHECKING);
        sleep(400);

        while (!list.isEmpty()){
            Comparable item=list.remove(0);
            if(item.equals(end))
                return edgeTo(g,edgeTo,s,e);
            int v=g.getIndex(item);
            controler.setNodeState(v, NodeStates.CHECKED);
            sleep(400);
            marked[v]=true;
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    if(dist[w]>(dist[v]+edge.weight())){
                        dist[w]=dist[v]+edge.weight();
                        edgeTo[w]=v;
                        list.add((Comparable) g.getItem(w));
                        controler.setNodeState(w,NodeStates.CHECKING);
                        sleep(300);
                    }
                }
            }

            Collections.sort(list,(o1, o2) -> {
                if(o1 instanceof Stanje && o2 instanceof Stanje){
                    Stanje st1=(Stanje) o1;
                    Stanje st2=(Stanje) o2;
                    int i1=g.getIndex(st1);
                    int i2=g.getIndex(st2);
                    return (int)((st1.heuristic((Stanje) end)+dist[i1])-(st2.heuristic((Stanje)end)+dist[i2]));
                }

                return (int) (dist[(int)o1]-dist[(int)o2]);
            });
        }


        return null;
    }

    @Override
    protected void walk(int s) {

    }
}
