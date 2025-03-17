package queueImplementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {
	Arraylist<T> Queue;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue() {
		Queue = (Arraylist<T>) new Arraylist<Object>();
	}
	
	public int size() {
		int count = 0; 
		for (@SuppressWarnings("unused") Object val : Queue) {
			 count++;
		 }
		return count;
	}
	
	public T peek(){
		return this.Queue.size() < 1 ? null : (T) this.Queue.get(0);
	}
	
	public T poll() {
		if (this.Queue.size() < 1) {
			return null;
		}
		else {
			this.Queue.remove(0);
			for(int i=0;i<Queue.size();i++){
				if (!(Queue.get(i) == null)) {
					if (!(Queue.get(0) == null)) {
						int parentIndex=(i-1)/2;
						if(Queue.get(parentIndex).compareTo(Queue.get(i))>0){
							T temp=Queue.get(parentIndex);
							Queue.set(parentIndex,Queue.get(i));
							Queue.set(i,temp);
						}
					}
				}
			}
			return this.Queue.get(0);
		}
	}
	
	public boolean remove(T value) {
		boolean output = false;
		if (this.Queue.size() < 1) {
			return false;
		}
		for (int i=0; i<this.Queue.size(); i++) {
			if (this.Queue.get(i) == value) {
				this.Queue.remove(i);
				output = true;
				break;
			}
		}
		return output;
	}
	
	public boolean delete(int index) {
		boolean output = false;
		System.arraycopy(Queue, 0, Queue, index+1, Queue.size());
		return output;
	}
	
	public boolean offer(T value) {
		if (this.Queue.size() < 1) {
			return false;
		}
		this.Queue.add(value);
		return true;
	}
	
	public boolean add(T value) {
		if (this.Queue.size() < 1) {
			throw new ArrayIndexOutOfBoundsException("elements not found");
		}
		this.Queue.add(value);
		for(int i=0;i<Queue.size();i++){
			if (!(Queue.get(i) == null)) {
				if (!(Queue.get(0) == null)) {
					int parentIndex=(i-1)/2;
					if(Queue.get(parentIndex).compareTo(Queue.get(i))>0){
						T temp=Queue.get(parentIndex);
						Queue.set(parentIndex,Queue.get(i));
						Queue.set(i,temp);
					}
				}
			}
		}
		return true;
	}
	
	public boolean contains(T value) {
		boolean output = false;
		for (int i=0; i<Queue.size(); i++) {
			if (Queue.get(i) == value) {
				output = true;
				break;
			}
		}
		return output;
	}
	
	public void clear() {
		Queue.clear();
	}
	
	public T get(int ind) {
		return Queue.get(ind);
	}

	public T removeLast() {
		if (this.Queue.size() < 1) {
			return null;
		}
		else {
			T val = Queue.get(Queue.size()-1);
			this.Queue.remove(Queue.size()-1);
			return val;
		}
	}

	public boolean addAll(T[] arr) {
		boolean output = false;
		if (arr.length == 0) {
			return output;
		}
		for (int i=0; i<arr.length; i++) {
			this.Queue.add(arr[i]);
		}
		output = true;
		return output;
	}

	public boolean addAll(Arraylist<T> arr) {
		boolean output = false;
		for (int i=0; i<arr.size(); i++) {
			this.Queue.add(arr.get(i));
		}
		output = true;
		return output;
	}
	
	public boolean containsAll(T[] arr) {
		boolean output = true;
		for (int i=0; i<arr.length; i++) {
			if (!this.Queue.contains(arr[i])) {
				output = false;
				break;
			}
		}
		return output;
	}

	public boolean containsAll(Arraylist<T> arr) {
		boolean output = true;
		for (int i=0; i<arr.size(); i++) {
			if (!this.Queue.contains(arr.get(i))) {
				output = false;
				break;
			}
		}
		return output;
	}

	public int indexOf(T data) {
		int index = -1;
		for (int i=0; i<Queue.size(); i++) {
			if (Queue.get(i) == data) {
				index = 0;
			}
		}
		return index;
	}

	public int lastIndexOf(T data) {
		int index = -1;
		for (int i=Queue.size(); i>0; i--) {
			if (Queue.get(i) == data) {
				index = 0;
			}
		}
		return index;
	}

	public int indexOf(T data, int starting_index)  {
		int index = 0;
		if (Queue.size() < 1 || starting_index > Queue.size() ) {
			throw new ArrayIndexOutOfBoundsException("input error");
		}
		for (int i=starting_index; i<Queue.size(); i++) {
			if (Queue.get(i) == data) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public int indexOf(T data, int starting_index, int last_index){
		int index = 0;
		if (Queue.size() < 1 || starting_index > Queue.size() || last_index > Queue.size() ) {
			throw new ArrayIndexOutOfBoundsException("input error");
		}
		for (int i=starting_index; i<last_index; i++) {
			if (Queue.get(i) == data) {
				index = i;
				break;
			}
		}
		return index;
	}

	class MyIterator implements Iterator<T> {
		private int index;
		
		@Override
		public boolean hasNext() {
			return index < Queue.size();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("element not found");
			}
			return Queue.get(index++);
		}
	}

	public Iterator<T> iterator() {
		return new MyIterator();
	}

}
