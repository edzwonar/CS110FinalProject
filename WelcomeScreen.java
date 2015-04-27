/*
   Emilie Dzwonar
   CS 110 Final Project
   War Game Welcome Screen
*/

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class WelcomeScreen extends  JFrame
{
      private JPanel imgPanel;
      private JPanel playPanel;
      private JPanel rulesPanel;
      private JLabel welcomeMessage;
      private JButton play;
      private JButton rules;
      final int WINDOW_WIDTH = 605;
      final int WINDOW_HEIGHT = 215;
      
      /**
         constructor
      */
      
      public WelcomeScreen()
      {
         // Set the title
         setTitle("War Game");
         
         // Set the size of the window.
         setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
         
         // Specify what happens when the close button is clicked
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         // Create a BorderLayout manager
         setLayout(new GridLayout(1,1));
         
         // set welcome message
         welcomeMessage = new JLabel("Welcome to War!");
         welcomeMessage.setFont(new Font("SansSerif", Font.BOLD, 26));
         
         // create panel for welcome message
      //   imgPanel = new JPanel();
         
         // add welcome message to imgPanel
   //      imgPanel.add(welcomeMessage);
         
         // create play button
         play = new JButton("Play Game");
         
         play.setFont(new Font("SansSerif", Font.BOLD, 30));
         
          // Register event listeners with play button
          play.addActionListener(new PlayButtonListener());
         
         // create panel for play button
   //      playPanel = new JPanel();
         
         // add play button to playPanel
   //      playPanel.add(play);
         
         // create rules button
         rules = new JButton("Rules");
         rules.setFont(new Font("SansSerif", Font.BOLD, 30));
         
          // Register event listeners with play button
          play.addActionListener(new RulesButtonListener());
         
         // create panel for rules button
      //   rulesPanel = new JPanel();
         
         // add rules button to rulesPanel
   //      playPanel.add(rules);
         
  //content       // add panels to content pane
         add(welcomeMessage);
         add(play);
         add(rules);
        // add(rulesPanel, BorderLayout.CENTER);
         
         // Display the window.
         setVisible(true);
      }
      
      /**
         play button event handler
      */
      private class PlayButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            new CreatePlayer();
            setVisible(false);
          }
      }
      
       /**
         rules button event handler
      */
      private class RulesButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            
          }
      }
}