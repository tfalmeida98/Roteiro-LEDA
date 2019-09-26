package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;
import adt.linkedList.RecursiveSingleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			top.insert(element);
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T data = top.toArray()[top.size() - 1];
			top.removeLast();
			return data;
		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		return ((RecursiveSingleLinkedListImpl<T>) top).getData();
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return top.size() >= size;
	}


}
