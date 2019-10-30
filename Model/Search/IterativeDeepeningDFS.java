package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import Model.SymbolGraph;
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
    public List<Edge> search(SymbolGraph g, int s, int e) {
        boolean[] marked=new boolean[g.V()];
        int[] edgeTo=new int[g.V()];
        Queue<Integer> queue=new LinkedList<>();
        NodeControler controler=NodeControler.getInstance();
        queue.add(s);
        controler.setNodeState(s, NodeStates.CHECKING);
        sleep(500);
        List<Edge> list;
        while (!queue.isEmpty() && !queue.contains(e)){
            int v=queue.poll();
            marked[v]=true;
            controler.setNodeState(v, NodeStates.CHECKED);
            sleep(500);
            for(Edge edge:g.adj(v)) {
                int w = edge.other(v);
                if(!marked[w]) {
                    System.out.print(w+":");
                    printOfList(queue);
                    queue.add(w);
                    controler.setNodeState(w,NodeStates.CHECKING);
                    edgeTo[w]=v;
                    sleep(400);
                    if(w==e)
                        return edgeTo(g,edgeTo,s,e);
                }
            }

            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    _rDfs(g,queue,marked,edgeTo,w,s,e,1);
                    sleep(500);
//                    if(list!=null)
//                        return list;
                }
            }
        }
        return edgeTo(g,edgeTo,s,e);
    }

    private void  _rDfs(Graph g,Queue<Integer> queue,boolean[] marked,int[] edgeTo,int v,int s,int e,int i){
        if(i==4){
            marked[v]=true;
            queue.add(v);
            NodeControler.getInstance().setNodeState(v,NodeStates.TEST);
            sleep(400);
            return;
        }
        marked[v]=true;
        NodeControler.getInstance().setNodeState(v,NodeStates.CHECKED);
        sleep(500);
        for(Edge edge:g.adj(v)){
            int w=edge.other(v);
            if(!marked[w]){
                edgeTo[w]=v;
                NodeControler.getInstance().setNodeState(w,NodeStates.CHECKING);
                sleep(500);
                if(w==e) {
                    edgeTo[w]=v;
                    queue.add(w);
                    return;
                }
                else
                    _rDfs(g,queue,marked,edgeTo,w,s,e,i+1);
            }
        }
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
