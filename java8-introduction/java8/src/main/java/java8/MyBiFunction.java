package java8;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface MyBiFunction<T, U, R> extends BiFunction<T, U, R>{
	
	default Function<U, R> curry(T t) {
		return u -> apply(t, u);
	}
}
