import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int tabNumber;
    public static void TabWriter(){
        Main.tabNumber++;
        for(int i = 0; i < Main.tabNumber; i++)
            System.out.print("\t");
    }

    public static void main(String[] args) {


        System.out.println("Add meg honnan olvasson be: 1) consol 2) fájl");
        Scanner scanner = new Scanner(System.in);
        String scan;
        scan = scanner.nextLine();

        if(scan.equals("1")) {
            System.out.println("Az Exit-tel lehet kilépni.");
            Proto p = new Proto();
            scan = scanner.nextLine();
            while (!scan.equals("Exit")) {

                String[] splited = scan.split(" ");

                switch (splited[0]) {
                    case "CreateRoom": {
                        p.CreateRoom(splited);
                        break;
                    }
                    case "SetRoomNeighbours": {
                        p.SetRoomNeighbours(splited);
                        break;
                    }
                    case "CreateObject": {
                        p.CreateObject(splited);
                        break;
                    }
                    case "CreateCharacter": {
                        p.CreateCharacter(splited);
                        break;
                    }
                    case "Move": {
                        p.Move(splited);
                        break;
                    }
                    case "Pickup": {
                        p.Pickup(splited);
                        break;
                    }
                    case "UseItem": {
                        p.UseItem(splited);
                        break;
                    }
                    case "WickedRoom": {
                        p.WickedRoom(splited);
                        break;
                    }
                    case "SortRoom": {
                        p.SortRoom(splited);
                        break;
                    }
                    case "MergeRoom": {
                        p.MergeRoom(splited);
                        break;
                    }
                    case "Stat": {
                        try {
                            p.Stat(splited[1]);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
                scan = scanner.nextLine();
            }

        } else {
            System.out.println("Add meg melyik fájlból olvasson be:");
            scan = scanner.nextLine();
            Proto proto= new Proto(scan +"_out.txt");
            proto.reading_from_file(scan);
        }

    }
}

