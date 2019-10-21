package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import View.NodeStates;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IterativeDeepeningDFS extends Search {

    public final static IterativeDeepeningDFS INSTANCE=new IterativeDeepeningDFS();

    public IterativeDeepeningDFS(Graph graph, int s) {
        super(graph, s);
    }

    private IterativeDeepeningDFS() {
    }

    @Override
    public List<Edge> search(Graph g, int s, int e) {
        boolean[] marked=new boolean[g.V()];
        int[] edgeTo=new int[g.V()];
        Queue<Integer> queue=new LinkedList<>();
        NodeControler controler=NodeControler.getInstance();
        queue.add(s);
        controler.setNodeState(s, NodeStates.CHECKING);
        sleep(350);
        List<Edge> list;
        while (!queue.isEmpty() && !queue.contains(e)){
            int v=queue.poll();
            marked[v]=true;
            controler.setNodeState(v, NodeStates.CHECKED);
            sleep(400);
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    list=_rDfs(g,queue,marked,edgeTo,w,s,e,1);
                    sleep(400);
                    if(list!=null)
                        return list;
                }
            }
        }
        return edgeTo(g,edgeTo,s,e);
    }

    private List<Edge> _rDfs(Graph g,Queue<Integer> queue,boolean[] marked,int[] edgeTo,int v,int s,int e,int i){
        if(i==2){
            queue.add(v);
            NodeControler.getInstance().setNodeState(v,NodeStates.CHECKING);
            return null;
        }
        marked[v]=true;
        NodeControler.getInstance().setNodeState(v,NodeStates.CHECKED);
        sleep(300);
        for(Edge edge:g.adj(v)){
            int w=edge.other(v);
            if(!marked[w]){
                edgeTo[w]=v;
                NodeControler.getInstance().setNodeState(w,NodeStates.CHECKING);
                sleep(300);
                if(w==e)
                    return edgeTo(g,edgeTo,s,e);
                else
                    return _rDfs(g,queue,marked,edgeTo,w,s,e,i+1);
            }
        }
        return null;
    }

    @Override
    protected void walk(int s) {
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v=queue.poll();
            for(Edge edge:graph.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    rDfs(w,1,queue);
                }
            }
        }
    }

    private void rDfs(int v,int i,Queue<Integer> queue){
        if(i==5){
            queue.add(v);
            return;
        }
        marked[v]=true;
        for(Edge e:graph.adj(v)){
            int w=e.other(v);
            if(!marked[w]){
                edgeTo[w]=v;
                rDfs(v,i+1,queue);
            }
        }
    }
}
