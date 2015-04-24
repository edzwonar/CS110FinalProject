/*
   Emilie Dzwonar
   CS 110 Final Project
   War Game Card Class
*/

import javax.swing.*;
import java.awt.*; 

public class CardDeck
{
   public static void main(String[] args)
   {
      final int WINDOW_WIDTH = 350;
      final int WINDOW_HEIGHT = 250;
      
      // Create a window.
      JFrame window = new JFrame();
      
      // Set the title
      window.setTitle("A Simple Wish");
      
      // Set the size of the window.
      window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      // Specify what happens when the close button is clicked
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // Display the window.
      window.setVisible(true);
   }
}