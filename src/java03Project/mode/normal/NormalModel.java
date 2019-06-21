package java03Project.mode.normal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import java03Project.function.SelectMusic;
import java03Project.function.analysisMusic.Music;
import java03Project.mainframe.HomePage;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.*;

public class NormalModel extends JPanel implements ActionListener , MouseListener{

    private JButton increaseButton;
    private JButton randomButton;
    private JButton playButton;
    private JButton backButton;
    private HomePage home;
    private JPanel songListPanel;
    private MenuList menuListPanel;
    private JLabel bgLabel = null;
    private ImageIcon background = null;
    private int index;
    SelectMusic getMusicPath = new SelectMusic();
    MusicListViewer musicList = new MusicListViewer();
    MenuList close = new MenuList();
    static MediaPlayer _mediaPlayer;
    
    public NormalModel(HomePage home ) {
    	
    	File f = new File("src/backMusic/backmusic.wav");
		//System.out.println(f.toURI().toString());
		Media _media = new Media(f.toURI().toString());
		final JFXPanel fxPanel = new JFXPanel();
		_mediaPlayer = new MediaPlayer(_media);
		_mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		_mediaPlayer.play();
    	
    	this.setSize(1280, 720);
        this.setVisible(true);
        this.home = home;
        this.setLayout(null);
        
        songListPanel = new JPanel();
        songListPanel.setBounds(250, 30, 800, 450);
        songListPanel.setOpaque(false);
        songListPanel.add(musicList);
                
        playButton = new JButton("開始遊戲", new ImageIcon("src/img/jet.png"));
        playButton.addActionListener(this);
        playButton.setBounds(100, 500, 300, 64);
        playButton.addMouseListener(this);
        playButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        playButton.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        playButton.setForeground(new Color(154, 205, 50));
        playButton.setOpaque(false);
        playButton.setBackground(new Color(193, 255, 193));
        
        increaseButton = new JButton("自行新增", new ImageIcon("src/img/add.png"));
        increaseButton.addActionListener(this);
        increaseButton.setBounds(500, 500, 300, 64);
        increaseButton.addMouseListener(this);
        increaseButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        increaseButton.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        increaseButton.setForeground(new Color(154, 205, 50));
        increaseButton.setOpaque(false);
        increaseButton.setBackground(new Color(193, 255, 193));
        
        randomButton = new JButton("隨機選取", new ImageIcon("src/img/random.png"));
        randomButton.addActionListener(this);
        randomButton.setBounds(900, 500, 300, 64);
        randomButton.addMouseListener(this);
        randomButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        randomButton.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        randomButton.setForeground(new Color(154, 205, 50));
        randomButton.setOpaque(false);
        randomButton.setBackground(new Color(193, 255, 193));

        backButton = new JButton(new ImageIcon("src/img/backbutton.png"));
        backButton.addActionListener(this);
        backButton.setBounds(50, 600, 64, 64);
        backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        backButton.addMouseListener(this);
        backButton.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ; 
        backButton.setOpaque(false);
        backButton.setBackground(new Color(193, 255, 193));
        
        this.add(songListPanel);
        this.add(playButton);
        this.add(increaseButton);
        this.add(randomButton);
        this.add(backButton);
        
        //設定背景
        background = new ImageIcon("src/img/bg3.jpg");// 背景圖片
		bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
	    bgLabel.setBounds(0, 0, 1280, 720);// 把含有背景圖之Label位置設置為圖片剛好填充整個版面
	    this.add(bgLabel, new Integer(Integer.MIN_VALUE));     // 把背景圖添加到分層窗格的最底層以作為背景
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
    	if (event.getSource() == backButton) {
        	home.remove(this);
        	home.add(home.getSelectPanel());
            home.revalidate();
            home.repaint();
        }
    	
    	if(event.getSource() == playButton){
    		stopMusic();
    		//System.out.println(musicList.getJList().getSelectedIndex());
    		if(musicList.getJList().getSelectedIndex() < 0)
    		{
    			musicList.getFileContext(0);
    			setSelectedIndex(0);
    		}
    		else
    		{
    			musicList.getFileContext(musicList.getJList().getSelectedIndex());
        		setSelectedIndex(musicList.getJList().getSelectedIndex());
    		}
    		try {
				menuListPanel = new MenuList(this.home, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		home.remove(this);
    		home.add(menuListPanel);
            home.revalidate();
            home.repaint();
    	}
    	
    	if (event.getSource() == increaseButton) {
    		String[] musicSource = new String[2];
    		musicSource = getMusicPath.getSelectMusic();
    		////System.out.println("NormalModel:" + musicSource[1]);
    		musicList. addName(musicSource[0], musicSource[1]);
    	}
    	
    	if (event.getSource() == randomButton) {
    		Random ran = new Random();
    		////System.out.println(musicList.getJList().getModel().getSize());
    		int number = ran.nextInt(musicList.getJList().getModel().getSize());
    		musicList.getFileContext(number);
    		setSelectedIndex(number);
    		try {
				menuListPanel = new MenuList(this.home, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		home.remove(this);
    		home.add(menuListPanel);
            home.revalidate();
            home.repaint();
    	}
    	
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動產生的方法 Stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    		new Music("src/backMusic/press.wav");
    	}
		
		if(e.getSource() == playButton)
		{
			playButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
			new Music("src/backMusic/click.wav");
		}
		
		if (e.getSource() == increaseButton) {
			increaseButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
			new Music("src/backMusic/click.wav");
    	}
		
		if (e.getSource() == randomButton) {
			randomButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
			new Music("src/backMusic/click.wav");
    	}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動產生的方法 Stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
		if (e.getSource() == playButton) {
			playButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
		if (e.getSource() == increaseButton) {
			increaseButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
		if (e.getSource() == randomButton) {
			randomButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == playButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == increaseButton) {
			increaseButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == randomButton) {
			randomButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
	}
	private void setSelectedIndex(int index)
	{
		this.index = index;
	}
	public int getSelectedIndex()
	{
		return index;
	}
	public void stopMusic() {
		// TODO Auto-generated method stub
		_mediaPlayer.stop();
		
	}	
}
