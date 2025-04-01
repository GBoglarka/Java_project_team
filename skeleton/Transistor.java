public class Transistor extends Object{

    private boolean active;
    private Transistor pair;

    Transistor(){
        Main.TabWriter();
        System.out.println("Transistor.Transistor()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }


    Transistor(Node room)
    {
        setRoomin(room);
        active = false;
    }


    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("Transistor.Ability(Student)<void>");
        if(!active) ///Ha még nem lett aktiválva a tranzisztor pár
        {
            Main.TabWriter();
            System.out.println("setActive(true)");
            active = true;                              ///aktiválva lesz
            Main.tabNumber--;

            Main.TabWriter();
            System.out.println("setPair()");
            pair = new Transistor(this.GetRoomin());    ///A pár létrehozása
            Main.tabNumber--;

            Main.TabWriter();
            System.out.println("pair.setActive(true)<void>");
            pair.SetActive(true);                   ///A pár is aktív lesz
            Main.tabNumber--;
            pair.SetPair(this);                         ///A pár párja a jelenlegi tranzisztor lesz
            //pair.GetPair().setRoomin(this.GetRoomin());
        }
        else if(pair.GetRoomin()!=null)
        {
            active= false;                          //deaktiváljuk a transistort
            character.Move(pair.GetRoomin());       //átrakjuk a pair szobájába a hallgatót
            pair.setRoomin(null);                   //visszakerül hozzánk a pair
        }

        Main.tabNumber--;
    }

    public void SetActive(boolean active){ this.active = active;}
    public void SetPair(Transistor pair){ this.pair = pair;}
    public Transistor GetPair() {
        return this.pair;
    }
}
