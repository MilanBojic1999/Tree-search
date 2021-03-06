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
        return Instance;
    }

    private MyStage() {
        Instance=this;
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        double width=d.width;
        double height=d.height;

        canvas=new MyCanvas(width,height);
        Group group=new Group();
        group.getChildren().add(canvas);

        menu=MyMenu.getInstance();

        VBox pane=new VBox();
        pane.getChildren().addAll(menu,group);
        this.setFullScreen(true);
        scene=new Scene(pane,width,height+10);
        setScene(scene);

        //this.setFullScreen(true);

        setOnCloseRequest(event -> System.exit(10));
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public MyMenu getMenu() {
        return menu;
    }

}
