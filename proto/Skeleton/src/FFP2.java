public class FFP2 extends Object{

    /**
     * Paraméterezett konstruktora az FFP2 osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    FFP2(String i) {
        this.id=i;
    }

    /**
     * Paratméter nélküli konstruktora az FFP2 osztálynak
     */
    public FFP2() {
    }

    /**
     * Az FFP2 objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetPoisonProtected(true);     /// A student védve lesz a mérgező szobák hatásától
        availability--;                                   ///  Körönként egyel csökken a hatásának ideje
    }

}
