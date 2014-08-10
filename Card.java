
public class Card {

	private int id;
	private int value;
	private int suit;
	
	public Card(int id) {
		this.id = id;
		value = id % 13;
		suit = id / 13;
	}
	
	public String getCardInfo() {
		StringBuilder sb = new StringBuilder();
		switch(value) {
			case 0: sb.append("Two of ");   break;
			case 1: sb.append("Three of "); break;
			case 2: sb.append("Four of ");  break;
			case 3: sb.append("Five of ");  break;
			case 4: sb.append("Six of ");   break;
			case 5: sb.append("Seven of "); break;
			case 6: sb.append("Eight of "); break;
			case 7: sb.append("Nine of ");  break;
			case 8: sb.append("Ten of ");   break;
			case 9: sb.append("Jack of ");  break;
			case 10: sb.append("Queen of "); break;
			case 11: sb.append("King of ");  break;
			case 12: sb.append("Ace of ");   break;
		}
		switch(suit) {
			case 0:  sb.append("Clubs");   break;
			case 1:  sb.append("Diamonds"); break;
			case 2:  sb.append("Hearts");  break;
			case 3:  sb.append("Spades");  break;
		}
		return sb.toString();
	}
	
	public int getId() {
		return id;
	}
	public int getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}
}
