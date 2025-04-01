import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Skeleton {

    Map map;
    Student player1, player2, player3;
    Instructor inst1;
    Object tvsz, rag, beer, ffp2, transistror1, camamber, logarlec;
    Node room1, room2, room3;
    Controller cr;

    //tesztesetek előkészítése
    Skeleton() {
        map = new Map();
        tvsz = new TVSZ();
        rag = new Rag();
        beer = new Beer();
        ffp2 = new FFP2();
        camamber = new Camamber();
        logarlec = new Logarlec();
        player1 = new Student();
        player2 = new Student();
        player3 = new Student();
        inst1 = new Instructor();
        //transistror2 = new Transistor();

        //room1 adatai
        List<Object> obj1 = new ArrayList<>();
        obj1.add(tvsz);
        obj1.add(rag);
        List<Character> memb1 = new ArrayList<>();
        memb1.add(player1);
        memb1.add(inst1);

        //room2 adatai
        List<Object> obj2 = new ArrayList<>();
        obj2.add(beer);
        obj2.add(ffp2);
        List<Character> memb2 = new ArrayList<>();
        memb2.add(player2);

        //room3 adatai
        List<Object> obj3 = new ArrayList<>();
        obj3.add(tvsz);
        obj3.add(beer);
        obj3.add(rag);
        obj3.add(ffp2);
        obj3.add(transistror1);
        //obj3.add(transistror2);
        obj3.add(camamber);
        obj3.add(logarlec);
        List<Character> memb3 = new ArrayList<>();
        memb3.add(player3);

        room1 = new Node(obj1, memb1);
        room2 = new Node(obj2, memb2);
        room3 = new Node(obj3, memb3);

        transistror1 = new Transistor(room1);

        map.getAdjacencyList().put(room1, new ArrayList<>());
        map.AddNeighbours(room1);
        map.AddNeighbours(room2);
        List<Character> all = new ArrayList<>();
        all.add(player1);
        all.add(player2);
        all.add(player3);
        all.add(inst1);

        cr = new Controller(map, all);
        //cr.setMap(map);
        //cr.setCharacters(all);
    }

    public void Setup() {
        player1.setRoom(room1);
        player2.setRoom(room2);
        player3.setRoom(room1);
        inst1.setRoom(room1);
        room1.setNumIns(1);
        player1.PickupItem(tvsz);
        player1.PickupItem(rag);
        player2.PickupItem(ffp2);
        player2.PickupItem(tvsz);
        player2.PickupItem(beer);
        room1.AddEffect(new WickedRoom());
        room2.AddEffect(new PoisonRoom());
        System.out.println();
    }

    //Inicializáció teszt
    public void Teszt1() {
        System.out.println("Init test");
        Map map = new Map();

    }

    //DropItem teszteset
    public void Teszt2() {
        System.out.println("DropItem test");
        player1.DropItem(rag);

    }

    //PlayerTurn Student teszt
    public void Teszt3() {
        System.out.println("PlayerTurn Student test");
        player1.PlayerTurn();
    }

    //PlayerTurnInstructor
    public void Teszt4(){
        System.out.println("PlayerTurn Instructor test");
        inst1.PlayerTurn();
    }
    //UseItem teszt
    public void Teszt5(){
        System.out.println("UseItem test");
        player2.UseItem();
    }
    //MergeRoom teszt
    public void Teszt6(){
        System.out.println("MergeRoom test");
        map.MergeRoom();
    }
    //SortRoom teszt
    public void Teszt7(){
        System.out.println("SortRoom test");
        map.SortRoom();
    }
    //WickedRoom teszt
    public void Teszt8(){
        System.out.println("RoomSpecial WickedRoom test");

        room1.getEffects().get(0).RoomSpecial(map.getAdjacencyList(), room1);
    }
    //PoisonRoom teszt
    public void Teszt9(){
        System.out.println("RoomSpecial PoisonRoom test");
        room2.getEffects().get(0).RoomSpecial(map.getAdjacencyList(), room2);
    }
    //TVSZ Ability teszt
    public void Teszt11(){
        System.out.println("Setup:");
        player3.PickupItem(tvsz);
        System.out.println("TVSZ Ability test");
        player3.UseItem();

    }
    //Beer Ability teszt
    public void Teszt16(){
        System.out.println("Setup:");
        player3.DropItem(tvsz);
        player3.PickupItem(beer);
        System.out.println("Beer Ability test");
        player3.UseItem();
    }
    //Rag Ability teszt
    public void Teszt12(){
        System.out.println("Setup:");
        player3.DropItem(beer);
        player3.PickupItem(rag);
        System.out.println("Rag Ability test");
        player3.UseItem();
    }
    //FFP2 Ability teszt
    public void Teszt15(){
        System.out.println("Setup:");
        player3.DropItem(rag);
        player3.PickupItem(ffp2);
        System.out.println("FFP2 Ability test");
        player3.UseItem();

    }
    //Transistor Ability teszt
    public void Teszt10(){
        System.out.println("Setup:");
        player3.DropItem(ffp2);
        player3.PickupItem(transistror1);
        System.out.println("Transistor Ability test");
        player3.UseItem();
        Transistor pair = transistror1.GetPair();
        pair.setRoomin(room1);
        player3.UseItem();
    }
    //Camambert Ability teszt
    public void Teszt14(){
        System.out.println("Setup:");
        player3.PickupItem(camamber);
        System.out.println("Camamber Ability test");
        player3.UseItem();
    }
    //Logarlec teszt
    public void Teszt13(){
        System.out.println("Setup:");
        player3.PickupItem(logarlec);
        System.out.println("Logarlec Ability test");
        player3.UseItem();
    }
    //GameRounds teszt
    public void Teszt17(){
        System.out.println("GameRounds test");
        cr.GameRounds();
    }
    //PickupItem teszt
    public void Teszt18(){
        System.out.println("PickupItem test");
        player3.PickupItem(tvsz);
    }
}
