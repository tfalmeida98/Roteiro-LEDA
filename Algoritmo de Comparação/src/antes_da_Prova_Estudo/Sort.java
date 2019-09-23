package antes_da_Prova_Estudo;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		Integer[] array1 = { 1, 0, 3, 2, 4, 5, 9 , -8, 16, 0 };
		Integer[] array2 = { 1, 0, 3, 2, 4, 5, 9 , -8, 16, 0 };
		Integer[] array3 = { 1, 0, 3, 2, 4, 5, 9 , -8, 16, 0 };
		Integer[] array4 = { 1, 0, 3, 2, 4, 5, 9 , -8, 16, 0 };

		Sort sort = new Sort();
		sort.bubble(array1);
		sort.cocktail(array2);
		sort.selection(array3);
		sort.insertion(array4);

		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));

		System.out.println(sort.orderStatistc(array4, 400));
	}

	public void bubble(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[i])
					swap(array, i, j);
			}
		}
	}

	public void cocktail(Integer[] array) {
		boolean change = false;
		if (array.length > 1)
			change = true;

		while (change) {
			change = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
					change = true;
				}
			}
			for (int j = array.length - 1; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
					change = true;
				}
			}
		}
	}

	public void selection(Integer[] array) {
		int min;
		for (int i = 0; i < array.length - 1; i++) {
			min = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < array[min])
					min = j;
			}
			swap(array, i, min);
		}
	}

	public void insertion(Integer[] array) {
		int j;
		int key;
		for (int i = 1; i < array.length; i++) {
			j = i - 1;
			key = array[i];
			while (j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	public int orderStatistc(Integer[] array, int x) {
		return orderStatistc(array, x, 0, array.length - 1);
	}

	private int orderStatistc(Integer[] array, int x, int left, int right) {
		if (left > right)
			return -1;


		int middle = (left + right) / 2;
		if (array[middle] == x)
			return middle;

		if (array[middle] > x)
			return orderStatistc(array, x, left, middle - 1);
		else
			return orderStatistc(array, x, middle + 1, right);

	}

	private void swap(Integer[] array, int i, int j) {
		Integer aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

}
