package Project02;

import java.util.Arrays;

public class MaximumSubSubarray {
    /**
    this app finds the contiguous sub-array with the largest sum from a given
     int[] array.
     */
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//using method to find the sub-array with the largest sum and then printing it
        int[] maxSubarray = findSubarrayWithMaxSum(arr);
        printArray(maxSubarray);
    }

/* this method's time complexity is O(n) as it uses a single loop that iterates
through each element of the input array once. no nested loops are used.
    */
    public static int[] findSubarrayWithMaxSum(int[] arr){
        //checking if arr is empty, and if so, returning an empty array
        if(arr.length == 0) return new int[0];
        //initializing maxSum as the first element of the array
        int maxSum = arr[0];
        //sum is the current sum, we compare it with maxSum in each step
        int sum = arr[0];
        //we don't know the length of the max sub-array from the start so
        //we set start, end and temp values as indexes for it.
        int start = 0, end = 0, temp = 0;

        //traversing the array using a for-loop
        for(int i = 0 ; i < arr.length ; i++){
            //saving the sum of numbers so far in the array in the sum variable.
            sum += arr[i];
            //checking if sum is larger than maxSum
            if (sum > maxSum) {
                maxSum = sum; //if it is, then sum is the new maxSum
                start = temp; // Update start index of the max sub-array
                end = i; // Update end index to i
            }

            /* if the sum up to an index in the array is below zero, there is
             no point to carry it, because it only reduces the sum of any sub-array
             that comes next*/
            if(sum < 0){
                sum = 0;
                temp = i + 1; // Update temp to the next i
            }
        }
        //returning the sub-array with max sum using start and end indexes
        //using end+1 because we want end included in the result
        return Arrays.copyOfRange(arr,start,end+1);
    }

    static void printArray(int[] arr){
        if(arr.length == 0){
            System.out.println("The array is empty");
        }
        for (int num : arr){
            System.out.print(num + " ");
        }
    }
}
