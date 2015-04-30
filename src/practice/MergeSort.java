package practice;

public class MergeSort {
    int[] arr;
    
    public MergeSort(int[] arr) {
    	this.arr = arr;
    	sort(0, arr.length - 1);
    }
    
	public void sort(int left, int right) {
		if ((right - left) < 2) {
			return;
		}
		int mid = (left + right) / 2;

		sort(0, mid -1 );
		sort(mid, right);
		merge(left, mid, right);
	}

	public void merge(int left, int mid, int right) {
		int[] copy = new int[right - left + 1];
		int i = left;
		int j = mid;
		int index = 0;
		
		while (i < mid && j <= right) {
			if (arr[i] < arr[j]) {
				copy[index] = arr[i];
				index++;
				i++;
			} else {
				copy[index] = arr[j];
				index++;
				j++;
			}
		}
		while (i < mid) {
			copy[index] = arr[i];
			index++;
			i++;
		}
		while (j <= right) {
			copy[index] = arr[j];
			index++;
			j++;
		}
		
		for (int k = 0; k < copy.length; k++) {
			arr[left + k] = copy[k];
		}
	}
	
	public static void main(String[] args) {
		int[] input = new int[] {5,8,3,9,7,5,2,6,4,1,0,6};
		MergeSort ms = new MergeSort(input);
		ms.sort(0, input.length - 1);
		
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i] + " ");
		}
	}
}
