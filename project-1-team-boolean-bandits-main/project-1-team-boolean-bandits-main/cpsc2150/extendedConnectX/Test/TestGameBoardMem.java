package cpsc2150.extendedConnectX.Test;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestGameBoardMem {

    private GameBoardMem GameBoardMemFactory(){
        int row = 14;
        int col = 14;
        int numToWin = 7;
        return new GameBoardMem(row, col, numToWin);
    }

    private GameBoardMem  createGameBoardMem(){
        GameBoardMem gb = GameBoardMemFactory();
        return gb;
    }
    char[][] expected = new char[GameBoardMemFactory().getNumRows()][GameBoardMemFactory().getNumColumns()];

    @Test(timeout=200)
    public void testConstructor_Gameboard_Correct_Size(){

        GameBoardMem gb = createGameBoardMem();
        assertEquals(GameBoardMemFactory().getNumRows(), gb.getNumRows());
        assertEquals(GameBoardMemFactory().getNumColumns(), gb.getNumColumns());
        assertEquals(GameBoardMemFactory().getNumToWin(), gb.getNumToWin());
    }

    @Test(timeout=200)
    public void testConstructor_Blank_Character_GameBoardMem(){
        GameBoardMem cBoard = createGameBoardMem();

        for (int i = 0; i <= cBoard.getNumRows() - 1; i++){
            for(int j = 0; j <= cBoard.getNumColumns() - 1 ; j++){
                BoardPosition cPos = new BoardPosition(i,j);
                assertEquals(' ',cBoard.whatsAtPos(cPos));
            }

        }
    }

    @Test(timeout=200)
    public void constructor3(){

    }

    //fails due to out of bounds , change so out of bounds gives a pass
    @Test(timeout=200)
    public void testWhatsAtPos_One_Row_Full_Characters(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(1, 0);
        char player = 'X';
        for(int i = 0; i < cBoard.getNumColumns(); i++){
          cBoard.dropToken(player, i);
        }
        assertEquals(' ', cBoard.whatsAtPos(pos));
    }

    @Test(timeout=200)
    public void testWhatsAtPos_Empty_Character_1_1(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(1, 1);
        char expected = ' ';

        assertEquals(expected, cBoard.whatsAtPos(pos));
    }

    //expected is X ; actual is < >
    @Test(timeout=200)
    public void testWhatsAtPos_Next_To_Filled_Column(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(0, 1);
        char player = 'X';
        for(int i = 0; i < cBoard.getNumRows(); i++){
          cBoard.dropToken(player, 0);
        }
        assertEquals(' ', cBoard.whatsAtPos(pos));
    }

    @Test(timeout=200)
    public void testWhatsAtPos_Empty_Character_In_Double_Digit_Position_11_12(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(11,12);
        char expected = ' ';

        assertEquals(expected, cBoard.whatsAtPos(pos));
    }

    @Test(timeout=200)
    public void testWhatsAtPos_Character_In_Double_Digit_Position_11_12(){
        GameBoardMem cBoardMem = createGameBoardMem();
         BoardPosition pos = new BoardPosition(0, 0);
        char player = 'X';

        for(int i = 0; i < cBoardMem.getNumRows(); i++){
            
            cBoardMem.dropToken(player, 0);
    }
        assertEquals(player, cBoardMem.whatsAtPos(pos));
    }
    @Test(timeout=200)
    public void testIsPlayerAtPos_Out_Of_Bounds_15_15(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition cPos = new BoardPosition(15,15);
        assertFalse(cBoard.isPlayerAtPos(cPos,'X'));
    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Empty_Position_1_1(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition cPos = new BoardPosition(1,1);
        cBoard.dropToken('X', 0);
        assertFalse(cBoard.isPlayerAtPos(cPos,'X'));
    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Check_Matching_Character_At_Position_0_0(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertTrue(cBoard.isPlayerAtPos(cPos,'X'));

    }

    @Test(timeout=200)
    public void testIsPlayerAtPos_Check_Mismatch_Character_At_Position_0_0(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertFalse(cBoard.isPlayerAtPos(cPos,'O'));
    }


    @Test(timeout=200)
    public void testIsPlayerAtPos_When_Given_Null_Character(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition cPos = new BoardPosition(0,0);
        cBoard.dropToken('X', 0);
        cBoard.dropToken('O', 1);
        assertFalse(cBoard.isPlayerAtPos(null,'O'));
    }

    @Test(timeout=200)
    public void testDropToken_In_Empty_Board(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(0, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);

        assertEquals(player,cBoard.whatsAtPos(pos));

    }

    @Test(timeout=200)
    public void testDropToken_On_Partial_Filled_Column(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(2, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 1);
        cBoard.dropToken(player, 1);

        assertEquals(player, cBoard.whatsAtPos(pos));


    }

    @Test(timeout=200)
    public void testDropToken_Check_Only_One_Token_Placed(){
        GameBoardMem cBoard = createGameBoardMem();
        BoardPosition pos = new BoardPosition(0, 1);
        BoardPosition pos2 = new BoardPosition(1, 1);
        char player = 'X';
        cBoard.dropToken(player, 1);

        assertEquals(player,cBoard.whatsAtPos(pos));
        assertNotEquals(player, cBoard.whatsAtPos(pos2));


    }

    @Test(timeout=200)
    public void testDropToken_Check_Different_Column_selection(){
        GameBoardMem cBoard = createGameBoardMem();
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
        GameBoardMem cBoard = createGameBoardMem();
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
