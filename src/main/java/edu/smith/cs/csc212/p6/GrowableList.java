//P6 Lists
//Emily Rhyu

package edu.smith.cs.csc212.p6;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
//Imports
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

/**
 * Source for Big O Notation:
 * http://bigocheatsheet.com/
 */

/**
 * 
 * Create GrowableList
 *
 *	Similar to fixed size list, create array and use int fill to keep trakc of size
 *
 * @param <T> what type of item goes in list
 * @param START_SIZE - tell how big list is initially, can grow in future unlike
 * fixed size list
 * 
 */
public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;
	
	/**
	 * Create new Growable list using instance variables
	 */
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}

	/**
	 * Deletes item at index 0 AKA front of list
	 * 
	 * O(n)-indicate some change in growth of function, speed proportional to amt data
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	@Override
	public T removeFront() {
		return removeIndex(0);
	}

	/**
	 * Deletes last item of list
	 * 
	 * O(1)-indicate constant rate regardless of amt of data
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	@Override
	public T removeBack() {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		fill--;
		T value = this.getIndex(fill);
		this.array[fill] = null;
		return value;
	}

	/**
	 * Removes item at index(int index)
	 * 
	 * O(n)-indicate some change in growth of function, speed proportional to amt data
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 * @param index a number from 0 to size (excluding size).
	 * @throws BadIndexError  if the index does not exist.
	 */
	@Override
	public T removeIndex(int index) {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}

	/**
	 * Adds item to index 0 AKA front of list using AddIndex method
	 * 
	 * O(n)-indicate some change in growth of function, speed proportional to amt data
	 * 
	 * 
	 * @param item the data to add to the list.
	 */
	@Override
	public void addFront(T item) {
		addIndex(item, 0);	
	}

	/**
	 * Add an item to the back of this list. The item should be at
	 * getIndex(size()-1) after this call.
	 * 
	 * O(1)-indicate constant rate regardless of amt of data
	 * 
	 * @param item the data to add to the list.
	 * @throws RanOutOfSpaceError if number of items in list is over array length
	 */
	@Override
	public void addBack(T item) {
		if (fill >= this.array.length) { 
			int newSize=fill*2;
			Object[] newArray = new Object[newSize];
			for (int i=0; i<array.length; i++) {
				newArray[i] = array[i];
				}
		}
		this.array[fill++] = item;
	}

	/**
	 *  Add an item before ``index`` in this list. 
	 * 
	 * O(n)-indicate some change in growth of function, speed proportional to amt data
	 * 
	 * 
	 * @param item  the data to add to the list.
	 * @param index the index at which to add the item.
	 * @throws RanOutOfSpaceError if number items is over array length
	 */
	@Override
	public void addIndex(T item, int index) {
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
		}
	
	/**
	 * Get the first item in the list.
	 * 
	 * O(1)-no matter how much data, will execute at constant time
	 * 
	 * @return the item.
	 * @throws EmptyListError
	 */
	@Override
	public T getFront() {
		return this.getIndex(0);
	}

	/**
	 * Get the last item in the list.
	 * 
	 * O(1)-no matter how much data, will execute at constant time
	 * 
	 * @return the item.
	 * @throws EmptyListError
	 */
	@Override
	public T getBack() {
		return this.getIndex(this.fill-1);
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	
	/**
	 * Find the index-th element of this list.
	 * 
	 * O(1)-no matter how much data, will execute at constant time
	 * 
	 * @param index a number from 0 to size, excluding size.
	 * @return the value at index.
	 * @throws BadIndexError if the index does not exist.
	 */
	@Override
	public T getIndex(int index) {
		return (T) this.array[index];
	}

	/**
	 * Calculate the size of the list.
	 * 
	 * O(1)-no matter how much data, will execute at constant time
	 * 
	 * @return the length of the list, or zero if empty.
	 */
	@Override
	public int size() {
		return fill;
	}

	/**
	 * This is true if the list is empty. Looks at fill.
	 * 
	 * @return true if the list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return fill == 0;
	}


}
