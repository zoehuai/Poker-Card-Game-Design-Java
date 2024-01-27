/**
 * Purpose: This is the simpler version of the Poker project, this program
 * implements an application that displays a poker hand's category and output
 * the correct detail of the poker hand.
 */

import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This method implements the whole function to display the detail category of
 * the poker hand, its includes one main method, cardNum method and two enum
 * method to judge the validity or output correct classification detail.
 */
public class Poker {
    //The input is used to transfer the input from cmd to a string array.
    private static String[] input = new String[5];
    //This is global variable and char type to use in main method.
    private static char[] rankSuit;

    /**
     * The main method is to judge the correctness of the input length and makes
     * use of inValid method form Rank and Suit method to judge the correctness
     * of the input ranks and suits. CardNum method to count the number of the
     * same rank cards to classify the correct category.
     */
    public static void main(String[] args) {
        //Instantiate two array.
        int[] toIntRank = new int[5];
        int[] toIntSuit = new int[5];
        //Judging whether the input length is over 5.
        if (args.length > 5) {
            System.out.println("NOT UNDERTAKEN");
            System.exit(1);
        }
        //Judging the input length if it is a wrong number.
        if (args.length != 5) {
            System.out.println("Error: wrong number of arguments; must be" +
                    " a multiple of 5");
            System.exit(1);
        }
        for (int i = 0; i < args.length; i++) {
            //Change input to uppercase for unify the format to judge simpler.
            input[i] = args[i].toUpperCase();
            //Judging whether the length of each args[i] is 2.
            if (input[i].length() != 2) {
                System.exit(1);
            }
            //change the input to char type and the rank represent in char type,
            //in this way, it is simpler to correspond.
            rankSuit = input[i].toCharArray();
            //rankSuit is a char array, [0]is the first char and represent the
            // rank of the card,[1]is the second char and represent the suit of
            // the card, put the first char and int i to the Rank method to
            // judge whether it is valid, and the return i.getRanks() could
            // be a int array and the values is like"10","11","12".
            toIntRank[i] = Rank.inValid(rankSuit[0], i);
            //put the second char and int i to the Suit method to
            //judge whether it is valid, and the return i.getSuits() could
            //be a int array and the values is the "1""2""3""4",which could
            //represent four suits.
            toIntSuit[i] = Suit.inValid(rankSuit[1], i);
        }
        //use CardNum method to use int type data, the suit is represent by "1"
        // "2""3""4", so int is correspond the Suit method.
        System.out.print("Player 1: ");
        CardNum(toIntRank, toIntSuit);
    }

    /**
     * This method used for list the whole ranks without repeat and count the
     * number of the different ranks to find input classification.
     */
    public static void CardNum(int[] toIntRank, int[] toIntSuit) {
        //This count array is contains all the uncommon elements,such as
        // (2,2,3,3,5) will be(2,3,5).
        ArrayList<Integer> count = new ArrayList<>();
        //count the num of those elements, such as (2,2,3,3,5)will be(2,2,1).
        ArrayList<Integer> num = new ArrayList<>();
        //sort the array from smaller number to large number.
        Arrays.sort(toIntRank);
        //add the first element of the int array to this count array.
        count.add(toIntRank[0]);
        //add one for the first count array.
        num.add(1);
        int j = 0;
        for (int i = 1; i < toIntRank.length; i++) {
            //if count array have the same element of the Int array,num array
            //add one.
            if (toIntRank[i] == count.get(j)) {
                num.set(j, num.get(j) + 1);
                //if count array do not have the element in the Int array, add
                //this element to count array.
            } else if (toIntRank[i] != count.get(j)) {
                num.add(1);
                count.add(toIntRank[i]);
                //add j means to get all the uncommon elements ,which is j.
                j++;
            }
        }
        //sort the num array to be an ascending array.
        Collections.sort(num);
        //int[] ary = {1, 4};
        //int[] ary2 = {2, 3};
        //int[] ary3 = {1, 2, 2};
        //int[] ary4 = {1, 1, 3};
        //int[] ary5 = {1, 1, 1, 2};
        //int[] ary6 = {1, 1, 1, 1, 1};
        //this above array {1, 4} equals to the num array's format, and when num
        //array's second elements is 4, it must be four ranks and one other,
        //for a sorted array,the third elements of Int array must is four ranks.
        if (num.get(1) == 4) {
            System.out.println("Four " + Rank.getName(toIntRank[2]) + "s");
            System.exit(0);
        }
        //this above array {2, 3} equals to the num array's format, and when the
        //second elements is 3, it must be full house.
        if (num.get(1) == 3) {
            //fullHouse need to find which is three and which is pair, and for
            //sorted array the middle element must be the three ranks, so find
            //the other element and it must be the pair,so the fullHouse is the
            //pair one and it is a string so initialize it as a string.
            String fullHouse = "0";
            for (int k = 0; k < count.size(); k++) {
                if (toIntRank[2] != count.get(k)) {
                    fullHouse = Rank.getName(count.get(k));
                }
            }
            System.out.println(Rank.getName(toIntRank[2])
                    + "s full of " + fullHouse + "s");
            System.exit(0);
        }
        //this above array {1, 2, 2} equals to the num array's format, and when
        //the third elements of the array is 2, it must be two pair.
        //for a sorted array the second and fourth elements of Int array, it
        //must be two pair and the fourth one is larger than the second.
        if (num.get(2) == 2) {
            System.out.println(Rank.getName(toIntRank[3]) + "s over "
                    + Rank.getName(toIntRank[1]) + "s");
            System.exit(0);
        }
        //this above array{1, 1, 3} equals to the num array's format, and the
        //third elements of the num array is 3, it must be the three ranks.
        //for the sorted Int array, the third elements must is the three ranks.
        if (num.get(2) == 3) {
            System.out.println("Three " + Rank.getName(toIntRank[2]) + "s");
            System.exit(0);
        }
        //this above array{1, 1, 1, 2} equals to the num array's format,
        //the fourth elements of num array, it must be a pair.
        //to find a pair, find the Int array's same element.
        if (num.get(3) == 2) {
            String pair = "0";
            for (int k = 0; k < toIntRank.length - 1; k++) {
                if (toIntRank[k] == toIntRank[k + 1]) {
                    pair = Rank.getName(toIntRank[k]);
                }
            }
            System.out.println("Pair of " + pair + "s");
            System.exit(0);
        }
        int countSuit = 0;
        //this above array{1, 1, 1, 1, 1} equals to the num array's format, and
        //the fifth elements of num array is 1, it must be all different element.
        if (num.get(4) == 1) {
            //count the suits whether the hand have all the same suits.
            for (int m = 1; m < toIntRank.length; m++) {
                if (toIntSuit[0] == toIntSuit[m])
                    countSuit++;
            }
            //for all the unique number, if [5]-[4]=4, means this is a straight.
            if (toIntRank[4] - toIntRank[0] == 4) {
                if (countSuit == 4) {
                    System.out.println(Rank.getName(toIntRank[4])
                            + "-high straight flush");
                    System.exit(0);
                } else {
                    System.out.println(Rank.getName(toIntRank[4])
                            + "-high straight");
                    System.exit(0);
                }
            }
            //judge whether it is a high flush.
            if (countSuit == 4) {
                System.out.println(Rank.getName(toIntRank[4])
                        + "-high flush");
                System.exit(0);
            }
        }
        System.out.println(Rank.getName(toIntRank[4]) + "-high");
    }

