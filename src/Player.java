
public class Player {
	private String name;
	
	
	public Player(String name, int chips) {
		super();
		this.name = name;
		this.chips = chips;
	}
	public int turnScore;
	public int roundScore;
	public int chips;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
