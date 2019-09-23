package a;

public class BubbleSort {
	
	public void BubbleSort(Integer[] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if (array[j] > array[i])
					swap(array, i, j);
			}
		}
	}
	
	private void swap(Integer[] array,int a, int b) {
		int aux = array[b];
		array[b] = array[a];
		array[a] = aux;
	}

}
