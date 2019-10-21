package View;

import javafx.scene.paint.Color;

/**
 * Enum koji služi da se postave stanja čvoreva(NodeView)
 * NEUTRAL-čvor trenutno nema nikakvu primenu u pretrezi
 * PATH-čvor je put između dva čvora
 * CHEKING-čvor se trenutno proverava u pretrazi
 */
public enum NodeStates {
    NEUTRAL(Color.LIGHTGRAY,Color.BLACK),PATH(Color.LIMEGREEN,Color.DARKGREEN),
    CHECKING(Color.LIGHTBLUE,Color.DARKBLUE),CHECKED(Color.BLACK,Color.LIGHTGRAY);

    Color primeColor;
    Color secundColor;

    NodeStates(Color primeColor, Color secundColor) {
        this.primeColor = primeColor;
        this.secundColor = secundColor;
    }


}
