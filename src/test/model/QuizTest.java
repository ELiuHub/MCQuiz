package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    Quiz quiz;
    Question question;
    List<String> q;

    @BeforeEach
    public void setUp() {
        quiz = new Quiz();
        question = new Question();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, quiz.numQuestions());
    }

    @Test
    public void testAddQuestion() {
        quiz.addQuestion(question);
        assertEquals(1, quiz.numQuestions());
    }

    @Test
    public void testRemoveQuestion() {
        quiz.addQuestion(question);
        quiz.removeQuestion(question);
        assertEquals(0, quiz.numQuestions());
    }

    @Test
    public void testViewQuestions() {
        quiz.addQuestion(question);
        q.add(question.getQuestion());
        assertEquals(q, quiz.viewQuestions());
    }
}