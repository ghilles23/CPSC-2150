package cpsc2150.extendedConnectX.models;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Gabriel Hillesheim ghilles23 | Trent Brown trentbrown1 | Breanna Mackey BreannaMackey


 */ 
    /**
    * @invariant 
    * GameBoard.MIN_ROW <= Row <= GameBoard.MAX_ROW AND GameBoard.MIN_COL <= Column <= GameBoard.MAX_COL AND Player Token =! ' '
    */

public class BoardPosition
{
    private int Row;
    private int Column;

    /**
    * Constructs a BoardPosition Object with a location of row and column that will be applied to players.
    *
    * @param aRow [an int showing what row]
    * @param aColumn [an int showing what column]
    * @pre 0 <= Column <= GameBoard.MAX_COLm
    * @pre 0 <= Row <= GameBoard.MAX_ROW
    * @post Row = aRow
    * @post Column = aColumn
    *
    */
    public BoardPosition(int aRow, int aColumn)
    {
        //parameterized constructor for BoardPosition
        Row = aRow;
        Column = aColumn;
    }

    /**
    * Returns the row at which the player is set
    *
    * @return aRow, [the int representation of a row]
    * @pre None
    * @post getRow = Row AND row = #row AND col = #col
    */
    public int getRow()
    {
        //returns the row
        return Row;
    }

    /**
    * Returns the Column that the player is on
    * 
    * @return aColumn, [the int representation of a column]
    * @pre None
    * @post getColumn = Column AND row = #row AND col = #col
    */
    public int getColumn()
    {
        //returns the column
        return Column;
    }
    
    /**
    * Compares this BoardPostion object with another object to check for equality
    *
    * @param obj [The object to compare with this BoardPosition]
    * @return [True if the object 1 of row is equal to object 2 of row and object1 of column is equal to object 2 of column. False if they are not] 
    * @pre None
    * @post equals  = this.Row == obj.Row && this.Column == obj.Column AND row = #row AND col = #col
    *
    */
    @Override
    public boolean equals(Object obj)
    {
     if(this == obj){ //check if object is being compared to the same object
        return true; //the objects are the same 
    }

    if(obj == null || getClass() != obj.getClass()){ //check if the objects are different or null
        return false;
     }
     BoardPosition newPosition = (BoardPosition) obj; //creates an empty BoardPosition to record
     return this.Row == newPosition.Row && this.Column == newPosition.Column;
     }
    
    /** 
    * Returns a string of the BoardPostition object
    *
    * @return a string containing the row and column 
    * @pre None 
    * @post toString = "<row>, <column>" AND [Row = #Row AND Column = #Column]
    *
    */
    @Override
    public String toString()
    {

        return Row + ", " + Column;

    }
}
