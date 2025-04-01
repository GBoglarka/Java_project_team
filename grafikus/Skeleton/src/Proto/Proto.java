package Proto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import Models.*;

import Models.Object;
import Models.Character;

public class Proto {
    Map map;
    List<Character> characters;
    List<Node> nodes;
    List<Object> objects;
    FileWriter fileWriter;
    PrintWriter pw;

    public Proto() {
        map=new Map(10);
        characters = new ArrayList<>();
        nodes = new ArrayList<>();
        objects = new ArrayList<>();
    }
    public Proto(String file) {
        map=new Map(10);
        characters = new ArrayList<>();
        nodes = new ArrayList<>();
        objects = new ArrayList<>();
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pw = new PrintWriter(fileWriter);
        Controller.SetGameOver(false);
    }

    public void reading_from_file(String file) {
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splited = data.split(" ");
                //System.out.println(splited[0]+splited[1]+splited[2]);
                switch (splited[0]) {
                    case "CreateRoom": {
                        CreateRoom(splited);
                        break;
                    }
                    case "SetRoomNeighbours": {
                        SetRoomNeighbours(splited);
                        break;
                    }
                    case "CreateObject": {
                        CreateObject(splited);
                        break;
                    }
                    case "CreateCharacter": {
                        CreateCharacter(splited);
                        break;
                    }
                    case "Move": {
                        Move(splited);
                        break;
                    }
                    case "Pickup": {
                        Pickup(splited);
                        break;
                    }
                    case "UseItem": {
                        UseItem(splited);
                        break;
                    }
                    case "WickedRoom": {
                        WickedRoom(splited);
                        break;
                    }
                    case "SortRoom": {
                        SortRoom(splited);
                        break;
                    }
                    case "MergeRoom": {
                        MergeRoom(splited);
                        break;
                    }
                    case "Stat": {
                        Stat(splited[1]);
                        break;
                    }
                    default: {
                        if (pw != null) {
                            pw.println("Használható parancsok: CreateRoom, SetRoomNeighbours, CreateObject, CreateCharacter, " +
                                    "Move, Pickup, UseItem, WickedRoom, SortRoom, MergeRoom, Stat");
                            return;
                        }
                    }
                }
            }
            myReader.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateRoom(String[] args) {
        Node room1=new Node(args[1]);
        room1.SetCapacity(Integer.parseInt(args[2]));
        if(args.length > 3) {
            for(int i=3; i < args.length; i++) {
                switch (args[i]){
                    case "wicked": {
                        Effect ef= new WickedRoom();
                        room1.AddEffect(ef);
                        break;
                    }
                    case "poison": {
                        Effect ef= new PoisonRoom();
                        room1.AddEffect(ef);
                        break;
                    }
                    case "sticky": {
                        Effect ef= new StickyRoom();
                        room1.AddEffect(ef);
                        break;
                    }
                    default: {
                        if(pw != null) {
                            pw.println("Létrehozható szoba effektek: wicked, poison, sticky");
                            return;
                        } else {
                            System.out.println("Létrehozható szoba effektek: wicked, poison, sticky");
                            return;
                        }
                    }
                }
            }
        }
        map.AddRoom(room1);
        nodes.add(room1);
        if(pw != null) {
            pw.println(room1.GetId()+"@"+room1.GetId()+" Node created");
        } else System.out.println(room1.GetId()+"@"+room1.GetId()+" Node created");

        //pw.close();
    }

    public void SetRoomNeighbours(String[] args) {
        Node room = null;
        List<Node> neighbours = new ArrayList<>();
        for(Node n: nodes) {
            if(n.GetId().equals(args[1])) {
                room=n;
            }
        }
        for(int i=2; i < args.length; i++) {
            Node neigh = null;
            for(Node n: nodes) {
                if(n.GetId().equals(args[i])) {
                    neigh=n;
                }
            }
            if(neigh != null) neighbours.add(neigh);
        }
        map.SetAdjacencyList(room, neighbours);
        if(pw != null) {
            pw.println(room.GetId()+"@"+room.GetId()+" neighbours has been set");
        } else System.out.println(room.GetId()+"@"+room.GetId()+" neighbours has been set");

    }

