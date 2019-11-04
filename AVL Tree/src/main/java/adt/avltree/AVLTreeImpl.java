package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int retorno = 0;
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if (left != null && right != null)
			retorno = ( getHeight((BSTNode<T>) node.getLeft()) - getHeight((BSTNode<T>) node.getRight()));
		else if (right != null)
			retorno = - getHeight((BSTNode<T>) node.getRight());
		else if (left != null)
			retorno = getHeight((BSTNode<T>) node.getLeft());
		
		return retorno;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if (calculateBalance(node) > 1) {
			if (left != null && calculateBalance(left) >= 1 ) {
				leftRotation(node);
			} else if (left != null && calculateBalance(left) <= -1 ) {
				leftRotation(left);
				rightRotation(node);
			}
				
		}else if(calculateBalance(node) < -1) {
			if (right != null && calculateBalance(right) <= -1) {
				rightRotation(node);
			} else if(right != null && calculateBalance(right) >= 1) {
				rightRotation(right);
				leftRotation(node);
			}
		}
		
	}
	
	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		node = pivot;
	}
	
	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		node = pivot;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (!node.isEmpty())
			rebalance(node);
		if(parent != null)
			rebalanceUp(parent);
	}
	
	@Override
	public void insert(T element) {
		insert(root, element);
	}
	
	
	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode());
			node.setRight(new BSTNode());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);

		}
		if (node.getData().compareTo(element) < 0)
			insert((BSTNode<T>) node.getRight(), element);
		if (node.getData().compareTo(element) > 0)
			insert((BSTNode<T>) node.getLeft(), element);

		rebalanceUp(node);
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);


		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);

				
			} else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				if (parent != null && parent.getLeft() != null && parent.getRight() != null) {
					if (!parent.getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							parent.setRight(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setRight(node.getRight());
							node.getRight().setParent(parent);
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							parent.setLeft(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setLeft(node.getRight());
							node.getRight().setParent(parent);
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
				rebalanceUp(node);
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}
	
	
	@Override
	public int height() {
		return getHeight(root) - 1;

	}

	private int getHeight(BSTNode<T> node) {
		int altura = 0;
		int alturaDireita = 0;
		int alturaEsquerda = 0;
		if (!node.isEmpty()) {
			alturaDireita = getHeight((BSTNode<T>) node.getRight());
			alturaEsquerda = getHeight((BSTNode<T>) node.getLeft());

			if (alturaDireita > alturaEsquerda)
				altura = 1 + alturaDireita;
			else
				altura = 1 + alturaEsquerda;
		}
		return altura;
	}
	
	
}
