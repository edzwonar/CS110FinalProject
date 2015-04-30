/** 
  *CS 110 Final Project
   *Create Player Screen
   *
   * This class displays a window that prompts
   *the player for his/her name and then provides
   *a button to begin
  * @author Emilie Dzwonar
*/
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;


public class CreatePlayer extends GameWindow
{
      private JLabel enterPlayer;
      private JTextField nameField;
      private JPanel label;
      private JPanel name;
      private JPanel Button;
      private JPanel quitPanel;
      private JButton beginButton, quitButton;
      final int WINDOW_WIDTH = 330;
      final int WINDOW_HEIGHT = 150;

      /**
         constructor
      */
      public CreatePlayer()
      {
         setTitle("New Player");
         
          // Set the size of the window.
         setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
         
         // Specify what happens when the close button is clicked
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         // Create a BorderLayout manager
         setLayout(new GridLayout(3,1));
         
         // set text for entering name
         enterPlayer = new JLabel("Enter player name:");
         enterPlayer.setFont(new Font("SansSerif", Font.BOLD, 20));
         
         // create panel for text
         label = new JPanel();
         // make background white
         label.setBackground(Color.WHITE);
         
         // add text to label
         label.add(enterPlayer);
         
         // create text field for user input
         nameField = new JTextField("Player 1");
         nameField.setFont(new Font("SansSerif", Font.BOLD, 20));
         
         // add input field to label panel
         label.add(nameField);
         
         // create namePanel 
         name = new JPanel();
         
         // set background white
         name.setBackground(Color.WHITE);
         
         // create button for submitting name/ start game
         beginButton = new JButton("Begin");
         beginButton.setFont(new Font("SansSerif", Font.BOLD, 20));
         
         // register event listener with begin button
         beginButton.addActionListener(new BeginButtonListener());
         
       // add begin button to name panel
       name.add(beginButton);
       
       // create panel for quit button
        quitPanel = new JPanel();
        
        // make background white
        quitPanel.setBackground(Color.WHITE);
        
        // create quit button
        quitButton = getQuitButton();
        quitPanel.add(quitButton);
        
         // add panels to content pane
         add(label);
         add(name);
         add(quitPanel);
         
         // display the window
         setVisible(true);
       
      }
      
      /**
         begin button event handler
      */
      private class BeginButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            new PlayScreen(nameField.getText());
            setVisible(false);
         }
      }


}