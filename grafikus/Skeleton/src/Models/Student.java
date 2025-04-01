package Models;

import java.util.HashMap;
import java.util.List;

public class Student extends StandardCharacter {

    /**
     * A Student osztály paraméter nélkül konstruktora
     */
    public Student(){ }

    /**
     * Paraméterezett konstruktora a Student osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public Student(String i){
        this.id=i;
    }

    /**
     * A tárgyak felvételének megvalósítása a hallgató karaktereknek
     * @param object Azt a tárgyat kapja paraméterként, amit a hallgató karakter éppen felvesz
     */
    @Override
    public void PickupItem(Object object)
    {
        //tárgy felvétele ha nincs megtelve az invetory
        if(object.GetRoomin()== this.GetRoom()) {
            if (GetInventory().size() < GetInventorySize()) {
                Node room = GetRoom();       //a szoba kinyerése ahol a tanuló áll
                room.GetObjects().remove(object);  //az objectet töröljük a szobából
                object.SetRoomin(null);         //Tárgy helyzete nullára állítása, mert a hallgatóval mozog együtt
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
            Node room= GetRoom();       //a szoba kinyerése ahol a hallgató áll
            room.AddObject(object);         //az objectet hozzáadjuk a szobához
            object.SetRoomin(room);         //object helyzetének beállítása

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
        Node room= GetRoom();       //a szoba kinyerése ahol a hallgató áll
        room.GetMembers().remove(this); //kitöröljük a szobából a hallgatót
        to.GetMembers().add(this);         //hozzáadjuk a célszobához a hallgatót
        SetRoom(to);

        to.SetVisitors(to.GetVisitors()+1);
        if(to.GetVisitors()==10){
            to.AddEffect(new StickyRoom());
        }
        GetExpelled(null);
        for (Effect effect : GetRoom().GetEffects()){
            if(effect.Airfreshening(GetRoom())){
                effect.RoomSpecial(null, GetRoom());
            }
        }
    }

    /**
     * Egy hallgató karakter egy körét vezérli le, ideértve a szobák közötti mozgást,
     * a tárgyak esetleges felvételét vagy eldobását, illetve a tárgyak használatát
     * @param map  szobák és azok szomszédjai egy HashMap-ben
     * @return hamissal tér vissza, mivel még nem fejezi be a kört
     */
    @Override
    public boolean PlayerTurn(HashMap<Node, List<Node>> map)
    {
        this.SetPoisonProtected(false);
        this.SetInsProtected(false);

        for(Object o: this.GetInventory()) {
            o.SetProtects(this);
        }

        for(int i = 0; i < this.GetInventory().size(); i++) {
            if(this.GetInventory().get(i).GetAvailability() <= 0) {
                this.GetInventory().remove(this.GetInventory().get(i));
                i--;
            }
        }

        return false;
    }

    /**
     * Eltávolítja a hallgatót a játékban lévő karakterek közül, ha védetlenül találkozik egy oktatóval
     * @param characters A játékan lévő karakterek listája
     * @return Igaz értéket ad vissza, ha a hallgató kirúgásra kerül, hamisat, ha nem
     */
    @Override
    public boolean GetExpelled(List<Character> characters)
    {
        if(GetRoom().Conflict() && !GetInsProtected()){
            GetRoom().GetMembers().remove(this);
            Controller.StudentLost(this);   //Egyel kevesebb hallgató;
            return true;
        }
        return false;
    }

    /**
     * Tárgy felhasználására szolgáló függvény
     * @param item Az az Object, amit fel szeretnénk használni
     */
    @Override
    public void UseItem(Object item) {
        if(!item.IsFake()){
            item.Ability(this);
        }
    }

    /**
     * A tárgyak képességeinek használatát lehetővé tevő függvény
     */
    @Override
    public void UseItem()
    {
        for(int i = 0; i < GetInventory().size(); i++) {
            if(!GetInventory().get(i).IsFake()) {
                GetInventory().get(i).Ability(this);
            }
        }

    }

}
