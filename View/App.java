package View;

import Model.Graph;
import Model.GraphBuilder;
import Model.Search.BreadthFirstSearch;
import Model.Search.DepthFirstSearch;
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

    public App() {

    }

    public static App getInstance() {
        if(Instance==null)
            Instance=new App();
        return Instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage=MyStage.getInstance();
        stage.show();
        stage.getCanvas().getGraphView().showAll();
        //DepthFirstSearch.INSTANCE.search(stage.getCanvas().getGraph(), 0, 10);
        
        //stage.getCanvas().getGraphView().showPath();

        Timeline timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2),tl -> getStage().getCanvas().getGraphView().showAll()));

        timeline.play();



        ExecutorService executor = Executors.newSingleThreadExecutor();
        stage.setOnCloseRequest(event -> {
            executor.shutdown();
        });
        executor.submit(() -> {
            DepthFirstSearch.INSTANCE.search(getStage().getCanvas().getGraph(),0,10);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stage.getCanvas().getGraphView().showAll();
                }
            });
            //executor.shutdown();
            timeline.stop();
        });

    }

    public MyStage getStage() {
        return stage;
    }
}
