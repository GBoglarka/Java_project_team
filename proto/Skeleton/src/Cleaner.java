import java.util.List;

public class Cleaner extends StandardCharacter{

    /**
     * A Cleaner osztály paraméterezett konstruktora
     * @param i Erre az értékre állítja be a konstruktor az objektum azonosítóját
     */
    Cleaner(String i) {
        this.id=i;
    }

    /**
     * A Cleaner osztály paraméter nélküli konstruktora
     */
    public Cleaner() {
    }

    /**
     * A tárgyak felvételének megvalósítása a takarító karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a takarító karakter éppen felvesz
     */
    @Override
    public void PickupItem(Object object) {
        Node room= getRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().remove(object);  //az objectet töröljük a szobából
        object.setRoomin(room);         //object helyzetének beállítása

        GetInventory().add(object);     //object hozzáadása az inventory-hoz
    }

    /**
     * A tárgyak eldobásának megvalósítása a takarító karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a takarító karakter éppen eldob
     */
    @Override
    public void DropItem(Object object) {
        Node room= getRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().add(object);  //az objectet hozzáadjuk a szobához
        object.setRoomin(room);         //object helyzetének beállítása
        GetInventory().add(object);     //object hozzáadása az inventory-hoz
    }

    /**
     * A takarító karakterek szobák közötti mozgását megvalósító függvény
     * @param to Ez a paraméter adja meg, hogy melyik szomszédos szobába lép át a karakter
     */
    @Override
    public void Move(Node to) {
        Node room= getRoom();       //a szoba kinyerése ahol a takarító áll
        room.setNumCleaner(room.getNumCleaner()-1); //A szobából kilépett egy takarító
        room.GetMembers().remove(this); //kitöröljük a szobából az takarítót

        CleanRoom(to, room);

        to.GetMembers().add(this);  //hozzáadjuk a szobához az takarító
        to.setNumCleaner(to.getNumCleaner()+1); //a szobába belépett egy takarító
        setRoom(to);

        to.setVisitors(to.getVisitors()+1);
    }

    /**
     * Egy takarító karakter egy körét vezérli le.
     * Először egy random választott szomszédos szobába lép a karakter, majd annyi tárgyat felvesz,
     * amennyi még elfér nála, vagy amennyi tárgy a szobában van.
     * Ha már nem fér el több tárgy a karakternél, de még lenne mit felvennie, akkor eldob egy tárgyat, ????
     * ezzel befejezve a körét.
     */
    @Override
    public void PlayerTurn() {
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
     * Egy takarító nem lesz kirúgva, így egy egyszerű hamis értékkel térünk vissza, egyéb vizsgálat nélkül
     * @param characters a pályán lévő karakterek listája, ez van változtatva olyankor, amikor történik is kirúgás.
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
    public void UseItem() {
        //cleaner nem használ itemet
    }

    /**
     * A takarító karakterek képességét megvalósítő függvény
     * @param to Az a szoba, ahová a takarító belépett, itt fog takarítani
     * @param from Az a szoba, ahonnan a takarító a jelenlegi szobába lépett
     */
    public void CleanRoom(Node to, Node from){

        //karakterek kiküldése
        for(Character ch : to.GetMembers()){
            //ch.Move(from);    //tesztek miatt, most onnan hívódik, különben nem látszódik a kiiratás!
        }

        //visitorok nullára állítása
        to.setVisitors(0);

        //kitörli a poisons és a sticky effecteket
        to.getEffects().removeIf(ef -> ef.Cleaning(to));

    }
}
