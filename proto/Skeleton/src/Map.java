import java.util.*;

public class Map {
    private int roomChangeTimer;                       //Időköz ami elteltével a összekötése és szétválasztása megtörténik
    private HashMap<Node, List<Node>> adjacencyList;   // szoszédsági lista ami a gráf implementációjűt tartalmazza

    /**
     * A Map osztály paraméteres konstruktora
     * @param change Erre az értékre állítja be a konstruktor a roomChangeTimer attribútum értékét
     */
    Map(int change) {
        roomChangeTimer = change;
        List<Object> objects= new ArrayList<>();
        adjacencyList= new HashMap<>();
    }

    /**
     * A Map osztály paraméter nélküli konstruktora
     */
    public Map() {
    }

    /**
     * Egy új szobát vesz fel a Mapban lévő HashMap-be
     * @param node Az új szobát, amit a függvény felvesz a HashMap-be, kapja meg paraméterként
     */
    public void AddRoom(Node node) {
        adjacencyList.put(node, new ArrayList<>());
    }

    /**
     * adjacencyList setter
     * @param node Ennek a szobának a szomszédjait változtatjuk
     * @param adjacency Ez a szomszédok listája, amire kicseréljük az előző listát
     */
    public void setAdjacencyList(Node node, List<Node> adjacency) {
        adjacencyList.replace(node, adjacency);
    }

    /**
     * Néhány körönként, amikor a roomChangeTimer értéke 0 lett,
     * összevon két szobát a kapott feltételeknek megfelelően
     */
    public void MergeRoom()
    {
        if(roomChangeTimer == 0) {
            Node newNode= new Node();

            // Első két szoba kinyerése a HashMap-ből
            Iterator<Node> iterator = adjacencyList.keySet().iterator();
            Node firstRoom = iterator.next();
            Node secondRoom = iterator.next();

            // Szomszédok kinyerése az első és második szobákból
            List<Node> firstRoomNeighbours = adjacencyList.get(firstRoom);
            List<Node> secondRoomNeighbours = adjacencyList.get(secondRoom);

            // Az új szoba szomszédainak létrehozása és hozzáadása
            List<Node> newRoomNeighbours = new ArrayList<>();
            newRoomNeighbours.addAll(firstRoomNeighbours);
            newRoomNeighbours.addAll(secondRoomNeighbours);

            //új szoba tárgyainak létrehozása
            List<Object> objects= new ArrayList<>();
            objects.addAll(firstRoom.GetObjects());
            objects.addAll(secondRoom.GetObjects());

            //új szobában lévő karakterek
            List<Character> members= new ArrayList<>();
            members.addAll(firstRoom.GetMembers());
            members.addAll(secondRoom.GetMembers());

            //új szoba kapacitása
            int capacity= Math.max(firstRoom.getCapacity(), secondRoom.getCapacity());

            //új szoba oktatóinak száma
            int insnum= firstRoom.getNumIns()+ secondRoom.getNumIns();

            //új szoba effektjei
            List<Effect> effects= new ArrayList<>();
            effects.addAll(firstRoom.getEffects());
            effects.addAll(secondRoom.getEffects());

            //új szoba tulajdonságainak hozzáadása
            newNode.setObjects(objects);
            newNode.setEffects(effects);
            newNode.setCapacity(capacity);
            newNode.setNumIns(insnum);
            newNode.setMembers(members);

            //régi szobák törlése
            adjacencyList.remove(firstRoom);
            adjacencyList.remove(secondRoom);

            //új szoba belerakása a gráfba
            adjacencyList.put(newNode, newRoomNeighbours);
            roomChangeTimer = 0;
        }
        else {
            roomChangeTimer--;
        }
    }

