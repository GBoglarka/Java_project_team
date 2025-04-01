package Models;

import java.util.*;

public class Map {
    private int roomsortTimer;                       //Időköz ami elteltével a összekötése és szétválasztása megtörténik
    private int roommergeTimer;                       //Időköz ami elteltével a összekötése és szétválasztása megtörténik
    private LinkedHashMap<Node, List<Node>> adjacencyList;   // szoszédsági lista ami a gráf implementációjűt tartalmazza

    private int columnNumber;
    private int rowNumber;


    /**
     * A Map osztály paraméteres konstruktora
     * @param change Erre az értékre állítja be a konstruktor a roomChangeTimer attribútum értékét
     */
    public Map(int change) {
        roommergeTimer = 0;
        roomsortTimer = change;
        adjacencyList= new LinkedHashMap<>();
    }

    /**
     * A Map osztály paraméter nélküli konstruktora, amiben véletlenszerűen generál szobákat és tárgyakat a szobákba
     */
    public Map() {
        Random rnd= new Random();
        roommergeTimer= rnd.nextInt(30,50);
        roomsortTimer= rnd.nextInt(30,50);

        adjacencyList= new LinkedHashMap<>();

        int row = rnd.nextInt(3, 5);  //hány sor szoba legyen
        int column = rnd.nextInt(5, 7);  //hány oszlop szoba legyen

        for (int i = 0; i < row*column-2; i++) {
            Node node= new Node(String.valueOf(i));
            if(rnd.nextInt(100)<35){
                node.AddEffect(new WickedRoom());
            }
            if(rnd.nextInt(100)<25){
                node.AddEffect(new PoisonRoom());
            }

            if(rnd.nextInt(100)<20){
                node.AddObject(new AirFreshener("Légfrissítő", node));
            }
            if(rnd.nextInt(100)<20){
                node.AddObject(new Beer("Söröspoharak", node));
            }
            if(rnd.nextInt(100)<15){
                node.AddObject(new Camamber("Camamber", node));
            }
            if(rnd.nextInt(100)<35){
                node.AddObject(new FFP2("FFP2 maszk", node));
            }
            if(rnd.nextInt(100)<25){
                node.AddObject(new Rag("Nedves táblatörlő", node));
            }
            if(rnd.nextInt(100)<15){
                node.AddObject(new TVSZ("TVSZ", node));
            }
            for (Object object: node.GetObjects()){
                if(rnd.nextInt(100)<30){
                    object.SetFake(true);
                }
            }
            adjacencyList.put(node, new ArrayList<>());
        }

        int num= rnd.nextInt(row*column-2);
        for (Node node : adjacencyList.keySet()) {
            if((num--)==0)
                node.AddObject(new Transistor("Transistor", node));
        }
        num= rnd.nextInt(row*column-2);
        for (Node node : adjacencyList.keySet()) {
            if((num--)==0)
                node.AddObject(new Logarlec("Logarléc", node));
        }
        if(rnd.nextInt(100)<30) {
            num = rnd.nextInt(row * column - 2);
            for (Node node : adjacencyList.keySet()) {
                if ((num--) == 0) {
                    Object obj = new Logarlec("Logarléc", node);
                    obj.SetFake(true);
                    node.AddObject(obj);
                }
            }
        }

        for (Node node : adjacencyList.keySet()) {
            AddNeighbours(node, row, column);
        }

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
    public void SetAdjacencyList(Node node, List<Node> adjacency) {
        adjacencyList.replace(node, adjacency);
    }

    /**
     * Néhány körönként, amikor a roomChangeTimer értéke 0, összevon két szobát a kapott feltételeknek megfelelően
     */
    public void MergeRoom()
    {
        if (roommergeTimer <= 0) {
            Node firstRoom = null;
            Node secondRoom = null;
            Random rnd = new Random();

            // Véletlenszerű szobák kiválasztása
            int idx = rnd.nextInt(adjacencyList.keySet().size());
            Iterator<Node> it = adjacencyList.keySet().iterator();
            for (int i = 0; i <= idx && it.hasNext(); i++) {
                firstRoom = it.next();
            }

            // Ha az első szoba érvényes és van szomszédja
            if (firstRoom != null && adjacencyList.get(firstRoom).size() > 1) {
                int secidx = rnd.nextInt(adjacencyList.get(firstRoom).size());
                Iterator<Node> it2 = adjacencyList.get(firstRoom).iterator();
                for (int i = 0; i <= secidx && it2.hasNext(); i++) {
                    secondRoom = it2.next();
                }

                // Az új szoba szomszédainak létrehozása és hozzáadása
                List<Node> newRoomNeighbours = new ArrayList<>();
                newRoomNeighbours.addAll(adjacencyList.get(secondRoom));
                newRoomNeighbours.addAll(adjacencyList.get(firstRoom));
                newRoomNeighbours.remove(firstRoom);  // Ne legyen önmaga szomszédja
                newRoomNeighbours.remove(secondRoom); // Ne legyen önmaga szomszédja

                // Új szoba tárgyainak létrehozása
                List<Object> objects = new ArrayList<>();
                objects.addAll(secondRoom.GetObjects());
                objects.addAll(firstRoom.GetObjects());

                // Új szobában lévő karakterek
                List<Character> members = new ArrayList<>();
                members.addAll(firstRoom.GetMembers());
                members.addAll(secondRoom.GetMembers());

                // Új szoba kapacitása és oktatóinak száma
                int capacity = Math.max(firstRoom.GetCapacity(), secondRoom.GetCapacity());
                int insnum = firstRoom.GetNumIns() + secondRoom.GetNumIns();

                // Új szoba effektjei
                List<Effect> effects = new ArrayList<>();
                effects.addAll(secondRoom.GetEffects());
                effects.addAll(firstRoom.GetEffects());

                // Objektumok új szobához rendelése
                for (Object object : objects) {
                    object.SetRoomin(firstRoom);
                }

                // Karakterek új szobához rendelése
                for (Character ch : members) {
                    ch.SetRoom(firstRoom);
                }

                // Új szoba tulajdonságainak hozzáadása
                firstRoom.SetObjects(objects);
                firstRoom.SetEffects(effects);
                firstRoom.SetCapacity(capacity);
                firstRoom.SetNumIns(insnum);
                firstRoom.SetMembers(members);

                // Régi szobák törlése
                adjacencyList.remove(secondRoom);

                // Új szoba belerakása a gráfba
                adjacencyList.put(firstRoom, new ArrayList<>(newRoomNeighbours));

                for (Node node : adjacencyList.keySet()) {
                    List<Node> neighbors = adjacencyList.get(node);
                    if (neighbors != null && neighbors.contains(secondRoom)) {
                        neighbors.remove(secondRoom);
                        if (!node.equals(firstRoom)) {
                            neighbors.add(firstRoom);
                        }
                    }
                }

                roommergeTimer = rnd.nextInt(30,50);
            }
        } else {
            roommergeTimer--;
        }
    }

    /**
     * Néhány körönként, amikor a roomChangeTimer értéke 0,
     * egy szobából kettőt csinál a kapott feltételeknek megfelelően
     */
    public void SortRoom()
    {
        if(roomsortTimer == 0) {
            Node originalRoom = null;
            Random rnd= new Random();
            int idx= rnd.nextInt(adjacencyList.keySet().size());
            for (Node key : adjacencyList.keySet()) {
                if((idx--)==0)
                    originalRoom = key;
            }

            // Szomszédok kinyerése az eredeti szobából
            List<Node> originalRoomNeighbours = adjacencyList.get(originalRoom);

            // Szobák létrehozása
            Node secondNewRoom = new Node();

            // Szomszédok elosztása az új szobák között
            List<Node> firstNewRoomNeighbours = new ArrayList<>();
            List<Node> secondNewRoomNeighbours = new ArrayList<>();
            for (int i = 0; i < originalRoomNeighbours.size(); i++) {
                if (i % 2 == 0) {
                    firstNewRoomNeighbours.add(originalRoomNeighbours.get(i));
                } else {
                    secondNewRoomNeighbours.add(originalRoomNeighbours.get(i));
                    List<Node> neighbors = adjacencyList.get(originalRoomNeighbours.get(i));
                    if (neighbors != null && !neighbors.isEmpty() && neighbors.contains(originalRoom)) {
                        neighbors.remove(originalRoom);
                        neighbors.add(secondNewRoom);
                        adjacencyList.put(originalRoomNeighbours.get(i), neighbors);
                    }
                }
            }
            firstNewRoomNeighbours.add(secondNewRoom);
            secondNewRoomNeighbours.add(originalRoom);

            // Tárgyak, effektek, kapacitás és tagok elosztása az új szobák között
            //először kinyerjük az eredeti listákat és változókat
            List<Object> originalRoomObjects = originalRoom.GetObjects();
            List<Character> originalRoomMembers = originalRoom.GetMembers();
            List<Effect> originalRoomEffects = originalRoom.GetEffects();
            int originalRoomCapacity = originalRoom.GetCapacity();
            int originalRoomInsNum = originalRoom.GetNumIns();

            //tárgyak szétosztása
            List<Object> firstNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(0, originalRoomObjects.size() / 2));
            List<Object> secondNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(originalRoomObjects.size() / 2, originalRoomObjects.size()));
            for(Object object : firstNewRoomObjects) {
                object.SetRoomin(originalRoom);
            }

            for(Object object : secondNewRoomObjects) {
                object.SetRoomin(secondNewRoom);
            }

            for(Character ch : originalRoomMembers) {
                ch.SetRoom(originalRoom);
            }

            // Új szobák tulajdonságainak beállítása
            originalRoom.SetObjects(firstNewRoomObjects);
            originalRoom.SetEffects(originalRoomEffects);
            originalRoom.SetCapacity(originalRoomCapacity);
            originalRoom.SetMembers(originalRoomMembers);
            originalRoom.SetNumIns(originalRoomInsNum);

            secondNewRoom.SetObjects(secondNewRoomObjects);
            secondNewRoom.SetEffects(originalRoomEffects);
            secondNewRoom.SetCapacity(originalRoomCapacity);
            secondNewRoom.SetMembers(new ArrayList<>());
            secondNewRoom.SetNumIns(0);

            // Az új szobák hozzáadása a HashMap-hez

            adjacencyList.put(originalRoom, firstNewRoomNeighbours);
            adjacencyList.put(secondNewRoom, secondNewRoomNeighbours);


            int originalRoomId= Integer.parseInt(originalRoom.GetId());
            originalRoom.SetId(String.valueOf(originalRoomId));
            secondNewRoom.SetId(String.valueOf(originalRoomId + 1));

            // Indexek frissítése a HashMap-ben
            boolean changed = false;
            for (Node node : adjacencyList.keySet()) {
                if (node == originalRoom) {
                    changed = true;
                }
                if (changed && node != originalRoom) {
                    node.SetId(String.valueOf(++originalRoomId));
                }
            }

            roomsortTimer = new Random().nextInt(30,50);
        }
        else {
            roomsortTimer--;
        }
    }

    /**
     * A paraméterként kapott szobához szomszédokat rendel a már meglévő szobák közül
     * @param node Ennek a szobának a szomszédjai kerülnek kiválasztásra a függvényben
     * @param row A node sora a megjelenésnél
     * @param column A node oszlopa aa megjelenésnél
     */
    public void AddNeighbours(Node node, int row, int column) {
        List<Node> neighbours = adjacencyList.get(node);
        int index = GetNodeIndex(node, row, column);

        rowNumber = row;
        columnNumber = column;

        // Ellenőrizze a szomszédos csomópontokat a jobb, bal, felső, alsó, és az átlós irányokban is
        // és adjon hozzájuk szomszédságot a listához
        if (index % column != 0) // nem az első oszlop
            maybeAddNeighbour(neighbours, GetNodeByIndex(index - 1));
        if ((index + 1) % column != 0) // nem az utolsó oszlop
            maybeAddNeighbour(neighbours, GetNodeByIndex(index + 1));
        if (index >= column) // nem az első sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index - column));
        if (index < (row - 1) * column) // nem az utolsó sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index + column));

        // Átlós szomszédok
        if (index % column != 0 && index >= column) // nem az első oszlop és nem az első sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index - column - 1)); // fenti bal
        if ((index + 1) % column != 0 && index >= column) // nem az utolsó oszlop és nem az első sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index - column + 1)); // fenti jobb
        if (index % column != 0 && index < (row - 1) * column) // nem az első oszlop és nem az utolsó sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index + column - 1)); // alsó bal
        if ((index + 1) % column != 0 && index < (row - 1) * column) // nem az utolsó oszlop és nem az utolsó sor
            maybeAddNeighbour(neighbours, GetNodeByIndex(index + column + 1)); // alsó jobb

        //Ha üres, akkor hozzáadjuk vagy a jobb oldali vagy a bal oldali szomszédot. Attól függ melyik létezik
        Node guaranteedNeighbour = null;
        if(neighbours.isEmpty()){
            if ((index + 1) % column != 0) //Így nem köt össze utolsó, és következő sor első oszlopot
                guaranteedNeighbour = GetNodeByIndex(index + 1);
            if(guaranteedNeighbour == null){
                guaranteedNeighbour = GetNodeByIndex(index-1);
            }
            neighbours.add(guaranteedNeighbour);
        }

        adjacencyList.put(node, neighbours);
    }

    /**
     * Bizonyos eséllyel a listához adjuk a szobát
     * @param neighbours Egy Node lista, melyben szomszédok vannak
     * @param neighbour A szoba, amit lehet belerakunk a listába
     */
    private void maybeAddNeighbour(List<Node> neighbours, Node neighbour) {
        // 40% esély arra, hogy hozzáadjuk a szomszédot
        if (Math.random() < 0.65) {
            if(neighbour != null)
                neighbours.add(neighbour);
        }
    }

    /**
     * Visszaadja, hogy hanyadik a szoba
     * @param node A szoba, melynek index-ét keressük
     * @param row Sorok száma
     * @param column Oszlopok száma
     * @return A szoba indexével tér vissza, ha nincs ilyen, akkor -1-el
     */
    private int GetNodeIndex(Node node, int row, int column) {
        // A csomópont indexének meghatározása a mátrixon belül
        for (int i = 0; i < row * column; i++) {
            if (GetNodeByIndex(i) == node)
                return i;
        }
        return -1; // Csak ha nem találta meg a csomópontot
    }

    /**
     * Visszaadja az adott indexű szobát
     * @param index Az a szám, amelyik idexű szobát keressük
     * @return Visszatér a keresett szobával, ha nincs ilyen, akkor null-al
     */
    private Node GetNodeByIndex(int index) {
        // A csomópont visszaadása az index alapján
        int i = 0;
        for (Node node : adjacencyList.keySet()) {
            if (i == index)
                return node;
            i++;
        }
        return null; // Csak ha nem találta meg a csomópontot
    }

    /**
     * adjacencyList getter
     * @return adjacencyList értékét adja vissza
     */
    public HashMap<Node, List<Node>> GetAdjacencyList() {
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
        List<Effect> originalRoomEffects = room.GetEffects();
        int originalRoomCapacity = room.GetCapacity();
        int originalRoomInsNum = room.GetNumIns();

        //tárgyak szétosztása
        List<Object> firstNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(0, originalRoomObjects.size() / 2));
        List<Object> secondNewRoomObjects = new ArrayList<>(originalRoomObjects.subList(originalRoomObjects.size() / 2, originalRoomObjects.size()));

        // Új szobák tulajdonságainak beállítása
        room.SetObjects(firstNewRoomObjects);
        room.SetEffects(originalRoomEffects);
        room.SetCapacity(originalRoomCapacity);
        room.SetMembers(originalRoomMembers);
        room.SetNumIns(originalRoomInsNum);

        secondNewRoom.SetObjects(secondNewRoomObjects);
        secondNewRoom.SetEffects(originalRoomEffects);
        secondNewRoom.SetCapacity(originalRoomCapacity);
        secondNewRoom.SetMembers(new ArrayList<>());
        secondNewRoom.SetNumIns(0);

        // Az új szobák hozzáadása a HashMap-hez
        //adjacencyList.put(firstNewRoom, firstNewRoomNeighbours);
        adjacencyList.replace(room, firstNewRoomNeighbours);
        adjacencyList.put(secondNewRoom, secondNewRoomNeighbours);

        return secondNewRoom;
    }

    /**
     * A teszteseteknél használt MergeRoom függvény,
     * a paraméterként kapott szobákat vonja össze
     * @param r1 Az egyik szoba, ami összevonásra kerül
     * @param r2 A másik szoba, ami összevonásra kerül
     */
    public void MergeRoom(Node r1, Node r2)
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
        int capacity= Math.max(r1.GetCapacity(), r2.GetCapacity());

        //új szoba oktatóinak száma
        int insnum= r1.GetNumIns()+ r2.GetNumIns();

        //új szoba effektjei
        List<Effect> effects= new ArrayList<>();
        effects.addAll(r1.GetEffects());
        effects.addAll(r2.GetEffects());

        //új szoba tulajdonságainak hozzáadása
        r1.SetObjects(objects);
        r1.SetEffects(effects);
        r1.SetCapacity(capacity);
        r1.SetNumIns(insnum);
        r1.SetMembers(members);

        //régi szoba törlése
        adjacencyList.remove(r2);

    }

    /**
     * columnNumber gettere
     * @return Az oszlopok számával tér vissz
     */
    public int GetColumnNumber() {return columnNumber;}

    /**
     * rowNumber gettere
     * @return A sorok számával tér vissza
     */
    public int GetRowNumber() {return rowNumber;}

    /**
     * rowNumber settere
     * @param rowNumber Az érték, amire állítjuk a rowNumber-t
     */
    public void SetRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * columnNumber settere
     * @param columnNumber Az érték, amire állítjuk a columnNumber-t
     */
    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}