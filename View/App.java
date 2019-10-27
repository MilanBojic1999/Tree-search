package View;

import Model.Graph;
import Model.GraphBuilder;
import Model.Search.*;
import Model.Stanja.ThreeDices;
import Model.SymbolGraph;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App extends Application {

    private static App Instance;
    private MyStage stage;
    private Timeline timeline;
    private int end;

    public App() {
        Instance=this;      //If it break, it's because of this line :(
        stage=MyStage.getInstance();
        stage.show();
        stage.getCanvas().getGraphView().showAll();

        this.end=5;

        timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2),tl -> stage.getCanvas().getGraphView().showAll()));

        System.err.println(System.currentTimeMillis());
    }

    public static App getInstance() {
        /*if(Instance==null)
            Instance=new App();*/
        return Instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //work(DepthFirstSearch.INSTANCE);
    }

    public void work(Search search){
        timeline.play();

        stage.getCanvas().getGraphView().restartGraph();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        stage.setOnCloseRequest(event -> executor.shutdown());
        executor.submit(() -> {
            SymbolGraph graph=getStage().getCanvas().getGraph();
            search.search(graph,0,end);
            Platform.runLater(() -> stage.getCanvas().getGraphView().showAll());
            timeline.stop();
        });
    }

    public MyStage getStage() {
        return stage;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
