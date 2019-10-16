import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeView {

    private NodeView node1;
    private NodeView node2;
    private boolean path;
    private GraphicsContext gc;

    public EdgeView(NodeView node1, NodeView node2, GraphicsContext gc) {
        this.node1 = node1;
        this.node2 = node2;
        this.gc = gc;
        path=false;
    }

    public NodeView getNode1() {
        return node1;
    }

    public NodeView getNode2() {
        return node2;
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public void show(){
        gc.setLineWidth(5);
        if(path){
            gc.setStroke(Color.GREEN);
        }else {
            gc.setStroke(Color.BLACK);
        }
        gc.strokeLine(node1.getX()+20,node1.getY(),node2.getX()+20,node2.getY());
    }
}
