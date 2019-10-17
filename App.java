import Model.Graph;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.awt.*;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        double width=d.width*0.90;
        double height=d.height*0.90;
        Canvas canvas=new Canvas(width,height);
        Group group=new Group();
        group.getChildren().add(canvas);
        GraphicsContext gc=canvas.getGraphicsContext2D();
        gc.strokeLine(width/2,0,width/2,height);
        /*NodeView[] ar=new NodeView[4];
        for(int i=0;i<4;i++)
            ar[i]=new NodeView(gc,"TXT",5,1,i,4);
        for(NodeView nw:ar)
            nw.show();
        EdgeView ev=new EdgeView(ar[0],ar[1],gc);
        ev.show();*/
        Scene scene=new Scene(group,width,height);

        Graph graph=GraphBuilder.Instance.buildGraph("Graph1.txt");
        GraphView gv=new GraphView(graph,0,gc);
        canvas.resize(d.width,d.height);
        gv.showEdges();
        gv.showNodes();
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
