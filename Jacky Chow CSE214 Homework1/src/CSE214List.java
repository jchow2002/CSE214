public interface CSE214List<T> {
    /**
     * @return the number of elements in this list.
     */
    int size();
    /**
     * Adds the specified element at the specified index, such that after the addition
     * operation, the previous elements in that index and beyond (if any) will occupy
     * indices incremented by one.
     *
     * @param elem the specified element
     * @param index the specified index
     */
    void add(T elem, int index);
    /**
     * Adds the given element at the start of this list.
     *
     * @param elem the given element
     */
    void add(T elem);
    /**
     * Removes the element from the specified index, such that after the removal
     * operation, the previous elements beyond that index (if any) will occupy indices
     * decremented by one.
     *
     * @param index the specified index
     * @return the removed element
     */
    T remove(int index);
    /**
     * Removes the first occurrence of the given element from this list.
     * Throws {@link java.util.NoSuchElementException} if such an element is not present
     * in this list.
     * @param elem the given element
     */
    void remove(T elem);
    /**
     * Searches for the specified element in this list, and returns <code>true</code> if
     * and only if it was found.
     * @param elem the specified element
     * @return <code>true</code> if the element was found, <code>false</code> otherwise
     */
    boolean find(T elem);
}
