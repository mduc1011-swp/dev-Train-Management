package BSTree;

import LinkedList.LinkedList;
public class MyQueue<E extends Comparable<E>> {
     LinkedList<E> queue;

    public MyQueue() {
        this.queue = new LinkedList<E>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void clear() {
        this.queue.clear();
    }

    public void enqueue(E x) {
        this.queue.addToTail(x);
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
         E p = this.queue.removeFirst();
        return p;
    }

    public E front() {
        if (isEmpty()) {
            return null;
        }
        return this.queue.getFirst();

    }
}

