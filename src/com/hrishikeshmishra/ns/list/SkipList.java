package com.hrishikeshmishra.ns.list;

import java.util.*;

/**
 *
 * A SkipList S for a map M consists of a series of list {S0, S1, ......, Sh}. Each list Si
 * stores a subset of the entries of M sorted by increasing keys, plus entries with two sentinels
 * keys denoted -infinities to +infinities, where-infinities is smaller than every possible keys that
 * can be inserted in M and +infinities is larger than every possible keys that can be inserted in M.
 *
 *
 * SkipLists ( aka JumpList) are an alternative representation of balanced trees (not binary) that
 * provides approximate O(log n) access times with the advantage that the implementation is
 * straightforward and the storage requirement is less than a standard balanced tree structure.
 *
 * Advantages:
 *  - implementation is straightforward and easier than a typical balanced tree algorithm
 *  - storage requirements are less than for typical balanced trees
 *  - insertions and deletions do not require rebalancing
 *  - supports insert,remove,find and join operations (especially useful for symbol table applications)
 *
 * Disadvantages
 *  - search time for insert and find is longer than for trees.
 *  - depends on a random number generator which can mean difficult debug.
 *
 *
 * Insertion:
 * The entries in Si+1 are chosen at random from the entries in Si, by picking each entry from Si, to also
 * be in Si+1 with probability 1/2. That is, in essene, we "flip a coin" fro each entry in Si and place that entry
 * in Si+1 if the coin comes up "heads". Thus, we expect S1 to have about n/2 entries, S2 to have about n/4 entries
 * and in general, Si to have about n/2i entries. As a consequence, we expect the height h of S to about log n.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/skiplist/
 */
public class SkipList<K extends Comparable<K>, V> {

    private int size;
    private int height;
    private SkipListNode<K,V> head;
    private K minusInfinity;
    private K plusInfinity;
    private List<K> blockedValues;

    public SkipList(K minusInfinity, K plusInfinity) {
        this.minusInfinity = minusInfinity;
        this.plusInfinity = plusInfinity;

        SkipListNode<K,V> leftSentinel = new SkipListNode<K, V>(minusInfinity, null);
        SkipListNode<K,V> rightSentinel = new SkipListNode<K, V>(plusInfinity, null);
        leftSentinel.setNext(rightSentinel);
        rightSentinel.setPrevious(leftSentinel);

        height = 0;
        head = leftSentinel;
        blockedValues = new ArrayList<>(2);
        blockedValues.add(minusInfinity);
        blockedValues.add(plusInfinity);
    }

    /**
     * <p>
     * Search key in list, if present
     * returns value otherwise null
     * </p>
     *
     * @param key
     * @return
     */
    public V search(K key){
        SkipListNode<K, V> positionNode = getPosition(key);
        return (positionNode.getKey().compareTo(key) == 0)? positionNode.getValue():null;
    }

    /**
     * <p>
     *  Insert Key, Value in SkipList
     *  in ascending order and return inserted value.
     * </p>
     * @param key
     * @param value
     * @return
     */
    public SkipListNode<K, V> insert(K key, V value){
        if(blockedValues.contains(key))
            throw new IllegalArgumentException("infinity values not allowed");

        SkipListNode<K, V> position = getPosition(key);

        /** In case key already present in list **/
        if(position.getKey().compareTo(key) == 0)
            throw new IllegalArgumentException("Duplicate item.");

        SkipListNode<K, V> belowNode = null;
        SkipListNode<K, V> previous = position;
        int level = -1;

        do {
            level++;
            /** In last level becomes height list then, move sentinel one level above. **/
            if(level >= height){
                height++;
                SkipListNode<K, V> rightSentinel = head.getNext();
                head = insertAfterAbove(null, head, minusInfinity, null);
                insertAfterAbove(head, rightSentinel, plusInfinity, null);
            }

            /** Add node to list **/
            belowNode = insertAfterAbove(previous, belowNode, key, value);

            /** In case previous Node has no node on above it. **/
            while (Objects.isNull(previous.getAbove()))
                previous = previous.getPrevious();

            previous = previous.getAbove();

        } while (Coin.flip() == Coin.HEAD);

        size++;
        return belowNode;
    }

