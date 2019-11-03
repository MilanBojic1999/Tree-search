package View;

import Model.Edge;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeView {

    private NodeView node1;
    private NodeView node2;
    private boolean path;
    private GraphicsContext gc;
    private int node1num;
    private int node2num;
    private Edge edge;

    public EdgeView(NodeView node1, NodeView node2, GraphicsContext gc,Edge edge) {
        this.node1 = node1;
        this.node2 = node2;
        this.gc = gc;
        path=false;
        this.edge=edge;
    }

    public EdgeView(int node1num, int node2num,GraphicsContext gc,Edge edge) {
        this.node1num = node1num;
        this.node2num = node2num;
        this.gc=gc;
        path=false;
        this.edge=edge;
    }

    /**
     * iz date liste čvorova pronalazi dva sd odgovarajućim brojem i stavlja ih kao
     * dva čvora koje povezuje
     * @param list lista čvorova(NodeView)
     */

    public void setNodes(NodeView[] list){
        for(NodeView node:list){
            if(node==null)
                continue;
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
        if(this==obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof EdgeView)) return false;
        EdgeView other=(EdgeView) obj;
        return ((this.node1num==other.node1num) && (this.node2num==other.node2num) ||
                (this.node1num==other.node2num) && (this.node2num==other.node1num));
    }

    /**
     * @return vraća čvor
     */

    public NodeView getNode1() {
        return node1;
    }

    public NodeView getNode2() {
        return node2;
    }

    /**
     * @return da li je u pitanju put između dva čvora u grafu
     */

    public boolean isPath() {
        return path;
    }

    /**
     * postavlja da li je ivica zapravo put između dva čvora
     * @param path
     */
    public void setPath(boolean path) {
        this.path = path;
    }

    /**
     * prikazuje ivicu na platnu između dva čvora (od centra jednog do centra drugog)
     */

    public void show(){
        gc.setLineWidth(8);
        if(path){
            gc.setStroke(Color.GREEN);
        }else {
            gc.setStroke(Color.BLACK);
        }
        gc.strokeLine(node1.getX()+25,node1.getY()+25,node2.getX()+25,node2.getY()+25);
    }

    public boolean isEdge(Edge edge1){
        return edge.equals(edge1);
    }
}
