package Views;

import Models.Character;

import javax.swing.*;

public abstract class CharacterView {
    private int x;
    private int y;
    private Character pair;
    private String image;

    /**
     * CharacterView paraméteres konstruktora
     * @param Image A kép neve, amivel majd megjelenik a karakter
     * @param pair Az a Character, akihez tartozik a kinézet
     */
    public CharacterView(String Image, Character pair) {
        this.image = Image;
        this.pair = pair;
    }

    /**
     * Kirajzolja a karaktert
     * @param mapPanel Erre a JPanel-re rajzolja a karakter képét
     */
    public void Draw(JPanel mapPanel)
    {
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(x, y, 30, 50);
        mapPanel.add(imageLabel);
    }

    /**
     * x settere
     * @param x Az az érték, amire állítjuk az x-et
     */
    public void SetX(int x) {
        this.x = x;
    }

    /**
     * y settere
     * @param y Az az érték, amire állítjuk az y-t
     */
    public void SetY(int y) {
        this.y = y;
    }
}
