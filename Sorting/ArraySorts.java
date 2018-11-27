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

  private static int bookPartition(int a[], int lf, int rt, int pivot)
  {
    int tempItem;
    int save;
    int firstUnknown;
    int lastS1 = lf;
    int pivotValue = a[pivot];
    boolean duplicate = true;

    tempItem = a[lf];
    a[lf] = a[pivot];
    a[pivot] = tempItem;

    for(firstUnknown = lf + 1; firstUnknown <= rt; firstUnknown++){
      if(a[firstUnknown] < pivotValue){
        lastS1++;
        tempItem = a[firstUnknown];
        a[firstUnknown] = a[lastS1];
        a[lastS1] = tempItem;
      }else if(a[firstUnknown] == pivotValue){
        if(duplicate){
          lastS1++;
          save = a[firstUnknown];
          a[firstUnknown] = a[lastS1];
          a[lastS1] = save;
        }
        duplicate = !duplicate;
      }
    }

    tempItem = a[lf];
    a[lf] = a[lastS1];
    a[lastS1] = tempItem;

    return lastS1;
  }

  private static pair twoPointPartition(int a[], int lf, int rt, int pivot)
  {
    int leftPointer = lf;
    int rightPointer = rt;
    int pivotValue = a[pivot];
    int temp;

    while(leftPointer <= rightPointer){
      // System.out.println("in a loop");
      if(a[leftPointer] < pivotValue){
        leftPointer++;
      }else{
        if(a[rightPointer] > pivotValue){
          rightPointer--;
        }else{
          //swap
          temp = a[leftPointer];
          a[leftPointer] = a[rightPointer];
          a[rightPointer] = temp;

          leftPointer++;
          rightPointer--;
        }
      }
    }//while

    return new pair(leftPointer, rightPointer);
  }

  private static pair threePartition(int a[], int lf, int rt,
  int leftPivot, int rightPivot)
  {
    int temp;
    int lastSmall = lf;
    int firstUnknown = lf + 1;
    int firstBig = rt;
    boolean duplicate = true;

    temp = a[lf];
    a[lf] = a[leftPivot];
    a[leftPivot] = temp;

    temp = a[rt];
    a[rt] = a[rightPivot];
    a[rightPivot] = temp;

    if(a[lf] > a[rt]){
      temp = a[lf];
      a[lf] = a[rt];
      a[rt] = temp;
    }

    while(firstUnknown < firstBig){
      if(a[firstUnknown] < a[lf]){
        lastSmall++;
        temp = a[lastSmall];
        a[lastSmall] = a[firstUnknown];
        a[firstUnknown] = temp;
        firstUnknown++;
      }else if(a[firstUnknown] > a[rt]){
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
          }else if(a[firstUnknown] == a[rt]){
            firstBig--;
            temp = a[firstBig];
            a[firstBig] = a[firstUnknown];
            a[firstUnknown] = temp;
          }
        }else{
          firstUnknown++;
        }
        duplicate = !duplicate;
      }
    }

    temp = a[lf];
    a[lf] = a[lastSmall];
    a[lastSmall] = temp;

    temp = a[rt];
    a[rt] = a[firstBig];
    a[firstBig] = temp;

    return new pair(lastSmall, firstBig);
  }

  public static void QuickSort1(int a[], int n, int cutoff)
  {
    QuickSort1(a,0,n-1, cutoff);
    insertionSort(a, n);
  }

  private static void QuickSort1(int a[], int lf, int rt, int cutoff)
  {
    while(rt - lf + 1 >= cutoff){
      Random r = new Random();
      int pivot = r.nextInt((rt-lf)+1) + lf;
      pivot = bookPartition(a,lf,rt, pivot);
      int leftSize = (pivot - 1) - lf;
      int rightSize = rt - (pivot + 1);
      if(leftSize < rightSize){
        QuickSort1(a,lf,pivot-1,cutoff);
        lf = pivot+1;
      }else{
        QuickSort1(a,pivot+1,rt,cutoff);
        rt = pivot-1;
      }
    }
  }

  public static void QuickSort2(int a[], int n, int cutoff)
  {
    QuickSort2(a,0,n-1,cutoff);
    insertionSort(a,n);
  }

  private static void QuickSort2(int a[], int lf, int rt, int cutoff)
  {
    pair p;
    while(rt - lf + 1 >= cutoff){
      Random r = new Random();
      int pivot = r.nextInt((rt-lf)+1) + lf;
      p = twoPointPartition(a,lf,rt,pivot);
      int leftSize = (p.right) - lf;
      int rightSize = rt - (p.left);
      if(leftSize < rightSize){
        QuickSort2(a,lf,p.right,cutoff);
        lf = p.left;
      }else{
        QuickSort2(a,p.left,rt,cutoff);
        rt = p.right;
      }
    }

  }

  public static void QuickSort3(int a[], int n, int cutoff)
  {
    QuickSort3(a,0,n-1,cutoff);
    insertionSort(a,n);
  }


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

  public static void QuickSort5(int a[], int n, int cutoff)
  {
    QuickSort5(a,0,n-1,cutoff);
    insertionSort(a,n);
  }

  private static void QuickSort5(int a[], int lf, int rt, int cutoff)
  {
    while(rt - lf + 1 >= cutoff){
      Random r = new Random();
      int pivot1 = r.nextInt((rt-lf)+1) + lf;
      int pivot2 = r.nextInt((rt-lf)+1) + lf;
      pair pivot = threePartition(a,lf,rt,pivot1,pivot2);
      int leftSize = pivot.left - lf;
      int middleSize = pivot.right - pivot.left - 1;
      int rightSize = rt - pivot.right;
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

  public static void HeapSortTD(int a[], int n)
  {
    //i * 2 + 1 = left child
    //i * 2 + 2 = right child
    //parent = i-1/2
    //



  }

  public static void HeapSortBU(int a[], int n)
  {
    //
  }



  public static void AlmostQS1(int a[], int n, int cutoff)
  {
    QuickSort1(a,0,n-1, cutoff);
    // insertionSort(a, a.length);
  }

  public static void AlmostQS2(int a[], int n, int cutoff)
  {
    QuickSort2(a,0,n-1,cutoff);
    // insertionSort(a, a.length);
  }

  public static String myName()
  {
    return "John Aquino";
  }


    public static void main(String[] args){
      int size = 100;
      int arraySize = size;
      int[] a = new int[arraySize];
      Random r = new Random();
      for (int i = 0; i < arraySize; i++) {
          int rand = r.nextInt(size*10);
          a[i] = rand;
      }

      // QuickSort1(a,a.length,1);
      // QuickSort3(a,a.length,10);
      // int[] a = {25, 20 ,42};
      // insertionSort(a,a.length);
      // AlmostQS1(a,a.length,1);
      // QuickSort2(a,a.length,1);
      // QuickSort4(a,a.length,1);
      // AlmostQS2(a,a.length,1);
      QuickSort5(a,a.length,1);

      for (int i = 0; i < a.length; i++) {
        System.out.println(a[i]);
      }

      for(int j = 0; j < a.length - 1; j++){
        if(a[j] > a[j+1]){
          System.out.println("bad at " + j);
        }
      }

    }
}
