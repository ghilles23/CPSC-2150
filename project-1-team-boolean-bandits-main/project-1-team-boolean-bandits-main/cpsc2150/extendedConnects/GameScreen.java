package cpsc2150.extendedConnects;
import java.util.Scanner;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Gabriel Hillesheim @ghilles23
Trent Brown @trentbrown1
Breanna Mackey @breannamackey

 */
public class GameScreen {
        private static GameBoard gameBoard;
        private static GameBoardMem gameBoard2;
        public static int MAX_CHAR = 10;
        public static int MIN_CHAR = 1;
        public static void main(String[] args) {
            
            Scanner input = new Scanner(System.in);
            Scanner XVal = new Scanner(System.in);
            Scanner YVal = new Scanner(System.in);
            Scanner WIN = new Scanner(System.in);
           int Columns = 0;
           int Rows = 0;
           int NumToWIn = 0;


            boolean playAgain = true; //do over functionality   
            while(playAgain){ 
               System.out.print("What type of game fast(F) or memory efficient(M)? : ");
               String gameType = input.nextLine();
        if (gameType.equalsIgnoreCase("m")) {
            int rows = 0;
            int cols = 0;
            int numToWin= 0;
            do{
             System.out.print("Enter the number of rows: ");
             rows = input.nextInt();
              if(rows < gameBoard2.MIN_ROW || rows > gameBoard2.MAX_ROW){
                System.out.print("Invalid Row Selection! ");
             }
            }while(rows < gameBoard2.MIN_ROW || rows > gameBoard2.MAX_ROW);
            do{
             System.out.print("Enter the number of columns: ");
             cols = input.nextInt();
                if(cols < gameBoard2.MIN_COL || cols > gameBoard2.MAX_COL){
                System.out.print("Invalid Column Selection! ");
             }
            }while(cols < gameBoard2.MIN_COL || cols > gameBoard2.MAX_COL);
            do{
             System.out.print("Enter the number of tokens to win: ");
             numToWin = input.nextInt();
               if(numToWin < gameBoard2.MIN_NUMTOWIN || numToWin > gameBoard2.MAX_NUMTOWIN){
                System.out.print("Invalid Number to Win! ");
             }
            }while(numToWin < gameBoard2.MIN_NUMTOWIN || numToWin > gameBoard2.MAX_NUMTOWIN);
            gameBoard2 = new GameBoardMem(rows, cols, numToWin);
        } else if (gameType.equalsIgnoreCase("f")) {
            int rows = 0;
            int cols = 0;
            int numToWin= 0;
           
            do{
             System.out.print("Enter the number of row: ");
             rows = input.nextInt();
             if(rows < gameBoard.MIN_ROW || rows > gameBoard.MAX_ROW){
                System.out.print("Invalid Row Selection! ");
             }
            }while(rows < gameBoard.MIN_ROW || rows > gameBoard.MAX_ROW);
            do{
             System.out.print("Enter the number of columns: ");
             cols = input.nextInt();
             if(cols < gameBoard.MIN_COL || cols > gameBoard.MAX_COL){
                System.out.print("Invalid Column Selection! ");
             }
            }while(cols < gameBoard.MIN_COL || cols > gameBoard.MAX_COL);
            do{
             System.out.print("Enter the number of tokens to win: ");
             numToWin = input.nextInt();
             if(numToWin < gameBoard.MIN_NUMTOWIN || numToWin > gameBoard.MAX_NUMTOWIN){
                System.out.print("Invalid Number to Win! ");
             }
            }while(numToWin < gameBoard.MIN_NUMTOWIN || numToWin > gameBoard.MAX_NUMTOWIN);
            gameBoard = new GameBoard(rows, cols, numToWin);
        } else {
            System.out.println("Invalid choice. Please choose F or M.");
            return; // or handle the choice differently
        }
            int players = 0;
            do{
                System.out.print("How many Players would you like?: ");
                
                players = input.nextInt();
               if(players >= MAX_CHAR || players <= MIN_CHAR){
                    System.out.print("Invalid Number!: ");
                } 
                }
                while(players >= MAX_CHAR || players <= MIN_CHAR);
               char[] playerChar = new char[players];



                for(int i = 0; i < players; i++){

                    char chosenChar;
                    boolean DupeChecker;

                    do{
                        DupeChecker = false;
                    System.out.print("Player " + (i+1) + " what character would you like?: ");
        
                    chosenChar = Character.toUpperCase(input.next().charAt(0));
                

                        for(int j = 0; j < i; j++){
                            if(playerChar[j] == chosenChar){
                                DupeChecker = true;
                                System.out.print("Character already chosen. Choose another: ");
                                break;
                            }
                        }
                    }while (DupeChecker);

                    playerChar[i] = chosenChar;
                }

                int currPlayer = 0;

                    while (playAgain == true) {
                        if(gameType.equalsIgnoreCase("m")){
                            System.out.println(gameBoard2);
                                char currentPlayer = playerChar[currPlayer];
                                System.out.print("Player " + currentPlayer + " what column do you want to place your marker in?:\n");
                                int chosenCol = input.nextInt();
                                if(chosenCol >= 0 && chosenCol < gameBoard2.getNumColumns()){
                                    if(gameBoard2.checkIfFree(chosenCol)){
                                        gameBoard2.dropToken(currentPlayer, chosenCol);

                                        if(gameBoard2.checkForWin(chosenCol)){
                                            System.out.println(gameBoard2);
                                            System.out.println("Player " + currPlayer + " Won!");
                                            break;
                                        }
                                        currPlayer = (currPlayer + 1) % players;
                                    }
                                    else{System.out.println("Column is full");
                                }
                                }
                                else{
                                    System.out.println("Invalid column choice. Please choose a valid column.");
                                }
                        }
                         else if (gameType.equalsIgnoreCase("f")){
                            System.out.println(gameBoard);
                            char currentPlayer = playerChar[currPlayer];
                            System.out.print("Player " + currentPlayer + " what column do you want to place your marker in?:\n");
                            int chosenCol = input.nextInt();
                            if(chosenCol >= 0 && chosenCol < gameBoard.getNumColumns()){
                                if(gameBoard.checkIfFree(chosenCol)){
                                    gameBoard.dropToken(currentPlayer, chosenCol);

                                    if(gameBoard.checkForWin(chosenCol)){
                                        System.out.println(gameBoard);
                                        System.out.println("Player " + currentPlayer + " Won!");
                                        break;
                                    }
                                    else if(gameBoard.checkTie()){
                                    System.out.print("Its a Tie!");
                                    break;
                                }
                                    currPlayer = (currPlayer + 1) % players;
                                }
                                
                            
                        
                                else {
                                    System.out.println("Column is full");
                                }
                            } else {
                                System.out.println("Invalid column choice. Please choose a valid column.");
                            }}
                         else {
                            System.out.println("Invalid choice. Please choose F or M.");
                            return;
                        }
                    }
                System.out.println("Play again? y/n: ");
                String doOver = input.next();
                if(!doOver.equalsIgnoreCase("y")){
                    playAgain = false;
                }
                }


            }
        }

                

                
        
        
    
    
    