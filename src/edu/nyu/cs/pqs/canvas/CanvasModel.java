package edu.nyu.cs.pqs.canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the necessary functionality needed for simple paint class
 * @author Kavitha
 *
 */
public class CanvasModel {
  private List<CanvasListener> listeners = new ArrayList<CanvasListener>();
  private final int width;
  private final int breadth;
  private int oldX;
  private int oldY;
  private int currentX;
  private int currentY;
  
  /**
   * This constructor takes the width and breadth of the view to be created
   * @param width
   * @param breadth
   */
  public CanvasModel(int width, int breadth) {
    this.width = width;
    this.breadth = breadth;
    setPositionsZero();
  }
  
  /**
   * When the mouse pressed, it updates the startPosition
   * @param oldX
   * @param oldY
   */
  public void updateStartPositions(int oldX, int oldY) {
    if (checkMax(oldX, oldY)) {
      this.oldX = oldX;
      this.oldY = oldY;
    } else {
      setPositionsZero();
    }
  }
  
  /**
   * when the mouse is dragged, it updates the the old and current position as it gets dragged
   * @param currentX
   * @param currentY
   */
  public void updateEndPositions(int currentX, int currentY) {
    if (checkMax(currentX, currentY)) {
      this.currentX = currentX;
      this.currentY = currentY;
      firePaintMade();
      oldX = currentX;
      oldY = currentY;
    } else {
      setPositionsZero();
    }
  }
  
  /**
   * It resets the positions and clears all the listener
   */
  public void clear() {
    setPositionsZero();
    fireClearPaint();
  }
  
  public int getWidth() {
    return width;  
  }
  
  public int getBreadth() {
    return breadth;  
  }
  
  private boolean checkMax(int curX, int curY) {
    return curX < width && curY < breadth;
  }
  
  private void setPositionsZero() {
    oldX = 0;
    oldY = 0;
    currentX = 0;
    currentY = 0;
  }
  
  private void fireClearPaint() {
    for (CanvasListener listener : listeners) {
      listener.clearPaint(oldX, oldY);
    }
  }
  
  private void firePaintMade() {
    for (CanvasListener listener : listeners) {
      listener.paintMade(oldX, oldY, currentX, currentY);
    }
  }
  
  /**
   *  Registers the view as a listener to this model
   * @param canvasView
   */
  public void addCanvasListener(CanvasView canvasView) {
    listeners.add(canvasView);
  }
  
  /**
   *  Removes the view as a listener to this model
   * @param canvasView
   */
  public void removeCanvasListener(CanvasView canvasView) {
    listeners.remove(canvasView);
  }
  
  public int getOldX() {
    return oldX;
  }
  
  public int getOldY() {
    return oldY;
  }
  
  public int getCurrentX() {
    return currentX;
  }
  
  public int getCurrentY() {
    return currentY;
  }
  
}
