package practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Intervals {

	private static class Shift {
		int start;
		int end;
		
		Shift(int s, int e) {
			start = s;
			end = e;
		}
		
		int getDuration() {
			return (end - start);
		}
	}
	
	public static boolean canDrive(List<Shift> shifts, int minSleep, int maxWork, int now) {
		boolean slept8Hours = false;
		int work = 0;
		int lastShiftEnd = -1;
		
		Iterator<Shift> iter = shifts.iterator();
		
		while (iter.hasNext()) {
			Shift shift = iter.next();
			if (shift.start > now) {
				break;
			}
			
			if (lastShiftEnd > 0) {
				if (!slept8Hours) {
  				  slept8Hours = ((shift.start - lastShiftEnd) >= minSleep);
				}
				
				if (slept8Hours) {
					work = 0;
				} 
			}
			work += (now < shift.end) ? (now - shift.start) : shift.getDuration();
			lastShiftEnd = shift.end;
			System.out.println("work " + work + " hours, hasSlept8Hours=" + slept8Hours);
		}

		System.out.println();
		System.out.println("slept8Hours=" + slept8Hours + " work=" + work);
		return (work < maxWork);
	}
	
	public static void main(String[] args) {
		List<Shift> shifts = new ArrayList();
		shifts.add(new Shift(0, 5));
		shifts.add(new Shift(12, 16));
		shifts.add(new Shift(18, 21));
		
		System.out.println("can drive: " + Intervals.canDrive(shifts, 8, 12, 17));
	}
}
