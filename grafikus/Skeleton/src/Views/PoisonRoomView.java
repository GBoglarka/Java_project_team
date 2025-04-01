package Views;

import java.awt.*;

public class PoisonRoomView extends EffectView{

    /**
     * Kirajzolja a megfelelő színű kört
     * @param g2d Kirajzoláshoz szükséges Graphics2D
     * @param x A szoba x koordinátája
     * @param y A szoba y koordinátája
     * @param width Az ablak magassága
     */
    @Override
    public void Draw(Graphics2D g2d, int x, int y, int width) {
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x + width/2 - 15,y+20,10,10);
    }

}
