package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import Model.SymbolGraph;
import View.NodeStates;

import java.util.*;

public class BidirectionalSearch extends Search{

    public static final BidirectionalSearch INSTANCE=new BidirectionalSearch();

    public BidirectionalSearch(Graph graph, int s) {
        super(graph, s);
    }

    private BidirectionalSearch() {
    }

    @Override
    public List<Edge> search(SymbolGraph g, int s, int e) {
        boolean[] _marked=new boolean[g.V()];
        int[] edgeToStart=new int[g.V()];
        int[] edgeToEnd=new int[g.V()];
        Queue<Integer> startQ=new LinkedList<>();
        Queue<Integer> endQ=new LinkedList<>();
        NodeControler controler=NodeControler.getInstance();

        startQ.add(s);
        endQ.add(e);
        controler.setNodeState(s, NodeStates.CHECKING);
        controler.setNodeState(e,NodeStates.CHECKING);
        sleep(350);
        int w,w1;
        while ((!startQ.isEmpty() && !endQ.isEmpty()) && Collections.disjoint(startQ,endQ)){
                int sv=startQ.poll();
                controler.setNodeState(sv, NodeStates.CHECKED);
                _marked[sv]=true;
                sleep(350);

                for(Edge ed1:g.adj(sv)){
                    w=ed1.other(sv);
                    if(!_marked[w]){
                        edgeToStart[w]=sv;
                        startQ.add(w);
                        controler.setNodeState(w,NodeStates.CHECKING);
                    }
                }

                if(!Collections.disjoint(startQ,endQ)){
                    break;
                }
                int ev=endQ.poll();
                controler.setNodeState(ev,NodeStates.CHECKED);
                _marked[ev]=true;


            for(Edge ed2:g.adj(ev)){
                    w1=ed2.other(ev);
                    if(!_marked[w1]){
                        edgeToEnd[w1]=ev;
                        endQ.add(w1);
                        controler.setNodeState(w1,NodeStates.CHECKING);
                    }
                }
                sleep(400);
        }

        startQ.retainAll(endQ);
        if(startQ.isEmpty())
            return null;
        int bridge=startQ.poll();
        List<Edge> list=new ArrayList<>();
        list.addAll(edgeTo(g,edgeToStart,s,bridge));
        List<Edge> scnd=edgeTo(g,edgeToEnd,e,bridge);
        Collections.reverse(scnd);
        list.addAll(scnd);
        return list;
    }

    @Override
    protected void walk(int s) {
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v=queue.poll();
            marked[v]=true;
            for(Edge edge:graph.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    queue.add(w);
                }
            }
        }
    }
}
