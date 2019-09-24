package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

   protected DoubleLinkedList<T> top;
   protected int size;

   public StackDoubleLinkedListImpl(int size) {
      this.size = size;
      this.top = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (!isFull()) {
         top.insert(element);
         size++;
      } else
         throw new StackOverflowException();

   }

   @Override
   public T pop() throws StackUnderflowException {
      if (!isEmpty()) {
         DoubleLinkedListImpl singleList = (DoubleLinkedListImpl) top;
         SingleLinkedListNode last = singleList.getLast();
         T data = (T) last.getData();
         top.removeLast();
         size--;
         return data;
      } else
         throw new StackUnderflowException();
   }

   @Override
   public T top() {
      T retorno = null;
      if (!isEmpty()) {
         DoubleLinkedListImpl singleList = (DoubleLinkedListImpl) top;
         SingleLinkedListNode head = singleList.getHead();
         T data = (T) head.getData();
         retorno = data;
      }
      return retorno;
   }

   @Override
   public boolean isEmpty() {
      return top.isEmpty();
   }

   @Override
   public boolean isFull() {
      DoubleLinkedListImpl singleList = (DoubleLinkedListImpl) top;
      return singleList.getSize() == size;

   }

}
