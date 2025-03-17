package queueImplementation;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Arraylist<T> implements Iterable<T>{
	T[] arr;
	int indexvalue;
	
	@SuppressWarnings("unchecked")
	public Arraylist(int i) {
		arr = (T[]) new Object[i];
	}
	
	@SuppressWarnings("unchecked")
	public Arraylist() {
		arr = (T[]) new Object[10];
	}
	
	public int size() {
		int count = 0;
		for (@SuppressWarnings("unused") T val : arr) {
			count++;
		}
		return count;
	}
	
	public boolean add(T value) {
		boolean output = false;
//		if (arr.length > indexvalue) {
//			arr[indexvalue] = value;
//			indexvalue++;
//		}
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == null) {
				arr[i] = value;
				output = true;
				break;
			}
		}
		if (!output) {
			resize();
			for (int i=0; i<arr.length; i++) {
				if (arr[i] == null) {
					arr[i] = value;
					output = true;
					break;
				}
			}
		}
		return output;
	}
	
	public void resize() {
		arr = Arrays.copyOf(arr,(int) arr.length*3/2);
	}
	
	public T get(int ind) {
		if (this.arr.length < ind) {
			System.out.println("array out of bound error");
		}
		return (T) this.arr[ind];
	}
	
	public void clear() {
		arr = Arrays.copyOf(arr, 0);
	}
	
	public int indexOf(T obj) {
		int index = -1;
		for (int i=0; i<arr.length; i++) {
			if (this.arr[i] == obj) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean remove(int ind) {
		if (this.arr.length < 1) {
			return false;
		}
		System.arraycopy(this.arr, 0, this.arr, ind, this.arr.length-1);
		return true;
	}
	
	public boolean remove(T obj) {
		if (this.arr.length < 1) {
			return false;
		}
		int ind = -1;
		for (int i=0; i<this.arr.length; i++) {
			if (this.arr[i] == obj) {
				ind = i;
				break;
			}
		}
		if (ind != -1) {
			System.arraycopy(this.arr, 0, this.arr, ind, this.arr.length-1);
			return true;
		}
		else {
			return false;
		}
	}


	public boolean contains(T t) {
		boolean output = false;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == t) {
				output = true;
				break;
			}
		}
		return output;
	}

	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	class MyIterator implements Iterator<T> {
		private int index;
		
		@Override
		public boolean hasNext() {
			return index < arr.length;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return arr[index++];
		}
	}

	
	public void set(int ind, T t) {
		arr[ind] = t;
	}


	


}