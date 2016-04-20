package com.capgemini.java;

/**
 * Enumeration of possible cards setups with their values.
 * 
 * @author ADDZIEDZ
 *
 */
public enum CardSetup {
	HIGHCARD("11111", 0), ONEPAIR("2111", 1), TWOPAIR("221", 2), THREEOFAKIND("311", 3), STRAIGHT("11111", 4), FLSUH(
			"11111", 5), FULLHOUSE("32", 6), FOUROFAKIND("41", 7), STRAIGHTFLUSH("11111", 8), ROYALFLUSH("11111", 9);

	private final String signature;
	private final int valsetupValue;

	CardSetup(String signature, int valsetupValue) {
		this.signature = signature;
		this.valsetupValue = valsetupValue;
	}

	public int getSetupValue() {
		return this.valsetupValue;
	}

	public String getSignature() {
		return this.signature;
	}

	/**
	 * Converts setup signature to CardSetup object
	 * 
	 * @param signature
	 *            representation of cards in a given setup
	 * @return CardSetup object that represents given signature
	 */
	public static CardSetup getEnum(String signature) {
		for (CardSetup enumCardSetup : values()) {
			if (enumCardSetup.getSignature().equalsIgnoreCase(signature)) {
				return enumCardSetup;
			}
		}

		throw new IllegalArgumentException();
	}
}
