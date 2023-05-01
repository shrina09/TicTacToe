package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import boardgame.ui.PositionAwareButton;
import game.GameUI;
import java.awt.Color;
import java.awt.Font;

/**
* This class is for the user interface of tictactoe 
* @author Shrina
*/
public class TicTacToeView extends JPanel{
    private JLabel messageLabel;
    private TicTacToeGame game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    private GameUI root;
    
    //Constructor
    public TicTacToeView(int width, int height, GameUI gameFrame){
        // call the superclass constructor
        super();    
        setLayout(new BorderLayout());
        root = gameFrame;

        // instantiate the controller
        setGameController(new TicTacToeGame(width,height));   


        // make a new label to store messages
        messageLabel = new JLabel("Welcome to Tic Tac Toe");
        messageLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        messageLabel.setForeground(Color.orange);
        add(messageLabel, BorderLayout.NORTH);  // Messages go on top   
        add(makeNewGameButton(),BorderLayout.EAST);
        add(makeButtonGrid(height,width), BorderLayout.CENTER);
    }
    
    /**
    * For setting the control (tictactoe is being played)
    * @param this.game
    */
    public void setGameController(TicTacToeGame controller){
        this.game = controller;
    }

    /**
    *Creates the New Game button
    * @return button
    */
    private JButton makeNewGameButton(){
        JButton button = new JButton("New Game");
        button.addActionListener(e->newGame());
        button.setBackground(Color.green);
        button.setOpaque(true);
        return button;
    }
    
    /**
    *Creates the board
    * @param height
    * @param width
    * @return panel
    */
    private JPanel makeButtonGrid(int height, int width){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[height][width];
        panel.setLayout(new GridLayout(width, height));
                for (int y=0; y<width; y++){
            for (int x=0; x<height; x++){ 
                //Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); //made the choice to be 1-based
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                        setPlayer(e);
                                        checkGameState();
                                        });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    /**
    *Controlles the game ends it if a winner or a tie is found
    * @param height
    * @param width
    * @return panel
    */
    private void checkGameState(){
        int selection= 0;
        String whichPlayer; 
        JOptionPane gameOver = new JOptionPane();
        if(game.isWinner()){
            whichPlayer = game.whoWon();
            selection = gameOver.showConfirmDialog(null,
            String.format("Well done! Player %s won", whichPlayer), "PlayAgain?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }else if (game.isTie()) {
            selection = gameOver.showConfirmDialog(null,
            "Its a tie", "PlayAgain?", JOptionPane.YES_NO_OPTION);
            if(selection == JOptionPane.NO_OPTION){
                root.start();
            } else{
                newGame();
            }
        }
    
    }   
    
    /**
    *Updates the view of the buttons according to the input(what is currently in the board)
    */
    protected void updateView(){
        //update the labels on the buttons according to the model
        for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }

    }   
    /**
    *For start starting a new game
    */
    protected void newGame(){
        game.newGame();
        updateView();
    }
    
    /**
    *For setting the correct next palyer and for setting X or O here according to which player is playing
    * @param e
    */
    private void setPlayer(ActionEvent e){
        
        //send input to game and update view
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));

        if (game.takeTurn(clicked.getAcross(), clicked.getDown(),game.getCurrentPlayer())) {
            clicked.setText(game.getCurrentPlayer());
        }

        if (game.getCurrentPlayer() == "X") {
            game.setCurrentPlayer("O");
        }else if (game.getCurrentPlayer() == "O") {
            game.setCurrentPlayer("X");
        }
    }
}
