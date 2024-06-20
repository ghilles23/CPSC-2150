package cpsc2150.extendedConnectX.models;

/*@authors Breanna Mackey BreannaMackey, Trent Brown Trentbrown1, Gabriel Hillesheim ghilles23
 * @version 1.0
 * Constructs a GameBoard Object that is to be built upon through various methods.
 */

public class GameBoard extends AbsGameBoard
{

    public int numRows;
    public int numColumns;
    public int numToWin;
    private char[][] gameBoard;
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
     * @pre numToWin = five instances in a row
     * @post getNumToWin = numToWin
     */
    public int getNumToWin(){
        return numToWin; 
    }
    /** GameBoard Constructor Contracts and JavaDocs
     *Constructs a GameBoard object
     * @pre none
     * @post [self is initialized to a char array with all the values set to ' ']
     * @return None
     */
    public GameBoard(int rows, int columns, int numToWin)
    {
        numRows = rows;
        numColumns = columns;
        this.numToWin = numToWin;
        gameBoard = new char [MAX_ROW][MAX_COL];
        for (int i = 0; i <= MAX_ROW - 1; i++){
            for(int j = 0; j <= MAX_COL - 1 ; j++){
                gameBoard[i][j] = ' ';
            }

        }

    }

    /** dropToken Contracts and JavaDocs
     * Drops a token in a selected column
     *
     * @param p [player that is currently playing represented by a character]
     * @param c [column selected, int type]
     *
     * @pre MIN_COL <= c <= MAX_COL
     * @pre [player should not be represented by an empty character]
     * @pre [if checkIfFree returns true then drop token]
     * @post dropToken = [current player character column c of the new token at the lowest available row and the rest of the board remained the same AND p = #p].
     *
     */
    public void dropToken(char p, int c)
    {
        //places the character p in column c. The token will be placed in the lowest available row in column c.

        if(checkIfFree(c)){
        for(int row = 0; row < getNumRows(); row++){
            if(gameBoard[row][c] == ' '){
                gameBoard[row][c] = p;
                break;
            }
        }
        }
     }

    /** WhatsAtPos Contracts and JavaDocs
     * Checks if there is 5 in a vertical line win
     *
     * @param pos [position of token based on BoardPosition]
     * @pre None
     * @post whatsAtPos = [Returns ' ' AND self = #self]
     * @return [if pos = a character it returns the characer at its current position if not it returns a blank space]
     *
     */
    public char whatsAtPos(BoardPosition pos)
    {
        //returns what is in the GameBoard at position pos If no marker is there, it returns a blank space char.
        int row = pos.getRow();
        int col = pos.getColumn();
        return gameBoard[row][col];
        
    }


    

    }
