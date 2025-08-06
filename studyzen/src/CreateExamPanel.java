package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class CreateExamPanel extends JPanel {
    private JTextField examNameField;
    private JTextArea questionArea, optionAField, optionBField, optionCField, optionDField;
    private JTextField correctOptionField;
    private JTextArea outputLog;

    public CreateExamPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create Exam", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        examNameField = new JTextField();
        questionArea = new JTextArea(2, 20);
        optionAField = new JTextArea(1, 20);
        optionBField = new JTextArea(1, 20);
        optionCField = new JTextArea(1, 20);
        optionDField = new JTextArea(1, 20);
        correctOptionField = new JTextField();

        formPanel.add(labeled("Exam Name", examNameField));
        formPanel.add(labeled("Question", questionArea));
        formPanel.add(labeled("Option A", optionAField));
        formPanel.add(labeled("Option B", optionBField));
        formPanel.add(labeled("Option C", optionCField));
        formPanel.add(labeled("Option D", optionDField));
        formPanel.add(labeled("Correct Option (A/B/C/D)", correctOptionField));

        JButton saveButton = new JButton("Save Question");
        saveButton.addActionListener(this::saveQuestion);

        outputLog = new JTextArea(5, 30);
        outputLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputLog);

        add(formPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);
    }

    private JPanel labeled(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label + ": "), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void saveQuestion(ActionEvent e) {
        String examName = examNameField.getText().trim();
        String question = questionArea.getText().trim();
        String a = optionAField.getText().trim();
        String b = optionBField.getText().trim();
        String c = optionCField.getText().trim();
        String d = optionDField.getText().trim();
        String correct = correctOptionField.getText().trim().toUpperCase();

        if (examName.isEmpty() || question.isEmpty() || a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || correct.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try (FileWriter writer = new FileWriter("data/Exam_" + examName + ".txt", true)) {
            writer.write("Q: " + question + "\n");
            writer.write("A) " + a + "\n");
            writer.write("B) " + b + "\n");
            writer.write("C) " + c + "\n");
            writer.write("D) " + d + "\n");
            writer.write("Answer: " + correct + "\n");
            writer.write("----------------------\n");

            outputLog.append("Question saved to Exam_" + examName + ".txt\n");
            clearFields();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    private void clearFields() {
        questionArea.setText("");
        optionAField.setText("");
        optionBField.setText("");
        optionCField.setText("");
        optionDField.setText("");
        correctOptionField.setText("");
    }
}
