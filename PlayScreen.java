/**
   *CS 110 Final Project
   *War Game Play Screen
   *
  * this class displays the main game
  * play screen and provides the methods
  * for the function of the game
  *@author Emilie Dzwonar
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
public class PlayScreen extends GameWindow
{
      private String playerName;
      private JPanel compStatsPanel, playerStatsPanel, playerPanel, cardsPanel;
      private JPanel computerPanel, quitPanel, newGamePanel, sidePanel2;
      private JLabel compStatsTxt, playerStatsTxt, cCardFront, cCardFront2, pCardFront;
      private JLabel pCardFront2, compPickCard, compPickCard2, pickCard2, winnerMsg, warMsg;
      private ImageIcon smallBack, cardBack, smallFront, cardFaceImg, warIcon;
      private JButton pickCard, quitButton, newGame;
      final int WINDOW_WIDTH = 850, WINDOW_HEIGHT = 615;
      private int compNum = 26, playNum = 26;
      private QueueReferenceBased compPile, playerPile;
      private ArrayList<Card> wonCards = new ArrayList<>();
      private Card playerCard = new Card(0,0), compCard = new Card(0,0);
      boolean war = false;

   /**
      constructor accepts player's name, builds
      the main frame for the game and supplies
      a button for the player to remove the next
      card from their pile
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
         
         // build card image panel
         buildCardPanel();
         
         // build side panel
         buildSidePanel();
         
         // build stats panels
         buildStatsPanels();
         
           // deal cards
          dealCards();
                  
           // set visibility
           setVisible(true);
        
   }
   
   /**
      This method builds the panel that contains
      the card images for the game
   */
   private void buildCardPanel()
   {
        // create button for top of players card queue
           cardBack = new ImageIcon("cardpics//back.jpg");
           pickCard = new JButton(cardBack);
           
        //make card back image smaller for computer
           Image img = cardBack.getImage();
           Image img2 = img.getScaledInstance(125, 160,  java.awt.Image.SCALE_SMOOTH);
           smallBack = new ImageIcon(img2);
           
           // register event with pickCard button
           pickCard.addActionListener(new NextRound());
           
           // create card back label for computer
            compPickCard = new JLabel(smallBack); 
           
           // create panel to hold comp and player cards
           cardsPanel = new JPanel(new GridLayout(2,0));
           
           // set background color
           cardsPanel.setBackground(Color.WHITE);
           
           // create top panel for comp cards
           computerPanel = new JPanel();
           computerPanel.add(compPickCard);
           
           // set background color
           computerPanel.setBackground(Color.WHITE);
           
           // create bottom panel to hold player cards   
           playerPanel = new JPanel();
           
           // set background
           playerPanel.setBackground(Color.WHITE);
               
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
           
           // add player and comp card panels to cards panel
          cardsPanel.add(computerPanel);
           cardsPanel.add(playerPanel);
               
               
           // add cards panel to content pane
           add(cardsPanel, BorderLayout.CENTER);
   }
   
      /**
         this method builds a side panel to hold
         winner and war messages as well as buttons
      */
      private void buildSidePanel()
      {
            // create side panel
            sidePanel2 = new JPanel();
            
            // set background color
            sidePanel2.setBackground(Color.WHITE);
            
           // create icon for war message
           ImageIcon warIcon = new ImageIcon("cardpics//war.jpg");
           
            
            // create panel for buttons (quit, new game)
            JPanel buttonPanel = new JPanel(new GridLayout(4,1));
            
            // set background color
            buttonPanel.setBackground(Color.WHITE);
            
           // create new game button (begin button)
           newGame = getPlayButton();
           newGame.setText("New Game");
           
           // create panel for new game button
           newGamePanel = new JPanel();
           
           // set background
           newGamePanel.setBackground(Color.WHITE);
           
           // add new game button to panel
           newGamePanel.add(newGame);
            
            // add newGamePanel to buttonsPanel
            buttonPanel.add(newGamePanel);
                     
            // create quit button
           quitButton = getQuitButton();
                
           // register event with quit button
           quitButton.addActionListener(new QuitGame());
           
           // create panel for quit button
           quitPanel = new JPanel();
           
           // set background color
            quitPanel.setBackground(Color.WHITE);

             // add quit button to panel
             quitPanel.add(quitButton);
            
            // add quitPanel to buttonPanel
            buttonPanel.add(quitPanel);
            
             // create blank winner message to side panel
              winnerMsg = new JLabel("");
             winnerMsg.setFont(new Font("SansSerif", Font.BOLD, 20));
            
            // add winner message to buttonPanel
            buttonPanel.add(winnerMsg);
            
            // create war message label
            warMsg = new JLabel();
            
            // add war message label to buttonPanel
            buttonPanel.add(warMsg);
            
            // add all panels to sidePanel2
     //       sidePanel2.add(messagePanel);
            sidePanel2.add(buttonPanel);
            
            // add sidePanel2 to content pane
            add(sidePanel2, BorderLayout.EAST);
            
      }
      
      /**
         this method creates panels to hold the
         number of remaining cards in both piles
      */
     private void buildStatsPanels()
     {
          // create panel for computer stats
         compStatsPanel = new JPanel();
         
         // set background
         compStatsPanel.setBackground(Color.WHITE);
        
                  
         // create label for computer
         compStatsTxt = new JLabel("Computer's Remaining Cards: " + compNum);
         compStatsTxt.setFont(new Font("SansSerif", Font.BOLD, 20));
         compStatsPanel.add(compStatsTxt);
         
         // create panel for player stats
         playerStatsPanel = new JPanel();
         // set background
         playerStatsPanel.setBackground(Color.WHITE);
         
         // create label for player
         playerStatsTxt = new JLabel(playerName + "'s Remaining Cards: " + playNum);
         playerStatsTxt.setFont(new Font("SansSerif", Font.BOLD, 20));
         playerStatsPanel.add(playerStatsTxt);
        
        // add panels to content pane
        add(compStatsPanel, BorderLayout.NORTH);
        add(playerStatsPanel, BorderLayout.SOUTH);
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
                  // remove next card from both piles
                  removeCards();
                          
              // if preceded by war
              if (wonCards.size() > 0)
              {
                   handleWar();
              }
              // if nothing in array, then clear extra panels
              else
              {
                         // clear extra panels
                       clearPanels();
              }
              
            
                  // compare cards
                    if (playerCard.equals(compCard))
                    {
                         if (war)
                         {
                              handleWar();
                        }
                        
                         else
                         {
                              // set war boolean to true
                              war = true; 
                                
                              // clear winner message and set war message
                              winnerMsg.setText("        WAR!");
                              warMsg.setIcon(warIcon);
                              
                              // get face up  images
                              getImages();
                              
                              // set face up images
                              pCardFront.setIcon(cardFaceImg);
                              cCardFront.setIcon(smallFront);
                              
                              //  add cards to empty array list
                              wonCards.add(playerCard);
                              wonCards.add(compCard);
                        }
                  }      
                     
                     if (!playerCard.equals(compCard))
                    {
                    
                       // get card images
                       getImages();
                       
                          // declare string variable for winner text
                         String winner = "";
                         
                         // clear extra card images
                                if (!war)
                               {
                                  // place card images in same position
                                 cCardFront.setIcon(smallFront);
                                 pCardFront.setIcon(cardFaceImg);
                                }
                                
                                // set war boolean to false
                                war = false; 
                      
                     if (playerCard.getRank() > compCard.getRank())
                       {   
                           
                             // assign winner message to winner String
                              winner = "<html>     " +playerName + "<br> wins round!</html>";
                                                        
                          // if array list has items
                          if (wonCards.size() > 0)    
                          {
                               // add all cards in war-generated arraylist to player's queue
                              for (Card card: wonCards)
                              {
                                 playerPile.enqueue(card);
                              }
                         }
                         // if not
                         else
                         {
                               // add current cards to player's queue
                              playerPile.enqueue(compCard);
                              playerPile.enqueue(playerCard);

                         }
                              
                              // update card counts
                              playNum = playNum + wonCards.size()/2 + 1;
                              compNum = compNum - wonCards.size()/2 - 1;
                            
                              // empty arraylist of wonCards
                              wonCards = new ArrayList<>();  
                          
                         }
                     
                         else if (playerCard.getRank() < compCard.getRank())
                        {
                                 // assign winner message to winner string
                                 winner = "<html>       Computer <br>wins round!</html>";
                                
                                 // if array list has items
                                if (wonCards.size() > 0)    
                                {
                                     // add all cards in war-generated arraylist to player's queue
                                    for (Card card: wonCards)
                                    {
                                       compPile.enqueue(card);
                                    }
                               }
                               // if not
                               else
                               {
                                     // add current cards to player's queue
                                    compPile.enqueue(compCard);
                                    compPile.enqueue(playerCard);
      
                               }
                                       
                                 // update card counts
                                 playNum = playNum - wonCards.size()/2 - 1;
                                 compNum = compNum + wonCards.size()/2 + 1;
                                    
                                 // empty arraylist of wonCards
                                wonCards = new ArrayList<>();  
                  
                       }
                        
                        // if either card count dips below zero, reassign to zero
                         if (compNum < 0)
                              compNum = 0;
                         if (playNum < 0)
                             playNum = 0;   

                         // set winner message
                          winnerMsg.setText(winner);
                          
                 }
                                 
            // reset remaining cards counts
          compStatsTxt.setText("Computer's Remaining Cards: " + compNum);
          playerStatsTxt .setText(playerName + "'s Remaining Cards: " + playNum);
            
            setVisible(true);
         }
        
       }
         
         /**
            This method removes the next card from the top
            of both the computer's and player's piles
         */
         private void removeCards()
       {
       
             // if either pile is empty, end game
                  if (compPile.isEmpty() || playerPile.isEmpty())
                  {
                    // disable pick card  button
                     pickCard.setEnabled(false);
                     
                     // clear pick card images
                     pickCard.setIcon(null);
                     compPickCard.setIcon(null);
                     
                     // display winner message
                     if (compPile.isEmpty())
                     {
                         winnerMsg.setText("<html>" + playerName + "<br> loses the game!</html>");
                     }
                    if (playerPile.isEmpty())
                    {
                        winnerMsg.setText("<html>" +playerName + "<br>wins the game!</html>");
                    }
                }
   
                   // remove card from player's pile
                   playerCard = (Card)playerPile.dequeue();
                   
                  // remove card from computer's pile
                   compCard = (Card)compPile.dequeue();
            
     }
      
      /**
         This method gets the images for the cards 
         from the computer's and player's piles and
         assigns them to the image icon variables
      */
      private void getImages()
      {
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

      }
      
      /**
         This method is used to clear extra panels
         (previously holding card images) 
         when they are not needed
      */
      private void clearPanels()
      {
             cCardFront2.setIcon(null);
             pCardFront2.setIcon(null);
             compPickCard2.setIcon(null);
             pickCard2.setIcon(null);    
         
      }
      
      /**
         this method removes the next
         cards and displays them when
         a war has occurred
      */
      private void handleWar()
      {
            // add face down cards to array (will have been next removed cards above)
                 wonCards.add(compCard);
                 wonCards.add(playerCard);
                 
                  // add card back image
                  compPickCard2.setIcon(smallBack);
                  pickCard2.setIcon(cardBack);    
                  
                // remove face up cards from queues
                 removeCards();
                 getImages();
                 
                 wonCards.add(compCard);
                 wonCards.add(playerCard);
                     
               // place card front images in next position
                 cCardFront2.setIcon(smallFront);
                 pCardFront2.setIcon(cardFaceImg);  

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
 
