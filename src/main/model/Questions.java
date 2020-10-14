package model;

import java.util.ArrayList;
import java.util.List;

// Represents a question, answer, and options
public class Questions {
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private List<String> choices;

    // EFFECTS: Constructs a question with an answer and up to four options
    public Questions(String question, String answer, String option1, String option2, String option3, String option4) {
        this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    // EFFECTS: returns a list of the options
    public List<String> options() {
        choices = new ArrayList<>();
        choices.add("1. " + getOption1());
        choices.add("2. " + getOption2());
        choices.add("3. " + getOption3());
        choices.add("4. " + getOption4());
        return choices;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

}
