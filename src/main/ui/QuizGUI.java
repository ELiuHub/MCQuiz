package ui;

import model.Quiz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Quiz App with GUI
public class QuizGUI extends JFrame {
    private JPanel background;
    private JButton makeQuizButton;
    private JButton startQuizButton;
    private JButton quitButton;
    private JLabel title;
    private Quiz quiz = new Quiz("Quiz");

    // EFFECTS: runs application
    public QuizGUI() {
        super("Quiz App");
        initStartWindow();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes starting window
    private void initStartWindow() {
        setPreferredSize(new Dimension(1000, 700));

        initBackgroundTitle();

        initImage();

        initMakeQuizButton();
        initStartQuizButton();
        initQuitQuizButton();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.Alignment.TRAILING,
                                GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
    }

    // MODIFIES: this
    // EFFECTS: initializes image
    //          find image in data folder, throws IOException if not found
    private void initImage() {
        JLabel brain;
        BufferedImage brainImage = null;

        try {
            brainImage = ImageIO.read(new File("./data/brain.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        brain = new JLabel(new ImageIcon(brainImage));
        background.add(brain);
        brain.setBounds(670, 250, 290, 260);
    }

    // MODIFIES: this
    // EFFECTS: initializes background and title
    private void initBackgroundTitle() {
        background = new JPanel();
        title = new JLabel();

        background.setBackground(new Color(219, 204, 160));
        background.setLayout(null);

        title.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 50));
        title.setForeground(new Color(0, 0, 0));
        title.setText("Welcome to the Quiz App!");
        background.add(title);
        title.setBounds(180, 100, 800, 84);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Make Quiz button
    private void initMakeQuizButton() {
        makeQuizButton = new JButton();

        makeQuizButton.setBackground(new Color(230, 223, 204));
        makeQuizButton.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        makeQuizButton.setForeground(new Color(0, 0, 0));
        makeQuizButton.setText("Make Quiz");
        makeQuizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        makeQuizButton.setActionCommand("makeButton");
        makeQuizButton.addActionListener(this::actionPerformed);
        background.add(makeQuizButton);
        makeQuizButton.setBounds(350, 270, 220, 60);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Take Quiz button
    private void initStartQuizButton() {
        startQuizButton = new JButton();

        startQuizButton.setBackground(new Color(230, 223, 204));
        startQuizButton.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        startQuizButton.setForeground(new Color(0, 0, 0));
        startQuizButton.setText("Take Quiz");
        startQuizButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startQuizButton.setActionCommand("startButton");
        startQuizButton.addActionListener(this::actionPerformed);
        background.add(startQuizButton);
        startQuizButton.setBounds(350, 370, 220, 60);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Quit button
    private void initQuitQuizButton() {
        quitButton = new JButton();

        quitButton.setBackground(new Color(230, 223, 204));
        quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        quitButton.setForeground(new Color(0, 0, 0));
        quitButton.setText("Quit");
        quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quitButton.setActionCommand("quitButton");
        quitButton.addActionListener(this::actionPerformed);
        background.add(quitButton);
        quitButton.setBounds(350, 470, 220, 60);
    }

    // EFFECTS: produces what each button does when clicked
    private void quizButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("makeButton")) {
            this.dispose();
            new MakeQuizGUI(quiz);
        } else if (e.getActionCommand().equals("startButton")) {
            this.dispose();
            new TakeQuizGUI(quiz);
        } else {
            this.dispose();
        }
    }

    public void actionPerformed(ActionEvent e) {
        quizButtonActionPerformed(e);
    }
}
