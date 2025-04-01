package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WickedRoom implements Effect {
    private int interval;
    private Node chosen;
    private Node thisNode;

    /**
     * A WickedRoom osztály konstruktora
     */
    public WickedRoom()
    {
        Random random = new Random();
        interval = random.nextInt(1,4);
        chosen=null;
    }

    /**
     * A WickedRoom osztály képességét megvalósító függvény. Időközönként eltűntet vagy megjelenít egy ajtót
     * az egyik szomszédos szobába.
     * @param map A játékhoz tartozó pálya
     * @param room Az a szoba, amihez a WickedRoom effekt tartozik
     */
    @Override
    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room)
    {
        if(interval == 0 && chosen == null)
        {
            Random random = new Random();                /// Új értéket adunk az intervalnak, legközelebb ennyi kör
            interval = random.nextInt(1, 4); /// után fut le az if blokkja

            List<Node> neighbours = map.get(room); /// A jelenlegi szoba szomszédjainak lekérése
            if(neighbours!= null && neighbours.size() >= 2)
            {
                thisNode = room;

                int randomIdx = random.nextInt(0,neighbours.size()); /// Lehetséges szomszédok indexeiből random választott szám

                chosen = neighbours.get(randomIdx); /// A szomszédok közül random választott szobát elmentjük

                neighbours.remove(chosen);
                map.replace(room,neighbours);

            }
        }
        else if(interval == 0) {
            Random random = new Random();                /// Új értéket adunk az intervalnak, legközelebb ennyi kör
            interval = random.nextInt(1, 4); /// után fut le az if blokkja

            List<Node> neighbours = map.get(thisNode);  /// A jelenlegi szoba szomszédjainak lekérése
            if(neighbours!=null) {
                neighbours.add(chosen);                 /// Az előző intervalnál változtatott szobát
                map.replace(thisNode, neighbours);
            }

            chosen = null;                          //A kiválasztott szoba null-ra állítása, azaz nincs kijelölt szoba
        }
        else{
            interval--;
        }
    }

    /**
     * Teszteléshez használt függvénye a WickedRoom osztálynak
     * @param map A játékhoz tartozó pálya
     * @param room A szoba, amihez a WickedRoom effekt tartozik
     * @param neigh Az a szoba, amelyikbe nyíló ajtót kívánjuk eltűntetni vagy megjeleníteni
     */
    @Override
    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room, Node neigh)  {

        if(map.get(room).contains(neigh))
        {

            List<Node> neighbours = map.get(room); /// A jelenlegi szoba szomszédjainak lekérése
            if(neighbours.size() >= 2)
            {
                thisNode = room;

                neighbours.remove(neigh);
                map.replace(room,neighbours);


                List<Node> chosenNeighbours = map.get(neigh);
                if(chosenNeighbours != null) {
                    chosenNeighbours.remove(room);
                }
                map.replace(neigh,chosenNeighbours);

            }
        }
        else {

            List<Node> neighbours = map.get(room);  /// A jelenlegi szoba szomszédjainak lekérése
            neighbours.add(neigh);                 /// Az előző intervalnál változtatott szobát
            map.replace(room, neighbours);

            List<Node> chosenNeighbours = map.get(neigh);
            if (chosenNeighbours != null) {
                chosenNeighbours.add(room);
            }
            map.replace(neigh,chosenNeighbours);
            chosen = null;                          //A kiválasztott szoba null-ra állítása, azaz nincs kijelölt szoba
        }
    }

    /**
     * Visszaadja, hogy a takarító el tudja-e tüntetni az effektet
     * @param room A szoba, amihez a WickedRoom ezen példánya tartozik
     * @return Visszaadja, hogy a room takarítható-e
     */
    @Override
    public boolean Cleaning(Node room) {
        return false;
    }

    /**
     * Visszaadja, hogy a légfrissitő megszünteti-e ezt a hatást
     * @param room A szoba, amihez a WickedRoom ezen példánya tartozik
     * @return Igazat ad vissza, ha a szoba rendelkezik gázos tulajdonsággal, egyébként hamis
     */
    @Override
    public boolean Airfreshening(Node room) {return false;}

    /**
     * A prototípusban a kiírást segítő függvény
     * @return A WickedRoom osztály rövidített, string típusú azonosítóját adja vissza
     */
    @Override
    public String toString() {return "wicked";}
}
