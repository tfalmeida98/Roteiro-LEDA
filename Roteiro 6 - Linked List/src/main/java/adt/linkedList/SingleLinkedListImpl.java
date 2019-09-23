package adt.linkedList;

import java.util.ArrayList;
import java.util.Arrays;

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

		if (!isEmpty()) {
			SingleLinkedListNode<T> auxHead = head;

			while (!auxHead.isNIL()) {
				if (auxHead.getData().equals(element)) {
					elementFinded = auxHead.getData();
					break;
				}
				auxHead.next = auxHead.getNext();
			}

		}

		return elementFinded;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			SingleLinkedListNode<T> head = getHead();
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, head);
			setHead(newHead);
			
		} else {
			SingleLinkedListNode<T> auxHead = head;
			while (!auxHead.getNext().isNIL()) {
				auxHead = auxHead.getNext();

			}

			SingleLinkedListNode<T> nodeNIL = auxHead.getNext();
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, nodeNIL);
			auxHead.setNext(newNode);

		}
		size++;
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			SingleLinkedListNode<T> auxHead = head;

			boolean notChanged = true;
			while ((!auxHead.isNIL()) && notChanged) {
				if (auxHead.getData().equals(element)) {
					SingleLinkedListNode<T> next = auxHead.getNext();
					T data = next.getData();
					SingleLinkedListNode<T> newNext = next.getNext();
					auxHead.setData(data);
					auxHead.setNext(newNext);
					notChanged = false;
					size--;
					break;
					
				}
				auxHead = auxHead.getNext();
			}

		}

	}

	@Override
	public T[] toArray() {
		ArrayList<T> arrayList = new ArrayList<T>();

		SingleLinkedListNode<T> auxHead = head;

		while (!auxHead.isNIL()) {
			arrayList.add(auxHead.getData());
			auxHead = auxHead.getNext();
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

	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		SingleLinkedListImpl<Integer> teste = new SingleLinkedListImpl<Integer>();
		teste.insert(2);
		teste.insert(4);
		teste.insert(0);
		System.out.println(Arrays.toString(teste.toArray()));
		teste.remove(8);
		teste.insert(8);
		System.out.println(Arrays.toString(teste.toArray()));
		teste.remove(8);
		System.out.println(Arrays.toString(teste.toArray()));
		System.out.println(teste.getSize());
		teste.remove(2);
		System.out.println(Arrays.toString(teste.toArray()));
	}

}
