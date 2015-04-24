/**
   author: Emilie Dzwonar
   CS 110 Final Project
   War Game Play Screen
*/

import java.util.*;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

/**
   this class displays the main game
   play screen and provides the methods
   for the function of the game
*/
public class PlayScreen extends JFrame
{
      private JPanel compStatsPanel;
      private JPanel cardsPanel;
      private JPanel playerStatsPanel;
      private JLabel compStatsTxt;
      private JLabel playerStatsTxt;
      private JButton pickCard;
      final int WINDOW_WIDTH = 700;
      final int WINDOW_HEIGHT = 315;
      private int compNum = 26;
      private int playNum = 26;

   /**
      constructor accepts player's name, sets
      the JFrame window and calls methods
      for playing the game
      @parameter playerName a string containing
      the player's name
   */
   public PlayScreen(String playerName)
   {
         // set title   
          setTitle("War Game");
         
          // Set the size of the window.
         setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
         
         // Specify what happens when the close button is clicked
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         // Create a BorderLayout manager
         setLayout(new BorderLayout());
         
         // create panel for computer stats
         compStatsPanel = new JPanel();
         
         // create label for computer
         compStatsTxt = new JLabel("Computer's Remaining Cards: " + compNum);
         compStatsPanel.add(compStatsTxt);
         
         // create panel for player stats
         playerStatsPanel = new JPanel();
         
         // create label for player
         playerStatsTxt = new JLabel(playerName + "'s Remaining Cards: " + playNum);
        playerStatsPanel.add(playerStatsTxt);
        
        // add panels to content pane
        add(compStatsPanel, BorderLayout.NORTH);
        add(playerStatsPanel, BorderLayout.SOUTH);
        
        // deal cards
        dealCards();
        
        // create button for top of players card queue
        ImageIcon cardBack = new ImageIcon("cardpics//back.jpg");
        pickCard = new JButton(cardBack);
        
        // add card button to card panel
        cardsPanel = new JPanel();
        cardsPanel.add(pickCard);
        
        // add cardPanel to content pane
        add(cardsPanel, BorderLayout.CENTER);
        
         // set visibility
         setVisible(true);
   }
   
   /**
      this method creates a new deck and 
      deals the deck into two piles, one for the player
      and one for the computer
   */
   private void dealCards()
   {
      // create a new deck of 52 cards
      Deck deck = new Deck();  
      
      // shuffle cards
      deck.shuffle();
      
      // create queues to deal cards to player and computer
      QueueReferenceBased compPile = new QueueReferenceBased();
      QueueReferenceBased playerPile = new QueueReferenceBased();
      
      // deal cards into 2 queues with 26 cards each
      while (deck.cardsRemaining()  > 0)
      {
         compPile.enqueue(deck.dealCard());
         playerPile.enqueue(deck.dealCard());
      }
      
      while (!compPile.isEmpty())
      {
         add(new JLabel(compPile.dequeue().toString()));
 
      }
   }
   
   /**
      this method conducts a round of War,
      comparing the values of the player vs. computer's
      cards upon draw
   */
   private void newRound()
   {
   }
   
}