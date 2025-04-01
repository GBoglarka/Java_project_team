public class TVSZ extends Object{

    TVSZ(){
        Main.TabWriter();
        System.out.println("TVSZ.TVSZ()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }

    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("TVSZ.Ability(Student)<void>");

        character.SetInsProtected(true); ///A tulajdonosa védve van az oktatóktól
        if(character.GetRoom().getNumIns() > 0) {          /// Ha találkozik a tulajdonos egy oktatóval
            availability--;                         /// Csökken a lehetséges használatok száma
            Main.TabWriter();
            System.out.println("availability--");
            Main.tabNumber--;
        }
        Main.tabNumber--;
    }
}