    /**
     * <p>
     *     Remove item from SkipList if present and return value
     *     otherwise null.
     * </p>
     *
     * @param key
     * @return
     */
    public V remove(K key){
        if(blockedValues.contains(key))
            throw new IllegalArgumentException("infinity values not allowed");

        SkipListNode<K, V> position = getPosition(key);
        if(position.getKey().compareTo(key) != 0)
            return null;

        SkipListNode<K, V> navigationNode = position;

        while(!Objects.isNull(navigationNode)){
            navigationNode.getPrevious().setNext(navigationNode.getNext());
            navigationNode.getNext().setPrevious(navigationNode.getPrevious());
            navigationNode.setPrevious(null);
            navigationNode.setNext(null);

            SkipListNode aboveNode = navigationNode.getAbove();
            navigationNode.setBottom(null);
            navigationNode.setAbove(null);
            navigationNode = aboveNode;
        }
        return position.getValue();
    }

    /**
     * <p>
     *     Get position of key in SkipList if present
     *     otherwise get previous node that largest smaller value from key.
     * </p>
     * @param key
     * @return
     */
    private SkipListNode<K, V> getPosition(K key){
        SkipListNode<K,V> navigatorNode = head;
        while (!Objects.isNull(navigatorNode.getBottom())){
            navigatorNode = navigatorNode.getBottom();

            while (key.compareTo(navigatorNode.getNext().getKey()) >= 0)
                navigatorNode = navigatorNode.getNext();
        }
        return navigatorNode;
    }

    /**
     * <p>
     *     Create (insert) a node just right after "previous" node and
     *     above of "below" node.
     * </p>
     * @param previous
     * @param below
     * @param k
     * @param v
     * @return
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K,V> previous, SkipListNode<K,V> below, K k, V v ){
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(k, v);

        /** Updating horizontal links **/
        if(!Objects.isNull(previous)) {
            newNode.setPrevious(previous);

            /** In case of right sentinel, previous next will be null. **/
            if(!Objects.isNull(previous.getNext())) {
                newNode.setNext(previous.getNext());
                previous.getNext().setPrevious(newNode);
            }
            previous.setNext(newNode);
        }

