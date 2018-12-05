// John Aquino, Comp282 Mon/Wed, Assignment 3
// Date: Decemeber 5th 2018
//
// Description: This program contains various sorting algorithms, some
// of which have slightly different implementations.
// Sorting algorithms are: InsertionSort, QuickSort, and HeapSort

import java.math.*;
import java.util.Random;

class pair{
  public int left, right;

  public pair(int left, int right)
  {
    this.left = left;
    this.right = right;
  }
}

class ArraySorts{

  /**
   * normal insertion sort, operates in O(n^2) time, operates by
   * shifting elements until array is sorted
   * @param a[] [array of integers]
   * @param n   [size of array]
   */
  public static void insertionSort(int a[], int n)
  {
    int save;
    int startPos, secondPos;

    for(startPos = 1; startPos < n; startPos++){
      save = a[startPos];
      secondPos = startPos - 1;
      while((secondPos >= 0) && (a[secondPos] > save)){
        a[secondPos + 1] = a[secondPos];
        secondPos--;
      }
      a[secondPos+1] = save;
    }
  }

  /**
   * Partition method used by QS1, QS3, and AlmostQS1
   * @param  a[]   [array of integers]
   * @param  lf    [left most element]
   * @param  rt    [right most element]
   * @param  pivot [position of pivot]
   * @return       [a partitioned array relative to pivot]
   */
  private static int bookPartition(int a[], int lf, int rt, int pivot)
  {
    int tempItem;
    int save;
    int firstUnknown;
    int lastS1 = lf;
    int pivotValue = a[pivot];
    boolean duplicate = true;

    //swap
    tempItem = a[lf];
    a[lf] = a[pivot];
    a[pivot] = tempItem;

    for(firstUnknown = lf + 1; firstUnknown <= rt; firstUnknown++){
      if(a[firstUnknown] < pivotValue){
        lastS1++;
        //swap
        tempItem = a[firstUnknown];
        a[firstUnknown] = a[lastS1];
        a[lastS1] = tempItem;
      }else if(a[firstUnknown] == pivotValue){
        //duplicate boolean toggle
        if(duplicate){
          lastS1++;
          //swap
          save = a[firstUnknown];
          a[firstUnknown] = a[lastS1];
          a[lastS1] = save;
        }
        duplicate = !duplicate;
      }//if
    }//for

    //swap
    tempItem = a[lf];
    a[lf] = a[lastS1];
    a[lastS1] = tempItem;

    return lastS1;
  }

  /**
   * Partition method used by QS2, QS4, and AlmostQS2
   * @param  a[]   [array of integers]
   * @param  lf    [left most element]
   * @param  rt    [right most element]
   * @param  pivot [position of pivot]
   * @return       [a pair of positions indicating where
   *                the two partitioned arrays start and end]
   */
  private static pair twoPointPartition(int a[], int lf, int rt, int pivot)
  {
    int leftPointer = lf;
    int rightPointer = rt;
    int pivotValue = a[pivot];
    int temp;

    while(leftPointer <= rightPointer){
      while(a[leftPointer] < pivotValue){
        leftPointer++;
      }//while
      while(a[rightPointer] > pivotValue){
        rightPointer--;
      }//while
      if(leftPointer <= rightPointer){
        //swap
        temp = a[leftPointer];
        a[leftPointer] = a[rightPointer];
        a[rightPointer] = temp;

        leftPointer++;
        rightPointer--;
      }//if
    }//while

    return new pair(leftPointer, rightPointer);
  }

