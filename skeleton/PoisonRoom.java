import java.util.HashMap;
import java.util.List;

public class PoisonRoom implements Effect{
    @Override
    public void RoomSpecial(HashMap<Node,List<Node>> map, Node room)
    {
        Main.TabWriter();
        System.out.println("PoisonRoom.RoomSpecial(HashMap, Node)<void>");

        //TODO valaki más nézze meg
        for(Character character : room.GetMembers()) ///Minden a szobában lévő karaktert megnézünk
        {
            if(!character.GetPoisonProtected()) ///Ha a karakternek nincs védelme a mérgezett szobával szemben
            {
                /*for (Object object : character.GetInventory()) ///Eldobja az összes tárgyát
                {
                    character.DropItem(object); //TODO ez nem biztos, hogy úgy működik, ahogy képzelem
                }*/
                while(!character.GetInventory().isEmpty()) {
                    character.DropItem(character.GetInventory().get(0));
                }
            }
        }

        Main.tabNumber--; ///Tabulátorok számának visszacsökkentése az előző hívási szintjére
    }
}
