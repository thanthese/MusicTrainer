package musicTrainer;

import java.util.List;

import com.beust.jcommander.Parameter;

public class Options {

	@Parameter(names = { "-m", "--starting-measure" },
			description = "Which measure to start on.  Starts from 1.")
	public int startingMeasure = 1;

	@Parameter(names = { "-b", "--beats-per-measure" },
			description = "Number of beats per measure.  Usually 4.")
	public int beatsPerMeasure = 4;

	@Parameter(
			description = "MIDI",
			required = true)
	public List<String> inputMidiFiles;
}
