import java.io.Console;
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
        //TODO skeleton class
        //Map map = new Map();
        //Skeleton skeleton = new Skeleton();
        //skeleton.Setup();
        //skeleton.Teszt1();    OK
        //skeleton.Teszt2();    OK
        //skeleton.Teszt3();    OK
        //skeleton.Teszt4();    OK
        //skeleton.Teszt5();    OK
        //skeleton.Teszt6();    OK
        //skeleton.Teszt7();    OK
        //skeleton.Teszt8();    NEM OK!!
        //skeleton.Teszt9();    OK
        //skeleton.Teszt10();   OK
        //skeleton.Teszt11();   OK
        //skeleton.Teszt12();   OK?
        //skeleton.Teszt13();   OK
        //skeleton.Teszt14();   OK
        //skeleton.Teszt15();   OK
        //skeleton.Teszt16();   OK
        //skeleton.Teszt17();   OK
        //skeleton.Teszt18();   OK
        System.out.println("Add mega választott teszteset sorszámát(1-18)");
        System.out.println("1 - Init\n2 - DropItem\n3 - PlayerTurn Student\n4 - PlayerTurn Instructor\n5 - UseItem");
        System.out.println("6 - MergeRoom\n7 - SortRoom\n8 - RoomSpecial Wicked\n9 - RoomSpecial Poison\n10 - Transistor Ability\n11 - TVSZ Ability");
        System.out.println("12 - Rag Ability\n13 - Logarlec Ability\n14 - Camamber Ability\n15 - FFP2 Ability");
        System.out.println("16 - Beer Ability\n17 - GameRounds\n18 - PickupItem");
        /*System.out.println("1 - DropItem \n2 - Init\n3 - PlayerTurn(Student)\n4 - PlayerTurn(Instructor)\n5 - UseItem");
        System.out.println("6 - MergeRoom\n7 - SortRoom\n8 - WickedRoom\n9 - PoisonRoom\n10 - TVSZ Ability\n11 - Beer Ability");
        System.out.println("12 - Rag Ability\n13 - FFP2 Ability\n14 - Transistor Ability\n15 - Camamber Ability");
        System.out.println("16 - Logarlec Ability\n17 - GameRounds\n18 - PickupItem");*/
        Scanner scanner = new Scanner(System.in);


        String number = "1";
        while(!number.equals("0")) {
            System.out.println("Add meg a sorszámot: ");
            number = scanner.nextLine();

            Skeleton skeleton = new Skeleton();
            skeleton.Setup();
            switch (number) {
                case "1": skeleton.Teszt1(); break;
                case "2": skeleton.Teszt2(); break;
                case "3": skeleton.Teszt3(); break;
                case "4": skeleton.Teszt4(); break;
                case "5": skeleton.Teszt5(); break;
                case "6": skeleton.Teszt6(); break;
                case "7": skeleton.Teszt7(); break;
                case "8": skeleton.Teszt8(); break;
                case "9": skeleton.Teszt9(); break;
                case "10": skeleton.Teszt10(); break;
                case "11": skeleton.Teszt11(); break;
                case "12": skeleton.Teszt12(); break;
                case "13": skeleton.Teszt13(); break;
                case "14": skeleton.Teszt14(); break;
                case "15": skeleton.Teszt15(); break;
                case "16": skeleton.Teszt16(); break;
                case "17": skeleton.Teszt17(); break;
                case "18": skeleton.Teszt18(); break;
            }
        }
    }
}

