package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListImpl;

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
         DoubleLinkedListNode<T> last = (DoubleLinkedListNode<T>) ((DoubleLinkedListImpl<T>) top).getLast();
         top.removeLast();
         size--;
         return last.getData();
      } else
         throw new StackUnderflowException();
   }

   @Override
   public T top() {
      T retorno = null;
      if (!isEmpty()) {
         DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) ((SingleLinkedListImpl<T>) top).getHead();
         retorno = head.getData();
      }
      return retorno;
   }

   @Override
   public boolean isEmpty() {
      return top.isEmpty();
   }

   @Override
   public boolean isFull() {
      return ((SingleLinkedListImpl<T>) top).getSize() >= size;

   }

}
