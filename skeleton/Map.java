import java.util.*;

public class Map {
    private int roomChangeTimer;                       //Időköz ami elteltével a összekötése és szétválasztása megtörténik
    private HashMap<Node, List<Node>> adjacencyList;   // szoszédsági lista ami a gráf implementációjűt tartalmazza

    //Init tesztekhez felhasználandó függvény
    Map(){
        //Kiírás mélység alapján
        Main.TabWriter();
        System.out.println("Map.Map()<constructor>");

        roomChangeTimer = 0;                             //Minden körben fog majd egyet mergelni és sortolni
        adjacencyList= new HashMap<>();

        Node node2= new Node(new ArrayList<>(), new ArrayList<>());    //első szoba
        node2.AddEffect(new WickedRoom());
        node2.AddEffect(new PoisonRoom());

        List<Object> objects= new ArrayList<>();       //Init szekvencia diagram alapján az első szoba megkapja a Rag és a Beer Objectetekt
        objects.add(new Rag());
        objects.add(new Beer());
        objects.add(new Camamber());
        objects.add(new TVSZ());
        Node node1= new Node(objects, new ArrayList<>());    //második szoba

        Node node3= new Node(new ArrayList<>(), Arrays.asList(new Student())); //Harmadik szoba egy hallgatóval
        Node node4= new Node(new ArrayList<>(), Arrays.asList(new Instructor())); //Negyedik szoba egy oktatóval

        adjacencyList.put(node1, new ArrayList<>());   //Első szoba kialakítása a gráfban
        adjacencyList.put(node2, new ArrayList<>());   //Második szoba kialakítása a gráfban
        adjacencyList.put(node3, new ArrayList<>());   //Harmadik szoba kialakítása a gráfban
        adjacencyList.put(node4, new ArrayList<>());   //Negyedik szoba kialakítása a gráfban

        this.AddNeighbours(node1);                     //Első szoba szoszédainak hozzáadása
        this.AddNeighbours(node2);                     //Második szoba szomszádainak hozzáadsa
        this.AddNeighbours(node3);                     //Harmadik szoba szomszádainak hozzáadsa
        this.AddNeighbours(node4);                     //Negyedik szoba szomszádainak hozzáadsa

        Main.tabNumber--;
    }
    public void MergeRoom()
    {
        //Kiírás mélység alapján
        Main.TabWriter();
        System.out.println("Map.MergeRoom()<void>");

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
            Main.TabWriter();
            System.out.println("adjacencyList.put(Node, List<Node>)");
            adjacencyList.put(newNode, newRoomNeighbours);
            Main.tabNumber--;
            roomChangeTimer = 0;
        }
        else {
            roomChangeTimer--;
        }



        Main.tabNumber--;
    }

    public void SortRoom()
    {
        //Kiírás mélység alapján
        Main.TabWriter();
        System.out.println("Map.SortRoom()<void>");

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
            Main.TabWriter();
            System.out.println("adjacencyList.put(Node, List<Node>)");
            adjacencyList.put(firstNewRoom, firstNewRoomNeighbours);
            Main.tabNumber--;
            Main.TabWriter();
            System.out.println("adjacencyList.put(Node, List<Node>)");
            adjacencyList.put(secondNewRoom, secondNewRoomNeighbours);
            Main.tabNumber--;

            // Az eredeti szoba törlése a HashMap-ből
            adjacencyList.remove(originalRoom);
            roomChangeTimer = 0;
        }
        else {
            roomChangeTimer--;
        }


        Main.tabNumber--;
    }

    public void AddNeighbours(Node node)
    {
        //Kiírás mélység alapján
        Main.TabWriter();
        System.out.println("Map.AddNeighbours(Node)<void>");

        List<Node> neighbours = new ArrayList<>();      //A tesztek miatt minden szoba összeköttetésre kerül
        for (Node key : adjacencyList.keySet()) {
            if (!key.equals(node)) {
                neighbours.add(key);
            }
        }
        adjacencyList.replace(node, neighbours);  //Az új szomszédos listára cserélés

        Main.tabNumber--;
    }

    public HashMap<Node, List<Node>> getAdjacencyList() {
        return adjacencyList;
    }
}