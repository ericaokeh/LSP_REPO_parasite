package org.howard.edu.lsp.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for IntegerSet.
 *
 * Covers all required methods with normal cases, edge cases, null inputs,
 * mutation safety, large inputs, chained operations, and the Java equals/hashCode contract.
 *
 * @author Erica Okeh
 * @version 1.0
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    /**
     * Initializes test sets before each test method.
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
    // clear
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("clear() empties a non-empty set")
    public void testClear() {
        set1.clear();
        assertTrue(set1.isEmpty(), "Set should be empty after clear()");
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("clear() on an already-empty set is safe")
    public void testClearAlreadyEmpty() {
        emptySet.clear();
        assertTrue(emptySet.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("length() returns correct count")
    public void testLength() {
        assertEquals(3, set1.length(), "set1 should have length 3");
        assertEquals(0, emptySet.length(), "Empty set should have length 0");
    }

    @Test
    @DisplayName("length() is not inflated by duplicate adds")
    public void testLengthNoDuplicates() {
        set1.add(2);
        set1.add(2);
        assertEquals(3, set1.length());
    }

    // -----------------------------------------------------------------------
    // equals / hashCode
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("equals() returns true for sets with same elements in different order")
    public void testEquals() {
        IntegerSet copy = new IntegerSet();
        copy.add(3); copy.add(1); copy.add(2);
        assertTrue(set1.equals(copy), "Sets with same elements should be equal");
    }

    @Test
    @DisplayName("equals() returns false for sets with different elements")
    public void testEqualsFalse() {
        assertFalse(set1.equals(set2), "Different sets should not be equal");
    }

    @Test
    @DisplayName("equals() returns false when passed null")
    public void testEqualsNull() {
        assertFalse(set1.equals(null), "Comparing with null should return false");
    }

    @Test
    @DisplayName("equals() returns false when passed a non-IntegerSet object")
    public void testEqualsWrongType() {
        Object notASet = "not a set";
        assertFalse(set1.equals(notASet));
    }

    @Test
    @DisplayName("equals() returns true when compared to itself")
    public void testEqualsSameReference() {
        assertTrue(set1.equals(set1));
    }

    @Test
    @DisplayName("equals() returns true for two empty sets")
    public void testEqualsBothEmpty() {
        assertTrue(emptySet.equals(new IntegerSet()));
    }

    @Test
    @DisplayName("hashCode() is equal for two equal sets")
    public void testHashCodeEqualSets() {
        IntegerSet copy = new IntegerSet();
        copy.add(3); copy.add(1); copy.add(2);
        assertEquals(set1.hashCode(), copy.hashCode());
    }

    @Test
    @DisplayName("hashCode() is equal for two empty sets")
    public void testHashCodeBothEmpty() {
        assertEquals(emptySet.hashCode(), new IntegerSet().hashCode());
    }

    @Test
    @DisplayName("hashCode() contract holds inside a HashSet")
    public void testHashCodeInHashSet() {
        IntegerSet copy = new IntegerSet();
        copy.add(2); copy.add(1); copy.add(3);
        HashSet<IntegerSet> container = new HashSet<>();
        container.add(set1);
        assertTrue(container.contains(copy), "Equal IntegerSet must be found in HashSet");
    }

    // -----------------------------------------------------------------------
    // contains
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("contains() returns true for a present element")
    public void testContainsPresent() {
        assertTrue(set1.contains(2));
    }

    @Test
    @DisplayName("contains() returns false for an absent element")
    public void testContainsAbsent() {
        assertFalse(set1.contains(99));
    }

    @Test
    @DisplayName("contains() returns false on an empty set")
    public void testContainsOnEmptySet() {
        assertFalse(emptySet.contains(0));
    }

    // -----------------------------------------------------------------------
    // largest / smallest
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("largest() returns the maximum element")
    public void testLargest() {
        assertEquals(3, set1.largest());
    }

    @Test
    @DisplayName("largest() on an empty set throws IllegalStateException")
    public void testLargestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> emptySet.largest());
    }

    @Test
    @DisplayName("largest() works with negative numbers")
    public void testLargestNegatives() {
        IntegerSet s = new IntegerSet();
        s.add(-5); s.add(-1); s.add(-3);
        assertEquals(-1, s.largest());
    }

    @Test
    @DisplayName("smallest() returns the minimum element")
    public void testSmallest() {
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("smallest() on an empty set throws IllegalStateException")
    public void testSmallestEmptyThrows() {
        assertThrows(IllegalStateException.class, () -> emptySet.smallest());
    }

    @Test
    @DisplayName("smallest() works with negative numbers")
    public void testSmallestNegatives() {
        IntegerSet s = new IntegerSet();
        s.add(-5); s.add(-1); s.add(-3);
        assertEquals(-5, s.smallest());
    }

    // -----------------------------------------------------------------------
    // add / remove
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add() does not insert duplicate elements")
    public void testAddNoDuplicates() {
        set1.add(2);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("remove() eliminates a present element")
    public void testRemove() {
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("remove() on a non-existent element does nothing")
    public void testRemoveNonExistent() {
        set1.remove(99);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("remove() on an empty set does not throw")
    public void testRemoveFromEmptySet() {
        emptySet.remove(1);
        assertEquals(0, emptySet.length());
    }

    // -----------------------------------------------------------------------
    // isEmpty
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() returns true on a new set")
    public void testIsEmptyTrue() {
        assertTrue(emptySet.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() returns false on a non-empty set")
    public void testIsEmptyFalse() {
        assertFalse(set1.isEmpty());
    }

    // -----------------------------------------------------------------------
    // union
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union() contains all elements from both sets without duplicates")
    public void testUnion() {
        IntegerSet result = set1.union(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(1); expected.add(2); expected.add(3); expected.add(4);
        assertTrue(result.equals(expected));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("union() with empty set returns copy of non-empty set")
    public void testUnionWithEmpty() {
        IntegerSet result = set1.union(emptySet);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("union() of two empty sets returns empty set")
    public void testUnionBothEmpty() {
        assertTrue(emptySet.union(new IntegerSet()).isEmpty());
    }

    @Test
    @DisplayName("union() does not modify original sets")
    public void testUnionNoMutation() {
        set1.union(set2);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("union() throws IllegalArgumentException when argument is null")
    public void testUnionNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.union(null));
    }

    // -----------------------------------------------------------------------
    // intersect
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("intersect() contains only common elements")
    public void testIntersect() {
        IntegerSet result = set1.intersect(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(2); expected.add(3);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("intersect() of disjoint sets returns empty set")
    public void testIntersectDisjoint() {
        IntegerSet a = new IntegerSet(); a.add(1); a.add(2);
        IntegerSet b = new IntegerSet(); b.add(3); b.add(4);
        assertTrue(a.intersect(b).isEmpty());
    }

    @Test
    @DisplayName("intersect() with empty set returns empty set")
    public void testIntersectWithEmpty() {
        assertTrue(set1.intersect(emptySet).isEmpty());
    }

    @Test
    @DisplayName("intersect() does not modify original sets")
    public void testIntersectNoMutation() {
        set1.intersect(set2);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("intersect() throws IllegalArgumentException when argument is null")
    public void testIntersectNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.intersect(null));
    }

    // -----------------------------------------------------------------------
    // diff
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("diff() returns elements in A but not in B")
    public void testDiff() {
        IntegerSet result = set1.diff(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("diff() with empty B returns copy of A")
    public void testDiffWithEmptyB() {
        assertTrue(set1.diff(emptySet).equals(set1));
    }

    @Test
    @DisplayName("diff() of identical sets returns empty set")
    public void testDiffIdentical() {
        IntegerSet copy = new IntegerSet();
        copy.add(1); copy.add(2); copy.add(3);
        assertTrue(set1.diff(copy).isEmpty());
    }

    @Test
    @DisplayName("diff() does not modify original sets")
    public void testDiffNoMutation() {
        set1.diff(set2);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("diff() throws IllegalArgumentException when argument is null")
    public void testDiffNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.diff(null));
    }

    // -----------------------------------------------------------------------
    // complement
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("complement() returns elements in B but not in A")
    public void testComplement() {
        IntegerSet result = set1.complement(set2);
        IntegerSet expected = new IntegerSet();
        expected.add(4);
        assertTrue(result.equals(expected));
    }

    @Test
    @DisplayName("complement() with empty B returns empty set")
    public void testComplementEmptyB() {
        assertTrue(set1.complement(emptySet).isEmpty());
    }

    @Test
    @DisplayName("complement() when A is empty returns copy of B")
    public void testComplementEmptyA() {
        assertTrue(emptySet.complement(set1).equals(set1));
    }

    @Test
    @DisplayName("complement() does not modify original sets")
    public void testComplementNoMutation() {
        set1.complement(set2);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
    }

    @Test
    @DisplayName("complement() throws IllegalArgumentException when argument is null")
    public void testComplementNull() {
        assertThrows(IllegalArgumentException.class, () -> set1.complement(null));
    }

    // -----------------------------------------------------------------------
    // toString
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("toString() returns [] for an empty set")
    public void testToStringEmpty() {
        assertEquals("[]", emptySet.toString());
    }

    @Test
    @DisplayName("toString() sorts elements in ascending order")
    public void testToStringSorted() {
        IntegerSet s = new IntegerSet();
        s.add(3); s.add(1); s.add(2);
        assertEquals("[1, 2, 3]", s.toString());
    }

    @Test
    @DisplayName("toString() handles negative numbers correctly")
    public void testToStringNegatives() {
        IntegerSet s = new IntegerSet();
        s.add(0); s.add(-2); s.add(3);
        assertEquals("[-2, 0, 3]", s.toString());
    }

    @Test
    @DisplayName("toString() called repeatedly returns same value without mutating the set")
    public void testToStringIdempotent() {
        String first  = set1.toString();
        String second = set1.toString();
        String third  = set1.toString();
        assertEquals(first, second);
        assertEquals(second, third);
        assertEquals(3, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
    }

    @Test
    @DisplayName("toString() does not affect subsequent set operations")
    public void testToStringNoSideEffect() {
        set1.toString();
        IntegerSet result = set1.union(set2);
        assertEquals(4, result.length());
    }

    // -----------------------------------------------------------------------
    // equals — additional edge cases
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("equals() returns false when one set is empty and the other is not")
    public void testEqualsOneEmptyOneNot() {
        assertFalse(set1.equals(emptySet));
        assertFalse(emptySet.equals(set1));
    }

    @Test
    @DisplayName("equals() returns false for sets with different sizes")
    public void testEqualsDifferentSizes() {
        IntegerSet smaller = new IntegerSet();
        smaller.add(1); smaller.add(2);
        assertFalse(set1.equals(smaller));
    }

    // -----------------------------------------------------------------------
    // largest / smallest — single element
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("largest() on a single-element set returns that element")
    public void testLargestSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(42);
        assertEquals(42, s.largest());
    }

    @Test
    @DisplayName("smallest() on a single-element set returns that element")
    public void testSmallestSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(42);
        assertEquals(42, s.smallest());
    }

    // -----------------------------------------------------------------------
    // add — zero, negatives, re-add after remove
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add() and contains() work correctly with zero and negatives")
    public void testAddZeroAndNegative() {
        IntegerSet s = new IntegerSet();
        s.add(0); s.add(-7);
        assertTrue(s.contains(0));
        assertTrue(s.contains(-7));
        assertEquals(2, s.length());
    }

    @Test
    @DisplayName("element can be re-added after being removed")
    public void testReAddAfterRemove() {
        set1.remove(2);
        assertFalse(set1.contains(2));
        set1.add(2);
        assertTrue(set1.contains(2));
        assertEquals(3, set1.length());
    }

    // -----------------------------------------------------------------------
    // remove — contains false after remove, remove all → isEmpty
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("contains() returns false immediately after element is removed")
    public void testContainsFalseAfterRemove() {
        set1.remove(1);
        assertFalse(set1.contains(1));
    }

    @Test
    @DisplayName("isEmpty() returns true after removing all elements one by one")
    public void testIsEmptyAfterRemovingAll() {
        set1.remove(1);
        set1.remove(2);
        set1.remove(3);
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    // -----------------------------------------------------------------------
    // clear — set is functional after clear
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("set is fully functional after clear(); add and contains work normally")
    public void testSetFunctionalAfterClear() {
        set1.clear();
        set1.add(99);
        assertTrue(set1.contains(99));
        assertEquals(1, set1.length());
        assertFalse(set1.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length — after remove
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("length() decrements correctly after remove")
    public void testLengthAfterRemove() {
        set1.remove(1);
        assertEquals(2, set1.length());
        set1.remove(2);
        assertEquals(1, set1.length());
    }

    // -----------------------------------------------------------------------
    // union — with itself
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union() of a set with itself returns a set equal to the original")
    public void testUnionWithItself() {
        IntegerSet result = set1.union(set1);
        assertTrue(result.equals(set1));
        assertEquals(set1.length(), result.length());
    }

    // -----------------------------------------------------------------------
    // intersect — with itself, subset case
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("intersect() of a set with itself returns a set equal to the original")
    public void testIntersectWithItself() {
        IntegerSet result = set1.intersect(set1);
        assertTrue(result.equals(set1));
    }

    @Test
    @DisplayName("intersect() when one set is a subset returns the subset")
    public void testIntersectSubset() {
        // set2 contains {2,3,4}; subset = {2,3}
        IntegerSet subset = new IntegerSet();
        subset.add(2); subset.add(3);
        IntegerSet result = set2.intersect(subset);
        assertTrue(result.equals(subset));
    }

    // -----------------------------------------------------------------------
    // diff — non-commutativity, A subset of B
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("diff() is not commutative: A−B does not equal B−A (when sets differ)")
    public void testDiffNotCommutative() {
        IntegerSet aMinusB = set1.diff(set2); // {1}
        IntegerSet bMinusA = set2.diff(set1); // {4}
        assertFalse(aMinusB.equals(bMinusA));
    }

    @Test
    @DisplayName("diff() returns empty set when A is a subset of B")
    public void testDiffASubsetOfB() {
        IntegerSet a = new IntegerSet(); a.add(2); a.add(3); // subset of set2 {2,3,4}
        assertTrue(a.diff(set2).isEmpty());
    }

    // -----------------------------------------------------------------------
    // complement — differs from diff, B subset of A
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("complement() is the reverse of diff(): complement(B) = B − A, diff(B) = A − B")
    public void testComplementVsDiff() {
        IntegerSet comp = set1.complement(set2); // {4}  (set2 − set1)
        IntegerSet diff = set1.diff(set2);       // {1}  (set1 − set2)
        assertFalse(comp.equals(diff));
        assertTrue(comp.contains(4));
        assertTrue(diff.contains(1));
    }

    @Test
    @DisplayName("complement() returns empty set when B is a subset of A")
    public void testComplementBSubsetOfA() {
        IntegerSet b = new IntegerSet(); b.add(1); b.add(2); // subset of set1 {1,2,3}
        assertTrue(set1.complement(b).isEmpty());
    }

    // -----------------------------------------------------------------------
    // toString — single element
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("toString() of a single-element set is formatted correctly")
    public void testToStringSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        assertEquals("[5]", s.toString());
    }

    // -----------------------------------------------------------------------
    // operations with empty sets
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("all set operations behave correctly with empty set operands")
    public void testOperationsWithEmptySet() {
        assertTrue(set1.union(emptySet).equals(set1));
        assertTrue(set1.intersect(emptySet).isEmpty());
        assertTrue(set1.diff(emptySet).equals(set1));
        assertTrue(set1.complement(emptySet).isEmpty());
    }

    // -----------------------------------------------------------------------
    // large input sets
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add and contains work correctly on 1000 elements")
    public void testLargeSetAddContains() {
        IntegerSet large = new IntegerSet();
        for (int i = 0; i < 1000; i++) large.add(i);
        assertEquals(1000, large.length());
        assertTrue(large.contains(0));
        assertTrue(large.contains(999));
        assertFalse(large.contains(1000));
    }

    @Test
    @DisplayName("union of two large disjoint sets has correct cardinality")
    public void testLargeSetUnion() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        for (int i = 0;   i < 500; i++) a.add(i);
        for (int i = 500; i < 1000; i++) b.add(i);
        assertEquals(1000, a.union(b).length());
    }

    @Test
    @DisplayName("largest() and smallest() are correct on a 1000-element set")
    public void testLargeSetLargestSmallest() {
        IntegerSet large = new IntegerSet();
        for (int i = 1; i <= 1000; i++) large.add(i);
        assertEquals(1000, large.largest());
        assertEquals(1, large.smallest());
    }

    // -----------------------------------------------------------------------
    // chained operations
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union(B).intersect(C) produces correct result")
    public void testChainedUnionIntersect() {
        IntegerSet setC = new IntegerSet();
        setC.add(3); setC.add(6); setC.add(7);
        // ({1,2,3} ∪ {2,3,4}) ∩ {3,6,7} = {1,2,3,4} ∩ {3,6,7} = {3}
        IntegerSet result = set1.union(set2).intersect(setC);
        assertEquals(1, result.length());
        assertTrue(result.contains(3));
    }

    @Test
    @DisplayName("diff(B).union(C) produces correct result")
    public void testChainedDiffUnion() {
        IntegerSet setC = new IntegerSet();
        setC.add(9);
        // ({1,2,3} − {2,3,4}) ∪ {9} = {1} ∪ {9} = {1,9}
        IntegerSet result = set1.diff(set2).union(setC);
        assertEquals(2, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(9));
    }

    @Test
    @DisplayName("chained operations do not mutate any original set")
    public void testChainedNoMutation() {
        IntegerSet setC = new IntegerSet();
        setC.add(3); setC.add(5);
        set1.union(set2).intersect(setC);
        assertEquals(3, set1.length());
        assertEquals(3, set2.length());
        assertEquals(2, setC.length());
    }
}