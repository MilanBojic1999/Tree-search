package Contorler;

import Model.Search.BidirectionalSearch;
import Model.Search.BreadthFirstSearch;
import Model.Search.DepthFirstSearch;
import Model.Search.IterativeDeepeningDFS;
import Model.SymbolGraph;
import View.App;
import View.MyCanvas;
import View.MyStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.input.MouseEvent;


public class SearchControler implements EventHandler<ActionEvent> {

    private String string;
    private App app;

    public SearchControler(String string) {
        this.string=string;
        app=App.getInstance();
    }

    /**
     * Na zadatu akciju aktivira Search algoritam koji odgovara potražnji
     * @param event
     */

    @Override
    public void handle(ActionEvent event) {
        System.out.println(string);
        switch (string) {
            case "DepthFirstSearch":
                app.work(DepthFirstSearch.INSTANCE);
                break;
            case "BreadthFirstSearch":
                app.work(BreadthFirstSearch.INSTANCE);
                break;
            case "BidirectionalSearch":
                app.work(BidirectionalSearch.INSTANCE);
                break;
            case "IterativeDeepeningDFS":
                app.work(IterativeDeepeningDFS.INSTANCE);
                break;

            default:System.err.println("No no");
        }
    }
}
