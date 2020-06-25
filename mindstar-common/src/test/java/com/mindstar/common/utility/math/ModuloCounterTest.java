package com.mindstar.common.utility.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Created by M Waldner on 6/24/2020.
 */
class ModuloCounterTest {

	@Test
	void counterInitializesToThreshold() {
		ModuloCounter counter = new ModuloCounter(3, 17);

		assertEquals(3, counter.getCurrent(), "init() did not set counter at threshold");
	}

	@Test
	void constructorThrowsExceptionWhenCeilingIsGreaterThanOrEqualToThreshold() {
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> new ModuloCounter(21, 17),
				"Expected IllegalArgumentException to be thrown"
		);

		assertEquals("Ceiling must be greater than threshold.", thrown.getMessage());
	}

	@Test
	void resetReturnsCounterToThreshold() {
		ModuloCounter counter = new ModuloCounter(3, 17);
		counter.increment();
		counter.reset();

		assertEquals(counter.getThreshold(), counter.getCurrent(), "reset() did not return counter to threshold");
	}

	@Test
	void resetReturnsCounterToThresholdWhenRangeIsOneNumber() {
		ModuloCounter counter = new ModuloCounter(3, 4);
		counter.increment();
		counter.reset();

		assertEquals(counter.getThreshold(), counter.getCurrent(), "reset() did not return counter to threshold");
	}

	@Test
	void resetReturnsCounterToThresholdWhenRangeIsOneNegativeNumber() {
		ModuloCounter counter = new ModuloCounter(-3, -2);
		counter.increment();
		counter.reset();

		assertEquals(counter.getThreshold(), counter.getCurrent(), "reset() did not return counter to threshold");
	}

	@Test
	void canSuccessfullyMoveTimerToAPositionLessThanCurrent() {
		ModuloCounter counter = new ModuloCounter(3, 17);
		counter.incrementBy(10);
		counter.moveTo(12);

		assertEquals(12, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void canSuccessfullyMoveTimerToAPositionLessThanCurrentWhenRangeIsOneNumber() {
		ModuloCounter counter = new ModuloCounter(3, 4);
		counter.incrementBy(1);
		counter.moveTo(3);

		assertEquals(3, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void canSuccessfullyMoveTimerToAPositionLessThanCurrentWhenRangeIsOneNegativeNumber() {
		ModuloCounter counter = new ModuloCounter(-3, -2);
		counter.incrementBy(1);
		counter.moveTo(-3);

		assertEquals(-3, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void canSuccessfullyMoveTimerToPositionHighThanCurrent() {
		ModuloCounter counter = new ModuloCounter(3, 17);
		counter.incrementBy(10);
		counter.moveTo(14);

		assertEquals(14, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void canSuccessfullyMoveTimerToPositionHighThanCurrentWhenRangeIsOneNumber() {
		ModuloCounter counter = new ModuloCounter(3, 4);
		counter.moveTo(4);

		assertEquals(4, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void canSuccessfullyMoveTimerToPositionHighThanCurrentWhenRangeIsOneNegativeNumber() {
		ModuloCounter counter = new ModuloCounter(-3, -2);
		counter.moveTo(-2);

		assertEquals(-2, counter.getCurrent(), "moveTo() is broken");
	}

	@Test
	void moveToCircularSuccessfullyWrapsAroundWhenHigherThanCeiling() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.moveToCircular(10);

		assertEquals(5, counter.getCurrent(), "moveToCircular() is broken");
	}

	@Test
	void moveToCircularSuccessfullyWrapsAroundWhenLowerThanThreshold() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.moveToCircular(1);

		assertEquals(6, counter.getCurrent(), "moveToCircular() is broken");
	}

	@Test
	void attemptingToMoveTimerToPositionGreaterThanCeilingThrowsException() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> counter.moveTo(21),
				"Expected IllegalArgumentException to be thrown"
		);

		assertEquals("Can only move counter to a number between the threshold and the ceiling.", thrown.getMessage());
	}

	@Test
	void attemptingToMoveTimerToPositionLessThanThresholdThrowsException() {
		ModuloCounter counter = new ModuloCounter(3, 7);
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class,
				() -> counter.moveTo(2),
				"Expected IllegalArgumentException to be thrown"
		);

		assertEquals("Can only move counter to a number between the threshold and the ceiling.", thrown.getMessage());
	}

	@Test
	void canFastForwardToMax() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.fastForwardToMax();

		assertEquals(counter.getCeiling(), counter.getCurrent(), "fastForwardToMax() is broken");
	}

	@Test
	void canSuccessfullyIncrementCounter() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.increment();

		assertEquals(3, counter.getCurrent(), "increment() is broken");
	}

	@Test
	void incrementingCounterAtMaxWrapsToThreshold() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.fastForwardToMax();
		counter.increment();

		assertEquals(2, counter.getCurrent(), "increment() is broken");
	}

	@Test
	void canSuccessfullyIncrementCounterByDesiredAmountOfSteps() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.incrementBy(3);

		assertEquals(5, counter.getCurrent(), "incrementBy() is broken");
	}

	@Test
	void incrementByWrapsAround() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.incrementBy(6);

		assertEquals(2, counter.getCurrent(), "increment() is broken");
	}

	@Test
	void canSuccessfullyDecrementCounter() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.moveTo(5);
		counter.decrement();

		assertEquals(4, counter.getCurrent(), "decrement() is broken");
	}

	@Test
	void decrementingCounterAtMinWrapsToCeiling() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.reset();
		counter.decrement();

		assertEquals(7, counter.getCurrent(), "decrement() is broken");
	}

	@Test
	void canSuccessfullyDecrementCounterByDesiredAmountOfSteps() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.moveTo(6);
		counter.decrementBy(3);

		assertEquals(3, counter.getCurrent(), "decrementBy() is broken");
	}

	@Test
	void decrementByWrapsAround() {
		ModuloCounter counter = new ModuloCounter(2, 7);
		counter.decrementBy(2);

		assertEquals(6, counter.getCurrent(), "decrementBy() is not wrapping around");
	}

}