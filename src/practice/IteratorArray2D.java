package practice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class IteratorArray2D implements Iterator<Integer> {
	private final Iterator<List<Integer>> rowIter;
	private Iterator<Integer> colIter;
	private Integer lastItem = null;
	private Integer lastItemRemoved = null;
	
	public IteratorArray2D(List<List<Integer>> data) {
		rowIter = data.iterator();
		colIter = Collections.emptyIterator();
	}
	
	@Override
	public boolean hasNext() {
		if (colIter.hasNext()) {
			return true;
		}
		
		if (rowIter.hasNext()) {
			colIter = rowIter.next().iterator();
			return hasNext();
		}
		
		return false;
	}

	@Override
	public Integer next() throws NoSuchElementException {
		if (hasNext()) {
			lastItem = colIter.next();
			return lastItem;
		}
		
		throw new NoSuchElementException("No more element");
	}
	
	@Override
	public void remove() {
		System.out.println("before remove: lastItem=" + lastItem + " lastItemRemoved=" + lastItemRemoved);
		colIter.remove();
		if (lastItem != null) {
			lastItemRemoved = lastItem;				
		}
		lastItem = null;

		System.out.println("after remove: lastItem=" + lastItem + " lastItemRemoved=" + lastItemRemoved);
	}
	
	private Integer getLastItemRemoved() {
		return lastItemRemoved;
	}
	
	public static void main(String[] args) {
		List<Integer> row1 = new ArrayList<>(Arrays.asList(new Integer[] {1,2}));
		List<Integer> row2 = new ArrayList<>();
		List<Integer> row3 = new ArrayList<>(Arrays.asList(new Integer[] {3}));
		List<Integer> row4 = new ArrayList<>(Arrays.asList(new Integer[] {4,5}));
		List<Integer> row5 = new ArrayList<>();
		
		List<List<Integer>> data = new ArrayList<>();
		data.add(row1);
		data.add(row2);
		data.add(row3);
		data.add(row4);
		data.add(row5);
		
		IteratorArray2D iter = new IteratorArray2D(data);
		System.out.println("case 1: \nhasNext expect true " + iter.hasNext());
		System.out.println("next expect 1 " + iter.next());
		System.out.println("next expect 2 " + iter.next());
		iter.remove();
		try {
			iter.remove();			
		} catch(Exception e) {
			System.out.println("caught remove after remove" + e.getMessage());
		}
		System.out.println("remove: " + iter.getLastItemRemoved());
		System.out.println("hasNext expect true " + iter.hasNext());
		System.out.println("next expect 3 " + iter.next());
		System.out.println("hasNext expect true " + iter.hasNext());
		try {
			iter.remove();			
		} catch(Exception e) {
			System.out.println("caught remove after hasNext" + e.getMessage());
		}
		System.out.println("next expect 4 " + iter.next());
		System.out.println("next expect 5 " + iter.next());
		System.out.println("hasNext expect false " + iter.hasNext());
		try {
			iter.next();			
		} catch(NoSuchElementException e) {
			System.out.println("caught " + e.getMessage());
		}

		// data is empty
		List<List<Integer>> data1 = new ArrayList<>();
		IteratorArray2D iter1 = new IteratorArray2D(data1);
		System.out.println("\n\ncase 2:\nhasNext expect false " + iter1.hasNext());
		try {
			iter1.next();			
		} catch(NoSuchElementException e) {
			System.out.println("caught " + e.getMessage());
		}
		
		// data is has one empty row
		List<List<Integer>> data2 = new ArrayList<>();
		data2.add(new ArrayList<Integer>());
		IteratorArray2D iter2 = new IteratorArray2D(data2);
		System.out.println("\n\ncase 3:\nhasNext expect false " + iter2.hasNext());
		try {
			iter2.next();			
		} catch(NoSuchElementException e) {
			System.out.println("caught " + e.getMessage());
		}

		// data starts with 2 empty row
		List<List<Integer>> data3 = new ArrayList<>();
		data3.add(new ArrayList<Integer>());
		data3.add(new ArrayList<Integer>());
		data3.add(new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3})));
		IteratorArray2D iter3 = new IteratorArray2D(data3);

		System.out.println("\n\ncase 4:\nhasNext expect true " + iter3.hasNext());
		System.out.println("next expect 1 " + iter3.next());
		System.out.println("next expect 2 " + iter3.next());
		System.out.println("next expect 3 " + iter3.next());
		try {
			iter3.next();			
		} catch(NoSuchElementException e) {
			System.out.println("caught " + e.getMessage());
		}
	}

}
