package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class FlashcardPanel extends JPanel {
    private JTextField questionField;
    private JTextField answerField;
    private JTextArea flashcardDisplay;
    private static final String FLASHCARD_FILE = "flashcards.txt";

    public FlashcardPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Flashcards", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Question:"));
        questionField = new JTextField();
        inputPanel.add(questionField);

        inputPanel.add(new JLabel("Answer:"));
        answerField = new JTextField();
        inputPanel.add(answerField);

        JButton saveButton = new JButton("Save Flashcard");
        JButton viewButton = new JButton("View Flashcards");

        inputPanel.add(saveButton);
        inputPanel.add(viewButton);

        add(inputPanel, BorderLayout.CENTER);

        flashcardDisplay = new JTextArea(10, 40);
        flashcardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(flashcardDisplay);
        add(scrollPane, BorderLayout.SOUTH);

        // Button Actions
        saveButton.addActionListener(this::saveFlashcard);
        viewButton.addActionListener(this::loadFlashcards);
    }

    private void saveFlashcard(ActionEvent e) {
        String question = questionField.getText().trim();
        String answer = answerField.getText().trim();

        if (question.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both fields are required.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FLASHCARD_FILE, true))) {
            writer.write("Q: " + question);
            writer.newLine();
            writer.write("A: " + answer);
            writer.newLine();
            writer.write("-----");
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Flashcard saved!");
            questionField.setText("");
            answerField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving flashcard: " + ex.getMessage());
        }
    }

    private void loadFlashcards(ActionEvent e) {
        flashcardDisplay.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(FLASHCARD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                flashcardDisplay.append(line + "\n");
            }
        } catch (IOException ex) {
            flashcardDisplay.setText("No flashcards found.");
        }
    }
}
