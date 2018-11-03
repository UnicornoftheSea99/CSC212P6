package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.P6NotImplemented;


public class SinglyLinkedList<T> implements P6List<T>, Iterable<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		T before = start.value;
		start = start.next;
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
			for (Node<T> current = start; current != null; current = current.next) {
				if (current.next.next==null) {
					T groot=current.next.value;
					current.next=null;
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
			Node<T>current=start;
			for (gl=index-1; gl>0; gl--) {
				if(current.next!=null) {
					current=current.next;
				}
				else {
					current.next=null;
				}
			}
			current.next=current.next.next;
		}
		return removed;
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		if (size()==0){
			addFront(item);
		}else {
			Node<T> current=start;
			for (current = start; current.next != null; current = current.next) {	
			}
		current.next = new Node<T>(item,null);
	}
	}
	@Override
	public void addIndex(T item, int index) {
		if(index==0){
			addFront(item);
		}else {
			int gi=0;
			for (Node<T> current = start; current != null; current = current.next) {
				if (gi==index-1) {
					Node<T> boo = new Node<T>(item, current.next);
					current.next=boo;
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
		checkNotEmpty();
		if (size()==1) {
			T joe=start.value;
			return joe;
		}
		else {
			for (Node<T> current = start; current != null; current = current.next) {
				if (current.next.next==null) {
					T groot=current.next.value;
					return groot;
				}
			}
		}
		return null;
	}

	@Override
	public T getIndex(int index) {
		int at = 0;
		for (Node<T> current = start; current != null; current = current.next) {
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
		for (Node<T> n = this.start; n != null; n = n.next) {
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

	/**
	 * Helper method to throw the right error for an empty state.
	 */
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * I'm providing this class so that SinglyLinkedList can be used in a for loop
	 * for {@linkplain ChunkyLinkedList}. This Iterator type is what java uses for
	 * {@code for (T x : list) { }} lops.
	 * 
	 * @author jfoley
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(SinglyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.next;
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}
}
