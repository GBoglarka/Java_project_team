package Models;

public class FFP2 extends Object {

    /**
     * Paraméterezett konstruktora az FFP2 osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public FFP2(String i) {
        this.id=i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public FFP2(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
    }

    /**
     * Paratméter nélküli konstruktora az FFP2 osztálynak
     */
    public FFP2() { }

    /**
     * Az FFP2 objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetPoisonProtected(true);     /// A student védve lesz a mérgező szobák hatásától
        //availability--;                                   ///  Körönként egyel csökken a hatásának ideje
        used = true;
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
            character.SetPoisonProtected(true);
            availability--;
        }
        if(availability <= 0) {
            character.SetPoisonProtected(false);
        }
    }

}
