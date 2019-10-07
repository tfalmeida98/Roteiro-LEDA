package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (!isFull()) {
			HashFunctionQuadraticProbing<T> hash = (HashFunctionQuadraticProbing<T>) hashFunction;

			boolean notFounded = true;
			int i = 0;

			while (i < tableSize && notFounded) {
				int index = Math.abs( hash.hash(element, i));
				if (table[index] == null || table[index].equals(deletedElement)) {
					table[index] = element;
					notFounded = false;
					elements++;
				} else {
					COLLISIONS++;
				}
				i++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			HashFunctionQuadraticProbing<T> hash = (HashFunctionQuadraticProbing<T>) hashFunction;

			boolean notFounded = true;
			int i = 0;
			while (i < size() && notFounded) {
				int index = Math.abs( hash.hash(element, i));
				if (table[index] != null && table[index].equals(element)) {
					table[index] = new DELETED();
					notFounded = false;
					elements--;
				}
				i++;
			}
		}
	}

	@Override
	public T search(T element) {
		T retorno = null;

		if (!isEmpty()) {

			HashFunctionQuadraticProbing<T> hash = (HashFunctionQuadraticProbing<T>) hashFunction;
			int i = 0;
			boolean notFounded = true;
			while (i < size() && notFounded) {
				int index = Math.abs( hash.hash(element, i));
				if (table[index] != null && table[index].equals(element)) {
					retorno = (T) table[index];
					notFounded = false;
				}

				i++;
			}
		}

		return retorno;
	}

	@Override
	public int indexOf(T element) {
		int elementIndex = -1;

		if (!isEmpty()) {

			HashFunctionQuadraticProbing<T> hash = (HashFunctionQuadraticProbing<T>) hashFunction;
			int i = 0;
			boolean notFounded = true;
			while (i < size() && notFounded) {
				int index = Math.abs( hash.hash(element, i));
				if (table[index] != null && table[index].equals(element)) {
					elementIndex = index;
					notFounded = false;
				}

				i++;
			}
		}

		return elementIndex;
	}

}
