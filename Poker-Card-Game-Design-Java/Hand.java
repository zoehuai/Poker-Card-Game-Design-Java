
import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	//the hand is collection of individual card, therefore representing as a arraylist
	ArrayList<Card> hand = new ArrayList<Card>();
	
	//the ranks is collection of individual rank value, therefore representing as a arraylist
	ArrayList<Rank> ranks = new ArrayList<Rank>();
	
	/**
	 * constructor take a string array as input
	 * @param text
	 */
	public Hand(String[] text){
		//throw error message when input is not greater than 0 and input is not multiple of 5
		if (text.length < 1 || text.length % 5 != 0){
			System.out.println("Error: wrong number of arguments; must be a multiple of 5");
			System.exit(1);
		}
		//prevent entering challenging version
		else if (text.length > 5){
			System.out.println("NOT UNDERTAKEN");
			System.exit(1);
		}

		/**
		 * if the input is good to go, pass the string array to a for each loop
		 * add cards to hand and add rank of each card to an arraylist of rank type
		 */
		else {
			for (String s : text){
				Card c = new Card(s);
				hand.add(c);
				ranks.add(Rank.caseInsensitive(s.substring(0,1)));
			}
		}
	}
	
	/**
	 * sort arraylist by value of ranks in ascending order
	 * here using external library "collection" in order to efficiently sort the arraylist of rank
	 * @param none
	 * @return ArrayList<Rank>
	 */
	public ArrayList<Rank> sortRank(){
		Collections.sort(ranks);
		return ranks;
	}
	
	/**
	 * function return boolean value to determine whether the sorted rank of hand is straights 
	 * i.e. each rank is in consecutive order
	 * @param none
	 * @return boolean value
	 * 
	 */
	public boolean isStraights(){
		for (int i=0; i<ranks.size()-1;i++){
			if ((ranks.get(i).getValue() + 1 == ranks.get(i+1).getValue())){
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	

	/**
	 * function return boolean value to determine whether the sorted rank of hand is flushes that each suit is identical
	 * @param none
	 * @return boolean
	 */
	public boolean isFlushes(){	
		for (int i=0; i<hand.size()-1; i++){
			if (hand.get(i).getSuit().equals(hand.get(i+1).getSuit())){
				continue;
			}
			else{
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * 	the function return an integer array
	 * the function took two integer index number which marks the start and end location of arraylist <rank>
	 * loc[0] is the start index and loc[1] is the end index of the first "n of a kind" combination of one hand
	 * loc[2] is the start index and loc[3] is the end index of the second "n of a kind" combination of one hand
	 * @param start
	 * @param end
	 * @return integer array
	 */
	public int[] N_of_a_Kind(int start, int end){
		//the integer array of location index which has size of 4
		int[] loc = new int[4];
		//boolean variable used as lock to decide which indices combinations we should filled
		boolean first_is_not_full = true;
		//initialize all location index to the start index which is 0 in this case
		loc[0] = start;
		loc[1] = start;
		loc[2] = start;
		loc[3] = start;
		for(int i = start;i<end;i++){
			//if rank[i] is identical to rank[i+1]
			if(ranks.get(i).compareTo(ranks.get(i+1))==0){
				//here we need to fill the first location indices combination
				if (first_is_not_full){
					loc[1] = i + 1;
				}
				//after we have the first location indices filled, we need to fill the second indices combination
				else {
					loc[3] = i + 1;
				}
			}
			//if rank[i] is not identical to rank[i+1]
			else{
				
				/**
				 *  if the first indices combination point to the same element, i.e. there is none identical rank sequence yet
				 *  we point loc[0] and loc[1] to the next available rank
				 */
				if(loc[1]-loc[0] == 0){
					loc[0] = i + 1;
					loc[1] = loc[0];
				}	
				 /**
				  * if the first indices combination not point to the same element, i.e. there is some identical rank sequence 
				  * and also the second indices combination is empty 
				  * we point loc[2] and loc[3] to the next available rank
				  */
				else if ((loc[1]-loc[0] != 0) && (loc[3]-loc[2] == 0)){
					first_is_not_full = false;
					loc[2] = i + 1;
					loc[3] = loc[2];
				}
			}
		}
		return loc;
	}
	
	/**
	 * evaluate the hand of cards
	 * @param start
	 * @param end
	 * @return none
	 */
	public void evaluation(int start, int end){
		//Straight flush
		if (isStraights() && isFlushes()){
			System.out.println("Player 1: " + ranks.get(ranks.size()-1).getOutName() + "-high straight flush");
		}
		//Four of a kind
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==3){
			System.out.println("Player 1: " + "Four "+ ranks.get(N_of_a_Kind(start,end)[1]).getOutName() + "s");
		}
		//Full house
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==2 && N_of_a_Kind(start,end)[3]-N_of_a_Kind(start,end)[2]==1){
			System.out.println("Player 1: " + ranks.get(N_of_a_Kind(start,end)[1]).getOutName() +"s " + "full of " + ranks.get(N_of_a_Kind(start,end)[3]).getOutName() + "s");
		}
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==1 && N_of_a_Kind(start,end)[3]-N_of_a_Kind(start,end)[2]==2){
			System.out.println("Player 1: " + ranks.get(N_of_a_Kind(start,end)[3]).getOutName() +"s " + "full of " + ranks.get(N_of_a_Kind(start,end)[1]).getOutName() + "s");
		}
		//Flush
		else if(isFlushes()){
			System.out.println("Player 1: " + ranks.get(ranks.size()-1).getOutName() + "-high flush");
		}
		//Straight
		else if(isStraights()){
			System.out.println("Player 1: " + ranks.get(ranks.size()-1).getOutName() + "-high straight");
		}
		//Three of a kind
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==2){
			System.out.println("Player 1: " + "Three "+ ranks.get(N_of_a_Kind(start,end)[1]).getOutName() + "s");
		}
		//Two pair
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==1 && N_of_a_Kind(start,end)[3]-N_of_a_Kind(start,end)[2]==1){
			System.out.println("Player 1: " + ranks.get(N_of_a_Kind(start,end)[3]).getOutName() + "s" + " over " + ranks.get(N_of_a_Kind(start,end)[1]).getOutName() +"s");
		}
		//One pair
		else if(N_of_a_Kind(start,end)[1]-N_of_a_Kind(start,end)[0]==1){
			System.out.println("Player 1: " + "Pair of " + ranks.get(N_of_a_Kind(start,end)[1]).getOutName() +"s");
		}
		//High card
		else {
			System.out.println("Player 1: " + ranks.get(ranks.size()-1).getOutName() + "-high");
		}
	}

}
