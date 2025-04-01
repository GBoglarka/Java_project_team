import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WickedRoom implements Effect{
    private int interval;
    private Node chosen;
    private Node thisNode;

    WickedRoom()
    {
        Random random = new Random();
//        interval = random.nextInt(1,4);
        interval = 0; ///teszt
    }
    @Override
    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room)
    {
        Main.TabWriter();
        System.out.println("WickedRoom.RoomSpecial(HashMap<Node,List<Node>>, Node)<void>");

        if(interval == 0 && chosen == null)
        {
            Random random = new Random();                /// Új értéket adunk az intervalnak, legközelebb ennyi kör
            interval = random.nextInt(1, 4); /// után fut le az if blokkja

            List<Node> neighbours = map.get(room); /// A jelenlegi szoba szomszédjainak lekérése
            if(neighbours.size() >= 2)
            {
                thisNode = room;

                int randomIdx = random.nextInt(0,neighbours.size()); /// Lehetséges szomszédok indexeiből random választott szám

                Main.TabWriter();
                System.out.println("SetChosen(Node)");
                chosen = neighbours.get(randomIdx); /// A szomszédok közül random választott szobát elmentjük
                Main.tabNumber--;

                Main.TabWriter();
                System.out.println("SetNeighbours()");
                neighbours.remove(chosen);
                map.replace(room,neighbours);
                Main.tabNumber--;

                List<Node> chosenNeighbours = map.get(chosen);
                if(chosenNeighbours != null) {
                    chosenNeighbours.remove(room);
                }
                map.replace(chosen,chosenNeighbours);

                while(interval > 0) {
                    interval--;
                    Main.TabWriter();
                    System.out.println("SetInterval(" + interval + ")");
                    Main.tabNumber--;
                }
                //Main.tabNumber--;
            }
        }
        if(interval == 0) {
            Random random = new Random();                /// Új értéket adunk az intervalnak, legközelebb ennyi kör
            Main.TabWriter();
            System.out.println("SetInterval(random int)");
            interval = random.nextInt(1, 4); /// után fut le az if blokkja
            Main.tabNumber--;

            List<Node> neighbours = map.get(thisNode);  /// A jelenlegi szoba szomszédjainak lekérése
            neighbours.add(chosen);                 /// Az előző intervalnál változtatott szobát
            map.replace(thisNode, neighbours);

            List<Node> chosenNeighbours = map.get(chosen);
            if (chosenNeighbours != null) {
                chosenNeighbours.add(thisNode);
            }
            map.replace(chosen,chosenNeighbours);
            Main.TabWriter();
            System.out.println("SetNeighbours()");
            Main.tabNumber--;

            chosen = null;                          //A kiválasztott szoba null-ra állítása, azaz nincs kijelölt szoba
            Main.TabWriter();
            System.out.println("SetChosen(null)");
            Main.tabNumber--;
        }

        Main.tabNumber--;
    }
}
