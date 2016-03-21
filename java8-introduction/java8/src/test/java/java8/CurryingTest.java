package java8;

import java.util.function.Function;

import org.junit.Test;

public class CurryingTest {

	@Test
	public <R, U, T> void curryTest() {
		MyBiFunction<Integer, Integer, Integer> multiplier = (x, y) -> x * y;
		Function<Integer, Integer> times100 = multiplier.curry(100);
		Integer result = times100.apply(3);
		System.out.println("times100 ==> " + result);
	}
	
	@Test
	public void curryDirectlyTest() {
		Function<Integer, Function<Integer, Integer>> curryMultiplier = a -> b -> a * b;
		Function<Integer, Integer> times100 = curryMultiplier.apply(100);
		Integer result = times100.apply(3);
		System.out.println("times100 ==> " + result);
	}
	
	@Test
	public void composeTest() {
		Function<Integer, Integer> square = x -> x * x;
		Function<Integer, Integer> myDouble = x -> x + x;
		Function<Integer, Integer> inc = x -> x + 1;
		
		// (2x)^2
		Function<Integer, Integer> doubleSquare= square.compose(myDouble);
		
		// (2(x+1))^2
		Function<Integer, Integer> incDoubleSquare= doubleSquare.compose(inc);
		
		// (2 * (2 + 1))^2 = 6^2 = 36
		Integer result = incDoubleSquare.apply(2);
		
		System.out.println("result == " + result);
	}
}
