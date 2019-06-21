package java03Project.mode.normal;

import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MusicInfoReader {
	private Scanner input;
	private Scanner input1;
	private String fileName; // target file name
	private String fileName1;

	public MusicInfoReader(String fileName, String fileName1) {
		this.fileName = fileName;
		this.fileName1 = fileName1;
	}

	public String[] readAllMusics() {
		openFile();
		ArrayList<MusicName> list = readMusics();
		//System.out.println(list);
		String[] listArray = convertArrayList2Array(list);
		closeFile();

		return listArray;
	}

	public void openFile() {
		try {
			input = new Scanner(Paths.get(fileName));
			input1 = new Scanner(Paths.get(fileName1));
		} catch (IOException ioException) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
	}

	// Read all records and return an ArrayList of Player Objects
	public ArrayList<MusicName> readMusics() {
		ArrayList<MusicName> list = new ArrayList<MusicName>();
		MusicName music;

		//System.out.printf("%-12s%-12s%10s%n", "First Name", "Last Name", "Balance");

		try {
			while (input.hasNext()) // while there is more to read
			{
				//TODO
				music = new MusicName(input.next(), input1.next());
				list.add(music);
			}
		} catch (NoSuchElementException elementException) {
			System.err.println("File improperly formed. Terminating.");
		} catch (IllegalStateException stateException) {
			System.err.println("Error reading from file. Terminating.");
		}

		return list;
	} // end method readRecords

	
	public String[] convertArrayList2Array(ArrayList<MusicName> list) {
		int size = list.size();
		String[] listArray = new String[size];

		for (int i = 0; i < size; i++) {
			MusicName account = list.get(i);
			listArray[i] = account.getSongName();
		}
		return listArray;
	}

	// close file and terminate application
	public void closeFile() {
		if (input != null)
			input.close();
	}

}
