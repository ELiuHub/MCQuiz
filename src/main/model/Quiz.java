package model;

import ui.Question;

import java.util.ArrayList;
import java.util.List;

// Represents a quiz
public class Quiz {
    private List<Question> questions;

    // EFFECTS: Constructs a quiz with no questions
    public Quiz() {
        questions = new ArrayList<>();
    }

    public List<Question> quizQuestions() {
        return questions;
    }

    // MODIFIES: this
    // EFFECTS: add question q to quiz
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // MODIFIES: this
    // EFFECTS: remove question q from quiz
    public void removeQuestion(Question q) {
        questions.remove(q);
    }

    // EFFECTS: returns the number of questions in the quiz
    public int numQuestions() {
        return questions.size();
    }

    // EFFECTS: returns list of questions in quiz as strings
    public List<String> viewQuestions() {
        List<String> listOfQuestions =  new ArrayList<>();
        for (Question q : questions) {
            listOfQuestions.add(q.getQuestion());
        }
        return listOfQuestions;
    }
}
