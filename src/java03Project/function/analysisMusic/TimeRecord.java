package java03Project.function.analysisMusic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TimeRecord extends JFrame implements KeyListener {
	private static TimeRecord demo;
	private static JPanel panel;
	private static JTextArea text;
	private static int[] tempo;
	private static int index = 0;

	private static long start;
	private static long end;
	private static JTextArea result;
	
	private static Music music;
	
	public static void main(String[] args) {
		demo = new TimeRecord();
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.setSize(600, 600);
		demo.setVisible(true);
	}

	public TimeRecord() {
		super("display");
		this.getContentPane().setLayout(new GridLayout(1, 1));
		panel = new JPanel();

		tempo = new int[40000];
		tempo[index++] = 0;
		displayTime();

		text = new JTextArea("0");
		text.setEditable(false);
		text.addKeyListener(this);
	
		panel.add(text);
		this.add(panel);
		
		result = new JTextArea("");
		result.setEditable(false);
		this.add(result);
		
		Thread thread1 = new Thread() {
            public void run() {
            	music = new Music();
				start = System.currentTimeMillis(); 
            }
		};
		thread1.start();
	}

	public static void displayTime() {
		try {
			String stringValue = Integer.toString(tempo[index-1]);
			text.setText(stringValue);
		}
		catch(Exception e) {

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			end = System.currentTimeMillis(); 
			tempo[index++] = (int)(end - start);
			displayTime();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			String tmp = "";
			for(int i = 0; i < index; i++) {
				tmp = tmp + tempo[i] + "\n";
			}
			
			result.setText(tmp);
			System.out.println(tmp);
			
			TimeRecordWriter writer = new TimeRecordWriter(music.musicName, music.musicPath, tmp);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}