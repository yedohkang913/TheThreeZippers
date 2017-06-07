/*=============================
  class MergeSort
  Description: sorts an array using MergeSort
  ==============================*/


public class MergeSort {

    /******************************************************
     * int[] merge(Word[], Word[]) 
     * Merges two input arrayss
     * Precond:  Input arrays are sorted in ascending order
     * Postcond: Input arrays unchanged, and 
     * output array sorted in ascending order.
     ******************************************************/
    private static Word[] merge( Word[] a, Word[] b ) 
    {
	// create a new array
	Word[] retArr = new Word[a.length + b.length];
	int aCounter = 0;
	int bCounter = 0;
	int index = 0;
        while (aCounter < a.length  && bCounter < b.length){
	    if (a[aCounter].alphaCompare(b[bCounter]) < 0) {
		retArr[index] = a[aCounter];
		index++;
		aCounter++;
	    }
	    else {
		retArr[index] = b[bCounter];
		index++;
		bCounter++;
	    }
	}
	if (aCounter < a.length){
	    for (int x = aCounter; x < a.length; x++){
		retArr[index] = a[x];
		index++;
	    }
	}
	else if (bCounter < b.length){
	    for (int x = bCounter; x < b.length; x++){
		retArr[index] = b[x];
		index++;
	    }
	}
	// System.out.println ("Merged List: " + printArray(retArr));
	return retArr;
    }//end merge()


    /******************************************************
     * Word[] sort(Word[]) 
     * Sorts input array using mergesort algorithm
     * Returns sorted version of input array (ascending)
     ******************************************************/
    public static Word[] sort( Word[] arr ) 
    {
	if (arr.length == 1){
	    return arr;
	}
	else{
	    Word[] leftHalf = new Word[arr.length/2];
	    for (int x= 0; x < leftHalf.length; x++){
		leftHalf[x] = arr[x];
	    }
	    Word[] rightHalf = new Word[(arr.length)-leftHalf.length];
	    int counter = 0;
	    for (int y = leftHalf.length; y<arr.length; y++){
		rightHalf[counter] = arr[y];
		counter++;
	    }
	    // System.out.print("Left Half: " + printArray(leftHalf));
	    // System.out.println("  Right Half: " + printArray(rightHalf));
	    return merge (sort (leftHalf), sort(rightHalf));
	}
    }//end sort()



    //-------------------HELPERS-------------------------
    //tester function for exploring how arrays are passed
    //usage: print array, mess(array), print array. Whaddayasee?
    /*
    public static void mess( Word[] a ) {
	for( int i = 0 ; i<a.length; i++ )
	    a[i] = 0;
    }

    //helper method for displaying an array
    public static String printArray( Word[] a ) {
	String retStr = "[";
	for( int i : a ){
	    retStr +=  i;
	    retStr += " ,";
	}
	retStr += "]";
	return retStr;
    }
    */
    //---------------------------------------------------
}//end class MergeSort
