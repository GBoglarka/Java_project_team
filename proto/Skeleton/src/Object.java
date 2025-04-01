import java.io.PrintWriter;

public abstract class Object {
    protected int availability;
    private Node roomin;
    protected String id;
    private boolean fake;

    public abstract void Ability(Student character);


    /**
     * roomin setter
     * @param roomin Erre az értékre állítja a függvény a roomin attribútum értékét
     */
    public void setRoomin(Node roomin) {
        this.roomin = roomin;
    }

    /**
     * roomin getter
     * @return Visszaadja azt a szobát, amiben ez a tárgy található
     */
    public Node GetRoomin(){return roomin;}

    public Transistor GetPair() {
        return null;
    }

    /**
     * id getter
     * @return Visszaadja az objektumhoz tartozó azonosítót
     */
    public String getId() { return id; }

    /**
     * fake setter
     * @param f Erre az értékre állítja a függvény a fake attribútum értékét
     */
    public void setFake(boolean f) {fake=f;}

    /**
     * fake getter
     * @return Visszaadja, hogy a tárgy valódi-e, értéke true, ha a tárgy hamis, false, ha a tárgy valós
     */
    public boolean isFake() { return fake; }

}
