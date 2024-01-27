
public enum Rank {
	TWO(2,"2","2"),
	THREE(3,"3","3"),
	FOUR(4,"4","4"),
	FIVE(5,"5","5"),
	SIX(6,"6","6"),
	SEVEN(7,"7","7"),
	EIGHT(8,"8","8"),
	NINE(9,"9","9"),
	TEN(10,"10","T"),
	JACK(11,"Jack","J"),
	QUEEN(12,"Queen","Q"),
	KING(13,"King","K"),
	ACE(14,"Ace","A");
	
	private final int rankValue;
	private final String rankOutName;
	private final String rankName;
	
	/**
	 * constructor which pass the string to rank's field
	 * @param rankValue
	 * @param rankOutName
	 * @param rankName
	 */
	private Rank(int rankValue, String rankOutName, String rankName){
		this.rankValue = rankValue;
		this.rankOutName = rankOutName;
		this.rankName = rankName;
	}

	/**
	 * get the integer value of each rank
	 * @return integer
	 */
	public int getValue(){
		return this.rankValue;
	}
	
	/**
	 * get the legal output name (according to the this project's specification)
	 * @return String object
	 */
	public String getOutName(){
		return this.rankOutName;
	}
	
	/**
	 * get the name which user inputed as rank on the command line
	 * @return String object
	 */
	public String getName(){
		return this.rankName;
	}
	

	/**
	 * function pass a string variable, for each rank r in the array of ranks, 
	 * we check case insensitively whether string has corresponding one in the ranks, 
	 * if it has, we return the rank, else we return null
	 * @param text
	 * @return Rank object
	 */
	public static Rank caseInsensitive(String text){
		for (Rank r : Rank.values()){
			if (r.getName().equalsIgnoreCase(text)){
				return r;
			}
		}
		return null;
	}
	
}
