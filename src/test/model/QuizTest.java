package model;

import exceptions.EmptyException;
import exceptions.InvalidInputException;
import exceptions.LastQuestionException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.QuizApp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    private Quiz quiz;
    private Questions questions;
    private int numTimes = 5;
    private List<Questions> loq;
    private List<String> los;

    @BeforeEach
    public void setUp() {
        quiz = new Quiz("Quiz");
        questions = new Questions("how are you", "good",
                "ok", "bad", "sad", "mad");
    }

    @Test
    public void testConstructor() {
        assertEquals("Quiz", quiz.getName());
        assertEquals(0, quiz.numQuestions());
    }

    @Test
    public void testAddQuestionOnce() {
        quiz.addQuestion(questions);
        assertEquals(1, quiz.numQuestions());
    }

    @Test
    public void testAddQuestionException() {
        Questions q = new Questions("", "answer", "1", "2", "3", "4");
        try {
            quiz.addQuestion(q);
            throw new EmptyException();
        } catch (EmptyException e) {
            // expected
        }
    }

    @Test
    public void testAddQuestionAnswerException() {
        Questions q = new Questions("question", "", "1", "2", "3", "4");
        try {
            quiz.addQuestion(q);
            throw new EmptyException();
        } catch (EmptyException e) {
            // expected
        }
    }

    @Test
    public void testAddQuestionOptionsException() {
        Questions q = new Questions("question", "answer", "", "", "", "");
        try {
            quiz.addQuestion(q);
            throw new EmptyException();
        } catch (EmptyException e) {
            // expected
        }
    }

    @Test
    public void testAddQuestionOptions() {
        Questions q1 = new Questions("question", "answer", "1", "", "", "");
        Questions q2 = new Questions("question", "answer", "", "2", "", "");
        Questions q3 = new Questions("question", "answer", "", "", "3", "");
        Questions q4 = new Questions("question", "answer", "", "", "", "4");
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
        assertEquals(4, quiz.numQuestions());
    }

    @Test
    public void testAddMultipleQuestions() {
        for (int i = 0; i < numTimes; i++) {
            quiz.addQuestion(questions);
        }
        assertEquals(numTimes, quiz.numQuestions());
    }

    @Test
    public void testRemoveQuestion() {
        quiz.addQuestion(questions);
        quiz.addQuestion(questions);
        try {
            quiz.removeQuestion(questions);
        } catch (LastQuestionException e) {
            fail("LastQuestionException thrown, not expected");
        }
        assertEquals(1, quiz.numQuestions());
    }

    @Test
    public void testRemoveQuestionExceptionExpected() {
        quiz.addQuestion(questions);
        try {
            quiz.removeQuestion(questions);
            fail("This is your last question!");
        } catch (LastQuestionException e) {
            // expected
        }
    }

    @Test
    public void testViewQuestions() {
        los = new ArrayList<>();
        los.add(questions.getQuestion());
        quiz.addQuestion(questions);
        assertEquals(los, quiz.viewQuestions());
    }

    @Test
    public void testViewMultipleQuestions() {
        los = new ArrayList<>();
        for (int i = 0; i < numTimes; i++) {
            los.add(questions.getQuestion());
            quiz.addQuestion(questions);
        }
        assertEquals(los, quiz.viewQuestions());
    }

    @Test
    public void testQuizQuestions() {
        loq = new ArrayList<>();
        loq.add(questions);
        quiz.addQuestion(questions);
        assertEquals(loq, quiz.quizQuestions());
    }

    @Test
    public void testMultipleQuizQuestions() {
        loq = new ArrayList<>();
        for (int i = 0; i < numTimes; i++) {
            loq.add(questions);
            quiz.addQuestion(questions);
        }
        assertEquals(loq, quiz.quizQuestions());
    }

    @Test
    public void testToJson() {
        JSONObject json = new JSONObject();
        json.put("name", quiz.getName());
        json.put("questions", quiz.questionsToJson());
        assertEquals(json.toString(), quiz.toJson().toString());
    }

    @Test
    public void testQuestionsToJson() {
        loq = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < numTimes; i++) {
            loq.add(questions);
            quiz.addQuestion(questions);
        }
        for (Questions q : loq) {
            jsonArray.put(q.toJson());
        }
        assertEquals(jsonArray.toString(), quiz.questionsToJson().toString());
    }
}