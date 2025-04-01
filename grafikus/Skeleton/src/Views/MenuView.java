package Views;

import Models.*;
import Models.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuView extends JFrame {
    private final Controller controller;
    private boolean studentadd=false;
    private boolean instructoradd=false;
    private int studentidx=0;
    private List<String> stundentImage= new ArrayList<>();
    private int instructoridx=0;
    private List<String> instrucrtorImage= new ArrayList<>();
    private List<String> instrucrtorName= new ArrayList<>();

    /**
     * A MenuView paraméter nélküli konstruktora
     */
    public MenuView(){
        controller= new Controller();
        setTitle("Labirintus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("./menuback").getImage());

        this.setVisible(true);
    }

    /**
     * A menüt kirajzoló füügvény
     */
    public void MenuDraw(){

        JLabel background = new JLabel(new ImageIcon("./menuback.png"));
        this.add(background);
        background.setLayout(new GridBagLayout());

        stundentImage.add("Diak1.png");
        stundentImage.add("Diak2.png");
        stundentImage.add("Diak3.png");
        stundentImage.add("Diak4.png");

        instrucrtorImage.add("Gajdos.png");
        instrucrtorImage.add("Tasi.png");
        instrucrtorImage.add("Hassan.png");
        instrucrtorImage.add("Szirmay.png");

        instrucrtorName.add("Gajdos Sándor");
        instrucrtorName.add("Tasnádi Tamás");
        instrucrtorName.add("Charaf Hassan");
        instrucrtorName.add("Szirmay Kalos László");


        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();


        JPanel studentpanel = new JPanel(new BorderLayout());
        studentpanel.setPreferredSize(new Dimension(200, 300));
        studentpanel.setVisible(studentadd);
        JLabel sidepanelframe= new JLabel(new ImageIcon("./sidepanel2.png"));
        studentpanel.add(sidepanelframe, BorderLayout.NORTH);



        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(700, 400));
        JLabel menuframe = new JLabel(new ImageIcon("./menuframe.png"));
        menuframe.setLayout(new GridBagLayout());
        menuPanel.add(menuframe, BorderLayout.NORTH);



        JPanel Instructorpanel = new JPanel(new BorderLayout());
        Instructorpanel.setPreferredSize(new Dimension(200, 300));
        Instructorpanel.setVisible(instructoradd);
        JLabel sidepanel2frame= new JLabel(new ImageIcon("./sidepanel2.png"));
        sidepanel2frame.setLayout(new GridBagLayout());
        Instructorpanel.add(sidepanel2frame, BorderLayout.NORTH);



        JLabel gameTitle = new JLabel("<html> <br><br> </html>"); //a cím magán a képen van rajta, így 2 darab sortöréssel töltöm ki a sort
        gameTitle.setFont(new Font(Font.MONOSPACED , Font.BOLD, 20));
        gbc2.gridy = 0;
        gbc2.gridx = 1;
        gbc2.weighty = 1.0;
        gbc2.weightx = 1.0;
        gbc2.fill= GridBagConstraints.CENTER;
        menuframe.add(gameTitle, gbc2);


        gbc2.gridy = 1;
        gbc2.gridx = 0;
        gbc2.weighty = 3.0;
        gbc2.weightx = 2.0;
        JPanel studentNamesPanel= new JPanel();
        studentNamesPanel.setOpaque(false);
        studentNamesPanel.setLayout(new GridLayout(5, 1, 0, 0));
        JLabel nameLabel= new JLabel("Hallgatók");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.TOP);
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        studentNamesPanel.add(nameLabel);
        menuframe.add(studentNamesPanel, gbc2);


        gbc2.gridx = 2;
        JPanel instructorNamePanel= new JPanel();
        instructorNamePanel.setOpaque(false);
        instructorNamePanel.setLayout(new GridLayout(5, 1, 0, 0));
        JLabel oktatok= new JLabel("Oktatók");
        oktatok.setHorizontalAlignment(SwingConstants.CENTER);
        oktatok.setVerticalAlignment(SwingConstants.TOP);
        oktatok.setFont(new Font("Verdana", Font.BOLD, 20));
        instructorNamePanel.add(oktatok);
        menuframe.add(instructorNamePanel, gbc2);


        gbc2.gridy = 2;
        gbc2.gridx = 1;
        gbc2.weighty = 1.0;
        gbc2.weightx=1.0;
        JButton playButton = new JButton(new ImageIcon("./startbutton.png"));
        playButton.setPreferredSize(new Dimension(78, 34));
        playButton.setEnabled(false);
        playButton.addActionListener(e -> {
            controller.GetCharacters().remove(controller.GetPlayer());
            controller.GetCharacters().add(0,controller.GetPlayer());
            Character cleaner= new Cleaner("Cleaner");
            controller.AddCharacter(cleaner);
            controller.getGame().GetMapView().CharacterPut(cleaner, new CleanerView("./Cleaner.png", cleaner));
            controller.PlaceStudents();
            InstructionDraw(menuPanel);
        });
        menuframe.add(playButton, gbc2);


        gbc2.gridx = 0;
        JButton studentAddButton = new JButton(new ImageIcon("./plusbutton.png"));
        studentAddButton.setPreferredSize(new Dimension(42, 34));
        studentAddButton.addActionListener(e -> {
            studentadd= !studentadd;
            studentpanel.setVisible(studentadd);
        });
        menuframe.add(studentAddButton, gbc2);

        gbc2.gridx = 2;
        JButton instructorAddButton = new JButton(new ImageIcon("./plusbutton.png"));
        instructorAddButton.setPreferredSize(new Dimension(42, 34));
        instructorAddButton.addActionListener(e -> {
            instructoradd= !instructoradd;
            Instructorpanel.setVisible(instructoradd);
        });
        menuframe.add(instructorAddButton, gbc2);

        gbc2 = new GridBagConstraints();

        JPanel studentAddPanel = new JPanel();
        studentAddPanel.setLayout(new GridBagLayout());
        JLabel imageStudentLabel = new JLabel(new ImageIcon("./"+stundentImage.get(studentidx)));
        imageStudentLabel.setPreferredSize(new Dimension(120, 200));
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        gbc2.weighty = 2.0;
        studentAddPanel.add(imageStudentLabel, gbc2);


        JTextField textField = new JTextField(10);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        gbc2.gridy = 1;
        gbc2.gridx = 0;
        gbc2.weighty = 1.0;
        studentAddPanel.add(textField, gbc2);


        JPanel studentButtonPanel = new JPanel(new GridBagLayout());
        JButton studentlistAddButton= new JButton(new ImageIcon("./addbutton.png"));
        studentlistAddButton.setPreferredSize(new Dimension(64, 34));
        studentlistAddButton.addActionListener(e -> {
            Student new_;
            if ((textField.getText().isEmpty())) {
                Random rand = new Random();
                new_=new Student("player" + rand.nextInt(0, 100));
            } else {
                new_ = new Student(textField.getText());
            }
            controller.AddCharacter(new_);
            controller.SetPlayer(new_);
            controller.getGame().GetMapView().CharacterPut(new_, new StudentView("./Kis"+stundentImage.get(studentidx), new_));
            controller.AddStudentNum();
            textField.setText("");
            JLabel name2 = new JLabel(controller.GetCharacters().get(controller.GetCharacters().size()-1).GetId());
            name2.setHorizontalAlignment(SwingConstants.CENTER);
            name2.setFont(new Font("Verdana", Font.BOLD, 10));
            studentNamesPanel.add(name2);
            studentadd= !studentadd;
            studentpanel.setVisible(studentadd);
            stundentImage.remove(studentidx);
            if(controller.GetCharacters().size()- Controller.GetStudentNum()>=1)
                playButton.setEnabled(true);
            if(stundentImage.isEmpty()){
                studentAddButton.setEnabled(false);
            }
            else {
                studentidx = (studentidx + 1) % stundentImage.size();
                ImageIcon imageIcon = new ImageIcon("./"+stundentImage.get(studentidx));
                imageStudentLabel.setIcon(imageIcon);
            }
        });
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        gbc2.weighty = 1.0;
        studentButtonPanel.add(studentlistAddButton, gbc2);
        JButton studentNextButton= new JButton(new ImageIcon("./nextbutton.png"));
        studentNextButton.setPreferredSize(new Dimension(64, 34));
        studentNextButton.addActionListener(e -> {
            studentidx=(studentidx + 1) % stundentImage.size();
            ImageIcon imageIcon = new ImageIcon("./"+stundentImage.get(studentidx));
            imageStudentLabel.setIcon(imageIcon);
        });
        gbc2.gridy = 0;
        gbc2.gridx = 1;
        gbc2.weighty = 1.0;
        studentButtonPanel.add(studentNextButton, gbc2);

        sidepanelframe.setLayout(new GridBagLayout());
        gbc2.gridx=0;
        gbc2.gridy=0;
        gbc2.weightx=1.0;
        gbc2.weighty=1.0;
        gbc2.fill= GridBagConstraints.BOTH;
        studentAddPanel.setOpaque(false);
        sidepanelframe.add(studentAddPanel, gbc2);
        gbc2.gridx=0;
        gbc2.gridy=1;
        studentButtonPanel.setOpaque(false);
        sidepanelframe.add(studentButtonPanel, gbc2);

        gbc.gridy = 0;
        gbc.gridx = 0;
        background.add(studentpanel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.fill= GridBagConstraints.CENTER;
        background.add(menuPanel, gbc);


        gbc2= new GridBagConstraints();


        JPanel instructorPanel = new JPanel();
        instructorPanel.setLayout(new GridBagLayout());
        JLabel imageInstructorLabel = new JLabel(new ImageIcon("./"+instrucrtorImage.get(instructoridx)));
        imageInstructorLabel.setPreferredSize(new Dimension(120, 200));
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        gbc2.weighty = 2.0;
        instructorPanel.add(imageInstructorLabel, gbc2);


        JLabel instructorName = new JLabel(instrucrtorName.get(instructoridx));
        gbc2.gridy = 1;
        gbc2.gridx = 0;
        gbc2.weighty = 1.0;
        instructorPanel.add(instructorName, gbc2);


        JPanel instructorButtonPanel = new JPanel(new GridBagLayout());
        JButton instructorlistAddButton= new JButton(new ImageIcon("./addbutton.png"));
        instructorlistAddButton.setPreferredSize(new Dimension(64, 34));
        instructorlistAddButton.addActionListener(e -> {
            Instructor new_=new Instructor(instrucrtorName.get(instructoridx));
            controller.AddCharacter(new_);
            controller.getGame().GetMapView().CharacterPut(new_, new InstructorView("./Kis"+instrucrtorImage.get(instructoridx), new_));
            JLabel name2 = new JLabel(controller.GetCharacters().get(controller.GetCharacters().size()-1).GetId());
            name2.setHorizontalAlignment(SwingConstants.CENTER);
            name2.setFont(new Font("Verdana", Font.BOLD, 10));
            instructorNamePanel.add(name2);
            instructoradd= !instructoradd;
            Instructorpanel.setVisible(instructoradd);
            instrucrtorName.remove(instructoridx);
            instrucrtorImage.remove(instructoridx);
            if(Controller.GetStudentNum()>=1 && controller.GetCharacters().size()- Controller.GetStudentNum()>=1)
                playButton.setEnabled(true);
            if(instrucrtorImage.isEmpty()){
                instructorAddButton.setEnabled(false);
            }
            else {
                instructoridx = (instructoridx + 1) % instrucrtorImage.size();
                ImageIcon imageIcon = new ImageIcon("./"+instrucrtorImage.get(instructoridx));
                imageInstructorLabel.setIcon(imageIcon);
                instructorName.setText(instrucrtorName.get(instructoridx));
            }
        });
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        gbc2.weighty = 1.0;
        gbc2.fill=GridBagConstraints.CENTER;
        instructorButtonPanel.add(instructorlistAddButton, gbc2);
        JButton instructorNextButton= new JButton(new ImageIcon("./nextbutton.png"));
        instructorNextButton.setPreferredSize(new Dimension(64, 34));
        instructorNextButton.addActionListener(e -> {
            instructoridx=(instructoridx + 1) % instrucrtorImage.size();
            ImageIcon imageIcon = new ImageIcon("./"+instrucrtorImage.get(instructoridx));
            imageInstructorLabel.setIcon(imageIcon);
            instructorName.setText(instrucrtorName.get(instructoridx));
        });
        gbc2.gridy = 0;
        gbc2.gridx = 1;
        gbc2.weighty = 1.0;
        instructorButtonPanel.add(instructorNextButton, gbc2);

        gbc2.gridx=0;
        gbc2.gridy=0;
        gbc2.weightx=1.0;
        gbc2.weighty=1.0;
        gbc2.fill= GridBagConstraints.BOTH;
        instructorPanel.setOpaque(false);
        sidepanel2frame.add(instructorPanel, gbc2);
        gbc2.gridx=0;
        gbc2.gridy=1;
        instructorButtonPanel.setOpaque(false);
        sidepanel2frame.add(instructorButtonPanel, gbc2);

        gbc.gridy = 0;
        gbc.gridx = 2;
        background.add(Instructorpanel, gbc);
    }

    /**
     * A játékmenetet és szabályokat kirajzoló függvény
     * @param menuPanel Az a JPanel, amelyikre kirajzolja a tudnivalókat
     */
    public void InstructionDraw(JPanel menuPanel){
        List<String> pages= new ArrayList<>();
        AtomicInteger index= new AtomicInteger();
        pages.add("./Instruction1.png");
        pages.add("./Instruction2.png");
        pages.add("./Instruction3.png");
        menuPanel.removeAll();
        menuPanel.setOpaque(false);
        JLabel instructionLabel = new JLabel(new ImageIcon(pages.get(index.get())));
        menuPanel.add(instructionLabel, BorderLayout.NORTH);

        JButton nextButton = new JButton(new ImageIcon("./nextbutton.png"));
        nextButton.setBounds(menuPanel.getWidth()-95, menuPanel.getHeight()-70, 64, 34);
        instructionLabel.add(nextButton);

        JButton playButton = new JButton(new ImageIcon("./startbutton.png"));
        playButton.setBounds(menuPanel.getWidth()-109, menuPanel.getHeight()-70, 78, 34);
        playButton.addActionListener(e2 -> {
            controller.GameRounds();
            this.dispose();
        });

        JButton prevButton = new JButton(new ImageIcon("./prevbutton.png"));
        prevButton.setBounds(30, menuPanel.getHeight()-70, 68, 34);
        prevButton.addActionListener(e2 -> {
            index.getAndDecrement();
            instructionLabel.removeAll();
            instructionLabel.setIcon(new ImageIcon(pages.get(index.get())));
            instructionLabel.add(nextButton);
            if (index.get()!=0){
                instructionLabel.add(prevButton);
            }
        });

        nextButton.addActionListener(e -> {
            index.getAndIncrement();
            instructionLabel.removeAll();
            instructionLabel.setIcon(new ImageIcon(pages.get(index.get())));
            instructionLabel.add(prevButton);
            if(index.get()==pages.size()-1)
                instructionLabel.add(playButton);
            else
                instructionLabel.add(nextButton);

        });
        menuPanel.revalidate();
    }
}
