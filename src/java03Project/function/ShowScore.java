package java03Project.function;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import java03Project.mainframe.HomePage;
import java03Project.mode.normal.NormalModel;

public class ShowScore extends JPanel implements ActionListener, MouseListener {
	
	private HomePage home;
	private NormalModel normalModelPanel;
	//private JTextArea exp, score, correct;
	private JLabel expLabel, scoreLabel, correctLabel, expImg, scoreImg, correctImg;
	private JButton IGButton, FBButton, backButton;
	private JPanel sharePanel, expPanel, scorePanel, correctPanel, homePanel;
	static BufferedImage bg = null;
	
	public ShowScore(HomePage home) {
		
		this.setSize(1280, 720);
        this.setVisible(true);
        this.home = home;
        this.setLayout(new GridLayout(5, 1));
        //設定背景
        try {
            	bg = ImageIO.read(new File("src/img/bg6.jpg"));
        	} catch (Exception ex) {
         		System.out.println(ex);
        }
      		
        sharePanel = new JPanel();
        sharePanel.setOpaque(false);
        expPanel = new JPanel();
        expPanel.setOpaque(false);
        scorePanel = new JPanel();
        scorePanel.setOpaque(false);
        correctPanel = new JPanel();
        correctPanel.setOpaque(false);
        homePanel = new JPanel();
        homePanel.setOpaque(false);
        
        
        expLabel = new JLabel("經驗值 : ");
        expImg = new JLabel(new ImageIcon("src/img/exp.png"));
        expLabel.setForeground(new Color(255, 250, 205));
        expLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        expPanel.add(expImg);
        expPanel.add(expLabel);
        add(expPanel);
        
        
        scoreLabel = new JLabel("總分數 : ");
        scoreLabel.setForeground(new Color(255, 250, 205));
        scoreLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        scoreImg = new JLabel(new ImageIcon("src/img/score.png"));
        scorePanel.add(scoreImg);
        scorePanel.add(scoreLabel);
        add(scorePanel);
        
        
        correctLabel = new JLabel("正確率 : ");
        correctLabel.setForeground(new Color(255, 250, 205));
        correctLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        correctImg = new JLabel(new ImageIcon("src/img/correct.png"));
        correctPanel.add(correctImg);
        correctPanel.add(correctLabel);
        add(correctPanel);
        
        
        backButton = new JButton(new ImageIcon("src/img/home.png"));
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        backButton.setPreferredSize(new Dimension(96, 96));
        backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        backButton.setOpaque(false);
        homePanel.add(backButton);
        
        IGButton = new JButton(new ImageIcon("src/img/ig.png"));
        IGButton.addActionListener(this);
        IGButton.addMouseListener(this);
        IGButton.setPreferredSize(new Dimension(150, 96));
        IGButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        IGButton.setOpaque(false);
        
        FBButton = new JButton(new ImageIcon("src/img/fb.png"));
        FBButton.addActionListener(this);
        FBButton.addMouseListener(this);
        FBButton.setPreferredSize(new Dimension(150, 96));
        FBButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        FBButton.setOpaque(false);
        
        sharePanel.add(IGButton);
        sharePanel.add(FBButton);
        add(sharePanel);
        add(homePanel);
        
	}

	@Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(400, 300);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            home.remove(this);
            home.add(home.getSelectPanel());
            home.revalidate();
            home.repaint();
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
            try {
                URL cb;
                File f = new File("src/backMusic/Scanner.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
        }
    	if (e.getSource() == FBButton) {
    		FBButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
            try {
                URL cb;
                File f = new File("src/backMusic/click.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
    	}
    	if (e.getSource() == IGButton) {
    		IGButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
            try {
                URL cb;
                File f = new File("src/backMusic/click.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
    	}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createRaisedBevelBorder());
            try {
                URL cb;
                File f = new File("src/backMusic/enter.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
    	}
    	if (e.getSource() == FBButton) {
    		FBButton.setBorder(BorderFactory.createRaisedBevelBorder());
            try {
                URL cb;
                File f = new File("src/backMusic/enter.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
    	}
    	if (e.getSource() == IGButton) {
    		IGButton.setBorder(BorderFactory.createRaisedBevelBorder());
            try {
                URL cb;
                File f = new File("src/backMusic/enter.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
    	}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
            
    	}
		if (e.getSource() == FBButton) {
			FBButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		if (e.getSource() == IGButton) {
			IGButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}	
	}
}
