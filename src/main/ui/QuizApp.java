package ui;

import model.Quiz;
import model.Questions;

import java.util.Scanner;

// Quiz application
public class QuizApp {
    private Quiz quiz;
    private Questions question;
    private Scanner input;
    private int numKey;
    private String key;

    // EFFECTS: runs the application
    public QuizApp() {
        runQuiz();
    }

    // method from TellerApp
    private void runQuiz() {
        init();

        System.out.println("Welcome to the Quiz App!");

        makeQuiz();
        startMenu();
        System.out.println("-----------------------------------------------------------------------------------------");
        takeQuiz();
    }

    // method from TellerApp
    // EFFECTS: Initializes quiz and scanner
    private void init() {
        quiz = new Quiz();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays starting options of app
    private void startMenu() {
        while (true) {
            System.out.println("Press v to view what questions you have. Press r to delete a question. "
                    + "Press t to take the quiz.");
            key = input.next();
            if (key.equals("t")) {
                break;
            } else if (key.equals("v")) {
                System.out.println(quiz.viewQuestions());
            } else if (key.equals("r")) {
                userDeleteQuestion();
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: makes a quiz
    private void makeQuiz() {
        quiz.addQuestion(question = new Questions("Here are some sample questions.", "ok",
                "ok", "", "", ""));
        quiz.addQuestion(question = new Questions("How is the app?", "amazing",
                "ok", "good", "amazing", "bad"));
        quiz.addQuestion(question = new Questions("What grade would you give it?", "A",
                "A", "B", "C", "D"));
    }

    // MODIFIES: this
    // EFFECTS: asks user which question to remove
    private void userDeleteQuestion() {
        while (true) {
            System.out.println("If you wish to go back, press b. Otherwise, press any key to continue.");
            key = input.next();
            if (key.equals("b")) {
                break;
            } else {
                System.out.println("Which question would you like to delete?");
                numKey = input.nextInt();
                quiz.removeQuestion(quiz.quizQuestions().get(numKey));
                System.out.println("These are the questions you have left:");
                System.out.println(quiz.viewQuestions());
            }
        }
    }

    // EFFECTS: takes the quiz created
    private void takeQuiz() {
        int score = 0;

        if (quiz.numQuestions() == 0) {
            System.out.println("There seems to be no questions in the quiz :(");
        } else {
            for (Questions q : quiz.quizQuestions()) {
                System.out.println(q.getQuestion());
                System.out.println("Your options are:");
                for (String s : q.options()) {
                    System.out.println(s);
                }

                System.out.println("What is your answer?");
                numKey = input.nextInt();

                processCommand(q);

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
    // REQUIRES: 1 <= numKey <= 4
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
}
