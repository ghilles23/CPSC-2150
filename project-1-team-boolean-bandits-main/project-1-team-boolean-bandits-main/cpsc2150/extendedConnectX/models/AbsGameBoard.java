package cpsc2150.extendedConnectX.models;

import cpsc2150.extendedConnects.GameScreen;

public abstract class AbsGameBoard implements IGameBoard{
    
         /** 
    * Returns a string of the gameBoard object
    * @pre None
    * @post toString = [self = #self, The state of the GameBoard is unchanged]
    * @return [String representing the state of the GameBoard]
    */
    @Override
    public String toString(){
        String connectBoard = "";
        if(getNumColumns() >= 10){
        for(int j = 0; j <= 9; j++){
            connectBoard += "| " + j;
        }
        for(int i = 10; i <= getNumColumns() - 1; i++){
           connectBoard += "|" + i ;
        }}
        else if(getNumColumns() < 10){
                for(int j = 0; j < getNumColumns(); j++){
            connectBoard += "| " + j;
        }}
        connectBoard += "| \n";
         for(int i = getNumRows() - 1; i >= 0; i--){
                connectBoard +="| ";
            for(int j = 0; j <= getNumColumns() - 1; j++){
                    BoardPosition pos = new BoardPosition(i, j);
                    connectBoard += (whatsAtPos(pos)+ "| ");

                }
                connectBoard += "\n";
            
            }
            return connectBoard.toString();
        } 
        
    }