import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.Math;
import java.util.*;
import java.io.PrintWriter;
import java.io.*;

public class externalSortClass {

    public static void main(String args[]) throws UnsupportedEncodingException {
        File f = new File("inputTest.txt");
        File o = new File("outputTest.txt");

        externalSort(f, o, 10, 3);
    }

    public static void externalSort(File inputFile, File outputFile, int n, int k) throws UnsupportedEncodingException{
        //n items, k max memory input

        int chunks = (int)Math.ceil(n/k);

        try {
            Scanner s = new Scanner(inputFile);

            for (int i = 0; i < chunks; i++) {
                double arr[] = new double[k];

                int counter = 0;
                while (s.hasNextLine() && counter < k) {
                    arr[counter] = Integer.parseInt(s.nextLine());
                    counter++;
                }

                quadraticSort(arr, 0, counter);

                String chunkNo;
                if (i < 9) {
                    chunkNo = "0" + i;
                } else {
                    chunkNo = "" + i;
                }

                try {
                    PrintWriter writer = new PrintWriter("temp" + chunkNo + ".txt", "UTF-8");
                    for (int x = 0; x < k; x++) {
                        //System.out.println(arr[x]);
                        writer.println(arr[x]);
                    }
                    writer.close();
                } catch (UnsupportedEncodingException f) {
                    System.out.println("Unsupported encoding exception.");
                }
            }

            mergeSort(outputFile, n, k, chunks);

        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }

    }

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

    }

    static void mergeSort(File outputFile, int n, int k, int chunks) throws FileNotFoundException, UnsupportedEncodingException {

        int indices[] = new int[chunks];
        double tempMin[] = new double[chunks];
        int sortedChunks = 0;
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");

        while (sortedChunks < chunks){

            for (int i = 0; i < tempMin.length; i++) {
                String chunkNo;
                if (i < 9) {
                    chunkNo = "0" + i;
                } else {
                    chunkNo = "" + i;
                }
                File f = new File("temp" + chunkNo + ".txt");
                Scanner s = new Scanner(f);
                if (indices[i] >= k) {
                    tempMin[i] = -1;
                } else {
                    for (int j = 0; j <= indices[i]; j++) {
                        tempMin[i] = Double.parseDouble(s.nextLine());
                    }
                }
            }

            int min = findMin(tempMin);
            writer.println(tempMin[min]);
            indices[min] += 1;
            if(indices[min] == k){
                sortedChunks += 1;
            }

        }
        writer.close();

    }

    static int findMin(double [] a){
        int min = 0;
        if(a[min] == -1){
            for(int i = 1; i < a.length; i++){
                if(a[i] != -1){
                    min = i;
                    break;
                }
            }
            for(int i = min+1; i < a.length; i++){
                if(a[i] < a[min] && a[i] != -1){
                    min = i;
                }
            }
            return min;
        }else{
            for(int i = 1; i < a.length; i++){
                if(a[i] < a[min] && a[i] != -1){
                    min = i;
                }
            }
        }

        return min;
    }
}
