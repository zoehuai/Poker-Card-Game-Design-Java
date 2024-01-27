
public enum Suit {
	C("Clubs"),
	D("Diamonds"),
	H("Hearts"),
	S("Spades");
	
	private final String suitName;
	
	/**
	 * constructor which pass the string to suit's field
	 * @param suitName
	 */
	private Suit(String suitName){
		this.suitName = suitName;
	}
	
	/**
	 * get the string name of each suit
	 * @return String object
	 */
	public String getName(){
		return this.suitName;
	}
	

	/**
	 * function pass a string variable, for each suit s in the array of suits, 
	 * we check case insensitively whether string has corresponding one in the suits, 
	 * if it has, we return the suit, else we return null
	 * @param text
	 * @return Suit object
	 */
	public static Suit caseInsensitive(String text){
		for (Suit s : Suit.values()){
			if (s.toString().equalsIgnoreCase(text)){
				return s;
			}
		}
		return null;
	}
	
}
