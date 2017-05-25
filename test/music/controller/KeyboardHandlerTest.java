package music.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Tests the {@link KeyboardHandler} class
 */
public class KeyboardHandlerTest {
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

  KeyEvent z = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_Z, ' ', 0);
  KeyEvent x = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_X, ' ', 0);
  KeyEvent space = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0);
  KeyEvent upArrow = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_UP, ' ', 0);
  KeyEvent downArrow = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_DOWN, ' ', 0);
  KeyEvent leftArrow = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_LEFT, ' ', 0);
  KeyEvent rightArrow = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_RIGHT, ' ', 0);
  KeyEvent homeKey = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_HOME, ' ', 0);
  KeyEvent endKey = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_END, ' ', 0);

  private Runnable zKey = () -> System.out.print("z pressed");
  private Runnable xKey = () -> System.out.print("x pressed");
  private Runnable spaceKey = () -> System.out.print("space pressed");
  private Runnable arrowUp = () -> System.out.print("up pressed");
  private Runnable arrowDown = () -> System.out.print("down pressed");
  private Runnable arrowLeft = () -> System.out.print("left pressed");
  private Runnable arrowRight = () -> System.out.print("right pressed");
  private Runnable home = () -> System.out.print("home pressed");
  private Runnable end = () -> System.out.print("end pressed");

  @Test
  public void testTyped() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyTyped(KeyEvent.VK_Z, zKey).build();
    k.keyTyped(z);
    assertEquals("z pressed", outputData.toString());
  }

  @Test
  public void testZ() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_Z, zKey).build();
    k.keyPressed(z);
    assertEquals("z pressed", outputData.toString());
  }

  @Test
  public void testX() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_X, xKey).build();
    k.keyPressed(x);
    assertEquals("x pressed", outputData.toString());
  }

  @Test
  public void testSpace() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_SPACE, spaceKey)
      .build();
    k.keyPressed(space);
    assertEquals("space pressed", outputData.toString());
  }

  @Test
  public void testUpArrow() {
    KeyboardHandler k = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_UP, arrowUp).build();
    k.keyPressed(upArrow);
    assertEquals("up pressed", outputData.toString());
  }

  @Test
  public void testDownArrow() {
    KeyboardHandler k = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_DOWN, arrowDown).build();
    k.keyPressed(downArrow);
    assertEquals("down pressed", outputData.toString());
  }

  @Test
  public void testLeftArrow() {
    KeyboardHandler k = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_LEFT, arrowLeft).build();
    k.keyPressed(leftArrow);
    assertEquals("left pressed", outputData.toString());
  }

  @Test
  public void testRightArrow() {
    KeyboardHandler k = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_RIGHT, arrowRight).build();
    k.keyPressed(rightArrow);
    assertEquals("right pressed", outputData.toString());
  }

  @Test
  public void testHome() {
    KeyboardHandler k = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_HOME, home).build();
    k.keyPressed(homeKey);
    assertEquals("home pressed", outputData.toString());
  }

  @Test
  public void testEnd() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_END, end).build();
    k.keyPressed(endKey);
    assertEquals("end pressed", outputData.toString());
  }

  @Test
  public void testReleased() {
    KeyboardHandler k = new KeyboardHandler.Builder().addKeyReleased(KeyEvent.VK_Z, zKey).build();
    k.keyReleased(z);
    assertEquals("z pressed", outputData.toString());
  }
}
