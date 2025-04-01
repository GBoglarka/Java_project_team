public class Logarlec extends Object{


    /**
     * Paraméterezett konstruktora a Logarlec osztálynak
     * @param i A konstruktor erre az értékre állítja be az objektum id tagváltozóját
     */
    Logarlec(String i) {
        this.id=i;
    }

    /**
     * Paraméter nélküli konstruktora a Logarlec osztálynak
     */
    public Logarlec() {
    }

    /**
     * A Logarlec objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        Controller.GameOver();      ///Ha egy diák felveszi, a játéknak vége
    }

}
