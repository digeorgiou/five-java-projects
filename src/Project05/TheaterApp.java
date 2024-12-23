package Project05;

import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Character.toUpperCase;

public class TheaterApp {
    /**
     * An application that manages bookings for a theater with 30 rows and 12
     * columns.
     * Seats are stored as boolean variables in a 2d array.
     * One dimension of the 2d array represents the row and the
     * other dimension represents the column where the seat is located.
     * The boolean value of the seat represents if it's full or empty.
     * true means full, false means empty.
     * Also, there are methods to make or cancel a booking.
     */

//setting the array as static because I want all methods to have access to it.
    static boolean seats[][] = new boolean[30][12];

//all boolean variables in the array are initialized to false, so false
// represents empty
    public static void main(String[] args) {

        int choice = -1;
        int row = -1;

        while(true) {
            printMenu();
            //asking for menu choice until user enters valid number
            while (true) {
                try {
                    choice = getMenuChoice();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Choice must be an integer");
                }
            }
            //checking if user chose "Exit"
            if(choice == 3 ) break;
            //getColumn returns a char after making necessary checks
            System.out.println("Insert column: ");
            char column = getColumn();
            //asking for row number until user gives valid row number
            System.out.println("Insert row");
            while (true) {
                try {
                    row = getRow();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Row must be an integer");
                }
            }
        //if user chose 1, we use the book method on the position they chose
        if(choice == 1){
            book(column, row);
        }
        //if user chose 2, we use the cancel method on the position they chose
        else if (choice == 2){
            cancel(column, row);
        }
        }
        //this only prints after the while loop is broken,
        // so only if user chooses "Exit"
        System.out.println("Thank you for using TheaterApp");
    }

    static void printMenu(){
        System.out.println("Choose:");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel a booking");
        System.out.println("3. Exit");
    }

//asking from user to choose what they want to do
    static int getMenuChoice() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
            try {
                choice = sc.nextInt();
                while (choice > 3 || choice < 1) {
                    System.out.println("Number must be between 1 and 3");
                    choice = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                throw e;
            }
        return choice;
    }

//2 methods to ask for row (int) and column (letter) from user
//also making necessary checks.
    static int getRow(){
    Scanner sc = new Scanner(System.in);
    int row = -1;
    try{
        row = sc.nextInt();
        while(row > 30 || row < 1){
            System.out.println("Number must be between 1 and 30");
            row = sc.nextInt();
        }
    } catch (InputMismatchException e) {
        throw e;
    }
    return row;
    }

    static char getColumn() {
        Scanner sc = new Scanner(System.in);
        String column = sc.nextLine();
        while(!(column.length() == 1)) {
            System.out.println("Column must be exactly one letter");
            column = sc.nextLine();

            while (!Character.isLetter(column.charAt(0))) {
                System.out.println("Column must be a letter");
                column = sc.nextLine();
            }
        }
        return column.charAt(0);
    }


    static void book(char column, int row){

    /* subtracting the Integer value of 'A' from the input char's value
    because the numbering of the columns needs to start from 0 as it will be
    used as index in an array.
    (seats[0] represents column A , seats[1] represents B etc.
    */
    //converting input to uppercase in case the input is 'c2' instead of 'C2'
        int columnNumber = toUpperCase(column) - 'A';

    /* subtracting 1 from input because in seats array the first row has
    index 0,  second row has  index 1 etc.
    */
        int rowNumber = row - 1;

    //making these checks so there is no ArrayIndexOutOfBoundsException
        if(rowNumber > 29 || rowNumber < 0){
            System.out.println("Row number must be between 1 and 30");
            return;
        }
        if(columnNumber > 11 || columnNumber < 0){
            System.out.println("Column character must be between A and L");
            return;
        }
    //checking if seat's position is 'false' (empty) in the array and if it is
    //then switch it to 'true' (full)
        if(!seats[rowNumber][columnNumber]) {
            seats[rowNumber][columnNumber] = true;
    //printing message to confirm the booking.
            System.out.println("Booking of seat in row: " + row + " and column: " + toUpperCase(column) +
                    " was successful");
        }
    //if the seat is already full , print appropriate message
        else{
            System.out.println("The seat in row: " + row + " and column: " + toUpperCase(column) +
                    " is already booked");
        }
    }

    static void cancel(char column, int row){
        //same steps as the book method.
        int columnNumber = toUpperCase(column) - 'A';
        int rowNumber = row - 1;
        if(rowNumber > 29 || rowNumber < 0){
            System.out.println("Row number must be between 1 and 30");
            return;
        }
        if(columnNumber > 11 || columnNumber < 0){
            System.out.println("Column character must be between A and L");
            return;
        }
        //if seat is booked (true) , switch it to false and print message to
        //confirm cancellation.
        if(seats[rowNumber][columnNumber]) {
            seats[rowNumber][columnNumber] = false;
            System.out.println("Booking of seat in row: " + row + " and " +
                    "column: " + toUpperCase(column) +
                    " was canceled");
        }
        //if the seat was not already booked, print appropriate message.
        else{
            System.out.println("Can't cancel because the seat in row: " + row +
                    " and column: " + toUpperCase(column) + " is not booked");
        }
    }
}

