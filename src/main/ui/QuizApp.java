package ui;

import model.Quiz;
import model.Questions;

import java.util.Scanner;

// Quiz application
public class QuizApp {
    private Scanner input;
    private Quiz quiz;
    private String key;

    // EFFECTS: runs the quiz app
    public QuizApp() {
        runQuiz();
    }

    // method from TellerApp
    private void runQuiz() {
        init();

        System.out.println("Welcome to the Quiz App!");

        makeQuiz();
        takeQuiz();
    }

    // method from TellerApp
    // MODIFIES: this
    // EFFECTS: Initializes quiz and scanner
    private void init() {
        quiz = new Quiz();
        input = new Scanner(System.in);
    }

    // REQUIRES: must enter a key instructed by program
    // EFFECTS: Makes a new quiz
    private void makeQuiz() {
        QuizSetUp qsu;
        int numKey;
        System.out.println("Let's make a quiz!");

        quiz.addQuestion(new Questions(qsu = new QuizSetUp()));

        while (true) {
            System.out.println("Would you like to add another question to the quiz? (Yes/No)");
            key = input.next();
            if (key.equals("no")) {
                noMoreQuestions();
                numKey = input.nextInt();
                if (processCommand(numKey)) {
                    break;
                }
            } else {
                quiz.addQuestion(new Questions(qsu = new QuizSetUp()));
            }
        }
    }

    // method from TellerApp
    // REQUIRES: must enter one of the displayed keys
    // MODIFIES: this
    // EFFECTS: processes user command
    private boolean processCommand(int numKey) {
        if (numKey == 4) {
            return true;
        } else if (numKey == 2) {
            System.out.println(quiz.viewQuestions());
        } else if (numKey == 3) {
            System.out.println("Here are the questions you have: ");
            System.out.println(quiz.viewQuestions());
            System.out.println("Which question would you like to delete?");
            numKey = input.nextInt();
            quiz.removeQuestion(quiz.quizQuestions().get(numKey));
            System.out.println("These are the questions you have left:");
            System.out.println(quiz.viewQuestions());
        }
        return false;
    }

    // EFFECTS: Takes the quiz created
    private void takeQuiz() {
        int score = 0;

        System.out.println("Let's start the quiz!");

        for (Questions q : quiz.quizQuestions()) {
            System.out.println(q.question());
            System.out.println("Your options are:");

            for (String s : q.options()) {
                System.out.println(s);
            }

            System.out.println("What is your answer?");
            input.nextLine();
            key = input.nextLine();

            if (key.equals(q.answer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Sorry, that was incorrect.");
            }
        }
        System.out.println("You got " + score + " out of " + quiz.numQuestions());
    }

    // EFFECTS: prints out instructions
    private void noMoreQuestions() {
        System.out.println("If you changed your mind, press 1 to add more questions.");
        System.out.println("To see what questions you have written, press 2.");
        System.out.println("To delete a question, press 3.");
        System.out.println("To finish making your quiz, press 4.");
    }

}
