import java.util.List;

public interface Character {
    public void PickupItem(Object object);
    public void DropItem(Object object);
    public void Move(Node to);
    public void PlayerTurn(/*Node to, Object pick, Object drop*/);
    public boolean GetExpelled(List<Character> characters);
    public boolean GetPoisonProtected();
    public List<Object> GetInventory();
}
