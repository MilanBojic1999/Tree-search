package Model;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Edge>[] edges;
    private int V;
    private int E;

    public Graph(int v) {
        V = v;
        E=0;
        edges=(List<Edge>[]) new List[V];
        for(int i=0;i<V;i++){
            edges[i]=new ArrayList<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge edge){
        int v=edge.ether();
        int w=edge.other(v);
        if(v>V || w>V)
            return;
        edges[v].add(edge);
        edges[w].add(edge);
        E++;
    }

    public List<Edge> adj(int v){
        return edges[v];
    }

    public List<Integer> nodesConnectedTo(int v){
        List<Integer> list=new ArrayList<>();
        for(Edge e:adj(v)){
            list.add(e.other(v));
        }
        return list;
    }

    public List<Edge> edges(){
        List<Edge> list=new ArrayList<>();
        for(int i=0;i<V;i++){
            for(Edge e:adj(i)){
                if(!list.contains(e))
                    list.add(e);
            }
        }
        return list;
    }

    public Edge getEdge(int v,int w){
        for(Edge edge:edges()){
            if(edge.edgeOf(v,w))
                return edge;
        }
        return null;
    }
}
