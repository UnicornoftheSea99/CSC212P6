//P6 Lists
//Emily Rhyu

package edu.smith.cs.csc212.p6;

//Imports
import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

/**
 * Source for Big O Notation:
 * http://bigocheatsheet.com/
 */

/**
 * 
 * Create FixedSizeList
 *
 * @param <T> - type of item in list
 */
public class FixedSizeList<T> implements P6List<T> {
	private Object[] array;
	private int fill;
	
	/**
	 * 
	 * @param maximumSize - make a fixed size list a fixed size list, set maximum on 
	 * how many things can be in list
	 * 
	 *@param fill -set to zero currently, used for keeping track number of items
	 *in list
	 */
	public FixedSizeList(int maximumSize) {
		this.array = new Object[maximumSize];
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
	 * 
	 * @param item the data to add to the list.
	 * @throws RanOutOfSpaceError if number of items in list is over array length
	 */
	@Override
	public void addBack(T item) {
		if (fill < array.length) {
			array[fill++] = item;
		} else {
			throw new RanOutOfSpaceError();
		}
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
		if (fill >= array.length) {
			throw new RanOutOfSpaceError();
		}
		// loop backwards, shifting items to the right.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
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
		if (index < 0 || index > fill) {
			throw new BadIndexError();
		}
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
		return this.fill;
	}

	/**
	 * This is true if the list is empty. Looks at fill.
	 * 
	 * @return true if the list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return this.fill == 0;
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
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
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
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(this.size()-1);
	}
}