    /*This method use ENUM to classify thirteen ranks and verify whether the
     *input ranks from cmd is valid.
     */
    enum Rank {
        TWO('2', 2), THREE('3', 3), FOUR('4', 4),
        FIVE('5', 5), SIX('6', 6), SEVEN('7', 7),
        EIGHT('8', 8), NINE('9', 9), TEN('T', 10),
        JACK('J', 11), QUEEN('Q', 12),
        KING('K', 13), ACE('A', 14);
        char rank;
        int r;

        /*Rank is the constructor of this Enum method.*/
        Rank(char rank, int r) {
            this.rank = rank;
            this.r = r;
        }

        /*This method is to get the int r, which represented by int number.*/
        public int getRanks() {
            return r;
        }

        /*This method is to get the char type rank, which represented by char.*/
        public char getRank() {
            return rank;
        }

        /*Because some number such as A,J,Q,K need to transfer to the string,
          this method is useful to transfer them to output them as string.*/
        public static String getName(int number) {

            switch (number) {
                case 14:
                    return "Ace";
                case 13:
                    return "King";
                case 12:
                    return "Queen";
                case 11:
                    return "Jack";
                default:
                    return String.valueOf(number);
            }
        }

        /*This method is to test whether it is valid of the ranks output.*/
        public static int inValid(char rank, int index) {
            for (Rank i : Rank.values()) {
                if (rank == i.getRank()) {
                    return i.getRanks();
                }
            }
            //input[index] is from the method that use inValid method to find
            //the invalid elements, and print it out.
            System.out.println("Error: invalid card name '" +input[index] +"'");
            System.exit(1);
            return 0;
        }
    }

    /**
     * This method use ENUM to classify four suits and verify whether the input
     * suits from cmd is valid.
     */
    enum Suit {
        Clubs('C', 1), Diamonds('D', 2), Hearts('H', 3),
        Spades('S', 4);
        char suit;
        int s;

        /*Suit is the constructor of this Enum method.*/
        Suit(char suit, int s) {
            this.suit = suit;
            this.s = s;
        }

        /*This method is to get the int suit, which represented by int number.*/
        public int getSuits() {
            return s;
        }

        /*This method is to get the char type suit, which represented by char.*/
        public char getSuit() {
            return suit;
        }

        /*This method is to test whether it is valid of the suits output.*/
        public static int inValid(char suit, int index2) {
            for (Suit j : Suit.values()) {
                if (suit == j.getSuit()) {
                    return j.getSuits();
                }
            }
            //input[index2] is from the method that use inValid method to find
            //the invalid elements, and print it out.
            System.out.println("Error: invalid card name '"+input[index2] + "'");
            System.exit(1);
            return 0;
        }
    }
}

