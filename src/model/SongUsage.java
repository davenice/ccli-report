package model;

public class SongUsage {
	
	private Song song;

	private String ccl;
	private String lastReported;
	private String reporter;
	private String print;
	private String digital;
	private String record;
	private String translation;
	
	public SongUsage(Song song, String ccl, String lastReported,
			String reporter, String print, String digital, String record,
			String translation) {
		super();
		this.song = song;
		this.ccl = ccl;
		this.lastReported = lastReported;
		this.reporter = reporter;
		this.print = print;
		this.digital = digital;
		this.record = record;
		this.translation = translation;
	}

	public Song getSong() {
		return song;
	}

	public String getCcl() {
		return ccl;
	}

	public String getLastReported() {
		return lastReported;
	}

	public String getReporter() {
		return reporter;
	}

	public String getPrint() {
		return print;
	}

	public String getDigital() {
		return digital;
	}

	public String getRecord() {
		return record;
	}

	public String getTranslation() {
		return translation;
	}
	
}
