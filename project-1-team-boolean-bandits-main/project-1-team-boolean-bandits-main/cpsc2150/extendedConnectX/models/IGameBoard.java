package cpsc2150.extendedConnectX.models;

public interface IGameBoard{
    // Primary methods 
    /** getNumRowss Contracts and JavaDocs
     * Returns the number of rows in the board
     * @pre none
     * @post getNumRows = [the Max about of rows in the gameboard and returns it].
     *
     */
    public int getNumRows();
   
    /** getNumColumns Contracts and JavaDocs
     * Returns the number of columns in the board
     * @pre none
     * @post getNumColumns = [the Max ammout of columns in the gameboard and returns it].
     *
     */
    public int getNumColumns();
   
    /** getNumToWin Contracts and JavaDocs
     * Returns the number to record a win
     * @pre numToWin = five instances in a row
     * @post getNumToWin = numToWin
     */
    public int getNumToWin();
    
    /** dropToken Contracts and JavaDocs
     * Drops a token in a selected column
     *
     * @param p [player that is currently playing represented by a character]
     * @param c [column selected, int type]
     *
     * @pre 0 <= c <= MAX.COL
     * @pre [player should not be represented by an empty character]
     * @pre [if checkIfFree returns true then drop token]
     * @post dropToken = [current player character column c of the new token at the lowest available row and the rest of the board remained the same AND p = #p].
     *
     */
    public void dropToken(char p, int c);
    
    /** WhatsAtPos Contracts and JavaDocs
     * Checks if there is 5 in a vertical line win
     *
     * @param pos [position of token based on BoardPosition]
     * @pre None
     * @post whatsAtPos = [Returns ' ' AND self = #self]
     * @return [if pos = a character it returns the characer at its current position if not it returns a blank space]
     *
     */

    public char whatsAtPos(BoardPosition pos);
    //Secondary methods
    
        /** checkIfFree Contracts and JavaDocs
     * Checks if column has available space to accept another token
     *
     * @param [Check if there is availible space for c column]
     *
     * @pre 0 <= c <= MAX.COL
     * @post checkIfFree = [returns true if there is space for another token to be placed and false if there is not space for another token. The top-most row for the column contains a blank space AND boardLength = #boardLength]
     * 
     */
    public default boolean checkIfFree(int c)
    { 
        
        //returns true if the column can accept another token; false otherwise.
            
        for(int i = 0; i < getNumRows(); i++){
            BoardPosition pos = new BoardPosition(i, c);
            if(whatsAtPos(pos) == ' '){
                return true;
            }
            }
            return false;  
        } 
        
 
 
    //DEVCOM: if current column is less than MAX_COL return true else false
    
    /** checkForWin Contracts and JavaDocs
    *Checks if the player won after placing token
    *
    * @param c [column position that will be checked for a player win as int type]
    *
    * @pre 0 <= c <= MAX.COL
    * 
    * @post checkForWin = [if last token == last token placed to a consecutive win for vertical, horizontal, or diagonal win AND self = #self]
    * @return [returns true if the last token placed in column c results in a win false if not]
     */
    public default boolean checkForWin(int c)
    {
        /*this function will check to see if the last token placed in column c resulted in the player winning the game.
        If so it will return true, otherwise false. Note: this is not checking the entire board for a win, it is just
        checking if the last token placed results in a win. You may call other methods to complete this method */

        for(int i = 0; i < getNumColumns(); i++){
            BoardPosition newpos = new BoardPosition(i, c);
            if(whatsAtPos(newpos) != ' '){
                if(checkHorizWin(newpos, whatsAtPos(newpos)) ||checkVertWin(newpos, whatsAtPos(newpos)) || checkDiagWin(newpos, whatsAtPos(newpos))){
                    return true; //Return true if any win condition is met
                }
            }
        }return false;
        }
    

    /** checkTie Contracts and JavaDocs
    *Checks for a tie between players
    * @param None
    * @pre None
    * @post checkTie = [returns true if game all gameboard positions are filled and checkForWin function is false, otherwise returns false]
    * @return [returns true if all game board positions are filled]
     */
    public default boolean checkTie()
    {
        /*this function will check to see if the game has resulted in a tie. A game is tied if there are no free board
        positions remaining. You do not need to check for any potential wins because we can assume that the players
        were checking for win conditions as they played the game. It will return true if the game is tied and
        false otherwise.*/ 
        if(!checkForWin(getNumColumns())){
        for(int i = 0; i < getNumRows(); i++){
            for(int j = 0; j < getNumColumns(); j++){
                if(whatsAtPos(new BoardPosition(i, j)) == ' '){ //If there is a remaining blank character return false
                    return false;
                }
            }
        }}
        return true;
    }

        
    /* checkHorizWin Contracts and JavaDocs
    * Checks if there is a horizontal line win based on numToWin input
    *
    * @param pos [position of token based on BoardPosition]
    * @param p [player as character type]
    * @pre p =! ' '
    *
    * @post checkHorizWin = [if last token placed makes numToWin in a row horizontally return true, otherwise return false] AND self = #self
    *
    * @return [returns true if there is a horizontal win based on numToWin input otherwise returns false]
    *
    *
    */