    /**
     * Néhány körönként, amikor a roomChangeTimer értéke 0 lett,
     * egy szobából kettőt csinál a kapott feltételeknek megfelelően
     */
    public void SortRoom()
    {
        if(roomChangeTimer == 0) {
            // Utolsó elem kinyerése a HashMap-ből
            Node originalRoom = null;
            for (Node key : adjacencyList.keySet()) {
                originalRoom = key;
            }

            // Szomszédok kinyerése az eredeti szobából
            List<Node> originalRoomNeighbours = adjacencyList.get(originalRoom);

            // Szobák létrehozása
            Node firstNewRoom = new Node();
            Node secondNewRoom = new Node();

            // Szomszédok elosztása az új szobák között
            List<Node> firstNewRoomNeighbours = new ArrayList<>();
            List<Node> secondNewRoomNeighbours = new ArrayList<>();
            for (int i = 0; i < originalRoomNeighbours.size(); i++) {
                if (i % 2 == 0) {
                    firstNewRoomNeighbours.add(originalRoomNeighbours.get(i));
                } else {
                    secondNewRoomNeighbours.add(originalRoomNeighbours.get(i));
                }
            }
            firstNewRoomNeighbours.add(secondNewRoom);
            secondNewRoomNeighbours.add(firstNewRoom);

            // Tárgyak, effektek, kapacitás és tagok elosztása az új szobák között
            //először kinyerjük az eredeti listákat és változókat
            List<Object> originalRoomObjects = originalRoom.GetObjects();
            List<Character> originalRoomMembers = originalRoom.GetMembers();
            List<Effect> originalRoomEffects = originalRoom.getEffects();
            int originalRoomCapacity = originalRoom.getCapacity();
            int originalRoomInsNum = originalRoom.getNumIns();

            //tárgyak szétosztása
            List<Object> firstNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(0, originalRoomObjects.size() / 2));
            List<Object> secondNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(originalRoomObjects.size() / 2, originalRoomObjects.size()));

            // Új szobák tulajdonságainak beállítása
            firstNewRoom.setObjects(firstNewRoomObjects);
            firstNewRoom.setEffects(originalRoomEffects);
            firstNewRoom.setCapacity(originalRoomCapacity);
            firstNewRoom.setMembers(originalRoomMembers);
            firstNewRoom.setNumIns(originalRoomInsNum);

            secondNewRoom.setObjects(secondNewRoomObjects);
            secondNewRoom.setEffects(originalRoomEffects);
            secondNewRoom.setCapacity(originalRoomCapacity);
            secondNewRoom.setMembers(new ArrayList<>());
            secondNewRoom.setNumIns(0);

            // Az új szobák hozzáadása a HashMap-hez

            adjacencyList.put(firstNewRoom, firstNewRoomNeighbours);
            adjacencyList.put(secondNewRoom, secondNewRoomNeighbours);

