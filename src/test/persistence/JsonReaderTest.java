package persistence;

import exceptions.EmptyException;
import model.Questions;
import model.Quiz;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test class from JsonSerializationDemo project provided
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz q = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals("QuizTest", q.getName());
            assertEquals(0, q.numQuestions());
        } catch (IOException e) {
            fail("IOException thrown but not expected");
        }
    }

    @Test
    void testReaderNotEmptyQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderNotEmptyQuiz.json");
        try {
            Quiz q = reader.read();
            assertEquals("QuizTest", q.getName());
            List<Questions> questions = q.quizQuestions();
            assertEquals(2, questions.size());
            checkQuestion("hello", "hi",
                    "stop", "no", "hi", "go away", questions.get(0));
            checkQuestion("how are you", "good",
                    "bad", "good", "terrible", "no", questions.get(1));
        } catch (IOException e) {
            fail("IOException thrown but not expected");
        }
    }
}
