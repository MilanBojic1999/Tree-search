import Model.Graph;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class NodeView {

    private NodeStates state;
    private String text;
    private int num;
    private GraphicsContext gc;
    private int lvl;
    private int coll;
    private int maxColl;
    private double x;
    private double y;

    public NodeView(GraphicsContext gc,String text, int num,int lvl,int coll,int maxColl) {
        this.text = text;
        this.num = num;
        state=NodeStates.NEUTRAL;
        this.gc=gc;
        this.lvl=lvl;
        this.coll=coll;
        this.maxColl=maxColl;
        y=10+lvl*75;
        x=gc.getCanvas().getWidth()/(maxColl+1)+maxColl*coll*20;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setState(NodeStates state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
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
