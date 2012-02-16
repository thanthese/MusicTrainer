package musicTrainer;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Song {

	private Sequence seq;
	private Sequencer seqr;
	private int beatsPerMeasure;

	public static final int LEFT_HAND = 0;
	public static final int RIGHT_HAND = 1;
	public static final int BOTH_HANDS = 2;

	public Song(File file, int beatsPerMeasure) {

		this.beatsPerMeasure = beatsPerMeasure;

		try {
			seq = MidiSystem.getSequence(file);

			seqr = MidiSystem.getSequencer();
			seqr.open();
			seqr.setSequence(seq);
		} catch (Exception e) {
			// I really don't care what broke
			e.printStackTrace();
		}
	}

	public void startLoop(int hand, double tempoFactor, int endingMeasure, int totalMeasures) {
		setHand(hand);
		setTempo((float) tempoFactor);
		setLoop(endingMeasure, totalMeasures);
		seqr.start();
	}

	private void setLoop(int endingMeasure, int totalMeasures) {
		long endTicks = measureToTicks(endingMeasure);
		long startTicks = endTicks - measureToTicks(totalMeasures);
		if(startTicks < 0) startTicks = 0;

		seqr.setTickPosition(startTicks);
		seqr.setLoopStartPoint(startTicks);
		seqr.setLoopEndPoint(endTicks);
		seqr.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
	}

	private void setHand(int hand) {
		seqr.setTrackMute(LEFT_HAND, hand == LEFT_HAND);
		seqr.setTrackMute(RIGHT_HAND, hand == RIGHT_HAND);
	}

	private void setTempo(float tempoFactor) {
		seqr.setTempoFactor(tempoFactor);
	}

	private long measureToTicks(int startOfMeasure) {
		return seq.getResolution() * beatsPerMeasure * startOfMeasure;
	}

	public void stop() {
		seqr.stop();
	}

}
