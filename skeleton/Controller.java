import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Controller {
    private Map map;
    private List<Character> characters;
    private static boolean gameOver = false;
    private static int studentNum;

    Controller(Map m, List<Character> c) {
        this.map = m;
        this.characters = c;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public void setCharacters(List<Character> c) {
        this.characters = c;
    }

    public void GameRounds()
    {
        Main.TabWriter();
        System.out.println("Controller.GameRounds()<void>");

        studentNum=2;

        Iterator<Character> iterator= characters.iterator();

        int rounds = 1;     //csak a tesztelés miatt kell, hogy egy kört futtasson
        //while (!gameOver){
        while (rounds != 0){
            if(iterator.hasNext()){
                Character ch= iterator.next();

                map.MergeRoom();
                map.SortRoom();

                HashMap<Node, List<Node>> gameMap= map.getAdjacencyList();
                for (Node room: gameMap.keySet()){
                    room.UseEffect(gameMap);
                }

                List<Character> help = characters;
                for(int i = 0; i < characters.size(); i++) {
                    characters.get(i).PlayerTurn();
                    if(characters.get(i).GetExpelled(characters)) {
                        i--;
                    }
                }
            }
            else{
                iterator = characters.iterator();
            }
            rounds--;
        }

        Main.tabNumber--;
    }
    public static void GameOver()
    {
        Main.TabWriter();
        System.out.println("Controller.GameOver()<void>");

        gameOver=true;

        Main.tabNumber--;
    }

    public static void StudentLost(){
        studentNum--;       //EGy hallgató kiesett a játékból
        if(studentNum<=0){  //Ha nincs több hallgató akkor gameover
            gameOver=true;
        }
    }

}
