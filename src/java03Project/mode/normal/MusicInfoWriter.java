package java03Project.mode.normal;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class MusicInfoWriter {
	private static Formatter output; // outputs text to a file
	private static Formatter output1;
	private String fileName; // target file name
	private String fileName1;

	public MusicInfoWriter(String fileName, String fileName1) {
		this.fileName = fileName;
		this.fileName1 = fileName1;
	}

	public void addMusic(String SongName, String SongPath) {
		openFile();
		//System.out.println("In MusicInfoWriter addMusic:" + SongPath);
		addMusicName(SongName, SongPath);
		closeFile();
	}

	public void openFile() {
		try {
			FileWriter fw = new FileWriter(fileName, true);
			FileWriter fw1 = new FileWriter(fileName1, true);
			output = new Formatter(fw);
			output1 = new Formatter(fw1);
			// output = new Formatter("clients.txt"); // open the file
		} catch (SecurityException securityException) {
			System.err.println("Write permission denied. Terminating.");
			System.exit(1); // terminate the program
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1); // terminate the program
		} catch (IOException e) {
			System.err.println("I/O error. Terminating.");
			System.exit(1); // terminate the program
		}
	}

	// add records to file
	public void addMusicName(String SongName, String SongPath) {
		try {
			// output new record to file; assumes valid input
			// TODO
			//System.out.println("In MusicInfoWriter addMusic:" + SongPath);
			MusicName music;
			music = new MusicName(SongName, SongPath);
			output.format("%s\n", SongName);
			output1.format("%s%n", SongPath);
			
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file. Terminating.");
		}
	}

	// close file
	public static void closeFile() {
		if (output != null)
			output.close();
		if (output1 != null)
			output1.close();
	}
}