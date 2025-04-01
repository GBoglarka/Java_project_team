import java.util.List;

public interface Character {
    public void PickupItem(Object object);
    public void DropItem(Object object);
    public void Move(Node to);
    public void PlayerTurn(/*Node to, Object pick, Object drop*/);
    public boolean GetExpelled(List<Character> characters);
    public boolean GetPoisonProtected();
    public boolean GetInsProtected();
    public List<Object> GetInventory();
    public void setRoom(Node room);

    public void UseItem();
    public String getId();

    public Node getRoom();
}
