package adt.skipList;

import java.util.ArrayList;
import java.util.List;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;
	private int size;

	protected double PROBABILITY = 0.5;
	
	/**
	 * Aloooo
	 * Estou ligando de um orelhão
	 * Ta um barulho uma confusão
	 * mas eu preciso tanto lhe falaaar
	 * 
	 * Depois das 6pm, tô te esperabdo no mesmo lugar
	 * Pois estou louco pra te encontrar
	 * pra outra noite de aventura
	 * 
	 * Fui eu quem fiz amor por brincadeira e acabei me apaixonando
	 * Meu amor, eu me rendo a você
	 * pois estou te amando
	 * Você deixou em mim uma saudade com seu jeito de fazer paixão
	 * Você fez maravilhas loucuras no meu coração
	 * 
	 * Um beijo pra você
	 * não posso demorar
	 * To numa ligação urbana
	 * tem mais gente pra ligar (8)
	 * 
	 * ajeita essa postura ai, rapaz ;)
	 * 
	 */

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
		size = 0;
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T> search = search(key);
		if (search != null) {
			search.setValue(newValue);
			return;
		}

		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;

		for (int i = this.maxHeight - 1; i > -1; i--) {
			while ((node.getForward(i).getValue() != null) && (node.getForward(i).getKey() < key)) {
				node = node.getForward(i);
			}

			update[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			node.setValue(newValue);
		} else {
			size++;
			node = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < node.height(); i++) {
				node.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = node;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = root;
		for (int i = this.maxHeight - 1; i > -1; i--) {
			while ((node.getForward(i).getValue() != null) && (node.getForward(i).getKey() < key)) {
				node = node.getForward(i);
			}
			update[i] = node;
		}
		node = node.getForward(0);
		if (node.getKey() == key) {
			size--;
			for (int i = 0; i < maxHeight; i++) {
				if (update[i].getForward(i).equals(node)) {
					update[i].getForward()[i] = node.getForward(i);
				}

			}
		}

	}

	@Override
	public int height() {
		return size;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> node = root;

		for (int i = maxHeight - 1; i > -1; i--) {
			while (node.getForward(i).getKey() < key) {
				node = node.getForward(i);
			}
		}
		node = node.getForward(0);

		SkipListNode<T> retorno = null;
		if (node.getKey() == key)
			retorno = node;

		return retorno;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		List<SkipListNode<T>> list = new ArrayList<>();
		SkipListNode<T> node = this.root;

		while (node.getKey() < Integer.MAX_VALUE) {
			list.add(node);
			node = node.getForward(0);
		}

		list.add(node);

		SkipListNode<T>[] array = new SkipListNode[list.size()];
		return list.toArray(array);
	}

}
