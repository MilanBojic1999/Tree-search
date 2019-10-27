package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import View.*;

public class Graph {

    private List<Edge>[] edges;
    private int V; /** broj čvorova**/
    private int E; /** broj ivica**/

    public Graph(int v) {
        V = v;
        E = 0;
        edges=(List<Edge>[]) new List[V];
        for(int i=0;i<V;i++){
            edges[i]=new ArrayList<>();
        }
    }

    /**
     * @return broj čvorova
     */
    public int V(){
        return V;
    }

    /**
     * @return broj ivica
     */
    public int E(){
        return E;
    }

    /**
     * @param edge se dodaje na graf
     */

    public void addEdge(Edge edge){
        int v=edge.ether();
        int w=edge.other(v);
        if(v>V || w>V)
            return;
        edges[v].add(edge);
        edges[w].add(edge);
        E++;
    }

    protected boolean hasEdgeToVertace(int w){
        for(Edge edge:edges[w]){
            if(edge.isEdgeTo(w))
                return true;
        }
        return false;
    }

    /**
     * @param v je broj čvora(Node)
     * @return ivice(Edge) koje polaze iz v čvora(Node)
     */
    public List<Edge> adj(int v){
        return edges[v];
    }

    /**
     *
     * @param v je broj čvora
     * @return ostake čvorove koje su u direktnoj vezi sa v čvorom
     */

    public List<Integer> nodesConnectedTo(int v){
        List<Integer> list=new ArrayList<>();
        for(Edge e:adj(v)){
            list.add(e.other(v));
        }
        return list;
    }

    /**
     * @return sve ivice(Edge) grafa
     */

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

    /**
     *
     * @param v i w su dva čvora
     * @return ako posoji ivicu između dva čvora
     */

    public Edge getEdge(int v,int w){
        for(Edge edge:edges()){
            if(edge.edgeOf(v,w))
                return edge;
        }
        return null;
    }
}
