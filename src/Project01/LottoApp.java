package Project01;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class LottoApp {

    public static void main(String[] args) {
//setting input file path and output file path
        File inFilePath = new File("src/Project01/txt/lottoNumbers.txt");
        File outFilePath = new File( "src/Project01/txt/lottoNumbersNEW.txt");

        //using this array to store numbers from txt file
        int[] lottoNumbers;

        try{
            //using a method that takes numbers from txt file and returns
            // them in an array.
            lottoNumbers = getNumbers(inFilePath);
            Arrays.sort(lottoNumbers); //sorting them
            final int SIZE = 6; // we need combinations of 6 numbers
            int[] result = new int[SIZE]; // initializing an array of 6
            int window = lottoNumbers.length - SIZE; //window increases in
            // each nested for-loop so that we get all combinations of
            // different numbers.
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = lottoNumbers[i];
                                    result[1] = lottoNumbers[j];
                                    result[2] = lottoNumbers[k];
                                    result[3] = lottoNumbers[l];
                                    result[4] = lottoNumbers[m];
                                    result[5] = lottoNumbers[n];
                    /* each time the loops finish,one different combination of 6 numbers is created
                    and stored in result[]. Then we make the following check and
                    if the combination passes , it is printed in another txt file.
                     */
                                        if( (countEvens(result) <= 4) && (countOdds(result) <= 4)
                                                && (!hasMoreThanTwoConsec(result)) && (!hasMoreThanThreeSameEnding(result))
                                                &&(!hasMoreThanThreeInSameDecade(result))){
                                            try{
                    // using a method to print result[] into our new txt file
                                                printArrayInFile(result, outFilePath);
                                            } catch (FileNotFoundException e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }

    //reads numbers from a file and returns an array of them.
    static int[] getNumbers(File file) throws IOException{
        //setting array length as 49 because 49 is the maximum amount of numbers
        int[] lottoNumbers = new int[49];
        //we don't know exactly how many numbers there will be in the txt
        // file so we use the end as index in the sub-array the method returns
        int end = 0;
        //using try with resources so the scanner closes automatically after
        //the read is finished
        try (Scanner reader = new Scanner(file)){
            //reading the file until there are no more numbers
            // or lotto numbers reached max amount
            while (reader.hasNextInt() && end <= 48) {
                int value = reader.nextInt();
                //adding the number from the file to the array
                lottoNumbers[end] = value;
                //end index increased by 1, as the array[end] is now filled
                end += 1;
            }
        } catch(IOException e){
            throw e;
        }
        //returning a sub-array of lottoNumbers containing the positions
        //filled with a number from the file
        return Arrays.copyOfRange(lottoNumbers,0,end);
    }

    static void printArray(int[] arr) {
        if (arr.length == 0)
            System.out.println("The Array is Empty");
        for(int elem: arr){
            System.out.print(elem + " ");
        }
    }

/*takes an array and a file as inputs. prints the array in the file using a
 PrintWriter (uses append=true because we don't want to lose previous data
 in the file)
 */
    static void printArrayInFile(int[] arr, File file) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
            pw.printf("%d %d %d %d %d %d\n",arr[0], arr[1], arr[2],
                    arr[3], arr[4], arr[5]);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
//simple method to count even numbers in an int[] array
    static int countEvens(int[] arr) {
        if(arr.length == 0) return 0;
        int evensCount = 0;

        for (int elem : arr) {
            if (elem % 2 == 0) {
                evensCount += 1;
            }
        }
        return evensCount;
    }
//simple method to count odd numbers in an int[] array
    static int countOdds(int[] arr) {
        if(arr.length == 0) return 0;
        int oddsCount = 0;

        for (int elem : arr) {
            if (elem % 2 == 1) {
                oddsCount += 1;
            }
        }
        return oddsCount;
    }
//method that checks if there are three consecutive numbers in an int[]array
    static boolean hasMoreThanTwoConsec(int[] arr){
        //for loop ends at (length-2) so that we don't get error for Array index
        for (int i = 0 ; i < arr.length - 2 ; i ++){
            if ((arr[i] == arr[i+1]-1) && (arr[i] == arr[i+2]-2)){
                return true;
            }
        }
        return false;
    }
//method that checks if there are more than 3 numbers with
//the same ending in an int[] array.
    static boolean hasMoreThanThreeSameEnding(int[] arr){
        //using a new array to store endings and their count
        //each ending is used as an index in the new array
        int[] endings = new int[10];
        for (int i = 0 ; i < arr.length ; i++) {
            endings[arr[i]%10] += 1;// number%10 gives us the ending of number
        }
        for (int i : endings){
            if (i>3){ //checking if any ending appears more than 3 times.
                return true;
            }
        }
        return false;
    }

//method that checks if there are more than three numbers in the same decade
// in an int[] array
    static boolean hasMoreThanThreeInSameDecade(int[] arr){
//making a new array, that contains all the numbers of the original array
// divided by 10.(5 will be 0 , 15 will be 1, 19 will be 1 etc.)
        int[] dividedByTen = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            dividedByTen[i] = arr[i]/10;
        }
//if 2 numbers are in the same decade then when divided by 10 they will have
// the same ending, so we use the above method in the new array we created.
        return hasMoreThanThreeSameEnding(dividedByTen);
    }
}
