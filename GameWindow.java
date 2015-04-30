/**
  *CS 110 Final Project
   *This class supplies getters for producing
   *various buttons common to most game
   *windows
   *@author Emilie Dzwonar
*/

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;


public class GameWindow extends JFrame
{
   private JButton quitButton, playButton;

      /**
          This method creates a play button, registers
          an event with the button to generate a new
          player screen where a player can enter his/her name,
          and returns this button
      */
      protected JButton getPlayButton()
      {
         // create play button
         playButton = new JButton("Play Game");
         
         // style play button
         playButton.setFont(new Font("SansSerif", Font.BOLD, 20));
         
          // Register event listeners with play button
          playButton.addActionListener(new PlayButtonListener());
          
          //return play button
          return playButton;
     }
          
   /**
      This method creates a quit button, registers an
      event to quit the game when the button is clicked,
      and returns this button
   */
   protected JButton getQuitButton()
   {
      // create quit button
      quitButton = new JButton("Quit");
      
      // style quit button
      quitButton.setFont(new Font("SansSerif", Font.BOLD, 20));
      
      // register event with quit button
      quitButton.addActionListener(new QuitGame());
      
      // return quit button
      return quitButton;
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