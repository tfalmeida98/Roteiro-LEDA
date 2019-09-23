package orderStatistic;

public class StatiscOrder<T extends Comparable<T>> implements OrderStatistics<T>  {

	@Override
	public T getOrderStatistics(T[] array, int k) {
		if (array.length == 0)
			return null;
		
		T min = array[0];
		T max = array[0];
		
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(min) < 0){
				min = array[i];
			}
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
			
		}
		
		return getOrder(array, min, max, k);
	}

	
	private T getOrder(T[] array,T min,T max,int k) {
		if (k == 1)
			return min;
		
		if(min.equals(max))
			return null;
		
		T newMin = max;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(min) > 0 && array[i].compareTo(newMin) < 0)
				newMin = array[i];
		}
		
		
		return getOrder(array, newMin, max, k-1);
	}
	
	public static void main(String[] args) {
		StatiscOrder order = new StatiscOrder<Integer>();
		Integer[] array = {4, 5, 1, 9, 15};
		System.out.println(order.getOrderStatistics(array,3 ));
	}

}
