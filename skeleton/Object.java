public abstract class Object {
    protected int availability;
    private Node roomin;

    public abstract void Ability(Student character);


    public void setRoomin(Node roomin) {
        Main.TabWriter();
        System.out.println("Object.SetRoomin(Node)<void>");
        this.roomin = roomin;
        Main.tabNumber--;
    }
    public Node GetRoomin(){return roomin;}

    public Transistor GetPair() {
        return null;
    }
}
