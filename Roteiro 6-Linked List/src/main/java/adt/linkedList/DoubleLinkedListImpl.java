package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.setHead(new DoubleLinkedListNode<>());
		this.setLast((DoubleLinkedListNode<T>) this.head);
	}

	@Override
	public void insertFirst(T element) {

		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>();
		DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T>();
		node.setNext(this.head.getNext());
		node.setData(this.head.getData());
		node.setPrevious(nodeNIL);

		((DoubleLinkedListNode<T>) getHead()).setPrevious(node);

		if (this.getHead().isNIL()) {
			setLast(node);
		}

		setHead(node);
		super.size++;
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>();
		node.setData(element);
		DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T>();
		node.setNext(nodeNIL);
		
		getLast().setNext(node);
		node.setPrevious(getLast());

		if (getHead().isNIL())
			setHead(node);

		setLast(node);
		super.size++;
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) ((DoubleLinkedListNode<T>) getHead()).getNext();
			setHead(node);
			if (getHead().isNIL())
				setLast(node);

			DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T>();
			node.setPrevious(nodeNIL);
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			DoubleLinkedListNode<T> node = getLast().getPrevious();
			if (node.isNIL())
				setHead(node);

			DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T>();
			node.setNext(nodeNIL);
		}

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	public static void main(String[] args) {
		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();

		list.insert(3);
		System.out.println(list.getLast());
		list.insert(2);
		System.out.println(list.size);
		System.out.println(Arrays.toString(list.toArray()));

	}

}
