import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Controller {
    private Map map;
    private List<Character> characters;
    private static boolean gameOver = false;
    private static int studentNum;

    /**
     * A Controller osztály konstruktora
     * @param m A játékhoz tartozó objektum Map, ezt az értéket kapja a Controller map attribútuma
     * @param c A játékban résztvevő karakterek listája, ezt az értéket kapja a Controller characters attribtuma
     */
    Controller(Map m, List<Character> c) {
        this.map = m;
        this.characters = c;
    }

    /**
     * Setter a map attribútumhoz
     * @param map a map attribútum új értéke
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * characters attribútum settere
     * @param c characters új értéke
     */
    public void setCharacters(List<Character> c) {
        this.characters = c;
    }

    /**
     * Ezt a függvényt hívva indul el a játék. Addig fut, amíg a játéknak vége nincs,
     * avagy, amíg a "gameOver" változó true nem lesz.
     * Először a szobákat változtató függvények hívódnak, majd minden szoba kifejti a hatásáit, ha vannak.
     * Végül a pályán lévő karakterek körei játszódnak le, ami után az egész előlről indul.
     */
    public void GameRounds()
    {
        //Addig megy a játék amíg nincs gameover
        while (!gameOver){

                map.MergeRoom();
                map.SortRoom();

                HashMap<Node, List<Node>> gameMap= map.getAdjacencyList();
                for (Node room: gameMap.keySet()){
                    room.UseEffect(gameMap);
                }

                for(int i = 0; i < characters.size(); i++) {
                    characters.get(i).PlayerTurn();
                    if(characters.get(i).GetExpelled(characters)) {
                        i--;
                    }
                }

        }
    }

    /**
     * A gameOver változót állítja be true értékűre, ezzel jelezve a játék végét
     */
    public static void GameOver()
    {
        gameOver=true;

    }

    /**
     * Akkor hívódik, ha egy hallgató ki lett rúgva. Ez esetben csökkenti a hallgatók számának értékét eggyel.
     * Ha a hallgatók száma eléri a 0-t, akkor a gameOver változót true értékűre állítja, jelezve a játék végét.
     */
    public static void StudentLost(){
        studentNum--;       //EGy hallgató kiesett a játékból
        if(studentNum<=0){  //Ha nincs több hallgató akkor gameover
            gameOver=true;
        }
    }

    /**
     * gameOver setter
     * @param b gameOver új értéke
     */
    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    /**
     * gameOver getter
     * @return gameOver értékét adja vissza a függvény
     */
    public static boolean getGameOver() {return gameOver;}
}
