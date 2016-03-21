package java8;

public interface MyInterface {
	
	static int square(int x) {
		return x * x;
	}
	
	// new in java 8: default method 
	default public long sum(long a, long b) {  // public can be omitted
		return a + b;
	}
	
	public long multiply(long x, long y);
	
	// why?
	// Default methods enable you to add new functionality to existing interfaces and ensure binary compatibility with code written for older versions of those interfaces.
	// e.g. new method "comparing()" in Comparator class, old src-code can be used without modification!
}
