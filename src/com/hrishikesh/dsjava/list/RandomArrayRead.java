package com.hrishikesh.dsjava.list;

/**
 * Ques 1
 * Write a java method that repeatedly selects and removes a random entry from an array until the array holds
 * no more entries.
 */
public class RandomArrayRead {

    private int arr [] = {12, 3, 4, 5, 6, 18, 8};
    private int size = arr.length;

    PseudoRandom random = new PseudoRandom(10, 2, 21);

    class PseudoRandom {
        int a, cur, b , n;

        PseudoRandom(int a, int cur, int b ) {
            this.a = a;
            this.cur = cur;
            this.b = b;
        }

        int getNext(int n){
            cur = (a * cur + b) %n;
            return cur;
        }
    }

    public int removeFromArray(int index ){
        int removedValue = arr[index];

        for (int i = index+1; i< size; i++ ){
            arr[i -1 ] = arr [i];
        }

        size --;
        return removedValue;
    }

    public void printArray(){
        System.out.print("{");
        for(int i=0; i <size; i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.print("}");
    }

    public void removeOneByOne(){
        while(size > 0){
            int removedValue = removeFromArray(random.getNext(size));
            System.out.println("\n\n_____________________________________________________");
            System.out.println("-----------------------------------------------------");
            System.out.println("Removed Value : " + removedValue);
            System.out.println("Array after removable :");
            printArray();
            System.out.println("\n-----------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        new RandomArrayRead().removeOneByOne();
    }
}


