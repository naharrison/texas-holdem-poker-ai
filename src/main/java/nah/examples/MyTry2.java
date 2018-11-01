package nah.examples;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ntnu.texasai.controller.EquivalenceClassController;
import edu.ntnu.texasai.controller.GameHandController;
import edu.ntnu.texasai.controller.HandPowerRanker;
import edu.ntnu.texasai.controller.HandStrengthEvaluator;
import edu.ntnu.texasai.controller.StatisticsController;
import edu.ntnu.texasai.controller.opponentmodeling.OpponentModeler;
import edu.ntnu.texasai.controller.phase2.PlayerControllerPhaseIINormal;
import edu.ntnu.texasai.model.Game;
import edu.ntnu.texasai.model.GameHand;
import edu.ntnu.texasai.model.Player;
import edu.ntnu.texasai.model.cards.Card;
import edu.ntnu.texasai.model.cards.CardNumber;
import edu.ntnu.texasai.model.cards.CardSuit;
import edu.ntnu.texasai.model.cards.Deck;
import edu.ntnu.texasai.model.gameproperties.NahGameProperties;
import edu.ntnu.texasai.persistence.OpponentsModelPersistence;
import edu.ntnu.texasai.persistence.PersistenceManager;
import edu.ntnu.texasai.persistence.PreFlopPersistence;
import edu.ntnu.texasai.utils.ConsoleLogger;

public class MyTry2 {
public static void main(String[] args) {

    String clin = "";
    Scanner sc = new Scanner(System.in);
    System.out.println("type \"yup\" to proceed");
    while(!clin.equals("yup")) {
        clin = sc.next();
    }
    sc.close();

    // create players:
    int nPlayers = 3;
    int initialMoney = 200;

    ArrayList<EquivalenceClassController> eccs = new ArrayList<>();
    ArrayList<PersistenceManager> pms = new ArrayList<>();
    ArrayList<ConsoleLogger> cls = new ArrayList<>();
    ArrayList<PreFlopPersistence> pfps = new ArrayList<>();
    ArrayList<HandPowerRanker> hprs = new ArrayList<>();
    ArrayList<HandStrengthEvaluator> hses = new ArrayList<>();
    ArrayList<PlayerControllerPhaseIINormal> pcs = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    for(int k = 0; k < nPlayers; k++) {
        eccs.add(new EquivalenceClassController());
        pms.add(new PersistenceManager());
        cls.add(new ConsoleLogger());
        pfps.add(new PreFlopPersistence(pms.get(k), cls.get(k)));
        hprs.add(new HandPowerRanker());
        hses.add(new HandStrengthEvaluator(hprs.get(k)));
        pcs.add(new PlayerControllerPhaseIINormal(eccs.get(k), pfps.get(k), hses.get(k)));
        players.add(new Player(k+1, initialMoney, pcs.get(k)));
    }


    // create deck:
    Deck deck = new Deck();
    deck.removeAllCards();
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.ACE));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.KING));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.QUEEN));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.JACK));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.TEN));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.NINE));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.EIGHT));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.SEVEN));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.SIX));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.FIVE));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.FOUR));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.THREE));
    deck.addCard(new Card(CardSuit.SPADE, CardNumber.TWO));

    Deck deck2 = new Deck();
    deck2.removeAllCards();
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.ACE));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.KING));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.QUEEN));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.JACK));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.TEN));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.NINE));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.EIGHT));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.SEVEN));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.SIX));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.FIVE));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.FOUR));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.THREE));
    deck2.addCard(new Card(CardSuit.CLUB, CardNumber.TWO));


    GameHand gameHand = new GameHand(players, deck);
    GameHand gameHand2 = new GameHand(players, deck2);
    Game game = new Game(players);

    EquivalenceClassController ecc = new EquivalenceClassController();
    PersistenceManager pm = new PersistenceManager();
    ConsoleLogger cl = new ConsoleLogger();
    PreFlopPersistence pfp = new PreFlopPersistence(pm, cl);
    HandPowerRanker hpr = new HandPowerRanker();
    HandPowerRanker hpr2 = new HandPowerRanker();
    HandStrengthEvaluator hse = new HandStrengthEvaluator(hpr);
    HandStrengthEvaluator hse2 = new HandStrengthEvaluator(hpr2);

    PlayerControllerPhaseIINormal pc = new PlayerControllerPhaseIINormal(ecc, pfp, hse);
    OpponentsModelPersistence omp = new OpponentsModelPersistence(pm, cl);
    OpponentModeler om = new OpponentModeler(omp);
    NahGameProperties ngp = new NahGameProperties(pc);


    GameHandController ghc = new GameHandController( new ConsoleLogger(), new HandPowerRanker(), ngp, new StatisticsController(), hse2, om);


    // Play:
    System.out.println("");
    for(int j = 0; j < players.size(); j++) System.out.println(players.get(j).getMoney());
    System.out.println("");

    ghc.playWithGameHand(game, gameHand);

    System.out.println("");
    for(int j = 0; j < players.size(); j++) System.out.println(players.get(j).getMoney());
    System.out.println("");

    ghc.playWithGameHand(game, gameHand2);

    System.out.println("");
    for(int j = 0; j < players.size(); j++) System.out.println(players.get(j).getMoney());
    System.out.println("");




}
}

