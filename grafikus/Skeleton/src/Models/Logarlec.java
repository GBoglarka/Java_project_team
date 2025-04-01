package Models;

public class Logarlec extends Object {


    /**
     * Paraméterezett konstruktora a Logarlec osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    public Logarlec(String i) {
        this.id=i;
    }

    /**
     *Paraméteres konstruktor
     * @param i Az objektum id tagváltozójának értékét erre állítja
     * @param node Az a szoba, amelyikbe az objektum létrehozódik
     */
    public Logarlec(String i, Node node) {
        this.id=i;
        this.SetRoomin(node);
    }

    /**
     * Paraméter nélküli konstruktora a Logarlec osztálynak
     */
    public Logarlec() { }

    /**
     * A Logarlec objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        Controller.GameOver();
    }

    /**
     * Beállítja a paraméterül kapott Student insProtected és poisonProtected tagváltozóit a tárgy képességének megfelelően
     * @param character Az a Student, amelyiknél a tárgy van
     */
    @Override
    public void SetProtects(Student character) { }
}
