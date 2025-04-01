package Models;

public class TVSZ extends Object {

    /**
     * A TVSZ osztály paraméter nélküli konstruktora
     */
    public TVSZ(){
    }

    /**
     * Paraméterezett konstruktora a TVSZ osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public TVSZ(String i) {
        this.id=i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public TVSZ(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
        this.availability= 3;
    }

    /**
     * A TVSZ objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        this.used = true;
        character.SetInsProtected(true); ///A tulajdonosa védve van az oktatóktól
        if(character.GetRoom().GetNumIns() > 0) {          /// Ha találkozik a tulajdonos egy oktatóval
            availability--;                         /// Csökken a lehetséges használatok száma
        }
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
        if(availability > 0 && this.used) {
            character.SetInsProtected(true); ///A tulajdonosa védve van az oktatóktól
            if (character.GetRoom().GetNumIns() > 0) {          /// Ha találkozik a tulajdonos egy oktatóval
                availability--;                         /// Csökken a lehetséges használatok száma
            }
        }
        if(availability <= 0) {
            character.SetInsProtected(false);
        }
    }
}
