import java.util.List;

public class Student extends StandardCharacter {

    /**
     * A Student osztály paraméter nélkül konstruktora
     */
    Student(){

    }

    /**
     * Paraméterezett konstruktora a Student osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    Student(String i){
        this.id=i;
    }

    /**
     * A tárgyak felvételének megvalósítása a hallgató karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a hallgató karakter éppen felvesz
     */
    @Override
    public void PickupItem(Object object)
    {
        //tárgy felvétele ha nincs megtelve az invetory TODO:nem veheti fel, ha sticky room
        if(object.GetRoomin() == this.getRoom()) {
            if (GetInventory().size() < GetInventorySize()) {
                Node room = getRoom();       //a szoba kinyerése ahol a tanuló áll
                room.GetObjects().remove(object);  //az objectet töröljük a szobából
                object.setRoomin(null);         //Tárgy helyzete nullára állítása, mert a hallgatóval mozog együtt
                GetInventory().add(object);
            }
        }
    }

    /**
     * A tárgyak eldobásának megvalósítása a hallgató karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a hallgató karakter éppen eldob
     */
    @Override
    public void DropItem(Object object)
    {
        //ha nála van a tárgy eldobhatja
        if(GetInventory().contains(object)){
            Node room= getRoom();       //a szoba kinyerése ahol a hallgató áll
            room.AddObject(object);         //az objectet hozzáadjuk a szobához
            object.setRoomin(room);         //object helyzetének beállítása

            this.GetInventory().remove(object);
        }
    }

    /**
     * A hallgató karakterek szobák közötti mozgását megvalósító függvény
     * @param to Ez a paraméter adja meg, hogy melyik szomszédos szobába lép át a karakter
     */
    @Override
    public void Move(Node to)
    {
        Node room= getRoom();       //a szoba kinyerése ahol a hallgató áll
        room.GetMembers().remove(this); //kitöröljük a szobából a hallgatót
        to.GetMembers().add(this);         //hozzáadjuk a célszobához a hallgatót
        setRoom(to);

        to.setVisitors(to.getVisitors()+1);
        if(to.getVisitors()==10){
            to.AddEffect(new StickyRoom());
        }
    }

    /**
     * Egy hallgató karakter egy körét vezérli le, ideértve a szobák közötti mozgást,
     * a tárgyak esetleges felvételét vagy eldobását, illetve a tárgyak használatát
     */
    @Override
    public void PlayerTurn(/*Node to, Object pick, Object drop*/)
    {
        Move(getRoom());
        UseItem();
        Object beer = new Beer();
        getRoom().GetObjects().add(beer);   //hozzáadjuk a jelenlegi szobához a tárgyat
        beer.setRoomin(getRoom());          //beállítjuk a tárgy helyét
        PickupItem(beer);                   //Karakter felveszi a tárgyat
        DropItem(null);               //tárgy eldobás nem történik
    }

    /**
     * @param characters A pályán lévő karakterek listája, ha valaki kirúgásra kerül, ezt kell módosítani
     * @return Igaz értéket ad vissza, ha a hallgató kirúgásra kerül, hamisat, ha nem
     */
    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        if(getRoom().Conflict() && !GetInsProtected()){
            characters.remove(this); //karakter kiszedése az aktív játékosok közül
            Controller.StudentLost();   //Egyel kevesebb hallgató;
            return true;
        }
        return false;
    }

    /**
     * A tárgyak képességeinek használatát lehetővé tevő függvény
     */
    @Override
    public void UseItem()
    {
        for(int i = 0; i < GetInventory().size(); i++) {
            if(!GetInventory().get(i).isFake()) {
                GetInventory().get(i).Ability(this);
            }
        }
        /*for (Object ob: GetInventory()){
            if(!ob.isFake()) {
                ob.Ability(this);
            }
        }*/
    }

}
