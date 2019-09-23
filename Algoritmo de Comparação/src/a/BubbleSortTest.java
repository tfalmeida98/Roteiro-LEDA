package a;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testBubbleSort() {
		Integer[] array = {3, 5 ,1 ,123 , 6, 1};
		Integer[] auxBubble = Arrays.copyOf(array, array.length);
		Integer[] auxInsertion = Arrays.copyOf(array, array.length);
		Integer[] auxInsertion2 = Arrays.copyOf(array, array.length);
		Integer[] auxSelection = Arrays.copyOf(array, array.length);
		BubbleSort bubble = new BubbleSort();
		Selection selection = new Selection();
		Insertion2 insert = new Insertion2();
		//Selection
		bubble.BubbleSort(auxBubble);
		selection.Insertion(auxInsertion);
		insert.Insertion(auxInsertion2);
		System.out.println(Arrays.toString(auxBubble));
		System.out.println(Arrays.toString(auxInsertion));
		System.out.println(Arrays.toString(auxInsertion2));
	}

}
