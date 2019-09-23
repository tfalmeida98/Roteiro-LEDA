package adt.linkedList;

import java.util.ArrayList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	private int size;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return (head.isNIL());
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T search(T element) {
		T elementFinded = null;

		if (head.equals(element))
			elementFinded = head.getData();
		else {
			SingleLinkedListNode<T> auxHead = head;
			while (!auxHead.next.isNIL()) {
				auxHead.next = auxHead.getNext();
				if (auxHead.equals(element))
					elementFinded = auxHead.getData();
			}
		}

		return elementFinded;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> nodeNIL = new SingleLinkedListNode<T>(null, null);

		SingleLinkedListNode<T> auxHead = head;
		while (!auxHead.next.isNIL()) {
			auxHead.next = auxHead.getNext();
		}

		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, nodeNIL);
		auxHead.setNext(newNode);
		size++;

	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.getData().equals(element)) {
				setHead(head.next);
				size --;
			} else {
				SingleLinkedListNode<T> auxHead = head;
				
				while (!auxHead.next.isNIL()) {
					auxHead.next = auxHead.getNext();
					if(auxHead.getData().equals(element)) {
						SingleLinkedListNode<T> newNode = auxHead.getNext();
						auxHead = newNode;
						size --;
					}
					
				}
				
			}
		}

	}

	@Override
	public T[] toArray() {
		ArrayList arrayList = new ArrayList<T>();
		
		SingleLinkedListNode<T> auxHead = head;
		
		while (!auxHead.next.isNIL()) {
			arrayList.add(auxHead.getData());
			auxHead.next = auxHead.getNext();
		}
		T[] array = (T[]) arrayList.toArray();
		return array;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}


}
