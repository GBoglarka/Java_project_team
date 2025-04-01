package Models;

public class AirFreshener extends Object {

    /**
     * Paraméterezett konstruktora az AirFreshener osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public AirFreshener(String i) {
        this.id=i;
    }

    /**
     * Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public AirFreshener(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
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
        Node room= character.GetRoom();
        room.GetEffects().removeIf(ef -> ef.Airfreshening(room));
        character.GetInventory().remove(this);
    }

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) {
    }
}
