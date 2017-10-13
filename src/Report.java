import java.io.File;
import java.io.IOException;
import java.util.Collection;

import model.Song;
import ccli.CSVParser;

public class Report {
	public static void main(String[] args) throws IOException {
		File inputFile;
		if(args.length == 0) {
			inputFile = new File("ActivityReport.csv");
		} else {
			inputFile = new File(args[0]);
		}
		CSVParser parser = new CSVParser(inputFile);
		Collection<Song> songs = parser.parse();
		System.out.println("Title,Number of times sung,Last sung");
		for (Song song : songs) {
			
			System.out.print(song.getTitle() + ",");
			System.out.print(song.getSongUsages().size() + ",");
			System.out.print(song.getLastReported());
			System.out.println();
		}
	}
}
