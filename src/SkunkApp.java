import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SkunkApp {
	public static final int STARTING_CHIPS = 100;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of players");
		List<Player> players = new ArrayList<>();
		Kitty kitty = new Kitty();
		int numberOfPlayers = sc.nextInt();
		// initialization
		Dice dice = new Dice();
		
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.println("Enter player " + i + "'s name:");
			players.add(new Player(sc.next(), STARTING_CHIPS));
		}
		// begin game
		while (!checkScore(players)) {
			for (Player p : players) {
				System.out.println(p.getName() + "'s turn:");
				System.out.println("Current round score: " + p.roundScore);
				System.out.println("Current chips: " + p.chips);
				boolean pass = false;
				while(!pass) {
					//begin turn
					System.out.println("Current turn score " + p.turnScore);
					Roll roll = dice.roll();
					
					applyPenalty(p, kitty, roll);
					updateTurnScore(p, roll);
					if (roll.isTwoSkunk || roll.isSkunk || roll.isSkunkAndDeuce ) {
						pass = true;
					} else {
						System.out.println("Would you like to pass ? (y/n)");
						pass = sc.next().equals("y");
					}
				}
				System.out.println("******************");
				p.roundScore += p.turnScore;
				p.turnScore = 0;
			}
		}
		
		//determine winner
		
		Comparator<Player> scoreComp = (p1,p2) -> {return -1*(p1.roundScore - p2.roundScore);};
		Collections.sort(players, scoreComp);
		
		Player winner =  players.get(0);
	
		winner.chips += kitty.chips;
		kitty.chips = 0;
		System.out.println("Winner : " + winner.getName());
		System.out.println("Winner's round score: " + winner.roundScore);
		System.out.println("Winner's chips: " + winner.chips);
		
		// end game
		sc.close();
	}
	
	public static boolean checkScore(List<Player> players) {
		for (Player p : players) {
			if (p.roundScore >= 100) return true;
		}
		return false;
	}
	
	public static void applyPenalty(Player p, Kitty k, Roll r) {
		 p.chips -= r.penalty;
		 k.chips += r.penalty;
		 if (r.isTwoSkunk) {
			 p.turnScore = 0;
			 p.roundScore = 0;
		 } else if (r.isSkunk || r.isSkunkAndDeuce) {
			 p.turnScore = 0;
		 } 
	}
	public static void updateTurnScore(Player p, Roll r) {
		p.turnScore += r.score;
	}
}