    public default boolean checkHorizWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in numToWin input in
        a row horizontally. Returns true if it does, otherwise false*/

        //check left and right for tokens 
        
        int row = pos.getRow();
        int column = pos.getColumn();
        int numToWin = getNumToWin();
        int Lcount = -1; // Left counter set to -1 to account for the final token being placed in the middle
        for(int i = 0; i < numToWin; i++){ //check same token left
            int LCol = column - i;
            if(LCol >= 0 && whatsAtPos(new BoardPosition(row, LCol)) == p){//ensure boundary
                Lcount++; // count to be added to compare with numToWin
            }
            else{
                break;
            }
        }
       
        int Rcount = 0; //Right counter set to zero
        for(int i = 0; i < numToWin; i++){ //check for same token right
                int RCol = column + i;
                if(RCol < getNumColumns() && whatsAtPos(new BoardPosition(row, RCol)) == p){
                    Rcount++; //count to be added to compare with numToWin
            }
            else{
                break;
            }
           
        }

        if((Rcount + Lcount) >= numToWin){ // check for total count being equal or greater than win
            return true;
        }
        else{
            return false;
        }

            


    }


    
    /* checkVertWin Contracts and JavaDocs
     *Checks if there is 5 in a vertical line win
     *
     * @param pos [position of token based on BoardPosition]
     * @param p [player as character type]
     *
     * @pre p =! ' ' [character p cannot equal a blank space]
     *
     * @post checkVertWin = [if last token placed makes 5 in a row vertically return true, otherwise return false] AND self = #self
     * @return [If there is a vertical win return true, otherwise return false]
     */
    public default boolean checkVertWin(BoardPosition pos, char p)
    {
        /*checks to see if the last token placed (which was placed in position pos by player p) resulted in 5 in a row
        vertically. Returns true if it does, otherwise false*/
        int row = pos.getRow();
        int column = pos.getColumn();
        int numToWin = getNumToWin();
        int count = 0;

                //only need to check down for vert
                    for(int i = 0; i < getNumRows(); i++){ // iterate down the column
                        if(whatsAtPos(new BoardPosition(i, column)) == p){
                            count++;
                           if(count == numToWin){
                            return true;
                           }
                        }
                        else{
                            count = 0;
                        }
                    }
        
                 return false;
            
        }
        
        


    
        /* checkDiagWin Contracts and JavaDocs
     * Checks if there is 5 in a diagonal line win
     *
     * @param pos position of token based on BoardPosition
     * @param p player as character type
     *
     * @pre p =! ' ' [character p cannot equal a blank space]
     *
     * @post checkDiagWin = [Returns ' ' AND self = #self]
     * @return [True if there is a diagonal win, otherwise retrun false]
     */
    public default boolean checkDiagWin(BoardPosition pos, char p)
    {
        int numToWin = getNumToWin();
        int row = pos.getRow();
        int col = pos.getColumn();
            int[][] directions = {
                {1, 1},  // bottom-right
                {1, -1}, // bottom-left
                {-1, 1}, // top-right
                {-1, -1} // top-left
            };
        
            for (int[] dir : directions) {
                int counter = 1;
                
            
                for (int i = 1; i < numToWin; i++) {
                    int newRow = row + dir[0] * i;
                    int newCol = col + dir[1] * i;
                    
                    if (newRow >= 0 && newRow < getNumRows() && newCol >= 0 && newCol < getNumColumns()) {
                        if (isPlayerAtPos(new BoardPosition(newRow, newCol), p)) {
                            counter++;
                        } else {
                            break;
                        }
                    } else {
                        break; // Stop if outside the board bounds
                    }
                }
                // Check Downwards Diagonal
                for(int i = 1; i < numToWin; i++){
                    int newRow = row - dir[0] * i; //Checking Backwards Row
                    int newCol = col - dir[1] * i; //Checking Backwards Column
                
                    if(newRow >= 0 && newRow < getNumRows() && newCol >= 0 && newCol < getNumColumns()){
                        if(isPlayerAtPos(new BoardPosition(newRow, newCol), p)){
                            counter ++;
                        }
                            else{
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                
                if (counter == numToWin) {
                    return true; 
                }
                
        
                
    }
    return false;
}

        /* isPlayerAtPos Contracts and JavaDocs
    * @param pos [an object of board position]
    * @param player [The character that the player has chosen]
    * 
    * @pre [player must be a character]
    * @post isPlayerAtPos = [self = #self, The state of GameBoard is unchanged]
    * @return returns true if player is at position false if not
    * 
    */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if(player == '\0'){
            throw new IllegalArgumentException("Player Cannot equal null!");
        }
        /*returns true if the player is at pos; otherwise, it returns false. Note: this method will be implemented very
        similarly to whatsAtPos, but it's asking a different question. We only know they will be similar because we
        know GameBoard will contain a 2D array. If the data structure were to change in the future,
        these two methods could be radically different.*/
        return whatsAtPos(pos) == player;
        

    }

}
