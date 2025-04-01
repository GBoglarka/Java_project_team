package Views;

import java.io.IOException;
import java.util.Scanner;
import Proto.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void NewGame(){
        MenuView menu = new MenuView();
        menu.MenuDraw();
    }

    public static void main(String[] args) {

        System.out.println("A szám beírásával add meg mit szeretnél elindítani: 1) Kész grafikus játék\t2) Proto");
        Scanner scann = new Scanner(System.in);
        String sc;
        sc = scann.nextLine();

        if(sc.equals("1")) {
            NewGame();
        }
        else if(sc.equals("2")) {

            System.out.println("Add meg honnan olvasson be: 1) consol 2) fájl");
            Scanner scanner = new Scanner(System.in);
            String scan;
            scan = scanner.nextLine();

            if (scan.equals("1")) {
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
                        default: {
                            System.out.println("Használható parancsok: CreateRoom, SetRoomNeighbours, CreateObject, CreateCharacter, " +
                                    "Move, Pickup, UseItem, WickedRoom, SortRoom, MergeRoom, Stat");
                            break;
                        }
                    }
                    scan = scanner.nextLine();
                }

            } else {
                System.out.println("Add meg melyik fájlból olvasson be:");
                scan = scanner.nextLine();
                Proto proto = new Proto(scan + "_out.txt");
                proto.reading_from_file(scan);
            }
        }

    }
}

