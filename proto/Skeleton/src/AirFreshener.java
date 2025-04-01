public class AirFreshener extends Object{

    /**
     * Paraméterezett konstruktora az AirFreshener osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    AirFreshener(String i) {
        this.id=i;
    }

    /**
     * Az AirFreshener osztály paraméter nélküli konstruktora
     */
    AirFreshener() {}

    /**
     * Az AirFreshener objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character) {
        Node room= character.getRoom();
        room.getEffects().removeIf(ef -> ef.Airfreshening(room));
    }
}
