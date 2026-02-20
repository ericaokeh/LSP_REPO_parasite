package org.howard.edu.lsp.assignment3;

/**
 * Defines a general contract for transforming an input object into an output object.
 * Demonstrates Polymorphism.
 * @param <I> The input type
 * @param <O> The output type
 */
public interface Transformer<I, O> {
    /**
     * Transforms the input into the desired output format.
     * @param input the raw input object
     * @return the transformed output object
     * @throws NumberFormatException if numeric parsing fails
     */
    O transform(I input) throws NumberFormatException;
}