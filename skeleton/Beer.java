public class Beer extends Object{

    Beer(){
        Main.TabWriter();
        System.out.println("Beer.Beer()<constructor>");         //Object létrehozásához kiírás
        this.availability = 2;
        Main.tabNumber--;
    }
    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("Beer.Ability(Student)<void>");

        character.SetInsProtected(true);  ///Beállítja, hogy a Student védve legyen az oktatóktól
        availability--;                             ///Körönként egyel csökken a használhatóságának ideje
        Main.TabWriter();
        System.out.println("availability--");
        Main.tabNumber--;

        Main.tabNumber--;
    }

}
