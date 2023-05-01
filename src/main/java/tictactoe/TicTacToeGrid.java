package tictactoe;

/**
* This class is for the managing the grid for both tictactoe and numerical tictactoe games
* @author Shrina
*/
import boardgame.Grid;
import java.util.Iterator;

public class TicTacToeGrid extends boardgame.Grid{
    private String line = null;
    private Character cellWall = '|';
    private static int cellWidth = 4;
    
    //Constructor
    public TicTacToeGrid(int width, int height){
            super(width,height);
            makeGridLine();
    }
    
    /**
    * For making the grid lines 
    * @param this.game
    */
    private void makeGridLine(){
        line = "";
        for(int j=0; j<getWidth()*cellWidth; j++){
            line +="-";
        }
    }
    
    /**
    * For turning the grid into a string, in Grid.java which this class extends to
    * @return toPrint
    */
    @Override
    public String getStringGrid(){
        if(line == null){
            makeGridLine();
        }
        Iterator<String> iter = iterator();
        String toPrint ="";
        int i=0;
        String cell;
        while(iter.hasNext()){
            cell = iter.next();
            toPrint = toPrint + " "+ cell+ cellWall;
            i++;
            if(i == getWidth()){
                toPrint = toPrint + "\n" + line + "\n";
                i = 0;
            }

        }
        return toPrint;
    }

   /**
    * Here so it does not break the code its does not break the code is used in another class
    * @return toPrint
    */
    public void parseStringIntoBoard(String toParse){
        int lenStr = toParse.length();
    }
}
