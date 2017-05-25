package music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import music.controller.MusicController;
import music.model.MusicInterface;
import music.util.CompositionBuilderImpl;
import music.util.MusicReader;
import music.view.GuiView;
import music.view.MusicViewInterface;
import music.view.ViewCreator;

public final class MusicEditor {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("No arguments were given. Please enter file-name and view-type as " +
        "command line arguments");
    }
    Readable file = null;
    try {
      file = new FileReader("/Users/kevindo/Desktop/code/06/code/mary-little-lamb.txt");
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    MusicInterface model = MusicReader.parseFile(file, new CompositionBuilderImpl());
    MusicViewInterface view = ViewCreator.create("composite");
    if (view instanceof GuiView) {
      GuiView visual = (GuiView) view;
      MusicController controller = new MusicController(model, visual);
    } else {
      view.showView(model);
      try {
        Thread.sleep(10000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
