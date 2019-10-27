package View;

import Contorler.GraphControler;
import Contorler.SearchControler;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MyMenu extends MenuBar {
    private static MyMenu Instance;

    private MyMenu() {
        Menu graphItem=new Menu("Graphs");
        graphItem.getItems().addAll(getGraphs());
        this.getMenus().add(graphItem);

        Menu serchMenu=new Menu("Searches");
        serchMenu.getItems().addAll(getSearchs());
        this.getMenus().add(serchMenu);

        Menu work=new Menu("Work");
        this.getMenus().add(work);

        this.setVisible(true);
    }

    public static MyMenu getInstance() {
        if(Instance==null)
            Instance=new MyMenu();
        return Instance;
    }

    private List<MenuItem> getGraphs(){
        List<MenuItem> list=new ArrayList<>();
        list.add(new MenuItem("Graph1"));
        list.add(new MenuItem("Graph2"));
        list.add(new MenuItem("Graph3"));
        list.add(new MenuItem("BinaryTree1"));
        list.add(new MenuItem("Stanja1"));
        for(MenuItem item:list) {
            item.addEventHandler(ActionEvent.ANY, new GraphControler(item.getText()));
        }
        return list;
    }

    private List<MenuItem> getSearchs(){
        List<MenuItem> list=new ArrayList<>();
        list.add(new MenuItem("DepthFirstSearch"));
        list.add(new MenuItem("BreadthFirstSearch"));
        list.add(new MenuItem("BidirectionalSearch"));
        list.add(new MenuItem("IterativeDeepeningDFS"));
        for(MenuItem item:list)
            item.addEventHandler(ActionEvent.ANY,new SearchControler(item.getText()));

        return list;
    }
}
