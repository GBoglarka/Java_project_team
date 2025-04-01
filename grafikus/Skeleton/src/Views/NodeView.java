package Views;

import Models.Effect;
import Models.Node;
import Models.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeView {
    private int x;
    private int y;
    private int width;
    private List<CharacterView> members= new ArrayList<>();
    private List<ObjectView> objects= new ArrayList<>();
    private List<EffectView> effects=new ArrayList<>();
    private Node pair;

    /**
     * NodeView paraméteres konstruktora
     * @param x x koordináta értéke
     * @param y y koordináta értéke
     * @param width Szoba magassága
     * @param pair Modellbeli párja
     */
    public NodeView(int x, int y,int width, Node pair)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.pair = pair;
    }

    /**
     * Kirajzol  egy szobát és a benne lévő karaktereket
     * @param g2d A kirajzoláshoz szükséges Graphics2D
     * @param mapPanel Az a JPanel, ahova rajzoljuk a szobákat
     * @param characters A játékban lévő karakterek, akiket újra kirajzol
     */
    public void Draw(Graphics2D g2d, JPanel mapPanel , HashMap<Character, CharacterView> characters)
    {
        Update(characters);
        g2d.setColor(Color.lightGray);
        g2d.fillOval(x,y,width,width);
        for(EffectView effect : effects)
        {
            effect.Draw(g2d,x,y,width);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x,y,width,width);

        for(int i = 0; i < members.size(); i++)
        {
            members.get(i).SetX(x+5+30*i);
            members.get(i).SetY(y+width/2-30);
            members.get(i).Draw(mapPanel);
        }
    }

    /**
     * x gettere
     * @return Az x koordináta értékével tér vissza
     */
    public int GetX(){return x;}

    /**
     * y gettere
     * @return Az y koordináta értékével tér vissza
     */
    public int GetY(){return y;}

    /**
     * width gettere
     * @return A magasság értékével tér vissza
     */
    public int GetWidth(){return width;}

    /**
     * Frissíti a members tagváltozót
     * @param characters Az a HashMap, melyben tároljuk a Character-ekhez a hozzájuk tartozó CharacterView-kat
     */
    public void Update(HashMap<Character, CharacterView> characters)
    {
        members.clear();
        for(Character ch : pair.GetMembers())
        {
            members.add(characters.get(ch));
        }

        effects.clear();
        for(Effect effect : pair.GetEffects())
        {
            switch(effect.toString())
            {
                case "poison": effects.add(new PoisonRoomView()); break;
                case "wicked": effects.add(new WickedRoomView()); break;
                case "sticky": effects.add(new StickyRoomView()); break;
            }
        }
    }

    /**
     * Lépteti a karakter a kettintással kiválasztott szobába
     * @param x Kattintás x koordinátája
     * @param y Kattintás y koordinátája
     * @param adjacencyList A HashMap, ami által meg tudjuk mondani, hogy a kiválasztott szoba szomszéd-e
     * @param player Az a karakter, akinek a köre van
     * @return Igazzal tér vissza, ha sikeres volt a lépés, egyébként hamissal
     */
    public boolean NodeMove(int x, int y,HashMap<Node, List<Node>> adjacencyList, Character player)
    {
        if( (x > this.x && x < this.x + width) &&( y > this.y && y < this.y + width) )
        {
            if(adjacencyList.get(player.GetRoom()) != null) {
                if (adjacencyList.get(player.GetRoom()).contains(pair)) {
                    if(pair.GetCapacity() > pair.GetMembers().size()) {
                        player.Move(pair);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
