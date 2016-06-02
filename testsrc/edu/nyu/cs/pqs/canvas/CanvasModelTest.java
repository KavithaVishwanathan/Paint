package edu.nyu.cs.pqs.canvas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CanvasModelTest {
  CanvasModel model;
  CanvasModel sizeZeroModel;
  
  @Before
  public void setUp() {
    model = new CanvasModel(100,100);
    sizeZeroModel = new CanvasModel(0,0);
  }

  @Test
  public void testInitialValues() {
    assertEquals(0, model.getOldX());
  }

  @Test
  public void testUpdateStartPositions() {
    model.updateStartPositions(2, 2);
    assertEquals(2, model.getOldX());
  }
  
  
  @Test
  public void testUpdateStartPositions_zeroModel() {
    sizeZeroModel.updateStartPositions(20, 20);
    assertEquals(0, sizeZeroModel.getOldX());
  }
  
  @Test
  public void testUpdateStartPositions_moreThanScreenWidth() {
    model.updateStartPositions(200, 50);
    assertEquals(0,model.getOldX());
  }
  
  @Test
  public void testUpdateStartPositions_moreThanScreenBreadth() {
    model.updateStartPositions(50, 200);
    assertEquals(0,model.getOldY());
  }
  
  @Test
  public void testUpdateStartPositions_moreWidthAndBreadth() {
    model.updateStartPositions(200, 200);
    assertEquals(0,model.getOldX());
    assertEquals(0,model.getOldY());
  }
  
  @Test
  public void testUpdateEndPositions() {
    model.updateEndPositions(2, 2);
    assertEquals(2, model.getCurrentX());
  }
  
  @Test
  public void testUpdateEndPositions_zeroModel() {
    sizeZeroModel.updateEndPositions(20, 20);
    assertEquals(0, sizeZeroModel.getCurrentX());
  }
  
  @Test
  public void testUpdateEndPositions_moreThanScreenWidth() {
    model.updateEndPositions(200, 50);
    assertEquals(0,model.getCurrentX());
  }
  
  @Test
  public void testUpdateEndPositions_moreThanScreenBreadth() {
    model.updateEndPositions(50, 200);
    assertEquals(0,model.getCurrentY());
  }
  
  @Test
  public void testUpdateEndPositions_moreWidthAndBreadth() {
    model.updateEndPositions(200, 200);
    assertEquals(0,model.getCurrentX());
    assertEquals(0,model.getCurrentY());
  }
  
  @Test
  public void testUpdateEndPositions_sameWidth() {
    model.updateEndPositions(100, 20);
    assertEquals(0,model.getCurrentX());
    assertEquals(0,model.getCurrentY());
  }
  
  @Test
  public void testUpdateEndPositions_sameBreadth() {
    model.updateEndPositions(20, 100);
    assertEquals(0,model.getCurrentX());
    assertEquals(0,model.getCurrentY());
  }
  
  @Test
  public void testUpdateEndPositions_sameWidthAndBreadth() {
    model.updateEndPositions(100, 100);
    assertEquals(0,model.getCurrentX());
    assertEquals(0,model.getCurrentY());
  }
  
  @Test
  public void testClear_checkInitially() {
    model.clear();
    assertEquals(0,model.getOldX());
    assertEquals(0,model.getOldY());
  }
  
  @Test
  public void testClear_checkStart() {
    model.updateStartPositions(10, 10);
    model.clear();
    assertEquals(0,model.getOldX());
    assertEquals(0,model.getOldY());
  }
  
  @Test
  public void testClear_checkEnd() {
    model.updateEndPositions(10, 10);
    model.clear();
    assertEquals(0,model.getCurrentX());
    assertEquals(0,model.getCurrentY());
  }

}
