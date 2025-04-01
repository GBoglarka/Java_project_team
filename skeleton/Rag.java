public class Rag extends Object{

    Rag(){
        Main.TabWriter();
        System.out.println("Rag.Rag()<constructor>");         //Object létrehozásához kiírás
        Main.tabNumber--;
    }
    @Override
    public void Ability(Student character)
    {
        Main.TabWriter();
        System.out.println("Rag.Ability(Student)<void>");

        character.SetInsProtected(true);    ///A student, akinél van, védve van az oktatóktól, amíg nem találkozik egyel
        if(character.GetRoom().getNumIns() > 0) {            ///Ha találkozik oktatóval a hallgató, csökken a használatok száma
            availability--;
            Main.TabWriter();
            System.out.println("availability--");
            Main.tabNumber--;
        }

        Main.tabNumber--;
    }

}
