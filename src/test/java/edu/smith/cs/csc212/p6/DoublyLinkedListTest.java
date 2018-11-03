package edu.smith.cs.csc212.p6;

import org.junit.Assert;
import org.junit.Test;

import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class DoublyLinkedListTest {
	@Test
	public void testEmpty() {
		P6List<String> data = new DoublyLinkedList<String>();
		Assert.assertEquals(0, data.size());
		data = new DoublyLinkedList<String>();
		Assert.assertEquals(0, data.size());
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveFrontCrash() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.removeFront();
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveBackCrash() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.removeBack();
	}
	
	@Test(expected=EmptyListError.class)
	public void testRemoveIndexCrash() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.removeIndex(3);
	}

	@Test
	public void testAddToFront() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.addFront("1");
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("1", data.getIndex(0));
		data.addFront("0");
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("0", data.getIndex(0));
		Assert.assertEquals("1", data.getIndex(1));
		data.addFront("-1");
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("-1", data.getIndex(0));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(2));
		data.addFront("-2");
		Assert.assertEquals("-2", data.getIndex(0));
		Assert.assertEquals("-1", data.getIndex(1));
		Assert.assertEquals("0", data.getIndex(2));
		Assert.assertEquals("1", data.getIndex(3));
	}
	
	@Test
	public void testAddToBack() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.addBack("1");
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("0");
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-1");
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
		data.addBack("-2");
		Assert.assertEquals("-2", data.getIndex(3));
		Assert.assertEquals("-1", data.getIndex(2));
		Assert.assertEquals("0", data.getIndex(1));
		Assert.assertEquals("1", data.getIndex(0));
	}
	
	/**
	 * Helper method to make a full list.
	 * @return
	 */
	public P6List<String> makeFullList() {
		P6List<String> data = new DoublyLinkedList<String>();
		data.addFront("a");
		data.addFront("b");
		data.addFront("c");
		data.addFront("d");
		return data;
	}
	
	
	@Test
	public void testRemoveFront() {
		P6List<String> data = makeFullList();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("d", data.removeFront());
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("c", data.removeFront());
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("b", data.removeFront());
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("a", data.removeFront());
		Assert.assertEquals(0, data.size());
	}
	@Test
	public void testRemoveBack() {
		P6List<String> data = makeFullList();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("a", data.removeBack());
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("b", data.removeBack());
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("c", data.removeBack());
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("d", data.removeBack());
		Assert.assertEquals(0, data.size());
	}
	
	@Test
	public void testRemoveIndex() {
		P6List<String> data = makeFullList();
		Assert.assertEquals(4, data.size());
		Assert.assertEquals("b", data.removeIndex(2));
		Assert.assertEquals(3, data.size());
		Assert.assertEquals("a", data.removeIndex(2));
		Assert.assertEquals(2, data.size());
		Assert.assertEquals("c", data.removeIndex(1));
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("d", data.removeIndex(0));
		Assert.assertEquals(0, data.size());
	}
}
