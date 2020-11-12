package ui;

import model.Quiz;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

// pop up window that gives user the option to save quiz
public class SavePrompt extends JFrame {
    private JLabel promptText;
    private JButton yesButton;
    private JButton noButton;
    private Quiz quiz;
    private static final String JSON_STORE = "./data/quiz.json";

    // EFFECTS: prompts user to save quiz
    public SavePrompt(Quiz quiz) {
        super("Saving...");
        initSaveWindow();
        pack();
        setVisible(true);
        setResizable(false);
        this.quiz = quiz;
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initSaveWindow() {
        setPreferredSize(new Dimension(400, 200));
        promptText = new JLabel();

        setLayout(null);

        promptText.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        promptText.setForeground(new Color(0, 0, 0));
        promptText.setText("Would you like to save your quiz?");
        promptText.setBounds(45, 20, 500, 30);

        add(promptText);

        initYesNoButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates yes and no buttons
    private void initYesNoButtons() {
        yesButton = new JButton();
        noButton = new JButton();

        yesButton.setText("Yes");
        yesButton.setActionCommand("yesButton");
        yesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        yesButton.addActionListener(this::actionPerformed);
        add(yesButton);
        yesButton.setBounds(100, 80, 100, 40);
        yesButton.setSize(60,30);

        noButton.setText("No");
        noButton.setActionCommand("noButton");
        noButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        noButton.addActionListener(this::actionPerformed);
        add(noButton);
        noButton.setBounds(200, 80, 100, 40);
        noButton.setSize(60,30);
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
            saveQuiz();
        }
        dispose();
        new QuizGUI();
    }

    public void actionPerformed(ActionEvent e) {
        yesNoButtonActionPerformed(e);
    }
}
