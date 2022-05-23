public interface CSE214Stack<T> {
    int size();
    /**
     * Returns the top element of this stack, without removing it from this stack.
     * Throws {@link java.util.NoSuchElementException} if this stack is empty.
     *
     * @return the top element of this stack, if it exists
     */
    T peek();
    /**
     * Pops and returns the top element of this stack. If the stack is empty, throws
     * {@link java.util.NoSuchElementException}.
     *
     * @return the top element of this stack, if it exists
     */
    T pop();
    /**
     * Pushes the given element onto the top of this stack.
     *
     * @param elem the given element to be pushed onto the top of this stack
     */
    void push(T elem);
    /**
     * @return <code>true</code> if this stack is empty, and <code>false</code> otherwise
     */
    boolean isEmpty();
}