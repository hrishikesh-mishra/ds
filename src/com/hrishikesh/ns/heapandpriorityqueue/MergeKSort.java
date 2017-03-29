package com.hrishikesh.ns.heapandpriorityqueue;

/**
 * Problem:
 * Merge k sorted arrays
 * ;
 * Merge k sorted arrays using Min heap
 * ;
 * ;
 * Algorithm:
 * - Create a HeapNode class with following properties
 * - - key
 * - - arrayId
 * - - arrayIndex
 * - Create array to hold merge result
 * - Add first element from all sorted array to MinHeap
 * - Iterate 0 to n*k
 * - - remove min from MinHeap and add to output_array
 * - - If remove _element's array has more element then
 * - - - add add element from that array to MinHeap
 * - - Else
 * - - - Add some dummy high number to MinHeap
 *
 * @author hrishikesh.mishra
 * @link  http://hrishikeshmishra.com/merge-k-sorted-arrays/
 */
public class MergeKSort {

    public int[] sort(int[][] arrays) {
        int k = arrays.length, n = arrays[0].length, i;
        int[] kSortArray = new int[k * n];
        MinHeap minHeap = new MinHeap(k);

        /** inserting first element of each array in heap **/
        for (i = 0; i < k; i++) {
            minHeap.insert(new HeapNode(arrays[i][0], i, 0));
        }

        for (i = 0; i < k * n; i++) {
            HeapNode minHeapNode = minHeap.removeMin();
            kSortArray[i] = minHeapNode.getKey();
            int nextIndex = minHeapNode.getArrayIndex() + 1;

            if (nextIndex < arrays[minHeapNode.getArrayId()].length)
                minHeap.insert(new HeapNode(arrays[minHeapNode.getArrayId()][nextIndex], minHeapNode.getArrayId(), nextIndex));
            else
                minHeap.insert(new HeapNode(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        return kSortArray;
    }

    private static class MinHeap {
        private HeapNode[] heapNodes;
        private int capacity;
        private int size;

        private MinHeap(int capacity) {
            this.capacity = capacity;
            heapNodes = new HeapNode[capacity];
        }

        public void insert(HeapNode heapNode) {
            if (isFull()) throw new StackOverflowError("Heap is full");
            heapNodes[size] = heapNode;
            percolateUp(size);
            size++;
        }

        public HeapNode removeMin() {
            if (isEmpty()) throw new RuntimeException("Heap is empty");
            HeapNode node = heapNodes[0];
            heapNodes[0] = heapNodes[size - 1];
            size--;
            percolateDown(0);
            return node;
        }

        private void percolateUp(int index) {
            int parentIndex = getParent(index),
                    data = getNodeData(index),
                    minIndex = index;

            if (isValidPointer(parentIndex) && data < getNodeData(parentIndex))
                minIndex = parentIndex;

            /** Everything is ok, no need further processing. **/
            if (minIndex == index) return;

            swap(minIndex, index);
            percolateUp(minIndex);

        }

        private void percolateDown(int index) {
            int minIndex = index,
                    data = getNodeData(index),
                    leftChildPointer = getLeftChild(index),
                    rightChildPointer = getRightChild(index);

            if (isValidPointer(leftChildPointer) && data > getNodeData(leftChildPointer))
                minIndex = leftChildPointer;

            if (isValidPointer(rightChildPointer) && getNodeData(minIndex) > getNodeData(rightChildPointer))
                minIndex = rightChildPointer;

            /** Everything is ok, no need further processing. **/
            if (minIndex == index) return;

            swap(minIndex, index);
            percolateDown(minIndex);
        }

        private int getLeftChild(int i) {
            int left = 2 * i + 1;
            return isValidPointer(left) ? left : -1;
        }

        private int getRightChild(int i) {
            int right = 2 * i + 2;
            return isValidPointer(right) ? right : -1;
        }

        private int getParent(int i) {
            int parent = (i - 1) / 2;
            return isValidPointer(parent) ? parent : -1;
        }

        private boolean isValidPointer(int i) {
            return (i < 0 || i >= size) ? false : true;
        }

        private int getNodeData(int index) {
            return heapNodes[index].getKey();
        }

        private void swap(int i, int j) {
            HeapNode temp = heapNodes[i];
            heapNodes[i] = heapNodes[j];
            heapNodes[j] = temp;
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        private boolean isFull() {
            return size == capacity;
        }
    }

    private static class HeapNode {
        int key;
        int arrayId;
        int arrayIndex;

        private HeapNode(int key, int arrayId, int arrayIndex) {
            this.key = key;
            this.arrayId = arrayId;
            this.arrayIndex = arrayIndex;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getArrayId() {
            return arrayId;
        }

        public void setArrayId(int arrayId) {
            this.arrayId = arrayId;
        }

        public int getArrayIndex() {
            return arrayIndex;
        }

        public void setArrayIndex(int arrayIndex) {
            this.arrayIndex = arrayIndex;
        }

        @Override
        public String toString() {
            return "[" +
                    "key=" + key +
                    ", arrayId=" + arrayId +
                    ", arrayIndex=" + arrayIndex +
                    ']';
        }
    }

}


class MergeKSortTest {
    public static void main(String[] args) {
        MergeKSort mergeKSort = new MergeKSort();
        int[][] array = {
                {1, 3, 8, 9, 15},
                {20, 48, 58, 67, 68},
                {2, 10, 22, 49, 60},
        };

        int[] ksort = mergeKSort.sort(array);

        for (int i = 0; i < array.length; i++) {
            print(array[i]);
            System.out.println("");
        }

        System.out.print("\nAfter KSort: ");
        print(ksort);
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) System.out.print(array[i] + " ");
    }

}
