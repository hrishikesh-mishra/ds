package com.hrishikesh.ns.stack;

import java.util.StringJoiner;

/**
 * Problem:
 * Implement three stacks in one array.
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/implement-three-stacks-in-one-array/
 */
public class ThreeStackArray<E> {
    private E[] repository;
    int top1, top2, top3, base2, size;

    public ThreeStackArray(int size) throws Exception {
        if (size < 3) throw new Exception("size must not be less than 4");
        this.size = size;
        this.top1 = -1;
        this.base2 = size / 3;
        this.top2 = this.base2 - 1;
        this.top3 = size;
        this.repository = (E[]) new Object[size];
    }

    public void push(E data, int stackId) {
        switch (stackId) {
            case 1:
                if (top1 + 1 == base2) {
                    if (!isStack2MovableToRight())
                        throw new RuntimeException("Stack 1 is recharged max limit.");
                    moveStack2Right();
                }
                repository[++top1] = data;
                break;
            case 2:
                if (top2 + 1 == top3) {
                    if (!isStack2MovableToLeft())
                        throw new RuntimeException("Stack 2 is recharged max limit.");
                    moveStack2Left();
                }
                repository[++top2] = data;
                break;
            case 3:
                if (top3 - 1 == top2) {
                    if (!isStack2MovableToLeft())
                        throw new RuntimeException("Stack 3 is recharged max limit.");
                    moveStack2Left();
                }
                repository[--top3] = data;
                break;

            default:
                throw new RuntimeException("Invalid stack id");
        }
    }

    public E pop(int stackId) {
        E data = null;
        switch (stackId) {
            case 1:
                if (top1 == -1)
                    throw new RuntimeException("Stack 1 is underflow.");
                data = repository[top1];
                repository[top1--] = null;
                return data;
            case 2:
                if (top2 == base2)
                    throw new RuntimeException("Stack  2 is underflow.");
                data = repository[top2];
                repository[top2--] = null;
                return data;
            case 3:
                if (top3 == size)
                    throw new RuntimeException("Stack 3 is underflow");
                data = repository[top3];
                repository[top3++] = null;
                return data;
            default:
                throw new RuntimeException("Invalid stack id.");
        }
    }

    public E top(int stackId) {
        switch (stackId) {
            case 1:
                if (top1 == -1)
                    throw new RuntimeException("Stack 1 is underflow.");
                return repository[top1];
            case 2:
                if (top2 == base2)
                    throw new RuntimeException("Stack  2 is underflow.");
                return repository[top2];
            case 3:
                if (top3 == size)
                    throw new RuntimeException("Stack 3 is underflow");
                return repository[top3];
            default:
                throw new RuntimeException("Invalid stack id.");
        }
    }

    private void moveStack2Left() {
        for (int i = base2 - 1; i < top3; i++)
            repository[i] = repository[i + 1];
        base2--;
        top2--;

    }

    private void moveStack2Right() {
        for (int i = top2 + 1; i > base2; i--) {
            repository[i] = repository[i - 1];
        }
        base2++;
        top2++;
    }

    private boolean isStack2MovableToRight() {
        return (top2 + 1 < top3);
    }

    private boolean isStack2MovableToLeft() {
        return (base2 - 1 > top1);
    }

    @Override
    public String toString() {
        StringJoiner joiner1 = new StringJoiner(", ", "[", "]"),
                joiner2 = new StringJoiner(", ", "[", "]"),
                joiner3 = new StringJoiner(", ", "[", "]");

        int i;
        for (i = top1; i >= 0; i--) joiner1.add(String.valueOf(repository[i]));
        for (i = top2; i >= base2; i--) joiner2.add(String.valueOf(repository[i]));
        for (i = top3; i < size; i++) joiner3.add(String.valueOf(repository[i]));

        return "{" + joiner1.toString() + ", "
                + joiner2.toString() + ", "
                + joiner3.toString() + "}";
    }
}

class ThreeStackArrayTest {
    public static void main(String[] args) throws Exception {
        ThreeStackArray<Integer> stackArray = new ThreeStackArray<>(9);
        stackArray.push(new Integer(1), 1);
        stackArray.push(new Integer(2), 2);
        stackArray.push(new Integer(3), 3);

        System.out.println(stackArray);
        stackArray.push(new Integer(4), 2);
        stackArray.push(new Integer(5), 2);
        stackArray.push(new Integer(5), 2);

        System.out.println(stackArray);
        stackArray.push(new Integer(6), 2);
        stackArray.push(new Integer(7), 2);

        System.out.println(stackArray);
        stackArray.push(new Integer(8), 1);

        System.out.println(stackArray);
    }

}