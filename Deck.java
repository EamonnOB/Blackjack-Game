import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();
	private int limit = 52;
	
	public Deck() {
		Card temp;
		for(int i=0; i<52; i++) {
			temp = new Card(i);
			deck.add(temp);
		}
	}
	
	public void reshuffle() {
		deck = new ArrayList<Card>();
		limit = 52;
		Card temp;
		for(int i=0; i<52; i++) {
			temp = new Card(i);
			deck.add(temp);
		}
	}
	
	public Card getCard() {
		Random r = new Random();
		int pos = r.nextInt(limit);
		Card temp = deck.get(pos);
		deck.remove(pos);
		limit--;
		return temp;
	}
}
