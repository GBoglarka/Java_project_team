public class TVSZ extends Object{

    /**
     * A TVSZ osztály paraméter nélküli konstruktora
     */
    TVSZ(){
    }

    /**
     * Paraméterezett konstruktora a TVSZ osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    TVSZ(String i) {
        this.id=i;
    }

    /**
     * A TVSZ objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetInsProtected(true); ///A tulajdonosa védve van az oktatóktól
        if(character.getRoom().getNumIns() > 0) {          /// Ha találkozik a tulajdonos egy oktatóval
            availability--;                         /// Csökken a lehetséges használatok száma
        }
    }
}
