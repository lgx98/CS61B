public class LinkedListDeque<T> {

    /**
     * Inner class for double linked list node.
     */
    private static class DNode<T> {
        private T value;
        private DNode<T> prev;
        private DNode<T> next;

        DNode(T value, DNode<T> prev, DNode<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private final DNode<T> sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new DNode<>(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        DNode<T> newNode = new DNode<>(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = newNode;
        this.sentinel.next = newNode;
        this.size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        DNode<T> newNode = new DNode<>(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = newNode;
        this.sentinel.prev = newNode;
        this.size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        DNode<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        DNode<T> first = this.sentinel.next;
        first.next.prev = this.sentinel;
        this.sentinel.next = first.next;
        this.size = Math.max(this.size - 1, 0);
        return first.value;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        DNode<T> last = this.sentinel.prev;
        last.prev.next = this.sentinel;
        this.sentinel.prev = last.prev;
        this.size = Math.max(this.size - 1, 0);
        return last.value;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (((index + 1) > this.size) || (index < 0)) {
            return null;
        } else {
            DNode<T> current = sentinel.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }

    /**
     * Has the same function as get(), but recursively.
     */
    public T getRecursive(int index) {
        if ((index + 1) > this.size) {
            return null;
        } else {
            return getIthAhead(this.sentinel.next, index);
        }
    }

    private T getIthAhead(DNode<T> current, int index) {
        if (index == 0) {
            return current.value;
        } else {
            return getIthAhead(current.next, index - 1);
        }
    }

}
