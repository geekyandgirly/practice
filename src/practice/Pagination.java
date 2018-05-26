package practice;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Pagination {
	
	private static class Listing {
		final String hostId;
		final float score;
		final String val;
		
		Listing(String listing) {
			hostId = getHostId(listing);
			score = getScore(listing);
			val = listing;
		}
	}
	
	private static class ListingComparator implements Comparator<Listing> {

		@Override
		public int compare(Listing o1, Listing o2) {
			// Can x10 then compare if accuracy is critical, or use Float.compare. I don't think in this use case it's neccessary.
			if (o1.score < o2.score) {
				return 1;
			} else if (o1.score > o2.score) {
				return -1;
			} else {
				return 0;
			}			
		}
	}
	
	public static List<String> displayPages(List<String> input, int pageSize) {
		List<String> ret = new ArrayList<>(input.size() + input.size() / pageSize);
		Iterator<String> iter = input.iterator();
		HashSet<String> perPageSet = new HashSet<>(pageSize);
		
		HashMap<String, LinkedList<Listing>> dupMap = new HashMap<>();
		
		int count = 0;
		// Skip first line. TODO(luping): parse first line and map the fields. Currently fields are hard coded.
		iter.next();
		
		while (iter.hasNext()) {
			if (count == pageSize) {
				ret.add("");
				perPageSet.clear();
				count = 0;
			}
			
			Listing listing = new Listing(iter.next());
			String hostId = listing.hostId;
			if (perPageSet.contains(listing.hostId)) {
				// hostId already exist on current page, remove and store into duplicate maps
				iter.remove();
				LinkedList<Listing> dups = dupMap.get(hostId);
				if (dups == null) {
					dups = new LinkedList<>();
				}
				dups.add(listing);
			} else {
				ret.add(listing.val);
				perPageSet.add(hostId);
				count++;
			}
		}
		
		PriorityQueue<Listing> queue = new PriorityQueue<>(new ListingComparator());
		while (!dupMap.isEmpty()) {
			if (count == pageSize) {
				ret.add("");
				perPageSet.clear();
				count = 0;
				queue.clear();
			}

			Listing nextListing = null;
			if (queue.isEmpty()) {
				fillPriorityQueue(perPageSet, dupMap, queue);
			} 
			
			nextListing = queue.poll();
			ret.add(nextListing.val);
			perPageSet.add(nextListing.val);
			count ++;
		}
		
		return ret;
	}
	
	private static void fillPriorityQueue(HashSet<String> set, HashMap<String, LinkedList<Listing>> map, PriorityQueue<Listing> queue) {
		Set<String> keys = map.keySet();
		
		// first check for non-dup keys.
		keys.removeAll(set);
		if (keys.isEmpty()) {
			// no more non-dups, have to use duplicate
			keys = map.keySet();			
		}
				
		// can use a priority queue?
		for (String key : keys) {
			Listing firstListing = map.get(key).getFirst();
			queue.add(firstListing);
		}
		
		Listing minListing = queue.peek();
		if (minListing != null) {
			LinkedList<Listing> listings = map.get(minListing.hostId);
			if (listings != null && !listings.isEmpty()) {
				listings.removeFirst();
				if (listings.isEmpty()) {
					map.remove(minListing.hostId);
				}
			}
		} 
	}
	
	private static String getHostId(String listing) {
		return listing.split(",")[0];
	}
	
	private static float getScore(String listing) {
		return Float.valueOf(listing.split(",")[2]);
	}

	public static void main(String [] args) {
	    int PER_PAGE = 12;

	    ArrayList<String> input = new ArrayList<String>();
	    input.add("host_id,listing_id,score,city");
	    input.add("1,28,300.1,San Francisco");
	    input.add("4,5,209.1,San Francisco");
	    input.add("20,7,208.1,San Francisco");
	    input.add("23,8,207.1,San Francisco");
	    input.add("16,10,206.1,Oakland");
	    input.add("1,16,205.1,San Francisco");
	    input.add("6,29,204.1,San Francisco");
	    input.add("7,20,203.1,San Francisco");
	    input.add("8,21,202.1,San Francisco");
	    input.add("2,18,201.1,San Francisco");
	    input.add("2,30,200.1,San Francisco");
	    input.add("15,27,109.1,Oakland");
	    input.add("10,13,108.1,Oakland");
	    input.add("11,26,107.1,Oakland");
	    input.add("12,9,106.1,Oakland");
	    input.add("13,1,105.1,Oakland");
	    input.add("22,17,104.1,Oakland");
	    input.add("1,2,103.1,Oakland");
	    input.add("28,24,102.1,Oakland");
	    input.add("18,14,11.1,San Jose");
	    input.add("6,25,10.1,Oakland");
	    input.add("19,15,9.1,San Jose");
	    input.add("3,19,8.1,San Jose");
	    input.add("3,11,7.1,Oakland");
	    input.add("27,12,6.1,Oakland");
	    input.add("1,3,5.1,Oakland");
	    input.add("25,4,4.1,San Jose");
	    input.add("5,6,3.1,San Jose");
	    input.add("29,22,2.1,San Jose");
	    input.add("30,23,1.1,San Jose");
	    
	    List<String> result = displayPages(input, PER_PAGE);
	    for (String r : result) {
	    	System.out.println(r);
	    }
	}
}
