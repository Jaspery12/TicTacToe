import java.awt.*;
import java.awt.event.*;
import java.swing.*;
import javax.swing.JFrame;

public class TicTacToe {
  int boardWidth = 600;
  int boardHeight = 650;

  JFrame frame = new JFrame();

  public TicTacToe() {
    frame.setVisible(true);
    frame.setSize(boardHeight,boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
  }
  
}
