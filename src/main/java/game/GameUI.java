package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import tictactoe.TicTacToeView;
import tictactoe.NumTicTacToeView;
import tictactoe.TicTacToeGame;
import tictactoe.NumTicTacToe;

/**
* This game is for the user interface, it is like the main function that has everything that ties the GUI
* together and also has the main method to run the game
* @author Shrina
*/
public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;

    public GameUI(String title){
        // call the superclass constructor
        super();    
        // set the size, title and default close of the jframe
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(title);
        makeMenu();
        setJMenuBar(menuBar);
        gameContainer = new JPanel();

        gameContainer.setBackground(Color.pink);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // make a new label to store messages
        
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(),BorderLayout.EAST);
        start();

    }
   /**
   *Adds the tic tac toe and numerical tic tac toe buttons to the pannel (the homepage)
   * @return buttonPanel
   */
    private JPanel makeButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeTicTacToeButton());
        buttonPanel.add(makeNumTTTButton());
        return buttonPanel;
    }
    
    /**
   * Makes the homePage of the game and returns the pannel
   * @return temp
   */
    private JPanel startupMessage(){
        JPanel temp = new JPanel();
        temp.add(new JLabel("Tictactoe or numerical tictactoe!"));
        return temp;

    }
   
   /**
   * Makes the Play TicTacToe button
   * @return button
   */
    private JButton makeTicTacToeButton(){
        JButton button = new JButton("Play Tictactoe");
        button.addActionListener(e->ticTacToe());
        button.setBackground(Color.gray);
        button.setOpaque(true);
        return button;
    }
   
   /**
   * Makes the Play Num Tictactoe button
   * @return button
   */
    private JButton makeNumTTTButton(){
        JButton button = new JButton("Play Num Tictactoe");
        button.addActionListener(e->numTicTacToe());
        
        button.setBackground(Color.gray);
        button.setOpaque(true);
        return button;
    }

   /**
   * Makes the menu for the giving the option to add a file name to save the current state of the game
   */
    public void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("A submenu");
        JMenuItem item = new JMenuItem("Add file");
        menu.add(item);
        menuBar.add(menu);
        item.addActionListener(e->saveSomething());

    }
    /**
    * For starting the game (gets everything set up)
    */
    public void start(){
        gameContainer.removeAll();
        gameContainer.add(startupMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    
    /**
    * For saving both the numerical tictactoe and tictactoe into the same file
    */
    protected void saveSomething(){
        String fileName = JOptionPane.showInputDialog("Enter the file");
        try{
            try{
                File csvWriter = new File(fileName);
                FileWriter write = new FileWriter(csvWriter);
                
                TicTacToeGame saveIt1 = new TicTacToeGame(3,3);
                String boardSave1 = saveIt1.getStringToSave();
                write.write(boardSave1);

                NumTicTacToe saveIt2 = new NumTicTacToe(3,3);
                String boardSave2 = saveIt2.getStringToSave();
                write.write(boardSave2);
                
                write.close();
            }catch (NullPointerException eme) {
                JOptionPane.showMessageDialog(null,"please save the board when there are players",
                "Entered a wrong file", JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(IOException exe){
           JOptionPane.showMessageDialog(null,"please try again and enter a vaild file",
            "Entered a wrong file", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    /**
    * For creating the tictactoe game page
    */
    protected void ticTacToe(){
        gameContainer.removeAll();
        gameContainer.add(new TicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    
    /**
    * For creating the numerical Tictactoe game page
    */
    protected void numTicTacToe(){
        gameContainer.removeAll();
        gameContainer.add(new NumTicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
     
    }

    //Main method
    public static void main(String[] args){
        GameUI example = new GameUI("Games");
        example.setVisible(true);
    }
} 