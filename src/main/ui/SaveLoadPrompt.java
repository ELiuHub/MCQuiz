package ui;

import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// pop up window that gives user the option to save or load quiz
public class SaveLoadPrompt extends JFrame {
    private JLabel promptText;
    private JButton yesButton;
    private JButton noButton;
    private Quiz quiz;
    private QuizGUI quizGUI;
    private static final String JSON_STORE = "./data/quiz.json";
    private String choice;
    private MakeQuizGUI makeQuizGUI;

    // EFFECTS: prompts user to save or load quiz
    public SaveLoadPrompt(Quiz quiz, QuizGUI quizGUI, MakeQuizGUI makeQuizGUI, String choice) {
        super(choice);
        this.choice = choice;
        this.quiz = quiz;
        this.quizGUI = quizGUI;
        this.makeQuizGUI = makeQuizGUI;
        initSaveLoadWindow();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initSaveLoadWindow() {
        setPreferredSize(new Dimension(400, 200));
        promptText = new JLabel();

        setLayout(null);

        promptText.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        promptText.setForeground(new Color(0, 0, 0));

        if (choice.equals("Loading...")) {
            promptText.setText("Would you like to load your quiz?");
        } else {
            promptText.setText("Would you like to save your quiz?");
        }

        promptText.setBounds(50, 20, 500, 30);

        add(promptText);

        initYesNoButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates yes and no buttons
    private void initYesNoButtons() {
        yesButton = new JButton();
        noButton = new JButton();

        buttonSettings(yesButton, "Yes", "yesButton", 120);
        buttonSettings(noButton, "No", "noButton", 220);
    }

    // EFFECTS: button settings
    private void buttonSettings(JButton button, String text, String ac, int x) {
        button.setText(text);
        button.setActionCommand(ac);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this::actionPerformed);
        add(button);
        button.setBounds(x, 80, 100, 40);
        button.setSize(60,30);
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

    // method from JsonSerializationDemo
    // EFFECTS: saves the quiz to file
    private void saveQuiz() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            System.out.println("Saved " + quiz.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: produces what yes and no buttons do when clicked
    private void yesNoButtonActionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("yesButton")) {
            if (choice.equals("Loading...")) {
                loadQuiz();
                quizGUI.dispose();
                new TakeQuizGUI(quiz);
            } else {
                saveQuiz();
                makeQuizGUI.dispose();
                new QuizGUI();
            }
        } else {
            if (choice.equals("Saving...")) {
                makeQuizGUI.dispose();
                new QuizGUI();
            }
            dispose();
        }
    }

    public void actionPerformed(ActionEvent e) {
        yesNoButtonActionPerformed(e);
    }
}
