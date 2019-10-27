package Contorler;

import Model.SymbolGraph;
import View.MyCanvas;
import View.MyStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.input.MouseEvent;


public class SearchControler implements EventHandler<ActionEvent> {

    private String string;

    public SearchControler(String string) {
        System.out.println(System.currentTimeMillis()+" "+string);
        this.string=string;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(string);
        switch (string){

        }
    }
}
