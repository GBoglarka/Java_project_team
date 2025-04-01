package Models;

import Views.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Controller {
    private Map map;
    private static List<Character> characters;
    private static boolean gameOver;
    private static int studentNum;
    private GameView game;
    private Character player;
    private int rounds=0;

    /**
     * A Controller osztály konstruktora
     * @param m A játékhoz tartozó objektum Map, ezt az értéket kapja a Controller map attribútuma
     * @param c A játékban résztvevő karakterek listája, ezt az értéket kapja a Controller characters attribtuma
     */
    public Controller(Map m, List<Character> c) {
        this.map = m;
        characters = c;
    }

    /**
     * A Controller osztály paraméter nélküli konstruktora
     */
    public Controller(){
        characters= new ArrayList<>();
        gameOver = false;
        studentNum=0;
        map= new Map();
        game=new GameView(this);
    }

    /**
     * Setter a map attribútumhoz
     * @param map a map attribútum új értéke
     */
    public void SetMap(Map map) {
        this.map = map;
    }

    /**
     * characters attribútum settere
     * @param c characters új értéke
     */
    public void SetCharacters(List<Character> c) {
        characters = c;
    }

    /**
     * Ez a metódus végrehajtja a játék köröket
     * Először a szobákat változtató függvények hívódnak, majd minden szoba kifejti a hatásáit, ha vannak.
     * Végül a pályán lévő karakterek körei játszódnak le, ami után frissíti a megjelenést
     */
    public void GameRounds()
    {
        if(rounds%2==0)
            map.MergeRoom();
        else
            map.SortRoom();

        HashMap<Node, List<Node>> gameMap= map.GetAdjacencyList();
        for (Node room: gameMap.keySet()){
            room.UseEffect(gameMap);
        }

        for(int i = 0; i < characters.size(); i++) {
            if(characters.get(i).GetExpelled(characters)) {
                i--;
            }
        }

        if(characters.indexOf(player)==characters.size()-1){
            player= characters.get(0);
        }else{
            player= characters.get(characters.indexOf(player)+1);
        }

        game.GameDraw(player, map);
        rounds++;
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
     * @param c Az a karakter, amelyik kirúgásra került
     */
    public static void StudentLost(Character c){
        if(characters != null) characters.remove(c);
        studentNum--;
        if(studentNum<=0){  //Ha nincs több hallgató akkor gameover
            gameOver=true;
        }
    }

    /**
     * gameOver setter
     * @param b gameOver új értéke
     */
    public static void SetGameOver(boolean b) {
        gameOver = b;
    }

    /**
     * gameOver getter
     * @return gameOver értékét adja vissza a függvény
     */
    public static boolean GetGameOver() {return gameOver;}

    /**
     * Új karaktert adunk az élő karakterek listájához
     * @param c A felvenni kívánt Character
     */
    public void AddCharacter(Character c) {characters.add(c);}

    /**
     * characters gettere
     * @return characters értéke, a játékban lévő karakterek listája
     */
    public List<Character> GetCharacters() {
        return characters;
    }

    /**
     * game gettere
     * @return A Controllerhez tartozó GameView-t adja vissza
     */
    public GameView getGame() {
        return game;
    }

    /**
     * A studentNum értékét növeli eggyel
     */
    public void AddStudentNum(){studentNum++;}

    /**
     * studentNum gettere
     * @return A játékban lévő hallgatók számával tér vissza
     */
    public static int GetStudentNum() {
        return studentNum;
    }

    /**
     * Elhelyezi a játékban lévő karaktereket random szobákba és beállítja a kezdő karaktert
     */
    public void PlaceStudents(){
        Random rand = new Random();
        List<Node> nodes= map.GetAdjacencyList().keySet().stream().toList();
        List<Node> placedNodes= new ArrayList<>();
        for(Character c: characters) {
            Node node=nodes.get(rand.nextInt(nodes.size()));
            while (placedNodes.contains(node)) {
                node=nodes.get(rand.nextInt(nodes.size()));
            }
            c.SetRoom(node);
            node.GetMembers().add(c);
            placedNodes.add(node);
        }
        player= characters.get(0);
    }

    /**
     * player gettere
     * @return visszatér azzal a karakterrel, akinek épp a köre van
     */
    public Character GetPlayer()
    {
        return player;
    }

    /**
     * player settere
     * @param c erre állítja be az aktuális játékost
     */
    public void SetPlayer(Character c) {
        player = c;
    }
}
