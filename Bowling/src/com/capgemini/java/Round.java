package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates single frame for bowling game.
 * 
 * @author ADDZIEDZ
 *
 */
public class Round {
	protected List<Roll> rolls = new ArrayList<>();
	private Round nextRound;
	private boolean isStrike = false;
	private boolean isSpare = false;

	/**
	 * Assign number of pins from single roll to the round.
	 * 
	 * @param numberOfPins
	 *            number of knocked down pins
	 */
	public void addRoll(int numberOfPins) {
		if (!rolls.isEmpty() && rolls.get(0).getScore() + numberOfPins > 10) {
			throw new IllegalArgumentException("Illegal number of pins");
		}
		rolls.add(new Roll(numberOfPins));
	}

	/**
	 * Calculate total number of knocked pins in a single round.
	 * 
	 * @return sum of points for knocked down pins and bonus
	 */
	public int calculateScore() {
		int score = 0;
		for (Roll singleRoll : rolls) {
			score += singleRoll.getScore();
		}
		return score + calculateBonusPoints();
	}

	private void updateBonus() {
		if (rolls.get(0).getScore() == 10) {
			this.isStrike = true;
		}
		if (rolls.size() == 2 && rolls.get(0).getScore() + rolls.get(1).getScore() == 10) {
			this.isSpare = true;
		}
	}

	private int calculateBonusPoints() {
		updateBonus();
		if (nextRound != null) {
			if (isStrike) {
				int bonusScore;
				if (nextRound.rolls.size() == 1 && nextRound.nextRound == null) {
					bonusScore = nextRound.rolls.get(0).getScore();
				} else if (nextRound.rolls.size() == 1 && nextRound.nextRound != null) {
					bonusScore = nextRound.rolls.get(0).getScore() + nextRound.nextRound.rolls.get(0).getScore();
				} else {
					bonusScore = nextRound.rolls.get(0).getScore() + nextRound.rolls.get(1).getScore();
				}
				return bonusScore;
			}
			if (isSpare) {
				return nextRound.rolls.get(0).getScore();
			}
		}
		return 0;
	}

	/**
	 * Makes a reference to the next round
	 * 
	 * @param nextRound
	 *            reference to the next Round object
	 */
	public void assignNextRound(Round nextRound) {
		this.nextRound = nextRound;
	}

	/**
	 * Checks the status of current round
	 * 
	 * @return true if current round is finished, otherwise false
	 */
	public boolean isFinished() {
		return rolls.get(0).getScore() == 10 || rolls.size() == 2;
	}
}
