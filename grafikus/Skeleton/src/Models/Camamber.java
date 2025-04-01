package Models;

public class Camamber extends Object {

    /**
     * A Camamber osztály paraméterezett konstruktora
     * @param i Erre az értékre állítja be a konstruktor az objektum azonosítóját
     */
    public Camamber(String i) {
        this.id=i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public Camamber(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
    }

    /**
     * A Camamber osztály paraméter nélküli konstruktora
     */
    public Camamber() {
    }

    /**
     * A Camamber objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        Node room = character.GetRoom();    ///Lekérjük a tárgy tulajdonosának szobáját
        room.AddEffect(new PoisonRoom());   ///A lekért szoba effekjeihez adjuk a mérgezősséget
        character.GetInventory().remove(this);
    }

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) { }
}
