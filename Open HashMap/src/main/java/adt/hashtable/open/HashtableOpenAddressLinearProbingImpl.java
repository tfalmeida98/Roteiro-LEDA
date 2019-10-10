package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (!isFull()) {
			HashFunctionLinearProbing<T> hash = (HashFunctionLinearProbing<T>) hashFunction;

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
			HashFunctionLinearProbing<T> hash = (HashFunctionLinearProbing<T>) hashFunction;

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

			HashFunctionLinearProbing<T> hash = (HashFunctionLinearProbing<T>) hashFunction;
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

			HashFunctionLinearProbing<T> hash = (HashFunctionLinearProbing<T>) hashFunction;
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
