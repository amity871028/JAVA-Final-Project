package java03Project.mode.normal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java03Project.function.analysisMusic.Music;
import java03Project.mainframe.HomePage;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuList extends JPanel implements ActionListener, ChangeListener, ItemListener  , MouseListener{
	
	private HomePage home;
	private JLabel songName, difficulty, rate, prop;
	private JTextArea name;
	private JTextField moneyCost;
	private JPanel choosePanel1, choosePanel2, namePanel, propPanel, propStore, checkPanel;
	private JRadioButton easy, normal, hard, master;
	private ButtonGroup G1, G2;
	private JButton backButton, propButton1, propButton2, checkButton, startGameButton;
	private NormalModel normalModelPanel;
	private JSlider speed; 
	private JRadioButton speedUp, doubleMoney, doubleGrade, regain, noise, invincible, caption;
	private JLabel propName, propImg, money, propLine1, propexp;
	private JPanel propImgPanel, moneyPanel, prop1Panel;
	public int totalEnter = 0;
	public String chooseProp = "src/img/mark.png";
	private Game game;
    static BufferedImage bg = null;
    static BufferedImage bg2 = null;
    private String musicName;
    private MusicListViewer musicList = new MusicListViewer();
    static MediaPlayer _mediaPlayer;
    
    public MenuList()
    {
    	
    }
	public MenuList(HomePage home,  NormalModel normalModelPanel) throws IOException {
		
		this.setSize(1280, 720);
        this.setVisible(true);
        this.home = home; 
       
        this.setLayout(new GridLayout(8, 1));
        this.normalModelPanel = normalModelPanel;
        
        //設定背景
        try {
            	bg = ImageIO.read(new File("src/img/bg.jpg"));
        	} catch (Exception ex) {
         		System.out.println(ex);
        }
        try {
        	bg2 = ImageIO.read(new File("src/img/bg4.png"));
    	} catch (Exception ex) {
     		System.out.println(ex);
    	}
   

        //顯示歌曲名稱
        namePanel = new JPanel();
        namePanel.setOpaque(false);
        songName = new JLabel("歌曲名稱 : ");
        songName.setForeground(new Color(255, 250, 205));
        songName.setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        songName .setOpaque(false);
        
        name = new JTextArea(100, 50);
        name.setEditable(false); //不允許寫入
        name.setOpaque(false);
        name.setForeground(new Color(209, 238, 238));
        name.setFont(new Font("微軟正黑體", Font.PLAIN, 20));       
        //name.setText("test~~ 這裡是Song Name TextArea!");
        //System.out.println(normalModelPanel.getSelectedIndex());
        //System.out.println(getFileLineNumber(normalModelPanel.getSelectedIndex()));
        name.setText(getFileContext(normalModelPanel.getSelectedIndex())); //出現音樂名稱
        namePanel.add(name);
        
        //選擇難易度
        difficulty = new JLabel("選擇難度 : 簡單"); 
        difficulty.setForeground(new Color(255, 245, 238));
        difficulty.setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        difficulty.setOpaque(false);
        choosePanel1 = new JPanel();
        choosePanel1.setOpaque(false);
        choosePanel2 = new JPanel();
        choosePanel2.setOpaque(false);
        
        //簡單 普通 困難 設定
        easy = new JRadioButton("簡單", new ImageIcon("src/img/easy.png"), true);//加入圖片
        easy.setPreferredSize(new Dimension(150, 96));	//設定距離
        easy.addItemListener(this);	//事件處理
        easy.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        easy.setForeground(new Color(255, 245, 238));
        easy.setOpaque(false);
        
        normal = new JRadioButton("普通", new ImageIcon("src/img/normal.png"),false); 
        normal.setPreferredSize(new Dimension(150, 96));
        normal.addItemListener(this);
        normal .setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        normal.setForeground(new Color(255, 245, 238));
        normal.setOpaque(false);
        
        hard = new JRadioButton("困難", new ImageIcon("src/img/hard.png"),false); 
        hard.setPreferredSize(new Dimension(150, 96));
        hard.addItemListener(this);
        hard.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        hard.setForeground(new Color(255, 245, 238));
        hard.setOpaque(false);
        
        master = new JRadioButton("跟JAVA一樣難", new ImageIcon("src/img/master.png"),false);
        master.setPreferredSize(new Dimension(300, 96));
        master.addItemListener(this);
        master.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        master.setForeground(new Color(255, 245, 238));
        master.setOpaque(false);
        
        G1 = new ButtonGroup();
        G1.add(easy);
        G1.add(normal);
        G1.add(hard);
        G1.add(master);
    
        choosePanel1.add(easy);
        choosePanel1.add(normal);
        choosePanel1.add(hard);
        choosePanel1.add(master);
             
        rate = new JLabel("音樂速度 : 100" );
        rate .setForeground(new Color(240, 255, 240));
        rate .setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        rate.setOpaque(false);
        speed = new JSlider(0, 200, 100); 
        speed.setPaintTrack(true); 
        speed.setPaintTicks(true); 
        speed.setPaintLabels(true); 
        speed.setMajorTickSpacing(50); 
        speed.addChangeListener(this); 
        speed.setPreferredSize(new Dimension(600, 40));
        speed.setOpaque(false);
        choosePanel2.add(speed);
        
        //道具設定
        prop = new JLabel("遊戲道具 : ");
        prop.setOpaque(false);
        prop.setForeground(new Color(230, 230, 250));
        prop.setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        
        propPanel = new JPanel();
        propPanel.setOpaque(false);
        
        propButton1 = new JButton(new ImageIcon("src/img/mark.png"));
        propButton1.setPreferredSize(new Dimension(150, 96));
        propButton1.addActionListener(this);
        propButton1.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        propButton1.addMouseListener(this);
        propButton1.setOpaque(false);
        propButton1.setBackground(new Color(193, 255, 193));
        
        propButton2 = new JButton(new ImageIcon("src/img/mark.png"));
        propButton2.setPreferredSize(new Dimension(150, 96));
        propButton2.addActionListener(this);
        propButton2.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        propButton2.addMouseListener(this);
        propButton2.setOpaque(false);
        propButton2.setBackground(new Color(193, 255, 193));
        
        backButton = new JButton(new ImageIcon("src/img/backbutton.png"));
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(150, 64));
        backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        backButton.addMouseListener(this);
        backButton.setOpaque(false);
        backButton.setBackground(new Color(193, 255, 193));
        
        startGameButton = new JButton("START", new ImageIcon("src/img/start2.png"));
        startGameButton.addActionListener(this);
        startGameButton.setPreferredSize(new Dimension(150, 64));
        startGameButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
        startGameButton.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        startGameButton.addMouseListener(this);
        startGameButton.setForeground(new Color(255, 250, 250));
        startGameButton.setOpaque(false);
        startGameButton.setBackground(new Color(193, 255, 193));
        
        propPanel.add(backButton);
        propPanel.add(propButton1);
        propPanel.add(propButton2);
        propPanel.add(startGameButton);
        
        add(songName);
        add(namePanel);
        add(difficulty);
        add(choosePanel1);
        add(rate);
        add(choosePanel2);
        add(prop);
        add(propPanel);
        
        //設置道具商店
        propStore = new JPanel(new GridLayout(7, 1)){
            @Override
            protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.drawImage(bg2, 0, 0, getWidth(), getHeight(), this);
            }
            @Override
            public Dimension getPreferredSize() {
              return new Dimension(400, 300);
            }
          };
        propStore.setSize(1280, 720);
       
        
        //顯示選擇道具以及說明
        propImgPanel = new JPanel();
        propImgPanel.setOpaque(false);
        propName = new JLabel("我選擇的道具 : ");
        propName.setOpaque(false);
        propName.setForeground(new Color(135, 206, 255));
        propName.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        propImg = new JLabel();
        propImg.setOpaque(false);
        propImgPanel.add(propImg);
        propexp = new JLabel();
        propexp.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        propexp.setOpaque(false);
        propexp.setForeground(new Color(240, 255, 240));
        propImgPanel.add(propexp);
        
        
        //顯示金錢
        moneyPanel = new JPanel();
        moneyPanel.setOpaque(false);
        money = new JLabel("我的錢錢 : " + 800);//顯示player目前的錢
        money.setForeground(new Color(135, 206, 255));
        money.setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        moneyCost = new JTextField(50);
        moneyCost.setEditable(false); //不允許寫入
        moneyCost.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
        moneyCost.setBackground(Color.BLACK);
        moneyCost.setForeground(Color.WHITE);
        moneyPanel.add(moneyCost);
        System.out.println(home.getPlayer().getMoney());//!
        //設置選擇道具列
        prop1Panel = new JPanel();
        prop1Panel.setOpaque(false);
        propLine1 = new JLabel("道具 : ");
        propLine1.setForeground(new Color(135, 206, 255));
        propLine1.setFont(new Font("微軟正黑體", Font.PLAIN, 35));
        propLine1.setOpaque(false);
        
        /*
        speedUp = new JRadioButton("速度加倍", new ImageIcon("src/img/speedup.png"), false);  
        speedUp.setPreferredSize(new Dimension(200, 96));
        speedUp.addItemListener(this);
        speedUp.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        speedUp.setForeground(new Color(175, 238, 238));
         */
        
        caption = new JRadioButton("隊長防禦", new ImageIcon("src/img/caption.png"), false); 
        caption.setPreferredSize(new Dimension(200, 96));
        caption.addItemListener(this);
        caption.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        caption.setForeground(new Color(175, 238, 238));
        caption.setOpaque(false);
        
        doubleMoney = new JRadioButton("金錢加倍", new ImageIcon("src/img/money.png"), false); 
        doubleMoney.setPreferredSize(new Dimension(200, 96));
        doubleMoney.addItemListener(this);
        doubleMoney.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        doubleMoney.setForeground(new Color(175, 238, 238));
        doubleMoney.setOpaque(false);
        
        doubleGrade = new JRadioButton("分數加倍", new ImageIcon("src/img/addition.png"), false); 
        doubleGrade.setPreferredSize(new Dimension(200, 96));
        doubleGrade.addItemListener(this);
        doubleGrade.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        doubleGrade.setForeground(new Color(175, 238, 238));
        doubleGrade.setOpaque(false);
        
        regain = new JRadioButton("HP補給", new ImageIcon("src/img/return.png"), false); 
        regain.setPreferredSize(new Dimension(200, 96));
        regain.addItemListener(this);
        regain.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        regain.setForeground(new Color(175, 238, 238));
        regain.setOpaque(false);
        
        /*
        noise = new JRadioButton("噪音干擾", new ImageIcon("src/img/noise.png"), false); 
        noise.setPreferredSize(new Dimension(200, 96));
        noise.addItemListener(this);
        noise.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        noise.setForeground(new Color(175, 238, 238));
        */
        
        invincible = new JRadioButton("無敵模式", new ImageIcon("src/img/ironman.png"), false); 
        invincible.setPreferredSize(new Dimension(200, 96));
        invincible.addItemListener(this);
        invincible.setFont(new Font("微軟正黑體", Font.PLAIN ,20) ) ;
        invincible.setForeground(new Color(175, 238, 238));
        invincible.setOpaque(false);
        
        //prop1Panel.add(speedUp);
        prop1Panel.add(caption);
        prop1Panel.add(doubleMoney);
        prop1Panel.add(doubleGrade);
        prop1Panel.add(regain);
        //prop1Panel.add(noise);
        prop1Panel.add(invincible);
        
        G2 = new ButtonGroup();
        //G2.add(speedUp);
        G2.add(caption);
        G2.add(doubleMoney);
        G2.add(doubleGrade);
        G2.add(regain);
        //G2.add(noise);
        G2.add(invincible);
        
        propStore.add(propName);
        propStore.add(propImgPanel);
        propStore.add(money);
        propStore.add(moneyPanel);
        propStore.add(propLine1);
        propStore.add(prop1Panel);
        
        checkPanel = new JPanel();
        checkPanel.setOpaque(false);
        checkButton = new JButton("確認");
        checkButton.setFont(new Font("微軟正黑體", Font.PLAIN ,30) ) ;
        checkButton.setBackground(new Color(193, 255, 193));
        checkButton.setForeground(new Color(154, 205, 50));
        checkButton.addActionListener(this);
        checkButton.setPreferredSize(new Dimension(200, 64));
        checkButton.setOpaque(false);
        checkPanel.add(checkButton);
        propStore.add(checkPanel);
       
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
        	home.remove(this);
        	home.add(normalModelPanel);
            home.revalidate();
            home.repaint();
        }
		
		if (event.getSource() == propButton1) {
			totalEnter = 1;
			home.remove(this);
        	home.add(propStore);
            home.revalidate();
            home.repaint();
		}
		
		if (event.getSource() == propButton2) {
			totalEnter = 2;
			home.remove(this);
        	home.add(propStore);
            home.revalidate();
            home.repaint();
		}
		if (event.getSource() == checkButton) {
			
			if(totalEnter == 1) {
				propButton1.setIcon(new ImageIcon(chooseProp));
				totalEnter = 0;
				chooseProp = "src/img/mark.png";
			}
			if(totalEnter == 2) {
				propButton2.setIcon(new ImageIcon(chooseProp));
				totalEnter = 0;
				chooseProp = "src/img/mark.png";
			}
			
			home.remove(propStore);
        	home.add(this);
            home.revalidate();
            home.repaint();
		}
		if (event.getSource() == startGameButton) {
			game = new Game(this.home, propButton1.getIcon(), propButton2.getIcon());
			//System.out.println("Here:" + musicList.getSongPath());
			//System.out.println(normalModelPanel.getSelectedIndex());
			new Music(musicList.getFileContext(normalModelPanel.getSelectedIndex())); //播放選取的音樂
			home.remove(this);
			home.add(game);
	        home.revalidate();
	        home.repaint();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		rate.setText("音樂速度 : " + speed.getValue());
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == easy) {
			difficulty.setText("選擇難度 : 簡單");
		}
		
		if (e.getSource() == normal) {
			difficulty.setText("選擇難度 : 普通");
		}
		
		if (e.getSource() == hard) {
			difficulty.setText("選擇難度 : 困難");
		}
		
		if (e.getSource() == master) {
			difficulty.setText("選擇難度 : 不死不罷休");
		}
		/*
		if (e.getSource() == speedUp) {
			propImg.setIcon(new ImageIcon("src/img/speedup.png"));
			propexp.setText("<html>加速狂飆，妳說不要！<br/>說明：陷害對手使音樂加速進行。</html>");
			int sum = totalMoney - 100;
			moneyCost.setText("此道具費用 : 100 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/speedup.png";
		}
		*/
		
		if (e.getSource() == caption) {
			propImg.setIcon(new ImageIcon("src/img/caption.png"));
			propexp.setText("<html>米國隊長！無敵防禦<br/>說明：什麼攻擊都能擋下，唯獨擋不住對尚彬的愛❤️。</html>");
			int sum = home.getPlayer().getMoney() - 400;
			home.getPlayer().setMoney(sum);
			money.setText("我的錢錢:"+String.valueOf(home.getPlayer().getMoney()));
			moneyCost.setText("此道具費用 : 400 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/caption.png";
		}
		
		if (e.getSource() == doubleMoney) {
			propImg.setIcon(new ImageIcon("src/img/money.png"));
			propexp.setText("<html>老子有錢，妳說我要！<br/>說明：嘿嘿～讓妳荷包賺滿滿，就怕妳說不要。</html>");
			int sum = home.getPlayer().getMoney() - 400;
			home.getPlayer().setMoney(sum);
			money.setText("我的錢錢:"+String.valueOf(home.getPlayer().getMoney()));
			moneyCost.setText("此道具費用 : 400 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/money.png";
		}
		if (e.getSource() == doubleGrade) {
			propImg.setIcon(new ImageIcon("src/img/addition.png"));
			propexp.setText("<html>分數加倍，不會死當！<br/>說明：海大沒二一這招沒有用，轉學台大用到手軟～。</html>");
			int sum = home.getPlayer().getMoney() - 400;
			home.getPlayer().setMoney(sum);
			money.setText("我的錢錢:"+String.valueOf(home.getPlayer().getMoney()));
			moneyCost.setText("此道具費用 : 400 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/addition.png";
		}
		if (e.getSource() == regain) {
			propImg.setIcon(new ImageIcon("src/img/return.png"));
			propexp.setText("<html>快給我生命，把血加到爆！<br/>說明：我把光劍給了許為元教授，讓我遭受了重大攻擊，給我補血！</html>");
			int sum = home.getPlayer().getMoney() - 150;
			home.getPlayer().setMoney(sum);
			money.setText("我的錢錢:"+String.valueOf(home.getPlayer().getMoney()));
			moneyCost.setText("此道具費用 : 150 元，扣除費用後，剩餘金額為 : " + sum + "。"); 
			chooseProp = "src/img/return.png";
		}
		/*
		if (e.getSource() == noise) {
			propImg.setIcon(new ImageIcon("src/img/noise.png"));
			propexp.setText("<html>使用資工未來系館施工噪音攻擊！<br/>說明：等了十年的系館，噪音威力強大。</html>");
			int sum = totalMoney - 100;
			moneyCost.setText("此道具費用 : 100 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/noise.png";
		}
		*/
		if (e.getSource() == invincible) {
			propImg.setIcon(new ImageIcon("src/img/ironman.png"));
			propexp.setText("<html>無雙模式，讓Iron Man 帶你一飛沖天！<br/>說明：進入無敵狀態，誰也殺不死。<br/>可是復仇者聯盟4我死了...</html>");
			int sum = home.getPlayer().getMoney() - 400;
			home.getPlayer().setMoney(sum);
			money.setText("我的錢錢:"+String.valueOf(home.getPlayer().getMoney()));
			moneyCost.setText("此道具費用 : 400 元，扣除費用後，剩餘金額為 : " + sum + "。");
			chooseProp = "src/img/ironman.png";
		}    
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
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    		new Music("src/backMusic/press.wav");
        }
		
		if (e.getSource() == propButton1) {
			propButton1.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == propButton2) {
			propButton2.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		if (e.getSource() == startGameButton) {
			startGameButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
			new Music("src/backMusic/start.wav");
        }
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		if (e.getSource() == propButton1) {
			propButton1.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
		if (e.getSource() == propButton2) {
			propButton2.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		if (e.getSource() == startGameButton) {
			startGameButton.setBorder(BorderFactory.createRaisedBevelBorder());
			new Music("src/backMusic/enter.wav");
    	}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == backButton) {
    		backButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == propButton1) {
			propButton1.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
		if (e.getSource() == propButton2) {
			propButton2.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		if (e.getSource() == startGameButton) {
			startGameButton.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
    	}
		
	}
	
	//從SongName裡面找到選取的音樂名稱，SongName為存音樂名稱的txt
	private void setFileLineNumber(int lineNumber) throws IOException {
		LineNumberReader lnr = new LineNumberReader(new FileReader("src/MusicPackage/MusicNames/SongName.txt"));
		//System.out.println("getFileLineNumber:" + lineNumber);
		String musicName = lnr.readLine(); //讀行的內容
		lineNumber += 2; //修正選取的index和行數差
		int num = 0;
		//此迴圈還不太懂，但大概就是可以獲得選取的行數音樂名稱
		while (musicName != null) {
			if (lineNumber == ++num) {
				//System.out.println("line   " + lineNumber + ":   " + musicName);
				break;
			}
			musicName = lnr.readLine();
		}
		//System.out.println("In MenuList getFileLineNumber: " + musicName);
		//設定音樂名稱
		this.musicName = musicName;
	}
	//得到音樂名稱
	//不要問我為什麼這樣寫set和get，我快要茫了，能跑就好行行好
	public String getFileContext(int lineNumber)
	{
		//System.out.println("in MenuList getFileContext:" + lineNumber);
		//String temp ="";
		try
		{
			//傳行數進去找音樂名稱
			setFileLineNumber(lineNumber);
			//temp = path;
		}
		catch(IOException e)
		{
			e.getMessage();
		}
		//System.out.println("Here:" + path);
		return musicName;
	}
}