        /** Updating vertical links **/
        if(!Objects.isNull(below)){
            newNode.setBottom(below);
            below.setAbove(newNode);
        }
        return newNode;
    }

    /**
     * <p>
     *     Traverse all nodes for all SkipLists from
     *     top to bottom.
     * </p>
     */
    public void printRaw() {
        SkipListNode<K, V> navigatorNode = head;
        while (!Objects.isNull(navigatorNode)){
            SkipListNode<K, V> verticalMoveNode  = navigatorNode, horizontalMoveNode = navigatorNode;
            while (!Objects.isNull(horizontalMoveNode)){
                System.out.print("  " + horizontalMoveNode.getKey());
                horizontalMoveNode = horizontalMoveNode.getNext();
            }
            navigatorNode = verticalMoveNode.getBottom();
            System.out.println();
        }
    }

    /**
     * <p>
     *     Print all SkipNode in proper format.
     * </p>
     */
    public void print() {
        int maxChars = minusInfinity.toString().length() + 3;
        K[][] matrix = getKeysMatrix();
        String placeholder = getPlaceholder(maxChars);
        String formatter = getFormatString(maxChars);

        for(int i=0; i<= height; i++){
            String [] keys = new String[size+2];

            for(int j=0; j< size+2; j++){

                if(Objects.isNull(matrix[i][j]))
                    keys[j] = placeholder;
                else if(matrix[i][j].compareTo(minusInfinity) == 0 )
                    keys[j] = "-∞";
                else if(matrix[i][j].compareTo(plusInfinity) == 0)
                    keys[j] = "+∞";
                else
                    keys[j] = matrix[i][j].toString();
            }
            System.out.println(String.format(formatter, keys));
        }
    }

    /**
     * <p>
     *     Create matrix of SkipLists Node's key
     * </p>
     * @return
     */
    private K[][] getKeysMatrix() {
        K [][] matrix =  (K[][]) new Comparable[ height + 1 ][ size + 2];
        SkipListNode<K, V> navigatorNode = head;
        int i = height, j = 0;

        while (!Objects.isNull(navigatorNode.getBottom()))
            navigatorNode = navigatorNode.getBottom();

        /** Filling bottom listing, it has all values **/
        SkipListNode<K, V> rightMove  = navigatorNode;
        while (!Objects.isNull(rightMove)){
            matrix[i][j++] = rightMove.getKey();
            rightMove = rightMove.getNext();
        }

        /** moving list above from bottom **/
        navigatorNode = navigatorNode.getAbove();
        while (!Objects.isNull(navigatorNode)){
            rightMove  = navigatorNode;
            while (!Objects.isNull(rightMove)){
                matrix[i][getIndex(matrix[height], rightMove.getKey())] = rightMove.getKey();
                rightMove = rightMove.getNext();
            }
            navigatorNode = navigatorNode.getAbove();
            i--;
        }

        return matrix;
    }

    private int getIndex(K[] matrix, K key){
        for(int i=0; i< matrix.length; i++){
            if(key.compareTo(matrix[i]) == 0)
                return i;
        }
        throw new NoSuchElementException("key : " + key);
    }

    private String getPlaceholder(int length){
        String str = "";
        for(int i=0; i<length; i++)
            str += " ";

        return str;
    }

    private String getFormatString(int maxChars){
        String str = "";
        for(int i=0; i<size+2; i++){
            str +=  "%-" + maxChars + "s";
        }
        return str;
    }

    private static enum Coin {
        HEAD, TAIL;

        public static Coin flip(){
            Random random = new Random();
            return random.nextBoolean()? HEAD : TAIL;
        }
    }

    /**
     * <p>
     *     SkipList Node
     * </p>
     *
     * @param <K>
     * @param <V>
     */
    private class SkipListNode <K, V> {
        private V value;
        private K key;
        private SkipListNode above;
        private SkipListNode bottom;
        private SkipListNode previous;
        private SkipListNode next;

        private SkipListNode(K key, V value, SkipListNode above, SkipListNode bottom, SkipListNode previous, SkipListNode next) {
            this.value = value;
            this.key = key;
            this.above = above;
            this.bottom = bottom;
            this.previous = previous;
            this.next = next;
        }

        private SkipListNode(K key, V value) {
            this.value = value;
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public SkipListNode getAbove() {
            return above;
        }

        public void setAbove(SkipListNode above) {
            this.above = above;
        }

        public SkipListNode getBottom() {
            return bottom;
        }

        public void setBottom(SkipListNode bottom) {
            this.bottom = bottom;
        }

        public SkipListNode getPrevious() {
            return previous;
        }

        public void setPrevious(SkipListNode previous) {
            this.previous = previous;
        }

        public SkipListNode<K,V> getNext() {
            return next;
        }

        public void setNext(SkipListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ')';
        }
    }
}


class SkipListTest {

    public static void main(String[] args) {
        SkipList<Integer, String> skipList = new SkipList<Integer, String>(Integer.MIN_VALUE, Integer.MAX_VALUE);
        skipList.insert(12, "A");
        skipList.insert(11, "C");
        skipList.insert(35, "D");
        skipList.insert(13, "G");
        skipList.insert(14, "H");
        skipList.insert(111, "K");
        skipList.insert(123, "P");
        skipList.insert(-123, "Y");
        skipList.insert(23, "U");

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();

        System.out.println();

        System.out.println("Search 123: " + skipList.search(123));

        System.out.println("Remove 123 : " + skipList.remove(123));

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();

        System.out.println("Remove -123 : " + skipList.remove(-123));

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();


        System.out.println("Remove 12: " + skipList.remove(12));

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();



        System.out.println("Remove 14: " + skipList.remove(14));

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();


        System.out.println("Remove 140: " + skipList.remove(140));

        System.out.println("Raw Print ");
        skipList.printRaw();

        System.out.println("\n\n\n\nClear Print");
        skipList.print();
    }


}