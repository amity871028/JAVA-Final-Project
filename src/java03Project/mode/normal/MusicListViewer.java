package java03Project.mode.normal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class MusicListViewer extends JPanel {

	private JList<String> MusicNameList;
	private JScrollPane tableWithScrollBar;


	private MusicInfoReader reader;
	private MusicInfoWriter writer;
	
	private String path;

	// Constructor
	public MusicListViewer() {
		// create reader and writer
		// TODO
		reader = new MusicInfoReader("src/MusicPackage/MusicNames/SongName.txt", "src/MusicPackage/MusicPaths/SongPaths.txt");
		writer = new MusicInfoWriter("src/MusicPackage/MusicNames/SongName.txt", "src/MusicPackage/MusicPaths/SongPaths.txt");
		setBackground(null);
		setOpaque(false);

		fillData();
	}

	public void addName(String SongName, String SongPath)
	{
		////System.out.println("In MusicListViwer addName:" + SongPath);
		addMusic(SongName, SongPath);
	}
	
	private void addMusic(String name, String path) {
		String SongName;
		////System.out.println("In MusicListViwer addMusic:" + path);
		//int spaceIndex = name.indexOf(" ");
		int nameLength = name.length() - 4;
		if (nameLength != -1) {
			name = name.replace(" ", "_");
			SongName = name.substring(0, nameLength);
		}
		else {
			SongName = name;
		}

		// write a record to the text file
		// TODO
		writer.addMusic(SongName, path);
		updateTable();
	}
	

	private void updateTable() {
		remove(tableWithScrollBar);
		fillData();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private void fillData() {
		
		// read all records from the text file
		// TODO
		String[] data =  reader.readAllMusics();

		MusicNameList = new JList<String>(data);
		tableWithScrollBar = new JScrollPane(MusicNameList);
		add(tableWithScrollBar, BorderLayout.CENTER);
	}
	public JList getJList()
	{
		return MusicNameList;
	}
	
	private void getFileLineNumber(int lineNumber) throws IOException {
		LineNumberReader lnr = new LineNumberReader(new FileReader("src/MusicPackage/MusicPaths/SongPaths.txt"));
		////System.out.println("in MusicListViewer getFileLineNumber:" + lineNumber);
		String path = lnr.readLine();
		lineNumber += 2;
		int num = 0;
        while (path != null) {
            if (lineNumber == ++num) {
               // //System.out.println("line   " + lineNumber + ":   " + path);
                break;
            }
            path = lnr.readLine();
        }
        this.path = path;
	}
	public String getFileContext(int lineNumber)
	{
		////System.out.println("getFileContext:" + lineNumber);
		//String temp ="";
		
		try
		{
			getFileLineNumber(lineNumber);
			//temp = path;
		}
		catch(IOException e)
		{
			e.getMessage();
		}
		////System.out.println("Here:" + path);
		return path;
	}
}
