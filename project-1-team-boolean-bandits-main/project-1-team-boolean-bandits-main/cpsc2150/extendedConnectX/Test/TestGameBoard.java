package cpsc2150.extendedConnectX.Test;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TestGameBoard {

    private GameBoard GameBoardFactory(){
        int row = 14;
        int col = 14;
        int numToWin = 7;
        return new GameBoard(row, col, numToWin);
    }

    private GameBoard createGameBoard(){
        GameBoard gb = GameBoardFactory();
        return gb;
    }
    char[][] expected = new char[GameBoardFactory().getNumRows()][GameBoardFactory().getNumColumns()];

    private String helper(char[][] arr){

        String connectBoard = "";
        if(GameBoardFactory().getNumColumns() >= 10){
            for(int j = 0; j <= 9; j++){
                connectBoard += "| " + j;
            }
            for(int i = 10; i <= GameBoardFactory().getNumColumns() - 1; i++){
                connectBoard += "|" + i ;
            }}
        else if(GameBoardFactory().getNumColumns() < 10){
            for(int j = 0; j < GameBoardFactory().getNumColumns(); j++){
                connectBoard += "| " + j;
            }}
        connectBoard += "| \n";
        for(int i = GameBoardFactory().getNumRows() - 1; i >= 0; i--){
            connectBoard += "| ";
            for(int j = 0; j <= GameBoardFactory().getNumColumns() - 1; j++){
                connectBoard += (expected[i][j]+ "| ");

            }
            connectBoard += "\n";

        }
        return connectBoard;
    }

    @Test(timeout=200)
    public void testConstructor_Empty_GameBoard(){
        GameBoard cBoard = createGameBoard();
        //fill expected array with what we want to test
        for (int i = 0; i <= cBoard.getNumRows() - 1; i++) {
            for (int j = 0; j <= cBoard.getNumColumns() - 1; j++) {
                expected[i][j] = ' ';
            }
        }
        String strExpected = helper(expected);

        assertEquals(strExpected,cBoard.toString());
    }


    @Test(timeout=200)
    public void testConstructor_Blank_Character_GameBoard(){
        GameBoard cBoard = createGameBoard();

        for (int i = 0; i <= cBoard.getNumRows() - 1; i++){
            for(int j = 0; j <= cBoard.getNumColumns() - 1 ; j++){
                BoardPosition cPos = new BoardPosition(i,j);
                assertEquals(' ',cBoard.whatsAtPos(cPos));
            }

        }
    }

    @Test(timeout=200)
    public void testConstructor_Different_Sizes(){
        int rows = 7;
        int cols = 7; 
        int NUMSTOWIN = 3;

        GameBoard cBoard = new GameBoard(rows, cols, NUMSTOWIN);
        assertEquals(rows, cBoard.getNumRows());
        assertEquals(cols, cBoard.getNumColumns());
        assertEquals(NUMSTOWIN, cBoard.getNumToWin());
    }

    @Test(timeout=200)
    public void testCheckIfFree_On_Empty_Board(){
        GameBoard cBoard = createGameBoard();


        assertTrue(cBoard.checkIfFree(0));

    }

    @Test(timeout=200)
    public void testCheckIfFree_On_Column_With_Players(){
        GameBoard cBoard = createGameBoard();
        char player = 'X';


        for(int i = 0; i < 2; i++){
            cBoard.dropToken(player, 0);
        }
        assertTrue(cBoard.checkIfFree(0));
    }

    @Test(timeout=200)
    public void testCheckIfFree_On_Full_Column(){
                GameBoard testBoard = createGameBoard();
        char player = 'X';


        for(int i = 0; i < testBoard.getNumRows(); i++){
            testBoard.dropToken(player, 0);
        }
        assertFalse(testBoard.checkIfFree(0));
    }

    @Test(timeout=200)
    public void testCheckHorizWin_On_Empty_Board(){
        GameBoard cBoard = GameBoardFactory();
        BoardPosition pos = new BoardPosition(13, 13);
        assertFalse(cBoard.checkHorizWin(pos, 'X'));
    }

    @Test(timeout=200)
    public void testCheckHorizWin_Win_On_Left_Side(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 0);
        char player = 'X';
        for(int i = 1; i < 7; i++){
            cBoard.dropToken(player, i);
        }
        cBoard.dropToken(player, 0);
        assertTrue(cBoard.checkHorizWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckHorizWin_Win_On_Right_Side(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 7);
        char player = 'X';
        for(int i = 0; i < 7; i++){
            cBoard.dropToken(player, i);
        }
        cBoard.dropToken(player, 7);
        assertTrue(cBoard.checkHorizWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckHorizWin_Final_Token_Placed_In_Middle(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 3);
        char player = 'X';
        for(int i = 0; i < 3; i++){
            cBoard.dropToken(player, i);
        }
        for(int j = 4; j <= 7; j++){
            cBoard.dropToken(player, j);
        }
        cBoard.dropToken(player, 3);
        assertTrue(cBoard.checkHorizWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckVertWin_No_Vert_Win_Misc_Players(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(3, 1);
        char player = 'X';
        char player2 = 'O';
        for(int i = 0; i < 4; i++){
            cBoard.dropToken(player, 1);
        }
        for(int j = 0; j < 3; j++){
            cBoard.dropToken(player2, 1);
        }
        assertFalse(cBoard.checkVertWin(pos, player));

    }

    @Test(timeout=200)
    public void testCheckVertWin_At_Bottom_Of_Column(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 1);
        char player = 'X';
        for(int i = 0; i < 8; i++){
            cBoard.dropToken(player, 1);
        }
        assertTrue(cBoard.checkVertWin(pos, player));

    }

    @Test(timeout=200)
    public void testCheckVertWin_Amidst_Characters_In_Middle_Column(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(10, 7);
        char player = 'X';
        char player2 = 'O';

        cBoard.dropToken(player, 7);
        cBoard.dropToken(player2, 7);
        cBoard.dropToken(player, 7);
        cBoard.dropToken(player2, 7);
        for(int i = 0; i < 8; i++){
            cBoard.dropToken(player2, 7);
        }
        assertTrue(cBoard.checkVertWin(pos, player2));
    }

    @Test(timeout=200)
    public void testCheckVertWin_At_Top_Of_Board_Last_Col(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(14, 14);
        char player = 'X';
        char player2 = 'O';

        cBoard.dropToken(player, 14);
        cBoard.dropToken(player2, 14);
        cBoard.dropToken(player, 14);
        cBoard.dropToken(player2, 14);
        cBoard.dropToken(player, 14);
        cBoard.dropToken(player2, 14);
        cBoard.dropToken(player, 14);
        
        for(int i = 0; i < 8; i++){
            cBoard.dropToken(player2, 14);
        }
        assertTrue(cBoard.checkVertWin(pos, player2));

    }

    @Test(timeout=200)
    public void testCheckDiagWin_False_Empty_Board(){
        GameBoard cBoard = GameBoardFactory();
        BoardPosition pos = new BoardPosition(0, 0);
        char player = 'X';
        assertFalse(cBoard.checkDiagWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckDiagWin_Up_and_Right_Last_placed_Top(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(7, 7);
      char player = 'X';
      for(int i = 0; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 1; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 2; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 3; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 4; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 5; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 6; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 7);
      assertTrue(cBoard.checkDiagWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckDiagWin_When_Placed_In_Middle(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(7, 7);
      char player = 'X';
      for(int i = 0; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 1; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 2; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 4; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 4; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 5; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 6; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 3);
      assertTrue(cBoard.checkDiagWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckDiagWin_Final_Token_In_Corner(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(7, 7);
      char player = 'X';
      for(int i = 1; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 1; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 2; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 3; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 4; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 5; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 6; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 0);
      assertTrue(cBoard.checkDiagWin(pos, player));

    }

    @Test(timeout=200)
    public void testCheckDiagWin_In_Down_Right_Direction_Last_Placed_Top(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(6, 0);
      char player = 'X';
      for(int i = 1; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 1; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 2; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 3; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 4; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 5; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 6; i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 0);
      assertTrue(cBoard.checkDiagWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckDiagWin_Down_Right_Last_Token_Middle(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(3, 3);
      char player = 'X';
      for(int i = 0; i < cBoard.getNumToWin(); i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 1; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 2; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 3; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 3; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 5; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 6; i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 3);
      assertTrue(cBoard.checkDiagWin(pos, player));
    }

    @Test(timeout=200)
    public void testCheckDiagWin_Down_Right_Last_Placed_Bottom(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(0, 6);
      char player = 'X';
      for(int i = 1; i < cBoard.getNumToWin() - 1; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 1; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 2; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 3; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 4; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 5; i++){
        cBoard.dropToken(player, i);
      }
      for(int i = 0; i < cBoard.getNumToWin() - 6; i++){
        cBoard.dropToken(player, i);
      }
      cBoard.dropToken(player, 0);
      cBoard.dropToken(player, 7);
      assertTrue(cBoard.checkDiagWin(pos, player));
    }
    

    @Test(timeout=200)
    public void testCheckTie_With_Empty_Board(){
        GameBoard cBoard = createGameBoard();
        assertFalse(cBoard.checkTie());;

    }
//
    @Test(timeout=200)
    public void testCheckTie_With_GameBoard_Full(){
        GameBoard cBoard = createGameBoard();
        for(int i = 0; i < cBoard.getNumRows(); i++){
        for(int j = 0; j < cBoard.getNumColumns(); j++){
            char player = 'X';
            char player2 = 'O';
            cBoard.dropToken(player, j);
            cBoard.dropToken(player2, j + 1);
            j++;
        }
        }
        assertTrue(cBoard.checkTie());
    }

    @Test(timeout=200)
    public void testCheckTie_Partial_Filled_Board(){
        GameBoard cBoard = createGameBoard();
        char player = 'X';
        cBoard.dropToken(player, 0);
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 0);
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 0);

        assertFalse(cBoard.checkTie());


    }
//
    @Test(timeout=200)
    public void testCheckTie_When_Win_Present(){
        
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(7, 7);
        for(int i = 0; i < cBoard.getNumRows() - 1; i++){
        for(int j = 0; j < cBoard.getNumColumns(); j++){
            char player = 'X';
            cBoard.dropToken(player, j);
            
        }
        }
        if(cBoard.checkForWin(7)){
          assertFalse(cBoard.checkTie());
        }

        }
        
    

    


    @Test(timeout=200)
    public void testWhatsAtPos_One_Row_Full_Characters(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(1, 0);
      char player = 'X';
      for(int i = 0; i < cBoard.getNumColumns(); i++){
        cBoard.dropToken(player, i);
      }
      assertEquals(' ', cBoard.whatsAtPos(pos));
       
    }

    @Test(timeout=200)
    public void testWhatsAtPos_Empty_Character_1_1(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(1,1);
        //fill expected array with what we want to test
        for (int i = 0; i <= cBoard.getNumRows() - 1; i++) {
            for (int j = 0; j <= cBoard.getNumColumns() - 1; j++) {
                expected[i][j] = ' ';
            }
        }
        assertEquals(expected[1][1],cBoard.whatsAtPos(cPos));
    }

    //expected is X ; actual is < >
    @Test(timeout=200)
    public void testWhatsAtPos_Next_To_Filled_Column(){
      GameBoard cBoard = createGameBoard();
      BoardPosition pos = new BoardPosition(0, 1);
      char player = 'X';
      for(int i = 0; i < cBoard.getNumRows(); i++){
        cBoard.dropToken(player, 0);
      }
      assertEquals(' ', cBoard.whatsAtPos(pos));
        

    }

    @Test(timeout=200)
    public void testWhatsAtPos_Empty_Character_In_Double_Digit_Position_11_12(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(11,12);
        for (int i = 0; i <= cBoard.getNumRows() - 1; i++) {
            for (int j = 0; j <= cBoard.getNumColumns() - 1; j++) {
                expected[i][j] = ' ';
            }
        }
        assertEquals(expected[11][12],cBoard.whatsAtPos(cPos));
    }

    @Test(timeout=200)
    public void testWhatsAtPos_Character_In_Double_Digit_Position_11_12(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(11,12);
        //fill expected array with what we want to test
        for (int i = 0; i <= cBoard.getNumRows() - 1; i++) {
            for (int j = 0; j <= cBoard.getNumColumns() - 1; j++) {
                expected[i][j] = ' ';
            }
        }
        for(int i = 0; i < 11; i++) {
            cBoard.dropToken('X', 12);
            expected[i][12] = 'X';
        }

        assertEquals(expected[11][12],cBoard.whatsAtPos(cPos));
    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Out_Of_Bounds_15_15(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(15,15);
        assertFalse(cBoard.isPlayerAtPos(cPos,'X'));
    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Empty_Position_1_1(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(1,1);
        cBoard.dropToken('X', 0);
        assertFalse(cBoard.isPlayerAtPos(cPos,'X'));
    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Check_Matching_Character_At_Position_0_0(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertTrue(cBoard.isPlayerAtPos(cPos,'X'));

    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Check_Mismatch_Character_At_Position_0_0(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertFalse(cBoard.isPlayerAtPos(cPos,'O'));
    }

//
    @Test(timeout=200)
    public void testIsPlayerAtPos_When_Given_Null_Character(){
        GameBoard cBoard = createGameBoard();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertFalse(cBoard.isPlayerAtPos(cPos, ' '));
    }

    @Test(timeout=200)
    public void testDropToken_In_Empty_Board(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);

        assertEquals(player,cBoard.whatsAtPos(pos));

    }

    @Test(timeout=200)
    public void testDropToken_On_Partial_Filled_Column(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(2, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 1);

        assertEquals(player, cBoard.whatsAtPos(pos));


    }

    @Test(timeout=200)
    public void testDropToken_Check_Only_One_Token_Placed(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 1);
        BoardPosition pos2 = new BoardPosition(1, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);

        assertEquals(player,cBoard.whatsAtPos(pos));
        assertNotEquals(player, cBoard.whatsAtPos(pos2));


    }

    @Test(timeout=200)
    public void testDropToken_Check_Different_Column_selection(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(0, 1);
        BoardPosition pos2 = new BoardPosition(0, 2);
        BoardPosition pos3 = new BoardPosition(0, 3);
        char player = 'X';
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 2);
        cBoard.dropToken(player, 3);

        assertEquals(player,cBoard.whatsAtPos(pos));
        assertEquals(player, cBoard.whatsAtPos(pos2));
        assertEquals(player, cBoard.whatsAtPos(pos3));
    }

    @Test(timeout=200)
    public void testDropToken_Dont_Drop_In_Filled(){
        GameBoard cBoard = createGameBoard();
        BoardPosition pos = new BoardPosition(cBoard.getNumRows(), cBoard.getNumColumns());
        char player = 'X';

        for(int i = 0; i < cBoard.getNumRows(); i++){
            cBoard.dropToken(player, 1);
        }
        char player2 = 'O';
        cBoard.dropToken(player2, 1); //Attempt to drop a token in this column (Column should be full)

        for(int j = 0; j < cBoard.getNumRows(); j++){
            assertEquals(player, cBoard.whatsAtPos(new BoardPosition(j, 1)));
        }



    }
}
