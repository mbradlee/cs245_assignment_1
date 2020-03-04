import java.util.*;
import java.io.*;

public class hybridSortClass {

    public static void main(String args[]) throws FileNotFoundException{
        File file = new File("test3.txt");
        Scanner s = new Scanner(file);

        //add dynamic allocation?
        //how can I loop through the data to see what the size of my dataArray should be

//        int counter = 0;

//        while(s.hasNextLine()){
//            s.nextLine();
//            counter++;
//        }

        double dataArray[] = new double[1000000];
        double num;

        for(int i = 0; i < dataArray.length; i++){
            String numLine = s.nextLine();
            num = Integer.parseInt(numLine);
            dataArray[i] = num;
        }

        hybridSort(dataArray, 0, dataArray.length - 1);
        //dataArray print test
        for(int i = 0; i < dataArray.length; i++){
            System.out.println(dataArray[i]);
        }

    }

    /**
     * generates a random number up to a maximum given number
     * @param left
     * @param right
     * @return pivot -> pivot is a random index between two given indices inclusive
     */
   static int pivotGen(int left, int right){
        int pivot;

        Random r = new Random();
        pivot = r.nextInt((right - left) + 1 ) + left;

        return pivot;
    }


    /**
     * swaps 2 values in array 'a' given two indices in array 'a'
     * @param a -> given array containing the values that will be swapped
     * @param x -> index 1
     * @param y -> index 2
     */
    static void swap(double [] a, int x, int y){
        double temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }


    /**
     * This quickSort function sorts everything to the left or right of a randomly generated
     * pivot and returns the pivot index, which will act as the partition for the following
     * call of quickSort (recursively)
     * given signature: public int quickSort(double [] arr, int left, int right);
     * @param arr
     * @param left
     * @param right
     * @return
     */
    static int quickSort(double [] arr, int left, int right){
        int pivot = pivotGen(left, right);
        int pIndex = left;
        swap(arr, pivot, right);
        pivot = right;
        for(int i = left; i < right; i++){
            if(arr[i] <= arr[pivot]){
                swap(arr, i, pIndex);
                ++pIndex;
            }
        }

        swap(arr, pIndex, right);
        return pIndex;
    }

    /**
     * This quadratic sort will be implemented as insertion sort
     * Insertion sort is the fastest quadratic sort that we have learned, but it becomes less viable
     * after 10,000 and almost unviable after 50,000 items (compared to quick sort).
     * given signature: public void quadraticSort(double [] arr, int left, int right);
     * @param a
     */
    static void quadraticSort(double [] a, int left, int right){

        for(int i = left; i < right; i++) {
            double value = a[i];
            int j = i - 1;
            while (j >= left && value < a[j]) {
                a[j + 1] = a[j];
                --j;
            }
            a[j+1] = value;
        }

        System.out.println("Finished quadratic sort");

    }

    public static void hybridSort(double [] a , int left, int right){
        int subSize = 1000;
        if(right - left <= subSize){
            System.out.println("quadratic sorting...");
            quadraticSort(a, left, right);
        }else{
            System.out.println("quick sorting...");
            int pivot = quickSort(a, left, right);
            hybridSort(a, left, pivot - 1);
            hybridSort(a, pivot + 1, right);
        }

    }

}