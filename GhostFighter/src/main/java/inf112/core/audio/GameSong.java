package inf112.core.audio;

import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * GameSong class taken from INF101 Semesteroppgave 1 Spring 2024
 * Added constructor with trackpath in contrary to fixed path in INF101
 */
public class GameSong implements Runnable {

  private final String trackPath;
  private Sequencer sequencer;

  public GameSong(String trackPath) {
    this.trackPath = trackPath;
  }

  @Override
  public void run() {
    InputStream song = GameSong.class.getClassLoader().getResourceAsStream(trackPath);
    this.doPlayMidi(song);
  }

  private void doPlayMidi(final InputStream is) {
    try {
      this.doStopMidiSounds();
      (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
      this.sequencer.setLoopCount(-1);
      this.sequencer.open();
      this.sequencer.start();
    } catch (Exception e) {
      this.midiError("" + e);
    }
  }

  /**
   * Stops and releases the MIDI sequence.
   */
  public void doStopMidiSounds() {
    try {
      if (this.sequencer == null || !this.sequencer.isRunning()) {
        return;
      }
      this.sequencer.stop();
      this.sequencer.close();
    } catch (Exception e) {
      this.midiError("" + e);
    }
    this.sequencer = null;
  }

  /**
   * Pauses the MIDI sequence.
   */
  public void doPauseMidiSounds() {
    try {
      if (this.sequencer == null || !this.sequencer.isRunning()) {
        return;
      }
      this.sequencer.stop();
    } catch (Exception e) {
      this.midiError("" + e);
    }
  }

  /**
   * Unpauses the MIDI sequence.
   */
  public void doUnpauseMidiSounds() {
    try {
      if (this.sequencer == null) {
        return;
      }
      this.sequencer.start();
    } catch (Exception e) {
      this.midiError("" + e);
    }
  }

  /**
   * Handles MIDI errors.
   */
  private void midiError(final String msg) {
    System.err.println("Midi error: " + msg);
    this.sequencer = null;
  }
}