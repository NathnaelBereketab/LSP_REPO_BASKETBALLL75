package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers.
 * A set cannot contain duplicates and supports standard set operations.
 * 
 * @author Assignment 6 - Integer Set Implementation
 */
public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Clears the internal representation of the set.
   */
  public void clear() {
    set.clear();
  }

  /**
   * Returns the number of elements in the set.
   * 
   * @return the number of elements in the set
   */
  public int length() {
    return set.size();
  }

  /*
   * Returns true if the 2 sets are equal, false otherwise;
   * Two sets are equal if they contain all of the same values in ANY order.
   * This overrides the equals method from the Object class.
   */
  @Override
  public boolean equals(Object o) { 
    if (o == null) {
      return false;
    }
    
    if (this == o) {
      return true;
    }
    
    if (!(o instanceof IntegerSet)) {
      return false;
    }
    
    IntegerSet other = (IntegerSet) o;
    
    if (this.set.size() != other.set.size()) {
      return false;
    }
    
    return this.set.containsAll(other.set);
  }

  /**
   * Returns true if the set contains the value, otherwise false.
   * 
   * @param value the value to check for membership
   * @return true if the set contains the value, false otherwise
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns the largest item in the set (throws IllegalStateException if empty).
   * 
   * @return the largest item in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    
    int max = set.get(0);
    for (int i = 1; i < set.size(); i++) {
      if (set.get(i) > max) {
        max = set.get(i);
      }
    }
    return max;
  }

  /**
   * Returns the smallest item in the set (throws IllegalStateException if empty).
   * 
   * @return the smallest item in the set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest()  { 
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    
    int min = set.get(0);
    for (int i = 1; i < set.size(); i++) {
      if (set.get(i) < min) {
        min = set.get(i);
      }
    }
    return min;
  }

  /**
   * Adds an item to the set or does nothing if already present.
   * 
   * @param item the item to add to the set
   */
  public void add(int item) { 
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * Removes an item from the set or does nothing if not there.
   * 
   * @param item the item to remove from the set
   */
  public void remove(int item) { 
    set.remove(Integer.valueOf(item));
  }

  /**
   * Set union: modifies this to contain all unique elements in this or other.
   * 
   * @param other the other set to union with
   */
  public void union(IntegerSet other) { 
    for (Integer item : other.set) {
      this.add(item);
    }
  }

  /**
   * Set intersection: modifies this to contain only elements in both sets.
   * 
   * @param other the other set to intersect with
   */
  public void intersect(IntegerSet other) { 
    this.set.retainAll(other.set);
  }

  /**
   * Set difference (this \ other): modifies this to remove elements found in other.
   * 
   * @param other the other set to compute difference with
   */
  public void diff(IntegerSet other) { 
    this.set.removeAll(other.set);
  }

  /**
   * Set complement: modifies this to become (other \ this).
   * 
   * @param other the other set to compute complement with
   */
  public void complement(IntegerSet other) { 
    // Create a copy of the current set to preserve it
    List<Integer> tempThis = new ArrayList<Integer>(this.set);
    // Clear this set
    this.set.clear();
    // Add all elements from other that are not in the original this
    for (Integer item : other.set) {
      if (!tempThis.contains(item)) {
        this.set.add(item);
      }
    }
  }

  /**
   * Returns true if the set is empty, false otherwise.
   * 
   * @return true if the set is empty, false otherwise
   */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * Returns a String representation; overrides Object.toString().
   * 
   * @return a string representation of the set in the format [1, 2, 3]
   */
  @Override
  public String toString() { 
    if (set.isEmpty()) {
      return "[]";
    }
    
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < set.size(); i++) {
      sb.append(set.get(i));
      if (i < set.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}

