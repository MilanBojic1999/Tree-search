package View;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class NodeView implements Comparable<NodeView>{

    private NodeStates state;
    private String text;
    private int num;
    private GraphicsContext gc;
    private int lvl;
    private int coll;
    private int maxColl;
    private double x;
    private double y;

    public NodeView(GraphicsContext gc,String text, int num,int lvl,int coll,int maxLvl,int maxColl) {
        this.text = text;
        this.num = num;
        state=NodeStates.NEUTRAL;
        this.gc=gc;
        this.lvl=lvl;
        this.coll=coll;
        this.maxColl=maxColl;
        double size=gc.getCanvas().getHeight();
        y=(size*(2*lvl+1))/(2*maxLvl)-25;
        size=gc.getCanvas().getWidth();
        x=(size*(2*coll+1))/(2*maxColl)-25;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public void setState(NodeStates state) {
        this.state = state;
    }

    public boolean isPath(){
        return state==NodeStates.PATH;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public int compareTo(NodeView o) {
        return this.num-o.num;
    }

    public void show(){
        gc.setLineWidth(3);
        gc.setFill(state.primeColor);
        gc.setStroke(state.secundColor);
        gc.fillOval(x,y,50,50);
        gc.strokeOval(x,y,50,50);
        gc.setFill(state.secundColor);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        gc.fillText(text,x+25,y+29);
    }
}
