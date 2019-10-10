package adt.linkedList.set;

import java.util.Arrays;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void removeDuplicates() {
      if (!isEmpty()) {

         SingleLinkedListNode<T> nodeNIL = new SingleLinkedListNode<T>(null, null);
         SingleLinkedListNode<T> newHead = new SingleLinkedListNode(getHead().getData(), nodeNIL);
         SingleLinkedListNode<T> aux1 = getHead().getNext();
         SingleLinkedListNode<T> aux2 = new SingleLinkedListNode<T>(null, null);
         boolean finished = (aux1.isNIL());
         while (!finished) {
            if (aux1.getNext().isNIL())
               finished = true;

            boolean hasElement = false;
            boolean nextIsNill = false;
            aux2 = newHead;
            while (!nextIsNill) {
               if (aux2.equals(aux1))
                  hasElement = true;
               if (aux2.isNIL())
                  nextIsNill = true;
               aux2 = aux2.getNext();
            }

            if (!hasElement)
               newHead.setNext(aux1);

            aux1 = aux1.getNext();
         }

         setHead(newHead);

      }

   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

      SingleLinkedListNode<T> aux = this.getHead();
      SetLinkedListImpl<T> retorno = new SetLinkedListImpl<T>();

      boolean finished = false;
      while (finished) {
         if (aux.isNIL())
            finished = true;
         retorno.insert(aux.getData());
         aux = aux.getNext();

      }

      aux = ((SingleLinkedListImpl<T>) otherSet).getHead();
      finished = false;
      while (finished) {
         if (aux.isNIL())
            finished = true;
         retorno.insert(aux.getData());
         aux = aux.getNext();

      }

      retorno.removeDuplicates();
      return retorno;
   }

   ///// FAZER DAQUI PRA BAIXO ///
   ///////////
   ////////
   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      this.removeDuplicates();
      otherSet.removeDuplicates();

      T[] array = this.toArray();
      T[] array2 = otherSet.toArray();
      SetLinkedListImpl intersection = new SetLinkedListImpl();
      SingleLinkedListNode<T> nodeNIL = new SingleLinkedListNode<T>(null, null);

      for (int i = 0; i < array.length; i++) {
         boolean foundedElement = false;
         while (!foundedElement) {
            int j = 0;
            if (array[i].equals(array2[j])) {
               intersection.insert(array[i]);
               foundedElement = true;
            }
            j++;
         }

      }

      return intersection;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      T[] array = otherSet.toArray();

      for (int i = 0; i < array.length; i++) {
         insert(array[i]);
      }

      this.removeDuplicates();
   }

   public static void main(String[] args) {
      SetLinkedListImpl<Integer> lista = new SetLinkedListImpl<Integer>();

      lista.insert(3);
      lista.insert(3);
      lista.insert(3);
      lista.insert(3);
      lista.insert(10);
      lista.insert(10);
      lista.insert(10);
      lista.insert(10);
      System.out.println(Arrays.toString(lista.toArray()));
      lista.removeDuplicates();
      System.out.println(Arrays.toString(lista.toArray()));
   }

}
