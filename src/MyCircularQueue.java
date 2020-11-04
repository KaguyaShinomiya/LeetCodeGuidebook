import org.junit.Assert;
import org.junit.Test;

public class MyCircularQueue {
    int[] array;
    int front;
    int rear;
    int size;
    int count;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.array = new int[k];
        this.size = k;
        this.front = 0;
        this.rear = 0;
        this.count = 0;

    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (this.isFull()) {
            return false;
        } else {
            array[rear] = value;
            if (this.rear == size - 1) {
                rear = 0;
            } else {
                rear++;
            }
            count++;
            return true;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (this.isEmpty()) {
            return false;
        } else {
            if (this.front == size - 1) {
                front = 0;
            } else {
                front++;
            }
            count--;
            return true;
        }

    }

    /** Get the front item from the queue. */
    public int Front() {
        return front;

    }

    /** Get the last item from the queue. */
    public int Rear() {
        return rear;
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if (front == rear) return true;
        return false;

    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if (count == size) return true;
        return false;
    }




}