  /**
   * Partition method used by QS5 and AlmostQS5
   * @param  a[]        [array of integers]
   * @param  lf         [left most element]
   * @param  rt         [right most element]
   * @param  leftPivot  [position of pivot for less than partition]
   * @param  rightPivot [position of pivot for greater than partition]
   * @return            [a pair of positions indicating where the 3
   *                    partition arrays start and end]
   */
  private static pair threePartition(int a[], int lf, int rt,
  int leftPivot, int rightPivot)
  {
    int temp;
    int lastSmall = lf;
    int firstUnknown = lf + 1;
    int firstBig = rt;
    boolean duplicate = true;

    //swap
    temp = a[lf];
    a[lf] = a[leftPivot];
    a[leftPivot] = temp;

    //swap
    temp = a[rt];
    a[rt] = a[rightPivot];
    a[rightPivot] = temp;

    //left size contains smaller values,
    //right side contains bigger values
    if(a[lf] > a[rt]){
      temp = a[lf];
      a[lf] = a[rt];
      a[rt] = temp;
    }

    while(firstUnknown < firstBig){
      if(a[firstUnknown] < a[lf]){
        //increment, swap, increment
        lastSmall++;
        temp = a[lastSmall];
        a[lastSmall] = a[firstUnknown];
        a[firstUnknown] = temp;
        firstUnknown++;
      }else if(a[firstUnknown] > a[rt]){
        //decrement, swap
        firstBig--;
        temp = a[firstBig];
        a[firstBig] = a[firstUnknown];
        a[firstUnknown] = temp;
      }else{
        if(duplicate){
          if(a[firstUnknown] == a[lf]){
            lastSmall++;
            temp = a[lastSmall];
            a[lastSmall] = a[firstUnknown];
            a[firstUnknown] = temp;
            firstUnknown++;
          }
        }else{
          firstUnknown++;
        }
        duplicate = !duplicate;
      }
    }

    //swap
    temp = a[lf];
    a[lf] = a[lastSmall];
    a[lastSmall] = temp;

    //swap
    temp = a[rt];
    a[rt] = a[firstBig];
    a[firstBig] = temp;

    return new pair(lastSmall, firstBig);
  }

  /**
   * Driver method for Quicksort, uses bookPartition,
   * will switch to insertionSort once
   * partitioned arrays are below cutoffValue
   * @param a[]    [array of integers]
   * @param n      [size of array]
   * @param cutoff [partitioned array's max size]
   */
  public static void QuickSort1(int a[], int n, int cutoff)
  {
    QuickSort1(a,0,n-1, cutoff);
    insertionSort(a, n);
  }

  /**
   * Uses BookPartition, and selects a random pivot position
   * Continues until cutoff is reached
   * @param a[]    [array of integers]
   * @param lf     [left most element]
   * @param rt     [right most element]
   * @param cutoff [partitioned array's max size]
   */
  private static void QuickSort1(int a[], int lf, int rt, int cutoff)
  {
    //while loop to avoid stack overflow
    while(rt - lf + 1 >= cutoff){
      //create random pivot
      int pivot = lf + (int)(Math.random() * (rt-lf+1));
      pivot = bookPartition(a,lf,rt, pivot);// partition array
      int leftSize = (pivot - 1) - lf;//calculate size of left partition
      int rightSize = rt - (pivot + 1);//calculate size of right partition
      //continue to sort the smaller partition, and "pretend" the other
      //partition is finished
      if(leftSize < rightSize){
        QuickSort1(a,lf,pivot-1,cutoff);
        lf = pivot+1;
      }else{
        QuickSort1(a,pivot+1,rt,cutoff);
        rt = pivot-1;
      }//if
    }//while
  }

  /**
   * Driver method for Quicksort, uses 2 pointer partition,
   * will switch to insertionSort once
   * partitioned arrays are below cutoffValue
   * @param a[]    [array of integers]
   * @param n      [size of array]
   * @param cutoff [partitioned array's max size]
   */
  public static void QuickSort2(int a[], int n, int cutoff)
  {
    QuickSort2(a,0,n-1,cutoff);
    insertionSort(a,n);
  }

  /**
   * Uses 2ptr partition, and selects a random pivot position
   * Continues until cutoff is reached
   * @param a[]    [array of integers]
   * @param lf     [left most element]
   * @param rt     [right most element]
   * @param cutoff [partitioned array's max size]
   */
  private static void QuickSort2(int a[], int lf, int rt, int cutoff)
  {
    pair p;
    //while loop to avoid stack overflow
    while(rt - lf + 1 >= cutoff){
      int pivot = lf + (int)(Math.random() * (rt-lf+1));
      p = twoPointPartition(a,lf,rt,pivot); // pair of pivots
      int leftSize = (p.right) - lf;
      int rightSize = rt - (p.left);
      //continue sorting smaller partition
      if(leftSize < rightSize){
        QuickSort2(a,lf,p.right,cutoff);
        lf = p.left;
      }else{
        QuickSort2(a,p.left,rt,cutoff);
        rt = p.right;
      }//if
    }//while

  }

