package problems;

import orderStatistic.StatiscOrder;

public class FloorCeilBinary implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		int left = 0;
		int right = array.length - 1;

		return findFloor(array, x, left, right);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		int left = 0;
		int right = array.length - 1;
		return findCeil(array, x, left, right);
	}

	private Integer findFloor(Integer[] array, Integer x, int left, int right) {

		if (left >= right) {
			if (right > 0)
				return array[right];
			else
				return null;
		}

		int middle = (left + right) / 2;

		if (array[middle] == x)
			return x;

		if (array[middle] < x)
			return findFloor(array, x, middle + 1, right);
		else
			return findFloor(array, x, left, middle - 1);

	}

	private Integer findCeil(Integer[] array, Integer x, int left, int right) {
		if (left > right) {
			if (right < 0)
				return null;
				
			else
				return array[right];
		}

		int middle = (left + right) / 2;

		if (array[middle] == x)
			return array[middle];

		if (array[middle] > x)
			return findCeil(array, x, left, middle - 1);
		else
			return findCeil(array, x, middle + 1, right);

	}

	public static void main(String[] args) {
		FloorCeilBinary order = new FloorCeilBinary();
		Integer[] array = { 2, 4, 5, 6, 7 };
		System.out.println(order.ceil(array, 3));
	}

}
