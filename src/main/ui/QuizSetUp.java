package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the quiz setup, prompting the user to enter
// a question, answer, and answer options
public class QuizSetUp {
    private String question;
    private String answer;
    private List<String> choices;
    private List<String> options;
    private Scanner input = new Scanner(System.in);

    // EFFECTS: asks the user to enter a question,
    // answer, and answer options
    public QuizSetUp() {
        askQuestion();
        setCorrectAnswer();
        setOptions();
    }

    public String getQuestion() {
        return question;
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter a question
    public void askQuestion() {
        System.out.println("What question would you like to ask?");
        question = input.nextLine();
    }

    public String getCorrectAnswer() {
        return answer;
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter the correct answer
    public void setCorrectAnswer() {
        System.out.println("What is the correct answer?");
        answer = input.nextLine();
    }

    public List<String> getOptions() {
        choices = new ArrayList<>();
        for (String s : options) {
            choices.add(s);
        }
        return choices;
    }

    // REQUIRES: must contain correct answer
    // MODIFIES: this
    // EFFECTS: prompts the user to enter answer options
    public void setOptions() {
        int numChoice;
        options = new ArrayList<>();

        System.out.println("How many options do you want to give?");
        numChoice = input.nextInt();
        System.out.println("What are the options?");
        input.nextLine();

        for (int i = 0; i < numChoice; i++) {
            options.add(input.nextLine());
        }
    }
}

