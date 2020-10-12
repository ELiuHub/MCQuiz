package ui;

import model.Quiz;

import java.util.Scanner;

public class QuizApp {
    private Scanner input;
    private Quiz quiz;
    private String key;

    public QuizApp() {
        runApp();
    }

    // EFFECTS: starts the quiz app
    private void runApp() {
        init();

        System.out.println("Welcome to the Quiz App!");
        makeQuiz();
        takeQuiz();
    }

    // EFFECTS: Constructs a new quiz and scanner
    private void init() {
        quiz = new Quiz();
        input = new Scanner(System.in);
    }

    // EFFECTS: Makes a new quiz
    private void makeQuiz() {
        int numKey;

        System.out.println("Let's make a quiz!");

        quiz.addQuestion(new Question());

        while (true) {
            System.out.println("Would you like to add another question to the quiz? (Yes/No)");
            key = input.next();
            if (key.equals("no")) {
                noMoreQuestions();
                numKey = input.nextInt();
                if (numKey == 4) {
                    break;
                } else if (numKey == 2) {
                    System.out.println(quiz.viewQuestions());
                } else if (numKey == 3) {
                    System.out.println("Which question would you like to delete?");
                    numKey = input.nextInt();
                    quiz.removeQuestion(quiz.quizQuestions().get(numKey));
                    System.out.println("These are the questions you have left:");
                    System.out.println(quiz.viewQuestions());
                }
            }
        }
    }

    // EFFECTS: Takes the quiz created
    private void takeQuiz() {
        int score = 0;

        System.out.println("Let's start the quiz!");

        for (Question q : quiz.quizQuestions()) {
            System.out.println(q.getQuestion());
            System.out.println("Your options are:");
            q.getOptions();
            System.out.println("What is your answer?");
            input.nextLine();
            key = input.nextLine();
            if (key.equals(q.getCorrectAnswer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Sorry, that was incorrect.");
            }
        }
        System.out.println("You got " + score + " out of " + quiz.numQuestions());
    }

    private void noMoreQuestions() {
        System.out.println("If you changed your mind, press 1 to add more questions.");
        System.out.println("To see what questions you have written, press 2.");
        System.out.println("To delete a question, press 3.");
        System.out.println("To finish making your quiz, press 4.");
    }

}
