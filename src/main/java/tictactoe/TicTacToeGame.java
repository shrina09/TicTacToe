package tictactoe;

import java.util.Iterator;

/**
* This class for the controller for the tictactoe game, it does all of the backend date handling 
* for it 
* @author Shrina
*/

public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    private String currentPlayer = "X"; 
                
    //Constructor for the class
    public TicTacToeGame(int width, int height){
        super(width,height);
        setGrid(new TicTacToeGrid(width,height));

    }
    
   /**
   * Setter for currentPlayer private String variable 
   * @param playerCurrent
   */   
    public void setCurrentPlayer(String playerCurrent) {
        currentPlayer = playerCurrent;
    }
   
   /**
   *Getter for currentPlayer private String variable 
   * @return currentPlayer
   */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

   /**
   *For starting a new game 
   */
    @Override
    public void newGame(){
        super.newGame();
    }
    
   /**
   *For making sure the input is the correct current player
   *It also makes sure that across and down parameters (the positions) are within the bounds of the board
   *If the conditions above described are meet it returns true if not returns false
   * @param across
   * @param down
   * @param input
   * @return boolean
   */
    @Override
    public boolean takeTurn(int across, int down, String input){
        if (across >=1 && across <= 3){
            if (down >=1 && down <= 3) {
                if (currentPlayer == input) {
                    setValue(across,down,input);
                    return true;
                }
            }   
        }
        
       return false;
    }
    
   /**
   * This was in the code in the Kakuro (the example provided), left it here so it does not mess up the game
   * It is also in the boardGame class which this class extends to
   * @param across
   * @param down
   * @param input
   * @return boolean
   */
    @Override
    public boolean takeTurn(int across, int down, int input){
        return true;
    }
    
    /**
    * This was in the code in the Kakuro (the example provided), left it here so it does not mess up the game
    * It is also in the boardGame class which this class extends to
    * @return boolean
    */
    @Override
    public boolean isDone(){
        return false;
    }
    
    /**
    * Checks if there is a winner 
    * @return boolean if there is a winner it returns true and if not it returns false
    */
    boolean isWinner() {
        boolean winner = false;
        //horizontal
        for (int i = 1; i < 4; i++) {
            if (this.getCell(i,1) == this.getCell(i,2) && this.getCell(i,1) == this.getCell(i,3) 
            && this.getCell(i,1) != " ") {
                winner = true;
            }
        }
        //vertical
        for (int i = 1; i < 4; i++) {
            if (this.getCell(1,i) == this.getCell(2,i) && this.getCell(1,i) == this.getCell(3,i) 
            && this.getCell(1,i) != " ") {
                winner = true;
            }
        }
        //diagonal 1
        if (this.getCell(1,1) == this.getCell(2,2) && this.getCell(1,1) == this.getCell(3,3) 
        && this.getCell(1,1) != " ") {
            winner = true;
        }
        //diagonal 2
        if (this.getCell(1,3) == this.getCell(2,2) && this.getCell(1,3) == this.getCell(3,1) 
        && this.getCell(1,3) != " ") {
            winner = true;
        }

        return winner;
    }
    
    /**
    * For making sure the right winner (palyer) is returned
    * @return returnString
    */
    public String whoWon() {
        String returnString = "X";

        if (currentPlayer == "X") {
            returnString = "O";
        }else if (currentPlayer == "O") {
            returnString = "X";
        }
        return returnString;
    }
    
    /**
    * Checks for a tie
    * @return boolean if tie is there returns true if not false 
    */
    public boolean isTie() {
        if (this.getCell(1,1)!= " " && this.getCell(1,2)!= " " &&this.getCell(1,3)!= " "
        &&this.getCell(2,1)!= " " && this.getCell(2,2)!= " " && this.getCell(2,3)!= " "   
        &&this.getCell(3,1)!= " " &&this.getCell(3,2)!= " " &&this.getCell(3,3)!= " ") {

            return true;
        }
        return false;
    }
    
    /**
    * Here so it does not break the program it is part of boardGame.java which this class extends to
    * @return "You Won"
    */
    @Override
    public String getGameStateMessage(){
        return "You Won!"; 
    }
    
    /**
    * Converts the board into a string
    * @return board
    */
    @Override
    public String getStringToSave(){
        String board = getCell(1,1) + "," + getCell(1,2) + "," + getCell(1,3) + "\n"
                       +getCell(2,1) + "," + getCell(2,2) + "," + getCell(2,3) + "\n"
                       +getCell(3,1) + "," + getCell(3,2) + "," + getCell(3,3) + "\n"; 

        
        return board;
    }
    
    /**
    * Here so it does not break the program it is part of saveable.java which this class implements to
    * (saveable interface)
    */
    @Override
    public void loadSavedString(String saved){
        
        TicTacToeGrid myGrid = (TicTacToeGrid)getGrid();  

        myGrid.parseStringIntoBoard(saved);
    }
    
    /**
    * Here so it does not break the program it is part of boardGame.java which this class extends to
    * @return 1
    */
    @Override
    public int getWinner(){
        return 1;  //there is only one player in kakuro
    }






}
