package com.mindstar.common.utility.math;

/**
 * Created by M Waldner on 6/24/2020.<br><br>
 *
 * This counter is initialized with 2 integers:
 * the number to start counting from (threshold), and the number we count to (ceiling).
 * For instance, say we wanted to count from 0 to 25, we initialize the counter thus: <code>{@link #ModuloCounter} counter = new {@link #ModuloCounter}(0,25)</code>.
 * Calling {@link #init} would set the counter to 0. Calling {@link #increment} would increment the counter by 1.
 * Once the counter reaches 25, calling {@link #increment} would wrap it around right back to 0.
 *
 */
public class ModuloCounter {
	private final int threshold;
	private final int ceiling;
	private int current;

	/**
	 *
	 * @param threshold number to begin counting from
	 * @param ceiling number to count to before wrapping around
	 */
	public ModuloCounter(int threshold, int ceiling) {
		this.threshold = threshold;
		this.ceiling = ceiling;
	}

	/**
	 * (re)set counter to threshold
	 */
	public void init() {
		current = threshold;
	}

	/**
	 *
	 * @return the number the counter is currently up to
	 */
	public int get() {
		return current;
	}

	/**
	 * increments the counter by 1
	 */
	public void increment() {
		if (current < ceiling) {
			current++;
		} else {
			current = threshold;
		}
	}

}
