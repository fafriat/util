package com.blogspot.javadesignperformance.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SpecialLinkedListTest {

	static int countRemoved = 0;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void simpleTest() {
		countRemoved = 0;
		final SpecialLinkedList<Integer> list = new SpecialLinkedList<Integer>();
		final int totalCount = 5;

		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0; i<totalCount; i++) {
					list.addToTail(i);
					System.out.println("Added = " + i);
				}
			}
		});
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(countRemoved < totalCount) {
					Integer value = list.removeFromHead();
					System.out.println("Removed = " + value);
					if (value != null) {
						countRemoved++;
					}
				}						
			}
		});
		producer.start();
		consumer.run();
		Assert.assertEquals("A problem has occurred...", totalCount, countRemoved);
	}

	public static void main(String[] args) {
		SpecialLinkedListTest sllt =  new SpecialLinkedListTest();
		sllt.simpleTest();
	}
}
