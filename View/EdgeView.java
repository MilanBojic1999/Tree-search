package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeView {

    private NodeView node1;
    private NodeView node2;
    private boolean path;
    private GraphicsContext gc;
    private int node1num;
    private int node2num;

    public EdgeView(NodeView node1, NodeView node2, GraphicsContext gc) {
        this.node1 = node1;
        this.node2 = node2;
        this.gc = gc;
        path=false;
    }

    public EdgeView(int node1num, int node2num,GraphicsContext gc) {
        this.node1num = node1num;
        this.node2num = node2num;
        this.gc=gc;
        path=false;
    }

    public void setNodes(NodeView[] list){
        for(NodeView node:list){
            if(node.getNum()==node1num)
                node1=node;
            else if(node.getNum()==node2num)
                node2=node;
        }
    }

    @Override
    public int hashCode() {
        final int prime=53;
        int hash=(node1num*10+node2num)*prime;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
        gc.strokeLine(node1.getX()+25,node1.getY()+50,node2.getX()+25,node2.getY());
    }
}
