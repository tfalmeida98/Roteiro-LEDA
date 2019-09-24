package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (! isFull()) 
			list.insert(element);
		else
			throw new QueueOverflowException();
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (!isEmpty()) {
			SingleLinkedListNode<T> head =(SingleLinkedListNode) ((SingleLinkedListImpl<T>) list).getHead();
			list.removeFirst();
			return (T) head.getData();
		}else
			throw new QueueUnderflowException();
	}

	@Override
	public T head() {
		T retorno = null;
		if (! isEmpty()) {
			SingleLinkedListNode<T> head =(SingleLinkedListNode) ((SingleLinkedListImpl<T>) list).getHead();
			retorno = (T) head.getData();
		}
		
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (((SingleLinkedListImpl<T>) list).getSize() == size);
	}
	
	public static void main (String[] args) {
		QueueDoubleLinkedListImpl<Integer> fila = new QueueDoubleLinkedListImpl<Integer>(3);
		fila.enqueue(3);
		
	}

}
