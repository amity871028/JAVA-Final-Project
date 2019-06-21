package java03Project.function.analysisMusic;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class TimeRecordWriter {

	private static Formatter output;
	
	public TimeRecordWriter(String musicName, String musicPath, String tempo) {
		openfile();
		addRecord(musicName, musicPath, tempo);
		closeFile();
	}
	public void openfile() {
		try {
			FileWriter fw = new FileWriter("TimeRecordWithMusic.txt", true);
			output = new Formatter(fw);
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
	
	public void addRecord(String musicName, String musicPath, String tempo) {
		try {
			output.format("%s %s %s%n", musicName, musicPath, tempo);
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file. Terminating.");
		}
		
	}
	
	public static void closeFile() {
		if (output != null)
			output.close();
	}
}
