package org.howard.edu.lsp.assignment6;

/**
 * IntegerSetException is thrown when an invalid operation is performed on an IntegerSet.
 *
 * Common cases include calling largest() or smallest() on an empty set,
 * or passing a null argument to a set operation (union, intersect, diff, complement).
 *
 * Extends RuntimeException so no throws clause is required by callers.
 *
 * @author Erica Okeh
 * @version 1.0
 */
public class IntegerSetException extends RuntimeException {

    /**
     * Constructs an IntegerSetException with the specified detail message.
     *
     * @param message the detail message describing the error
     */
    public IntegerSetException(String message) {
        super(message);
    }
}
