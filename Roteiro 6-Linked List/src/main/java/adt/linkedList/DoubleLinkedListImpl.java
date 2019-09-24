package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			this.insert(element);
		} else {
			T data = getHead().getData();
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, getHead());
			this.head = newHead;

		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			SingleLinkedListNode<T> newHead = getHead().getNext();
			setHead(newHead);
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			int k = size;
			SingleLinkedListNode<T> aux = getHead();
			while (k > 1) {
				aux = aux.getNext();
				k--;
			}
			aux.data = aux.getNext().getData();
			aux.next = aux.getNext();
		}

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {

		this.last = last;
	}



}
