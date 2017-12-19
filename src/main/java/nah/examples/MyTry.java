package nah.examples;

import java.util.ArrayList;

import edu.ntnu.texasai.controller.EquivalenceClassController;
import edu.ntnu.texasai.controller.GameHandController;
import edu.ntnu.texasai.controller.HandPowerRanker;
import edu.ntnu.texasai.controller.HandStrengthEvaluator;
import edu.ntnu.texasai.controller.StatisticsController;
import edu.ntnu.texasai.controller.opponentmodeling.OpponentModeler;
import edu.ntnu.texasai.controller.phase2.PlayerControllerPhaseIIBluff;
import edu.ntnu.texasai.controller.phase2.PlayerControllerPhaseIINormal;
import edu.ntnu.texasai.controller.phase3.PlayerControllerPhaseIIIAgressive;
import edu.ntnu.texasai.controller.phase3.PlayerControllerPhaseIIIConservative;
import edu.ntnu.texasai.model.Game;
import edu.ntnu.texasai.model.Player;
import edu.ntnu.texasai.model.gameproperties.DemoGameProperties;
import edu.ntnu.texasai.persistence.OpponentsModelPersistence;
import edu.ntnu.texasai.persistence.PersistenceManager;
import edu.ntnu.texasai.persistence.PreFlopPersistence;
import edu.ntnu.texasai.utils.ConsoleLogger;

public class MyTry {
public static void main(String[] args) {
	
   	EquivalenceClassController equivalenceClassController1 = new EquivalenceClassController();
   	PersistenceManager persistenceManager1 = new PersistenceManager();
   	ConsoleLogger logger1 = new ConsoleLogger();
   	PreFlopPersistence preFlopPersistence1 = new PreFlopPersistence(persistenceManager1, logger1);
   	HandPowerRanker handPowerRanker1 = new HandPowerRanker();
   	HandStrengthEvaluator handStrengthEvaluator1 = new HandStrengthEvaluator(handPowerRanker1);
    PlayerControllerPhaseIINormal p1Controller = new PlayerControllerPhaseIINormal(equivalenceClassController1, preFlopPersistence1, handStrengthEvaluator1);
	Player p1 = new Player(1, 1000, p1Controller);

   	EquivalenceClassController equivalenceClassController2 = new EquivalenceClassController();
   	PersistenceManager persistenceManager2 = new PersistenceManager();
   	ConsoleLogger logger2 = new ConsoleLogger();
   	PreFlopPersistence preFlopPersistence2 = new PreFlopPersistence(persistenceManager2, logger2);
   	HandPowerRanker handPowerRanker2 = new HandPowerRanker();
   	HandStrengthEvaluator handStrengthEvaluator2 = new HandStrengthEvaluator(handPowerRanker2);
    PlayerControllerPhaseIINormal p2Controller = new PlayerControllerPhaseIINormal(equivalenceClassController2, preFlopPersistence2, handStrengthEvaluator2);
	Player p2 = new Player(2, 1000, p2Controller);
	
	ArrayList<Player> players = new ArrayList<>();
	players.add(p1);
	players.add(p2);
	
	//GameHand gameHand = new GameHand(players);
	
	
	
	Game game = new Game(players);

	PlayerControllerPhaseIINormal playerControllerPhaseIINormal = new PlayerControllerPhaseIINormal(equivalenceClassController2, preFlopPersistence2, handStrengthEvaluator2);
	PlayerControllerPhaseIIBluff playerControllerPhaseIIBluff = new PlayerControllerPhaseIIBluff(equivalenceClassController2, preFlopPersistence2, handStrengthEvaluator2);
	OpponentsModelPersistence opponentsModelPersistence = new OpponentsModelPersistence(persistenceManager2, logger2);
	OpponentModeler opponentModeler = new OpponentModeler(opponentsModelPersistence);
	PlayerControllerPhaseIIIAgressive playerControllerPhaseIIIAgressive = new PlayerControllerPhaseIIIAgressive(playerControllerPhaseIINormal, handStrengthEvaluator2, opponentModeler);
	PlayerControllerPhaseIIIConservative playerControllerPhaseIIIConservative = new PlayerControllerPhaseIIIConservative(playerControllerPhaseIINormal, handStrengthEvaluator2, opponentModeler);
	
	DemoGameProperties demoGameProperties =
			new DemoGameProperties(playerControllerPhaseIINormal,
			playerControllerPhaseIIBluff,
			playerControllerPhaseIIIAgressive,
			playerControllerPhaseIIIConservative);
	
	GameHandController gameHandController =
			new GameHandController(new ConsoleLogger(),
			handPowerRanker2,
			demoGameProperties,
			new StatisticsController(),
			handStrengthEvaluator2,
			opponentModeler);
	
	// The math in the output doesn't seem to add up...
	for(int k = 0; k < 3; k++) {
	System.out.println("");
	System.out.println(p1.getMoney());
	System.out.println(p2.getMoney());
	System.out.println("");
	gameHandController.play(game);
	}
	System.out.println("");
	System.out.println(p1.getMoney());
	System.out.println(p2.getMoney());
	System.out.println("");
	
}
}

