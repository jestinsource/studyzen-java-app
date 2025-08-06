package studyzen;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private JPanel sidePanel, contentPanel;

    public DashboardFrame(String user) {
        setTitle("StudyZen Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        sidePanel = new JPanel(new GridLayout(8, 1));
        sidePanel.setPreferredSize(new Dimension(180, 0));
        sidePanel.setBackground(new Color(30, 30, 60));

        String[] features = {"Timer", "Stopwatch", "Flashcards", "PDF Viewer", "Create Exam"};
        for (String feature : features) {
            JButton btn = new JButton(feature);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(50, 50, 80));
            btn.setForeground(Color.WHITE);
            btn.addActionListener(e -> loadPanel(feature));
            sidePanel.add(btn);
        }

        contentPanel = new JPanel(new BorderLayout());
        loadPanel("Timer");

        add(sidePanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadPanel(String name) {
        contentPanel.removeAll();
        switch (name) {
            case "Timer": contentPanel.add(new TimerPanel()); break;
            case "Stopwatch": contentPanel.add(new StopwatchPanel()); break;
            case "Flashcards": contentPanel.add(new FlashcardPanel()); break;
            case "PDF Viewer": contentPanel.add(new PDFViewerPanel()); break;
            case "Create Exam": contentPanel.add(new CreateExamPanel()); break;
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
