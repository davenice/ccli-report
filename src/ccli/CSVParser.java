package ccli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Song;
import model.SongUsage;

public class CSVParser {

	private enum Type { Song, Usage, Unknown };
	
	private Type currentState = Type.Unknown;
	private Song currentSong = null;
	private Map<String, Song> songs = new HashMap<String, Song>();
	private File input;
	
	public CSVParser(File input) {
		this.input = input;
	}
	
	public Collection<Song> parse() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String line;
		while((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			processLine(fields);
		}
		reader.close();
		return Collections.unmodifiableCollection(songs.values());
	}
	
	private void processLine(String[] fields) {
		if(isBlankLine(fields)) {
			currentState = Type.Unknown;
		} else {
			if(fields[0].contains("Title")) {
				assert(fields.length == 5 && fields[0].equals("Title") && fields[1].equals("Copyright") && fields[2].equals("Song#") && fields[3].equals("Last Reported") && fields[4].equals("Reporter"));
				currentState = Type.Song;
			} else if(fields[0].contains("CCL")) {
				assert(fields.length == 7 && fields[0].equals("CCL") && fields[1].equals("Last Reported") && fields[2].equals("Reporter") && fields[3].equals("Print") && fields[4].equals("Digital") && fields[5].equals("Record") && fields[6].equals("Translation"));
				currentState = Type.Usage;
			} else {
				processContentLine(fields);
			}
		}
	}
	
	private void processContentLine(String[] fields) {
		switch (currentState) {
		case Song:
			String number = fields[2];
			if(songs.containsKey(number)) {
				System.err.println("Found song "+number+ " already recorded this time.");
				currentSong = songs.get(number);
			} else {
				currentSong = new Song(fields[0], fields[1], fields[2], fields[3], fields[4]);
				songs.put(fields[2], currentSong);
			}
			break;
		case Usage:
			currentSong.addSongUsage(new SongUsage(currentSong, fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]));
			break;
		default:
			throw new RuntimeException("Didn't expect to process content line in this state");
		}
	}
	
	private boolean isBlankLine(String[] fields) {
		if(fields == null || fields.length == 0) return true;
		for (String string : fields) {
			if(string.length() > 0) return false;
		}
		return true;
	}
}
