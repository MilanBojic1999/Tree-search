package Model.Search;

import Model.Edge;
import Model.Graph;

import java.util.*;

public class BidirectionalSearch extends Search{


    public BidirectionalSearch(Graph graph, int s) {
        super(graph, s);
    }

    @Override
    public List<Edge> search(Graph g, int s, int e) {
        boolean[] marked=new boolean[graph.V()];
        int[] edgeToStart=new int[graph.V()];
        int[] edgeToEnd=new int[graph.V()];
        Queue<Integer> startQ=new LinkedList<>();
        Queue<Integer> endQ=new LinkedList<>();
        startQ.add(s);
        endQ.add(e);
        int w,w1;
        while ((!startQ.isEmpty() && !endQ.isEmpty()) && Collections.disjoint(startQ,endQ)){
            int sv=startQ.poll();
            int ev=endQ.poll();

            marked[sv]=true;
            marked[ev]=true;

            for(Edge ed1:g.adj(sv)){
                w=ed1.other(sv);
                if(!marked[w]){
                    edgeToStart[w]=sv;
                    startQ.add(w);
                }
            }

            for(Edge ed2:g.adj(ev)){
                w1=ed2.other(ev);
                if(!marked[w1]){
                    edgeToEnd[w1]=ev;
                    endQ.add(w1);
                }
            }
        }

        startQ.retainAll(endQ);
        if(startQ.isEmpty())
            return null;
        int bridge=startQ.poll();
        System.out.println(bridge);
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
