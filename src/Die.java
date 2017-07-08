import java.util.Random;

public class Die {
	
	public static final int SKUNK = 1;
	public static final int DEUCE = 2;
	
	public int roll() {
		Random r = new Random();
		return r.nextInt(7) + 1 ;
	}
	
	
	
}
