package java03Project.mainframe;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java03Project.function.*;
import java03Project.function.SelectModel;
import java03Project.function.analysisMusic.Music;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java03Project.function.Player;
public class HomePage extends JFrame implements ActionListener, MouseListener {
	
    private JButton startButton;
    private JButton resetButton;
    private JPanel contentPanel, imagePanel;
    private JLabel bgLabel = null;
    private ImageIcon background = null;
    private SelectModel selection;
    static MediaPlayer _mediaPlayer;
    private static Player newPlayer = new Player(); 
    public JLabel title;
    
    
    public JPanel getContent() {
    	return this.contentPanel;
    }
    
    public JPanel getSelectPanel() {
    	return selection;
    }
    
    public HomePage() {  
    	
    	//背景音樂播放
    	File f = new File("src/backMusic/backmusic.wav");
		//System.out.println(f.toURI().toString());
		Media _media = new Media(f.toURI().toString());
		final JFXPanel fxPanel = new JFXPanel();
		_mediaPlayer = new MediaPlayer(_media);
		_mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		_mediaPlayer.play();
  
    	contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setOpaque(false);
        this.setLayout(new BorderLayout());
        
        title = new JLabel("<html><font color='Orange'>指</font><font color='White'>是有點厲害</font></html>");
        title.setFont(new Font("微軟正黑體", Font.PLAIN, 80));
        title.setBounds(400, 100, 500, 100);
        
        startButton = new JButton("START", new ImageIcon("src/img/start.png"));
        startButton.addActionListener(this);
        startButton.addMouseListener(this);
        startButton.setBounds(550, 300, 200, 64);
        startButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        startButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,24) ) ; 
        startButton.setForeground(new Color(191,239,255));
        startButton.setOpaque(false);
        startButton.setBackground(new Color(191,239,255));
        
        
        resetButton = new JButton("RESET", new ImageIcon("src/img/reset.png"));
        resetButton.addActionListener(this);
        resetButton.addMouseListener(this);
        resetButton.setBounds(550, 500, 200, 64);
        resetButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        resetButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN ,24) ) ; 
        resetButton.setForeground(new Color(191,239,255));
        resetButton.setOpaque(false);
        resetButton.setBackground(new Color(191,239,255));
        
        add(title);
        contentPanel.add(startButton);
		contentPanel.add(resetButton);
		
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		// 設定背景
		background = new ImageIcon("src/img/desk.jpg");// 背景圖片
		bgLabel = new JLabel(background);      // 把背景圖顯示在Label中
	    bgLabel.setBounds(0, 0, 1280, 720);// 把含有背景圖之Label位置設置為圖片剛好填充整個版面
	    // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
	    imagePanel = (JPanel) this.getContentPane();
	    imagePanel.setOpaque(false);
	    this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));     // 把背景圖添加到分層窗格的最底層以作為背景
	 
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            selection = new SelectModel(this);
            this.remove(contentPanel);
            this.remove(title);
            this.add(selection);
            this.revalidate();
            this.repaint();
        }
        
        if (event.getSource() == resetButton) {
        	
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == startButton) {
			startButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
    	if (e.getSource() == resetButton) {
    		resetButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == startButton) {
			startButton.setBorder(BorderFactory.createRaisedBevelBorder());
    	}
    	if (e.getSource() == resetButton) {
    		resetButton.setBorder(BorderFactory.createRaisedBevelBorder());
    	}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == startButton) {
			startButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
    	if (e.getSource() == resetButton) {
    		resetButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
	}

	public void stopMusic() {
		// TODO Auto-generated method stub
		_mediaPlayer.stop();
		
	}	
	public Player getPlayer() {
		return newPlayer;
	}
	
	public void setPlayerMoney(int money) {
		newPlayer.setMoney(money);
	}
	
	public void setPlayerProp(Prop[] props) {
		newPlayer.setProps(props);
	}
}
