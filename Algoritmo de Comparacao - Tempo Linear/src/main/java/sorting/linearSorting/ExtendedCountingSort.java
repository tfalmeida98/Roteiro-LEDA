package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] < min)
				min = array[i];
			if (array[i] > max)
				max = array[i];
		}
		
		int offset = -min;
		
		Integer[] resultArray = new Integer[max + offset + 1];
		zeraArray(resultArray);
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			resultArray[array[i] + offset] += 1; 
		}
		
		fixArray(array, resultArray, offset);
				
	}
	
	private void zeraArray(Integer[] array) {
		for (int i = 0; i < array.length; i++ ) {
			array[i] = 0;
		}
	}
	
	private void fixArray(Integer[] array, Integer[] resultArray, int offset) {
		int indice = 0;
		for (int i = 0; i < resultArray.length; i++) {
			while (resultArray[i] > 0) {
				array[indice] = i - offset;
				resultArray[i] -= 1;
				indice ++;
			}
		}
	}

}