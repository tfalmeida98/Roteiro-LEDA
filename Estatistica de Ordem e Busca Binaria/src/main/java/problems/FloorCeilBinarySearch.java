package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		int left = 0;
		int right = array.length - 1;
		return (findFloor(array, x, left, right));

	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		int left = 0;
		int right = array.length - 1;
		return findCeil(array, x, left, right);
	}

	private Integer findFloor(Integer[] array, Integer x, int left, int right) {
		int n = (left + right) / 2;

		if (left > right) {
			if (right < 0)
				return null;

			return array[right];
		}

		if (array[n] == x)
			return array[n];

		else {
			if (array[n] > x) {
				return findFloor(array, x, left, n - 1);
			} else {
				return findFloor(array, x, n + 1, right);
			}
		}
	}

	private Integer findCeil(Integer[] array, Integer x, int left, int right) {
		int n = (left + right) / 2;

		if (left > right) {
			if (left > array.length - 1)
				return null;

			return array[left];
		}

		if (array[n] == x)
			return array[n];

		else {
			if (array[n] > x) {
				return findCeil(array, x, left, n - 1);
			} else {
				return findCeil(array, x, n + 1, right);
			}
		}

	}

}
