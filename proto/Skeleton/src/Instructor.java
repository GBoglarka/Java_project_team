import java.util.List;

public class Instructor extends StandardCharacter{


    /**
     * Paraméterezett konstruktora az Instructor osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    Instructor(String i) {
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
        if(object.GetRoomin() == this.getRoom()) {
            Node room = getRoom();       //a szoba kinyerése ahol az oktató áll
            room.GetObjects().remove(object);  //az objectet töröljük a szobából
            object.setRoomin(room);         //object helyzetének beállítása

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
        Node room= getRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().add(object);  //az objectet hozzáadjuk a szobához
        object.setRoomin(room);         //object helyzetének beállítása
        GetInventory().remove(object);     //object eltávolítása az inventory-ból
    }

    /**
     * Az instruktor karakterek szobák közti mozgását megvalósító függvény
     * @param to Azt a szobát kapja a függvény paraméterként, ahova a karakter lépni fog
     */
    @Override
    public void Move(Node to)
    {
        Node room= getRoom();       //a szoba kinyerése ahol a oktató áll
        room.setNumIns(room.getNumIns()-1); //A szobából kilépett egy oktató
        room.GetMembers().remove(this); //kitöröljük a szobából az oktatót

        to.GetMembers().add(this);  //hozzáadjuk a szobához az oktatót
        to.setNumIns(to.getNumIns()+1); //a szobába belépett egy oktató
        setRoom(to);

        to.setVisitors(to.getVisitors()+1);
        if(to.getVisitors()==10){
            to.AddEffect(new StickyRoom());
        }
    }

    /**
     * Egy instruktor karakter egy körét vezérli le.
     * Először egy random választott szomszédos szobába lép a karakter, majd annyi tárgyat felvesz,
     * amennyi még elfér nála, vagy amennyi tárgy a szobában van.
     * Ha már nem fér el több tárgy a karakternél, de még lenne mit felvennie, akkor eldob egy tárgyat, ????
     * ezzel befejezve a körét.
     */
    @Override
    public void PlayerTurn(/*Node to, Object pick, Object drop*/)
    {
        Node room= getRoom();
        Move(getRoom());
        for(int i = 0; i < room.GetObjects().size(); i++) {
            if(GetInventory().size() < GetInventorySize()) {
                PickupItem(room.GetObjects().get(i));
                i--;

            }
            else {
                DropItem(GetInventory().get(GetInventorySize()-1));
                break;
            }
        }
    }

    /**
     * Az instruktorok nem kerülnek kirúgásra, a játék végéit jelen vannak a pályán.
     * @param characters A pályán lévő összes karakter listája, akkor van módosítva,
     *                   ha valamelyik hallgató kirúgásra kerül
     * @return Visszaad egy bool értéket, ami megmondja, hogy a karakter ki lett-e rúgva
     */
    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        //A függvény nem csinál semmit az oktatóknál
        return false;
    }

    /**
     * Egy örökölt függvény, ami az instruktorok esetében semmit nem csinál
     */
    @Override
    public void UseItem(){}
}
