package Linkedlist;

import static org.junit.Assert.*;
import org.junit.Test;

public class CLLTest {

	@Test
	public void testConstructor() {
	CLL<Integer> b= new CLL<Integer>();
	assertEquals("[]", b.toString());
	assertEquals("[]", b.toStringRev());
	assertEquals(0, b.size());
	}
	
	
	@Test
	public void testAppend() {
	CLL<Integer> c= new CLL<Integer>();
	c.append(5);
	assertEquals("[5]", c.toString());
	assertEquals("[5]", c.toStringRev());
	assertEquals(1, c.size());
	
	c.append(6);
	assertEquals("[5, 6]", c.toString());
	assertEquals("[6, 5]", c.toStringRev());
	assertEquals(2, c.size());
	
	c.append(7);
	assertEquals("[5, 6, 7]", c.toString());
	assertEquals("[7, 6, 5]", c.toStringRev());
	assertEquals(3, c.size());
	
	c.append(null);
	assertEquals("[5, 6, 7, null]", c.toString());
	assertEquals("[null, 7, 6, 5]", c.toStringRev());
	assertEquals(4, c.size());
	}
	
	
	@Test
	public void testPrepend() {
	CLL<Integer> c= new CLL<Integer>();
	c.prepend(5);
	assertEquals("[5]", c.toString());
	assertEquals("[5]", c.toStringRev());
	assertEquals(1, c.size());
	
	c.prepend(6);
	assertEquals("[6, 5]", c.toString());
	assertEquals("[5, 6]", c.toStringRev());
	assertEquals(2, c.size());
	
	c.prepend(7);
	assertEquals("[7, 6, 5]", c.toString());
	assertEquals("[5, 6, 7]", c.toStringRev());
	assertEquals(3, c.size());
	
	c.prepend(null);
	assertEquals("[null, 7, 6, 5]", c.toString());
	assertEquals("[5, 6, 7, null]", c.toStringRev());
	assertEquals(4, c.size());
	}
	
	
	@Test
	public void testGetNode(){
	CLL<Integer> c= new CLL<Integer>();
	c.append(5);
	c.append(6);
	c.append(7);
	c.append(8);
	c.append(null);
	assertEquals(5,(int)c.getNode(0).value);
	assertEquals(6,(int)c.getNode(1).value);
	assertEquals(8,(int)c.getNode(3).value);
	assertEquals(null,c.getNode(4).value);
	
	 Exception s1 = null;
	    try{
	    	c.getNode(-1);
	    } catch (Exception e){
	    	s1 = e;
	    }
	    assertTrue(s1 instanceof IndexOutOfBoundsException);
	    
	    Exception s2 = null;
	    try{
	    	c.getNode(20);
	    } catch (Exception e){
	    	s2 = e;
	    }
	    assertTrue(s2 instanceof IndexOutOfBoundsException);
	    
	    Exception s3 = null;
	    try{
	    	c.getNode(5);
	    } catch (Exception e){
	    	s3 = e;
	    }
	    assertTrue(s3 instanceof IndexOutOfBoundsException);
	}
	
	
	@Test
	public void testInsertAfter(){
		CLL<Integer> c= new CLL<Integer>();
		c.append(5);
		c.append(6);
		c.append(7);
		
	    c.insertAfter(8, c.getNode(0));
	    assertEquals(5,(int)c.getFirst().value);//check header
	    assertEquals("[5, 8, 6, 7]",c.toString());
	    assertEquals("[7, 6, 8, 5]",c.toStringRev());
	    assertEquals(4,c.size);
	    
	    c.insertAfter(null, c.getNode(3));
	    assertEquals(5,(int)c.getFirst().value);//check header
	    assertEquals("[5, 8, 6, 7, null]",c.toString());
	    assertEquals("[null, 7, 6, 8, 5]",c.toStringRev());
	    assertEquals(5,c.size);
		
	    c.insertAfter(10, c.getNode(2));
	    assertEquals(5,(int)c.getFirst().value);//check header
	    assertEquals("[5, 8, 6, 10, 7, null]",c.toString());
	    assertEquals("[null, 7, 10, 6, 8, 5]",c.toStringRev());
	    assertEquals(6,c.size);
		
	}
	
	@Test
	public void testRemove(){
		CLL<Integer> c= new CLL<Integer>();
		c.append(5);
		c.append(6);
		c.append(7);
		c.append(null);
		
		c.remove(c.getNode(0));//n==head && size>=2
		assertEquals("[6, 7, null]",c.toString());
	    assertEquals("[null, 7, 6]",c.toStringRev());
	    assertEquals(3,c.size);
	    
	    c.remove(c.getNode(1));
		assertEquals("[6, null]",c.toString());
	    assertEquals("[null, 6]",c.toStringRev());
	    assertEquals(2,c.size);
	    
	    c.remove(c.getNode(1));
		assertEquals("[6]",c.toString());
	    assertEquals("[6]",c.toStringRev());
	    assertEquals(1,c.size);
	    
	    c.remove(c.getNode(0));//size=1
		assertEquals("[]",c.toString());
	    assertEquals("[]",c.toStringRev());
	    assertEquals(0,c.size);
	}
	

}
