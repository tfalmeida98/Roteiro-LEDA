package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      int max = maxValue(array, leftIndex, rightIndex);

      Integer[] resultArray = new Integer[max + 1];
      zeraArray(resultArray);

      for (int i = leftIndex; i <= rightIndex; i++) {
         resultArray[array[i]] += 1;
      }

      fixArray(array, resultArray);

   }

   private int maxValue(Integer[] array, int leftIndex, int rightIndex) {
      int max = -1;

      for (int i = leftIndex; i <= rightIndex; i++) {
         if (array[i] > max)
            max = array[i];
      }
      return max;
   }

   private void zeraArray(Integer[] array) {
      for (int i = 0; i < array.length; i++) {
         array[i] = 0;
      }
   }

   private void fixArray(Integer[] array, Integer[] arrayResult) {
      int indice = 0;
      for (int i = 0; i < arrayResult.length; i++) {
         while (arrayResult[i] > 0) {
            array[indice] = i;
            arrayResult[i] -= 1;
            indice++;
         }
      }
   }

}
