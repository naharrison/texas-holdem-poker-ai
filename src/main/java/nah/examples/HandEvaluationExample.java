package nah.examples;

import java.util.ArrayList;

import edu.ntnu.texasai.controller.HandPowerRanker;
import edu.ntnu.texasai.controller.HandStrengthEvaluator;
import edu.ntnu.texasai.model.cards.Card;
import edu.ntnu.texasai.model.cards.CardNumber;
import edu.ntnu.texasai.model.cards.CardSuit;

public class HandEvaluationExample {
public static void main(String[] args) {
	
   	HandPowerRanker handPowerRanker = new HandPowerRanker();
   	HandStrengthEvaluator handStrengthEvaluator = new HandStrengthEvaluator(handPowerRanker);
	
	Card hole1 = new Card(CardSuit.CLUB, CardNumber.TWO);
	Card hole2 = new Card(CardSuit.CLUB, CardNumber.SEVEN);
	ArrayList<Card> playerHoleCards = new ArrayList<>();
	playerHoleCards.add(hole1);
	playerHoleCards.add(hole2);
	Card shared1 = new Card(CardSuit.CLUB, CardNumber.QUEEN);
	Card shared2 = new Card(CardSuit.CLUB, CardNumber.JACK);
	Card shared3 = new Card(CardSuit.CLUB, CardNumber.TEN);
	Card shared4 = new Card(CardSuit.CLUB, CardNumber.NINE);
	ArrayList<Card> sharedCards = new ArrayList<>();
	sharedCards.add(shared1);
	sharedCards.add(shared2);
	sharedCards.add(shared3);
	sharedCards.add(shared4);
	int numberOfPlayers = 4;
	
	double strength = handStrengthEvaluator.evaluate(playerHoleCards, sharedCards, numberOfPlayers);
	
	System.out.println(strength);
	
}
}
