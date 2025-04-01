public class Rag extends Object{

    /**
     * Paraméter nélküli konstruktora a Rag osztálynak
     */
    Rag(){
    }

    /**
     * Paraméterezett konstruktora a Rag osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    Rag(String i) {
        this.id= i;
    }

    /**
     * Az Rag objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetInsProtected(true);    ///A student, akinél van, védve van az oktatóktól, amíg nem találkozik egyel
        if(character.getRoom().getNumIns() > 0) {            ///Ha találkozik oktatóval a hallgató, csökken a használatok száma
            availability--;
        }
    }

}
