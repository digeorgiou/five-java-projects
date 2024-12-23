package Project04;

import java.util.Scanner;

public class TicTacToeApp {
    /**
     * Simple Game of Tic-Tac-Toe.
     * Player X and Player O take turns and fill positions in a board of
     * tic-tac-toe until one player has won or the board is full without a
     * winner.
     * Player wins if they fill an entire row, column or diagonal with their
     * symbol.
     */

//setting the 2d array as static because I want all methods to have access to
// it.
    static char[][] arr = {{' ', ' ', ' '},{' ',' ',' '},{' ',' ',' '}};

    public static void main(String[] args) {
//using char player as the name of each player and also as a symbol to insert
// in my array.
        char player;
//using int count to keep track of how the turns of the game.
        int count = 0;
//if count reaches nine, the board is full and the game is over.
        while(count < 9){
//printing the current state of the game
            printGame();
//checking if count is even or odd to determine which player's turn it is.
            player = (count % 2 == 0)? 'X' : 'O';
            System.out.println("Player " + player + " turn: ");
//using methods getRow and getColumn to ask from player to choose a position to
// play
            int row = getRow();
            int column = getColumn();
//checking if the position chosen by the player is already taken
            while(arr[row][column] != ' '){
                System.out.println("Position already taken, please choose " +
                        "again");
                row = getRow();
                column = getColumn();
            }
//Player's symbol is inserted in the position he chose
            arr[row][column] = player;
//checking if player has won
            if(hasPlayerWon(arr)){
                printGame();
//printing message for the winner
                System.out.println("Player " + player + " has won! ");
                System.out.println("Congrats!");
                break;
            }
//increasing count number after each while loop ends.
            count ++;
        }
//this only prints if no player won after the board is full
        if(!hasPlayerWon(arr)) {
            System.out.println("It's a tie !! ");
        }
//this is always printed
        System.out.println("Thank you for playing Tic Tac Toe");

    }

/* a method to help visually represent tha game of tic-tac-toe .
using a 2d char array where each char represents a position. if position is
empty then ' ' is printed.
 */
    static void printGame() {
        System.out.println("  +---+---+---+");
        System.out.print("3 | " + arr[2][0] + " | ");
        System.out.print(arr[2][1] + " | ");
        System.out.println(arr[2][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.print("2 | " + arr[1][0] + " | ");
        System.out.print(arr[1][1] + " | ");
        System.out.println(arr[1][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.print("1 | " + arr[0][0] + " | ");
        System.out.print(arr[0][1] + " | ");
        System.out.println(arr[0][2] + " |");
        System.out.println("  +---+---+---+");
        System.out.println("    1   2   3 ");
    }

/* two methods that ask from user for row and column choice.
they make the necessary checks and print messages accordingly.
 */
    static int getRow() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Insert Row (1,2 or 3) : ");
        while(!sc.hasNextInt()){
            System.out.println("Row must be an Integer (1,2,3)");
            sc.nextLine();
        }
        int row = sc.nextInt();
        while(row > 3 || row < 1){
            System.out.println("Number out of bounds");
            row = getRow();
        }
/* asking 1,2 or 3 from user because it's more user-friendly,
but  0,1,2  are needed to be used as indexes in the array.
         */
        return row - 1;
    }

//same method with different messages for column.
    static int getColumn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Insert Column (1,2 or 3) : ");
        while(!sc.hasNextInt()){
            System.out.println("Column must be an Integer (1,2,3)");
            sc.nextLine();
        }
        int column = sc.nextInt();
        while(column > 3 || column < 1){
            System.out.println("Column must be 1 , 2 or 3");
            column = getColumn();
        }
        return column - 1;
    }

//This method checks if there is a winner. It's applied in each turn.
    static boolean hasPlayerWon(char[][] arr1){

        for(int i = 0 ; i < 3 ; i ++){
            //checking vertical victory
            if (arr[i][0] != ' ' && (arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2])){
                return true;
            }
            //checking horizontal victory
            if (arr[0][i] != ' ' && (arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i])){
                return true;
            }
        }
        //checking both diagonals
        if (arr[0][0] != ' ' && (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2])) {
            return true;
        }
        if (arr[0][2] != ' ' && (arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0])) {
            return true;
        }
        return false;
    }
}
