package Models;

import java.util.HashMap;
import java.util.List;

public interface Effect {
    public void RoomSpecial(HashMap<Node, List<Node>> map, Node room);
    public String toString();

    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room, Node neigh);
    public boolean Cleaning(Node room);
    public boolean Airfreshening(Node room);
}
