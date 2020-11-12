package ui;

import model.Questions;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        enterQuestion.setBounds(250, 125, 500, 50);
        enterQuestion.setFont(new Font("Arial", Font.PLAIN, 20));
        enterQuestion.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterQuestion);

        enterAnswer.setBounds(250, 225, 500, 50);
        enterAnswer.setFont(new Font("Arial", Font.PLAIN, 20));
        enterAnswer.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterAnswer);

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

        enterOption1.setBounds(250, 325, 500, 50);
        enterOption1.setFont(new Font("Arial", Font.PLAIN, 20));
        enterOption1.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterOption1);

        enterOption2.setBounds(250, 385, 500, 50);
        enterOption2.setFont(new Font("Arial", Font.PLAIN, 20));
        enterOption2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterOption2);

        enterOption3.setBounds(250, 445, 500, 50);
        enterOption3.setFont(new Font("Arial", Font.PLAIN, 20));
        enterOption3.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterOption3);

        enterOption4.setBounds(250, 505, 500, 50);
        enterOption4.setFont(new Font("Arial", Font.PLAIN, 20));
        enterOption4.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        background.add(enterOption4);
    }

    // MODIFIES: this
    // EFFECTS: initializes text that ask for input
    private void initPrompts() {
        questionPrompt = new JLabel();
        answerPrompt = new JLabel();
        optionPrompt = new JLabel();

        questionPrompt.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        questionPrompt.setForeground(new Color(0, 0, 0));
        questionPrompt.setText("Enter your question: ");
        questionPrompt.setBounds(50, 100, 200, 84);
        background.add(questionPrompt);

        answerPrompt.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        answerPrompt.setForeground(new Color(0, 0, 0));
        answerPrompt.setText("Enter the answer: ");
        answerPrompt.setBounds(50, 200, 200, 84);
        background.add(answerPrompt);

        optionPrompt.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        optionPrompt.setForeground(new Color(0, 0, 0));
        optionPrompt.setText("Enter the options: ");
        optionPrompt.setBounds(50, 300, 200, 84);
        background.add(optionPrompt);
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

        addButton.setBackground(new Color(230, 223, 204));
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 15)); // NOI18N
        addButton.setForeground(new Color(0, 0, 0));
        addButton.setText("Add");
        addButton.setActionCommand("addButton");
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(this::actionPerformed);
        background.add(addButton);
        addButton.setBounds(380, 590, 110, 40);
    }

    // MODIFIES: this
    // EFFECTS: creates the view questions button
    private void initViewButton() {
        viewButton = new JButton();

        viewButton.setBackground(new Color(230, 223, 204));
        viewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15)); // NOI18N
        viewButton.setForeground(new Color(0, 0, 0));
        viewButton.setText("View Questions");
        viewButton.setActionCommand("viewButton");
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.addActionListener(this::actionPerformed);
        background.add(viewButton);
        viewButton.setBounds(420, 40, 150, 40);
    }

    // MODIFIES: this
    // EFFECTS: creates the finish button
    private void initFinishButton() {
        finishButton = new JButton();

        finishButton.setBackground(new Color(230, 223, 204));
        finishButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        finishButton.setForeground(new Color(0, 0, 0));
        finishButton.setText("Finish");
        finishButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        finishButton.setActionCommand("finishButton");
        finishButton.addActionListener(this::actionPerformed);
        background.add(finishButton);
        finishButton.setBounds(510, 590, 110, 40);
    }

    // EFFECTS: produces what buttons do when clicked
    private void quizButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addButton")) {
            addQuestions();
            removeText();
        } else if (e.getActionCommand().equals("finishButton")) {
            new SavePrompt(quiz);
            dispose();
        } else {
            new ViewQuestionsWindow(quiz);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds entered questions to quiz
    private void addQuestions() {
        String question = enterQuestion.getText();
        String answer = enterAnswer.getText();
        String option1 = enterOption1.getText();
        String option2 = enterOption2.getText();
        String option3 = enterOption3.getText();
        String option4 = enterOption4.getText();

        quiz.addQuestion(new Questions(question, answer,
                option1, option2, option3, option4));
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
