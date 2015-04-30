package practice;

public class QuickSort {
	int[] arr;
	int k;
	
  public QuickSort(int[] arr, int k) {
	  this.arr = arr;
	  this.k = k;
  }
  
  public int partition(int left, int right) {
    int pivot = (left + right) / 2;
    int i = left;
    int j = right;
    
    while (i <= j) {
    	while (arr[i] < arr[pivot]) {
    		i++;
    	}
    	while (arr[j] > arr[pivot]) {
    		j--;
    	}
    	if (i <= j) {
   		  swap(i, j);
   		  i++;
   		  j--;
    	}
    }
    return i;
  }
  
  public void quickSort(int left, int right) {
	  int i = partition(left, right);
	  
	  if (left < (i-1)) {
   	    quickSort(left, i - 1);
	  }
	  if (i < right) {
  	   quickSort(i, right);
	  }
  }
  
  public void quickSortToK(int left, int right) {
	  int i = partition(left, right);
	  System.out.print("k=" + i + ": ");
	  for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + " ");
		}
	  System.out.println();

	  if (i == k) {
	  	return;
	  }

	  if (i > k && left < (i-1)) {
   	    quickSortToK(left, i - 1);
	  } else if (i < k && right > i) {
  	   quickSortToK(i, right);
	  }
  }

  private void swap(int i, int j) {
	  int tmp = arr[i];
	  arr[i] = arr[j];
	  arr[j] = tmp;
  }
  
	public static void main(String[] args) {		
		int[] input = new int[] {5,8,3,9,7,5,2,6,4,1,0,6};
		QuickSort qs = new QuickSort(input, 4);
		qs.quickSortToK(0, (input.length - 1));
		
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
		
	}
}
