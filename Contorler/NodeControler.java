package Contorler;

import Model.Edge;
import View.*;


import java.util.Set;

public class NodeControler {

    private static NodeControler Instance;
    private NodeView[] nodes;
    private Set<EdgeView> edges;

    private NodeControler() {
        MyCanvas canvas=MyStage.getInstance().getCanvas();
        nodes=canvas.getGraphView().getNodes();
        edges=canvas.getGraphView().getEdges();
    }


    public static NodeControler getInstance() {
        if(Instance==null)
            Instance=new NodeControler();
        return Instance;
    }

    public void setNodeState(int v, NodeStates state){
        nodes[v].setState(state);
    }

    public void setEgdeAsPath(Edge egde){
        for(EdgeView edgeView:edges)
            if(edgeView.isEdge(egde)){
                edgeView.setPath(true);
            }
    }
}
