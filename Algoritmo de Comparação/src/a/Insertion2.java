package a;

public class Insertion2 {
	public void Insertion(Integer array[]) {
		int key;
		int j;
		for(int i = 0; i < array.length; i++) {
			key = array[i];
			j = i - 1;
			while(j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				j --;
			}
			array[j +1] = key;

		}
	}

	

}
