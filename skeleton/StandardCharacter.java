import java.util.ArrayList;
import java.util.List;

public abstract class StandardCharacter implements Character{

    private List<Object> inventory = new ArrayList<Object>();
    private int inventorySize = 5;
    private Node room;
    private boolean insProtected;
    private boolean poisonProtected;

    public abstract void PickupItem(Object object);

    public abstract void DropItem(Object object);

    public abstract void Move(Node to);

    public abstract void PlayerTurn(/*Node to, Object pick, Object drop*/);

    public abstract boolean GetExpelled(List<Character> characters);

    public List<Object> GetInventory() {return inventory;}
    public int GetInventorySize() {return inventorySize;}
    public boolean GetInsProtected() {return insProtected;}
    public Node GetRoom(){return room;}
    public boolean GetPoisonProtected() {return poisonProtected;}
    public void SetInsProtected(boolean isProtected) {
        Main.TabWriter();
        System.out.println("Character.SetInsProtected(boolean)<void>");
        insProtected = isProtected;
        Main.tabNumber--;
    }
    public void SetPoisonProtected(boolean isProtected) {
        Main.TabWriter();
        System.out.println("Character.SetPoisonProtected(boolean)<void>");
        poisonProtected = isProtected;
        Main.tabNumber--;
    }

    public Node getRoom() {
        return room;
    }

    public void setRoom(Node room) {
        Main.TabWriter();
        System.out.println("Character.setRoom(Node)<void>");
        this.room = room;
        Main.tabNumber--;
    }
}
