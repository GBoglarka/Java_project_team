package Views;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import Models.Map;
import Models.Node;
import Models.Character;
import javax.swing.*;

public class MapView {
    private HashMap<Node, NodeView> nodes;
    private HashMap<Character, CharacterView> characters;

    /**
     * MapView paraméter nélküli konstruktora
     */
    public MapView()
    {
        characters= new HashMap<>();
        nodes = new HashMap<Node,NodeView>();
    }

    /**
     * Kirajzolja a szobákat és a bennük lévő karaktereket
     * @param g2d A kirajzoláshoz szükséges Graphics2D
     * @param map Ezt fogja kirajzolni
     * @param mapPanel Az a panel, amelyikre rajzolja a térképet
     */
    public void Draw(Graphics2D g2d, Map map, JPanel mapPanel){
        //Szín és vonalvastagság beállítása
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(5));

        HashMap<Node, List<Node>> adjacencyList = map.GetAdjacencyList();
        int width=0;

        //A vonalat berajzolása, ami a szobák szomszédosságát jelzik
        for(Node mapViewNode : nodes.keySet())
        {
            width= nodes.get(mapViewNode).GetWidth();

            int difference = nodes.get(mapViewNode).GetWidth()/2;
            for(Node adjacencyNode : adjacencyList.get(mapViewNode))
            {
                if(nodes.get(adjacencyNode) != null)
                    g2d.drawLine(nodes.get(mapViewNode).GetX()+difference,nodes.get(mapViewNode).GetY()+difference,
                            nodes.get(adjacencyNode).GetX()+difference,nodes.get(adjacencyNode).GetY()+difference);
            }
        }

        //A szobákhoz köröket rajzol, a szobákba karaktereket
        g2d.setStroke(new BasicStroke(3));
        for(NodeView nodeView : nodes.values())
        {
            //Ez rajzolja a kört, hívja a karaktereket rajzoló függvényt
            nodeView.Draw(g2d, mapPanel, characters);
        }

        for(Node mapViewNode : nodes.keySet())
            for(Node adjacencyNode : adjacencyList.get(mapViewNode))
            {
                if(nodes.get(adjacencyNode) != null) {
                    if (!adjacencyList.get(adjacencyNode).contains(mapViewNode)) {
                        int differenceX = nodes.get(adjacencyNode).GetX() - nodes.get(mapViewNode).GetX(); //A szoba és szomszédja X koordinátái közti különbség
                        int differenceY = nodes.get(adjacencyNode).GetY() - nodes.get(mapViewNode).GetY(); //A szoba és szomszédja Y koordinátái közti különbség
                        double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY); //A szoba és szomszédjának középpontjai közti távolság pixelben
                        double ratio = (double) (nodes.get(adjacencyNode).GetWidth() / 2) / distance;
                        g2d.setColor(Color.red);
                        g2d.fillOval((int) (nodes.get(adjacencyNode).GetX() + nodes.get(adjacencyNode).GetWidth() / 2 - ratio * differenceX - 5),
                                (int) (nodes.get(adjacencyNode).GetY() + nodes.get(adjacencyNode).GetWidth() / 2 - ratio * differenceY - 5), 10, 10);
                    }
                }
            }
    }

    /**
     * A tárolt Character CharacterView HashMap-be beletesz egy új elemet
     * @param ch A beletenni kívánt Character
     * @param chv A Characrehez tartozó CharacterView
     */
    public void CharacterPut(Character ch, CharacterView chv){
        characters.put(ch, chv);
    }

    /**
     * A nodes HashMap inicializálása
     * @param map Az a Map, ami alapján feltöltjük a nodes-t
     * @param width A Panel magassága
     */
    public void NodesInit(Map map, int width)
    {
        HashMap<Node, List<Node>> adjacencyList = map.GetAdjacencyList();
        int currentRowNumber = map.GetRowNumber(); // Jelenlegi sorok száma
        int currentColumnNumber = map.GetColumnNumber(); // Oszlopok száma

        nodes= new HashMap<>();
        int lastindex=0;
        for(Node node : adjacencyList.keySet())
        {
            int index=Integer.parseInt(node.GetId());
            nodes.put(node,new NodeView(60 + (index % currentColumnNumber) * width, 10 + (index / currentColumnNumber)*width,width-20, node ));
            if(lastindex<Integer.parseInt(node.GetId())){
                lastindex= Integer.parseInt(node.GetId());
            }
        }
        if (currentColumnNumber > 0 && currentRowNumber < Math.ceil((double)lastindex / currentColumnNumber)){
            map.SetRowNumber(currentRowNumber+1);
        }
    }

    /**
     * Egy karakter lépésének lekezelésében segítő függvény, ami a lépés után frissíti az adatokat
     * @param x Kattintás x koordinátája
     * @param y Kattintás y koordinátája
     * @param adjacencyList Szomszédok listája
     * @param player Az a karakter, akinek a köre van
     * @return
     */
    public boolean MapViewMove(int x, int y, HashMap<Node, List<Node>> adjacencyList, Character player)
    {
        boolean res = false;
        for(NodeView nodeView : nodes.values())
        {
            if( nodeView.NodeMove(x,y,adjacencyList, player) )
                res = true;
        }
        for(NodeView nodeView : nodes.values())
        {
            nodeView.Update(characters);
        }
        return res;

    }
}
