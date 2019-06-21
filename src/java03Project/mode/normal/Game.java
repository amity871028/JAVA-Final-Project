package java03Project.mode.normal;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java03Project.function.Player;
import java03Project.function.ShowScore;
import java03Project.function.analysisMusic.Music;
import java03Project.mainframe.HomePage;

public class Game extends JPanel implements ChangeListener, KeyListener{
	
	private static HomePage home;
	private JPanel showMessage, showGame, mpPanel, gradePanel, propPanel;
	private static JProgressBar blood; 
	private JTextArea point, display, testArea;
	private JLabel useProp1, useProp2, alt, ctrl;
	private ShowScore showscore;
	static BufferedImage bg = null;
	Player playerInfo = new Player();
	Music music = new Music();
	    
	public Game(HomePage home, Icon prop1, Icon prop2)  {
		
		this.setSize(1280, 720);
        this.setVisible(true);
        this.home = home;
        
        //設定背景
        try {
            	bg = ImageIO.read(new File("src/img/bg5.jpg"));
        	} catch (Exception ex) {
         		System.out.println(ex);
        }
      
        showMessage = new JPanel();
        showMessage.setOpaque(false);
        showGame = new JPanel();
        showGame.setOpaque(false);
        mpPanel = new JPanel();
        mpPanel.setOpaque(false);
        gradePanel = new JPanel();
        gradePanel.setOpaque(false);
        
        //設定血量
        blood = new JProgressBar();
        blood.setUI( new ProgressUI(this.blood, Color.pink));
        blood.setValue(0); 
        blood.setMinimum(0);
        blood.setMaximum(1000);
        blood.setStringPainted(true); 
        blood.setPreferredSize(new Dimension(400, 50));
        blood.setOpaque(false);
        
        //設定道具
        propPanel = new JPanel();
        propPanel.setOpaque(false);
        alt = new JLabel(new ImageIcon("src/img/alt.png"));
        ctrl = new JLabel(new ImageIcon("src/img/ctrl.png"));
        
        useProp1 = new JLabel();
        useProp1.setIcon(prop1);
        useProp2 = new JLabel();
        useProp2.setIcon(prop2);
        
        
        propPanel.add(ctrl);
        propPanel.add(useProp1);
        propPanel.add(alt);
        propPanel.add(useProp2);
        
        mpPanel.add(blood);
           
        //分數設定 顯示單字
        point = new JTextArea("Point", 1,2);
        display = new JTextArea("生命:",1, 2);
        point.setEditable(false);
        display.setEditable(false);
        gradePanel.add(point);
        gradePanel.add(display);
        
        mpPanel.add(gradePanel);
        mpPanel.add(propPanel);
        
        add(mpPanel);
        add(showGame);
        
        // 測試取消道具功能 shift離開 
        testArea = new JTextArea(4, 20);
        testArea.addKeyListener(this);
        add(testArea);
        //變更血量部分
        
        new Thread(){
			public void run(){
                    for(int i = 100;i >= 0;i--){
                            try{
                                    Thread.sleep(100);
                            }catch(InterruptedException e){
                                    e.printStackTrace();
                            }
                            blood.setValue(i);
                    }
                    blood.setString("Game Over！");  
            }
			
        }.start();
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
	public void stateChanged(ChangeEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//使用道具
		if (e.getKeyCode() == 157) {
			 useProp1.setIcon(new ImageIcon("src/img/cancel.png"));
            try {
                URL cb;
                File f = new File("src/backMusic/prop.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			 useProp1.setIcon(new ImageIcon("src/img/cancel.png"));
            try {
                URL cb;
                File f = new File("src/backMusic/prop.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
		     useProp2.setIcon(new ImageIcon("src/img/cancel.png"));
            try {
                URL cb;
                File f = new File("src/backMusic/prop.wav");
                cb = f.toURL();
                AudioClip aau;
                aau = Applet.newAudioClip(cb);
                aau.play();
            } catch (MalformedURLException en) {
                en.printStackTrace();
            }
		}

		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			showscore = new ShowScore(home);
			music.stopMusic(); //shift跳畫面，也要停止音樂
	        home.remove(this);
			home.add(showscore);
	        home.revalidate();
	        home.repaint();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}

class ProgressUI extends BasicProgressBarUI {
	
	private JProgressBar jProgressBar;
	private int progressvalue;
	private Color forecolor;
	
	ProgressUI(JProgressBar jProgressBar,Color forecolor) {
	    this.jProgressBar = jProgressBar;
	    this.forecolor=forecolor;
	}
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
	    
	    progressvalue = this.jProgressBar.getValue();
	    
	    //血量小於20 變紅色
	    if(progressvalue < 20){
	    	this.jProgressBar.setForeground(Color.RED);
	    }
	  //血量大於20小於60 變黃色
	    else if(progressvalue < 60){
	    	this.jProgressBar.setForeground(Color.YELLOW);
	    }
	  //血量大於60 綠色
	    else{
	    	this.jProgressBar.setForeground(Color.GREEN);
	    } 
	    super.paintDeterminate(g, c);
		}
	}

