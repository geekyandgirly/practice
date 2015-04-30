package practice;

public class Bits {
	public void bin(int num) {
		char[] str = new char[32];
		int index = 0;
		while (num != 0) {
			int temp = (num & 1);
			str[index] = String.valueOf(temp).charAt(0);
			num = (num >>> 1);
			index ++;
		}
		System.out.println("index=" + index);

//		for (int i = 0; i <= index; i++) {
	//		System.out.print("0 ");
		//}

		for (int i = index -1; i >= 0; i--) {
			System.out.print(str[i] + " ");
		}
		
		System.out.println();
	//	System.out.println(Integer.toBinaryString(num));
	}
	
	public void mul(int a, int b) {
		System.out.print(a + "x" + b + "=");
		int ret = 0;
    while (a > 0) {
			int temp = (a & 1);
			if (temp == 1) {
				ret += b;
			}
			a = (a >>> 1);
			b = (b << 1);
    }
    System.out.println(ret);
	}
	
	public void div(int n, int d) {
		if (n < d) {
			System.out.print("q=" + 0 + "r=" + n);
		}
		int q = 0;
		int r = n;
		int s = 1;
		
		while (r >= d) {
			if (r < (d << s)) {
				r = (r - (d << (s-1)));
				q += (1 << (s-1));
				s = 0;
			}
			s ++;
		}
		
		System.out.println("q=" + q + "r=" + r);
	}
	
	public static void main(String[] args) {  
		Bits bits = new Bits();
		bits.bin(9);
		bits.bin(-9);
		
		bits.mul(4, 25);
		bits.mul(12, 13);
		bits.div(34, 3);
		bits.div(56, 7);
	}
}
