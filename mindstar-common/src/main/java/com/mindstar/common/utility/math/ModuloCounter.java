package com.mindstar.common.utility.math;

/**
 * A counter that restarts at the beginning after reaching the upper limit.
 * <p>
 * {@link #ModuloCounter} is initialized with 2 integers:
 * the number to start counting from (threshold), and the number we count to (ceiling).
 * For instance, say we wanted to count from 0 to 25, we initialize the counter thus: <code>ModuloCounter counter = new ModuloCounter(0,25)</code>.
 * Calling {@link #reset} would set the counter to 0. Calling {@link #increment} would increment the counter by 1.
 * Once the counter reaches 25, calling <code>increment()</code> would wrap it around right back to 0.
 */
public class ModuloCounter {
	private final int threshold;
	private final int ceiling;
	private int current;

	/**
	 * @param threshold number to begin counting from
	 * @param ceiling   number to count to before wrapping around
	 * @throws IllegalArgumentException if threshold >= ceiling
	 */
	public ModuloCounter(int threshold, int ceiling) throws IllegalArgumentException {
		if (threshold >= ceiling) {
			throw new IllegalArgumentException("Ceiling must be greater than threshold.");
		}

		this.threshold = threshold;
		this.ceiling = ceiling;
		this.current = threshold;
	}

	/**
	 * Reset counter to threshold.
	 */
	public void reset() {
		current = threshold;
	}

	/**
	 * @param desiredNumber number to fast forward counter to
	 * @throws IllegalArgumentException if number is higher than the specified ceiling
	 */
	public void moveTo(int desiredNumber) throws IllegalArgumentException {
		if (desiredNumber < threshold || desiredNumber > ceiling) {
			throw new IllegalArgumentException("Can only move counter to a number between the threshold and the ceiling.");
		} else {
			current = desiredNumber;
		}
	}

	/**
	 * If desired position is out of range, counter will wrap around.
	 * For example: if threshold = 2, ceiling = 7, and desiredNumber = 10,
	 * counter will move to 5 (threshold + 3).
	 *
	 * @param desiredNumber number to fast forward counter to
	 */
	public void moveToCircular(int desiredNumber){
		if (desiredNumber < threshold) {
			int difference = threshold - desiredNumber;
			moveTo(ceiling - difference);
		} else if (desiredNumber > ceiling) {
			int difference = desiredNumber - ceiling;
			moveTo(threshold + difference);
		} else {
			current = desiredNumber;
		}
	}

	/**
	 * Fast forwards the counter to the max possible position (ceiling).
	 */
	public void fastForwardToMax() {
		moveTo(ceiling);
	}

	/**
	 * Increments the counter by 1.
	 */
	public void increment() {
		if (current < ceiling) {
			current++;
		} else {
			current = threshold;
		}
	}

	/**
	 * @param steps number of steps to increment the counter by
	 */
	public void incrementBy(int steps) {
		for (int i = 0; i < steps; i++) {
			increment();
		}
	}

	/**
	 * Decrements the counter by 1.
	 */
	public void decrement() {
		if (current > threshold) {
			current--;
		} else {
			current = ceiling;
		}
	}

	/**
	 * @param steps number of steps to decrement the counter by
	 */
	public void decrementBy(int steps) {
		for (int i = 0; i < steps; i++) {
			decrement();
		}
	}

	/**
	 * @return counter's lower limit
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * @return counter's upper limit
	 */
	public int getCeiling() {
		return ceiling;
	}

	/**
	 * @return the number the counter is currently up to
	 */
	public int getCurrent() {
		return current;
	}
}
