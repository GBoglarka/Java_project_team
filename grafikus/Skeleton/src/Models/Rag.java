package Models;

public class Rag extends Object {

    /**
     * Paraméter nélküli konstruktora a Rag osztálynak
     */
    public Rag(){
    }

    /**
     * Paraméterezett konstruktora a Rag osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public Rag(String i) {
        this.id= i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public Rag(String i, Node node) {
        this.id= i;
        this.SetRoomin(node);
    }


    /**
     * Az Rag objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        used = true;
        character.SetInsProtected(true);
        if(availability<=0){
            character.GetInventory().remove(this);
        }
    }

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének és elhasználtságának megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) {
        if(availability > 0 && used) {
            character.SetInsProtected(true);  ///Beállítja, hogy a Student védve legyen az oktatóktól
            availability--;
        }
        if(availability <= 0) {
            character.SetInsProtected(false);
        }
    }
}
