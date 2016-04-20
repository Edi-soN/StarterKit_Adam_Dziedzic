package com.capgemini.java;

import java.util.LinkedList;
import java.util.List;

/**
 * Ten-pin bowling game result calculator.
 * 
 * @author ADDZIEDZ
 *
 */
public class Game implements BowlingGameResultCalculator {
	private static final int MAX_NUMBER_OF_ROUNDS = 10;
	private static final int INDEX_OF_LAST_ROUND = 9;
	private List<Round> rounds = new LinkedList<>();

	/**
	 * Register a thrown a ball.
	 * 
	 * @param numberOfPins
	 *            number of knocked down pins
	 */
	@Override
	public void roll(int numberOfPins) {
		if (isFinished()) {
			throw new UnsupportedOperationException("Game is Finished");
		}
		if (numberOfPins < 0 || numberOfPins > 10) {
			throw new IllegalArgumentException("Illegal number of pins");
		}
		if (!rounds.isEmpty() && !rounds.get(rounds.size() - 1).isFinished()) {
			rounds.get(rounds.size() - 1).addRoll(numberOfPins);
		} else {
			if (rounds.size() == 9) {
				rounds.add(new LastRound());
			} else {
				rounds.add(new Round());
			}
			if (rounds.size() > 1) {
				rounds.get(rounds.size() - 2).assignNextRound(rounds.get(rounds.size() - 1));
			}
			rounds.get(rounds.size() - 1).addRoll(numberOfPins);
		}
	}

	/**
	 * @return true if a game is over, otherwise false
	 */
	@Override
	public boolean isFinished() {
		return rounds.size() == MAX_NUMBER_OF_ROUNDS && rounds.get(INDEX_OF_LAST_ROUND).isFinished();
	}

	/**
	 * @return current game score
	 */
	@Override
	public int score() {
		int score = 0;
		for (Round singleRound : rounds) {
			score += singleRound.calculateScore();
		}
		return score;
	}
}
