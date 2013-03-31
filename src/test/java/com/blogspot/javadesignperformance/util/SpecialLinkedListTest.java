/*
   Copyright 2012,2013 Frank AFRIAT

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.  
 */

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
