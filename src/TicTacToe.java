import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
  int boardWidth = 600;
  int boardHeight = 650;

  JFrame frame = new JFrame("Tic Tac Toe");
  JLabel textLabel = new JLabel();
  JPanel textPanel = new JPanel();
  JPanel boardPanel = new JPanel();
  JFrame popupFrame = new JFrame();
  
  JButton[][] board = new JButton[3][3];
  String playerX = "X";
  String playerO = "O";
  String currentPlayer = playerX;

  boolean gameOver = false;
  int turns = 0;

  public TicTacToe() {
    frame.setVisible(true);
    frame.setSize(boardWidth,boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    textLabel.setBackground(Color.BLACK);
    textLabel.setForeground(Color.WHITE);
    textLabel.setFont(new Font("Arial", Font.BOLD, 50));
    textLabel.setHorizontalAlignment(JLabel.CENTER);
    textLabel.setText("Tic Tac Toe");
    textLabel.setOpaque(true);

    textPanel.setLayout(new BorderLayout());
    textPanel.add(textLabel);
    frame.add(textPanel, BorderLayout.NORTH);

    boardPanel.setLayout(new GridLayout(3,3));
    boardPanel.setBackground(Color.gray);
    frame.add(boardPanel);
    
    for(int r=0; r<3; r++){
      for(int c=0; c<3; c++){
        JButton box = new JButton();
        board[r][c] = box;
        boardPanel.add(box);

        box.setBackground(Color.gray);
        box.setForeground(Color.white);
        box.setFont(new Font("Arial", Font.BOLD, 120));
        box.setFocusable(false); //So it can only be clicked with mouse 

        box.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            if (gameOver) return;
            JButton box = (JButton) e.getSource(); //Type button as there is only buttons 
            if (box.getText() == ""){
              box.setText(currentPlayer);
              turns += 1;
              checkWinner();

              if (!gameOver) {
                currentPlayer = currentPlayer == playerX ? playerO : playerX;  //If currentPlayer is X then set it to O or else X
                textLabel.setText(currentPlayer + "'s turn");
              }
            }
          }
        });
      }
    }
  }
  public void checkWinner(){
    for(int r=0; r<3; r++){
      if (board[r][0].getText() == "") continue;
      if (board[r][0].getText() == board[r][1].getText() &&
          board[r][1].getText() == board[r][2].getText()) {
        for (int i=0; i < 3; i++){
          setWinner(board[r][i]);
        }
        gameOver = true;
        winExitAlert(currentPlayer);
      }
    }

    for(int c=0; c<3; c++){
      if (board[0][c].getText() == "") continue;
      if (board[0][c].getText() == board[1][c].getText() &&
          board[1][c].getText() == board[2][c].getText()) {
        for (int i=0; i < 3; i++){
          setWinner(board[i][c]);
        }
        gameOver = true;
        winExitAlert(currentPlayer);
      }
    }

    if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != ""){
      for (int i=0; i < 3; i++){
        setWinner(board[i][i]);
      }
      gameOver = true;
      winExitAlert(currentPlayer);
    }

    if (board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != ""){
      setWinner(board[0][2]);
      setWinner(board[1][1]);
      setWinner(board[2][0]);
      gameOver = true;
      winExitAlert(currentPlayer);
    }

    if (turns == 9){
      for(int r=0; r<3; r++){
        for(int c=0; c<3; c++){
          setTie(board[r][c]);
        }
      }
      JOptionPane.showMessageDialog(null, "It's a tie!", "Press OK to exit", JOptionPane.INFORMATION_MESSAGE);
      System.exit(0);
    }
  }

  public void setTie(JButton box){
    box.setBackground(Color.darkGray);
    box.setForeground(Color.orange);
    textLabel.setText("Tie!");
  }
  public void setWinner(JButton box){
    box.setBackground(Color.red);
    box.setForeground(Color.green);
    textLabel.setText(currentPlayer + " Wins!");
  }

  public void winExitAlert(String currentPlayer){
    JOptionPane.showMessageDialog(null, currentPlayer + " is the winner!", "Press OK to exit", JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }
}
