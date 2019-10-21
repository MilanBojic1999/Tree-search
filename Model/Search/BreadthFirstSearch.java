package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import View.NodeStates;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends Search {

    public static final BreadthFirstSearch INSTANCE=new BreadthFirstSearch();

    public BreadthFirstSearch() {
    }

    public BreadthFirstSearch(Graph graph,int s) {
        super(graph,s);
    }

    @Override
    public List<Edge> search(Graph g, int s, int e) {
        boolean[] _marked=new boolean[g.V()];
        int[] _edgeTo=new int[g.V()];
        NodeControler controler=NodeControler.getInstance();


        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        controler.setNodeState(s, NodeStates.CHECKING);
        sleep(300);
        while (!queue.isEmpty() && !queue.contains(e)){
            int v=queue.poll();
            controler.setNodeState(v,NodeStates.CHECKED);
            _marked[v]=true;
            sleep(400);
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!_marked[w]){
                    _edgeTo[w]=v;
                    queue.add(w);
                    controler.setNodeState(w,NodeStates.CHECKING);
                    sleep(400);
                }
            }
        }

        return edgeTo(g,_edgeTo,s,e);
    }

    @Override
    public void walk(int s) {
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
