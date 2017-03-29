package com.hrishikesh.ns.mixed;

/**
 * Count Inversions in an array
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/count-inversions-in-an-array/
 */
public class ArrayInversionCounter {

    public int count(int[] array) {
        int[] temp = new int[array.length];
        return sortAndCount(array, temp, 0, array.length - 1);
    }

    public int sortAndCount(int[] array, int[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            int x = sortAndCount(array, temp, left, mid);
            int y = sortAndCount(array, temp, mid + 1, right);
            int z = mergeAndCount(array, temp, left, mid, right);
            return x + y + z;
        }
        return 0;
    }

    private int mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        int i = left, j = mid, tempPos = left, inversionCounter = 0;

        while ((i <= mid - 1) && (j <= right)) {
            if (array[i] <= array[j]) temp[tempPos++] = array[i++];
            else {
                temp[tempPos++] = array[j++];
                inversionCounter += (mid - i);
            }
        }


        /** copy remaining left part **/
        while (i <= mid - 1) temp[tempPos++] = array[i++];

        /** copy remaining right part **/
        while (j <= right) temp[tempPos++] = array[j++];

        /** update main array **/
        for (i = left; i <= right; i++) array[i] = temp[i];

        return inversionCounter;
    }

}


class ArrayInversionCounterTest {
    public static void main(String[] args) {

        int array[] = {2, 4, 1, 3, 5};
        ArrayInversionCounter inversionCounter = new ArrayInversionCounter();
        System.out.println("Counts: " + inversionCounter.count(array));

    }
}