package java03Project.function.analysisMusic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JDialog;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import java.net.*;

import java.util.Timer;
import java.util.TimerTask;

import javazoom.jl.player.Player;


import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Music extends Application {
	// 設定在這邊避免被java回收 導致音樂撥放5秒就結束
	// https://stackoverflow.com/questions/6241687/mediaplayer-stop-playing-after-about-5-seconds
	static MediaPlayer _mediaPlayer;
	static String musicPath;
	static String musicName;
	
	public Music(){
		
	}
	public Music(String Path){
		//System.out.println(Path);
		//Path = Path.replace("_", " ");
		sound(Path);
	}
	
	//播放音樂的主程式
	public void sound(String Path) {
		//System.out.println(Path);
		File f = new File(Path);
		//System.out.println(f.toURI().toString());
		Media _media = new Media(f.toURI().toString());
		final JFXPanel fxPanel = new JFXPanel();
		_mediaPlayer = new MediaPlayer(_media);
		_mediaPlayer.play();
	}

	// JFXPanel 需初始化 ToolKit
	// https://stackoverflow.com/questions/37553433/java-audio-is-not-loading-touri-not-working
	//呼叫撥放音樂

	// get music duration
	public static int getDuration(String position) {

		int length = 0;
		try {
			MP3File mp3File = (MP3File) AudioFileIO.read(new File(position)); // 印出一堆咚咚
			MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
			// 單位為秒
			length = audioHeader.getTrackLength();

			// return length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//停止音樂
	public void stopMusic()
	{
		_mediaPlayer.stop();
	}
	//無線撥放音樂
	public void loopMusic(String Song)
	{
		File f = new File(Song);
		//System.out.println(f.toURI().toString());
		Media _media = new Media(f.toURI().toString());
		final JFXPanel fxPanel = new JFXPanel();
		_mediaPlayer = new MediaPlayer(_media);
		_mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		_mediaPlayer.play();
	}
}
