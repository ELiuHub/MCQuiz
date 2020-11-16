package persistence;

import exceptions.EmptyException;
import model.Questions;
import model.Quiz;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// class from JsonSerializationDemo project provided
// Represents a reader that reads quiz from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads quiz from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Quiz read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuiz(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses quiz from JSON object and returns it
    private Quiz parseQuiz(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Quiz q = new Quiz(name);
        addQuestions(q, jsonObject);
        return q;
    }

    // MODIFIES: q
    // EFFECTS: parses questions from JSON object and adds them to quiz
    private void addQuestions(Quiz q, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(q, nextQuestion);
        }
    }

    // MODIFIES: q
    // EFFECTS: parses question from JSON object and adds it to quiz
    private void addQuestion(Quiz q, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String ans = jsonObject.getString("answer");
        String o1 = jsonObject.getString("option1");
        String o2 = jsonObject.getString("option2");
        String o3 = jsonObject.getString("option3");
        String o4 = jsonObject.getString("option4");
        Questions questions = new Questions(question, ans, o1, o2, o3, o4);
        try {
            q.addQuestion(questions);
        } catch (EmptyException e) {
            e.printStackTrace();
        }
    }
}
