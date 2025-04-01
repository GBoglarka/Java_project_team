package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
    private int capacity;
    private List<Character> members;
    private List<Object> objects;
    private List<Effect> effects;
    private int numIns;
    private String id;
    private int numCleaner;
    private int visitors;

    /**
     * Paraméterezett konstruktora a Node osztálynak
     * @param i Ezt az értéket adja a konstruktor az osztály azonosítójának
     */
    public Node(String i){

        id=i;

        this.objects = new ArrayList<>();
        this.members = new ArrayList<>();
        this.effects = new ArrayList<>();
        capacity = 3;
    }

    /**
     * Paraméter nélküli konstruktora a Node osztálynak
     */
    Node(){
        this.objects = new ArrayList<>();
        this.members = new ArrayList<>();
        this.effects = new ArrayList<>();
        capacity = 3;
    }

    /**
     * Paraméterezett konstruktora a Node osztálynak
     * @param objects A szobában található tárgyak listáját erre állítja be a konstruktor
     * @param members A szobában lévő karakterek listáját erre állítja be a konstruktor
     */
    public Node(List<Object> objects, List<Character> members){
        effects= new ArrayList<>();
        this.objects= objects;
        this.members= members;
        capacity = 3;
    }

    /**
     * Azt vizsgálja, hogy oktató és hallgató találkozott-e ebben a szobában (this)
     * @return Egy bool értéket ad vissza, hogy hallgató és oktató találkozik-e a szobában.
     *         true, ha igen, false, ha nem
     */
    public boolean Conflict()
    {
        return (members.size() - numIns -numCleaner> 0 && numIns>0);
    }

    /**
     * A paraméterként kapott effektet hozzáadja a szobának az effekteket tartalmazó listájához
     * @param ef Ezt az effektet adja hozzá a függvény az effektek listájához
     */
    ///A paraméterként kapott effektet a szobához adja
    public void AddEffect(Effect ef)
    {
        effects.add(ef);
    }

    /**
     * A szobához tartozó effekt listában lévő összes effektet használja, meghívja ezek függvényeit
     * @param map A játékhoz tartozó pályát kapja meg a függvény, mivel az effekt függvényének
     *            szüksége lehet rá
     */
    ///A szobához tartozó összes effekt függvényét meghívja
    public void UseEffect(HashMap<Node,List<Node>> map)
    {
        for(Effect ef : effects)
        {
            ef.RoomSpecial(map, this);
        }
    }

    /**
     * members getter
     * @return visszaadja a szobában lévő karakterek listáját
     */
    public List<Character> GetMembers()
    {
        return members;
    }

    /**
     * A paraméterként kapott tárgyat a szobában lévő tárgyakhoz adja a függvény
     * @param object Ez a tárgy lesz a szoba tárgyaihoz adva
     */
    public void AddObject(Object object)
    {
        objects.add(object);
    }

    /**
     * capacity getter
     * @return visszaadja a capacity attribútum értékét
     */
    public int GetCapacity() {
        return capacity;
    }

    /**
     * effects getter
     * @return visszaadja a szobához tartozó effektek listáját
     */
    public List<Effect> GetEffects() {
        return effects;
    }

    /**
     * numIns getter
     * @return Visszaadja a szobában lévő instruktorok számát
     */
    public int GetNumIns() {
        return numIns;
    }

    /**
     * objects getter
     * @return Visszaadja a szobában lévő tárgyak listáját
     */
    public List<Object> GetObjects(){
        return objects;
    }

    /**
     * capacity setter
     * @param capacity Erre az értékre állítja a függvény a capacity attribútum értékét
     */
    public void SetCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * effects setter
     * @param effects Erre az értékre állítja a függvény az effects attribútum értékét
     */
    public void SetEffects(List<Effect> effects) {
        this.effects = effects;
    }

    /**
     * members setter
     * @param members Erre az értékre állítja a függvény a members attribútum értékét
     */
    public void SetMembers(List<Character> members) {
        this.members = members;
    }

    /**
     * numIns setter
     * @param numIns Erre az értékre állítja a függvény a numIns attribútum értékét
     */
    public void SetNumIns(int numIns) {
        this.numIns = numIns;
    }

    /**
     * objects setter
     * @param objects Erre az értékre állítja a függvény az objects attribútum értékét
     */
    public void SetObjects(List<Object> objects) {
        this.objects = objects;
    }

    /**
     * id getter
     * @return Visszaadja az osztályhoz tartozó azonosító értékét
     */
    public String GetId() {return id;}

    /**
     * Törli a paraméterként kapott effektet a szobához tartozó effektek listájából
     * @param ef Ez az effekt lesz törölve a szoba effektjei közül
     */
    public void DeleteEffect(Effect ef){
        effects.remove(ef);
    }

    /**
     * visitors getter
     * @return Visszaadja a visitors attribútum értékét
     */
    public int GetVisitors() {
        return visitors;
    }

    /**
     * visitors setter
     * @param visitors Erre az értékre állítja a függvény a visitors attribútum értékét
     */
    public void SetVisitors(int visitors) {
        this.visitors = visitors;
    }

    /**
     * numCleaner getter
     * @return Visszaadja a szobában tartózkodó takarítók számát
     */
    public int GetNumCleaner(){
        return numCleaner;
    }

    /**
     * numCleaner setter
     * @param numCleaner Erre az értékre állítja a függvény a numCleaner attribútum értékét
     */
    public void SetNumCleaner(int numCleaner) {
        this.numCleaner = numCleaner;
    }

    /**
     * id setter
     * @param id Erre az értékre állítja a függvény az id attribútum értékét
     */
    public void SetId(String id) {
        this.id = id;
    }
}
