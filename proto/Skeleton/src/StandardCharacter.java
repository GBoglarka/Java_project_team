import java.util.ArrayList;
import java.util.List;

public abstract class StandardCharacter implements Character{

    private List<Object> inventory = new ArrayList<Object>();
    private int inventorySize = 5;
    private Node room;
    private boolean insProtected;
    private boolean poisonProtected;
    protected String id;

    public abstract void PickupItem(Object object);

    public abstract void DropItem(Object object);

    public abstract void Move(Node to);

    public abstract void PlayerTurn(/*Node to, Object pick, Object drop*/);

    public abstract boolean GetExpelled(List<Character> characters);

    /**
     * inventory getter
     * @return Visszaadja a karakternél lévő tárgyak listáját
     */
    public List<Object> GetInventory() {return inventory;}

    /**
     * inventorySize getter
     * @return Visszaadja, hogy hány tárgy lehet a karakternél
     */
    public int GetInventorySize() {return inventorySize;}

    /**
     * insProtected getter
     * @return Visszaadja, hogy a karakter védve van-e az oktatóktól
     */
    @Override
    public boolean GetInsProtected() {return insProtected;}


    /**
     * poisonProtected getter
     * @return Visszaadja, hogy a karakter védve van-e a gázos szobák hatásától
     */
    @Override
    public boolean GetPoisonProtected() {return poisonProtected;}

    /**
     * insProtected setter
     * @param isProtected Erre az értékre állítja a függvény az insProtected attribútum értékét
     */
    public void SetInsProtected(boolean isProtected) {
        insProtected = isProtected;
    }

    /**
     * poisonProtected setter
     * @param isProtected Erre az értékre állítja a függvény a poisonProtected attribútum értékét
     */
    public void SetPoisonProtected(boolean isProtected) {
        poisonProtected = isProtected;
    }

    /**
     * room getter
     * @return Visszaadja, hogy a karakter melyik szobában tartózkodik
     */
    @Override
    public Node getRoom() {
        return room;
    }

    /**
     * room setter
     * @param room Erre az értékre állítja a függvény a room attribútum értékét
     */
    @Override
    public void setRoom(Node room) {
        this.room = room;
    }

    /**
     * id getter
     * @return Visszaadja az objektumhoz tartozó azonosító értékét
     */
    @Override
    public String getId() {return id;}
}
