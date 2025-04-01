package Models;

public class Transistor extends Object {

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
    public Transistor(String i) {
        this.id=i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public Transistor(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
    }

    /**
     * Paraméterezett konstruktora a Transistor osztálynak
     * @param room az a szoba, amiben a Transistor van
     */
    public Transistor(Node room)
    {
        SetRoomin(room);
    }

    /**
     * A Transistor objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        if(!used) ///Ha még nem lett aktiválva a tranzisztor pár
        {
            used=true;
            pair = new Transistor(character.GetRoom());    ///A pár létrehozása
            pair.SetPair(this);                         ///A pár párja a jelenlegi tranzisztor lesz
            pair.GetPair().SetRoomin(this.GetRoomin());
        }
        else if(pair.GetRoomin()!=null)
        {
            used=false;
            Node room= pair.GetRoomin();
            character.DropItem(this);
            character.Move(room);       //átrakjuk a pair szobájába a hallgatót
            pair.SetRoomin(null);                   //visszakerül hozzánk a pair
        }

    }

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

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) { }

}
