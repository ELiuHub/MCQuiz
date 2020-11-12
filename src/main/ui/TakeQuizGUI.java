package ui;

import model.Quiz;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// represents window that allows user to take quiz
public class TakeQuizGUI extends JFrame {
    private JPanel background;
    private JRadioButton op1;
    private JRadioButton op2;
    private JRadioButton op3;
    private JRadioButton op4;
    private JButton submitButton;
    private JLabel question;
    private Quiz quiz;
    private int score = 0;
    private int questionNum;
    private JLabel scoreLabel;
    private static final String JSON_STORE = "./data/quiz.json";

    // EFFECTS: window where user takes quiz
    public TakeQuizGUI(Quiz quiz) {
        super(quiz.getName());
        initTakeQuizWindow();
        pack();
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
        question = new JLabel();
        scoreLabel = new JLabel();

        setPreferredSize(new Dimension(1000, 700));

        background.setBackground(new Color(219, 204, 160));
        background.setLayout(null);

        labelSettings();

        initOptions();
        initSubmitButton();

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

    // EFFECTS: sets up question label and score label
    private void labelSettings() {
        question.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 40));
        question.setForeground(new Color(0, 0, 0));
        question.setText("question");
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setBounds(0, 90, 1000, 40);
        background.add(question);

        scoreLabel.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 30));
        question.setForeground(new Color(0, 0, 0));
        scoreLabel.setText("Score: " + score);
        scoreLabel.setBounds(20, 20, 200, 40);
        background.add(scoreLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates radio buttons for quiz options
    private void initOptions() {
        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        op1.setActionCommand("op1");
        op2.setActionCommand("op2");
        op3.setActionCommand("op3");
        op4.setActionCommand("op4");

        op1.addActionListener(this::actionPerformed);
        op2.addActionListener(this::actionPerformed);
        op3.addActionListener(this::actionPerformed);
        op4.addActionListener(this::actionPerformed);

        optionsSettings();

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

    // EFFECTS: sets options location, background, font
    private void optionsSettings() {
        op1.setBounds(230, 200, 520, 60);
        op2.setBounds(230, 280, 520, 60);
        op3.setBounds(230, 360, 520, 60);
        op4.setBounds(230, 440, 520, 60);

        op1.setBackground(new Color(219, 204, 160));
        op2.setBackground(new Color(219, 204, 160));
        op3.setBackground(new Color(219, 204, 160));
        op4.setBackground(new Color(219, 204, 160));

        op1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        op2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        op3.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        op4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
    }

    // MODIFIES: this
    // EFFECTS: creates submit button
    private void initSubmitButton() {
        submitButton = new JButton();

        submitButton.setBackground(new Color(230, 223, 204));
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        submitButton.setForeground(new Color(0, 0, 0));
        submitButton.setText("Submit");
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setActionCommand("submitButton");
        submitButton.addActionListener(this::actionPerformed);
        background.add(submitButton);
        submitButton.setBounds(440, 550, 100, 30);
    }

    // EFFECTS: produces what happens when option button is selected
    private void optionPressed(ActionEvent e) {
        if (e.getActionCommand().equals("op1")) {
            if (op1.getText().equals(quiz.quizQuestions().get(questionNum).getAnswer())) {
                score++;
            }
        } else if (e.getActionCommand().equals("op2")) {
            if (op2.getText().equals(quiz.quizQuestions().get(questionNum).getAnswer())) {
                score++;
            }
        } else if (e.getActionCommand().equals("op3")) {
            if (op3.getText().equals(quiz.quizQuestions().get(questionNum).getAnswer())) {
                score++;
            }
        } else if (e.getActionCommand().equals("op4")) {
            if (op4.getText().equals(quiz.quizQuestions().get(questionNum).getAnswer())) {
                score++;
            }
        }
    }

    // EFFECTS: produces what happens when submit button is clicked
    private void actionPerformed(ActionEvent e) {
        optionPressed(e);

        if (e.getActionCommand().equals("submitButton")) {
            questionNum++;

            if (questionNum == quiz.numQuestions()) {
                dispose();
            } else {
                question.setText(quiz.quizQuestions().get(questionNum).getQuestion());
                op1.setText(quiz.quizQuestions().get(questionNum).getOption1());
                op2.setText(quiz.quizQuestions().get(questionNum).getOption2());
                op3.setText(quiz.quizQuestions().get(questionNum).getOption3());
                op4.setText(quiz.quizQuestions().get(questionNum).getOption4());
            }
        }
    }

    // method from JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads quiz from file
    private void loadQuiz() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            quiz = jsonReader.read();
            System.out.println("Loaded " + quiz.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints quiz to screen
    private void printQuiz() {
        loadQuiz();

        for (int i = 0; i < quiz.numQuestions(); i++) {
            question.setText(quiz.quizQuestions().get(questionNum).getQuestion());
            op1.setText(quiz.quizQuestions().get(questionNum).getOption1());
            op2.setText(quiz.quizQuestions().get(questionNum).getOption2());
            op3.setText(quiz.quizQuestions().get(questionNum).getOption3());
            op4.setText(quiz.quizQuestions().get(questionNum).getOption4());
        }
    }
}