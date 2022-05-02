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
        setLocationRelativeTo(null);
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

        initButtons();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
    }

    // MODIFIES: this
    // EFFECTS: initializes image
    //          find image in data folder, throws IOException if not found
    // Link to how I did this: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel?rq=1
    private void initImage() {
        JLabel brain;
        BufferedImage brainImage = null;

        try {
            brainImage = ImageIO.read(new File("./data/brain.jpg"));
        } catch (IOException e) {
            System.out.println("Image not found.");
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

    // EFFECTS: initializes all buttons
    private void initButtons() {
        makeQuizButton = new JButton();
        startQuizButton = new JButton();
        quitButton = new JButton();

        buttonSettings(makeQuizButton, 270, "Make Quiz", "makeButton");
        buttonSettings(startQuizButton, 370, "Take Quiz", "startButton");
        buttonSettings(quitButton, 470, "Quit", "quitButton");
    }

    // EFFECTS: button settings
    private void buttonSettings(JButton button, int y, String text, String ac) {
        button.setBackground(new Color(230, 223, 204));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        button.setForeground(new Color(0, 0, 0));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this::actionPerformed);
        button.setText(text);
        button.setActionCommand(ac);
        button.setBounds(350, y, 220, 60);
        background.add(button);
    }

    // EFFECTS: produces what each button does when clicked
    private void quizButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("makeButton")) {
            this.dispose();
            new MakeQuizGUI(quiz);
        } else if (e.getActionCommand().equals("startButton")) {
            new SaveLoadPrompt(quiz, this, null,"Loading...");
        } else {
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        quizButtonActionPerformed(e);
    }
}
