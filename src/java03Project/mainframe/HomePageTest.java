package java03Project.mainframe;

import javax.swing.JFrame;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class HomePageTest {

	public static void main(String[] args) {
		HomePage home = new HomePage();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setSize(1280, 720);
        home.setVisible(true);
        home.setResizable(false); 
	}
}
