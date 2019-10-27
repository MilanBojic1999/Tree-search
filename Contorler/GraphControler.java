package Contorler;

import View.MyStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GraphControler implements EventHandler<ActionEvent> {

    private String string;
    private MyStage stage;

    public GraphControler(String string) {
        this.string = string;
        stage=MyStage.getInstance();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(string);
        switch (string) {
            case "Graph1":
                stage.getCanvas().setGraph("Graph1");
                break;
            case "Graph2":
                stage.getCanvas().setGraph("Graph2");
                break;
            case "Graph3":
                stage.getCanvas().setGraph("Graph3");
                break;
            case "BinaryTree1":
                stage.getCanvas().setGraph("BinaryTree1");
                break;
            default:
                System.out.println("NB");
        }
    }
}
