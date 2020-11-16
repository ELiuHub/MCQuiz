package model;

import exceptions.EmptyException;
import exceptions.LastQuestionException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a quiz
public class Quiz implements Writable {
    private List<Questions> questions;
    String name;

    // EFFECTS: Constructs a quiz with no questions and a name
    public Quiz(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // returns list of questions in quiz
    public List<Questions> quizQuestions() {
        return questions;
    }

    // MODIFIES: this
    // EFFECTS: add question q to quiz
    public void addQuestion(Questions q) throws EmptyException {
        boolean optionsEmpty = q.getOption1().isEmpty() && q.getOption2().isEmpty() && q.getOption3().isEmpty()
                && q.getOption4().isEmpty();

        if (q.getQuestion().isEmpty() || q.getAnswer().isEmpty() || optionsEmpty) {
            throw new EmptyException();
        } else {
            questions.add(q);
        }
    }

    // MODIFIES: this
    // EFFECTS: if last question in quiz throw LastQuestionException
    //          otherwise remove question q from quiz
    public void removeQuestion(Questions q) throws LastQuestionException {
        if (questions.size() == 1) {
            throw new LastQuestionException();
        }
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

    // method from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("questions", questionsToJson());
        return json;
    }

    // method from JsonSerializationDemo
    // EFFECTS: returns things in this quiz as a JSON array
    public JSONArray questionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Questions q : questions) {
            jsonArray.put(q.toJson());
        }
        return jsonArray;
    }
}

