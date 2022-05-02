package ui;

import model.Quiz;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// represents window that allows user to take quiz
public class TakeQuizGUI extends JFrame {
    private JPanel background;
    private JRadioButton op1;
    private JRadioButton op2;
    private JRadioButton op3;
    private JRadioButton op4;
    private JButton submitButton;
    private JButton finishButton;
    private JLabel question;
    private Quiz quiz;
    private int score = 0;
    private int questionNum;
    private JLabel scoreLabel;

    // EFFECTS: window where user takes quiz
    public TakeQuizGUI(Quiz quiz) {
        super(quiz.getName());
        initTakeQuizWindow();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.quiz = quiz;
        printQuiz();
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initTakeQuizWindow() {
        background = new JPanel();

        setPreferredSize(new Dimension(1000, 700));

        background.setBackground(new Color(219, 204, 160));
        background.setLayout(null);

        initQuestionLabel();

        initOptions();
        initSubmitButton();

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

    // EFFECTS: sets up question label and score label
    private void initQuestionLabel() {
        question = new JLabel();

        question.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 40));
        question.setForeground(new Color(0, 0, 0));
        question.setText("question");
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setBounds(0, 90, 1000, 40);
        background.add(question);
    }

    // MODIFIES: this
    // EFFECTS: creates radio buttons for quiz options
    private void initOptions() {
        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        optionsSettings(op1, 200, "op1");
        optionsSettings(op2, 280, "op2");
        optionsSettings(op3, 360, "op3");
        optionsSettings(op4, 440, "op4");

        ButtonGroup group = new ButtonGroup();
        group.add(op1);
        group.add(op2);
        group.add(op3);
        group.add(op4);

        background.add(op1);
        background.add(op2);
        background.add(op3);
        background.add(op4);
    }

    // EFFECTS: options settings
    private void optionsSettings(JRadioButton button, int y, String ac) {
        button.setBounds(230, y, 520, 60);
        button.setBackground(new Color(219, 204, 160));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        button.setActionCommand(ac);
    }

    // MODIFIES: this
    // EFFECTS: creates submit button
    private void initSubmitButton() {
        submitButton = new JButton();
        buttonSettings(submitButton, "Submit", "submitButton", 550);
    }

    // EFFECTS: produces what happens when option is selected
    private void optionSelected() {
        if (op1.isSelected()) {
            checkAnswer(op1);
        } else if (op2.isSelected()) {
            checkAnswer(op2);
        } else if (op3.isSelected()) {
            checkAnswer(op3);
        } else if (op4.isSelected()) {
            checkAnswer(op4);
        }
    }

    // EFFECTS: checks if answer selected is correct
    private void checkAnswer(JRadioButton option) {
        if (option.getText().equals(quiz.quizQuestions().get(questionNum).getAnswer())) {
            score++;
            playAudio("./data/correct.wav");
        } else {
            playAudio("./data/incorrect.wav");
        }
    }

    // EFFECTS: plays audio
    // link to how I did this: https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    public void playAudio(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: produces what happens when submit button is clicked
    private void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("submitButton")) {
            optionSelected();
            questionNum++;

            if (questionNum == quiz.numQuestions()) {
                clearWindow();
                initScore();
                initImage();
                initFinishButton();
            } else {
                question.setText(quiz.quizQuestions().get(questionNum).getQuestion());
                op1.setText(quiz.quizQuestions().get(questionNum).getOption1());
                op2.setText(quiz.quizQuestions().get(questionNum).getOption2());
                op3.setText(quiz.quizQuestions().get(questionNum).getOption3());
                op4.setText(quiz.quizQuestions().get(questionNum).getOption4());
            }
        }

        if (e.getActionCommand().equals("finishButton")) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears everything off of window
    private void clearWindow() {
        question.setVisible(false);
        op1.setVisible(false);
        op2.setVisible(false);
        op3.setVisible(false);
        op4.setVisible(false);
        submitButton.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes image
    //          find image in data folder, throws IOException if not found
    // Link to how I did this: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel?rq=1
    private void initImage() {
        JLabel end;
        BufferedImage endImage = null;

        try {
            endImage = ImageIO.read(new File("./data/end.jpg"));
        } catch (IOException e) {
            System.out.println("Image not found");
        }
        end = new JLabel(new ImageIcon(endImage));
        background.add(end);
        end.setBounds(320, 180, 350, 350);
    }

    // MODIFIES: this
    // EFFECTS: creates finish button
    private void initFinishButton() {
        finishButton = new JButton();
        buttonSettings(finishButton, "Finish", "finishButton", 560);
    }

    // EFFECTS: button settings
    private void buttonSettings(JButton button, String text, String ac, int y) {
        button.setBackground(new Color(230, 223, 204));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button.setForeground(new Color(0, 0, 0));
        button.setText(text);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setActionCommand(ac);
        button.addActionListener(this::actionPerformed);
        background.add(button);
        button.setBounds(440, y, 100, 30);
    }

    // MODIFIES: this
    // EFFECTS: creates score label
    private void initScore() {
        scoreLabel = new JLabel();

        scoreLabel.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 50));
        scoreLabel.setForeground(new Color(0, 0, 0));
        scoreLabel.setText("YOU GOT: " + score + " OUT OF " + quiz.numQuestions());
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(0, 100, 1000, 40);
        background.add(scoreLabel);
    }

    // EFFECTS: prints quiz to screen
    private void printQuiz() {
        for (int i = 0; i < quiz.numQuestions(); i++) {
            question.setText(quiz.quizQuestions().get(questionNum).getQuestion());
            op1.setText(quiz.quizQuestions().get(questionNum).getOption1());
            op2.setText(quiz.quizQuestions().get(questionNum).getOption2());
            op3.setText(quiz.quizQuestions().get(questionNum).getOption3());
            op4.setText(quiz.quizQuestions().get(questionNum).getOption4());
        }
    }
}