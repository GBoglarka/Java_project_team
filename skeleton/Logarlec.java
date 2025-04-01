public class Logarlec extends Object{

    Logarlec(){
        Main.TabWriter();
        System.out.println("Logarlec.Logarlec()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }
    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("Logarlec.Ability(Student)<void>");

        Controller.GameOver();      ///Ha egy diák felveszi, a játéknak vége

        Main.tabNumber--;
    }

}
