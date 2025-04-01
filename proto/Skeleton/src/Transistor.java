public class Transistor extends Object{

    private boolean active;
    private Transistor pair;

    /**
     * Paraméter nélküli konstruktor a Transistor osztálynak
     */
    Transistor(){
    }

    /**
     * Paraméterezett konstruktora a Transistor osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    Transistor(String i) {
        this.id=i;
    }


    /**
     * Paraméterezett konstruktora a Transistor osztálynak
     * @param room az a szoba, amiben a Transistor van
     */
    Transistor(Node room)
    {
        setRoomin(room);
        active = false;
    }


    /**
     * A Transistor objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        if(!active) ///Ha még nem lett aktiválva a tranzisztor pár
        {
            active = true;                              ///aktiválva lesz
            pair = new Transistor(this.GetRoomin());    ///A pár létrehozása
            pair.SetActive(true);                   ///A pár is aktív lesz
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

    /**
     * active setter
     * @param active Erre az értékre állítja a függvény az active attribútum értékét
     */
    public void SetActive(boolean active){ this.active = active;}

    /**
     * pair setter
     * @param pair Erre az értékre állítja a függvény a pair attribútum értékét
     */
    public void SetPair(Transistor pair){ this.pair = pair;}

    /**
     * pair getter
     * @return Visszaadja a Transistor párjához tartozó Transistor objektumot
     */
    public Transistor GetPair() {
        return this.pair;
    }
}
