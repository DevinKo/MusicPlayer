package music.view;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

//import cs3500.music.mocks.MockSequencer;
import music.model.MusicInterface;
import music.model.MusicModel;
import music.model.Note;
import music.util.CompositionBuilderImpl;
import music.util.MusicReader;

import static org.junit.Assert.*;

/**
 * Tests the {@link MidiViewImpl} class
 */
public class MidiViewImplTest {
  @Test
  public void emptyModelTest() {
    MusicInterface model = new MusicModel();
    StringBuilder sb = new StringBuilder();
    MockSequencer sequencer = new MockSequencer(sb);
    MusicViewInterface view = new MidiViewImpl(sequencer);
    view.showView(model);
    String actual = sequencer.getOutput();
    assertEquals(actual, "Set tempo to be 0.0\n" +
            "Set sequence\n" +
            "Starting sequencer\n");
  }

  @Test
  public void simpleTest() {
    MusicInterface model = new MusicModel();
    model.setTempo(1000);
    model.addNote(new Note(0, 3, 1, 60, 1));
    model.addNote(new Note(1, 3, 1, 61, 1));
    model.addNote(new Note(2, 3, 1, 62, 1));
    StringBuilder sb = new StringBuilder();
    MockSequencer sequencer = new MockSequencer(sb);
    MusicViewInterface view = new MidiViewImpl(sequencer);
    view.showView(model);
    String actual = sequencer.getOutput();
    assertTrue(actual.contains("Set tempo to be 1000.0"));
    assertTrue(actual.contains("Message: 144, 0, 60, 1\n"));
    assertTrue(actual.contains("Message: 144, 0, 61, 1\n"));
    assertTrue(actual.contains("Message: 144, 0, 62, 1\n"));
    assertTrue(actual.contains("Message: 128, 0, 60, 1\n"));
    assertTrue(actual.contains("Message: 128, 0, 61, 1\n"));
    assertTrue(actual.contains("Message: 128, 0, 62, 1\n"));
    assertTrue(actual.contains("Set sequence\n"));
    assertTrue(actual.contains("Starting sequencer\n"));
  }

  // Testing that a good portion of the messages added were actually added to the sequence.
  @Test
  public void maryHadALittleLambTest() {
    Readable file = null;
    try {
      file = new FileReader("/Users/justinvincelette/Downloads/hw/06/code/mary-little-lamb.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    MusicModel model = MusicReader.parseFile(file, new CompositionBuilderImpl());
    StringBuilder sb = new StringBuilder();
    MockSequencer sequencer = new MockSequencer(sb);
    MusicViewInterface view = new MidiViewImpl(sequencer);
    view.showView(model);
    String actual = sequencer.getOutput();

    assertTrue(actual.contains("Set tempo to be 200000.0\n"));
    assertTrue(actual.contains("Message: 144, 0, 64, 72\n"));
    assertTrue(actual.contains("Message: 144, 0, 55, 70\n"));
    assertTrue(actual.contains("Message: 128, 0, 64, 72\n"));
    assertTrue(actual.contains("Message: 144, 0, 62, 72\n"));
    assertTrue(actual.contains("Message: 128, 0, 62, 72\n"));
    assertTrue(actual.contains("Message: 144, 0, 60, 71\n"));
    assertTrue(actual.contains("Message: 128, 0, 60, 71\n"));
    assertTrue(actual.contains("Message: 144, 0, 62, 79\n"));
    assertTrue(actual.contains("Message: 128, 0, 55, 70\n"));
    assertTrue(actual.contains("Message: 128, 0, 62, 79\n"));
    assertTrue(actual.contains("Message: 144, 0, 55, 79\n"));
    assertTrue(actual.contains("Message: 144, 0, 64, 85\n"));
    assertTrue(actual.contains("Message: 128, 0, 64, 85\n"));
    assertTrue(actual.contains("Message: 144, 0, 64, 78\n"));
    assertTrue(actual.contains("Message: 128, 0, 64, 78\n"));
    assertTrue(actual.contains("Message: 144, 0, 64, 74\n"));
    assertTrue(actual.contains("Message: 128, 0, 55, 79\n"));
    assertTrue(actual.contains("Message: 128, 0, 64, 74\n"));
    assertTrue(actual.contains("Message: 144, 0, 55, 77\n"));
    assertTrue(actual.contains("Set sequence\n"));
    assertTrue(actual.contains("Starting sequencer\n"));
  }
}
