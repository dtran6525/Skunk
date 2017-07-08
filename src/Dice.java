import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dice {
	
	public static final int DIE_NUM = 2;
	
	public List<Die> dice = new ArrayList<>();
	public Dice() {
		for (int i = 0 ; i < DIE_NUM; i++) {
			dice.add(new Die());
		}
	}
	
	public Roll roll() {
		int d1Roll = dice.get(0).roll();
		int d2Roll = dice.get(1).roll();
		System.out.println("Player rolled a " + d1Roll + " and a " + d2Roll);
		if (d1Roll == Die.SKUNK && d2Roll == Die.SKUNK) {
			return new Roll(false,false,true,0, 4);
		} else if ( (d1Roll == Die.SKUNK && d2Roll == Die.DEUCE) ||
					(d1Roll == Die.DEUCE && d2Roll == Die.SKUNK)) {
			return new Roll(false,true,false,0, 2);
		} else if ( (d1Roll == Die.SKUNK && d2Roll != Die.SKUNK) || 
					(d1Roll != Die.SKUNK && d2Roll == Die.SKUNK)) {
			return new Roll(true,false,false,0, 1);
		}
		return new Roll(false,false,false, d1Roll + d2Roll, 0);
		
	}
	
}
