package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionsTest {
    private Questions questions;

    @BeforeEach
    public void setUp() {
        questions = new Questions("how are you", "good",
                "ok", "bad", "sad", "mad");
    }

    @Test
    public void testConstructor() {
        assertEquals("how are you", questions.getQuestion());
        assertEquals("good", questions.getAnswer());
        assertEquals("ok", questions.getOption1());
        assertEquals("bad", questions.getOption2());
        assertEquals("sad", questions.getOption3());
        assertEquals("mad", questions.getOption4());
    }

    @Test
    public void testOptions() {
        List<String> listOfOptions = new ArrayList<>();
        listOfOptions.add("1. " + questions.getOption1());
        listOfOptions.add("2. " + questions.getOption2());
        listOfOptions.add("3. " + questions.getOption3());
        listOfOptions.add("4. " + questions.getOption4());
        assertEquals(listOfOptions, questions.options());
    }
}
