package com.blogspot.javadesignperformance.util;



public class SpecialLinkedListBenchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final SpecialLinkedList<Integer> list = new SpecialLinkedList<Integer>();
		final int totalCount = 10000000;
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0; i<totalCount; i++) {
					list.addToTail(i);
				}
			}
		});
		final long startTime = System.currentTimeMillis();
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int countRemoved = 0;
				while(countRemoved < totalCount) {
					Integer value = list.removeFromHead();
					if (value != null) {
						countRemoved++;
					}
				}
				if (countRemoved != totalCount) {
					throw new RuntimeException("A problem has occurred... expecting [" + totalCount + "] but effective [" + countRemoved + "]");
				}
				long endTime = System.currentTimeMillis();
				System.out.println("Result = " + (endTime-startTime) + " ms");
			}
		});
		consumer.start();
		producer.start();
	}

}
