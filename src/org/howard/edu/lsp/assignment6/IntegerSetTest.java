package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for IntegerSet (Assignment 6).
 *
 * Every method has at least one normal case and one edge case,
 * precisely matching the Assignment 6 Required Edge Cases rubric.
 *
 * @author Erica Okeh
 * @version 1.0
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

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
    @DisplayName("clear() - normal case: empties a populated set")
    public void testClearNormal() {
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("clear() - edge case: calling clear() on an already-empty set is safe")
    public void testClearEdgeAlreadyEmpty() {
        emptySet.clear();
        assertTrue(emptySet.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("length() - normal case: returns correct cardinality")
    public void testLengthNormal() {
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("length() - edge case: returns 0 for empty set")
    public void testLengthEdgeEmpty() {
        assertEquals(0, emptySet.length());
    }

    // -----------------------------------------------------------------------
    // equals()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("equals() - edge case: same elements different order")
    public void testEqualsEdgeSameElementsDifferentOrder() {
        IntegerSet copy = new IntegerSet();
        copy.add(3); // Added in reverse order
        copy.add(2);
        copy.add(1);
        assertTrue(set1.equals(copy));
    }

    @Test
    @DisplayName("equals() - normal case: completely different sets")
    public void testEqualsNormalDifferentSets() {
        assertFalse(set1.equals(set2));
    }

    // -----------------------------------------------------------------------
    // contains()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("contains() - normal case: value is present")
    public void testContainsNormal() {
        assertTrue(set1.contains(2));
    }

    @Test
    @DisplayName("contains() - edge case: value not present")
    public void testContainsEdgeValueNotPresent() {
        assertFalse(set1.contains(99));
    }

    // -----------------------------------------------------------------------
    // largest()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("largest() - normal case: returns largest in multiple-element set")
    public void testLargestNormal() {
        assertEquals(3, set1.largest());
    }

    @Test
    @DisplayName("largest() - edge case: single element")
    public void testLargestEdgeSingleElement() {
        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals(42, single.largest());
    }

    @Test
    @DisplayName("largest() - edge case: empty (exception)")
    public void testLargestEdgeEmptyException() {
        assertThrows(IllegalStateException.class, () -> emptySet.largest());
    }

    // -----------------------------------------------------------------------
    // smallest()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("smallest() - normal case: returns smallest in multiple-element set")
    public void testSmallestNormal() {
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("smallest() - edge case: single element")
    public void testSmallestEdgeSingleElement() {
        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals(42, single.smallest());
    }

    @Test
    @DisplayName("smallest() - edge case: empty (exception)")
    public void testSmallestEdgeEmptyException() {
        assertThrows(IllegalStateException.class, () -> emptySet.smallest());
    }

    // -----------------------------------------------------------------------
    // add()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("add() - normal case: adding a new, unique value")
    public void testAddNormal() {
        emptySet.add(5);
        assertTrue(emptySet.contains(5));
    }

    @Test
    @DisplayName("add() - edge case: duplicate values")
    public void testAddEdgeDuplicateValues() {
        set1.add(2); // 2 is already in set1
        assertEquals(3, set1.length()); // Length should not change
    }

    // -----------------------------------------------------------------------
    // remove()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("remove() - normal case: removing an existing value")
    public void testRemoveNormal() {
        set1.remove(2);
        assertFalse(set1.contains(2));
    }

    @Test
    @DisplayName("remove() - edge case: value not present")
    public void testRemoveEdgeValueNotPresent() {
        set1.remove(99); // 99 is not in set1
        assertEquals(3, set1.length()); // Should not fail, length remains same
    }

    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("isEmpty() - normal case: returns false on a non-empty set")
    public void testIsEmptyNormal() {
        assertFalse(set1.isEmpty(), "set1 should be non-empty");
    }

    @Test
    @DisplayName("isEmpty() - edge case: returns true on an empty set")
    public void testIsEmptyEdge() {
        assertTrue(emptySet.isEmpty(), "emptySet should be empty");
    }

    // -----------------------------------------------------------------------
    // union()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("union() - normal case: standard union of two sets")
    public void testUnionNormal() {
        IntegerSet result = set1.union(set2);
        assertEquals(4, result.length()); // Combined {1,2,3,4}
    }

    @Test
    @DisplayName("union() - edge case: with empty set")
    public void testUnionEdgeWithEmptySet() {
        IntegerSet result = set1.union(emptySet);
        assertTrue(result.equals(set1)); // Union with empty is the original set
    }

    @Test
    @DisplayName("union() - edge case: null argument throws exception")
    public void testUnionEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.union(null));
    }

    // -----------------------------------------------------------------------
    // intersect()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("intersect() - normal case: intersection of overlapping sets")
    public void testIntersectNormal() {
        IntegerSet result = set1.intersect(set2); // set1={1,2,3}, set2={2,3,4}
        assertTrue(result.contains(2) && result.contains(3));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("intersect() - edge case: no common elements")
    public void testIntersectEdgeNoCommonElements() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3); b.add(4);
        
        IntegerSet result = a.intersect(b);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("intersect() - edge case: null argument throws exception")
    public void testIntersectEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.intersect(null));
    }

    // -----------------------------------------------------------------------
    // diff()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("diff() - normal case: difference of two overlapping sets")
    public void testDiffNormal() {
        IntegerSet result = set1.diff(set2); // {1,2,3} - {2,3,4} = {1}
        assertTrue(result.contains(1));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("diff() - edge case: identical sets")
    public void testDiffEdgeIdenticalSets() {
        IntegerSet copy = new IntegerSet();
        copy.add(1); copy.add(2); copy.add(3);
        
        IntegerSet result = set1.diff(copy);
        assertTrue(result.isEmpty()); // A - A = empty set
    }

    @Test
    @DisplayName("diff() - edge case: null argument throws exception")
    public void testDiffEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.diff(null));
    }

    // -----------------------------------------------------------------------
    // complement()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("complement() - normal case: standard complement (B - A)")
    public void testComplementNormal() {
        IntegerSet result = set1.complement(set2); // B - A: {2,3,4} - {1,2,3} = {4}
        assertTrue(result.contains(4));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("complement() - edge case: disjoint sets")
    public void testComplementEdgeDisjointSets() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(3); b.add(4);
        
        // B - A for disjoint sets is just B
        IntegerSet result = a.complement(b);
        assertTrue(result.equals(b)); 
    }

    @Test
    @DisplayName("complement() - edge case: null argument throws exception")
    public void testComplementEdgeNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.complement(null));
    }

    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------
    @Test
    @DisplayName("toString() - normal case: populated set")
    public void testToStringNormal() {
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("toString() - edge case: empty set")
    public void testToStringEdgeEmptySet() {
        assertEquals("[]", emptySet.toString());
    }
}