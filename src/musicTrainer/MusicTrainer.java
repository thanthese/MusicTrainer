package musicTrainer;

import java.io.File;

public class MusicTrainer {

	public static void main(String[] args) {
		
		if(args.length != 3) {
			printHelp();
			System.exit(0);
		}
		
		String inputMidiFile = args[0];
		int startingMeasure = Integer.valueOf(args[1]);
		int beatsPerMeasure = Integer.valueOf(args[2]);
		
		Song song = new Song(new File(inputMidiFile), beatsPerMeasure);
		new SongRunner(song).run(startingMeasure);
		
		System.exit(0);
	}
	
	private static void printHelp() {
		System.out.println("");
		System.out.println("Learn to play a MIDI song on the piano!");
		System.out.println("");
		System.out.println("Arguments: ");
		System.out.println("");
		System.out.println("1.  Path to input MIDI file: ");
		System.out.println("2.  Measure to start on (starting from 1)");
		System.out.println("3.  Beats per measure (usually 4)");
		System.out.println("");
	}
}