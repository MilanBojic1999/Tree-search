package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import View.NodeStates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 /**
 * Abstraktna klasa za pretragu
 */
public abstract class Search {

    protected boolean[] marked;
    protected int[] edgeTo;
    protected Graph graph;

    public Search(Graph graph,int s) {
        this.graph = graph;
        marked=new boolean[graph.V()];
        edgeTo=new int[graph.V()];
        walk(s);
        edgeTo[s]=-1;
    }

    public Search(){}

    /**
     *
     * @param g Graf za pretragu
     * @param s početni čvor
     * @param e ciljini čvor
     * @return put između s i e čvora
     */
    public abstract List<Edge> search(Graph g, int s, int e);

    /**
     * @param e čvor
     * @return da li se čvor nalazi u grafu kao i s čvor
     */
    public  boolean connected(int e){
        return marked[e];
    }

    /**
     *
     * @param g graf koji je pretražen
     * @param edg niz veza između čvorova
     * @param s početni čvor
     * @param e ciljni čvor
     * @return put između s i e čvora
     */
    protected List<Edge> edgeTo(Graph g,int[] edg,int s,int e){
        List<Edge> stack=new Stack<>();
        NodeControler nc=NodeControler.getInstance();
        for(int i=e;i!=s;i=edg[i]) {
            Edge edge=g.getEdge(edg[i],i);
            stack.add(edge);
            nc.setEgdeAsPath(edge);
            nc.setNodeState(i, NodeStates.PATH);
            sleep(300);
        }
        nc.setNodeState(s,NodeStates.PATH);
        Collections.reverse(stack);
        return stack;
    }

    /**
     * Rezultat Walk metode
     * @param e čvor koji se traži
     * @return put između s i e metode
     */
    public List<Edge> edgeTo(int e){
        List<Edge> st=new ArrayList<>();
        for(int i=e;edgeTo[i]>0;i=edgeTo[i])
            st.add(graph.getEdge(i,edgeTo[i]));

        return st;
    }

    /**
     * Šetnja po grafu, traži put do svakog čvora iz početnog čvora
     * @param s
     */
    protected abstract void walk(int s);


    protected void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