    public void CreateObject(String[] args) {
        Object obj = null;
        switch (args[2]){
            case "Beer": {
                obj=new Beer(args[1]);
                break;
            }
            case "Transistor" : {
                obj = new Transistor(args[1]);
                break;
            }
            case "TVSZ" : {
                obj = new TVSZ(args[1]);
                break;
            }
            case "Rag": {
                obj = new Rag(args[1]);
                break;
            }
            case "Logarlec": {
                obj = new Logarlec(args[1]);
                break;
            }
            case "Camamber" : {
                obj = new Camamber(args[1]);
                break;
            }
            case "FFP2" : {
                obj = new FFP2(args[1]);
                break;
            }
            case "AirFreshener": {
                obj=new AirFreshener(args[1]);
                break;
            }
            default: {
                if(pw != null) {
                    pw.println("Létrehozható tárgyak: Beer, Transistor, TVSZ, Rag, Logarlec, Camamber, FFP2, AirFreshener");
                    return;
                } else {
                    System.out.println("Létrehozható tárgyak: Beer, Transistor, TVSZ, Rag, Logarlec, Camamber, FFP2, AirFreshener");
                    return;
                }
            }
        }
        obj.SetFake(Boolean.parseBoolean(args[3]));
        Node roomin = null;
        if(args.length>4) {
            for(Node n: nodes) {
                if(n.GetId().equals(args[4])) {
                    roomin=n;
                }
            }
        }
        obj.SetRoomin(roomin);
        if(obj.IsFake()) {
            if (roomin != null) {
                roomin.AddObject(obj);
                if(pw != null) {
                    pw.println(obj.GetId() + "@" + obj.GetId() + " fake " + args[2] + " created in " + obj.GetRoomin().GetId());
                } else
                    System.out.println(obj.GetId() + "@" + obj.GetId() + " fake " + args[2] + " created in " + obj.GetRoomin().GetId());
            } else {
                if(pw != null) {
                    pw.println(obj.GetId() + "@" + obj.GetId() + " fake " + args[2] + " created");
                } else
                    System.out.println(obj.GetId() + "@" + obj.GetId() + " fake " + args[2] + " created");
            }
        }
        else  {
            if (roomin != null) {
                roomin.AddObject(obj);
                if(pw != null) {
                    pw.println(obj.GetId() + "@" + obj.GetId() + " " + args[2] + " created in " + obj.GetRoomin().GetId());
                } else
                    System.out.println(obj.GetId() + "@" + obj.GetId() + " " + args[2] + " created in " + obj.GetRoomin().GetId());
            } else {
                if(pw != null) {
                    pw.println(obj.GetId() + "@" + obj.GetId() + " " + args[2] + " created");
                } else
                    System.out.println(obj.GetId() + "@" + obj.GetId() + " " + args[2] + " created");
            }
        }
        objects.add(obj);

    }

