package music.view;

import music.model.MusicInterface;

/**
 * Enforces the methods that a Music View should implement
 */
public interface MusicViewInterface {
  /**
   * Shows/Outputs the appropriate view from the given model
   */
  void showView(MusicInterface model);
}
