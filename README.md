# CPSC 210 Term Project

## A Multiple Choice Quiz App

**What will the application do?**
- The application that I will be making for this term project is a multiple choice quiz application. You will be able 
to add questions to the quiz as well as what the options will be and how many options you want them to have. You will be
able to view what questions you have made so far and remove them if you wish. When you are done making the quiz, you
will be able to take the quiz. When taking the quiz there will be a timer, and you will be able to ask for hints and 
get a score at the end.

**Who will use it?**
- I like this idea for my project because it's practical. Students could use it to help study for tests, or teachers
could use it to test their students' knowledge.

**Why is this project of interest to you?**
- I came up with this idea because it seemed like it could be challenging, especially if you add extra features, yet
doable at my current skill level. Also, like I mentioned before, it's practical. When I'm done making the application,
I will be able to actually use it.

## User Stories

- As a user, I want to be able to make a new quiz
- As a user, I want to be able to add questions to my quiz
- As a user, I want to be able to remove questions from my quiz
- As a user, I want to be able to view what questions are in my quiz
- As a user, I want to be able to take my quiz
- As a user, I want to be able to save my quiz to file
- As a user, I want to be able to load my quiz and take it

## Phase 4 Task 2
- The option that I chose to implement was to make a class in my model package robust because I had already designed a
method in a previous phase that threw a checked exception. In my Quiz class, the removeQuestion method throws a checked 
exception whenever the user tries to remove the last question in the quiz. This exception is being caught in my
ViewQuestionsWindow class.