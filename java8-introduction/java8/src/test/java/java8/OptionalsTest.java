package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class OptionalsTest {
	
	private static Logger logger = Logger.getLogger(OptionalsTest.class);

	List<Integer> numbers;
	
	@Before
	public void setUp() {
		numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	}

	@Test
	public void findSumOfOddSquare() {
		Predicate<Integer> isEven = i -> i % 2 == 0;
		Optional<Integer> sumOfOddSquares = 
					numbers.stream().filter(isEven::test)
									.map(i -> i * i)
									.reduce((x, y) -> x + y);
		
		// avoid null pointer exceptions (already exists in Google's Guava library)
		if (sumOfOddSquares.isPresent()) {
			sumOfOddSquares.ifPresent(logger::debug);
		}
	}
}
