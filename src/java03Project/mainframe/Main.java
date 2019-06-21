package java03Project.mainframe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
//w  w w . jav  a2  s .c om
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {
  static BufferedImage bg = null;

  public static void main(String[] args) {
    try {
      bg = ImageIO.read(new File("src/img/bg.jpg"));
    } catch (Exception ex) {
      System.out.println(ex);
    }

    JPanel tabPanel = new JPanel(new GridBagLayout()) {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
      }
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(400, 300);
      }
    };
    JPanel buttons = new JPanel(new GridLayout(4, 1, 15, 15));
    buttons.setOpaque(false);
    for (int i = 0; i < 4; i++) {
      buttons.add(new JButton("Button"));
    }
    tabPanel.add(buttons);

    JTabbedPane tabPane = new JTabbedPane();
    tabPane.add("Panel with Bachground", tabPanel);

    JFrame frame = new JFrame();
    frame.setContentPane(tabPane);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}