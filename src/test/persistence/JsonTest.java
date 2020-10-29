package persistence;

import model.Questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkQuestion(String question, String ans,
                                 String op1, String op2, String op3, String op4, Questions q) {
        assertEquals(question, q.getQuestion());
        assertEquals(ans, q.getAnswer());
        assertEquals(op1, q.getOption1());
        assertEquals(op2, q.getOption2());
        assertEquals(op3, q.getOption3());
        assertEquals(op4, q.getOption4());
    }
}
