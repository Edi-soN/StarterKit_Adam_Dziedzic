package com.capgemini.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.capgemini.java.Card;
import com.capgemini.java.CardValue;
import com.capgemini.java.CardConverter;
import com.capgemini.java.Poker;

public class PokerTest {
	@Test
	public void shouldReturnCardValueOfEight() {
		// given
		CardConverter conv = new CardConverter();
		List<Card> cardList = conv.convertRawDataToCards();
		// when
		int result = cardList.get(0).getCardValue().getValue();
		// then
		assertEquals(8, result);
	}

	@Test
	public void shouldReturnCardValueOfTen() {
		// given
		CardConverter conv = new CardConverter();
		List<Card> cardList = conv.convertRawDataToCards();
		// when
		int result = cardList.get(1).getCardValue().getValue();
		// then
		assertEquals(10, result);
	}

	@Test
	public void shouldReturnZero() {
		// given
		Card[] cards = new Card[] { new Card(CardValue.TWO, "S"), new Card(CardValue.TWO, "D"),
				new Card(CardValue.SEVEN, "C"), new Card(CardValue.NINE, "H"), new Card(CardValue.TEN, "H"),
				new Card(CardValue.FIVE, "S"), new Card(CardValue.FIVE, "D"), new Card(CardValue.FIVE, "C"),
				new Card(CardValue.NINE, "H"), new Card(CardValue.TEN, "H") };
		List<Card> cardList = new ArrayList<>(Arrays.asList(cards));
		Poker game = new Poker();
		// when
		int result = game.calculateNumberOfPlayerOneWins(cardList);
		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnOne() {
		// given
		Card[] cards = new Card[] { new Card(CardValue.TWO, "S"), new Card(CardValue.TWO, "D"),
				new Card(CardValue.TWO, "C"), new Card(CardValue.TWO, "H"), new Card(CardValue.TEN, "H"),
				new Card(CardValue.FIVE, "S"), new Card(CardValue.FIVE, "D"), new Card(CardValue.FIVE, "C"),
				new Card(CardValue.NINE, "H"), new Card(CardValue.TEN, "H") };
		List<Card> cardList = new ArrayList<>(Arrays.asList(cards));
		Poker game = new Poker();
		// when
		int result = game.calculateNumberOfPlayerOneWins(cardList);
		// then
		assertEquals(1, result);
	}

	@Test
	public void shouldReturn376FirstPlayerWins() {
		// given
		Poker game = new Poker();
		// when
		int result = game.calculateNumberOfPlayerOneWins();
		// then
		assertEquals(376, result);
	}
}
