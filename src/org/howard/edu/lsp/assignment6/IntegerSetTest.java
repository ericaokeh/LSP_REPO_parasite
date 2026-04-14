package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for IntegerSet (Assignment 6).
 *
 * Every method has at least one normal case and one edge case,
 * as required by the Assignment 6 test coverage specification.
 *
 * @author Erica Okeh
 * @version 1.0
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    /**
     * Initializes test fixtures before each test.
     * set1 = {1, 2, 3}, set2 = {2, 3, 4}, emptySet = {}
     */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);
    }

    // -----------------------------------------------------------------------
    // clear()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: empties a non-empty set")
    public void testClearNormal() {
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("clear() - edge: calling clear() on an already-empty set is safe")
    public void testClearEdgeAlreadyEmpty() {
        emptySet.clear();
        assertTrue(emptySet.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct cardinality")
    public void testLengthNormal() {
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("length() - edge: returns 0 for empty set")
    public void testLengthEdgeEmpty() {
        assertEquals(0, emptySet.length());
    }

    // -----------------------------------------------------------------------
    // equals()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: same elements in different insertion order are equal")
    public void testEqualsNormal() {
        IntegerSet copy = new IntegerSet();
        copy.add(3);
        copy.add(1);
        copy.add(2);
        assertTrue(set1.equals(copy));
    }

    @Test
    @DisplayName("equals() - edge: returns false for null argument")
    public void testEqualsEdgeNull() {
        assertFalse(set1.equals((IntegerSet) null));
    }

    @Test
    @DisplayName("equals() - edge: returns false when compared to non-IntegerSet object")
    public void testEqualsEdgeWrongType() {
        assertFalse(set1.equals("not a set"));
    }

    @Test
    @DisplayName("equals() - edge: two empty sets are equal")
    public void testEqualsEdgeBothEmpty() {
        assertTrue(emptySet.equals(new IntegerSet()));
    }

    // -----------------------------------------------------------------------
    // contains()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: returns true for a present element")
    public void testContainsNormal() {
        assertTrue(set1.contains(2));
    }

    @Test
    @DisplayName("contains() - edge: returns false for a value not in the set")
    public void testContainsEdgeAbsent() {
        assertFalse(set1.contains(99));
    }

    // -----------------------------------------------------------------------
    // largest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns the maximum element")
    public void testLargestNormal() {
        assertEquals(3, set1.largest());
    }

    @Test
    @DisplayName("largest() - edge: throws IllegalStateException on empty set")
    public void testLargestEdgeEmpty() {
        assertThrows(IllegalStateException.class, () -> emptySet.largest());
    }

    // -----------------------------------------------------------------------
    // smallest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns the minimum element")
    public void testSmallestNormal() {
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: throws IllegalStateException on empty set")
    public void testSmallestEdgeEmpty() {
        assertThrows(IllegalStateException.class, () -> emptySet.smallest());
    }

    // -----------------------------------------------------------------------
    // add()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: adds a new element and increments length")
    public void testAddNormal() {
        emptySet.add(5);
        assertTrue(emptySet.contains(5));
        assertEquals(1, emptySet.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate values are not inserted")
    public void testAddEdgeDuplicate() {
        set1.add(2);
        set1.add(2);
        assertEquals(3, set1.length());
    }

    // -----------------------------------------------------------------------
    // remove()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes a present element")
    public void testRemoveNormal() {
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("remove() - edge: removing a value not present does nothing")
    public void testRemoveEdgeNotPresent() {
        set1.remove(99);
        assertEquals(3, set1.length());
    }

    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - normal: returns false on a non-empty set")
    public void testIsEmptyNormal() {
        assertFalse(set1.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - edge: returns true on an empty set")
    public void testIsEmptyEdge() {
        assertTrue(emptySet.isEmpty());
    }

    // -----------------------------------------------------------------------
    // union()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: result contains all elements from both sets without duplicates")
    public void testUnionNormal() {
        IntegerSet result = set1.union(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        assertTrue(result.equals(expected));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("union() - edge: union with empty set returns copy of original")
    public void testUnionEdgeWithEmpty() {
        IntegerSet result = set1.union(emptySet);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("union() - edge: throws IllegalArgumentException when argument is null")
    public void testUnionEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.union(null));
    }

    // -----------------------------------------------------------------------
    // intersect()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: result contains only common elements")
    public void testIntersectNormal() {
        IntegerSet result = set1.intersect(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(2);
        expected.add(3);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("intersect() - edge: no common elements returns empty set")
    public void testIntersectEdgeNoCommon() {
        IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3);
        b.add(4);
        assertTrue(a.intersect(b).isEmpty());
    }

    @Test
    @DisplayName("intersect() - edge: throws IllegalArgumentException when argument is null")
    public void testIntersectEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.intersect(null));
    }

    // -----------------------------------------------------------------------
    // diff()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: returns elements in A but not in B")
    public void testDiffNormal() {
        IntegerSet result = set1.diff(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("diff() - edge: identical sets produce empty diff")
    public void testDiffEdgeIdentical() {
        IntegerSet copy = new IntegerSet();
        copy.add(1);
        copy.add(2);
        copy.add(3);
        assertTrue(set1.diff(copy).isEmpty());
    }

    @Test
    @DisplayName("diff() - edge: throws IllegalArgumentException when argument is null")
    public void testDiffEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.diff(null));
    }

    // -----------------------------------------------------------------------
    // complement()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: returns elements in B but not in A")
    public void testComplementNormal() {
        IntegerSet result = set1.complement(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(4);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets — complement returns all of B")
    public void testComplementEdgeDisjoint() {
        IntegerSet a = new IntegerSet();
        a.add(1);
        IntegerSet b = new IntegerSet();
        b.add(5);
        b.add(6);
        assertTrue(a.complement(b).equals(b));
    }

    @Test
    @DisplayName("complement() - edge: throws IllegalArgumentException when argument is null")
    public void testComplementEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.complement(null));
    }

    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: elements sorted ascending in [x, y, z] format")
    public void testToStringNormal() {
        IntegerSet s = new IntegerSet();
        s.add(3);
        s.add(1);
        s.add(2);
        assertEquals("[1, 2, 3]", s.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set returns []")
    public void testToStringEdgeEmpty() {
        assertEquals("[]", emptySet.toString());
    }
}