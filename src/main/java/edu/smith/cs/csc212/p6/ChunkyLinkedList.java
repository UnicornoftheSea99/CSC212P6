package edu.smith.cs.csc212.p6;


import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

/**
 * This is a data structure that has an array inside each node of a Linked List.
 * Therefore, we only make new nodes when they are full. Some remove operations
 * may be easier if you allow "chunks" to be partially filled.
 * 
 * @author jfoley
 * @param <T> - the type of item stored in the list.
 */
public class ChunkyLinkedList<T> implements P6List<T> {
	private int chunkSize;
	private SinglyLinkedList<FixedSizeList<T>> chunks;

	public ChunkyLinkedList(int chunkSize) {
		this.chunkSize = chunkSize;
		// We use chunks before creating it:
		chunks = new SinglyLinkedList<>();
		chunks.addBack(new FixedSizeList<>(chunkSize));
	}

	/**
	 * Delete the item at the front of the list.
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	@Override
	public T removeFront() {
		checkNotEmpty();
		return getIndex(0);
	}

	/**
	 * Delete the item at the back of the list.
	 * 
	 * @return the value of the item that was deleted.
	 * @throws EmptyListError if the list is empty.
	 */
	@Override
	public T removeBack() {
		checkNotEmpty();
		return getIndex(size()-1);
	}

	/**
	 * Delete the item at the specified index in the list.
	 * 
	 * @param index a number from 0 to size (excluding size).
	 * @return the value that was removed.
	 * @throws EmptyListError if the list is empty.
	 * @throws BadIndexError  if the index does not exist.
	 */
	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		return getIndex(index);
	}

	/**
	 * Add an item to the front of this list. The item should be at getIndex(0)
	 * after this call.
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
	 * @param item the data to add to the list.
	 */
	@Override
	public void addBack(T item) {
		if (size()==0){
			addFront(item);
		}else {
			}
	}

	/**
	 * Add an item before ``index`` in this list. Therefore,
	 * {@code addIndex(item, 0)} is the same as {@code addFront(item)}.
	 * 
	 * @param item  the data to add to the list.
	 * @param index the index at which to add the item.
	 */
	@Override
	public void addIndex(T item, int index) {
			
	}
	
	/**
	 * Get the first item in the list.
	 * @return the item.
	 * @throws EmptyListError
	 */
	@Override
	public T getFront() {
		return this.chunks.getFront().getFront();
	}

	/**
	 * Get the last item in the list.
	 * @return the item.
	 * @throws EmptyListError
	 */
	@Override
	public T getBack() {
		return this.chunks.getBack().getBack();
	}


	/**
	 * Find the index-th element of this list.
	 * 
	 * @param index a number from 0 to size, excluding size.
	 * @return the value at index.
	 * @throws BadIndexError if the index does not exist.
	 */
	@Override
	public T getIndex(int index) {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		int start = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			// calculate bounds of this chunk.
			int end = start + chunk.size();
			
			// Check whether the index should be in this chunk:
			if (start <= index && index < end) {
				return chunk.getIndex(index - start);
			}
			
			// update bounds of next chunk.
			start = end;
		}
		throw new BadIndexError();
	}

	/**
	 * Calculate the size of the list.
	 * 
	 * @return the length of the list, or zero if empty.
	 */
	@Override
	public int size() {
		int total = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			total += chunk.size();
		}
		return total;
	}

	/**
	 * This is true if the list is empty. This is usually implemented by looking at
	 * size() but that's a bad idea for linked lists.
	 * 
	 * @return true if the list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return this.chunks.isEmpty();
	}
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
}
