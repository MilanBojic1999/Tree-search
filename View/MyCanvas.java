package View;

import Model.Graph;
import Model.GraphBuilder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MyCanvas extends Canvas {

    private GraphView gv;
    private GraphicsContext gc;
    private Graph graph;

    public MyCanvas(double width, double height) {
        super(width, height);
        gc=getGraphicsContext2D();
        setGraph("Graph1.txt");
    }

    public GraphicsContext getGraphicsContext(){
        return getGraphicsContext2D();
    }

    public void setGraph(String graphName){
        graph= GraphBuilder.Instance.buildGraph(graphName);
        gv=new GraphView(graph,0,getGraphicsContext());
        gv.showAll();
    }

    public Graph getGraph() {
        return graph;
    }

    public GraphView getGraphView() {
        return gv;
    }
}