    public void CreateCharacter(String[] args) {
        Character ch = null;
        switch (args[2]) {
            case "Student": {
                ch = new Student(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.GetId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.SetRoom(roomin);
                roomin.GetMembers().add(ch);
                break;
            }
            case "Instructor": {
                ch= new Instructor(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.GetId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.SetRoom(roomin);
                roomin.GetMembers().add(ch);
                //roomin.setNumIns(roomin.getNumIns()+1);
                break;
            }
            case "Cleaner": {
                ch = new Cleaner(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.GetId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.SetRoom(roomin);
                roomin.GetMembers().add(ch);
                roomin.SetNumCleaner(roomin.GetNumCleaner()+1);
                break;
            }
            default: {
                if(pw != null) {
                    pw.println("Létrehozható karakterek: Student, Instructor, Cleaner");
                    return;
                } else {
                    System.out.println("Létrehozható karakterek: Student, Instructor, Cleaner");
                    return;
                }
            }
        }


        if(args.length>4) {
            for (int i = 4; i< args.length; i++) {
                for(Object o: objects) {
                    if(o.GetId().equals(args[i])) {
                        ch.GetInventory().add(o);
                    }
                }
            }
            if(pw != null) {
                pw.print(ch.GetId()+"@"+ ch.GetId() + " " + args[2] + " created in " + ch.GetRoom().GetId()+ " with");
            } else
                System.out.print(ch.GetId()+"@"+ ch.GetId() + " " + args[2] + " created in " + ch.GetRoom().GetId()+ " with");
            List<Object> inv = ch.GetInventory();
            for(Object o: inv) {
                if(pw != null) {
                    pw.print(" " + o.GetId());
                } else
                    System.out.print(" " + o.GetId());
            }
            if(pw != null) {
                pw.print("\n");
            } else System.out.print("\n");
        }
        else {
            if(pw != null) {
                pw.println(ch.GetId()+"@"+ ch.GetId() + " " + args[2] + " created in " + ch.GetRoom().GetId() + " with no Objects");
            } else
                System.out.println(ch.GetId()+"@"+ ch.GetId() + " " + args[2] + " created in " + ch.GetRoom().GetId() + " with no Objects");
        }
        characters.add(ch);

    }

    public void Move(String[] args) {
        Character ch = null;
        Node roomfrom = null;
        Node roomto = null;
        for(Character c: characters) {
            if(c.GetId().equals(args[2])) {
                ch = c;
                roomfrom = c.GetRoom();
            }
        }
        for(Node n: nodes) {
            if(n.GetId().equals(args[1])) {
                roomto = n;
            }
        }
        int effectsize = roomto.GetEffects().size();
        ch.Move(roomto);
        //roomto.UseEffect(map.getAdjacencyList());
        if(pw != null) {
            pw.print(ch.GetId()+"@"+ch.GetId()+" moved from " + roomfrom.GetId()+" to "+ch.GetRoom().GetId());
        } else
            System.out.print(ch.GetId()+"@"+ch.GetId()+" moved from " + roomfrom.GetId()+" to "+ch.GetRoom().GetId());

        if(!ch.GetRoom().GetMembers().contains(ch) && !ch.GetInsProtected() && ch.GetInventory().size() == 0) {
        //if(!.contains(ch)) {
            if(pw != null) {
                pw.print(", kicked out");
            } else System.out.print(", kicked out");
        }

        if(effectsize > roomto.GetEffects().size()) {
            if(pw != null) {
                pw.print(", " +roomto.GetId()+ " is now clean");
            } else
                System.out.print(", "+roomto.GetId()+ " is now clean");
        }
        if(pw != null){
            pw.print("\n");
        } else
            System.out.print("\n");
    }

    public void Pickup(String[] args) {
        Character ch = null;
        Object obj = null;
        boolean canPickUp = true;
        int originalInvSize=0, newInvSize=0;
        for(Character c: characters) {
            if(c.GetId().equals(args[2])) {
                ch = c;
            }
        }
        for(Object o: objects) {
            if(o.GetId().equals(args[1])) {
                obj = o;
            }
        }
        List<Effect> effects = ch.GetRoom().GetEffects();
        if(effects != null) {
            for (Effect e : effects) {
                e.RoomSpecial(map.GetAdjacencyList(), ch.GetRoom());
            }
        }

        if(ch!=null && canPickUp) {
            originalInvSize = ch.GetInventory().size();
            ch.PickupItem(obj);
            newInvSize = ch.GetInventory().size();
        }




        if(originalInvSize+1 == newInvSize) {
            if(pw != null) {
                pw.println(ch.GetId() + "@" + ch.GetId() + " picked up " + obj.GetId() + " object");
            } else
                System.out.println(ch.GetId() + "@" + ch.GetId() + " picked up " + obj.GetId() + " object");
        }else {
            if(pw != null) {
                pw.println(ch.GetId() + "@" + ch.GetId() + " can't pick up " + obj.GetId());
            } else
                System.out.println(ch.GetId() + "@" + ch.GetId() + " can't pick up " + obj.GetId() );
        }
    }

    public void UseItem(String[] args) {
        Character ch = null;
        Object obj = null;
        for(Character c: characters) {
            if(c.GetId().equals(args[2])) {
                ch = c;
            }
        }
        for(Object o: objects) {
            if(o.GetId().equals(args[1])) {
                obj = o;
            }
        }
        int originalEffectsNum = ch.GetRoom().GetEffects().size();
        int originalInvNum = ch.GetInventory().size();
        if(ch!=null) {
            ch.UseItem(obj);
            ch.GetRoom().UseEffect(map.GetAdjacencyList());

        }

        if(pw != null) {
            pw.print(ch.GetId() + "@" + ch.GetId() + " used " + obj.GetId() + " object");
            if(!ch.GetRoom().GetMembers().contains(ch) && !ch.GetInsProtected()) {
                pw.println(", kicked out");
            }
            else if(ch.GetRoom().GetEffects().size() > originalEffectsNum) {
                pw.println(", "+ ch.GetRoom().GetId()+ " is now poisoned");
            }
            else if(ch.GetRoom().GetEffects().size() < originalEffectsNum) {
                pw.println(", "+ ch.GetRoom().GetId()+ " now is not poisoned");
            }

            else if(ch.GetInsProtected() && ch.GetRoom().GetNumIns() > 0){
                pw.println(", avoided getting kicked out");
            }
            else if(ch.GetPoisonProtected()) {
                pw.println(", protected from the poison");
            }
            else if(!ch.GetPoisonProtected() && ch.GetInventory().size() == 0 && ch.GetRoom().GetEffects().size() > 0) {
                pw.println(", got poisoned");
            }
            else if(Controller.GetGameOver()) {
                pw.println(", the game is over");
            }
            else
            {
                pw.println();
            }
        } else {
            System.out.print(ch.GetId() + "@" + ch.GetId() + " used " + obj.GetId() + " object");
            if(ch.GetRoom().Conflict() && !ch.GetInsProtected()) {
                System.out.println(", kicked out");
            }
            else if(ch.GetRoom().GetEffects().size() > originalEffectsNum) {
                System.out.println(", "+ ch.GetRoom().GetId()+ " is now poisoned");
            }
            else if(ch.GetRoom().GetEffects().size() < originalEffectsNum) {
                System.out.println(", "+ ch.GetRoom().GetId()+ " now is not poisoned");
            }
            else if(Controller.GetGameOver()) {
                System.out.println(", the game is over");
            }
            else if(ch.GetInsProtected() && ch.GetRoom().GetNumIns() > 0){
                System.out.println(", avoided getting kicked out");
            }
            else if(ch.GetPoisonProtected()) {
                System.out.println(", protected from the poison");
            }
            else if(!ch.GetPoisonProtected() && ch.GetInventory().size() == 0) {
                System.out.println(", got poisoned");
            }
            else
            {
                System.out.println();
            }
        }

    }
    void Drop() {}
    public void WickedRoom(String[] args) {
        Node room = null;
        Node neigh = null;
        for(Node n: nodes) {
            if(n.GetId().equals(args[1])) {
                room = n;
            }
            if(n.GetId().equals(args[2])) {
                neigh = n;
            }
        }
        HashMap<Node, List<Node>> adjacency = map.GetAdjacencyList();
        List<Effect> effects = room.GetEffects();
        for(Effect e: effects) {
            if(e.toString().equals("wicked")) {
                e.RoomSpecial(adjacency, room, neigh);
                map.SetAdjacencyList(room, adjacency.get(room));
            }
        }
        if(pw != null) {
            pw.print(room.GetId()+"@"+room.GetId()+ " - "+neigh.GetId()+" door");
            if(map.GetAdjacencyList().get(room).contains(neigh)) {
                pw.println(" appeared");
            } else {
                pw.println(" disappeared");
            }
        } else {
            System.out.print(room.GetId() + "@" + room.GetId() + " - " + neigh.GetId() + " door");
            if (map.GetAdjacencyList().get(room).contains(neigh)) {
                System.out.println(" appeared");
            } else {
                System.out.println(" disappeared");
            }
        }
    }

    public void SortRoom(String[] args) {
        Node room = null;
        for(Node n: nodes) {
            if (n.GetId().equals(args[1])) {
                room = n;
            }
        }
        Node newRoom = map.SortRoom(room);
        nodes.add(newRoom);
        if(pw != null) {
            pw.println(room.GetId()+"@"+room.GetId()+" Sorted, new Node "+newRoom.GetId());
            pw.println(newRoom.GetId()+"@"+newRoom.GetId()+" Node created");
        } else {
            System.out.println(room.GetId() + "@" + room.GetId() + " Sorted, new Node " + newRoom.GetId());
            System.out.println(newRoom.GetId() + "@" + newRoom.GetId() + " Node created");
        }
    }

    public void MergeRoom(String[] args) {
        Node room1 = null;
        Node room2=null;
        for(Node n: nodes) {
            if (n.GetId().equals(args[1])) {
                room1 = n;
            }
            if (n.GetId().equals(args[2])) {
                room2 = n;
            }
        }
        map.MergeRoom(room1,room2);
        if(pw != null) {
            pw.println(room1.GetId()+"@"+room1.GetId()+" and "+room2.GetId()+" has been merged together into "+room1.GetId());
        } else
            System.out.println(room1.GetId()+"@"+room1.GetId()+" and "+room2.GetId()+" has been merged together into "+room1.GetId());
    }

    //Nem használtuk sehol sem
    void Load(String[] args) {}


    public void Stat(String arg) throws IOException {

        if(pw != null) {
            if (arg.equals("map")) {
                pw.println("Rooms:");

                for (Node n : map.GetAdjacencyList().keySet()) {
                    pw.print(n.GetId() + "\n\tCapacity: " + n.GetCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        pw.print(" " + c.GetId());
                    }
                    pw.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        pw.print(" " + o.GetId());
                    }
                    pw.print("\n\tEffects:");
                    for (Effect e : n.GetEffects()) {
                        pw.print(" " + e.toString());
                    }
                    pw.print("\n\tNeighbours:");
                    for (Node r : map.GetAdjacencyList().get(n)) {
                        pw.print(" " + r.GetId());
                    }
                    pw.print("\n");
                }
                pw.println("Characters:");
                for (Character c : characters) {
                    pw.print(c.GetId() + "\n\tRoom: " + c.GetRoom().GetId() + "\n\tInventory:");
                    for (Object o : c.GetInventory()) {
                        pw.print(" " + o.GetId());
                    }
                    pw.print("\n");
                }

            } else {
                Character ch = null;
                Node n = null;
                for (Character c : characters) {
                    if (c.GetId().equals(arg)) {
                        ch = c;
                    }
                }
                for (Node node : nodes) {
                    if (node.GetId().equals(arg)) {
                        n = node;
                    }
                }
                if (ch != null) {
                    pw.print(ch.GetId() + "\n\tRoom: " + ch.GetRoom().GetId() + "\n\tInventory:");
                    for (Object o : ch.GetInventory()) {
                        pw.print(" " + o.GetId());
                    }
                    pw.print("\n");
                }
                if (n != null) {
                    pw.print(n.GetId() + "\n\tCapacity: " + n.GetCapacity() + "\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        pw.print(" " + c.GetId());
                    }
                    pw.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        pw.print(" " + o.GetId());
                    }
                    pw.print("\n\tEffects:");
                    for (Effect e : n.GetEffects()) {
                        pw.print(" " + e.toString());
                    }
                    pw.print("\n\tNeighbours:");
                    for (Node r : map.GetAdjacencyList().get(n)) {
                        pw.print(" " + r.GetId());
                    }
                    pw.print("\n");
                }
            }
        } else {
            if (arg.equals("map")) {
                System.out.println("Rooms:");
                //pw.println("Rooms:");

                for (Node n : map.GetAdjacencyList().keySet()) {
                    System.out.print(n.GetId() + "\n\tCapacity: " + n.GetCapacity() + "\n\tCharacters:");
                    //pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        System.out.print(" " + c.GetId());
                    }
                    System.out.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        System.out.print(" " + o.GetId());
                    }
                    System.out.print("\n\tEffects:");
                    for (Effect e : n.GetEffects()) {
                        System.out.print(" " + e.toString());
                    }
                    System.out.print("\n\tNeighbours:");
                    for (Node r : map.GetAdjacencyList().get(n)) {
                        System.out.print(" " + r.GetId());
                    }
                    System.out.print("\n");
                }
                System.out.println("Characters:");
                for (Character c : characters) {
                    System.out.print(c.GetId() + "\n\tRoom: " + c.GetRoom().GetId() + "\n\tInventory:");
                    for (Object o : c.GetInventory()) {
                        System.out.print(" " + o.GetId());
                    }
                    System.out.print("\n");
                }

            } else {
                Character ch = null;
                Node n = null;
                for (Character c : characters) {
                    if (c.GetId().equals(arg)) {
                        ch = c;
                    }
                }
                for (Node node : nodes) {
                    if (node.GetId().equals(arg)) {
                        n = node;
                    }
                }
                if (ch != null) {
                    System.out.print(ch.GetId() + "\n\tRoom: " + ch.GetRoom().GetId() + "\n\tInventory:");
                    for (Object o : ch.GetInventory()) {
                        System.out.print(" " + o.GetId());
                    }
                    System.out.print("\n");
                }
                if (n != null) {
                    System.out.print(n.GetId() + "\n\tCapacity: " + n.GetCapacity() + "\n\tCharacters:");
                    //pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        System.out.print(" " + c.GetId());
                    }
                    System.out.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        System.out.print(" " + o.GetId());
                    }
                    System.out.print("\n\tEffects:");
                    for (Effect e : n.GetEffects()) {
                        System.out.print(" " + e.toString());
                    }
                    System.out.print("\n\tNeighbours:");
                    for (Node r : map.GetAdjacencyList().get(n)) {
                        System.out.print(" " + r.GetId());
                    }
                    System.out.print("\n");
                }
            }
        }
    }


}

