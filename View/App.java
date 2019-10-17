package View;

import Model.Graph;
import Model.GraphBuilder;
import Model.Search.DepthFirstSearch;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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

        Timeline timeline=new Timeline();
        timeline.setCycleCount(15);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200),tl -> getStage().getCanvas().getGraphView().showAll()));

        timeline.play();



        ExecutorService executor = Executors.newSingleThreadExecutor();
        stage.setOnCloseRequest(event -> {
            executor.shutdown();
        });
        executor.submit(() -> {
            System.out.println(12);
            DepthFirstSearch.INSTANCE.search(getStage().getCanvas().getGraph(),0,10);
            System.out.println(13);
            executor.shutdown();
            timeline.stop();
        });

    }

    public MyStage getStage() {
        return stage;
    }
}
