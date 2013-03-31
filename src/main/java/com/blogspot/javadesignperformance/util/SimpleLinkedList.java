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

public class SimpleLinkedList<T> {

	protected final SingleLinkedNode<T> head;
	protected SingleLinkedNode<T> tail;
	
	public SimpleLinkedList() {
		this.head = new SingleLinkedNode<T>();
		this.tail = head;
	}
	
	public void addToTail(T value) {
		SingleLinkedNode<T> newNode = new SingleLinkedNode<T>(value);
		tail.setNext(newNode);
		tail = newNode;
	}

	public T removeFromHead() {
		SingleLinkedNode<T> nodeToRemove = head.getNext();

		//Case empty
		if (nodeToRemove == null) {
			return null;
		}
		
		//At least one node
		T value = nodeToRemove.getValue();
		nodeToRemove.setValue(null);

		//real removing
		head.setNext(nodeToRemove.getNext());

		return value;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("H->");
		SingleLinkedNode<T> cur = head;
		while((cur = cur.getNext()) != null) {
			buf.append(cur.getValue());
			buf.append("->");
		}
		buf.append("null [T->");
		buf.append((head == tail)?"H":tail);
		buf.append("]");
		return buf.toString();
	}
	
	
}
