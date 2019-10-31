package View;

import javafx.scene.paint.Color;

/**
 * Enum koji služi da se postave stanja čvoreva(NodeView)
 * NEUTRAL-čvor trenutno nema nikakvu primenu u pretrezi
 * PATH-čvor je put između dva čvora
 * CHEKING-čvor se trenutno proverava u pretrazi
 */
public enum NodeStates {
    NEUTRAL(Color.rgb(255,100,10),Color.BLACK),PATH(Color.WHITE,Color.DARKRED),
    CHECKING(Color.rgb(150,0,215),Color.rgb(255,100,255)),CHECKED(Color.rgb(90,90,90),Color.BLACK),
    TEST(Color.GREEN,Color.GRAY);

    Color primeColor;
    Color secundColor;

    NodeStates(Color primeColor, Color secundColor) {
        this.primeColor = primeColor;
        this.secundColor = secundColor;
    }


}
