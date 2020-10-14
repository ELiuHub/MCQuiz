package model;

import ui.QuizSetUp;

import java.util.List;

// Represents a question, answer, and options
public class Questions {
    QuizSetUp quizSetUp;

    public Questions(QuizSetUp qsu) {
        quizSetUp = qsu;
    }

    // EFFECTS: returns the question from user input
    public String question() {
        return quizSetUp.getQuestion();
    }

    // EFFECTS: returns the answer from user input
    public String answer() {
        return quizSetUp.getCorrectAnswer();
    }

    // EFFECTS: returns the options from user input
    public List<String> options() {
        return quizSetUp.getOptions();
    }
}
