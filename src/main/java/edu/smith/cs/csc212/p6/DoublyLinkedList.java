package edu.smith.cs.csc212.p6;



import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;



public class DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.after;
		return before;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (size()==1) {
			T casper=start.value;
			start=null;
			return casper;
		}
		else {
			for (Node<T> current = start; current != null; current = current.after) {
				if (current.after.after==null) {
					T groot=current.after.value;
					current.after=null;
					return groot;
				}
			}
		}
		return null;
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		T removed = getIndex(index);
		if (size()==1) {
			T lonely=start.value;
			start=null;
			return lonely;
		}else {
			int gl=0;
			for (Node<T> current = start; index==gl-1; current = current.after) {
				gl++;
				if(index==gl-1) {
					current.after=null;
					current.after=current.after.after;
					return removed;
				}
			}
		}
		return removed;
	}

	@Override
	public void addFront(T item) {
		Node<T> first = new Node<T>(item);
		Node<T> second = start;
		if (second==null) {
			end=first;
		}else {
		first.after = second;
		first.before = null;
		second.before = first;
		start = first;
		}
	}

	@Override
	public void addBack(T item) {
		Node<T> last = new Node<T>(item);
		Node<T> secondLast = end;
		if(size()==0) {
			addFront(item);
		}else {
			last.before=secondLast;
			last.after=null;
			secondLast.after=last;
			end=last;
		}
	}

	@Override
	public void addIndex(T item, int index) {
		if(index==0){
			addFront(item);
		}else {
			int gi=0;
			for (Node<T> current = start; current != null; current = current.after) {
				if (gi==index-1) {
					Node<T> boo = new Node<T>(item);
					current.after=boo;
				}
				 gi++;
			}
		}	
	}

	@Override
	public T getFront() {
		return start.value;
	}

	@Override
	public T getBack() {
		return end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> current = start; current != null; current = current.after) {
			if (at == index) {
				return current.value;
			}
			at++;
	}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		if (this.size() ==0)
			return true;
		else {
		return false;
	}
	}
	
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
