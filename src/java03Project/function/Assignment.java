package java03Project.function;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java03Project.*;

public class Assignment
{
    private JFrame frame;
    private JPanel firstPanel;
    private JPanel secondPanel;

    private JButton forwardButton;
    private JButton backButton;

    private void displayGUI()
    {
        frame = new JFrame("Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        firstPanel = new JPanel();
        firstPanel.setOpaque(true);
        firstPanel.setBackground(Color.BLUE);

        secondPanel = new JPanel();
        secondPanel.setOpaque(true);
        secondPanel.setBackground(Color.RED);

        forwardButton = new JButton("Forward");
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                frame.remove(firstPanel);
                frame.add(secondPanel);
                frame.revalidate(); // For Java 1.7 or above.
                // frame.getContentPane().validate(); // For Java 1.6 or below.
                frame.repaint();
            }
        });

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                frame.remove(secondPanel);
                frame.add(firstPanel);
                frame.revalidate(); // For Java 1.7 or above.
                // frame.getContentPane().validate(); // For Java 1.6 or below.
                frame.repaint();
            }
        });

        firstPanel.add(forwardButton);
        secondPanel.add(backButton);

        frame.add(firstPanel);
        frame.setSize(300, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Assignment().displayGUI();
            }
        });
    }
}