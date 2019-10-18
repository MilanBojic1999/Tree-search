package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class MyStage extends Stage {
    private static MyStage Instance;
    private MyCanvas canvas;
    private MyMenu menu;
    private Scene scene;

    public static MyStage getInstance() {
        if(Instance==null)
            Instance=new MyStage();
        Instance.show();
        return Instance;
    }

    private MyStage() {
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        double width=d.width*0.90;
        double height=d.height*0.90;

        canvas=new MyCanvas(width,height);
        Group group=new Group();
        group.getChildren().add(canvas);

        menu=MyMenu.getInstance();

        VBox pane=new VBox();
        pane.getChildren().addAll(menu,group);

        scene=new Scene(pane,width,height+10);
        setScene(scene);
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public MyMenu getMenu() {
        return menu;
    }

}