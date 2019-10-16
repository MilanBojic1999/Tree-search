import Model.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphView {

    private Graph graph;
    private List<Integer>[] nodeMat;
    private List<NodeView> nodes;
    private List<EdgeView> edges;

    public GraphView(Graph graph,int start) {
        this.graph = graph;
        nodeMat=(List<Integer>[]) new ArrayList[graph.V()];
        for(int i=0;i<graph.V();i++)
            nodeMat[i]=new ArrayList<>();
        nodes=new ArrayList<>();
        edges=new ArrayList<>();
        buildGraph(graph,start);

    }

    private void buildGraph(Graph g,int s){
        Queue<Integer> primeQ=new LinkedList<>();
    }
}