            // Az eredeti szoba törlése a HashMap-ből
            adjacencyList.remove(originalRoom);
            roomChangeTimer = 0;
        }
        else {
            roomChangeTimer--;
        }
    }

    /**
     * A paraméterként kapott szobához szomszédokat rendel a már meglévő szobák közül
     * @param node Ennek a szobának a szomszédjai kerülnek kiválasztásra a függvényben
     */
    public void AddNeighbours(Node node)
    {
        List<Node> neighbours = new ArrayList<>();      //A tesztek miatt minden szoba összeköttetésre kerül
        for (Node key : adjacencyList.keySet()) {
            if (!key.equals(node)) {
                neighbours.add(key);
            }
        }
        adjacencyList.replace(node, neighbours);  //Az új szomszédos listára cserélés
    }

    /**
     * adjacencyList getter
     * @return adjacencyList értékét adja vissza
     */
    public HashMap<Node, List<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Teszthez használt SortRoom függvény
     * @param room Az a szoba, ami osztásra kerül
     * @return Visszaadja az új szobát, ami létrejött az eredeti mellé
     */
    public Node SortRoom(Node room)
    {

        // Szomszédok kinyerése az eredeti szobából
        List<Node> originalRoomNeighbours = adjacencyList.get(room);

        // Szobák létrehozása
        //Node firstNewRoom = new Node();
        Node secondNewRoom = new Node("rooms");

        // Szomszédok elosztása az új szobák között
        List<Node> firstNewRoomNeighbours = new ArrayList<>();
        List<Node> secondNewRoomNeighbours = new ArrayList<>();
        for (int i = 0; i < originalRoomNeighbours.size(); i++) {
            if (i % 2 == 0) {
                firstNewRoomNeighbours.add(originalRoomNeighbours.get(i));
            } else {
                secondNewRoomNeighbours.add(originalRoomNeighbours.get(i));
            }
        }
        //Egymás szomszédai legyenek
        firstNewRoomNeighbours.add(secondNewRoom);
        secondNewRoomNeighbours.add(room);

        // Tárgyak, effektek, kapacitás és tagok elosztása az új szobák között
        //először kinyerjük az eredeti listákat és változókat
        List<Object> originalRoomObjects = room.GetObjects();
        List<Character> originalRoomMembers = room.GetMembers();
        List<Effect> originalRoomEffects = room.getEffects();
        int originalRoomCapacity = room.getCapacity();
        int originalRoomInsNum = room.getNumIns();

        //tárgyak szétosztása
        List<Object> firstNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(0, originalRoomObjects.size() / 2));
        List<Object> secondNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(originalRoomObjects.size() / 2, originalRoomObjects.size()));

        // Új szobák tulajdonságainak beállítása
        room.setObjects(firstNewRoomObjects);
        room.setEffects(originalRoomEffects);
        room.setCapacity(originalRoomCapacity);
        room.setMembers(originalRoomMembers);
        room.setNumIns(originalRoomInsNum);

        secondNewRoom.setObjects(secondNewRoomObjects);
        secondNewRoom.setEffects(originalRoomEffects);
        secondNewRoom.setCapacity(originalRoomCapacity);
        secondNewRoom.setMembers(new ArrayList<>());
        secondNewRoom.setNumIns(0);

        // Az új szobák hozzáadása a HashMap-hez
        //adjacencyList.put(firstNewRoom, firstNewRoomNeighbours);
        adjacencyList.replace(room, firstNewRoomNeighbours);
        adjacencyList.put(secondNewRoom, secondNewRoomNeighbours);

        // Az eredeti szoba törlése a HashMap-ből
        //adjacencyList.remove(originalRoom);
        //roomChangeTimer = 0;

        return secondNewRoom;
    }

    /**
     * A teszteseteknél használt MergeRoom függvény,
     * a paraméterként kapott szobákat vonja össze
     * @param r1 Az egyik szoba, ami összevonásra kerül
     * @param r2 A másik szoba, ami összevonásra kerül
     */
    public void MergeRoom(Node r1,Node r2)
    {
        Node newNode= new Node();


        // Szomszédok kinyerése az első és második szobákból
        List<Node> firstRoomNeighbours = adjacencyList.get(r1);
        List<Node> secondRoomNeighbours = adjacencyList.get(r2);

        // Az új szoba szomszédainak létrehozása és hozzáadása
        List<Node> newRoomNeighbours = new ArrayList<>();
        newRoomNeighbours.addAll(firstRoomNeighbours);
        newRoomNeighbours.addAll(secondRoomNeighbours);

        //új szoba tárgyainak létrehozása
        List<Object> objects= new ArrayList<>();
        objects.addAll(r1.GetObjects());
        objects.addAll(r2.GetObjects());

        //új szobában lévő karakterek
        List<Character> members= new ArrayList<>();
        members.addAll(r1.GetMembers());
        members.addAll(r2.GetMembers());

        //új szoba kapacitása
        int capacity= Math.max(r1.getCapacity(), r2.getCapacity());

        //új szoba oktatóinak száma
        int insnum= r1.getNumIns()+ r2.getNumIns();

        //új szoba effektjei
        List<Effect> effects= new ArrayList<>();
        effects.addAll(r1.getEffects());
        effects.addAll(r2.getEffects());

        //új szoba tulajdonságainak hozzáadása
        r1.setObjects(objects);
        r1.setEffects(effects);
        r1.setCapacity(capacity);
        r1.setNumIns(insnum);
        r1.setMembers(members);

        //régi szobák törlése

        adjacencyList.remove(r2);

        //új szoba belerakása a gráfba
        //adjacencyList.put(newNode, newRoomNeighbours);
    }
}