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

	@Override
	public T removeFront() {
		checkNotEmpty();
		return getIndex(0);
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		return getIndex(size()-1);
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		return getIndex(index);
	}

	@Override
	public void addFront(T item) {
		addIndex(item, 0);	
	}

	@Override
	public void addBack(T item) {
		
	}

	@Override
	public void addIndex(T item, int index) {
			
	}
	
	@Override
	public T getFront() {
		return this.chunks.getFront().getFront();
	}

	@Override
	public T getBack() {
		return this.chunks.getBack().getBack();
	}


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

	@Override
	public int size() {
		int total = 0;
		for (FixedSizeList<T> chunk : this.chunks) {
			total += chunk.size();
		}
		return total;
	}

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
