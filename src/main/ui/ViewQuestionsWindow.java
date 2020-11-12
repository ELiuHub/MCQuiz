package ui;

import exceptions.LastQuestionException;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// represents window that allows user to view what questions they have
public class ViewQuestionsWindow extends JFrame {
    private DefaultListModel listModel;
    private JList list;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private Quiz quiz;

    // EFFECTS: new window to view questions in quiz
    public ViewQuestionsWindow(Quiz quiz) {
        super("Viewing Questions");
        initViewQuestionsWindow(quiz);
        pack();
        setVisible(true);
        setResizable(false);
        this.quiz = quiz;
    }

    // MODIFIES: this
    // EFFECTS: initializes window and creates remove button
    private void initViewQuestionsWindow(Quiz quiz) {
        setPreferredSize(new Dimension(500, 400));
        listModel = new DefaultListModel();

        for (int i = 0; i < quiz.numQuestions(); i++) {
            listModel.addElement(quiz.viewQuestions().get(i));
        }

        list = new JList(listModel);
        list.setFont(new Font("Bahnschrift Condensed", Font.PLAIN, 20));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane(list);

        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(this::actionPerformed);

        add(scrollPane, BorderLayout.CENTER);
        add(removeButton, BorderLayout.PAGE_END);
    }

    // EFFECTS: produces what remove button does when clicked
    //          catches LastQuestionException if user tries to
    //          remove the last question in the quiz
    private void removeButtonActionPerformed(ActionEvent e) {
        int index = list.getSelectedIndex();

        if (listModel.size() > 1) {
            listModel.remove(index);
        }
        
        try {
            quiz.removeQuestion(quiz.quizQuestions().get(index));
        } catch (LastQuestionException lastQuestionException) {
            System.out.println("This is the last question in the quiz!");
        }
    }

    private void actionPerformed(ActionEvent e) {
        removeButtonActionPerformed(e);
    }
}
