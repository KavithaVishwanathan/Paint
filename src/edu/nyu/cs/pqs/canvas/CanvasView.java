package edu.nyu.cs.pqs.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class renders the GUI needed for the canvas paint
 *@author Kavitha
 */
public class CanvasView implements CanvasListener {
  private CanvasModel model;
  private JFrame frame = new JFrame("Paint");
  private JPanel drawPanel;
  Graphics2D graphics;
  Image image;
  int width;
  int breadth;
  
  /**
   * The constructor takes the model and registers itself as a listener
   * @param model
   */
  public CanvasView(CanvasModel model) {
    this.model = model;
    model.addCanvasListener(this);
    width = model.getWidth();
    breadth = model.getBreadth();
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    JButton clearButton = new JButton("Clear");
    
    createDrawingPanel();
    startMouseListeners();
    mainPanel.add(drawPanel, BorderLayout.CENTER);
    mainPanel.add(clearButton, BorderLayout.SOUTH);
    
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        model.clear();
      }
    });
    
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, breadth);
    frame.setVisible(true); 
  }
  
  /**
   * This method creates the drawing panel and override the paintComponent to include the graphic 
   * component
   */
  private void createDrawingPanel() {
    drawPanel = new JPanel() {
      protected void paintComponent(Graphics g){
        if(image == null){
          image = createImage(getSize().width, getSize().height);
          graphics = (Graphics2D)image.getGraphics();
          graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
             RenderingHints.VALUE_ANTIALIAS_ON);
          clearPaint(0,0);
        }
        g.drawImage(image, 0, 0, null);
      }
    };
    drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    drawPanel.setPreferredSize(new Dimension(1000, 1000));
  }
  
  /**
   * This method listens for the mouse listeners and updates the model with the positions
   */
  public void startMouseListeners() {
    drawPanel.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        model.updateStartPositions(e.getX(), e.getY());
      }
    });
    
    drawPanel.addMouseMotionListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
          model.updateEndPositions(e.getX(), e.getY());
        }
    });
  }
  
  @Override
  public void paintMade(int oldX, int oldY, int currentX, int currentY) {   
    graphics.drawLine(oldX, oldY, currentX, currentY);
    drawPanel.repaint();
  }
  
  @Override
  public void clearPaint(int x, int y){
    graphics.setPaint(Color.white);
    graphics.fillRect(x, y, drawPanel.getSize().width, drawPanel.getSize().height);
    graphics.setPaint(Color.black);
    drawPanel.repaint();
  }
  
}
