package java03Project.function;

//編譯:javac MyPanel.java
//執行:appletviewer MyPanel.html
//需要大家自寫一個MyPanel.html
/*
<html>
<body bgcolor="pink">
<applet code="MyPanel.class" width="750" height="380">
</applet>
</body>
</html>
*/
//下面是MyPanel.java的原始碼。www.bianceng.cn
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;

public class MyPanel extends Applet implements Runnable {
	public int FPS;
	public Thread newthread;
	public static boolean swit;
	public Image img;
	Graphics off;
	int width, height;
	MouseListener ml = new A();
	KeyListener kl = new B();
	JPopupMenu jmp;
	JMenuItem jmi;
	letter myletter;
	AudioClip A1, A2, A3;
	Random r;
	int isTypedSum;
	int isOmittedSum;
	int isWrongTypedSum;
	float percent;

	public void init() {
		this.setBackground(Color.pink);
		this.setLayout(new FlowLayout());
		FPS = 100;
		isTypedSum = isOmittedSum = isWrongTypedSum = 0;
		percent = 0f;
		width = this.getSize().width;
		height = this.getHeight();
		img = this.createImage(width, height);
		off = img.getGraphics();
		r = new Random();
		addComponents();
		A1 = this.getAudioClip(this.getDocumentBase(), "Audio/AUDIO1.au");
		A2 = this.getAudioClip(this.getDocumentBase(), "Audio/AUDIO2.au");
		A3 = this.getAudioClip(this.getDocumentBase(), "Audio/AUDIO3.au");
	}

	public void addComponents() {
		this.addKeyListener(kl);
		jmp = new JPopupMenu();
		jmi = new JMenuItem("開始遊戲");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isTypedSum = isOmittedSum = isWrongTypedSum = 0;
				swit = true;
				start();
				A3.loop();
			}
		});
		jmp.add(jmi);
		jmi = new JMenuItem("結束遊戲");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
				swit = false;
				A3.stop();
			}
		});
		jmp.add(jmi);
		jmp.addSeparator();
		jmi = new JMenuItem("增加字母數字");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myletter.exist_letter_num == 9)
					;
				else
					myletter.exist_letter_num++;
				myletter.randomLetters();
			}
		});
		jmp.add(jmi);
		jmi = new JMenuItem("加快下落速度");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < myletter.exist_letter_num; i++)
					myletter.speed[i]++;
			}
		});
		jmp.add(jmi);
		jmp.addSeparator();
		jmi = new JMenuItem("減少字母數字");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myletter.exist_letter_num == 1)
					;
				else
					myletter.exist_letter_num--;
				myletter.randomLetters();
			}
		});
		jmp.add(jmi);
		jmi = new JMenuItem("減緩下落速度");
		jmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < myletter.exist_letter_num; i++) {
					if (myletter.speed[i] > 1)
						myletter.speed[i]--;
				}
			}
		});
		jmp.add(jmi);
		this.addMouseListener(ml);
	}

	class A extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}

		public void showPopup(MouseEvent e) {
			if (e.isPopupTrigger())
				jmp.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	class B extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char key = e.getKeyChar();
			if (isTyped(key)) {
				A1.play();
			} else {
				A2.play();
			}
		}

		public boolean isTyped(char key) {
			for (int i = 0; i < myletter.exist_letter_num; i++) {
				if ((char) (key - 32) == myletter.cc[i].charAt(0)) {
					isTypedSum++;
					myletter.reStart(i);
					return true;
				}
			}
			isWrongTypedSum++;
			return false;
		}
	}

	public void start() {
		newthread = new Thread(this);
		newthread.start();
		myletter = new letter(this, off);
		myletter.randomLetters();
	}

	public void run() {
		while (newthread != null) {
			this.repaint();
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		}
	}

	public void paint(Graphics g) {
		int sum;
		int showPercent = 0;
		if (swit) {
			off.setColor(this.getBackground());
			off.fillRect(0, 0, width, height);
			myletter.paintLetters(g);
			off.setColor(Color.blue);
			sum = isTypedSum + isWrongTypedSum + isOmittedSum;
			if (sum == 0) {
				percent = 0f;
				showPercent = 0;
			} else {
				percent = (float) isTypedSum / sum;
				showPercent = (int) (percent * 100);
			}
			off.drawString(
					"擊中" + isTypedSum + " 錯擊" + isWrongTypedSum + " 漏掉" + isOmittedSum + " 正確率" + showPercent + "%",
					200, 200);
			g.drawImage(img, 0, 0, width, height, this);
		} else {
			off.setColor(this.getBackground());
			off.fillRect(0, 0, width, height);
			off.drawString(
					"擊中" + isTypedSum + " 錯擊" + isWrongTypedSum + " 漏掉" + isOmittedSum + " 正確率" + showPercent + "%",
					200, 200);
			g.drawImage(img, 0, 0, width, height, this);
		}
	}

	public void update(Graphics g) {
		this.paint(g);
	}

	public void stop() {
		newthread = null;
	}
}

