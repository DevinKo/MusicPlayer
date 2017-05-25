package music.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the {@link MouseHandler} class
 */
public class MouseHandlerTest {
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

  MouseEvent leftClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON1_MASK, 0, 0, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1);

  Runnable left = () -> System.out.print("left clicked");

  @Test
  public void testClick() {
    MouseHandler m = new MouseHandler.Builder().addMouseClicked(MouseEvent.BUTTON1, left).build();
    m.mouseClicked(leftClick);
    assertEquals("left clicked", outputData.toString());
  }

  @Test
  public void testPressed() {
    MouseHandler m = new MouseHandler.Builder().addMousePressed(MouseEvent.BUTTON1, left).build();
    m.mousePressed(leftClick);
    assertEquals("left clicked", outputData.toString());
  }

  @Test
  public void testReleased() {
    MouseHandler m = new MouseHandler.Builder().addMouseReleased(MouseEvent.BUTTON1, left).build();
    m.mouseReleased(leftClick);
    assertEquals("left clicked", outputData.toString());
  }

  @Test
  public void testEntered() {
    MouseHandler m = new MouseHandler.Builder().addMouseEntered(MouseEvent.BUTTON1, left).build();
    m.mouseEntered(leftClick);
    assertEquals("left clicked", outputData.toString());
  }

  @Test
  public void testExited() {
    MouseHandler m = new MouseHandler.Builder().addMouseExited(MouseEvent.BUTTON1, left).build();
    m.mouseExited(leftClick);
    assertEquals("left clicked", outputData.toString());
  }
}
