package model;

import java.util.ArrayList;
import java.util.List;

// Represents a quiz
public class Quiz {
    private List<Questions> questions;

    // EFFECTS: Constructs a quiz with no questions
    public Quiz() {
        questions = new ArrayList<>();
    }

    // returns list of questions in quiz
    public List<Questions> quizQuestions() {
        return questions;
    }

    // MODIFIES: this
    // EFFECTS: add question q to quiz
    public void addQuestion(Questions q) {
        questions.add(q);
    }

    // REQUIRES: quiz cannot be empty, enter index position of question to be removed
    // MODIFIES: this
    // EFFECTS: remove question q from quiz
    public void removeQuestion(Questions q) {
        questions.remove(q);
    }

    // EFFECTS: returns the number of questions in the quiz
    public int numQuestions() {
        return questions.size();
    }

    // EFFECTS: returns list of questions in quiz as strings
    public List<String> viewQuestions() {
        List<String> listOfQuestions = new ArrayList<>();
        for (Questions q : questions) {
            listOfQuestions.add(q.getQuestion());
        }
        return listOfQuestions;
    }
}
