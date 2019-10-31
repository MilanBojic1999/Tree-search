package View;

import Model.Graph;
import Model.GraphBuilder;
import Model.Stanja.Stanje;
import Model.Stanja.ThreeDices;
import Model.SymbolGraph;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends Canvas {

    private GraphView gv;
    private GraphicsContext gc;
    private SymbolGraph graph;
    private Color color;
    public MyCanvas(double width, double height) {
        super(width, height);
        gc=getGraphicsContext2D();
        setGraph("Graph1");

        color=Color.rgb(255,100,10);
        gc.setFill(color);
        gc.fillRect(0,0,getWidth(),getHeight());
        /*List<Stanje> kraj=new ArrayList<>();
        for(int i=1;i<7;i++){
            kraj.add(new ThreeDices(i,i,i));
        }
        setGraph(GraphBuilder.buildGraphStanje(new ThreeDices(1,1,1),new ThreeDices(4,4,4)));*/
    }


    public GraphicsContext getGraphicsContext(){
        return gc;
    }

    /**
     * postavlja graf koji će se pokazivati
     * @param graphName ime grafa koji se prikazuje
     */
    public void setGraph(String graphName){
        //getGraphicsContext().clearRect(0,0,getWidth(),getHeight());
        gc.setFill(color);
        gc.fillRect(0,0,getWidth(),getHeight());
        graph= GraphBuilder.buildGraph(graphName);
        gv=new GraphView(graph,0,getGraphicsContext());
        gv.showAll();
    }

    public void setGraph(SymbolGraph graph){
        //getGraphicsContext().clearRect(0,0,getWidth(),getHeight());
        gc.setFill(color);
        gc.fillRect(0,0,getWidth(),getHeight());
        this.graph=graph;
        System.out.println(graph.getItem(0));
        gv=new GraphView(graph,0,getGraphicsContext());
        gv.showAll();
    }

    public SymbolGraph getGraph() {
        return graph;
    }

    public GraphView getGraphView() {
        return gv;
    }
}
