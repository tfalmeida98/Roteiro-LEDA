package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		previous = null;

	}
	
//Monitor Yuri esteve aqui -m "Vou monitorar seu roteiro"
//Monitor Luiz esteve aqui -m "Vou zerar seu roteiro"
	
	@Override
	public void insertFirst(T element) {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<T>();
			node.setData(this.getData());
			node.setNext(this.getNext());
			this.setData(element);
			this.setNext(node);
			node.setPrevious(this);
			
		}else {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
		}
	}
	
	@Override
	public void insert(T element) {
		if (!isEmpty()) 
			getNext().insert(element);
		else {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.setData(getNext().getData());
			if (!isEmpty()) {
				this.setNext((RecursiveDoubleLinkedListImpl<T>) getNext().getNext());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (! isEmpty()) {
			if (getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
			}else {
				((DoubleLinkedList<T>) getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