  /**
   * Driver for QuickSort3, same logic as QuickSort1
   * @param a[]    [array of integers]
   * @param n      [size of array]
   * @param cutoff [partitioned array's max size]
   */
  public static void QuickSort3(int a[], int n, int cutoff)
  {
    QuickSort3(a,0,n-1,cutoff);
    insertionSort(a,n);
  }


  /*
  Exact same logic as QuickSort1, except pivot is left most element
   */
  private static void QuickSort3(int a[], int lf, int rt, int cutoff)
  {
    while(rt - lf + 1 >= cutoff){
      int pivot = lf;
      pivot = bookPartition(a,lf,rt, pivot);
      int leftSize = (pivot - 1) - lf;
      int rightSize = rt - (pivot + 1);
      if(leftSize < rightSize){
        QuickSort3(a,lf,pivot-1,cutoff);
        lf = pivot+1;
      }else{
        QuickSort3(a,pivot+1,rt,cutoff);
        rt = pivot-1;
      }
    }
  }


  public static void QuickSort4(int a[], int n, int cutoff)
  {
    QuickSort4(a,0,n-1,cutoff);
    insertionSort(a,n);
  }

  /*
  Exact same logic as QuickSort2, except pivot is the left most element
   */
  private static void QuickSort4(int a[], int lf, int rt, int cutoff)
  {
    pair p;
    while(rt - lf + 1 >= cutoff){
      int pivot = lf;
      p = twoPointPartition(a,lf,rt,pivot);
      int leftSize = (p.right) - lf;
      int rightSize = rt - (p.left);
      if(leftSize < rightSize){
        QuickSort4(a,lf,p.right,cutoff);
        lf = p.left;
      }else{
        QuickSort4(a,p.left,rt,cutoff);
        rt = p.right;
      }
    }
  }

  /**
   * Driver method for QuickSort5, uses threePartition and
   * continues until cutoff is reached
   * @param a[]    [array of integers]
   * @param n      [size of array]
   * @param cutoff [description]
   */
  public static void QuickSort5(int a[], int n, int cutoff)
  {
    QuickSort5(a,0,n-1,cutoff);
    insertionSort(a,n);
  }

  /**
   * Uses threePartition and selects 2 random pivots and divides the
   * array into 3 partitions and continues partitioning until cutoff
   * is reached
   * @param a[]    [array of integers]
   * @param lf     [left most element]
   * @param rt     [right most element]
   * @param cutoff [partitioned array's max size]
   */
  private static void QuickSort5(int a[], int lf, int rt, int cutoff)
  {
    //while loop to avoid stack overflow
    while(rt - lf + 1 >= cutoff){
      //select 2 random pivots
      int pivot1 = lf + (int)(Math.random() * (rt-lf+1));
      int pivot2 = lf + (int)(Math.random() * (rt-lf+1));
      pair pivot = threePartition(a,lf,rt,pivot1,pivot2);
      //calculate sizes
      int leftSize = pivot.left - lf;
      int middleSize = pivot.right - pivot.left - 1;
      int rightSize = rt - pivot.right;
      //continue sorting the smaller partition
      if((leftSize >= middleSize) && (leftSize >= rightSize)){
        QuickSort5(a,pivot.left+1,pivot.right-1,cutoff);
        QuickSort5(a,pivot.right+1,rt,cutoff);
        rt = pivot.left - 1;
      }else if((rightSize >= middleSize) && (rightSize >= leftSize)){
        QuickSort5(a,pivot.left+1,pivot.right-1,cutoff);
        QuickSort5(a,lf,pivot.left-1,cutoff);
        lf = pivot.right+1;
      }else{
        QuickSort5(a,lf,pivot.left-1,cutoff);
        QuickSort5(a,pivot.right+1,rt,cutoff);
        lf = pivot.left + 1;
        rt = pivot.right - 1;
      }
    }
  }

