import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StickyRoom implements Effect{
    private List<Object> objects;

    /**
     * Konstruktor a StickyRoom osztályhoz
     */
    public StickyRoom() {
        objects = new ArrayList<>();
    }


    //Változások: Mivel a CleanRoom függvénynek el kell távolítania az összes sticky szobákat így azt a döntést hoztam hogy
    //            berakok egy Cleaning függvényt ami igaz értéket ad vissza ha a szoba takarítható illetve a sticky szobáknál
    //            az összes tárgyat visszarakja a szobához

    /**
     * A ragacsos szobák hatását valósítja meg
     * @param map A játékhoz tartozó pálya
     * @param room A szoba, amihez ez az effekt tartozik
     */
    @Override
    public void RoomSpecial(HashMap<Node, List<Node>> map, Node room) {
        if(!room.GetObjects().isEmpty() && objects.isEmpty()){
            //objects.addAll(room.GetObjects());
            for(Object ob : room.GetObjects()) {
                //room.GetObjects().remove(ob);
                objects.add(ob);
                ob.setRoomin(null);
            }
        }
    }

    /**
     * Teszteknél használt függvény, ebben az osztályban nem kerül implementálásra
     * @param map A játékhoz tartozó pálya
     * @param room A jelenlegi szoba, amihez ez az effekt tartozik
     * @param neigh Az a szoba, amit a room szomszédjai közül el akarunk tűntetni, vagy a room szomszédjaihoz hozzáadni
     */
    @Override
    public void RoomSpecial(HashMap<Node, List<Node>> map, Node room, Node neigh) {

    }

    /**
     * @param room A jelenlegi szoba, amihez ez az effekt is tartozik
     * @return igaz értéket ad vissza, ha a szoba takarítható, egyébként hamisat
     */
    @Override
    public boolean Cleaning(Node room) {

        room.setObjects(objects);
        objects = null;

        return true;
    }

    /**
     * @return A StickyRoom osztály rövidített, string típusú azonosítóját adja vissza
     */
    @Override
    public String toString() {return "sticky";}

    /**
     * @param room Az a szoba, amihez a StickyRoom osztály ezen példánya tartozik
     * @return Igazat ad vissza, ha a szoba rendelkezik gázos tulajdonsággal, egyébként hamis
     */
    @Override
    public boolean Airfreshening(Node room) {return false;}
}
