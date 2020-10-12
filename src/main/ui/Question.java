package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a question with an answer and a list of options
public class Question {
    private String question;
    private String answer;
    private List<String> options;
    private Scanner input = new Scanner(System.in);

    // EFFECTS: Constructs a question with an answer and list of options
    public Question() {
        setQuestion();
        setCorrectAnswer();
        setOptions();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion() {
        System.out.println("What question would you like to ask?");
        question = input.nextLine();
    }

    public String getCorrectAnswer() {
        return answer;
    }

    public void setCorrectAnswer() {
        System.out.println("What is the correct answer?");
        answer = input.nextLine();
    }

    public void getOptions() {
        for (String s : options) {
            System.out.println(s);
        }
    }

    // REQUIRES: must contain correct answer
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

