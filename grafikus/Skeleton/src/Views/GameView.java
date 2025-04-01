package Views;

import Models.*;
import Models.Character;
import Models.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class GameView extends JFrame {
    private MapView mapView;
    private Object choosen;
    private HashMap<String, ObjectView> connection;
    private Controller controller;
    private int moveleft;
    private Character lastcharacter;
    private HashMap<Node, List<Node>> adjacencyList;
    private JLabel inventoryLabel;
    JScrollPane scrollPane;
    JLabel moveLabel;

    /**
     * GameView konstruktora
     * @param controller Ezt állítja be a controller váltzójának értékéül
     */
    public GameView(Controller controller) {

        moveleft=1;
        mapView = new MapView();
        connection = new HashMap<>();
        this.controller = controller;
        choosen = null;

        connection.put("Légfrissítő", new AirFreshenerView("air.png"));
        connection.put("Söröspoharak", new BeerView("beer.png"));
        connection.put("Camamber", new CamamberView("camamber.png"));
        connection.put("FFP2 maszk", new FFP2View("ffp2.png"));
        connection.put("Logarléc", new LogarlecView("logar.png"));
        connection.put("Nedves táblatörlő", new RagView("rag.png"));
        connection.put("Transistor", new TransistorView("trans.png"));
        connection.put("TVSZ", new TVSZView("tvsz.png"));

        setTitle("Labirintus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1250, 750));
        setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(false);
    }

    /**
     * Kirajzolja a játék állását
     * @param ch Az a karakter, akinek a köre van
     * @param map A szobák, amiket ki kell rajzolni
     */
    public void GameDraw(Character ch, Map map) {
        setVisible(true);

        if(CheckWinOrLose()){
            return;
        }

        else if(lastcharacter!=ch){
            if(ch.PlayerTurn(map.GetAdjacencyList())) {
                lastcharacter=ch;
                controller.GameRounds();
                return;
            }
        }
        lastcharacter=ch;

        adjacencyList = map.GetAdjacencyList();

        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton useButton = new JButton(new ImageIcon("./usebutton.png"));
        JButton dropButton = new JButton(new ImageIcon("./dropbutton.png"));
        scrollPane= null;

        JPanel mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                this.removeAll();
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                ImageIcon background = new ImageIcon("./mapbackground.png");
                g2d.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                mapView.NodesInit(map, 152);
                int height=map.GetRowNumber()*152;
                mapView.Draw(g2d, map, this);
                this.setPreferredSize(new Dimension(712, height));
            }
        };
        JScrollPane mapscroll = new JScrollPane(mapPanel);
        mapscroll.setOpaque(false);
        mapscroll.setBorder(null);

        JLabel leftLabel = new JLabel(new ImageIcon("./pickupbackground.png"));
        leftPanel.add(leftLabel, BorderLayout.NORTH);

        inventoryLabel = new JLabel(new ImageIcon("./inventory.png"));
        inventoryLabel.setLayout(new GridLayout(1, 6));
        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);

        JPanel[] slot= new JPanel[5];

        InventoryDraw(slot, ch, buttonPanel, dropButton, useButton);

        for (JPanel jp:slot){
            inventoryLabel.add(jp);
        }

        pickupTableDraw(ch, slot, buttonPanel, dropButton, useButton);

        this.setLayout(new BorderLayout());

        rightPanel.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.CENTER;
        useButton.setContentAreaFilled(false);
        useButton.setPreferredSize(new Dimension(54, 34));
        useButton.addActionListener(e -> {
            if(choosen!=null) {
                ch.UseItem(choosen);
                choosen = null;
                InventoryDraw(slot, ch, buttonPanel, dropButton, useButton);
                pickupTableDraw(ch, slot, buttonPanel, dropButton, useButton);
                mapPanel.repaint();
                CheckWinOrLose();
            }
        });
        buttonPanel.add(useButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        dropButton.setContentAreaFilled(false);
        dropButton.setPreferredSize(new Dimension(70, 34));
        dropButton.addActionListener(e -> {
            if(choosen!=null) {
                ch.DropItem(choosen);
                choosen = null;
                InventoryDraw(slot, ch, buttonPanel, dropButton, useButton);
                pickupTableDraw(ch, slot, buttonPanel, dropButton, useButton);
            }
        });
        buttonPanel.add(dropButton, gbc);
        buttonPanel.setOpaque(false);
        inventoryLabel.add(buttonPanel);

        inventoryPanel.setPreferredSize(new Dimension(200, 100));
        rightPanel.add(inventoryPanel, BorderLayout.SOUTH);


        leftLabel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel(String.format("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style='text-align: center;'>Your Turn:<br>%s</div></html>", ch.GetId()));
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        leftLabel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        moveLabel = new JLabel(String.format("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style='text-align: center;'>You have %d move left</div></html>", moveleft));
        moveLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        moveLabel.setForeground(Color.BLACK);
        leftLabel.add(moveLabel, gbc);

        mapPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(moveleft != 0)
                {
                    if(mapView.MapViewMove(e.getX(), e.getY(), adjacencyList, controller.GetPlayer()))
                        moveleft--;
                    mapPanel.repaint();
                    moveLabel.setText(String.format("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style='text-align: center;'>You have %d move left</div></html>", moveleft));
                    pickupTableDraw(ch, slot, buttonPanel, dropButton, useButton);
                    InventoryDraw(slot, ch, buttonPanel, dropButton, useButton);
                    CheckWinOrLose();
                }
            }
        });


        gbc.gridy = 3;
        gbc.weighty = 2;
        gbc.insets= new Insets(0, 0, 20, 0);
        leftLabel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1;
        JButton EndTurnButton = new JButton(new ImageIcon("./endtbutton.png"));
        EndTurnButton.setContentAreaFilled(false);
        EndTurnButton.setPreferredSize(new Dimension(116, 34));
        EndTurnButton.addActionListener(e -> {
            GameView.this.getContentPane().removeAll();
            moveleft=1;
            controller.GameRounds();
        });
        leftLabel.add(EndTurnButton, gbc);
        leftPanel.setPreferredSize(new Dimension(250, 100));

        rightPanel.add(mapscroll, BorderLayout.CENTER);

        this.add(rightPanel, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.EAST);
        this.revalidate();
    }

    /**
     * Kirajzolja a játékosnál lévő tárgyakat
     * @param slot Egy JPanel tömb, ebbe rajzolja a tárgyakat
     * @param ch A karakter, akinek a tárgyait kirajzoljuk
     * @param buttonPanel Egy JPanel, ahova rakja majd a Use és Drop gombokat, és a védettséget jelentő sziveket
     * @param dropButton Egy JButton, ami a tárgy eldobását teszi lehetővé
     * @param useButton Egy JButton, ami a tárgy használatát teszi lehetővé
     */
    public void InventoryDraw(JPanel[] slot, Character ch, JPanel buttonPanel, JButton dropButton, JButton useButton){
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < slot.length; i++) {
            final int index = i;
            slot[i] = new JPanel();
            slot[i].setOpaque(false);
            slot[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (ch.GetInventory().size() > index && !ch.GetInventory().isEmpty()){
                        if(ch.GetInventory().contains(choosen)) {
                            slot[ch.GetInventory().indexOf(choosen)].setBorder(null);
                        }
                        slot[index].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        choosen = ch.GetInventory().get(index);
                    }
                }
            });
            slot[i].revalidate();
        }
        for (Object ob : ch.GetInventory()) {
            JPanel s=slot[ch.GetInventory().indexOf(ob)];
            connection.get(ob.GetId()).SetPair(ch.GetInventory().get(ch.GetInventory().indexOf(ob)));
            connection.get(ob.GetId()).Draw(s);
        }

        buttonPanel.removeAll();

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.CENTER;
        buttonPanel.add(useButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        buttonPanel.add(dropButton, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        if(ch.GetPoisonProtected()){
            buttonPanel.add( new JLabel(new ImageIcon("./greenheart.png")), gbc);
        }else{
            buttonPanel.add( new JLabel(new ImageIcon("./heart.png")), gbc);
        }

        gbc.gridy = 1;
        if(ch.GetInsProtected()){
            buttonPanel.add(new JLabel(new ImageIcon("./redheart.png")), gbc);
        }else{
            buttonPanel.add( new JLabel(new ImageIcon("./heart.png")), gbc);
        }
        buttonPanel.revalidate();

        inventoryLabel.removeAll();
        for (JPanel jp:slot){
            inventoryLabel.add(jp);
        }
        inventoryLabel.add(buttonPanel);
        inventoryLabel.revalidate();
    }

    /**
     * Kirajzolja  afelvehető tárgyakhoz tartozó gombokat
     * @param ch A karakter, akinek a köre van
     * @param slot Egy JPanel tömb, ebbe rajzolja a tárgyakat
     * @param buttonPanel Egy JPanel, ahova rakja majd a Use és Drop gombokat, és a védettséget jelentő sziveket
     * @param dropButton Egy JButton, ami a tárgy eldobását teszi lehetővé
     * @param useButton Egy JButton, ami a tárgy használatát teszi lehetővé
     */
    public void pickupTableDraw(Character ch, JPanel[] slot, JPanel buttonPanel, JButton dropButton, JButton useButton){
        int i = 0;
        JPanel tablepanel = new JPanel(new GridBagLayout());
        tablepanel.setOpaque(false);
        GridBagConstraints gbc1 = new GridBagConstraints();
        for (Object object : ch.GetRoom().GetObjects()) {
            gbc1.gridy = i;
            gbc1.gridx = 0;
            gbc1.fill = GridBagConstraints.BOTH;
            JLabel objectlabel = new JLabel(object.GetId());
            objectlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            objectlabel.setForeground(Color.BLACK);
            tablepanel.add(objectlabel, gbc1);
            gbc1.gridy = i;
            gbc1.gridx = 1;
            JButton pickUpButton = new JButton(new ImageIcon("./button" + connection.get(object.GetId()).GetImage()));
            pickUpButton.setPreferredSize(new Dimension(52, 34));
            pickUpButton.addActionListener(e -> {
                pickUpButton.setEnabled(false);
                ch.PickupItem(object);
                InventoryDraw(slot, ch, buttonPanel, dropButton, useButton);
                pickupTableDraw( ch, slot, buttonPanel, dropButton, useButton);
            });
            tablepanel.add(pickUpButton, gbc1);
            i++;
        }
        if(scrollPane==null)
            scrollPane= new JScrollPane(tablepanel);
        else
            scrollPane.setViewportView(tablepanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        scrollPane.revalidate();
    }

    /**
     * Megnézi, hogy vége-e a játéknak, ha igen, akkor megjeleníti a nyertes vagy vesztes ablakokat,
     * és megnyitja a menüt
     * @return igazzal tér vissza, ha vége a játéknak, egyébként hamissal
     */
    public boolean CheckWinOrLose(){
        if(Controller.GetGameOver()){
            this.getContentPane().removeAll();
            GridBagConstraints c = new GridBagConstraints();
            JPanel GameOverPanel = new JPanel(new BorderLayout());
            GameOverPanel.setPreferredSize(new Dimension(400, 200));
            JLabel GameOverLabel= new JLabel();
            GameOverLabel.setLayout(new GridBagLayout());
            GameOverPanel.add(GameOverLabel, BorderLayout.NORTH);
            JButton Retrybutton = new JButton();
            Retrybutton.addActionListener(e -> {
                Main.NewGame();
                this.dispose();
            });
            if(Controller.GetStudentNum()>0){
                GameOverLabel.setIcon(new ImageIcon("./winpanel.png"));
                Retrybutton.setIcon(new ImageIcon("./winbutton.png"));
                Retrybutton.setPreferredSize(new Dimension(56, 34));
            }
            else{
                GameOverLabel.setIcon(new ImageIcon("./gameover.png"));
                Retrybutton.setIcon(new ImageIcon("./gameoverbutton.png"));
                Retrybutton.setPreferredSize(new Dimension(64, 32));
            }
            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(100, 0, 0, 0);
            GameOverLabel.add(Retrybutton, c);
            this.setLayout(new GridBagLayout());
            c.gridy=0;
            c.insets=new Insets(0, 0, 0, 0);
            this.add(GameOverPanel, c);
            this.validate();
            return true;
        }
        return false;
    }

    /**
     * mapView gettere
     * @return Visszatér a GameView-hoz tartozó MapView-al
     */
    public MapView GetMapView() {
        return mapView;
    }
}