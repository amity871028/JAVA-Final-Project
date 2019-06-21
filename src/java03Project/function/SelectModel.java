package java03Project.function;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import javax.swing.*;

import java03Project.function.analysisMusic.Music;
import java03Project.mainframe.HomePage;
import java03Project.mode.normal.NormalModel;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SelectModel extends JPanel implements ActionListener, MouseListener{

    private static final String File = null;
	private JButton normalModel;
    private JButton surviveModel;
    private JButton backButton;
    private HomePage home;
    private NormalModel normalModelPanel;
    private JLabel bgLabel = null;
    private ImageIcon background = null;
    static MediaPlayer _mediaPlayer;

    
    public SelectModel(HomePage home) {
  
        this.setSize(1280, 720);
        this.setVisible(true);
        this.home = home;
        this.setLayout(null);

        normalModel = new JButton("一般模式", new ImageIcon("src/img/game1.png"));
        normalModel.addActionListener(this);
        normalModel.setBounds(300, 500, 300, 64);
        normalModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        normalModel.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        normalModel.addMouseListener(this);
        normalModel.setForeground(new Color(193, 255, 193));
        normalModel.setOpaque(false);
        normalModel.setBackground(new Color(193, 255, 193));
        
        surviveModel = new JButton("生存模式", new ImageIcon("src/img/game3.png"));
        surviveModel.addActionListener(this);
        surviveModel.setBounds(700, 500, 300, 64);
        surviveModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        surviveModel.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        surviveModel.addMouseListener(this);
        surviveModel.setForeground(new Color(155, 205, 155));
        surviveModel.setOpaque(false);
        surviveModel.setBackground(new Color(193, 255, 193));
        
        backButton = new JButton(new ImageIcon("src/img/backbutton.png"));
        backButton.addActionListener(this);
        backButton.setBounds(50, 600, 64, 64);
        backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        backButton.addMouseListener(this);
        backButton.setOpaque(false);
        backButton.setBackground(new Color(193, 255, 193));
        
        this.add(normalModel);
        this.add(surviveModel);
        this.add(backButton);
        
        //設定背景
        background = new ImageIcon("src/img/bg2.jpg");// 背景圖片
		bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
	    bgLabel.setBounds(0, 0, 1280, 720);// 把含有背景圖之Label位置設置為圖片剛好填充整個版面
	    this.add(bgLabel, new Integer(Integer.MIN_VALUE));     // 把背景圖添加到分層窗格的最底層以作為背景
	 
    }
    
   
	@Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == normalModel) {
        	home.stopMusic();
           normalModelPanel = new NormalModel(this.home);
           home.remove(this);
           home.add(normalModelPanel);
           home.revalidate();
           home.repaint();
        }
        
        if (event.getSource() == surviveModel) {
            normalModelPanel = new NormalModel(this.home);
            home.remove(this);
            //home.add(normalModelPanel);
            home.revalidate();
            home.repaint();
         }
        
        if (event.getSource() == backButton) {
        	home.remove(this);
        	home.add(home.title);
        	home.add(home.getContent());
            home.revalidate();
            home.repaint();
        }
    }
	public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
    	if (e.getSource() == normalModel) {
    		normalModel.setBorder(BorderFactory.createRaisedBevelBorder());
    		new Music("src/backMusic/enter.wav");
    	}
    	if (e.getSource() == surviveModel) {
    		surviveModel.setBorder(BorderFactory.createRaisedBevelBorder());
    		new Music("src/backMusic/enter.wav");
    	}
    	if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createRaisedBevelBorder());
    		new Music("src/backMusic/enter.wav");
    	}
    }

	public void mouseExited(MouseEvent e) {
    	if (e.getSource() == normalModel) {
    		normalModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
    	if (e.getSource() == surviveModel) {
    		surviveModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
    	if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
    }
    public void mousePressed(MouseEvent e) {
    	if (e.getSource() == normalModel) {
    		normalModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
            	new Music("src/backMusic/click.wav");
    	}
    	if (e.getSource() == surviveModel) {
    		surviveModel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    		new Music("src/backMusic/click.wav");
    	}
    	if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    		new Music("src/backMusic/press.wav");
    	}
    }
    public void mouseReleased(MouseEvent e) {}
   
    
}
