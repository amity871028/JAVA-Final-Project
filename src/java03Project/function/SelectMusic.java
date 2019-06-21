package java03Project.function;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JDialog;


public class SelectMusic {
	
	public String[] getSelectMusic()
	{
		return getFileOrDirectoryPath();
	}
	
	//選擇檔案
	private String[] getFileOrDirectoryPath() {
		// configure dialog allowing selection of a file or directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 File", "mp3")); //限定只能選mp3檔
		int result = fileChooser.showOpenDialog(null);
	
		// if user clicked Cancel button on dialog, return
		/*
		if (result == JFileChooser.CANCEL_OPTION)
			System.exit(0);*/
	
		String[] str = new String[2];
		str[0] = fileChooser.getSelectedFile().getName(); //檔案名
		str[1] = fileChooser.getSelectedFile().getAbsolutePath(); //檔案路徑
		return str;
	}

}