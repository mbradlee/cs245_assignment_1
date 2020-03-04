# cs245_assignment_1

#Hybrid Sort
**Please note that there is some off by one error in this hybridSort where only the last item is unsorted. I did not know how to fix this.

hybridSort combines quickSort and insertionSort in order to sort doubles in a given text file.

I used insertionSort because it was the fastest quadratic sort that we learned in class.

When hybridSorting, I needed to choose an array size in which I switch from quickSorting to insertionSorting. The number i chose was 1000. I chose this number because at a certain value of n, n^2 becomes much slower than nlogn. I know from the practice assignments that insetion sort scales really well compared to other quadratic sorts, so it's ability to sort 1000 ints at a time is viable, especially if hybrid sort is given 1,000,000 data points for example. quickSort is then able to efficiently handle the rest of the data.

#External Sort
externalSort sorts a file of n number of data into an output file only reading in k number of data at a time. 

**Please note that this implementation of externalSort cannot handle data sets that contain the number -1, because the way my merge function works is that when using "k-merge" I use the number -1 to indicate that the entire tempfile has already been written to output.
 
**also note that my implementation of external sort does not work correctly if n%k != 0 AND the last number in the last file is the largest number out of all the given data. If this condition is true, the program adds a -1 to the end of the output file and I'm not sure why. 