class letter {
	MyPanel game;
	final int Max;
	boolean let[];
	Graphics off;
	int X[];
	int Y[];
	int speed[];
	int exist_letter_num;
	int XY[];
	int ini;
	StringBuffer c[];
	String cc[];
	Random ran = new Random();
	Color mycolor[] = { Color.red, Color.green };
	int aa[];

	public letter(MyPanel game, Graphics off) {
		Max = 9; // 將字母最多設定為9個。此數為不可改變的。
		this.off = off;
		this.game = game;
		let = new boolean[Max];
		XY = new int[Max];
		ini = 50;
		initArray();
		exist_letter_num = 3; // 初始化,剛開始落下字母的個數。
	}

	public void initArray() {
		for (int i = 0; i < Max; i++) {
			let[i] = false;
			XY[i] = ini;
			ini += 70;
		}
	}

	public void randomLetters() // 隨機產生n個不同數字的值。
	{
		X = new int[exist_letter_num];
		Y = new int[exist_letter_num];
		speed = new int[exist_letter_num];
		aa = new int[100];
		for (int i = 0, n = 0; i < exist_letter_num; i++)// 通過9個不同的位置來隨機產生字母出現的座標位置。
		{
			aa[n] = ran.nextInt(9);
			if (i != 0) {
				while (check(aa, n)) {
					aa[n] = ran.nextInt(9);
				}
			}
			X[i] = XY[aa[n]];
			Y[i] = ran.nextInt(11) - 10;
			speed[i] = ran.nextInt(8) + 1;
			let[aa[n]] = true; // 儲存下放字母的位置。
			n++;
		}
		randomStrings();
	}

	public void randomStrings() {
		c = new StringBuffer[exist_letter_num];
		cc = new String[exist_letter_num];
		while (true) {
			for (int i = 0; i < exist_letter_num; i++) {
				c[i] = new StringBuffer();
				cc[i] = new String();
				c[i].setLength(1);
				c[i].setCharAt(0, (char) (ran.nextInt(26) + 65));
				cc[i] = "" + c[i];
			}
			if (checkChar(c))
				break;
		}
	}

	public boolean checkChar(StringBuffer c[]) {
		if (exist_letter_num == 1)
			return true;
		for (int i = 0; i < exist_letter_num - 1; i++)
			for (int j = i + 1; j < exist_letter_num; j++) {
				if (c[i].equals(c[j]))
					return false;
			}
		return true;
	}

	public boolean check(int aa[], int n) {
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++) {
				if (aa[i] == aa[j])
					return true;
			}
		return false;
	}

	public void paintLetters(Graphics g) {
		for (int temp = 0; temp < exist_letter_num; temp++) {
			off.setColor(mycolor[ran.nextInt(2)]);
			off.fill3DRect(X[temp], Y[temp], 20, 20, true);
			off.setColor(Color.blue);
			off.drawString(cc[temp], X[temp] + 5, Y[temp] + 15);
			Y[temp] += speed[temp];
			if (Y[temp] > game.height) // 當字母消失後,重新給初始位置和速度。
			{
				game.isOmittedSum++;
				reStart(temp);
			}
		}
	}

	public void reStart(int temp) {
		Y[temp] = ran.nextInt(11) - 10;
		speed[temp] = ran.nextInt(8) + 1;
		reStartX(temp);
		reStartStr(temp);
	}

public void reStartX(int temp)
{
	int cause;
	Label:while(true)
	{
	cause=ran.nextInt(9);
	for(int i=0;(i<exist_letter_num)&(i!=temp);i++)
	{
		if(cause==aa[i])
		continue Label;
	}
		break;
	}
	X[temp]=XY[cause];
	aa[temp]=cause;
}

public void reStartStr(int temp)
{
StringBuffer sb;
String s;
Label2:while(true)
{
sb=new StringBuffer();
sb.setLength(1);
s="";
sb.setCharAt(0,(char)(ran.nextInt(26)+65));
s+=sb;
for(int i=0;i<exist_letter_num&i!=temp;i++)
{
if(s.equals(cc[i]))
continue Label2;
}
break;
}
cc[temp]=s;
}
}