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

        externalSort(f, o, 20, 4);
    }

    public static void externalSort(File inputFile, File outputFile, int n, int k) throws UnsupportedEncodingException{
        //n items, k max memory input

        int chunks = (int)Math.ceil((double)n/(double)k);

        try {
            Scanner s = new Scanner(inputFile);

            for (int i = 0; i < chunks; i++) {
                if(i == chunks - 1 && n%k != 0){
                    double arr[] = new double[n%k];

                    int counter = 0;
                    while (s.hasNextLine()) {
                        arr[counter] = Double.parseDouble(s.nextLine());
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
                        for (int x = 0; x < n%k; x++) {
                            //System.out.println(arr[x]);
                            writer.println(arr[x]);
                        }
                        writer.close();
                    } catch (UnsupportedEncodingException f) {
                        System.out.println("Unsupported encoding exception.");
                    }
                }else{
                    double arr[] = new double[k];

                    int counter = 0;
                    while (s.hasNextLine() && counter < k) {
                        arr[counter] = Double.parseDouble(s.nextLine());
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

        int fileNum = tempMin.length;

        while (sortedChunks < chunks){

            System.out.println("Sorted Chunks: " + sortedChunks);
            System.out.println("Chunks: " + chunks);

            //populating tempMin array
            for (int i = 0; i < fileNum; i++) { //0 to the number of chunks

                //create chunkNO
                String chunkNo;
                if (i < 9) {
                    chunkNo = "0" + i;
                } else {
                    chunkNo = "" + i;
                }
                //create and read file
                File f = new File("temp" + chunkNo + ".txt");
                Scanner s = new Scanner(f);
                //check if entire file has been read

                if(i == tempMin.length - 1){
                    if (indices[i] >= n%k) {
                        tempMin[i] = -1; //yes? set temp min to be -1 at the index which the whole file has bee read
                        sortedChunks++;
                        fileNum--;
                    } else {
                        for (int j = 0; j <= indices[i]; j++) {
                            tempMin[i] = Double.parseDouble(s.nextLine()); //otherwise parse the file index num of times
                        }
                    }
                }else{
                    if (indices[i] >= k) {
                        tempMin[i] = -1; //yes? set temp min to be -1 at the index which the whole file has bee read
                    } else {
                        for (int j = 0; j <= indices[i]; j++) {
                            tempMin[i] = Double.parseDouble(s.nextLine()); //otherwise parse the file index num of times
                        }
                    }
                }
            }

            //find the min in tempMin to be written into output
            int min = findMin(tempMin);
            //write min to output
            writer.println(tempMin[min]);
            //increment this index that was written by one
            indices[min] += 1;

            for(int i = 0; i < tempMin.length; i++)
                System.out.println(tempMin[i]);
            for(int i = 0; i < indices.length; i++)
                System.out.println(indices[i]);

            //check to see if this index has been fully read, if so increment sortedChunks
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
