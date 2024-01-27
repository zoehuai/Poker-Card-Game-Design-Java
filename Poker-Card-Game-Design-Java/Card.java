
public class Card {
	
	private Rank rank;
	private Suit suit;
	
	/**
	 * constructor would take a string variable as parameter
	 * @param text
	 */
	public Card(String text){
		//matching the substring with each rank and suit enumerate value
		Rank rank = Rank.caseInsensitive(text.substring(0, 1));
		Suit suit = Suit.caseInsensitive(text.substring(1, 2));
		//This part would handle the invalid input exception
		if( rank == null || suit == null){
			System.out.println("Error: invalid card name " + "'" + text.substring(0, 1) + text.substring(1, 2) + "'");
			System.exit(1);
		}
		//if the input has exact case insensitive matching in the enumeration, the card object is available to generate
		else{
			this.rank = rank;
			this.suit = suit;
		}
	}

	/**
	 * get the card's suit name
	 * @return String object
	 */
	public String getSuit(){
		return suit.getName();
	}
	
	/**
	 * modify the way of printing card object to "ranksuit" e.g. 2H
	 * @return String object
	 */
	public String toString(){
		return this.rank + "" + this.suit;
	}
	
}
