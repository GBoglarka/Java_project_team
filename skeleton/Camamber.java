public class Camamber extends Object{

    Camamber(){
        Main.TabWriter();
        System.out.println("Camamber.Camamber()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }

    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("Camamber.Ability(Student)<void>");

        Node room = character.GetRoom();    ///Lekérjük a tárgy tulajdonosának szobáját
        room.AddEffect(new PoisonRoom());   ///A lekért szoba effekjeihez adjuk a mérgezősséget

        Main.tabNumber--;
    }

}
