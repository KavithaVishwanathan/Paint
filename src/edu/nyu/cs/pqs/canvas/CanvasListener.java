package edu.nyu.cs.pqs.canvas;

/**
 * This class will be implemented by all the views which needs to register/listen to the
 * CanvasModel
 */
public interface CanvasListener {
  /**
   * This method takes the old and current positions of the mouse and draw the graphic in the view
   * @param oldX
   * @param oldY
   * @param currentX
   * @param currentY
   */
  void paintMade(int oldX, int oldY, int currentX, int currentY);
  
  /**
   * Sets the position value to zero and clears the canvas
   * @param oldX
   * @param oldY
   */
  void clearPaint(int oldX, int oldY); 
  
}
