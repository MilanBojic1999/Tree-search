package View;

import Model.Graph;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class GraphView {

    private Graph graph;
   // private List<Integer>[] nodeMat;
    private NodeView[] nodes;
    private Set<EdgeView> edges;
    private GraphicsContext gc;

    public GraphView(Graph graph,int start,GraphicsContext gc) {
        this.graph = graph;
        /*nodeMat=(List<Integer>[]) new ArrayList[graph.V()];
        for(int i=0;i<graph.V();i++)
            nodeMat[i]=new ArrayList<>();*/
        nodes=new NodeView[graph.V()];
        edges=new HashSet<>();
        this.gc=gc;
        buildGraph(graph,start);

    }

    private void buildGraph(Graph g,int s){
        List<Integer>[] nodeMat=(List<Integer>[]) new ArrayList[graph.V()];
        for(int i=0;i<graph.V();i++)
            nodeMat[i]=new ArrayList<>();

        Queue<Integer> primeQ=new LinkedList<>();
        Queue<Integer> secundaQ=new LinkedList<>();
        primeQ.add(s);

        Boolean[] marked=new Boolean[g.V()];
        int l=0;

        Arrays.fill(marked,Boolean.FALSE);
        System.out.println(Arrays.asList(marked).contains(Boolean.FALSE));
        while (Arrays.asList(marked).contains(Boolean.FALSE)){
            nodeMat[l++].addAll(primeQ);
            for(int t:primeQ)
                marked[t]=true;
            while (!primeQ.isEmpty()){
                int v=primeQ.poll();
                for(int w:graph.nodesConnectedTo(v))
                    if(!marked[w] && !secundaQ.contains(w)) {
                        secundaQ.add(w);
                        edges.add(new EdgeView(v,w,gc));
                    }
            }
            deliteDuplicates(secundaQ);

            primeQ.addAll(secundaQ);
            secundaQ.clear();
        }

        /*for(List<Integer> list:nodeMat){
            //System.out.print(list.size()+": ");
            for(int w:list)
                System.out.print(w+" ");
            System.out.println();
        }*/

        buildGraphView(nodeMat,l);
    }

    private void buildGraphView(List<Integer>[] nodeMat,int maxLvl) {
        for(int i=0;i<nodeMat.length;i++){
            for(int j=0;j<nodeMat[i].size();j++){
                nodes[nodeMat[i].get(j)]=(new NodeView(gc,nodeMat[i].get(j).toString(),nodeMat[i].get(j),i,j,maxLvl,nodeMat[i].size()));
            }
        }
        for(EdgeView edge:edges){
            edge.setNodes(nodes);
        }
    }


    public void showNodes(){
        for(NodeView nw:nodes)
            nw.show();
    }

    public void showEdges(){
        for(EdgeView edge:edges)
            edge.show();
    }

    public void showAll(){
        showEdges();
        showNodes();
    }

    public NodeView[] getNodes() {
        return nodes;
    }

    public Set<EdgeView> getEdges() {
        return edges;
    }

    private<Item> void deliteDuplicates(Queue<Item> list){
        Set<Item> set=new HashSet<>(list);
        list.clear();
        list.addAll(set);
        /*for(Item w:set)
            System.err.print(w);*/
    }
}
