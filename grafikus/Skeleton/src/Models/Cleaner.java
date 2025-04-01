package Models;

import java.util.*;

public class Cleaner extends StandardCharacter {

    /**
     * A Cleaner osztály paraméterezett konstruktora
     * @param i Erre az értékre állítja be a konstruktor az objektum azonosítóját
     */
    public Cleaner(String i) {
        this.id=i;
    }

    /**
     * A Cleaner osztály paraméter nélküli konstruktora
     */
    public Cleaner() { }

    /**
     * A tárgyak felvételének megvalósítása a takarító karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a takarító karakter éppen felvesz
     */
    @Override
    public void PickupItem(Object object) {
        Node room= GetRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().remove(object);  //az objectet töröljük a szobából
        object.SetRoomin(room);         //object helyzetének beállítása

        GetInventory().add(object);     //object hozzáadása az inventory-hoz
    }

    /**
     * A tárgyak eldobásának megvalósítása a takarító karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a takarító karakter éppen eldob
     */
    @Override
    public void DropItem(Object object) {
        Node room= GetRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().add(object);  //az objectet hozzáadjuk a szobához
        object.SetRoomin(room);         //object helyzetének beállítása
        GetInventory().remove(object);     //object hozzáadása az inventory-hoz
    }

    /**
     * A takarító karakterek szobák közötti mozgását megvalósító függvény
     * @param to Ez a paraméter adja meg, hogy melyik szomszédos szobába lép át a karakter
     */
    @Override
    public void Move(Node to) {
        Node room= GetRoom();       //a szoba kinyerése ahol a takarító áll
        room.SetNumCleaner(room.GetNumCleaner()-1); //A szobából kilépett egy takarító
        room.GetMembers().remove(this); //kitöröljük a szobából az takarítót

        to.GetEffects().removeIf(ef -> ef.Cleaning(to));

        to.GetMembers().add(this);  //hozzáadjuk a szobához az takarító
        this.SetRoom(to);

        to.SetVisitors(to.GetVisitors()+1);
    }

    /**
     * Egy takarító karakter egy körét vezérli le.
     * Először egy random választott szomszédos szobába lép a karakter, majd annyi tárgyat felvesz,
     * amennyi még elfér nála, vagy amennyi tárgy a szobában van.
     * Ha már nem fér el több tárgy a karakternél, de még lenne mit felvennie, akkor eldob egy tárgyat,
     * majd befejezi a körét.
     * @param map A szobák és azok szomszédjai egy HashMap-ben, ez alapján válasszt szobát, hogy hova lép
     * @return igazzal tér vissza, ha sikeresen befejezte a körét
     */
    @Override
    public boolean PlayerTurn(HashMap<Node, List<Node>> map) {
        Node from= GetRoom();
        Random rnd=new Random();
        List<Node> neighbours= map.get(GetRoom());
        Node room= new Node();
        if(neighbours!=null) {
            do {
                room = neighbours.get(rnd.nextInt(neighbours.size()));
            } while (room.GetCapacity() <= room.GetMembers().size());

            CleanRoom(room, from, map);
            this.Move(room);
        }

        for(int i = 0; i < room.GetObjects().size(); i++) {
            if(GetInventory().size() < GetInventorySize()) {
                if (!room.GetObjects().get(i).GetId().equals("Logarléc") && !room.GetObjects().get(i).GetId().equals("Transistor")){
                    PickupItem(room.GetObjects().get(i));
                    i--;
                }
            }
            else {
                DropItem(GetInventory().get(GetInventorySize()-1));
                break;
            }
        }
        return true;
    }

    /**
     * Egy takarító nem lesz kirúgva, így egy egyszerű hamis értékkel térünk vissza, egyéb vizsgálat nélkül
     * @param characters A játékban lévő karakterek listája, ebből törli ki a kiesett Character-t
     * @return Visszaad egy bool értéket, ami megmondja, hogy a karakter ki lett-e rúgva
     */
    @Override
    public boolean GetExpelled(List<Character> characters) {
        return false;
    }

    /**
     * A takarító nem tud tárgyak használni, így ez egy üres függvény, az ősosztálytól örökölve
     */
    @Override
    public void UseItem() { }

    /**
     * A takarító karakterek képességét megvalósítő függvény
     * @param to Az a szoba, ahová a takarító belépett, itt fog takarítani
     * @param from Az a szoba, ahonnan a takarító a jelenlegi szobába lépett
     */
    public void CleanRoom(Node to, Node from, HashMap<Node, List<Node>> map){

        List<Character> moving=new ArrayList<>();
        for(Character member : to.GetMembers()) {
            moving.add(member);
        }

        for (Character ch: moving){
            Random rnd=new Random();
            List<Node> neighbours= map.get(to);
            if(neighbours!=null) {
                Node room = from;
                do {
                    room = neighbours.get(rnd.nextInt(neighbours.size()));
                } while (room.GetCapacity() <= room.GetMembers().size());
                ch.Move(room);
            }
        }

        //visitorok nullára állítása
        to.SetVisitors(0);


    }

    /**
     * Beállítja, hogy melyik szobában legyen a takarító
     * @param room Erre az értékre állítja a függvény a room attribútum értékét
     */
    @Override
    public void SetRoom(Node room) {
        super.SetRoom(room);
        room.SetNumCleaner(room.GetNumCleaner()+1);
    }

    /**
     * A takarító nem tud tárgyak használni, így ez egy üres függvény, az ősosztálytól örökölve
     * @param item ezt a tárgyat használná
     */
    @Override
    public void UseItem(Object item) { }
}
