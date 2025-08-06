package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class PDFViewerPanel extends JPanel {
    private JLabel fileLabel;

    public PDFViewerPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("PDF Viewer", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JButton browseButton = new JButton("Open PDF");
        fileLabel = new JLabel("No file selected", SwingConstants.CENTER);

        browseButton.addActionListener(this::openPDF);

        add(browseButton, BorderLayout.CENTER);
        add(fileLabel, BorderLayout.SOUTH);
    }

    private void openPDF(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose PDF File");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File pdfFile = fileChooser.getSelectedFile();
            fileLabel.setText("Selected: " + pdfFile.getName());

            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    JOptionPane.showMessageDialog(this, "Desktop not supported. Can't open file.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening PDF: " + ex.getMessage());
            }
        }
    }
}
