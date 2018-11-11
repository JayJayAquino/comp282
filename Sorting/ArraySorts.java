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

  public static int bookPartition(int a[], int lf, int rt, int pivot)
  {
    int tempItem;
    int firstUnknown;
    int lastS1 = lf;
    int pivotValue = a[pivot];

    tempItem = a[lf];
    a[lf] = a[pivot];
    a[pivot] = tempItem;

    for(firstUnknown = lf + 1; firstUnknown <= rt; firstUnknown++){
      if(a[firstUnknown] < pivotValue){
        lastS1++;
        tempItem = a[firstUnknown];
        a[firstUnknown] = a[lastS1];
        a[lastS1] = tempItem;
      }
    }

    tempItem = a[lf];
    a[lf] = a[lastS1];
    a[lastS1] = tempItem;

    return lastS1;
  }

  public static int twoPointPartition(int a[], int lf, int rt, int pivot)
  {

  }

  public static void QuickSort1(int a[], int n, int cutoff)
  {
    QuickSort1(a,0,n-1, cutoff);
    insertionSort(a, a.length);
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

  // public static void QuickSort2(int a[], int n, int cutoff)
  // {
  //   //
  // }

  public static void QuickSort3(int a[], int n, int cutoff)
  {
    QuickSort3(a,0,n-1,cutoff);
    insertionSort(a,n);
  }


  private static void QuickSort3(int a[], int lf, int rt, int cutoff)
  {
    while(rt - lf + 1 >= cutoff){
      Random r = new Random();
      int pivot = r.nextInt((rt-lf)+1) + lf;
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
  // public static void QuickSort4(int a[], int n, int cutoff)
  // {
  //   //
  // }
  // public static void QuickSort5(int a[], int n, int cutoff)
  // {
  //   //
  // }
  //
  public static void AlmostQS1(int a[], int n, int cutoff)
  {
    AlmostQS1(a,0,n-1, cutoff);
    // insertionSort(a, a.length);
  }

  private static void AlmostQS1(int a[], int lf, int rt, int cutoff)
  {
    while(rt - lf + 1 >= cutoff){
      Random r = new Random();
      int pivot = r.nextInt((rt-lf)+1) + lf;
      pivot = bookPartition(a,lf,rt, pivot);
      int leftSize = (pivot - 1) - lf;
      int rightSize = rt - (pivot + 1);
      if(leftSize < rightSize){
        AlmostQS1(a,lf,pivot-1,cutoff);
        lf = pivot+1;
      }else{
        AlmostQS1(a,pivot+1,rt,cutoff);
        rt = pivot-1;
      }
    }
  }

  public static String myName()
  {
    return "John Aquino";
  }

  //AlmostQS1
  //AlmostQS2

    public static void main(String[] args){
      int arraySize = 20;
      int[] a = new int[arraySize];
      Random r = new Random();
      for (int i = 0; i < arraySize; i++) {
          int rand = r.nextInt(100);
          a[i] = rand;
      }
      // QuickSort1(a,a.length,20);
      // int[] a = {20, 18, 100, 23, 34, 8, 3, 90, 9, 42, 24};
      // insertionSort(a,a.length);
      // AlmostQS1(a,a.length,1);
      // QuickSort3(a,a.length,20);

      for (int i = 0; i < a.length; i++) {
        System.out.println(a[i]);
      }
    }
}
