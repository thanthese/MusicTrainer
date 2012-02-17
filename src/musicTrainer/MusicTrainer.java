package musicTrainer;

import java.io.File;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class MusicTrainer {

	public static void main(String[] args) {

		Options opts = new Options();
		try {
			new JCommander(opts, args);
		} catch (ParameterException e) {
			showHelp();
			System.exit(0);
		}
		
		if(opts.inputMidiFiles.size() != 1 
				|| opts.inputMidiFiles.get(0).equals("")) {
			showHelp();
			System.exit(0);
		}
		
		Song song = new Song(new File(opts.inputMidiFiles.get(0)),
				opts.beatsPerMeasure);
		new SongRunner(song).run(opts.startingMeasure);
		
		System.exit(0);
	}

	private static void showHelp() {
		JCommander jcom = new JCommander(new Options());
		jcom.setProgramName("MusicTrainer");
		System.out.println("");
		System.out.println("Learn to play a MIDI song on the piano!");
		System.out.println("");
		jcom.usage();
		System.out.println("The MIDI file should be a piano file with a track for each hand.");
		System.out.println("");
	}
}