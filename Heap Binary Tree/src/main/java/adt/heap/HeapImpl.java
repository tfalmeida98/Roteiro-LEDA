package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar
	 * apenas o comparator e mandar reordenar a heap usando esse comparator. Assim
	 * os metodos da heap não precisam saber se vai funcionar como max-heap ou
	 * min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int i) {
		int max = i;
		int leftIndex = left(i);
		int rightIndex = right(i);

		
		if (leftIndex <= index && heap[leftIndex] != null && comparator.compare(heap[max], heap[leftIndex]) < 0)
			max = leftIndex;

		if (rightIndex <= index && heap[rightIndex] != null && comparator.compare(heap[max], heap[rightIndex]) < 0)
			max = rightIndex;

		if (max != i) {
			Util.swap(heap, max, i);
			heapify(max);
		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
			if (index == heap.length - 1) {
				heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
			}
			
			index += 1;
			heap[index] = element;
			int aux = index;
			while(comparator.compare(heap[parent(aux)], heap[aux]) < 0) {
				Util.swap(heap, parent(aux), aux);
				aux = parent(aux);
			}

		}
	}


	@Override
	public void buildHeap(T[] array) {
		heap = array;
		index = array.length - 1;
		for(int i = index/2; i >= 0; i--) {
			heapify(i);
		}

	}

	@Override
	public T extractRootElement() {
		T data = null;
		if (!isEmpty()) {
			data = heap[0];
			heap[0] = heap[index];
			heap[index] = null;
			index--;
			heapify(0);
		}
		return data;
	}

	@Override
	public T rootElement() {
		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		
		HeapImpl<T> heapAux = new HeapImpl<>((o1, o2) -> o1.compareTo(o2));
		heapAux.buildHeap(array);
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = array.length -1; i >= 0; i--) {
			aux[i] = heapAux.extractRootElement();
		}
		return aux;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
