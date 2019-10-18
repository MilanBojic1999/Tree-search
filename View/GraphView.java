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

    /**
     * vizulizacija datog grafa
     * @param graph graf koji se prikazuje
     * @param start "koren" grafa
     * @param gc graphicContext Canvasa
     */

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

    /**
     * pravi prikaz datog grafa sa početkom iz "korena" čvora
     * @param g dati graf
     * @param s "koren" čvora
     */

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
                for(int w:graph.nodesConnectedTo(v)) {
                    if (!marked[w]) {
                        secundaQ.add(w);
                    }
                    EdgeView edgeView=new EdgeView(v,w,gc,g.getEdge(v,w));
                    if(!containsEdge(edgeView))
                        edges.add(edgeView);
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

    /**
     *
     * @param nodeMat informacije o nivou povezanosti grafa
     * @param maxLvl koliko nivoa povezanosti čvora poistoji
     */

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

    /**
     * proverava da li ivica postoji u grafu
     * @param edge ivica na proveri
     * @return boolean
     */

    private boolean containsEdge(EdgeView edge){
        for(EdgeView edgeView:edges)
            if(edgeView.equals(edge))
                return true;

            return false;
    }

    /**
     * prikazuje sve čvorove grafa
     */
    public void showNodes(){
        for(NodeView nw:nodes)
            nw.show();
    }

    /**
     * prikazuje sve ivice grafa
     */
    public void showEdges(){
        for(EdgeView edge:edges)
            edge.show();
    }

    /**
     * prikazuje sve elemente grafa
     */
    public void showAll(){
        showEdges();
        showNodes();
    }

    /**
     * prikazuje sve elemente grafa koji su obeleženi kao put
     */
    public void showPath(){
        for(EdgeView edgeView:edges)
            if(edgeView.isPath())
                edgeView.show();

         for(NodeView nodeView:nodes)
             if(nodeView.isPath())
                 nodeView.show();
    }

    public NodeView[] getNodes() {
        return nodes;
    }

    public Set<EdgeView> getEdges() {
        return edges;
    }

    /**
     * briše sve duplikate iz zadate liste
     * @param list lista objekata
     * @param <Item>
     */

    private<Item> void deliteDuplicates(Queue<Item> list){
        Set<Item> set=new HashSet<>(list);
        list.clear();
        list.addAll(set);
        /*for(Item w:set)
            System.err.print(w);*/
    }
}
