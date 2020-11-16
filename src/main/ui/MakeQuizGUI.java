package ui;

import exceptions.EmptyException;
import model.Questions;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Makes a new quiz
public class MakeQuizGUI extends JFrame {
    private JPanel background;
    private JButton addButton;
    private JButton finishButton;
    private JButton viewButton;
    private JTextField enterQuestion;
    private JTextField enterAnswer;
    private JTextField enterOption1;
    private JTextField enterOption2;
    private JTextField enterOption3;
    private JTextField enterOption4;
    private JLabel questionPrompt;
    private JLabel answerPrompt;
    private JLabel optionPrompt;
    private Quiz quiz;

    // EFFECTS: represents the window where you
    //          enter a question, answer, and options
    public MakeQuizGUI(Quiz quiz) {
        super("Making a Quiz");
        initMakeQuizWindow();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.quiz = quiz;
    }

    // MODIFIES: this
    // EFFECTS: creates a new window to make a quiz
    private void initMakeQuizWindow() {
        background = new JPanel();

        setPreferredSize(new Dimension(1000, 700));

        background.setBackground(new Color(219, 204, 160));
        background.setLayout(null);

        initPrompts();
        initTextFields();
        initButtons();

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
    // EFFECTS: initializes text fields to enter a questions and answer
    private void initTextFields() {
        enterQuestion = new JTextField();
        enterAnswer = new JTextField();

        textFieldSettings(enterQuestion, 125);
        textFieldSettings(enterAnswer, 225);

        initOptions();
    }

    // REQUIRES: must enter correct answer as one of the options
    // MODIFIES: this
    // EFFECTS: initializes text fields to enter options
    private void initOptions() {
        enterOption1 = new JTextField();
        enterOption2 = new JTextField();
        enterOption3 = new JTextField();
        enterOption4 = new JTextField();

        textFieldSettings(enterOption1, 325);
        textFieldSettings(enterOption2, 385);
        textFieldSettings(enterOption3, 445);
        textFieldSettings(enterOption4, 505);
    }

    // EFFECTS: text field settings
    private void textFieldSettings(JTextField textField, int y) {
        textField.setBounds(250, y, 500, 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(textField);
    }

    // MODIFIES: this
    // EFFECTS: initializes text that ask for input
    private void initPrompts() {
        questionPrompt = new JLabel();
        answerPrompt = new JLabel();
        optionPrompt = new JLabel();

        promptSettings(questionPrompt, 100, "Enter your question: ");
        promptSettings(answerPrompt, 200, "Enter the answer: ");
        promptSettings(optionPrompt, 300,"Enter the options: ");
    }

    // EFFECTS: prompt settings
    private void promptSettings(JLabel prompt, int y, String text) {
        prompt.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        prompt.setForeground(new Color(0, 0, 0));
        prompt.setBounds(50, y, 200, 84);
        prompt.setText(text);
        background.add(prompt);
    }

    // EFFECTS: all buttons
    private void initButtons() {
        initAddButton();
        initFinishButton();
        initViewButton();
    }

    // MODIFIES: this
    // EFFECTS: creates the add button
    private void initAddButton() {
        addButton = new JButton();

        buttonSettings(addButton, "Add", "addButton");
        addButton.setBounds(380, 590, 110, 40);
    }

    // MODIFIES: this
    // EFFECTS: creates the view questions button
    private void initViewButton() {
        viewButton = new JButton();

        buttonSettings(viewButton, "View Questions", "viewButton");
        viewButton.setBounds(420, 40, 150, 40);
    }

    // MODIFIES: this
    // EFFECTS: creates the finish button
    private void initFinishButton() {
        finishButton = new JButton();

        buttonSettings(finishButton, "Finish", "finishButton");
        finishButton.setBounds(510, 590, 110, 40);
    }

    // EFFECTS: button settings
    private void buttonSettings(JButton button, String text, String ac) {
        button.setBackground(new Color(230, 223, 204));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        button.setForeground(new Color(0, 0, 0));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this::actionPerformed);
        button.setText(text);
        button.setActionCommand(ac);
        background.add(button);
    }

    // EFFECTS: produces what buttons do when clicked
    private void quizButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            addQuestions();
            removeText();
        } else if (e.getActionCommand().equals("finishButton")) {
            if (quiz.numQuestions() == 0) {
                System.out.println("You didn't enter any questions into your quiz!");
                dispose();
                new QuizGUI();
            } else {
                new SaveLoadPrompt(quiz, null, this,"Saving...");
            }
        } else {
            new ViewQuestionsWindow(quiz);
        }
    }

    // MODIFIES: this
    // EFFECTS: throws EmptyException if no question, answer, or option has been entered
    //          otherwise adds entered questions to quiz
    private void addQuestions() {
        String question = enterQuestion.getText();
        String answer = enterAnswer.getText();
        String option1 = enterOption1.getText();
        String option2 = enterOption2.getText();
        String option3 = enterOption3.getText();
        String option4 = enterOption4.getText();

        try {
            quiz.addQuestion((new Questions(question, answer, option1, option2, option3, option4)));
        } catch (EmptyException e) {
            System.out.println("Not enough information entered!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes text from text fields
    private void removeText() {
        enterQuestion.setText(null);
        enterAnswer.setText(null);
        enterOption1.setText(null);
        enterOption2.setText(null);
        enterOption3.setText(null);
        enterOption4.setText(null);
    }

    public void actionPerformed(ActionEvent e) {
        quizButtonActionPerformed(e);
    }
}
