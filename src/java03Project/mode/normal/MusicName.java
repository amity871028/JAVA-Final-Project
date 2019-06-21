package java03Project.mode.normal;

public class MusicName {
	private String SongName;
	private String SongPath;

	public MusicName() {
		this("", ""); // call other constructor
	}
	
	/*
	public MusicName(String SongName)
	{
		this.SongName = SongName;
		this.SongPath = "";
	}*/

	public MusicName(String SongName, String SongPath) {
		this.SongName = SongName;
		this.SongPath = SongPath;
	}

	// set first name
	public void setSongName(String SongName) {
		this.SongName = SongName;
	}

	// get first name
	public String getSongName() {
		return SongName;
	}
	
	public void setSongPath(String SongPath) {
		this.SongPath = SongPath;
	}

	// get first name
	public String getSongPath() {
		return SongPath;
	}
	
	@Override
	public String toString() {
		return "Name:" + SongName + '\n' + "Path:" + SongPath;
	}

}