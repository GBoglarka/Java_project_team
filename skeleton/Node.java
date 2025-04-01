import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Node {
    private int capacity;
    private List<Character> members;
    private List<Object> objects;
    private List<Effect> effects;
    private int numIns;

    Node(){

        Main.TabWriter();
        System.out.println("Node.Node()<constructor>");
        this.objects = new ArrayList<>();
        this.members = new ArrayList<>();
        this.effects = new ArrayList<>();
        Main.tabNumber--;
    }

    Node(List<Object> objects, List<Character> members){
        Main.TabWriter();
        System.out.println("Node.Node(List<Object>, List<Character>)<constructor>");

        effects= new ArrayList<>();
        this.objects= objects;
        this.members= members;

        Main.tabNumber--;
    }

    ///Igazat ad vissza, ha nem csak oktatók vannak a szobában.
    public boolean Conflict()
    {
        Main.TabWriter();
        System.out.println("Node.Conflict()<boolean>");

        Main.tabNumber--;
        return members.size() - numIns > 0;
    }

    ///A paraméterként kapott effektet a szobához adja
    public void AddEffect(Effect ef)
    {
        Main.TabWriter();
        System.out.println("Node.AddEffect(Effect)<void>");

        effects.add(ef);

        Main.tabNumber--;
    }

    ///A szobához tartozó összes effekt függvényét meghívja
    public void UseEffect(HashMap<Node,List<Node>> map)
    {
        Main.TabWriter();
        System.out.println("Node.UseEffect(HashMap<Node, List<Node>>)<void>");

        for(Effect ef : effects)
        {
            ef.RoomSpecial(map, this);
        }

        Main.tabNumber--;
    }

    public List<Character> GetMembers()
    {
        return members;
    }
    public void AddObject(Object object)
    {
        Main.TabWriter();
        System.out.println("Node.AddObject(Object)<void>");

        objects.add(object);

        Main.tabNumber--;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public int getNumIns() {
        return numIns;
    }

    public List<Object> GetObjects(){
        return objects;
    }

    public void setCapacity(int capacity) {
        Main.TabWriter();
        System.out.println("Node.setCapacity(int)<void>");
        this.capacity = capacity;
        Main.tabNumber--;
    }

    public void setEffects(List<Effect> effects) {
        Main.TabWriter();
        System.out.println("Node.setEffects(List<Effect>)<void>");
        this.effects = effects;
        Main.tabNumber--;
    }

    public void setMembers(List<Character> members) {
        Main.TabWriter();
        System.out.println("Node.setMembers(List<Character>)<void>");
        this.members = members;
        Main.tabNumber--;
    }

    public void setNumIns(int numIns) {
        Main.TabWriter();
        System.out.println("Node.setNumIns(int)<void>");
        this.numIns = numIns;
        Main.tabNumber--;
    }

    public void setObjects(List<Object> objects) {
        Main.TabWriter();
        System.out.println("Node.setObjects(List<Object>)<void>");
        this.objects = objects;
        Main.tabNumber--;
    }
}
