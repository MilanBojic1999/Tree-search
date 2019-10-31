package Contorler;

import Model.GraphBuilder;
import Model.Stanja.Stanje;
import Model.Stanja.ThreeDices;
import Model.SymbolGraph;
import View.App;
import View.MyStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GraphControler implements EventHandler<ActionEvent> {

    private String string;
    private MyStage stage;
    private App app;

    public GraphControler(String string) {
        this.string = string;
        stage=MyStage.getInstance();
        app=App.getInstance();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(string);
        switch (string) {
            case "Graph1":
                app.setEnd(6);
                stage.getCanvas().setGraph("Graph1");
                break;
            case "Graph2":
                app.setEnd(6);
                stage.getCanvas().setGraph("Graph2");
                break;
            case "Graph3":
                app.setEnd(4);
                stage.getCanvas().setGraph("Graph3");
                break;
            case "BinaryTree1":
                app.setEnd(28);
                stage.getCanvas().setGraph("BinaryTree1");
                break;
            case "Stanja1":
                Stanje kraj=new ThreeDices(6,6,6);
                SymbolGraph<Stanje> graph= GraphBuilder.buildGraphStanje(new ThreeDices(1,1,1),kraj,0);
                app.setEnd(graph.getIndex(kraj));
                stage.getCanvas().setGraph(graph);
                break;
            case "Stanja2":
                Stanje kraj2=new ThreeDices(6,4,3);
                SymbolGraph<Stanje> graph2= GraphBuilder.buildGraphStanje(new ThreeDices(1,2,3),kraj2,1);
                app.setEnd(graph2.getIndex(kraj2));
                stage.getCanvas().setGraph(graph2);
                break;
            default:
                System.out.println("NB");
        }
    }
}
