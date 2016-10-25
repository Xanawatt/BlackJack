import java.util.*;
public class Hand extends Vector{
	private Vector hand;
	public Hand(){
		hand = new Vector();
	}
	public void removeCards(){
		hand.removeAllElements();
	}
	public void addCardToHand(){
		hand.addElement(Main.playingCards[Main.CC]);
		Main.CC++;
	}
	public void removeCardFromHand(String card){
		hand.removeElement(card);
	}
	public String getCard(int position){
		return (String)hand.elementAt(position);

	}
	public void print(){
		Enumeration en = hand.elements();
		while(en.hasMoreElements() == true){
			System.out.println(en.nextElement());
		}
	}
	public int Value(){
		int handValue = 0;
		String card;
		boolean isAce = false;
		int cardValue = 0;
		Enumeration en = hand.elements();
		for(int i = 0; i < hand.size(); i++){

			card = (String) en.nextElement();
			if(card.contains("Ace")){
				handValue += 1;
				cardValue = 1;
				isAce = true;
			}
			else if(card.contains("Jack") || card.contains("Queen") || card.contains("King")){
				cardValue = 10;
				handValue += 10;
			}
			else if(card.contains("2 ")){
				cardValue = 2;
				handValue += 2;
			}
			else if(card.contains("3 ")){
				cardValue = 3;
				handValue += 3;
			}
			else if(card.contains("4 ")){
				cardValue = 4;
				handValue += 4;
			}
			else if(card.contains("5 ")){
				cardValue = 5;
				handValue += 5;
			}
			else if(card.contains("6 ")){
				cardValue = 6;
				handValue += 6;
			}
			else if(card.contains("7 ")){
				cardValue = 7;
				handValue += 7;
			}
			else if(card.contains("8 ")){
				cardValue = 8;
				handValue += 8;
			}
			else if(card.contains("9 ")){
				cardValue = 9;
				handValue += 9;
			}
			else if(card.contains("10 ")){
				cardValue = 10;
				handValue += 10;
			}
		}
		if(isAce == true && handValue + 10 == 21){
			handValue += 10;
		}
		return handValue;
	}
}