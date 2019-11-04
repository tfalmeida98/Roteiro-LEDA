package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */

// Yuri esteve aqui
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
   }

   @Override
   public T[] sort(T[] array) {

      while (!this.isEmpty()) {
         this.remove(this.getRoot().getData());
      }

      for (T i : array) {
         this.insert(i);
      }

      return this.order();
   }

   @Override
   public T[] reverseOrder() {
      ArrayList<T> list = new ArrayList<T>();
      reverseOrder(root, list);

      int size = list.size();
      T[] array = (T[]) new Comparable[size];

      for (int i = 0; i < size; i++) {
         array[i] = list.get(i);
      }

      return array;

   }

   private void reverseOrder(BSTNode node, ArrayList<T> array) {
      if (!node.isEmpty()) {
         reverseOrder((BSTNode<T>) node.getRight(), array);
         array.add((T) node.getData());
         reverseOrder((BSTNode<T>) node.getLeft(), array);

      }
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
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
      if (comparator.compare(element, node.getData()) > 0)
         insert((BSTNode<T>) node.getRight(), element);
      if (comparator.compare(element, node.getData()) < 0)
         insert((BSTNode<T>) node.getLeft(), element);

   }
   

}
