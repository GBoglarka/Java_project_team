import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WickedRoom implements Effect{
    private int interval;
    private Node chosen;
    private Node thisNode;

    /**
     * A WickedRoom osztály konstruktora
     */
    WickedRoom()
    {
        Random random = new Random();
//        interval = random.nextInt(1,4);
        interval = 0; ///teszt
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
            if(neighbours.size() >= 2)
            {
                thisNode = room;

                int randomIdx = random.nextInt(0,neighbours.size()); /// Lehetséges szomszédok indexeiből random választott szám

                chosen = neighbours.get(randomIdx); /// A szomszédok közül random választott szobát elmentjük

                neighbours.remove(chosen);
                map.replace(room,neighbours);

                List<Node> chosenNeighbours = map.get(chosen);
                if(chosenNeighbours != null) {
                    chosenNeighbours.remove(room);
                }
                map.replace(chosen,chosenNeighbours);

                while(interval > 0) {
                    interval--;
                }
            }
        }
        if(interval == 0) {
            Random random = new Random();                /// Új értéket adunk az intervalnak, legközelebb ennyi kör
            interval = random.nextInt(1, 4); /// után fut le az if blokkja

            List<Node> neighbours = map.get(thisNode);  /// A jelenlegi szoba szomszédjainak lekérése
            neighbours.add(chosen);                 /// Az előző intervalnál változtatott szobát
            map.replace(thisNode, neighbours);

            List<Node> chosenNeighbours = map.get(chosen);
            if (chosenNeighbours != null) {
                chosenNeighbours.add(thisNode);
            }
            map.replace(chosen,chosenNeighbours);

            chosen = null;                          //A kiválasztott szoba null-ra állítása, azaz nincs kijelölt szoba
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

                //int randomIdx = random.nextInt(0,neighbours.size()); /// Lehetséges szomszédok indexeiből random választott szám


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
     * @param room A szoba, amihez a WickedRoom ezen példánya tartozik
     * @return Visszaadja, hogy a room takarítható-e
     */
    @Override
    public boolean Cleaning(Node room) {
        return false;
    }

    /**
     * @param room A szoba, amihez a WickedRoom ezen példánya tartozik
     * @return Igazat ad vissza, ha a szoba rendelkezik gázos tulajdonsággal, egyébként hamis
     */
    @Override
    public boolean Airfreshening(Node room) {return false;}

    /**
     * @return A WickedRoom osztály rövidített, string típusú azonosítóját adja vissza
     */
    @Override
    public String toString() {return "wicked";}
}
