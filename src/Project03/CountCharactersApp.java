package Project03;

import java.io.*;
import java.util.Arrays;

public class CountCharactersApp {

    public static void main(String[] args) {
        File file = new File ("src/Project03/txt/characters.txt");
        int[][] charactersCount;

        try{
            //using methods to create the array with character appearances
            charactersCount = storeCharactersCountInTable(file);
            //sorting it by appearances number
            sortByAppearanceFrequency(charactersCount);
//            sortByCharacterAscii(charactersCount);
//            printDataAscending(charactersCount);

            //printing results
            printDataDescending(charactersCount);

    }catch(IOException e){
        System.out.println(e.getMessage());
    }
    }

/*method that takes a file as input and returns a [128][2] int table.
each character is represented by 2 values. first is the ascii number [0] and
the other is the amount of times they appear [1].
ascii number is also used as index.
 */
    static int[][] storeCharactersCountInTable(File file) throws IOException{
        String line;
        int[][] intCharArray = new int[128][2];
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((line = reader.readLine()) != null) {
                //converting each line in an array containing its characters.
                char[] characters = line.toCharArray();
                for (char character : characters){
                    if(Character.isWhitespace(character)){
                        continue; //Ignoring white spaces
                    }
                    int index = (int) character; // ASCII value as index
                    if (intCharArray[index][0] == 0) {
                        // Store character's ASCII value
                        intCharArray[index][0] = index;
                    }
                    intCharArray[index][1] += 1; //increment the count
                }
            }
            return intCharArray;
        }catch(IOException e){
            throw e; }
    }

    //sort the array by  the number of times a character appears
    static void sortByAppearanceFrequency(int[][] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));
    }

    //sort the array by the ascii number of the characters
    static void sortByCharacterAscii(int[][] arr){
        Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));
    }

    //printing data starting from the beginning of the array
    static void printDataDescending(int[][] arr){
        for (int[] chars : arr) {
            if (chars[1] == 0) return;
            System.out.println("Character '" + (char) chars[0] +
                    "' appears " + chars[1] + " times");
        }
    }

    //printing data starting from the end of the array
    static void printDataAscending(int[][] arr){
        for(int i = arr.length - 1 ; i >= 0 ; i--){
            if(arr[i][1] == 0) continue;
            System.out.println("Character '" + (char) arr[i][0] +
                    "' appears " + arr[i][1] + " times");
        }
    }
}
