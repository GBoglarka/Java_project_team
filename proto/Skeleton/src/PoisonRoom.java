import java.util.HashMap;
import java.util.List;

public class PoisonRoom implements Effect{

    /**
     * A gázos szobák képességét megvalósító függvény
     * @param map A játékoz tartozó pálya
     * @param room A szoba, amihez ez az effekt tartozik
     */
    @Override
    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room)
    {
        for(Character character : room.GetMembers()) ///Minden a szobában lévő karaktert megnézünk
        {
            if(!character.GetPoisonProtected()) ///Ha a karakternek nincs védelme a mérgezett szobával szemben
            {

                while(!character.GetInventory().isEmpty()) {            //amíg van tárgya a karakternek, addig meghívjuk a DropItem függvényt
                    character.DropItem(character.GetInventory().get(0));
                }
            }
        }
    }

    /**
     * @return A PoisonRoom osztály rövidített, string típusú azonosítóját adja vissza
     */
    @Override
    public String toString() {return "poison";}

    /**
     * Teszteléshez használt függvény, amit csak a WickedRoom osztálynak kell megvalósítania
     * @param map A játékhoz tartozó pálya
     * @param room A jelenlegi szoba, amihez ez az effekt tartozik
     * @param neigh A szoba szomszédja, amit el kívánunk tűntetni a szomszédjai közül
     */
    @Override
    public void RoomSpecial(HashMap<Node, List<Node>> map, Node room, Node neigh) {
        //üres body nem kell tudnia semmit
    }

    /**
     * @param room A szoba, amihez ez az effekt tartozik
     * @return Igazat ad vissza, ha a szoba takarítható
     */
    @Override
    public boolean Cleaning(Node room) {
        return true;
    }

    /**
     * @param room A szoba, amihez ez az effekt tartozik
     * @return Igazat ad vissza, ha gázos a szoba
     */
    @Override
    public boolean Airfreshening(Node room) {
        return true;
    }
}