  /**
   * Used by HeapSortTD, builds intitial heap by trickling up
   * @param a[] [array of integers]
   * @param n   [size of array]
   */
  private static void BuildTD(int a[], int n)
  {
    int save;
    int currentPos;
    int parent;

    //start at root and check every node
    for(int startPos = 0; startPos < n; startPos++){
      currentPos = startPos; //intialize current position
      parent = (currentPos-1)/2; // calculate parent
      save = a[currentPos]; //save value in case of shift
      while((currentPos != 0) && (save > a[parent])){
        a[currentPos] = a[parent]; //shift
        currentPos = parent; //re-calculate
        parent = (currentPos-1)/2; //re-calculate
      }
      a[currentPos] = save;
    }
  }

  /**
   * used by both HeapSortBU and HeapSortTD, trickles specified node
   * down the heap as far as it can
   * @param a[]    [array of integer]
   * @param top    [starting position]
   * @param bottom [last element in heap]
   */
  private static void TrickleDown(int a[], int top, int bottom)
  {
    int leftChild = (top*2)+1;
    int rightChild = (top*2)+2;
    int greaterChild;
    int currentPos = top;
    int save = a[top];

    //if right child's position exceeds the heap, left child must
    //be the greater child
    if(rightChild > bottom){
      greaterChild = leftChild;
    }else{//determine which child has greater value
      if(a[leftChild] >= a[rightChild]){
        greaterChild = leftChild;
      }else{
        greaterChild = rightChild;
      }
    }

    //trickle down by shifting until condition's are broken
    while((currentPos < bottom) &&
    (greaterChild <= bottom) &&
    (save < a[greaterChild])){

      a[currentPos] = a[greaterChild]; //shift
      currentPos = greaterChild; //update position
      leftChild = (currentPos * 2) + 1; //re-calculate
      rightChild = (currentPos * 2) + 2;//re-calculate

      //re-determine greater child
      if(rightChild > bottom){
        greaterChild = leftChild;
      }else{
        if(a[leftChild] >= a[rightChild]){
          greaterChild = leftChild;
        }else{
          greaterChild = rightChild;
        }
      }//if

    }//while
    a[currentPos] = save;
  }

  /**
   * Builds heap by trickling down, and sorts heap by trickling down
   * @param a[] [array of integers]
   * @param n   [size of array]
   */
  public static void HeapSortBU(int a[], int n)
  {
    int temp;
    int last = n-1;

    //build heap
    for(int build = last; build >= 0; build--){
      TrickleDown(a, build, last);
    }//for

    //swap first and last element, and "re-build"
    for(int i = 0; i < n; i++){
      temp = a[0];
      a[0] = a[last];
      a[last] = temp;
      TrickleDown(a,0,last-1);
      last--;
    }//for
  }

  /**
   * Builds heap by trickling up, sorts heap by trickling down
   * @param a[] [array of integers]
   * @param n   [size of array]
   */
  public static void HeapSortTD(int a[], int n)
  {
    int temp;
    int last = n-1;

    //build heap
    BuildTD(a, n);

    //swap and "re-build" heap
    for(int i = 0; i < n; i++){
      temp = a[0];
      a[0] = a[last];
      a[last] = temp;
      TrickleDown(a,0,last-1);
      last--;
    }//for
  }

  /*
  QS 1 no call to insertion sort
   */
  public static void AlmostQS1(int a[], int n, int cutoff)
  {
    QuickSort1(a,0,n-1, cutoff);
    // insertionSort(a, a.length);
  }

  /*
  QS 2 no call to insertion sort
   */
  public static void AlmostQS2(int a[], int n, int cutoff)
  {
    QuickSort2(a,0,n-1,cutoff);
    // insertionSort(a, a.length);
  }

  /*
  QS 5 no call to insertion sort
   */
  public static void AlmostQS5(int a[], int n, int cutoff)
  {
    QuickSort5(a,0,n-1,cutoff);
    // insertionSort(a, a.length);
  }

  public static String myName()
  {
    return "John Aquino";
  }

}
