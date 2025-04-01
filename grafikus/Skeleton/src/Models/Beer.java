package Models;

public class Beer extends Object {

    /**
     * A Beer osztály paraméter nélküli konstruktora.
     * Alapból két körön keresztül véd a tárgy, ezt állítja be a konstruktor.
     */
    public Beer(){
        this.availability = 2;
    }

    /**
     * A Beer osztály paraméterezett konstruktora.
     * @param i Erre az értékre állítja be a konstruktor az osztály azonosítóját
     */
    public Beer(String i) {
        this.id = i;
        this.availability = 2;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public Beer(String i, Node node) {
        this.id = i;
        this.availability = 2;
        this.SetRoomin(node);
    }

    /**
     * A Beer objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetInsProtected(true);  ///Beállítja, hogy a Student védve legyen az oktatóktól
        used = true;
        if(!character.GetInventory().isEmpty()) {
            character.DropItem(character.GetInventory().get(0));
        }
        if(availability<=0)
            character.GetInventory().remove(this);
    }

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének és elhasználtságának megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) {
        if(availability > 0 && used) {
            character.SetInsProtected(true);
            availability--;
        }
        if(availability <= 0) {
            character.SetInsProtected(false);
        }
    }
}
