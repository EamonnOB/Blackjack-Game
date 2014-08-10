import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Blackjack {

	private int credits = 200;
	private int bet = 0;
	private Deck deck1 = new Deck();
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	private ArrayList<Card> dealerHand = new ArrayList<Card>();
	private int playerScore;
	private int dealerScore;
	private int turnCount = 0;
	
	Blackjack() {
		
		String input = "";
		boolean continuePlaying = true;
		JOptionPane.showMessageDialog(null, "Welcome to Blackjack!");
		while(continuePlaying && credits > 0) {
			deck1.reshuffle();
			input = JOptionPane.showInputDialog(null, "You have " + credits + " credits\nDo you want to play or quit?");
			if(input == null)
				continuePlaying = false;
			else {
				input = input.toLowerCase();
				switch(input) {
					case "play": {
						makeBet();
						play(); 
						break;
					}
					case "quit": case "exit": {
						continuePlaying = false;
						JOptionPane.showMessageDialog(null, "\nYou finished with " + credits + " credits");
						break;
					}
					default: JOptionPane.showMessageDialog(null, "Input must be: \"Play\", \"Exit\", or \"Quit\"\n");
				}
			}
		}
	}
	
	public void play() {
	
		playerHand.clear();
		dealerHand.clear();
	
		boolean validInput = false, gameOver = false;
		
		playerHand.add(deck1.getCard());
		playerHand.add(deck1.getCard());
		dealerHand.add(deck1.getCard());
		
		displayTableInfo();
		
		if(playerScore == 21) {
			credits += (bet*2);
			JOptionPane.showMessageDialog(null, "Blackjack!!! You won " + (bet*2));
			validInput = true;
			gameOver = true;
		}
		while(!gameOver) {
				String input = JOptionPane.showInputDialog(null, "Do you want to stand or hit?");
				if(input != null) {
					input = input.toLowerCase();
					if(input.equals("hit")) {
						validInput = true;
						playerHand.add(deck1.getCard());
						displayTableInfo();
						if(playerScore > 21) {
							JOptionPane.showMessageDialog(null, "Bust! You lost " + bet);
							gameOver = true;
						}
					}
					else if(input.equals("stand")) {
						validInput = true;
						while(dealerScore < playerScore) {
							dealerHand.add(deck1.getCard());
							displayTableInfo();
						}
						if(dealerScore == playerScore) {
							credits += bet;
							JOptionPane.showMessageDialog(null, "You didn't win anything but you didn't lose anything either");
							gameOver = true;
						}
						else if(dealerScore <= 21) {
							JOptionPane.showMessageDialog(null, "You lost " + bet);
							gameOver = true;
						}
						else {
							credits += (bet*2);
							JOptionPane.showMessageDialog(null, "Well Done! You won " + (bet*2));
							gameOver = true;
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Input must be either stand or hit");
				}
				else {
					validInput = true;
					gameOver = true;
				}	
		}
	}
	
	public void makeBet() {
		boolean validBet = false;
		String input = "";
		while(!validBet) {
			input = JOptionPane.showInputDialog(null, "You have " + credits + " credits\nHow much do you want to bet?");
			if(input.matches("[0-9]+")) {
				bet = Integer.parseInt(input);
				if(bet <= credits && bet > 0) {
					credits -= bet;
					validBet = true;
				}
				else
					JOptionPane.showMessageDialog(null, "Bet can't be more than your total credits and must be at least one credit");
			}
			else
				JOptionPane.showMessageDialog(null, "Must enter a number");
		}
	}
	
	public int checkValue(Card temp) {
		int id = temp.getValue();
		int value = 0;
		switch(id) {
			case 0: value = 2; break;
			case 1: value = 3; break;
			case 2: value = 4; break;
			case 3: value = 5; break;
			case 4: value = 6; break;
			case 5: value = 7; break;
			case 6: value = 8; break;
			case 7: value = 9; break;
			case 8: case 9: case 10: case 11: {
				value = 10;
				break;
			}
			case 12: value = 11; break;
		}
		return value;
	}
	
	public void displayTableInfo() {
		String pH, pS, dH, dS;
		pH = getHand("player");
		pS = getScore("player");
		dH = getHand("dealer");
		dS = getScore("dealer");
		JOptionPane.showMessageDialog(null, pH + pS + "\n\n" + dH + dS, "Table Information", 1);
	}

	public String getHand(String p) {
		StringBuilder sb = new StringBuilder();
		switch(p) {
			case "player": {
				sb.append("Player Hand:\n");
				for(int i=0; i<playerHand.size(); i++) {
					sb.append(playerHand.get(i).getCardInfo() + "\n");
				}
				break;
			}
			case "dealer": {
				sb.append("Dealer Hand:\n");
				for(int i=0; i<dealerHand.size(); i++) {
					sb.append(dealerHand.get(i).getCardInfo() + "\n");
				}
				break;
			}
		}
		return sb.toString();
	}
	
	public String getScore(String p) {
		StringBuilder sb = new StringBuilder();
		if(p.equals("player")) {
			calculateScore("player");
			sb.append("Player Score: " + playerScore);
		}
		else if(p.equals("dealer")) {
			calculateScore("dealer");
			sb.append("Dealer Score: " + dealerScore);
		}
		return sb.toString();
	}
	
	public void calculateScore(String p) {
		int temp=0, aceCount=0;
		switch(p) {
			case "player": {
				playerScore = 0;
				for(int i=0; i<playerHand.size(); i++) {
					temp = checkValue(playerHand.get(i));
					if(temp == 11)
						aceCount += 1;
					playerScore += temp;
					if(playerScore > 21 && aceCount > 0) {
						for(int k=0; k<aceCount; k++) {
							if(playerScore > 21)
								playerScore -= 10;
						}
					}
				}
				break;
			}
			case "dealer": {
				dealerScore = 0;
				for(int i=0; i<dealerHand.size(); i++) {
					temp = checkValue(dealerHand.get(i));
					if(temp == 11)
						aceCount += 1;
					dealerScore += temp;
					if(dealerScore > 21 && aceCount > 0) {
						for(int k=0; k<aceCount; k++) {
							if(dealerScore > 21)
								dealerScore -= 10;
						}
					}
				}
				break;
			}
		}
	}
}
