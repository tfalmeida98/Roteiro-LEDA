package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
      root.setParent(new BSTNode<T>());
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
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

   @Override
   public BSTNode<T> search(T element) {
      return auxSearch(root, element);

   }

   private BSTNode<T> auxSearch(BSTNode<T> node, T element) {
      BSTNode aux = new BSTNode<T>();
      if (!node.isEmpty()) {
         if (node.getData().compareTo(element) == 0)
            aux = node;
         else if (node.getData().compareTo(element) < 0)
            aux = auxSearch((BSTNode<T>) node.getRight(), element);
         else if (node.getData().compareTo(element) > 0)
            aux = auxSearch((BSTNode<T>) node.getLeft(), element);
      }
      return aux;
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

   }

   @Override
   public BSTNode<T> maximum() {
      return maximum(root);
   }

   private BSTNode<T> maximum(BSTNode<T> node) {
      BSTNode<T> aux = null;
      if (!isEmpty()) {

         if (node.getRight().isEmpty()) {
            aux = node;
         } else {
            aux = maximum((BSTNode<T>) node.getRight());
         }
      }
      return aux;
   }

   @Override
   public BSTNode<T> minimum() {
      return minimum(root);

   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      BSTNode<T> aux = null;

      if (!isEmpty()) {
         if (node.getLeft().isEmpty())
            aux = node;
         else
            aux = minimum((BSTNode<T>) node.getLeft());
      }

      return aux;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> node = search(element);
      BSTNode<T> aux = null;

      if (node.isEmpty())
         return null;

      if (!node.getRight().isEmpty())
         aux = minimum((BSTNode<T>) node.getRight());

      else {
         aux = (BSTNode<T>) node.getParent();
         while (aux != null && node.equals(aux.getRight())) {
            node = aux;
            aux = (BSTNode<T>) aux.getParent();
         }

      }

      if (aux.isEmpty())
         aux = null;
      return aux;

   }

   @Override
   public BSTNode<T> predecessor(T element) {
      if (element == null)
         return null;
      BSTNode<T> node = search(element);
      BSTNode<T> aux = null;

      if (node.isEmpty())
         return aux;

      else if (!node.getLeft().isEmpty())
         aux = maximum((BSTNode<T>) node.getLeft());

      else {
         aux = (BSTNode<T>) node.getParent();
         while (aux != null && node.equals(aux.getLeft())) {
            node = aux;
            aux = (BSTNode<T>) aux.getParent();

         }
      }

      if (aux.isEmpty())
         aux = null;

      return aux;
   }

   @Override
   public void remove(T element) {
      if (element == null)
         return;
      BSTNode<T> node = search(element);

      removeNode(node);
   }

   private void removeNode(BSTNode<T> node) {

      if (node == null || node.isEmpty())
         return;

      if (node.isLeaf()) { // Folha
         node.setData(null);
         return;

      } else if ((!node.getLeft().isEmpty()) && (!node.getRight().isEmpty())) { // Possui dois filhos
         removeTwoChildren(node);
      } else {

         removeOneChild(node);
      }
   }

   private void removeTwoChildren(BSTNode node) {
      BSTNode aux = sucessor((T) node.getData());

      if (aux == null) {
         return;
      }
      node.setData((T) aux.getData());
      removeNode(aux);

   }

   private void removeOneChild(BSTNode node) {
      if (node.getParent() == null) { // root
         if (!node.getLeft().isEmpty()) {
            node.getLeft().setParent(null);
            root = (BSTNode<T>) node.getLeft();
            return;

         } else {
            node.getRight().setParent(null);
            root = (BSTNode<T>) node.getRight();
            return;
         }
      }

      BSTNode aux = null;
      if (node.getLeft().isEmpty()) // Possui um filho
         aux = (BSTNode<T>) node.getRight();
      else
         aux = (BSTNode<T>) node.getLeft();

      aux.setParent(node.getParent());

      if (node.equals(node.getParent().getLeft())) {
         node.getParent().setLeft(aux);

      } else if (node.equals(node.getParent().getRight())) {
         node.getParent().setRight(aux);
      }

   }

   @Override
   public T[] preOrder() {
      ArrayList<T> list = new ArrayList<T>();
      getPreOrder(root, list);

      int size = list.size();
      T[] array = (T[]) new Comparable[size];

      for (int i = 0; i < size; i++) {
         array[i] = list.get(i);
      }

      return array;

   }

   private void getPreOrder(BSTNode<T> node, ArrayList<T> array) {

      if (!node.isEmpty()) {
         array.add(node.getData());
         getPreOrder((BSTNode<T>) node.getLeft(), array);
         getPreOrder((BSTNode<T>) node.getRight(), array);
      }

   }

   @Override
   public T[] order() {
      ArrayList<T> list = new ArrayList<T>();
      getOrder(root, list);

      int size = list.size();
      T[] array = (T[]) new Comparable[size];

      for (int i = 0; i < size; i++) {
         array[i] = list.get(i);
      }

      return array;

   }

   private void getOrder(BSTNode<T> node, ArrayList<T> array) {
      if (!node.isEmpty()) {
         getOrder((BSTNode<T>) node.getLeft(), array);
         array.add(node.getData());
         getOrder((BSTNode<T>) node.getRight(), array);

      }
   }

   @Override
   public T[] postOrder() {
      ArrayList<T> list = new ArrayList<T>();
      getPosOrder(root, list);

      int size = list.size();
      T[] array = (T[]) new Comparable[size];

      for (int i = 0; i < size; i++) {
         array[i] = list.get(i);
      }

      return array;
   }

   private void getPosOrder(BSTNode<T> node, ArrayList<T> array) {
      if (!node.isEmpty()) {
         getPosOrder((BSTNode<T>) node.getLeft(), array);
         getPosOrder((BSTNode<T>) node.getRight(), array);
         array.add(node.getData());

      }
   }

   /**
    * This method is already implemented using recursion. You must understand how
    * it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

   /**
    * Pega o tamanho da Ã¡rvore de modo recursivo
    * 
    * @param node
    * @return altura da arvore
    */

}
