package tictactoe;

import java.util.Iterator;
/**
* This class for the controller for the numerical tictactoe game, it does all of the backend date handling 
* for it 
* @author Shrina
*/
public class NumTicTacToe extends boardgame.BoardGame implements boardgame.Saveable{
    private String currentPlayer = "1"; 
                
    //Constructor for the class
    public NumTicTacToe(int width, int height){
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
   *For making sure that the input is odd numbers for palyer "1" and even for player "2" and setting the
   *board with the value the player inputed.
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
                if ((input == null) || (input == "")) {
                    return false;
                }else if (currentPlayer == "1") {
                   if (Integer.valueOf(input) % 2 != 0) {
                        setValue(across,down,input);
                        return true;
                    }
                } else if (currentPlayer == "2") {
                    if (Integer.valueOf(input) % 2 == 0) {
                        setValue(across,down,input);
                        return true;
                    }
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
        //check that input is a digit between 1-9
        // setValue(across,down,String.valueOf(input));
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
    * Checks if there is a winner and calls winHorizontal, winVertical, winDiagonal1 and winDiagonal2
    * to check if a winner is there 
    * @return boolean if there is a winner it returns true and if not it returns false
    */
    boolean isWinner() {
        boolean horizWin = winHorizontal();
        boolean vertWin = winVertical();
        boolean diag1 = winDiagonal1();
        boolean diag2 = winDiagonal2();
    
        if (horizWin) {
            return true;
        }else if (vertWin) {
            return true;
        }else if (diag1) {
            return true;
        }else if (diag2) {
            return true;
        }
        return false;
    }
   
    /**
    * For making sure the right winner (palyer) is returned
    * @return returnString
    */
    public String whoWon() {
        String returnString = "1";

        if (currentPlayer == "1") {
            returnString = "2";
        }else if (currentPlayer == "2") {
            returnString = "1";
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
    * Checks for a horizontal win
    * @return boolean if horizontal win is there returns true if not false 
    */
    public boolean winHorizontal() {
        int getVal1;
        int getVal2;
        int getVal3;
        for (int i = 1; i < 4; i++) {
            if (this.getCell(i,1) != " " && this.getCell(i,2) != " " && this.getCell(i,3) != " "){
                getVal1 = Integer.valueOf(this.getCell(i,1));
                getVal2 = Integer.valueOf(this.getCell(i,2));
                getVal3 =  Integer.valueOf(this.getCell(i,3));
                if ((getVal1 + getVal2 + getVal3) == 15) {
                    return true;
                }
            }
        }

        return false;
    }
    
    /**
    * Checks for a vertical win
    * @return boolean if vertical win is there returns true if not false 
    */
    public boolean winVertical(){
        int getVal1;
        int getVal2;
        int getVal3;
        for (int i = 1; i < 4; i++) {
            if (this.getCell(1,i) != " " && this.getCell(2,i) != " " && this.getCell(3,i) != " "){
                getVal1 = Integer.valueOf(this.getCell(1,i));
                getVal2 = Integer.valueOf(this.getCell(2,i));
                getVal3 =  Integer.valueOf(this.getCell(3,i));
                if ((getVal1 + getVal2 + getVal3) == 15) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * Checks for a diagonal downward win
    * @return boolean if diagonal downward win is there returns true if not false 
    */
    public boolean winDiagonal1(){
        int getVal1;
        int getVal2;
        int getVal3;
        if (this.getCell(1,1) != " " && this.getCell(2,2) != " " && this.getCell(3,3) != " "){
            getVal1 = Integer.valueOf(this.getCell(1,1));
            getVal2 = Integer.valueOf(this.getCell(2,2));
            getVal3 =  Integer.valueOf(this.getCell(3,3));
            if ((getVal1 + getVal2 + getVal3) == 15) {
               return true;
            }
        }
        return false;
    }
   
    /**
    * Checks for a diagonal upward win
    * @return boolean if diagonal upward win is there returns true if not false 
    */
    public boolean winDiagonal2() {
        int getVal1;
        int getVal2;
        int getVal3;
        if (this.getCell(1,3) != " " && this.getCell(2,2) != " " && this.getCell(3,1) != " "){
            getVal1 = Integer.valueOf(this.getCell(1,3));
            getVal2 = Integer.valueOf(this.getCell(2,2));
            getVal3 =  Integer.valueOf(this.getCell(3,1));
            if ((getVal1 + getVal2 + getVal3) == 15) {
                return true;
            }
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
        return 1;  
    }
}