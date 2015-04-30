/**
  *CS 110 Final Project
  *War Game Welcome Screen
  *
  * This class supplies a welcome screen
  * for the card game War
  *@author Emilie Dzwonar
*/

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class WelcomeScreen extends  GameWindow
{
      private JPanel imgPanel;
      private JPanel playPanel;
      private JPanel rulesPanel;
      private JPanel buttonPanel;
      private JLabel welcomeMessage;
      private JButton playButton, rules;
      final int WINDOW_WIDTH = 605;
      final int WINDOW_HEIGHT = 315;
      
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
         setLayout(new GridLayout(2,1));
         
         // create button panel
         buttonPanel = new JPanel(new GridLayout(2,1));
         
         // create welcome image panel
         imgPanel = new JPanel();
         
         ImageIcon welcomeImg= new ImageIcon("cardpics//welcome.jpg");
         welcomeMessage = new JLabel(welcomeImg);
         
         imgPanel.add(welcomeMessage);
          
         // create play button
         playButton = getPlayButton();
         
         // create panel for play button
         playPanel = new JPanel();
         playPanel.add(playButton);
         // set backround to white
         playPanel.setBackground(Color.WHITE);
         
         // add play button panel to button panel
         buttonPanel.add(playPanel);
         
          // create rules button
         rules = new JButton("Rules");
         rules.setFont(new Font("SansSerif", Font.BOLD, 20));
         
          // Register event listeners with rules button
          rules.addActionListener(new RulesButtonListener());
         
         // create panel for rules button
         rulesPanel = new JPanel();
         
         // add rules button to rulesPanel
         rulesPanel.add(rules);
         
          // set backround to white
         rulesPanel.setBackground(Color.WHITE);
         
         // add rules panel to button panel
         buttonPanel.add(rulesPanel);
         
         // make background white
         imgPanel.setBackground(Color.WHITE);
         buttonPanel.setBackground(Color.WHITE);
         
       // add panels to content pane
         add(imgPanel);
         add(buttonPanel);
         
         // Display the window.
         setVisible(true);
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