import static java.lang.System.*;

public class ArrayDeque<T> {
    private int usedSize;
    private int arraySize;
    private int firstIndex; //0<=firstIndex<arraySize
    private final double lowUsageRatio = 0.25;
    private final double highUsageRatio = 1;

    private T[] array;

    /**
     * Make a new array of 2x size, then move all the data to the new Array.
     * This function assumes that the current array is full, which means: usedSize == arraySize.
     */
    private void checkExpandArray() {
        if (this.usedSize == this.arraySize) {
            T[] newArray = (T[]) new Object[this.arraySize * 2];
            //To make debugging easier, the first element will be at index 0 in the new array.
            // from the first element to the end of the old array
            arraycopy(this.array,
                    this.firstIndex,
                    newArray,
                    0,
                    this.arraySize - this.firstIndex);
            // for the wrapped-around elements
            arraycopy(this.array,
                    0,
                    newArray,
                    this.arraySize - this.firstIndex,
                    this.firstIndex);
            this.array = newArray;
            this.firstIndex = 0;
            this.arraySize *= 2;
        }
    }

    /**
     * Shrink the array to half of its size.
     */
    private void checkShrinkArray() {
        if ((this.arraySize > 8) && (this.usedSize < this.arraySize * lowUsageRatio)) {
            T[] newArray = (T[]) new Object[this.arraySize / 2];
            //To make debugging easier, the first element will be at index 0 in the new array.
            int length = Math.min(this.arraySize - this.firstIndex, this.usedSize);
            arraycopy(this.array,
                    this.firstIndex,
                    newArray,
                    0,
                    length);
            if (this.usedSize > length) {
                arraycopy(this.array,
                        0,
                        newArray,
                        length,
                        this.usedSize - length);
            }
            this.array = newArray;
            this.arraySize /= 2;
            this.firstIndex = 0;
        }
    }

    public ArrayDeque() {
        this.usedSize = 0;
        this.arraySize = 8;
        this.firstIndex = 0;
        this.array = (T[]) new Object[8];
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        checkExpandArray();
        this.usedSize++;
        this.firstIndex = ((this.firstIndex == 0 ? this.arraySize : this.firstIndex) - 1);
        this.array[this.firstIndex] = item;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        checkExpandArray();
        this.array[(this.firstIndex + this.usedSize) % this.arraySize] = item;
        this.usedSize++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.usedSize == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.usedSize;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < this.usedSize; i++) {
            out.print(this.array[(i + this.firstIndex) % this.arraySize] + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (this.usedSize == 0) {
            return null;
        }
        T value = this.array[firstIndex];
        this.firstIndex = (this.firstIndex == (this.arraySize - 1)) ? 0 : (this.firstIndex + 1);
        this.usedSize = (this.usedSize == 0) ? 0 : (this.usedSize - 1);
        checkShrinkArray();
        return value;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (this.usedSize == 0) {
            return null;
        }
        this.usedSize--;
        T value = this.array[(this.firstIndex + this.usedSize) % this.arraySize];
        checkShrinkArray();
        return value;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if ((index + 1 > this.usedSize) || (index < 0)) {
            return null;
        } else {
            return this.array[(this.firstIndex + index) % this.arraySize];
        }
    }
}
