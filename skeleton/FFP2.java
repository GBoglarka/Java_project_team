public class FFP2 extends Object{

    FFP2(){
        Main.TabWriter();
        System.out.println("FFP2.FFP2()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }
    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("FFP2.Ability(Student)<void>");

        character.SetPoisonProtected(true);     /// A student védve lesz a mérgező szobák hatásától
        availability--;                                   ///  Körönként egyel csökken a hatásának ideje
        Main.TabWriter();
        System.out.println("availability--");
        Main.tabNumber--;

        Main.tabNumber--;
    }

}
