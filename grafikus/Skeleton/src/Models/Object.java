package Models;

public abstract class Object {
    protected int availability = 2;
    private Node roomin;
    protected String id;
    private boolean fake;
    protected boolean used = false;

    public abstract void Ability(Student character);


    /**
     * roomin setter
     * @param roomin Erre az értékre állítja a függvény a roomin attribútum értékét
     */
    public void SetRoomin(Node roomin) {
        this.roomin = roomin;
    }

    /**
     * roomin getter
     * @return Visszaadja azt a szobát, amiben ez a tárgy található
     */
    public Node GetRoomin(){return roomin;}

    /**
     * Tranzisztor párját adja vissza
     * @return A tranzisztor párja
     */
    public Transistor GetPair() {
        return null;
    }

    /**
     * id getter
     * @return Visszaadja az objektumhoz tartozó azonosítót
     */
    public String GetId() { return id; }

    /**
     * fake setter
     * @param f Erre az értékre állítja a függvény a fake attribútum értékét
     */
    public void SetFake(boolean f) {fake=f;}

    /**
     * fake getter
     * @return Visszaadja, hogy a tárgy valódi-e, értéke true, ha a tárgy hamis, false, ha a tárgy valós
     */
    public boolean IsFake() { return fake; }

    /**
     * availability getter
     * @return Visszaadja, hogy még használható-e a tárgy
     */
    public int GetAvailability() {return availability;}

    public abstract void SetProtects(Student character);

    /**
     * used getter
     * @return Igazzal tér vissza, ha már használta a tanuló a tárgyat, egyébként hamissal
     */
    public boolean IsUsed() {
        return used;
    }
}
