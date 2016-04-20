package com.capgemini.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Set of five cards for a single player.
 * 
 * @author ADDZIEDZ
 *
 */
public class Hand implements Comparable<Hand> {
	private boolean isColor;
	private int setupValue = 0;
	private List<Map.Entry<Integer, Integer>> sortedCards;
	private Map<Integer, Integer> hand = new TreeMap<>(Collections.reverseOrder());

	/**
	 * Add all cards from given list to local map.
	 * 
	 * @param cardList
	 *            given list of cards
	 */
	public void addCardsToHand(List<Card> cardList) {
		checkCardsColor(cardList);
		for (Card card : cardList) {
			int cardValueCounter = 0;
			if (hand.get(card.getCardValue().getValue()) != null) {
				cardValueCounter = hand.get(card.getCardValue().getValue());
			}
			hand.put(card.getCardValue().getValue(), 1 + cardValueCounter);
		}
		sortCards();
		calculateSetupValue();
	}

	private void checkCardsColor(List<Card> cardList) {
		boolean allCardsOneColor = true;
		String firstCardColor = "";
		for (Card card : cardList) {
			if (firstCardColor.isEmpty()) {
				firstCardColor = card.getCardColor();
			}
			if (card.getCardColor() != firstCardColor) {
				allCardsOneColor = false;
			}
		}
		this.isColor = allCardsOneColor;
	}

	private void calculateSetupValue() {
		boolean idCardCounterEqualFive = (sortedCards.size() == 5);
		boolean isValueDiffFirstAndLastCardEqualFour = ((sortedCards.get(0).getKey()
				- sortedCards.get(sortedCards.size() - 1).getKey()) == 4);
		if (!idCardCounterEqualFive) {
			String cardQuantities = "";
			for (Map.Entry<Integer, Integer> card : sortedCards) {
				cardQuantities += card.getValue().toString();
			}
			this.setupValue = CardSetup.getEnum(cardQuantities).getSetupValue();
		}
		boolean isColorAndValueDifferenceFour = (isColor && isValueDiffFirstAndLastCardEqualFour);
		if (idCardCounterEqualFive && isColorAndValueDifferenceFour) {
			this.setupValue = CardSetup.STRAIGHTFLUSH.getSetupValue();
		}
		if (idCardCounterEqualFive && isColorAndValueDifferenceFour
				&& sortedCards.get(0).getKey() == CardValue.ACE.getValue()) {
			this.setupValue = CardSetup.ROYALFLUSH.getSetupValue();
		}
		if (idCardCounterEqualFive && isColorAndValueDifferenceFour) {
			this.setupValue = CardSetup.FLSUH.getSetupValue();
		}
		if (idCardCounterEqualFive && isValueDiffFirstAndLastCardEqualFour) {
			this.setupValue = CardSetup.STRAIGHT.getSetupValue();
		}
	}

	private void sortCards() {
		sortedCards = new ArrayList<Map.Entry<Integer, Integer>>(hand.entrySet());
		Collections.sort(sortedCards, new sortByCardQuantityThenByCardValue<Integer, Integer>());
	}

	/**
	 * Overridden method from Comparable interface defines the rules for
	 * comparing two sets of cards.
	 * 
	 * @return one if first set wins, -1 if second set wins, 0 if draw
	 */
	@Override
	public int compareTo(Hand secondHand) {
		if (this.setupValue > secondHand.setupValue) {
			return 1;
		} else if (this.setupValue < secondHand.setupValue) {
			return -1;
		} else {
			for (int i = 0; i < this.sortedCards.size(); i++) {
				if (this.sortedCards.get(i).getKey() > secondHand.sortedCards.get(i).getKey()) {
					return 1;
				}
				if (this.sortedCards.get(i).getKey() < secondHand.sortedCards.get(i).getKey()) {
					return -1;
				}
			}
		}
		return 0;
	}

	// Comparator that sorts Map.Entry objects with Comparable keys and values
	// Source:
	// http://stackoverflow.com/questions/3074154/sorting-a-hashmap-based-on-value-then-key
	private class sortByCardQuantityThenByCardValue<K extends Comparable<? super K>, V extends Comparable<? super V>>
			implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> firstCard, Map.Entry<K, V> secondCard) {
			int cardQuantity = secondCard.getValue().compareTo(firstCard.getValue());
			if (cardQuantity != 0) {
				return cardQuantity;
			} else {
				return secondCard.getKey().compareTo(firstCard.getKey());
			}
		}

	}

}
