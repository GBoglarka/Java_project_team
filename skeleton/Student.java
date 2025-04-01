import java.util.List;

public class Student extends StandardCharacter {

    Student(){
        Main.TabWriter();
        System.out.println("Student.Student()<constructor>");         //Object létrehozásához kiírás

        Main.tabNumber--;
    }

    @Override
    public void PickupItem(Object object)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.PickupItem(Object)<void>");

        //tárgy felvétele ha nincs megtelve az invetory
        if(GetInventory().size()<GetInventorySize()){
            Node room= getRoom();       //a szoba kinyerése ahol a tanuló áll
            room.GetObjects().remove(object);  //az objectet töröljük a szobából
            object.setRoomin(null);         //Tárgy helyzete nullára állítása, mert a hallgatóval mozog együtt
            Main.TabWriter();
            System.out.println("Student.addObject(Object)<void>");
            GetInventory().add(object);

            Main.tabNumber--;
        }

        Main.tabNumber--;
    }

    @Override
    public void DropItem(Object object)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.DropItem(Object)<void>");

        //ha nála van a tárgy eldobhatja
        if(GetInventory().contains(object)){
            Node room= getRoom();       //a szoba kinyerése ahol a hallgató áll

            room.AddObject(object);         //az objectet hozzáadjuk a szobához

            object.setRoomin(room);         //object helyzetének beállítása

            this.GetInventory().remove(object);
            Main.TabWriter();
            System.out.println("Student.SetInventory(Object)<void>");
            Main.tabNumber--;

        }

        Main.tabNumber--;
    }

    @Override
    public void Move(Node to)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.Move(Node)<void>");

        Node room= getRoom();       //a szoba kinyerése ahol a hallgató áll
        room.GetMembers().remove(this); //kitöröljük a szobából a hallgatót
        to.GetMembers().add(this);         //hozzáadjuk a célszobához a hallgatót
        setRoom(to);

        Main.tabNumber--;
    }

    @Override
    public void PlayerTurn(/*Node to, Object pick, Object drop*/)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.PlayerTurn()<void>");

        Move(getRoom());
        UseItem();
        Object beer = new Beer();
        getRoom().GetObjects().add(beer);   //hozzáadjuk a jelenlegi szobához a tárgyat
        beer.setRoomin(getRoom());          //beállítjuk a tárgy helyét
        PickupItem(beer);                   //Karakter felveszi a tárgyat
        DropItem(null);               //tárgy eldobás nem történik

        Main.tabNumber--;
    }

    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.GetExpelled(List<Character>)<List<Character>>");


        if(getRoom().Conflict() && !GetInsProtected()){
            characters.remove(this); //karakter kiszedése az aktív játékosok közül
            Controller.StudentLost();   //Egyel kevesebb hallgató;
            Main.tabNumber--;
            return true;
        }

        Main.tabNumber--;
        return false;
    }

    public void UseItem()
    {
        //mélységnek megfelelő kiíratás
        Main.TabWriter();
        System.out.println("Student.UseItem()<void>");

        for (Object ob: GetInventory()){
            ob.Ability(this);
        }

        Main.tabNumber--;
    }

}
