package musicTrainer;

import java.io.IOException;

public class SongRunner {

	private static float[] tempoFactors = { 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f };
	private Song song;

	public SongRunner(Song song) {
		this.song = song;
	}

	public void run(int startingMeasure) {
		int currentMeasure = startingMeasure;
		while (true) { // they'll eventually give up, right?

			acrossTempos(Song.LEFT_HAND, currentMeasure, 1);
			acrossTempos(Song.RIGHT_HAND, currentMeasure, 1);
			acrossTempos(Song.BOTH_HANDS, currentMeasure, 1);

			if (currentMeasure >= 2) {
				acrossTempos(Song.LEFT_HAND, currentMeasure, 2);
				acrossTempos(Song.RIGHT_HAND, currentMeasure, 2);
				acrossTempos(Song.BOTH_HANDS, currentMeasure, 2);
			}

			if (currentMeasure >= 3) {
				acrossTempos(Song.BOTH_HANDS, currentMeasure, 3);
			}

			currentMeasure++;
		}
	}

	private void acrossTempos(int hands, int endingMeasure, int totalMeasures) {
		for (int i = 0; i < tempoFactors.length; i++) {
			float tempoFactor = tempoFactors[i];

			System.out.println(prettyStatus(hands, endingMeasure,
					totalMeasures, tempoFactor));

			song.startLoop(hands, tempoFactor, endingMeasure, totalMeasures);
			pauseForEnter();
			song.stop();
		}
	}

	private String prettyStatus(int hands, int endingMeasure,
			int totalMeasures, float tempoFactor) {
		String measures = prettyMeasures(endingMeasure, totalMeasures);
		return String.format("Playing %s with %s at a %d%% tempo.", measures,
				handsToStr(hands), new Float(tempoFactor * 100).intValue());
	}

	private String prettyMeasures(int endingMeasure, int totalMeasures) {
		if (totalMeasures <= 1)
			return "measure " + String.valueOf(endingMeasure);

		int start = endingMeasure - totalMeasures + 1;
		return String.format("measures %d through %d", start, endingMeasure);
	}

	private String handsToStr(int hands) {
		if (hands == Song.LEFT_HAND)
			return "the left hand only";
		if (hands == Song.RIGHT_HAND)
			return "the right hand only";
		if (hands == Song.BOTH_HANDS)
			return "both hands";
		return "no hands?";
	}

	private void pauseForEnter() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
