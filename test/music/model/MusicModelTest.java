package music.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the {@link MusicModel} class
 */
public class MusicModelTest {
  @Test
  public void addNoteAndGetBeat() {
    MusicInterface model = new MusicModel();

    List<MusicNote> expected = new ArrayList<>();
    expected.add(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getNotesInBeat(0), null);
    model.addNote(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getNotesInBeat(0), expected);

    List<MusicNote> expected2 = new ArrayList<>();
    expected2.add(new Note(7, 8, 0, 0, 0));
    expected2.add(new Note(7, 10, 0, 10, 0));
    model.addNote(new Note(7, 8, 0, 0, 0));
    model.addNote(new Note(7, 10, 0, 10, 0));
    assertEquals(model.getNotesInBeat(7), expected2);
  }

  @Test
  public void getLengthTest() {
    MusicInterface model = new MusicModel();
    assertEquals(model.getLength(), 0);
    model.addNote(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getLength(), 4);
    model.addNote(new Note(10, 18, 0, 60, 0));
    assertEquals(model.getLength(), 17);
  }

  @Test
  public void removeNoteTest() {
    MusicInterface model = new MusicModel();

    List<MusicNote> expected = new ArrayList<>();
    expected.add(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getNotesInBeat(0), null);
    model.addNote(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getNotesInBeat(0), expected);
    model.removeNote(new Note(0, 5, 0, 0, 0));
    assertEquals(model.getNotesInBeat(0), new ArrayList<MusicNote>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeNoteThatDoesntExist() {
    MusicInterface model = new MusicModel();
    model.removeNote(new Note(0, 5, 0, 0, 0));
  }

  @Test
  public void tempoTest() {
    MusicInterface model = new MusicModel();
    assertEquals(model.getTempo(), 0);
    model.setTempo(100);
    assertEquals(model.getTempo(), 100);
  }

  @Test
  public void beatsPerMeasureTest() {
    MusicInterface model = new MusicModel();
    assertEquals(model.getBeatPerMeasure(), 0);
    model.setBeatPerMeasure(4);
    assertEquals(model.getBeatPerMeasure(), 4);
  }

  @Test
  public void getLowestNoteTest() {
    Note expected1 = new Note(7, 10, 0, 10, 0);
    Note expected2 = new Note(100, 102, 0, 3, 0);
    MusicInterface model = new MusicModel();
    model.addNote(new Note(7, 10, 0, 10, 0));
    assertTrue(model.getLowestNote().equals(expected1));
    model.addNote(new Note(100, 102, 0, 3, 0));
    assertTrue(model.getLowestNote().equals(expected2));
  }

  @Test
  public void getHighestNoteTest() {
    Note expected1 = new Note(7, 10, 0, 10, 0);
    Note expected2 = new Note(0, 5, 0, 60, 0);
    MusicInterface model = new MusicModel();
    model.addNote(new Note(7, 10, 0, 10, 0));
    assertTrue(model.getHighestNote().equals(expected1));
    model.addNote(new Note(0, 5, 0, 60, 0));
    assertTrue(model.getHighestNote().equals(expected2));
  }

  @Test
  public void getLowestNoteValTest() {
    MusicInterface model = new MusicModel();
    model.addNote(new Note(7, 10, 0, 10, 0));
    assertEquals(model.getLowestNoteVal(), 10);
    model.addNote(new Note(100, 102, 0, 3, 0));
    assertEquals(model.getLowestNoteVal(), 3);
  }

  @Test
  public void getHighestNoteValTest() {
    MusicInterface model = new MusicModel();
    model.addNote(new Note(7, 10, 0, 10, 0));
    assertEquals(model.getHighestNoteVal(), 10);
    model.addNote(new Note(0, 5, 0, 60, 0));
    assertEquals(model.getHighestNoteVal(), 60);
  }
}