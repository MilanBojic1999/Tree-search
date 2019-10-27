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
                Stanje kraj=new ThreeDices(3,3,6);
                SymbolGraph<Stanje> graph= GraphBuilder.buildGraphStanje(new ThreeDices(4,1,4),kraj);
                app.setEnd(graph.getIndex(kraj));
                stage.getCanvas().setGraph(graph);
            default:
                System.out.println("NB");
        }
    }
}
