package music.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the {@link Note} class
 */
public class NoteTest {
  @Test
  public void constructorsResultInSameNote() {
    Note n = new Note(0, 8, 0, 60, 0);
    Note n2 = new Note(0, 8, 0, Pitch.C, 4, 0);
    Note n3 = new Note(n);

    assertTrue(n.equals(n2));
    assertTrue(n.equals(n3));
    assertTrue(n2.equals(n3));
  }

  @Test
  public void testGetSetPitch() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    Pitch expected = Pitch.C;
    assertEquals(n.getPitch(), expected);
    n.setPitch(Pitch.D);
    assertEquals(n.getPitch(), Pitch.D);
  }

  @Test
  public void testGetSetOctave() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    int expected = 5;
    assertEquals(n.getOctave(), expected);
    n.setOctave(10);
    assertEquals(n.getOctave(), 10);
  }

  @Test
  public void testGetSetStart() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    assertEquals(n.getStart(), 0);
    n.setStart(10);
    assertEquals(n.getStart(), 10);
  }

  @Test
  public void testGetSetEnd() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    assertEquals(n.getEnd(), 8);
    n.setEnd(10);
    assertEquals(n.getEnd(), 10);
  }

  @Test
  public void testGetSetInstrument() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    assertEquals(n.getInstrument(), 0);
    n.setInstrument(5);
    assertEquals(n.getInstrument(), 5);
  }

  @Test
  public void testGetSetVolume() {
    Note n = new Note(0, 8, 0, Pitch.C, 5, 0);
    assertEquals(n.getVolume(), 0);
    n.setVolume(10);
    assertEquals(n.getVolume(), 10);
  }

  @Test
  public void compareToTest() {
    Note n = new Note(0, 5, 0, 10, 5);
    Note n2 = new Note(0, 5, 0, 60, 5);
    Note n3 = new Note(10, 19, 0, 60, 5);
    assertEquals(n.compareTo(n2), -1);
    assertEquals(n2.compareTo(n), 1);
    assertEquals(n2.compareTo(n3), 0);
  }

  @Test
  public void testEquals() {
    Note n = new Note(0, 5, 0, 10, 5);
    Note n2 = new Note(0, 5, 0, 60, 5);
    Note n3 = new Note(10, 19, 0, 60, 5);
    Note n4 = new Note(0, 5, 0, 60, 5);
    assertFalse(n.equals(n2));
    assertFalse(n.equals(n3));
    assertFalse(n.equals(n4));
    assertFalse(n2.equals(n3));
    assertTrue(n2.equals(n4));
  }

  @Test
  public void toStringTest() {
    String c4 = "C4";
    Note n = new Note(0, 0, 0, 60, 0);
    assertEquals(n.toString(), c4);
  }

  @Test
  public void pitchOctaveNumTest() {
    Note n = new Note(0, 0, 0, 60, 0);
    Note n2 = new Note(0, 0, 0, Pitch.C, 4, 0);
    assertEquals(n.getPitchOctaveNumber(), 60);
    assertEquals(n2.getPitchOctaveNumber(), 60);
  }
}