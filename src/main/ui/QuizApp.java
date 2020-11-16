package ui;

import exceptions.EmptyException;
import exceptions.InvalidInputException;
import exceptions.LastQuestionException;
import model.Quiz;
import model.Questions;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Quiz application
public class QuizApp {
    private static final String JSON_STORE = "./data/quiz.json";
    private Quiz quiz;
    private Scanner input;
    private int numKey;
    private String key;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the application
    public QuizApp() {
        runQuiz();
    }

    // method from TellerApp
    private void runQuiz() {
        init();
        System.out.println("Welcome to the Quiz App!");
        startMenu();
    }

    // method from TellerApp
    // EFFECTS: Initializes quiz, scanner, reader, and writer
    private void init() {
        quiz = new Quiz("Quiz");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays starting options of app
    private void startMenu() {
        while (true) {
            System.out.println("Press m to make a new quiz. Press t to take a quiz. "
                    + "Press q to quit.");
            key = input.next();
            if (key.equals("t")) {
                takeQuiz();
                break;
            } else if (key.equals("m")) {
                makeQuiz();
                takeQuiz();
                break;
            } else if (key.equals("q")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // EFFECTS: makes a quiz
    private void makeQuiz() {
        System.out.println("Let's make a quiz!");

        quizSetUp();

        while (true) {
            System.out.println("Would you like to add another question to the quiz? (Yes/No)");
            key = input.next();

            if (key.equals("no")) {
                noMoreQuestions();
                numKey = input.nextInt();
                try {
                    if (processInput(numKey)) {
                        saveQuiz();
                        break;
                    }
                } catch (InvalidInputException e) {
                    System.out.println("Enter one of the displayed numbers.");
                }
            } else if (key.equals("yes")) {
                quizSetUp();
            } else {
                System.out.println("Please enter yes or no.");
            }
        }

    }

    // REQUIRES: options must contain answer
    // MODIFIES: this
    // EFFECTS: prompts user to enter question, answer, options
    private void quizSetUp() {
        String question;
        List<String> options;
        String answer;
        System.out.println("What question would you like to ask?");
        input.nextLine();
        question = input.nextLine();
        System.out.println("What is the correct answer?");
        answer = input.nextLine();
        options = new ArrayList<>();
        System.out.println("What are the options?");

        for (int i = 0; i < 4; i++) {
            options.add(input.nextLine());
        }

        try {
            quiz.addQuestion(new Questions(question, answer,
                    options.get(0), options.get(1), options.get(2), options.get(3)));
        } catch (EmptyException e) {
            System.out.println("Not enough information entered!");
        }
    }

    // method from TellerApp
    // EFFECTS: processes user command
    //          if user doesn't input one of the displayed keys, throw InvalidInputException
    private boolean processInput(int numKey) throws InvalidInputException {
        if (numKey == 4) {
            return true;
        } else if (numKey == 1) {
            quizSetUp();
        } else if (numKey == 2) {
            System.out.println(quiz.viewQuestions());
        } else if (numKey == 3) {
            deleteQuestion();
        } else {
            throw new InvalidInputException();
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose what question to remove
    private void deleteQuestion() {
        int numKey;
        System.out.println("Here are the questions you have: ");
        System.out.println(quiz.viewQuestions());
        System.out.println("Which question would you like to delete? Enter the number of the question you'd "
                + "like to delete (starts at 0).");
        numKey = input.nextInt();
        try {
            quiz.removeQuestion(quiz.quizQuestions().get(numKey));
        } catch (LastQuestionException e) {
            System.out.println("This is the last question in the quiz!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not one of the questions!");
        }
        System.out.println("These are the questions you have left:");
        System.out.println(quiz.viewQuestions());
    }

    // EFFECTS: prints out instructions
    private void noMoreQuestions() {
        System.out.println("If you changed your mind, press 1 to add more questions.");
        System.out.println("To see what questions you have written, press 2.");
        System.out.println("To delete a question, press 3.");
        System.out.println("To finish making your quiz, press 4.");
    }

    // EFFECTS: takes the quiz
    private void takeQuiz() {
        int score = 0;

        if (quiz.numQuestions() == 0) {
            loadQuiz();
            takeQuiz();
        } else {
            for (Questions q : quiz.quizQuestions()) {
                System.out.println(q.getQuestion());
                System.out.println("Your options are:");
                for (String s : q.options()) {
                    System.out.println(s);
                }

                System.out.println("What is your answer?");
                numKey = input.nextInt();

                if (processCommand(q).equals(q.getAnswer())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Sorry, that was incorrect.");
                }
            }
            System.out.println("You got " + score + " out of " + quiz.numQuestions());
        }
    }

    // method from TellerApp
    // EFFECTS: processes user input
    private String processCommand(Questions q) {
        if (numKey == 1) {
            return q.getOption1();
        } else if (numKey == 2) {
            return q.getOption2();
        } else if (numKey == 3) {
            return q.getOption3();
        } else {
            return q.getOption4();
        }
    }

    // method from JsonSerializationDemo
    // EFFECTS: saves the quiz to file
    private void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            System.out.println("Saved " + quiz.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // method from JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads quiz from file
    private void loadQuiz() {
        try {
            quiz = jsonReader.read();
            System.out.println("Loaded " + quiz.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

