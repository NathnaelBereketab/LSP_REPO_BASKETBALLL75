package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet class.
 * Tests all public methods with normal and edge cases.
 * 
 * @author Assignment 6 - Integer Set Testing
 */
class IntegerSetTest {
  private IntegerSet set1;
  private IntegerSet set2;
  private IntegerSet emptySet;

  @BeforeEach
  void setUp() {
    set1 = new IntegerSet();
    set2 = new IntegerSet();
    emptySet = new IntegerSet();
  }

  // ========== clear() Tests ==========
  @Test
  void testClear() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    set1.clear();
    assertEquals(0, set1.length());
    assertTrue(set1.isEmpty());
  }

  @Test
  void testClearEmptySet() {
    emptySet.clear();
    assertTrue(emptySet.isEmpty());
    assertEquals(0, emptySet.length());
  }

  // ========== length() Tests ==========
  @Test
  void testLength() {
    assertEquals(0, emptySet.length());
    set1.add(1);
    assertEquals(1, set1.length());
    set1.add(2);
    assertEquals(2, set1.length());
    set1.add(1); // Duplicate - should not increase length
    assertEquals(2, set1.length());
  }

  @Test
  void testLengthAfterRemove() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    set1.remove(2);
    assertEquals(2, set1.length());
  }

  // ========== equals() Tests ==========
  /**
   * Tests that equals() correctly identifies sets with same elements in different order.
   * Sets are order-independent, so [1,2,3] should equal [3,1,2].
   */
  @Test
  void testEqualsSameSets() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    
    set2.add(3);
    set2.add(1);
    set2.add(2);
    
    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  @Test
  void testEqualsDifferentSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    
    assertFalse(set1.equals(set2));
    assertFalse(set2.equals(set1));
  }

  @Test
  void testEqualsEmptySets() {
    assertTrue(emptySet.equals(set1));
    assertTrue(set1.equals(emptySet));
  }

  @Test
  void testEqualsNull() {
    assertFalse(set1.equals(null));
  }

  @Test
  void testEqualsDifferentObjectType() {
    set1.add(1);
    assertFalse(set1.equals("not a set"));
    assertFalse(set1.equals(123));
  }

  @Test
  void testEqualsSameReference() {
    assertTrue(set1.equals(set1));
  }

  @Test
  void testEqualsDifferentSizes() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    
    assertFalse(set1.equals(set2));
    assertFalse(set2.equals(set1));
  }

  // ========== contains() Tests ==========
  @Test
  void testContains() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(4));
  }

  @Test
  void testContainsEmptySet() {
    assertFalse(emptySet.contains(1));
    assertFalse(emptySet.contains(0));
  }

  @Test
  void testContainsAfterRemove() {
    set1.add(1);
    set1.add(2);
    assertTrue(set1.contains(1));
    set1.remove(1);
    assertFalse(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  // ========== largest() Tests ==========
  @Test
  void testLargest() {
    set1.add(5);
    set1.add(2);
    set1.add(8);
    set1.add(1);
    assertEquals(8, set1.largest());
  }

  @Test
  void testLargestSingleElement() {
    set1.add(42);
    assertEquals(42, set1.largest());
  }

  @Test
  void testLargestNegativeNumbers() {
    set1.add(-5);
    set1.add(-10);
    set1.add(-1);
    assertEquals(-1, set1.largest());
  }

  /**
   * Tests that largest() throws IllegalStateException when called on empty set.
   * This is a required edge case per assignment specification.
   */
  @Test
  void testLargestEmptySet() {
    assertThrows(IllegalStateException.class, () -> {
      emptySet.largest();
    });
  }

  @Test
  void testLargestMixedNumbers() {
    set1.add(-5);
    set1.add(0);
    set1.add(10);
    assertEquals(10, set1.largest());
  }

  // ========== smallest() Tests ==========
  @Test
  void testSmallest() {
    set1.add(5);
    set1.add(2);
    set1.add(8);
    set1.add(1);
    assertEquals(1, set1.smallest());
  }

  @Test
  void testSmallestSingleElement() {
    set1.add(42);
    assertEquals(42, set1.smallest());
  }

  @Test
  void testSmallestNegativeNumbers() {
    set1.add(-5);
    set1.add(-10);
    set1.add(-1);
    assertEquals(-10, set1.smallest());
  }

  /**
   * Tests that smallest() throws IllegalStateException when called on empty set.
   * This is a required edge case per assignment specification.
   */
  @Test
  void testSmallestEmptySet() {
    assertThrows(IllegalStateException.class, () -> {
      emptySet.smallest();
    });
  }

  @Test
  void testSmallestMixedNumbers() {
    set1.add(-5);
    set1.add(0);
    set1.add(10);
    assertEquals(-5, set1.smallest());
  }

  // ========== add() Tests ==========
  @Test
  void testAdd() {
    set1.add(1);
    assertTrue(set1.contains(1));
    assertEquals(1, set1.length());
  }

  /**
   * Tests that adding a duplicate element does not increase set size.
   * Sets must not contain duplicates.
   */
  @Test
  void testAddDuplicate() {
    set1.add(1);
    set1.add(1); // Duplicate
    assertEquals(1, set1.length()); // Should still be 1
    assertTrue(set1.contains(1));
  }

  @Test
  void testAddMultiple() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
  }

  @Test
  void testAddZero() {
    set1.add(0);
    assertTrue(set1.contains(0));
    assertEquals(1, set1.length());
  }

  @Test
  void testAddNegative() {
    set1.add(-1);
    assertTrue(set1.contains(-1));
    assertEquals(1, set1.length());
  }

  // ========== remove() Tests ==========
  @Test
  void testRemove() {
    set1.add(1);
    set1.add(2);
    set1.remove(1);
    assertFalse(set1.contains(1));
    assertTrue(set1.contains(2));
    assertEquals(1, set1.length());
  }

  @Test
  void testRemoveNonExistent() {
    set1.add(1);
    set1.add(2);
    set1.remove(3); // Not in set
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  void testRemoveFromEmpty() {
    emptySet.remove(1);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  void testRemoveLastElement() {
    set1.add(1);
    set1.remove(1);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  // ========== union() Tests ==========
  /**
   * Tests union operation: set1 should contain all unique elements from both sets.
   * Note: union() modifies set1 (mutator method requirement).
   */
  @Test
  void testUnion() {
    set1.add(1);
    set1.add(2);
    set2.add(2);
    set2.add(3);
    
    set1.union(set2); // Modifies set1
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertEquals(3, set1.length()); // Duplicate 2 not counted twice
  }

  @Test
  void testUnionWithEmpty() {
    set1.add(1);
    set1.add(2);
    set1.union(emptySet);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  void testUnionEmptyWithNonEmpty() {
    set2.add(1);
    set2.add(2);
    emptySet.union(set2);
    assertEquals(2, emptySet.length());
    assertTrue(emptySet.contains(1));
    assertTrue(emptySet.contains(2));
  }

  /**
   * Tests union with self - edge case that should not change the set.
   * This verifies the operation is idempotent in this case.
   */
  @Test
  void testUnionWithSelf() {
    set1.add(1);
    set1.add(2);
    set1.union(set1); // Union with itself
    assertEquals(2, set1.length()); // Should remain unchanged
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  void testUnionDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.union(set2);
    assertEquals(4, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
  }

  // ========== intersect() Tests ==========
  /**
   * Tests intersection: set1 should contain only elements present in both sets.
   * set1 becomes [2, 3] after intersecting with set2 [2, 3, 4].
   */
  @Test
  void testIntersect() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.intersect(set2); // Modifies set1
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(1)); // Removed
    assertFalse(set1.contains(4)); // Not in set1 originally
    assertEquals(2, set1.length());
  }

  @Test
  void testIntersectDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.intersect(set2);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  @Test
  void testIntersectWithEmpty() {
    set1.add(1);
    set1.add(2);
    set1.intersect(emptySet);
    assertTrue(set1.isEmpty());
  }

  @Test
  void testIntersectEmptyWithNonEmpty() {
    set2.add(1);
    set2.add(2);
    emptySet.intersect(set2);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  void testIntersectWithSelf() {
    set1.add(1);
    set1.add(2);
    set1.intersect(set1);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  // ========== diff() Tests ==========
  /**
   * Tests set difference (this \ other): removes from set1 all elements found in set2.
   * set1 [1,2,3] diff set2 [2,3] should result in set1 [1].
   */
  @Test
  void testDiff() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(3);
    
    set1.diff(set2); // Removes 2 and 3 from set1
    assertTrue(set1.contains(1));
    assertFalse(set1.contains(2));
    assertFalse(set1.contains(3));
    assertEquals(1, set1.length());
  }

  @Test
  void testDiffDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.diff(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  void testDiffWithEmpty() {
    set1.add(1);
    set1.add(2);
    set1.diff(emptySet);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  void testDiffEmptyWithNonEmpty() {
    set2.add(1);
    set2.add(2);
    emptySet.diff(set2);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  void testDiffWithSelf() {
    set1.add(1);
    set1.add(2);
    set1.diff(set1);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  @Test
  void testDiffRemovesAll() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(2);
    
    set1.diff(set2);
    assertTrue(set1.isEmpty());
  }

  // ========== complement() Tests ==========
  /**
   * Tests complement operation: set1 becomes (set2 \ set1).
   * set1 [1,2] complement set2 [2,3,4] should result in set1 [3,4].
   */
  @Test
  void testComplement() {
    set1.add(1);
    set1.add(2);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.complement(set2); // set1 becomes elements in set2 but not in original set1
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2)); // 2 was in both, so excluded
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertEquals(2, set1.length());
  }

  @Test
  void testComplementWithEmpty() {
    set2.add(1);
    set2.add(2);
    emptySet.complement(set2);
    assertEquals(2, emptySet.length());
    assertTrue(emptySet.contains(1));
    assertTrue(emptySet.contains(2));
  }

  @Test
  void testComplementEmptyWithNonEmpty() {
    set1.add(1);
    set1.add(2);
    set1.complement(emptySet);
    assertTrue(set1.isEmpty());
  }

  @Test
  void testComplementDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    
    set1.complement(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2));
  }

  @Test
  void testComplementWithSelf() {
    set1.add(1);
    set1.add(2);
    set1.complement(set1);
    assertTrue(set1.isEmpty());
  }

  // ========== isEmpty() Tests ==========
  @Test
  void testIsEmpty() {
    assertTrue(emptySet.isEmpty());
    set1.add(1);
    assertFalse(set1.isEmpty());
  }

  @Test
  void testIsEmptyAfterClear() {
    set1.add(1);
    set1.add(2);
    assertFalse(set1.isEmpty());
    set1.clear();
    assertTrue(set1.isEmpty());
  }

  @Test
  void testIsEmptyAfterRemove() {
    set1.add(1);
    assertFalse(set1.isEmpty());
    set1.remove(1);
    assertTrue(set1.isEmpty());
  }

  // ========== toString() Tests ==========
  @Test
  void testToString() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    String result = set1.toString();
    assertTrue(result.startsWith("["));
    assertTrue(result.endsWith("]"));
    assertTrue(result.contains("1"));
    assertTrue(result.contains("2"));
    assertTrue(result.contains("3"));
  }

  @Test
  void testToStringEmpty() {
    assertEquals("[]", emptySet.toString());
  }

  @Test
  void testToStringSingleElement() {
    set1.add(42);
    assertEquals("[42]", set1.toString());
  }

  @Test
  void testToStringFormat() {
    set1.add(1);
    set1.add(2);
    String result = set1.toString();
    // Should be in format [x, y] or [y, x] (order may vary)
    assertTrue(result.equals("[1, 2]") || result.equals("[2, 1]"));
  }

  @Test
  void testToStringNegativeNumbers() {
    set1.add(-1);
    set1.add(-2);
    String result = set1.toString();
    assertTrue(result.contains("-1"));
    assertTrue(result.contains("-2"));
  }
}

