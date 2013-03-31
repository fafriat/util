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

public class SpecialLinkedList<T> {

	protected final SingleLinkedNode<T> head;
	protected SingleLinkedNode<T> tail;
	protected boolean firstNodeMarkedAsRemoved;
	
	public SpecialLinkedList() {
		this.head = new SingleLinkedNode<T>();
		this.tail = head;
		this.firstNodeMarkedAsRemoved = false;
	}
	
	public void addToTail(T value) {
		SingleLinkedNode<T> newNode = new SingleLinkedNode<T>(value);
		tail.setNext(newNode);
		tail = newNode;
	}

	public boolean isEmpty() {
		//Next not removed
		SingleLinkedNode<T> nodeToRemove = head.getNext();
		if (firstNodeMarkedAsRemoved) {
			nodeToRemove = nodeToRemove.getNext();
		}		
		return  (nodeToRemove == null);
	}

	public T remove() {
		T value = null;
		SingleLinkedNode<T> nodeToRemove = head.next;
		if (firstNodeMarkedAsRemoved) {
			if ((nodeToRemove = nodeToRemove.next) == null) {
				firstNodeMarkedAsRemoved = true;
				return null;
			}
			head.next = nodeToRemove;
			firstNodeMarkedAsRemoved = false;
			value = nodeToRemove.value;
			nodeToRemove.value = null;
			head.next = nodeToRemove.next;
		} else {
			if (nodeToRemove == null)
				return null;
			value = nodeToRemove.value;
			nodeToRemove.value = null;
			if (nodeToRemove.next == null)
				firstNodeMarkedAsRemoved = true;
			else
				head.next = nodeToRemove.next;
		}
		return value;
	}

	public T removeFromHead() {
		//Next not removed
		SingleLinkedNode<T> nodeToRemove = head.getNext();
		if (firstNodeMarkedAsRemoved) {
			nodeToRemove = nodeToRemove.getNext();
		}
		
		//Case empty
		if (nodeToRemove == null) {
			return null;
		}		
		//At least one node
		//Removing effectively the first node is possible
		if (firstNodeMarkedAsRemoved) {
			//real remove of previous node
			head.setNext(nodeToRemove);
			firstNodeMarkedAsRemoved = false;
		}

		T value = nodeToRemove.getValue();
		nodeToRemove.setValue(null);

		//Case one last node
		if (nodeToRemove.getNext() == null) {
			firstNodeMarkedAsRemoved = true;
		}
		else {
			//real removing
			head.setNext(nodeToRemove.getNext());
		}

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
