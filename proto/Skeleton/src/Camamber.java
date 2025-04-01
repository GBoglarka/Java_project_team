public class Camamber extends Object{

    /**
     * A Camamber osztály paraméterezett konstruktora
     * @param i Erre az értékre állítja be a konstruktor az objektum azonosítóját
     */
    Camamber(String i) {
        this.id=i;
    }

    /**
     * A Camamber osztály paraméter nélküli konstruktora
     */
    public Camamber() {
    }

    /**
     * A Camamber objektum képességét megvalósító függvény
     * @param character Azt a karaktert kapja a függvény, akinek a tárgy a birtokában van, aki éppen a tárgyat használja
     */
    @Override
    public void Ability(Student character)
    {
        Node room = character.getRoom();    ///Lekérjük a tárgy tulajdonosának szobáját
        room.AddEffect(new PoisonRoom());   ///A lekért szoba effekjeihez adjuk a mérgezősséget
    }

}
