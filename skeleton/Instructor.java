import java.util.List;

public class Instructor extends StandardCharacter{

    Instructor(){
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor.Instructor()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }

    @Override
    public void PickupItem(Object object)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor.PickupItem(Object)<void>");
        Main.tabNumber--;

        Node room= getRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().remove(object);  //az objectet töröljük a szobából
        object.setRoomin(room);         //object helyzetének beállítása
        Main.TabWriter();
        System.out.println("Instructor.addObject(Object)<void>");
        GetInventory().add(object);     //object hozzáadása az inventory-hoz

        Main.tabNumber--;
    }

    @Override
    public void DropItem(Object object)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor.DropItem(Object)<void>");

        Node room= getRoom();       //a szoba kinyerése ahol az oktató áll
        room.GetObjects().add(object);  //az objectet hozzáadjuk a szobához
        object.setRoomin(room);         //object helyzetének beállítása
        GetInventory().add(object);     //object hozzáadása az inventory-hoz

        Main.tabNumber--;
    }

    @Override
    public void Move(Node to)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor.Move(Node)<void>");

        Node room= getRoom();       //a szoba kinyerése ahol a oktató áll
        room.setNumIns(room.getNumIns()-1); //A szobából kilépett egy oktató
        room.GetMembers().remove(this); //kitöröljük a szobából az oktatót

        to.GetMembers().add(this);  //hozzáadjuk a szobához az oktatót
        to.setNumIns(to.getNumIns()+1); //a szobába belépett egy oktató
        setRoom(to);

        Main.tabNumber--;
    }

    @Override
    public void PlayerTurn(/*Node to, Object pick, Object drop*/)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor.PlayerTurn()<void>");

        Node room= getRoom();
        Move(getRoom());
        for(int i = 0; i < room.GetObjects().size(); i++) {
            if(GetInventory().size() < GetInventorySize()) {
                PickupItem(room.GetObjects().get(i));
                i--;

            }
            else {
                DropItem(GetInventory().get(GetInventorySize()-1));
                break;
            }
        }

        Main.tabNumber--;
    }

    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Instructor GetExpelled(List<StandardCharacter>) <List<StandardCharacter>>");

        //A függvény nem csinál semmit az oktatóknál

        Main.tabNumber--;
        return false;
    }

}
