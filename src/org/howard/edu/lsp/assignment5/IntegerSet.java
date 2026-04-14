package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * IntegerSet models a mathematical set of integers backed by an ArrayList.
 *
 * No duplicate elements are allowed. All set operations (union, intersect,
 * diff, complement) return a new IntegerSet without modifying the originals.
 * Null arguments to set operations throw IllegalArgumentException.
 *
 * JCF bulk methods addAll, retainAll, and removeAll are used to implement
 * the set algebra operations.
 *
 * @author Erica Okeh
 * @version 1.0
 */
public final class IntegerSet {

    /** Backing ArrayList for set elements. No duplicates allowed. */
    private ArrayList<Integer> set;

    /**
     * Constructs an empty IntegerSet.
     */
    public IntegerSet() {
        set = new ArrayList<Integer>();
    }

    /**
     * Private constructor that wraps an already-clean ArrayList directly.
     *
     * Used internally by set operations that produce a result list guaranteed
     * to have no duplicates, bypassing the per-element contains() check in add().
     *
     * @param elements an ArrayList with no duplicate elements
     */
    private IntegerSet(ArrayList<Integer> elements) {
        set = elements;
    }

    /**
     * Returns a defensive copy of the internal ArrayList for safe internal use.
     *
     * @return a new ArrayList containing all elements of this set
     */
    private ArrayList<Integer> getCopy() {
        return new ArrayList<Integer>(this.set);
    }

    /**
     * Removes all elements from this set.
     * After this call, isEmpty() returns true.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return cardinality of the set; never negative
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set and the given IntegerSet contain exactly the
     * same elements, regardless of insertion order. Returns false if b is null.
     *
     * @param b the other IntegerSet to compare with
     * @return true if both sets have identical element membership
     */
    public boolean equals(IntegerSet b) {
        if (b == null) return false;
        if (this.length() != b.length()) return false;
        return set.containsAll(b.getCopy());
    }

    /**
     * Overrides Object.equals to satisfy the full Java equality contract.
     * Delegates to equals(IntegerSet) after a type check.
     * Satisfies: reflexive, symmetric, transitive, and consistent.
     * Returns false for null or any non-IntegerSet argument.
     *
     * @param o the object to compare with
     * @return true if o is an IntegerSet with identical element membership
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        return equals((IntegerSet) o);
    }

    /**
     * Returns a hash code consistent with equals().
     *
     * Computed as the sum of all element hash codes — commutative and
     * order-independent, matching the order-independent semantics of equals().
     *
     * @return order-independent hash code for this set
     */
    @Override
    public int hashCode() {
        int result = 0;
        for (int item : set) {
            result += Integer.hashCode(item);
        }
        return result;
    }

    /**
     * Returns true if the specified value is in this set.
     *
     * @param value the integer to check
     * @return true if present, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in this set.
     *
     * @return the maximum integer element
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty — cannot call largest()");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in this set.
     *
     * @return the minimum integer element
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty — cannot call smallest()");
        }
        return Collections.min(set);
    }

    /**
     * Adds the specified integer to this set if not already present.
     * Has no effect if the element already exists (no duplicates allowed).
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the specified integer from this set if present.
     * Has no effect if the element is not a member.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet representing the union A ∪ B.
     *
     * Contains every element from either set; duplicates included only once.
     * Uses addAll to combine both sets. Neither original is modified.
     *
     * @param intSetb the other IntegerSet; must not be null
     * @return a new IntegerSet equal to A ∪ B
     * @throws IllegalArgumentException if intSetb is null
     */
    public IntegerSet union(IntegerSet intSetb) {
        if (intSetb == null) {
            throw new IllegalArgumentException("Argument to union() must not be null");
        }
        HashSet<Integer> deduped = new HashSet<>(set);
        deduped.addAll(intSetb.getCopy());
        return new IntegerSet(new ArrayList<>(deduped));
    }

    /**
     * Returns a new IntegerSet representing the intersection A ∩ B.
     *
     * Contains only elements common to both sets.
     * Uses retainAll to filter to common elements. Neither original is modified.
     *
     * @param intSetb the other IntegerSet; must not be null
     * @return a new IntegerSet equal to A ∩ B
     * @throws IllegalArgumentException if intSetb is null
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        if (intSetb == null) {
            throw new IllegalArgumentException("Argument to intersect() must not be null");
        }
        ArrayList<Integer> temp = getCopy();
        temp.retainAll(intSetb.getCopy());
        return new IntegerSet(temp);
    }

    /**
     * Returns a new IntegerSet representing the difference A − B.
     *
     * Contains elements in this set that do not appear in intSetb.
     * Uses removeAll to eliminate intSetb elements. Neither original is modified.
     *
     * @param intSetb the subtrahend IntegerSet; must not be null
     * @return a new IntegerSet equal to A − B
     * @throws IllegalArgumentException if intSetb is null
     */
    public IntegerSet diff(IntegerSet intSetb) {
        if (intSetb == null) {
            throw new IllegalArgumentException("Argument to diff() must not be null");
        }
        ArrayList<Integer> temp = getCopy();
        temp.removeAll(intSetb.getCopy());
        return new IntegerSet(temp);
    }

    /**
     * Returns a new IntegerSet representing the relative complement B − A.
     *
     * Contains elements in intSetb that do not appear in this set.
     * Uses removeAll to eliminate this set's elements from intSetb.
     * Neither original is modified.
     *
     * @param intSetb the IntegerSet from which this set is subtracted; must not be null
     * @return a new IntegerSet equal to B − A
     * @throws IllegalArgumentException if intSetb is null
     */
    public IntegerSet complement(IntegerSet intSetb) {
        if (intSetb == null) {
            throw new IllegalArgumentException("Argument to complement() must not be null");
        }
        ArrayList<Integer> temp = intSetb.getCopy();
        temp.removeAll(getCopy());
        return new IntegerSet(temp);
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if cardinality is zero, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of this set sorted in ascending order.
     *
     * Format: [1, 2, 3] — comma-space separated, enclosed in brackets.
     * Empty set returns []. Sorting is performed on a defensive copy so
     * internal element order is never affected.
     *
     * @return formatted string representation of this set
     */
    @Override
    public String toString() {
        ArrayList<Integer> copy = getCopy();
        Collections.sort(copy);
        return copy.toString();
    }
}