package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerPanel extends JPanel {
    private Timer timer;
    private int seconds = 1500;
    private JButton start, reset;
    private JLabel timeLabel;

    public TimerPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        timeLabel = new JLabel(formatTime(seconds), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Courier", Font.BOLD, 48));
        timeLabel.setForeground(new Color(0, 102, 204));
        add(timeLabel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        start = new JButton("Start");
        reset = new JButton("Reset");
        btnPanel.add(start);
        btnPanel.add(reset);
        add(btnPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            if (seconds > 0) seconds--;
            timeLabel.setText(formatTime(seconds));
        });

        start.addActionListener(e -> timer.start());
        reset.addActionListener(e -> {
            timer.stop();
            seconds = 1500;
            timeLabel.setText(formatTime(seconds));
        });
    }

    private String formatTime(int secs) {
        return String.format("%02d:%02d", secs / 60, secs % 60);
    }
}
