package persistence;

import model.Questions;
import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class from JsonSerializationDemo project provided
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Quiz q = new Quiz("QuizTest");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected but not thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Quiz q = new Quiz("QuizTest");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyQuiz.json");
            q = reader.read();
            assertEquals("QuizTest", q.getName());
            assertEquals(0, q.numQuestions());
        } catch (IOException e) {
            fail("IOException thrown but not expected");
        }
    }

    @Test
    void testWriterNotEmptyQuiz() {
        try {
            Quiz q = new Quiz("QuizTest");
            q.addQuestion(new Questions("how are you", "good",
                    "bad", "good", "terrible", "no"));
            q.addQuestion(new Questions("hello", "hi",
                    "stop", "no", "hi", "go away"));
            JsonWriter writer = new JsonWriter("./data/testWriterNotEmptyQuiz.json");
            writer.open();
            writer.write(q);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNotEmptyQuiz.json");
            q = reader.read();
            assertEquals("QuizTest", q.getName());
            List<Questions> questions = q.quizQuestions();
            assertEquals(2, questions.size());
            checkQuestion("how are you", "good",
                    "bad", "good", "terrible", "no", questions.get(0));
            checkQuestion("hello", "hi",
                    "stop", "no", "hi", "go away", questions.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}