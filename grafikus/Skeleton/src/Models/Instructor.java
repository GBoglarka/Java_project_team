package Models;

import java.util.*;

public class Instructor extends StandardCharacter {


    /**
     * Paraméterezett konstruktora az Instructor osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public Instructor(String i) {
        this.id=i;
    }


    /**
     * Az Instruktor osztály paraméter nélkül konstruktora
     */
    public Instructor() {
    }

    /**
     * Az instruktor karakterek tárgyfelvételét valósítja meg
     * @param object A felvenni kivánt tárgyat kapja a függvény paraméterként
     */
    @Override
    public void PickupItem(Object object)
    {
        if(object.GetRoomin() == this.GetRoom()) {
            Node room = GetRoom();       //a szoba kinyerése ahol az oktató áll
            room.GetObjects().remove(object);  //az objectet töröljük a szobából
            object.SetRoomin(room);         //object helyzetének beállítása

            GetInventory().add(object);     //object hozzáadása az inventory-hoz
        }
    }

    /**
     * Az instruktor karakterek tárgy eldobását valósítja meg
     * @param object Az eldobni kívánt tárgyat kapja függvény paraméterként
     */
    @Override
    public void DropItem(Object object)
    {
        Node room= GetRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().add(object);  //az objectet hozzáadjuk a szobához
        object.SetRoomin(room);         //object helyzetének beállítása
        GetInventory().remove(object);     //object eltávolítása az inventory-ból
    }

    /**
     * Az instruktor karakterek szobák közti mozgását megvalósító függvény
     * @param to Azt a szobát kapja a függvény paraméterként, ahova a karakter lépni fog
     */
    @Override
    public void Move(Node to)
    {
        Node room= GetRoom();       //a szoba kinyerése ahol a oktató áll
        room.SetNumIns(room.GetNumIns()-1); //A szobából kilépett egy oktató
        room.GetMembers().remove(this); //kitöröljük a szobából az oktatót

        to.GetMembers().add(this);  //hozzáadjuk a szobához az oktatót
        this.SetRoom(to);

        to.SetVisitors(to.GetVisitors()+1);
        if(to.GetVisitors()==10){
            to.AddEffect(new StickyRoom());
        }
    }

    /**
     * Egy instruktor karakter egy körét vezérli le.
     * Először egy random választott szomszédos szobába lép a karakter, majd annyi tárgyat felvesz,
     * amennyi még elfér nála, vagy amennyi tárgy a szobában van.
     * Ha már nem fér el több tárgy a karakternél, de még lenne mit felvennie, akkor eldob egy tárgyat,
     * majd befejezi a körét.
     * @param map A szobák és azok szomszédjai egy HashMap-ben, ez alapján válasszt szobát, hogy hova lép
     * @return igazzal tér vissza, ha sikeresen befejezte a körét
     */
    @Override
    public boolean PlayerTurn(HashMap<Node, List<Node>> map)
    {
        Random rnd=new Random();
        List<Node> neighbours= map.get(GetRoom());
        Node room= new Node();
        if(neighbours!=null) {
            do {
                room = neighbours.get(rnd.nextInt(neighbours.size()));
            } while (room.GetCapacity() <= room.GetMembers().size());
            Move(room);
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
     * Az instruktorok nem kerülnek kirúgásra, a játék végéit jelen vannak a pályán.
     *  @param characters A játékban lévő karakterek listája, ebből törli ki a kiesett Character-t
     * @return Visszaad egy bool értéket, ami megmondja, hogy a karakter ki lett-e rúgva
     */
    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        return false;
    }

    /**
     * Egy örökölt függvény, ami az instruktorok esetében semmit nem csinál, mert nem használhatnak tárgyat
     */
    @Override
    public void UseItem(){}

    /**
     * Beállítja, hogy melyik szobában legyen az oktató
     * @param room Erre az értékre állítja a függvény a room attribútum értékét
     */
    @Override
    public void SetRoom(Node room) {
        super.SetRoom(room);
        room.SetNumIns(room.GetNumIns()+1);
    }

    /**
     * A tárgyhasználat függvénye, üres, mert az oktató nem használhat tárgyat
     * @param item ezt a tárgyat használná fel
     */
    @Override
    public void UseItem(Object item) { }
}
