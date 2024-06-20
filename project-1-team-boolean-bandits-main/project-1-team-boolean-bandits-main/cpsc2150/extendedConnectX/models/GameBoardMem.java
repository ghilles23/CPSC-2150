package cpsc2150.extendedConnectX.models;
import java.util.*;
//Constructs a hashmap to make a memory efficient gameboard 
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private Map<Character, List<BoardPosition>> board;
    private int numRows;
    private int numColumns;
    private int numToWin;
    private Character player;
    private BoardPosition boardPositions;

    public static int MAX_COL = 100;
    public static int MAX_ROW = 100;
    public static int MAX_NUMTOWIN = 25;
    public static int MIN_COL = 3;
    public static int MIN_ROW = 3;
    public static int MIN_NUMTOWIN = 3;
    
    /** getNumRowss Contracts and JavaDocs
     * Returns the number of rows in the board
     * @pre none
     * @post getNumRows = [the Max about of rows in the gameboard and returns it].
     *
     */
    public int getNumRows(){
        return numRows;
    }
    /** getNumColumns Contracts and JavaDocs
     * Returns the number of columns in the board
     * @pre none
     * @post getNumColumns = [the Max ammout of columns in the gameboard and returns it].
     *
     */
    public int getNumColumns(){
        return numColumns;
    }
  /** getNumToWin Contracts and JavaDocs
     * Returns the number to record a win
     * @pre numToWin = custom Win Num instances in a row
     * @post getNumToWin = numToWin
     */
    public int getNumToWin(){
        return numToWin;
    }
     /** getNumToWin Contracts and JavaDocs
     * Returns the number to record a win
     * @pre numToWin = five instances in a row
     * @post getNumToWin = numToWin
     */
    public Character getPlayer(){
        return player;
    }

    public BoardPosition getBoardPosition(){
        return boardPositions;
    }

/** GameboardMem Constructor Contracts and JavaDocs
     *Constructs a GameBoard hashmap
     * @param [row int type] AND [col int type] AND [numberToWin number of tokens in a row to win; int type]
     * @param [player character that represnts player tokens; Character type] 
     * @param [boardPositions BoardPosition type and functions]
     * @pre None
     * @post [board hashmap of Character keys and List of BoardPosition values] AND [numToWin = numberToWin]
     * @post [numRows = rows] AND [numColumns = col]
     * @return None
     */
public GameBoardMem(int row, int col, int numberToWin){
    board = new HashMap<Character, List<BoardPosition>>();
    this.numToWin =  numberToWin;
    numRows = row;
    numColumns = col;
}

/** isPlayerAtPos Contracts and JavaDocs
     *Overridden function for a hashmap implementation to check if a player is at a specified position
     * @param [pos position of token] AND [player character representing player to be checked]
     * @pre none
     * @post none
     * @return [if player position is not empty and pos position is in player's position list return true; otherwise rturn false]
     */

 @Override
 public boolean isPlayerAtPos(BoardPosition pos, char player)
 {
    List<BoardPosition> playerPos = board.get(player);

    if(playerPos != null && playerPos.contains(pos)){
        return true;
    }
    else{
        return false;
    }
         /*returns true if the player is at pos; otherwise, it returns false. Note: this method will be implemented very
     similarly to whatsAtPos, but it's asking a different question. We only know they will be similar because we
     know GameBoard will contain a 2D array. If the data structure were to change in the future,
     these two methods could be radically different.*/
     

 }

 /** dropToken Contracts and JavaDocs
     *Overridden function for a hashmap implementation to drop a token at a specific position
     * @pre none
     * @post [board now has token placed in specified position]
     * @return if position is not empty and if key exists place a token and return; otherwise ...
     */
 @Override
 public void dropToken(char p, int c){
    if (checkIfFree(c) == true){
        for (int i = 0; i < numRows; i++){
            BoardPosition bp = new BoardPosition(i, c);

            if(whatsAtPos(bp) != ' '){
                continue;
            }
            List<BoardPosition> positions = board.get(p);
            if(positions == null){
                positions = new ArrayList<>();
            }
            positions.add(bp);
            board.put(p, positions);
            return;
                }
            }
        }

 /** whatsAtPos Contracts and JavaDocs
     *Overridden function for a hashmap implementation to check what character token is in a specified position\
     * @param [pos boardposition to be checked]
     * @pre none
     * @post none
     * @return [if position has matching token then return token otherwise return a blank character]
     */

 @Override
 public char whatsAtPos(BoardPosition pos){
    for (Map.Entry<Character, List<BoardPosition>> entry: board.entrySet())
        {
            for(BoardPosition position : entry.getValue()){
                if(pos.equals(position)){
                    return entry.getKey();
                }
                
                }
            }
            
            return ' ';
        }
    }      


