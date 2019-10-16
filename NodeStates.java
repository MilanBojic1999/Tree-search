import javafx.scene.paint.Color;

public enum NodeStates {
    NEUTRAL(Color.LIGHTGRAY,Color.BLACK),PATH(Color.LIMEGREEN,Color.DARKGREEN),
    CHECKING(Color.AQUAMARINE,Color.DARKBLUE);

    Color primeColor;
    Color secundColor;

    NodeStates(Color primeColor, Color secundColor) {
        this.primeColor = primeColor;
        this.secundColor = secundColor;
    }


}
