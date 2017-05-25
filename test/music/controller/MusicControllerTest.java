package music.controller;

import music.model.MusicInterface;
import music.util.CompositionBuilderImpl;
import music.util.MusicReader;
import music.view.GuiView;
import music.view.MusicViewInterface;
import music.view.ViewCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the {@link MusicController} class
 */
public class MusicControllerTest {
  private final ByteArrayOutputStream outputData = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errorData = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outputData));
    System.setErr(new PrintStream(errorData));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  KeyEvent space = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0);

  MouseEvent leftClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON1_MASK, 0, 0, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1);

  Runnable spaceKey = () -> System.out.print("space pressed");
  Runnable left = () -> System.out.print("left clicked");

  @Test
  public void testPlayPause() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_SPACE, spaceKey)
      .build();

    Readable file = null;
    try {
      file = new FileReader("/Users/kevindo/Desktop/code/06/code/mary-little-lamb.txt");
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    MusicInterface model = MusicReader.parseFile(file, new CompositionBuilderImpl());
    MusicViewInterface view = ViewCreator.create("composite");

    GuiView visual = (GuiView) view;
    MusicController controller = new MusicController(model, visual);

    int expectedBeat = controller.getCurrentBeat();

    k.keyPressed(space);

    try {
      Thread.sleep(3000);                 //3000 milliseconds is three seconds.
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    int actualBeat = controller.getCurrentBeat();


    assertEquals(actualBeat, expectedBeat);
  }

  @Test
  public void testAddNote() {
    MouseHandler m = new MouseHandler.Builder().addMousePressed(MouseEvent.BUTTON1, left).build();
    m.mousePressed(leftClick);

    assertEquals("left clicked", outputData.toString());
  }
}
