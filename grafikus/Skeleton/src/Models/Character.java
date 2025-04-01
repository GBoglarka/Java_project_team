package Models;

import java.util.HashMap;
import java.util.List;

public interface Character {
    public void PickupItem(Object object);
    public void DropItem(Object object);
    public void Move(Node to);
    public boolean PlayerTurn(HashMap<Node, List<Node>> map);
    public boolean GetExpelled(List<Character> characters);
    public void UseItem(Object item);
    public void UseItem();

    public boolean GetPoisonProtected();
    public boolean GetInsProtected();
    public List<Object> GetInventory();
    public String GetId();
    public Node GetRoom();

    public void SetRoom(Node room);
}
