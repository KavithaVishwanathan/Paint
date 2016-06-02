package edu.nyu.cs.pqs.canvas;

public class CanvasApp {

  public static void main(String[] args) {
    new CanvasApp().startGame();
  }

  private void startGame() {
    CanvasModel model = new CanvasModel(1000, 1000);
    new CanvasView(model);
    new CanvasView(model);
    //model.startApp();
  }

}
