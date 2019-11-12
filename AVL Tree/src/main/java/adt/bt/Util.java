package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if (!parent.isEmpty()) {
			if (parent.getLeft().equals(node)) {
				parent.setLeft(right);
			} else {
				parent.setRight(right);
			}
		}
		
		BSTNode<T> leftChildRight = (BSTNode<T>) right.getLeft();
		node.setParent(right);
		node.setRight(leftChildRight);
		if (!leftChildRight.isEmpty()) 
			leftChildRight.setParent(node);
		
		right.setParent(parent);
		right.setLeft(node);
		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		if (!parent.isEmpty()) {
			if (parent.getLeft().equals(node)) {
				parent.setLeft(left);
			} else {
				parent.setRight(left);
			} 
		}
		
		BSTNode<T> rightChildLeft = (BSTNode<T>) left.getRight();
		node.setParent(left);
		node.setLeft(rightChildLeft);
		if (!rightChildLeft.isEmpty()) 
			rightChildLeft.setParent(node);
		
		left.setParent(parent);
		left.setRight(node);
		return left;
	}


	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
