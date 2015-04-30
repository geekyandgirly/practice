package practice;

public class HeapSort {
  int[] arr;
  
  public HeapSort(int[] arr) {
	  this.arr = arr;
  }
  
  public void sort() {
	  int end = arr.length;

	  // First heapify the entire array
	  for (int i = end / 2; i >=1; i--) {
		  heapifyRecursive(i, end);
		  
		  // Swap first element and last
		  swap(1, end);
	  }
	  
	  for (int j = (end -1); j >= 1; j--) {
		  heapifyRecursive(1, j);
		  swap(1, j);
	  }
  }
  
  public void heapify(int root, int end) {
	  int i = root;
	  while (i <= end / 2) {
		  
		  int leftChild = i * 2 - 1;
		  int rightChild = i  * 2;
		  if (arr[leftChild - 1] > arr[i - 1] && arr[leftChild - 1] > arr[rightChild - 1]) {
			  swap(leftChild, i);
			  i = leftChild;
		  } else if (arr[rightChild - 1] > arr[i - 1] && arr[rightChild - 1] >= arr[leftChild - 1]) {
			  swap(rightChild, i);
			  i = rightChild;
		  } else {
			  return;
		  }
	  }
  }
  
  public void heapifyRecursive(int root, int end) {
		  
	  int leftChild = root * 2 - 1;
	  int rightChild = root  * 2;
	  if (leftChild > end) {
		  return;
	  }
	  if (rightChild > end) {
		  if (arr[leftChild - 1] > arr[root - 1]) {
			  swap(leftChild, end);
			  return;
		  }
	  } else if (arr[leftChild - 1] > arr[root - 1] && arr[leftChild - 1] > arr[rightChild - 1]) {
		  swap(leftChild, root);
		  heapifyRecursive(leftChild, end);;
	  } else if (arr[rightChild - 1] > arr[root - 1] && arr[rightChild - 1] >= arr[leftChild - 1]) {
		  swap(rightChild, root);
		  heapifyRecursive(rightChild, end);;
	  } else {
		  return;
	  }
  }
  
  private void swap(int i, int j) {
	  int tmp = arr[i-1];
	  arr[i-1] = arr[j-1];
	  arr[j-1] = tmp;
  }
  
	public static void main(String[] args) {
		int[] input = new int[] {5,8,3,9,7,5,2,6,4,1,0,6};
		HeapSort hs = new HeapSort(input);
		
		hs.sort();
		
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
}
