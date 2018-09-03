package function;

/**
 * Creates a ResultantFunction using a singular type, unlike {@link ResultantFunction},
 * where it takes in an input, and output type.
 *
 * @param <T> the parameter type, of both the argument, and resultant
 *
 * @author GrimlyTwisted
 */
@FunctionalInterface
public interface PrimaryFunction<T> extends ResultantFunction<T, T> {

	@Override
	T run(T argument);
}
