/* Emilie Dzwonar
     CS 110 Final Project
     Card
*/

/*
   the Card class represents a single
   playing card from a standard 52
   card deck
*/

public class Card
{
   // declare variables
   public static final int SPADES = 1, CLUBS = 2, HEARTS = 3, DIAMONDS = 4, ACE = 1, JACK = 11, QUEEN = 12, KING = 13;
   private int rank, suit;
   private String suitName;
   
   /* this constructor accepts a suit and a rank
   as integers and assigns their values to the
   suit and rank fields, respectively
   @param rank the playing card's rank
   @param suit the playing card's suit
   */
   
   public Card(int rank, int suit)
   {
   this.rank = rank;
   this.suit = suit;
   
   }
   
   /* the getSuit method
      returns the suit field's
      value
      @return the value in the suit field
   */
   
   public String getSuit()
   {  
      return toString();
   }
   
   /*
      the getRank method returns
      the rank field's value
      @return the value in the rank field
   */
   
   public int getRank()
   {
      return rank;
   }
   
   /*
      the toString method
      converts the integer value
      in the suit field to the
      name of the suit and returns
      this String value
      @return the name of the suit 
   */
    
    public String toString()
    { 
      switch (suit)
      {
         case SPADES:
            suitName = "spades";
            break;
         case CLUBS:
            suitName = "clubs";
            break;
         case HEARTS:
            suitName = "hearts";
            break;
         case DIAMONDS:
            suitName = "diamonds";
            break;
       }
            
      return (suitName);
    }
    
    /*
      the equals method checks if the calling card
      object has the same rank as the
      passing card object
      @param Card another card object
      @return true or false
    */
    
    public boolean equals(Card otherCard)
    {
      // declare boolean variable
      boolean status;
      
      // if the rank in both card objects is equal, assign true to status
      if (rank == otherCard.getRank())
      {
         status = true;
      }
      // if the rank in both card objects is not equal, assign false to status
      else
      {
         status = false;
      }
      
      // return the value in status variable
      return status;
    }
    
    
}