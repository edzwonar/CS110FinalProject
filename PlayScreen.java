/**
   Emilie Dzwonar
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
      private String playerName;
      private JPanel compStatsPanel, centerPanel, playerPanel, computerPanel;
      private JPanel playerStatsPanel, sidePanel1, sidePanel2;
      private JLabel compStatsTxt, playerStatsTxt, cCardFront, pCardFront, compPickCard, winnerMsg;
      private JButton pickCard;
      final int WINDOW_WIDTH = 600;
      final int WINDOW_HEIGHT = 515;
      private int compNum = 26;
      private int playNum = 26;
      private QueueReferenceBased compPile, playerPile;

   /**
      constructor accepts player's name, sets
      the JFrame window and calls methods
      for playing the game
      @parameter playerName a string containing
      the player's name
   */
   public PlayScreen(String playerName)
   {
         // assign player's name to variable
         this.playerName = playerName;
         
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
         
         // create side panels
          sidePanel1 = new JPanel();
          sidePanel2 = new JPanel();
         
         // add blank winner message to sidePanel2
         winnerMsg = new JLabel("");
         sidePanel2.add(winnerMsg);
         
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
           
           // register event with pickCard button
           pickCard.addActionListener(new nextRound());
           
           //make card back image smaller for computer
           Image img = cardBack.getImage();
           Image img2 = img.getScaledInstance(125, 160,  java.awt.Image.SCALE_SMOOTH);
           ImageIcon smallBack = new ImageIcon(img2);
         
            // add card back img for computer
            compPickCard = new JLabel(smallBack); 
           
           // create center panel to hold comp and player cards
           centerPanel = new JPanel(new GridLayout(2,1));
           
           // create top center panel for comp cards
           computerPanel = new JPanel(new GridLayout(1,3));
           
           // create bottom center panel for player cards
           playerPanel = new JPanel(new GridLayout(1,3));
           
           // add comp card back to top center panel
           computerPanel.add(compPickCard);
           
           // add player pickCard button to bottom center panel
           playerPanel.add(pickCard);
           
           // create labels for card front and add to computerPanel and playerPanel
           cCardFront = new JLabel();
           computerPanel.add(cCardFront);
           pCardFront = new JLabel();
           playerPanel.add(pCardFront);
           
           // add player and comp card panels to center panel
           centerPanel.add(computerPanel);
           centerPanel.add(playerPanel);
           
           // add center panel to content pane
           add(centerPanel, BorderLayout.CENTER);
           
          //hide war message
           sidePanel1.setVisible(false);
                  
           // add war message but keep hidden
          JLabel warMessage = new JLabel("War!");
           warMessage.setFont(new Font("SansSerif", Font.BOLD, 30));
                     
          // add label to side panel
          sidePanel1.add(warMessage);
                     
           // add sidePanel to content pane
           add(sidePanel1, BorderLayout.WEST);
           add(sidePanel2, BorderLayout.EAST);

           
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
      compPile = new QueueReferenceBased();
      playerPile = new QueueReferenceBased();
      
      // deal cards into 2 queues with 26 cards each
      while (!deck.isEmpty())
      { 
         //System.out.println(deck.dealCard());
           compPile.enqueue(deck.dealCard());
           playerPile.enqueue(deck.dealCard());
      }
   }
   
       /**
         pickCard button event handler
      */
      private class nextRound implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
                 // hide war message and winner message
                  sidePanel1.setVisible(false);
         //        sidePanel2.setVisible(false);
                  
                  // remove card from player's pile
                  Card playerCard = (Card)playerPile.dequeue();
                  
                  // remove card from computer's pile
                  Card compCard = (Card)compPile.dequeue();
                 
                 // get image urls for computer and player cards
                String pImgURL = "cardpics//" + playerCard.getRank() + (String)(playerCard.getSuit().charAt(0) + ".jpg");
                String cImgURL = "cardpics//" + compCard.getRank() + (String)(compCard.getSuit().charAt(0) + ".jpg");
 
                 // create image icons for computer and player cards
                 ImageIcon cardFaceImg = new ImageIcon(pImgURL);
                 ImageIcon compCardFaceImg = new ImageIcon(cImgURL);
                 
                //make card image smaller for computer
                Image img = compCardFaceImg.getImage();
               Image img2 = img.getScaledInstance(125, 160,  java.awt.Image.SCALE_SMOOTH);
               ImageIcon smallFront = new ImageIcon(img2);
               
                 cCardFront.setIcon(smallFront);
                 pCardFront.setIcon(cardFaceImg);
                 
          //       centerPanel.add(computerPanel);
          //       centerPanel.add(playerPanel);
                 
                 // add center panel to center of window
          //       add(centerPanel, BorderLayout.CENTER);   
                 
                 setVisible(true);
                 
                 // compare cards
                 if (playerCard.equals(compCard))
                 {    
                     // make war message visible
                     sidePanel1.setVisible(true);
                     
                     // add card back image and button to comp and player panels, respectively
                     computerPanel.add(compPickCard);
                     playerPanel.add(pickCard);
                     
                     centerPanel.add(computerPanel);
                     centerPanel.add(playerPanel);
                     
                     add(centerPanel, BorderLayout.CENTER);
                     
                 }
                 
               else if (playerCard.getRank() > compCard.getRank())
                 {
                  String winner = playerName + " wins round!";
                  winnerMsg.setText(winner);
                  playerPile.enqueue(compCard);
                  playerPile.enqueue(playerCard);
                  playNum++;
                  compNum--;
                 }
                 
               else if (playerCard.getRank() < compCard.getRank())
               {
                  String winner = "Computer wins round!";
                  winnerMsg.setText(winner);
                  compPile.enqueue(compCard);
                  playerPile.enqueue(playerCard);
                  compNum++;
                  playNum--;
               }
               
                 // add(sidePanel2, BorderLayout.EAST);
               //   sidePanel2.setVisible(true);
               
               // reset remaining cards counts
               compStatsTxt.setText("Computer's Remaining Cards: " + compNum);
               playerStatsTxt .setText(playerName + "'s Remaining Cards: " + playNum);             
         }
     }
   
}