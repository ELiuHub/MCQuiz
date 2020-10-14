package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        quiz = new Quiz();
        questions = new Questions("how are you", "good",
                "ok", "bad", "sad", "mad");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, quiz.numQuestions());
    }

    @Test
    public void testAddQuestionOnce() {
        quiz.addQuestion(questions);
        assertEquals(1, quiz.numQuestions());
    }

    @Test
    public void testAddMultipleQuestions() {
        for (int i = 0; i < numTimes; i++) {
            quiz.addQuestion(questions);
        }
        assertEquals(numTimes, quiz.numQuestions());
    }

    @Test
    public void testRemoveQuestionOnce() {
        quiz.addQuestion(questions);
        quiz.addQuestion(questions);
        quiz.removeQuestion(questions);
        assertEquals(1, quiz.numQuestions());
    }

    @Test
    public void testRemoveMultipleQuestions() {
        for (int i = 0; i < numTimes; i++) {
            quiz.addQuestion(questions);
            quiz.removeQuestion(questions);
        }
        assertEquals(0, quiz.numQuestions());
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
}