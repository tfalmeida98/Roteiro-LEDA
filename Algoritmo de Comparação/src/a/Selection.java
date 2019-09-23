package a;

public class Selection {

	public void Insertion(Integer[] array) {

		int min;
		for (int i = 0; i < array.length - 1; i++) {
			min = i;
			System.out.println(array[i]);
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, i, min);

		}
	}

	private void swap(Integer[] array, int a, int b) {
		int aux = array[b];
		array[b] = array[a];
		array[a] = aux;
	}

}
