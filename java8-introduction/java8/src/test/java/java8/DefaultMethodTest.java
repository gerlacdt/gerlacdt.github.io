package java8;

import org.junit.Test;

public class DefaultMethodTest {
	
	@Test
	public void testDefaultMethod() {
		MyInterface defaultIf = (x, y) -> x * y;
		System.out.println("Addition with default method 5 + 7 = " + defaultIf.sum(5, 7));
		System.out.println("Multipication with new implemented method 5 * 7 = " + defaultIf.multiply(5, 7));
		
		int x = MyInterface.square(8);
		
		System.out.println("x squared: " + x);
	}
}
