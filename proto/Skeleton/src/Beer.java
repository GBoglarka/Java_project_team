public class Beer extends Object{

    /**
     * A Beer osztály paraméter nélküli konstruktora.
     * Alapból két körön keresztül véd a tárgy, ezt állítja be a konstruktor.
     */
    Beer(){
        this.availability = 2;
    }

    /**
     * A Beer osztály paraméterezett konstruktora.
     * @param i Erre az értékre állítja be a konstruktor az osztály azonosítóját
     */
    Beer(String i) {
        this.id = i;
        this.availability = 2;
    }

    /**
     * A Beer objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        character.SetInsProtected(true);  ///Beállítja, hogy a Student védve legyen az oktatóktól
        availability--;                             ///Körönként egyel csökken a használhatóságának ideje
        if(!character.GetInventory().isEmpty()) {
            character.DropItem(character.GetInventory().get(0));
        }
    }

}
