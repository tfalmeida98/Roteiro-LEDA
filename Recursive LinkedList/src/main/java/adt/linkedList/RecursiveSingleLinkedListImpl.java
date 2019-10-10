package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

   protected T data;
   protected RecursiveSingleLinkedListImpl<T> next;

   public RecursiveSingleLinkedListImpl() {
      data = null;
      next = null;

   }

   @Override
   public boolean isEmpty() {
      return (getData() == null);
   }

   @Override
   public int size() {
      int retorno = 0;
      if (!isEmpty())
         retorno = 1 + getNext().size();
      return retorno;

   }

   @Override
   public T search(T element) {
      T retorno = null;
      if (!isEmpty()) {
         if (this.getData().equals(element))
            retorno = this.getData();
         else {
            retorno = getNext().search(element);
         }
      }
      return retorno;
   }

   @Override
   public void insert(T element) {
      if (isEmpty()) {
         this.setData(element);
         this.setNext(new RecursiveSingleLinkedListImpl<T>());
      } else {
         getNext().insert(element);
      }
   }

   @Override
   public void remove(T element) {
      if (!this.isEmpty()) {
         if (this.getData().equals(element)) {
            this.setData(getNext().getData());

            this.setNext(getNext().getNext());
         } else {
            this.getNext().remove(element);
         }
      }

   }

   @Override
   public T[] toArray() {
      ArrayList<T> lista = new ArrayList<T>();
      return listToArray(lista, this);

   }

   private T[] listToArray(ArrayList<T> lista, RecursiveSingleLinkedListImpl<T> node) {
      if (!node.isEmpty()) {
         lista.add(node.getData());
         node.getNext().listToArray(lista, node.getNext());
      }
      return (T[]) lista.toArray();
   }

   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }

   public RecursiveSingleLinkedListImpl<T> getNext() {
      return next;
   }

   public void setNext(RecursiveSingleLinkedListImpl<T> next) {
      this.next = next;
   }

}
