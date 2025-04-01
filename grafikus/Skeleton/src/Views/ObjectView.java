package Views;

import Models.Object;

import javax.swing.*;
import java.awt.*;

public abstract class ObjectView {
    private int x;
    private int y;
    private String image;
    private Object pair;

    /**
     * Az ObjectView paraméteres konstructora
     * @param image Az a kép, amit majd kirajzol
     */
    public ObjectView(String image){
        this.image=image;
    }

    /**
     * Kirajzolja a tárgyat
     * @param inventory Erre a JPanelre rajzol
     */
    public void Draw(JPanel inventory){
        JLabel object =new JLabel(new ImageIcon("./"+image));
        JLabel fake =new JLabel("Fake");
        fake.setFont(new Font("Tahoma", Font.BOLD, 20));
        fake.setForeground(new Color(138, 0, 0));
        fake.setHorizontalAlignment(SwingConstants.CENTER);
        fake.setVerticalAlignment(SwingConstants.CENTER);

        inventory.setLayout(new BorderLayout());
        inventory.add(object, BorderLayout.CENTER);
        if(pair.IsFake()){
            object.setLayout(new BorderLayout());
            object.add(fake, BorderLayout.CENTER);
        }
        x=object.getX();
        y=object.getY();
    }

    /**
     * image gettere
     * @return Az eltárolt képpel tér vissza
     */
    public String GetImage() {
        return image;
    }

    /**
     * x gettere
     * @return x koordináta értékével tér vissza
     */
    public int GetX() {
        return x;
    }

    /**
     * y gettere
     * @return y koordináta értékével tér vissza
     */
    public int GetY() {
        return y;
    }

    /**
     * pair gettere
     * @return A modellbeli párjával tér vissza
     */
    public Object GetPair() {
        return pair;
    }

    /**
     * pair settere
     * @param pair Az az Object, amit beállítunk új értéknek
     */
    public void SetPair(Object pair) {
        this.pair = pair;
    }
}
