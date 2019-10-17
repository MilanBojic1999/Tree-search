package Model.Search;

import Contorler.NodeControler;
import Model.Edge;
import Model.Graph;
import View.NodeStates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

    public abstract List<Edge> search(Graph g, int s, int e);
    public  boolean connected(int e){
        return marked[e];
    }

    protected List<Edge> edgeTo(Graph g,int[] edg,int s,int e){
        List<Edge> stack=new Stack<>();
        NodeControler nc=NodeControler.getInstance();
        for(int i=e;i!=s;i=edg[i]) {
            Edge edge=g.getEdge(edg[i],i);
            stack.add(edge);
            nc.setEgdeAsPath(edge);
            nc.setNodeState(i, NodeStates.PATH);
        }
        nc.setNodeState(s,NodeStates.PATH);

        Collections.reverse(stack);
        return stack;
    }


    public List<Edge> edgeTo(int e){
        List<Edge> st=new ArrayList<>();
        for(int i=e;edgeTo[i]>0;i=edgeTo[i])
            st.add(graph.getEdge(i,edgeTo[i]));

        return st;
    }
    protected abstract void walk(int s);
}
