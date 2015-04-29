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
      private JPanel compStatsPanel, centerPanel, midCenterPanel, playerPanel;
      private JPanel computerPanel, quitPanel, newGamePanel, playerStatsPanel, sidePanel1, sidePanel2;
      private JLabel compStatsTxt, playerStatsTxt, cCardFront, cCardFront2, pCardFront;
      private JLabel pCardFront2, compPickCard, compPickCard2, pickCard2, winnerMsg;
      private ImageIcon smallBack, cardBack, smallFront, cardFaceImg;
      private JButton pickCard, quit, newGame;
      final int WINDOW_WIDTH = 800;
      final int WINDOW_HEIGHT = 615;
      private int compNum = 26;
      private int playNum = 26;
      private QueueReferenceBased compPile, playerPile;
      private ArrayList<Card> wonCards = new ArrayList<>();
      private Card playerCard = new Card(0,0), compCard = new Card(0,0);

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
          sidePanel2 = new JPanel(new GridLayout(3,1));
                  
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
           cardBack = new ImageIcon("cardpics//back.jpg");
           pickCard = new JButton(cardBack);
           
           // register event with pickCard button
           pickCard.addActionListener(new NextRound());
           
           // create quit button
           quit = new JButton("Quit");
           
           // register event with quit button
           quit.addActionListener(new QuitGame());
           
           // create panel for quit button
           quitPanel = new JPanel();
           
           // add quit button to panel
           quitPanel.add(quit);
           
           // create new game button
           newGame = new JButton("New Game");
           
           // register event with new game button
           newGame.addActionListener(new CreateGame());
           
           // create panel for new game button
           newGamePanel = new JPanel();
           
           // add new game button to panel
           newGamePanel.add(newGame);
           
           // add new game panel to sidepanel2
           sidePanel2.add(newGamePanel);
           
           // add quit panel  to sidepanel2
           sidePanel2.add(quitPanel);
           
           //make card back image smaller for computer
           Image img = cardBack.getImage();
           Image img2 = img.getScaledInstance(125, 160,  java.awt.Image.SCALE_SMOOTH);
           smallBack = new ImageIcon(img2);
         
            // add card back img for computer
            compPickCard = new JLabel(smallBack); 
           
           // create center panel to hold comp and player cards and middle text
           centerPanel = new JPanel(new GridLayout(3,1));
           
           // create top center panel for comp cards
           computerPanel = new JPanel(new FlowLayout());
           
           // create middle center panel for text
           midCenterPanel = new JPanel(new FlowLayout());
           
           // add blank winner message to midCenter panel
          winnerMsg = new JLabel("");
          midCenterPanel.add(winnerMsg);
           
           // create bottom center panel for player cards
           playerPanel = new JPanel(new FlowLayout());
           
           // add comp card back to top center panel
           computerPanel.add(compPickCard);
           
           // add player pickCard button to bottom center panel
           playerPanel.add(pickCard);
           
           // create labels for card front and add to computerPanel and playerPanel
           cCardFront = new JLabel();
           computerPanel.add(cCardFront);
           pCardFront = new JLabel();
           playerPanel.add(pCardFront);
           
           // create labels for additional card fronts (will need on war) and add to panels
           cCardFront2 = new JLabel();
           compPickCard2 = new JLabel();
           
           computerPanel.add(compPickCard2);
           computerPanel.add(cCardFront2);
           
           pCardFront2 = new JLabel();
           pickCard2 = new JLabel();
           
           playerPanel.add(pickCard2);
           playerPanel.add(pCardFront2);
           
           // add player and comp card panels, and middle to center panel
           centerPanel.add(computerPanel);
           centerPanel.add(midCenterPanel);
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
      private class NextRound implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
                 // hide war message and winner message
                  sidePanel1.setVisible(false);
                          
              // if preceded by war
              if (wonCards.size() > 0)
              {
           
                // remove face down cards from queues, add to array
                 removeCards();
                 wonCards.add(compCard);
                 wonCards.add(playerCard);
                 
                  // add card back image
                  compPickCard2.setIcon(smallBack);
                  pickCard2.setIcon(cardBack);    
                  
                // remove face up cards from queues
                 removeCards();
                 wonCards.add(compCard);
                 wonCards.add(playerCard);
                     
               // place card front images in next position
                 cCardFront2.setIcon(smallFront);
                 pCardFront2.setIcon(cardFaceImg);  
              }
              
               else
                {
                  // remove cards from queues
                    removeCards(); 
                 
                  // compare cards
                    if (playerCard.equals(compCard))
                    {    
                        // clear winner message
                        winnerMsg.setText("");
                        
                        // make war message visible
                        sidePanel1.setVisible(true);
                        
                        // set face up images
                        pCardFront.setIcon(cardFaceImg);
                        cCardFront.setIcon(smallFront);
                        
                        //  add cards to empty array list
                        wonCards.add(playerCard);
                        wonCards.add(compCard);
                        
                     }
                     else
                    {
                    
                      // place card images in same position
                       cCardFront.setIcon(smallFront);
                       pCardFront.setIcon(cardFaceImg);
                       
                       // clear extra card images
                       cCardFront2.setIcon(null);
                       pCardFront2.setIcon(null);
                       compPickCard2.setIcon(null);
                       pickCard2.setIcon(null);
                       
                          // declare string variable for winner text
                         String winner = "";
                       
                       if (playerCard.getRank() > compCard.getRank())
                       {
                             // assign winner message to winner String
                              winner = playerName + " wins round!";
                              
                              // add current cards to player's queue
                              playerPile.enqueue(compCard);
                              playerPile.enqueue(playerCard);
                              
                              // add all (if any) cards in war-generated arraylist to player's queue
                              for (Card card: wonCards)
                              {
                                 System.out.println(card);
                                 playerPile.enqueue(card);
                              }
                              
                             // update card counts
                            playNum = playNum + wonCards.size()/2 + 1;
                            compNum = compNum - wonCards.size()/2 - 1;
                         }
                     
                         else if (playerCard.getRank() < compCard.getRank())
                        {
                                 // assign winner message to winner string
                                 winner = "Computer wins round!";
                                 
                                 // add current card's to computer's queue
                                 compPile.enqueue(compCard);
                                 compPile.enqueue(playerCard);
                                 
                                 // add all (if any) cards in war-generated arraylist to player's queue
                                 for (Card card: wonCards)
                                 {
                                    System.out.println(card);
                                    compPile.enqueue(card);
                                 }
                                 
                                   // update card counts
                                     playNum = playNum - wonCards.size()/2 - 1;
                                    compNum = compNum + wonCards.size()/2 + 1;
                  
                                 // if either card count dips below zero, reassign to zero
                        /*         if (compNum < 0)
                                    compNum = 0;
                                 if (playNum < 0)
                                    playNum = 0;   */
                           }
               

                         // set winner message
                         winnerMsg.setText(winner);
                         
                          // empty arraylist of wonCards
                          wonCards = new ArrayList<>();   
                 }
            }
                 
                 setVisible(true);
                        
           
            // reset remaining cards counts
          compStatsTxt.setText("Computer's Remaining Cards: " + compNum);
          playerStatsTxt .setText(playerName + "'s Remaining Cards: " + playNum);    
         }
         
       }
         
         private void removeCards()
       {
          // remove card from player's pile
          playerCard = (Card)playerPile.dequeue();
    //      System.out.println(playerCard + " " + playerCard.getRank());
                 
         // remove card from computer's pile
          compCard = (Card)compPile.dequeue();
   //        System.out.println(compCard + " " + compCard.getRank());
           
          // get image urls for computer and player cards
         String pImgURL = "cardpics//" + playerCard.getRank() + (String)(playerCard.getSuit().charAt(0) + ".jpg");
         String cImgURL = "cardpics//" + compCard.getRank() + (String)(compCard.getSuit().charAt(0) + ".jpg");
 
          // create image icons for computer and player cards
           cardFaceImg = new ImageIcon(pImgURL);
           ImageIcon compCardFaceImg = new ImageIcon(cImgURL);
                 
           //make card image smaller for computer
           Image img = compCardFaceImg.getImage();
           Image img2 = img.getScaledInstance(125, 160,  java.awt.Image.SCALE_SMOOTH);
           smallFront = new ImageIcon(img2);
           
           // if either pile is empty, end game
            if (compPile.isEmpty() || playerPile.isEmpty())
               {
                  pickCard.setEnabled(false);
                  pickCard.setIcon(null);
                  compPickCard.setIcon(null);
                  
                  if (compNum == 0)
                      winnerMsg.setText(playerName + " loses the game!");
                 if (playNum == 0)
                     winnerMsg.setText(playerName + "wins the game!");
         }

     }
      
     /**
         new game button event handler
      */
      private class CreateGame implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            new CreatePlayer();
            setVisible(false);
         }
      }
      
      /**
         quit button event handler
      */
      private class QuitGame implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }

}