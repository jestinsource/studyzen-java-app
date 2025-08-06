package studyzen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopwatchPanel extends JPanel {
    private Timer timer;
    private int count = 0;
    private JLabel label;
    private JButton start, pause, reset;

    public StopwatchPanel() {
        setLayout(new BorderLayout());
        label = new JLabel("00:00", SwingConstants.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 48));
        add(label, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        start = new JButton("Start");
        pause = new JButton("Pause");
        reset = new JButton("Reset");

        controls.add(start);
        controls.add(pause);
        controls.add(reset);
        add(controls, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            count++;
            label.setText(String.format("%02d:%02d", count / 60, count % 60));
        });

        start.addActionListener(e -> timer.start());
        pause.addActionListener(e -> timer.stop());
        reset.addActionListener(e -> {
            timer.stop();
            count = 0;
            label.setText("00:00");
        });
    }
}
