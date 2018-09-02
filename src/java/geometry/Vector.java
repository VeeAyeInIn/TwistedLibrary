package geometry;

import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Vector with generics, allowing the object to be created
 * with different aspects. Can be created using {@link Long},
 * a {@link Double}, or even {@link java.math.BigDecimal}.
 *
 * @param <T> a number type parameter
 *
 * @author GrimlyTwisted
 */
public class Vector<T extends Number> {

	private BinaryOperator<T> function;
	private T x;
	private T y;
	private T z;

	/**
	 * Instantiates a new Vector.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 */
	public Vector(T x, T y, T z) {

		Objects.requireNonNull(x, "Number cannot be null.");
		Objects.requireNonNull(y, "Number cannot be null.");
		Objects.requireNonNull(z, "Number cannot be null.");

		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Instantiates a new Vector.
	 *
	 * @param x        the x coordinate
	 * @param y        the y coordinate
	 * @param z        the z coordinate
	 * @param function the function
	 */
	public Vector(T x, T y, T z, BinaryOperator<T> function) {

		Objects.requireNonNull(x, "Number cannot be null.");
		Objects.requireNonNull(y, "Number cannot be null.");
		Objects.requireNonNull(z, "Number cannot be null.");
		Objects.requireNonNull(function, "SingleFunction cannot be null.");

		this.x = x;
		this.y = y;
		this.z = z;
		this.function = function;
	}

	/**
	 * Gets the x coordinate.
	 *
	 * @return the x value
	 */
	public T getX() {
		return x;
	}

	/**
	 * Sets the x coordinate.
	 *
	 * @param x the x value
	 */
	public void setX(T x) {
		Objects.requireNonNull(x, "Number cannot be null.");
		this.x = x;
	}

	/**
	 * Gets the y coordinate.
	 *
	 * @return the y value
	 */
	public T getY() {
		return y;
	}

	/**
	 * Sets the y coordinate.
	 *
	 * @param y the y value
	 */
	public void setY(T y) {
		Objects.requireNonNull(y, "Number cannot be null.");
		this.y = y;
	}

	/**
	 * Gets the z coordinate.
	 *
	 * @return the z value
	 */
	public T getZ() {
		return z;
	}

	/**
	 * Sets the z coordinate.
	 *
	 * @param z the z value
	 */
	public void setZ(T z) {
		Objects.requireNonNull(z, "Number cannot be null.");
		this.z = z;
	}

	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public BinaryOperator<T> getFunction() {
		return function;
	}

	/**
	 * Sets the function that controls the relationship between the coordinates and
	 * the offsets in {@link #dislocate(Number, Number, Number)}.
	 *
	 * @param function the function
	 */
	public void setFunction(BinaryOperator<T> function) {
		Objects.requireNonNull(function, "SingleFunction cannot be null.");
		this.function = function;
	}

	/**
	 * Dislocate the Vector from its original position, using a custom function to
	 * determine the relationship between the offsets and coordinates.
	 *
	 * @param offsetX  the offset x value
	 * @param offsetY  the offset y value
	 * @param offsetZ  the offset z value
	 * @param function the custom function
	 */
	public void dislocate(T offsetX, T offsetY, T offsetZ, BinaryOperator<T> function) {

		Objects.requireNonNull(offsetX, "Offset cannot be null.");
		Objects.requireNonNull(offsetY, "Offset cannot be null.");
		Objects.requireNonNull(offsetZ, "Offset cannot be null.");
		Objects.requireNonNull(function, "SingleFunction cannot be null.");

		this.setX(function.apply(getX(), offsetX));
		this.setY(function.apply(getY(), offsetY));
		this.setY(function.apply(getZ(), offsetZ));
	}

	/**
	 * Dislocate the Vector from its original position.
	 *
	 * @param offsetX the offset x value
	 * @param offsetY the offset y value
	 * @param offsetZ the offset z value
	 */
	public void dislocate(T offsetX, T offsetY, T offsetZ) {

		Objects.requireNonNull(offsetX, "Offset cannot be null.");
		Objects.requireNonNull(offsetY, "Offset cannot be null.");
		Objects.requireNonNull(offsetZ, "Offset cannot be null.");

		this.setX(runFunction(getX(), offsetX));
		this.setY(runFunction(getY(), offsetY));
		this.setZ(runFunction(getZ(), offsetZ));
	}

	private T runFunction(T a, T b) {

		Objects.requireNonNull(a, "Number cannot be null.");
		Objects.requireNonNull(b, "Number cannot be null.");

		return function.apply(a, b);
	}

	@Override
	public String toString() {
		return "{" + x + ", " + y + ", " + z + "}";
	}
}
