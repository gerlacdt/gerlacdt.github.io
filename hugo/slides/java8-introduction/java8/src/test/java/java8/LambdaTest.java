package java8;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class LambdaTest {
	
	private static Logger logger = Logger.getLogger(LambdaTest.class);
	
	List<String> names;
	
	String name;
	
	@Before
	public void setUp()  {
		names = Arrays.asList("Jamie", "Cersei", "Tyrion", "Littlefinger");
	}

	@Test
	public void oldJavaStyleTest() {
		Collections.sort(names, new Comparator<String>() {
			 @Override
			 public int compare (String a, String b) {
				 return a.compareTo(b);
			 }
		});
		logger.debug("names sorted:");
		names.stream().forEach(System.out::println);
	}
	
	@Test
	public void lambdaStyle() {
		// comparator as lambda expression
		Collections.sort(names, (String a, String b) -> {return a.compareTo(b);});

		// shorter without parenthesis and return statement
		Collections.sort(names, (String a, String b) -> a.compareTo(b));
		
		// even shorter without parameter types (if we have only one parameter, braces can be omitted)
		Collections.sort(names, (a, b) -> a.compareTo(b));   // type inference
		
		logger.debug("names sorted:");
		names.stream().forEach(System.out::println);
		assertEquals("first entry should match", "Cersei" ,names.get(0));
		assertEquals("second entry should match", "Jamie" ,names.get(1));
		assertEquals("third entry should match", "Littlefinger" ,names.get(2));
		assertEquals("forth entry should match", "Tyrion" ,names.get(3));
	}
	
	@Test
	public void testLambdaScope() throws Exception {
		final String prefix = "pre-";
		String postfix = "-post";  // lambda can use non-final variable (new in java 8), throws compile-error if you try to change in lambda
		name = "Tywin Lannister";  // lambda can use instance and class variables
		Callable<String> job = () ->  prefix + name.toUpperCase() + postfix;
		System.out.println(job.call());
		assertEquals("name should have pre- and postfix", "pre-TYWIN LANNISTER-post", job.call());
	}
}
