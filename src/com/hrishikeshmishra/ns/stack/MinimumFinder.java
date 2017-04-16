package com.hrishikeshmishra.ns.stack;

/**
 * Problem:
 * How to get get minimum from list of integer in O(1) using stack.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/how-to-get-get-minimum-from-list-of-integer-in-o1-using-stack/
 */
public class MinimumFinder {
    private Stack<Integer> elementStack;
    private Stack<Integer> minimumStack;

    public MinimumFinder() {
        this.elementStack = new LinkedStack<>();
        this.minimumStack = new LinkedStack<>();
    }

    public void push(int data) {
        elementStack.push(data);
        if (minimumStack.isEmpty())
            minimumStack.push(data);
        else if (data <= minimumStack.top())
            minimumStack.push(data);
    }

    public int pop() {
        if (elementStack.isEmpty())
            throw new RuntimeException("Stack is underflow");

        int data = elementStack.pop();
        if (data <= minimumStack.top() || elementStack.isEmpty())
            minimumStack.pop();

        return data;
    }

    public int getMinimum() {
        return minimumStack.top();
    }

    @Override
    public String toString() {
        return "MinimumFinder {" +
                "elementStack=" + elementStack +
                ", minimumStack=" + minimumStack +
                '}';
    }
}


class MinimumFinderTest {
    public static void main(String[] args) {
        MinimumFinder minimumFinder = new MinimumFinder();
        minimumFinder.push(2);
        minimumFinder.push(4);
        minimumFinder.push(1);
        minimumFinder.push(5);
        minimumFinder.push(1);
        minimumFinder.push(6);
        minimumFinder.push(2);

        System.out.println(minimumFinder);
        System.out.println("Minimum : " + minimumFinder.getMinimum());

        System.out.println("Pop : " + minimumFinder.pop());
        System.out.println("Pop : " + minimumFinder.pop());
        System.out.println("Pop : " + minimumFinder.pop());
        System.out.println("Pop : " + minimumFinder.pop());

        System.out.println(minimumFinder);
        System.out.println("Minimum : " + minimumFinder.getMinimum());


        System.out.println("Pop : " + minimumFinder.pop());

        System.out.println(minimumFinder);
        System.out.println("Minimum : " + minimumFinder.getMinimum());
    }
}

