import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Proto {
    Map map;
    List<Character> characters;
    List<Node> nodes;
    List<Object> objects;
    FileWriter fileWriter;
    PrintWriter pw;

    Proto() {
        map=new Map(10);
        characters = new ArrayList<>();
        nodes = new ArrayList<>();
        objects = new ArrayList<>();
    }
    Proto(String file) {
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
    }

    void reading_from_file(String file) {
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

    void CreateRoom(String[] args) {
        Node room1=new Node(args[1]);
        room1.setCapacity(Integer.parseInt(args[2]));
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
                }
            }
        }
        map.AddRoom(room1);
        nodes.add(room1);
        if(pw != null) {
            pw.println(room1.getId()+"@"+room1.getId()+" Node created");
        } else System.out.println(room1.getId()+"@"+room1.getId()+" Node created");

        //pw.close();
    }

    void SetRoomNeighbours(String[] args) {
        Node room = null;
        List<Node> neighbours = new ArrayList<>();
        for(Node n: nodes) {
            if(n.getId().equals(args[1])) {
                room=n;
            }
        }
        for(int i=2; i < args.length; i++) {
            Node neigh = null;
            for(Node n: nodes) {
                if(n.getId().equals(args[i])) {
                    neigh=n;
                }
            }
            if(neigh != null) neighbours.add(neigh);
        }
        map.setAdjacencyList(room, neighbours);
        if(pw != null) {
            pw.println(room.getId()+"@"+room.getId()+" neighbours has been set");
        } else System.out.println(room.getId()+"@"+room.getId()+" neighbours has been set");

    }

    void CreateObject(String[] args) {
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
        }
        obj.setFake(Boolean.parseBoolean(args[3]));
        Node roomin = null;
        if(args.length>4) {
            for(Node n: nodes) {
                if(n.getId().equals(args[4])) {
                    roomin=n;
                }
            }
        }
        obj.setRoomin(roomin);
        if(obj.isFake()) {
            if (roomin != null) {
                roomin.AddObject(obj);
                if(pw != null) {
                    pw.println(obj.getId() + "@" + obj.getId() + " fake " + args[2] + " created in " + obj.GetRoomin().getId());
                } else
                    System.out.println(obj.getId() + "@" + obj.getId() + " fake " + args[2] + " created in " + obj.GetRoomin().getId());
            } else {
                if(pw != null) {
                    pw.println(obj.getId() + "@" + obj.getId() + " fake " + args[2] + " created");
                } else
                    System.out.println(obj.getId() + "@" + obj.getId() + " fake " + args[2] + " created");
            }
        }
        else  {
            if (roomin != null) {
                roomin.AddObject(obj);
                if(pw != null) {
                    pw.println(obj.getId() + "@" + obj.getId() + " " + args[2] + " created in " + obj.GetRoomin().getId());
                } else
                    System.out.println(obj.getId() + "@" + obj.getId() + " " + args[2] + " created in " + obj.GetRoomin().getId());
            } else {
                if(pw != null) {
                    pw.println(obj.getId() + "@" + obj.getId() + " " + args[2] + " created");
                } else
                    System.out.println(obj.getId() + "@" + obj.getId() + " " + args[2] + " created");
            }
        }
        objects.add(obj);

    }

    void CreateCharacter(String[] args) {
        Character ch = null;
        switch (args[2]) {
            case "Student": {
                ch = new Student(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.getId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.setRoom(roomin);
                roomin.GetMembers().add(ch);
                break;
            }
            case "Instructor": {
                ch= new Instructor(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.getId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.setRoom(roomin);
                roomin.GetMembers().add(ch);
                roomin.setNumIns(roomin.getNumIns()+1);
                break;
            }
            case "Cleaner": {
                ch = new Cleaner(args[1]);
                Node roomin = null;
                for(Node n: nodes) {
                    if(n.getId().equals(args[3])) {
                        roomin=n;
                    }
                }

                ch.setRoom(roomin);
                roomin.GetMembers().add(ch);
                roomin.setNumCleaner(roomin.getNumCleaner()+1);
                break;
            }
        }


        if(args.length>4) {
            for (int i = 4; i< args.length; i++) {
                for(Object o: objects) {
                    if(o.getId().equals(args[i])) {
                        ch.GetInventory().add(o);
                    }
                }
            }
            if(pw != null) {
                pw.print(ch.getId()+"@"+ ch.getId() + " " + args[2] + " created in " + ch.getRoom().getId()+ " with");
            } else
                System.out.print(ch.getId()+"@"+ ch.getId() + " " + args[2] + " created in " + ch.getRoom().getId()+ " with");
            List<Object> inv = ch.GetInventory();
            for(Object o: inv) {
                if(pw != null) {
                    pw.print(" " + o.getId());
                } else
                    System.out.print(" " + o.getId());
            }
            if(pw != null) {
                pw.print("\n");
            } else System.out.print("\n");
        }
        else {
            if(pw != null) {
                pw.println(ch.getId()+"@"+ ch.getId() + " " + args[2] + " created in " + ch.getRoom().getId() + " with no Objects");
            } else
                System.out.println(ch.getId()+"@"+ ch.getId() + " " + args[2] + " created in " + ch.getRoom().getId() + " with no Objects");
        }
        characters.add(ch);

    }

    void Move(String[] args) {
        Character ch = null;
        Node roomfrom = null;
        Node roomto = null;
        for(Character c: characters) {
            if(c.getId().equals(args[2])) {
                ch = c;
                roomfrom = c.getRoom();
            }
        }
        for(Node n: nodes) {
            if(n.getId().equals(args[1])) {
                roomto = n;
            }
        }
        int effectsize = roomto.getEffects().size();
        ch.Move(roomto);
        //roomto.UseEffect(map.getAdjacencyList());
        if(pw != null) {
            pw.print(ch.getId()+"@"+ch.getId()+" moved from " + roomfrom.getId()+" to "+ch.getRoom().getId());
        } else
            System.out.print(ch.getId()+"@"+ch.getId()+" moved from " + roomfrom.getId()+" to "+ch.getRoom().getId());

        if(ch.getRoom().Conflict() && !ch.GetInsProtected() && ch.GetInventory().size() == 0) {
            if(pw != null) {
                pw.print(", kicked out");
            } else System.out.print(", kicked out");
        }

        if(effectsize > roomto.getEffects().size()) {
            if(pw != null) {
                pw.print(", " +roomto.getId()+ " is now clean");
            } else
                System.out.print(", "+roomto.getId()+ " is now clean");
        }
        if(pw != null){
            pw.print("\n");
        } else
            System.out.print("\n");
    }

    void Pickup(String[] args) {
        Character ch = null;
        Object obj = null;
        boolean canPickUp = true;
        int originalInvSize=0, newInvSize=0;
        for(Character c: characters) {
            if(c.getId().equals(args[2])) {
                ch = c;
            }
        }
        for(Object o: objects) {
            if(o.getId().equals(args[1])) {
                obj = o;
            }
        }
        List<Effect> effects = ch.getRoom().getEffects();
        if(effects != null) {
            for (Effect e : effects) {
                e.RoomSpecial(map.getAdjacencyList(), ch.getRoom());
            }
        }

        if(ch!=null && canPickUp) {
            originalInvSize = ch.GetInventory().size();
            ch.PickupItem(obj);
            newInvSize = ch.GetInventory().size();
        }

        //TODO sticky room ellenőrzés


        if(originalInvSize+1 == newInvSize) {
            if(pw != null) {
                pw.println(ch.getId() + "@" + ch.getId() + " picked up " + obj.getId() + " object");
            } else
                System.out.println(ch.getId() + "@" + ch.getId() + " picked up " + obj.getId() + " object");
        }else {
            if(pw != null) {
                pw.println(ch.getId() + "@" + ch.getId() + " can't pick up " + obj.getId());
            } else
                System.out.println(ch.getId() + "@" + ch.getId() + " can't pick up " + obj.getId() );
        }
    }

    void UseItem(String[] args) {
        Character ch = null;
        Object obj = null;
        for(Character c: characters) {
            if(c.getId().equals(args[2])) {
                ch = c;
            }
        }
        for(Object o: objects) {
            if(o.getId().equals(args[1])) {
                obj = o;
            }
        }
        int originalEffectsNum = ch.getRoom().getEffects().size();
        int originalInvNum = ch.GetInventory().size();
        if(ch!=null) {
            ch.UseItem();
            ch.getRoom().UseEffect(map.getAdjacencyList());

        }

        if(pw != null) {
            pw.print(ch.getId() + "@" + ch.getId() + " used " + obj.getId() + " object");
            if(ch.getRoom().Conflict() && !ch.GetInsProtected()) {
                pw.println(", kicked out");
            }
            else if(ch.getRoom().getEffects().size() > originalEffectsNum) {
                pw.println(", "+ ch.getRoom().getId()+ " is now poisoned");
            }
            else if(ch.getRoom().getEffects().size() < originalEffectsNum) {
                pw.println(", "+ ch.getRoom().getId()+ " now is not poisoned");
            }
            else if(Controller.getGameOver()) {
                pw.println(", the game is over");
            }
            else if(ch.GetInsProtected() && ch.getRoom().getNumIns() > 0){
                pw.println(", avoided getting kicked out");
            }
            else if(ch.GetPoisonProtected()) {
                pw.println(", protected from the poison");
            }
            else if(!ch.GetPoisonProtected() && ch.GetInventory().size() == 0) {
                pw.println(", got poisoned");
            }
            else
            {
                pw.println();
            }
        } else {
            System.out.print(ch.getId() + "@" + ch.getId() + " used " + obj.getId() + " object");
            if(ch.getRoom().Conflict() && !ch.GetInsProtected()) {
                System.out.println(", kicked out");
            }
            else if(ch.getRoom().getEffects().size() > originalEffectsNum) {
                System.out.println(", "+ ch.getRoom().getId()+ " is now poisoned");
            }
            else if(ch.getRoom().getEffects().size() < originalEffectsNum) {
                System.out.println(", "+ ch.getRoom().getId()+ " now is not poisoned");
            }
            else if(Controller.getGameOver()) {
                System.out.println(", the game is over");
            }
            else if(ch.GetInsProtected() && ch.getRoom().getNumIns() > 0){
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
    void WickedRoom(String[] args) {
        Node room = null;
        Node neigh = null;
        for(Node n: nodes) {
            if(n.getId().equals(args[1])) {
                room = n;
            }
            if(n.getId().equals(args[2])) {
                neigh = n;
            }
        }
        HashMap<Node, List<Node>> adjacency = map.getAdjacencyList();
        List<Effect> effects = room.getEffects();
        for(Effect e: effects) {
            if(e.toString().equals("wicked")) {
                e.RoomSpecial(adjacency, room, neigh);
                map.setAdjacencyList(room, adjacency.get(room));
            }
        }
        if(pw != null) {
            pw.print(room.getId()+"@"+room.getId()+ " - "+neigh.getId()+" door");
            if(map.getAdjacencyList().get(room).contains(neigh)) {
                pw.println(" appeared");
            } else {
                pw.println(" disappeared");
            }
        } else {
            System.out.print(room.getId() + "@" + room.getId() + " - " + neigh.getId() + " door");
            if (map.getAdjacencyList().get(room).contains(neigh)) {
                System.out.println(" appeared");
            } else {
                System.out.println(" disappeared");
            }
        }
    }

    void SortRoom(String[] args) {
        Node room = null;
        for(Node n: nodes) {
            if (n.getId().equals(args[1])) {
                room = n;
            }
        }
        Node newRoom = map.SortRoom(room);
        nodes.add(newRoom);
        if(pw != null) {
            pw.println(room.getId()+"@"+room.getId()+" Sorted, new Node "+newRoom.getId());
            pw.println(newRoom.getId()+"@"+newRoom.getId()+" Node created");
        } else {
            System.out.println(room.getId() + "@" + room.getId() + " Sorted, new Node " + newRoom.getId());
            System.out.println(newRoom.getId() + "@" + newRoom.getId() + " Node created");
        }
    }

    void MergeRoom(String[] args) {
        Node room1 = null;
        Node room2=null;
        for(Node n: nodes) {
            if (n.getId().equals(args[1])) {
                room1 = n;
            }
            if (n.getId().equals(args[2])) {
                room2 = n;
            }
        }
        map.MergeRoom(room1,room2);
        if(pw != null) {
            pw.println(room1.getId()+"@"+room1.getId()+" and "+room2.getId()+" has been merged together into "+room1.getId());
        } else
            System.out.println(room1.getId()+"@"+room1.getId()+" and "+room2.getId()+" has been merged together into "+room1.getId());
    }

    //Nem használtuk sehol sem
    void Load(String[] args) {}


    void Stat(String arg) throws IOException {

        if(pw != null) {
            if (arg.equals("map")) {
                pw.println("Rooms:");

                for (Node n : map.getAdjacencyList().keySet()) {
                    pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        pw.print(" " + c.getId());
                    }
                    pw.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        pw.print(" " + o.getId());
                    }
                    pw.print("\n\tEffects:");
                    for (Effect e : n.getEffects()) {
                        pw.print(" " + e.toString());
                    }
                    pw.print("\n\tNeighbours:");
                    for (Node r : map.getAdjacencyList().get(n)) {
                        pw.print(" " + r.getId());
                    }
                    pw.print("\n");
                }
                pw.println("Characters:");
                for (Character c : characters) {
                    pw.print(c.getId() + "\n\tRoom: " + c.getRoom().getId() + "\n\tInventory:");
                    for (Object o : c.GetInventory()) {
                        pw.print(" " + o.getId());
                    }
                    pw.print("\n");
                }

            } else {
                Character ch = null;
                Node n = null;
                for (Character c : characters) {
                    if (c.getId().equals(arg)) {
                        ch = c;
                    }
                }
                for (Node node : nodes) {
                    if (node.getId().equals(arg)) {
                        n = node;
                    }
                }
                if (ch != null) {
                    pw.print(ch.getId() + "\n\tRoom: " + ch.getRoom().getId() + "\n\tInventory:");
                    for (Object o : ch.GetInventory()) {
                        pw.print(" " + o.getId());
                    }
                    pw.print("\n");
                }
                if (n != null) {
                    pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() + "\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        pw.print(" " + c.getId());
                    }
                    pw.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        pw.print(" " + o.getId());
                    }
                    pw.print("\n\tEffects:");
                    for (Effect e : n.getEffects()) {
                        pw.print(" " + e.toString());
                    }
                    pw.print("\n\tNeighbours:");
                    for (Node r : map.getAdjacencyList().get(n)) {
                        pw.print(" " + r.getId());
                    }
                    pw.print("\n");
                }
            }
        } else {
            if (arg.equals("map")) {
                System.out.println("Rooms:");
                //pw.println("Rooms:");

                for (Node n : map.getAdjacencyList().keySet()) {
                    System.out.print(n.getId() + "\n\tCapacity: " + n.getCapacity() + "\n\tCharacters:");
                    //pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        System.out.print(" " + c.getId());
                    }
                    System.out.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        System.out.print(" " + o.getId());
                    }
                    System.out.print("\n\tEffects:");
                    for (Effect e : n.getEffects()) {
                        System.out.print(" " + e.toString());
                    }
                    System.out.print("\n\tNeighbours:");
                    for (Node r : map.getAdjacencyList().get(n)) {
                        System.out.print(" " + r.getId());
                    }
                    System.out.print("\n");
                }
                System.out.println("Characters:");
                for (Character c : characters) {
                    System.out.print(c.getId() + "\n\tRoom: " + c.getRoom().getId() + "\n\tInventory:");
                    for (Object o : c.GetInventory()) {
                        System.out.print(" " + o.getId());
                    }
                    System.out.print("\n");
                }

            } else {
                Character ch = null;
                Node n = null;
                for (Character c : characters) {
                    if (c.getId().equals(arg)) {
                        ch = c;
                    }
                }
                for (Node node : nodes) {
                    if (node.getId().equals(arg)) {
                        n = node;
                    }
                }
                if (ch != null) {
                    System.out.print(ch.getId() + "\n\tRoom: " + ch.getRoom().getId() + "\n\tInventory:");
                    for (Object o : ch.GetInventory()) {
                        System.out.print(" " + o.getId());
                    }
                    System.out.print("\n");
                }
                if (n != null) {
                    System.out.print(n.getId() + "\n\tCapacity: " + n.getCapacity() + "\n\tCharacters:");
                    //pw.print(n.getId() + "\n\tCapacity: " + n.getCapacity() +"\n\tCharacters:");
                    for (Character c : n.GetMembers()) {
                        System.out.print(" " + c.getId());
                    }
                    System.out.print("\n\tObjects:");
                    for (Object o : n.GetObjects()) {
                        System.out.print(" " + o.getId());
                    }
                    System.out.print("\n\tEffects:");
                    for (Effect e : n.getEffects()) {
                        System.out.print(" " + e.toString());
                    }
                    System.out.print("\n\tNeighbours:");
                    for (Node r : map.getAdjacencyList().get(n)) {
                        System.out.print(" " + r.getId());
                    }
                    System.out.print("\n");
                }
            }
        }
    }


}

