package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			this.insert(element);
		} else {
			T data = getHead().getData();
			DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T> ( ) ;
			DoubleLinkedListNode<T>newHead = new DoubleLinkedListNode<T>(element, nodeNIL, nodeNIL);
			DoubleLinkedListNode<T> newSecond = new DoubleLinkedListNode<T>(data, getHead().getNext(), nodeNIL)
		}
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeLast() {
		DoubleLinkedListNode<T> previousLast = last.getPrevious();
		DoubleLinkedListNode<T> nodeNIL = new DoubleLinkedListNode<T> ( ) ;
		this.last = nodeNIL;
		last.setPrevious(previousLast);
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		
		this.last = last;
	}

}