package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.java.Game;

public class BowlingTest {

	@Test
	public void shouldReturnScoreZeroForZeroRolls() {
		// given
		Game game = new Game();
		// when
		int result = game.score();
		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnFalseBeforeFirstRoll() {
		// given
		Game game = new Game();
		// when
		boolean result = game.isFinished();
		// then
		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueAfterLastRoll() {
		// given
		Game game = new Game();
		for (int i = 0; i < 20; i++) {
			game.roll(1);
		}
		// when
		boolean result = game.isFinished();
		// then
		assertTrue(result);
	}

	@Test
	public void shouldReturnScoreZeroForZerosOnly() {
		// given
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			game.roll(0);
		}
		// when
		int result = game.score();
		// then
		assertEquals(0, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionForNegativeNumberOfPins() {
		// given
		Game game = new Game();
		game.roll(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionForNumberOfPinsOverTenInSingleRoll() {
		// given
		Game game = new Game();
		game.roll(11);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionForNumberOfPinsOverTenInTwoRollsInARow() {
		// given
		Game game = new Game();
		game.roll(5);
		game.roll(6);
	}

	@Test
	public void shouldReturnMaxScore() {
		// given
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			game.roll(10);
		}
		// when
		int result = game.score();
		// then
		assertEquals(300, result);
	}

	@Test
	public void shouldReturnTenForOnesOnly() {
		// given
		Game game = new Game();
		for (int i = 0; i < 20; i++) {
			game.roll(1);
		}
		// when
		int result = game.score();
		// then
		assertEquals(20, result);
	}

	@Test
	public void shouldReturnTwenty() {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(5);
		// when
		int result = game.score();
		// then
		assertEquals(20, result);
	}

	@Test
	public void shouldReturnTen() {
		// given
		Game game = new Game();
		game.roll(10);
		// when
		int result = game.score();
		// then
		assertEquals(10, result);
	}

	@Test
	public void shouldReturnFive() {
		// given
		Game game = new Game();
		game.roll(5);
		// when
		int result = game.score();
		// then
		assertEquals(5, result);
	}

	@Test
	public void shouldReturnTwelve() {
		// given
		Game game = new Game();
		game.roll(2);
		game.roll(8);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(12, result);
	}

	@Test
	public void shouldReturnEleven() {
		// given
		Game game = new Game();
		game.roll(5);
		game.roll(0);
		game.roll(6);
		// when
		int result = game.score();
		// then
		assertEquals(11, result);
	}

	@Test
	public void shouldReturnThirty() {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(10);
		// when
		int result = game.score();
		// then
		assertEquals(30, result);
	}

	@Test
	public void shouldReturnThirtyThree() {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(10);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(33, result);
	}

	@Test
	public void shouldReturnThirtyTwo() {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(3);
		game.roll(7);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(32, result);
	}

	@Test
	public void shouldReturnOneHundredEightySeven() {
		// given
		int[] numberOfPins = new int[] { 10, 9, 1, 5, 5, 7, 2, 10, 10, 10, 9, 0, 8, 2, 9, 1, 10 };
		Game game = new Game();
		for (int i = 0; i < numberOfPins.length; i++) {
			game.roll(numberOfPins[i]);
		}
		// when
		int result = game.score();
		// then
		assertEquals(187, result);
	}
}
