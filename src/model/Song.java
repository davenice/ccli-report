package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Song {
	
	private List<SongUsage> songUsages = new ArrayList<SongUsage>();
	
	private String title;
	private String copyright;
	private String number;
	private String lastReported;
	private String reporter;
	
	public Song(String title, String copyright, String number, String lastReported, String reporter) {
		super();
		this.title = title;
		this.copyright = copyright;
		this.number = number;
		this.lastReported = lastReported;
		this.reporter = reporter;
	}
	public String getTitle() {
		return title;
	}
	public String getCopyright() {
		return copyright;
	}
	public String getNumber() {
		return number;
	}
	public String getLastReported() {
		return lastReported;
	}
	public String getReporter() {
		return reporter;
	}
	
	public void addSongUsage(SongUsage songUsage) {
		if(songUsage.getSong() != this) throw new RuntimeException("Tried to add usage for a different song");
		songUsages.add(songUsage);
	}
	
	public List<SongUsage> getSongUsages() {
		return Collections.unmodifiableList(songUsages);
	}
	
}
