package java8;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class StreamsTest {

	private static Logger logger = Logger.getLogger(StreamsTest.class);
	
	private Person p1 = new Person(30, "The Mountain", "Celgane");
	private Person p2 = new Person(41, "The Viper", "Martell");
	private Person p3 = new Person(12, "Arya", "Stark");
	private Person p4 = new Person(15, "Joffrey", "Baratheon");
	private Person p5 = new Person(16, "Daenerys", "Targaryen");
	private Person p6 = new Person(10, "Sansa", "Stark");
	private Person p7 = new Person(38, "Tyrion", "Lannister");
	private Person p8 = new Person(40, "Cersei", "Lannister");
	private Person p9 = new Person(40, "Jamie", "Lannister");

	private List<Integer> numbers;
	private List<Person> persons;
	
	@Before
	public void setUp() {
		numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		persons = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);
	}
	
	public static boolean isEven(int x) {
		return x % 2 == 0;
	}

	@Test
	public void findSquares() {
		List<Integer> squares = 
				numbers.stream()
						.map(i -> i * i)  // intermediate operation
						.collect(Collectors.toList());  // terminal operation (collect)
		
		logger.debug("square of all numbers: ");
		squares.stream().forEach(System.out::println);
	}

	@Test
	public void findSquaresForEvenNumbers() {
		List<Integer> evenSquares = 
				numbers.stream()
					   .filter(p -> isEven(p))
					   .map(i -> i * i)
					   .collect(Collectors.toList());
		
		logger.debug("square of even numbers: ");
		evenSquares.stream().forEach(logger::debug);
	}
	
	@Test
	public void findSumOfOddSquares() {
		Predicate<Integer> isOdd = i -> i % 2 == 1;
		Function<Integer, Integer> square = i -> i * i;
		Integer sumOfOddSquares = 
					numbers.stream().map(square::apply)
									.filter(isOdd::test)
									.reduce(0, (x, y) -> x + y);  // terminal function (reducer)
		
		logger.debug("sumOfOddSquares: " + sumOfOddSquares);
	}

	
	// functional programming (combine and pass functions)
	@Test
	public void findOddSquaresMoreFunctional() {
		Predicate<Integer> isOdd= i -> i % 2 == 1;
		
		Integer sumOfOddSquares = mapFilterAndReduce(numbers, i -> i * i, isOdd, (x, y) -> x + y, 0);
		
		System.out.println("sumOfOddSquares: " + sumOfOddSquares);
	}
	
	public <T> List<T> filterElements(List<T> elements, Predicate<T> checker) {
		return elements.stream().filter(checker::test).collect(Collectors.toList());
	}
	
	public <T, R> List<R> mapFilterAndCollectElements(List<T> elements, Function<T, R> function, Predicate<R> checker) {
		List<R> results = elements.stream()
					   			  .map(function::apply)
					   			  .filter(checker::test)
					   			  .collect(Collectors.toList());
		
		return results;
	}
	
	public <T, R> R mapFilterAndReduce(List<T> elements, Function<T, R> function, Predicate<R> checker, BinaryOperator<R> accumulator, R initial) {
		R result = elements.stream()
						   .map(function::apply)
						   .filter(checker::test)
						   .reduce(initial, accumulator);
		
		return result;
	}

	@Test
	public void testGroupByLastname() {
		Map<String, List<Person>> groupByMap = persons.stream().collect(Collectors.groupingBy(Person::getLastname));
		
		// print map
		for (Entry<String, List<Person>> entry : groupByMap.entrySet()) {
			System.out.print(entry.getKey() + " ==> ");
			for (Person person : entry.getValue()) {
				System.out.print(person.getFirstname() + " " + person.getLastname() + "; ");
			}
			System.out.println("");
		}
	}
	
	@Test
	public void testMatch() {
		boolean containsLannister = persons.stream().anyMatch(person -> person.getLastname().equals("Lannister"));
		System.out.println("containsLannister: "+ containsLannister);
	}
	
	// return Function with Closure
	public Function<Integer, Integer> adder(Integer i) {
		// i is a free variable, captured in a closure
		return x -> x + i;
	}
	
	@Test
	public void testAdder() {
		Function<Integer, Integer> adder5 = adder(5);
		
		Integer result = adder5.apply(10);
		
		System.out.println("result from adder5(10) == " + result);
		assertTrue(result == 15);
	}
	
	@Test
	public void testFilterPerson() {
		persons.stream().filter(p -> p.getAge() > 20).forEach(System.out::println);
		
		persons.stream().mapToInt(Person::getAge).sorted().forEach(System.out::println);;
		
		persons.stream().sorted((p, other) -> p.getAge().compareTo(other.getAge()))
						.forEach(p -> System.out.println(p.getFirstname() + " " + p.getLastname() + " is " + p.getAge()));
	}
	
	@Test
	public void testFunctionComposition() {
		Function<Integer, Integer> doubler = x -> x * 2;
		Function<Integer, Integer> tripleDouble = doubler.compose(x -> x * 3);
		numbers.stream().map(tripleDouble).forEach(System.out::println);
		
		System.out.println("Function combinatation with andThen()");
		numbers.stream().map(doubler.andThen(x -> x * 3)).forEach(System.out::println);
	}
	
	@Test
	public void testOptional() {
		Optional<Integer> result = calculate(5);
		result.ifPresent(System.out::println);
	}
	
	public Optional<Integer> calculate(Integer x) {
		Optional<Integer> result = Optional.of(x);
		return result;
	}
}
