/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a class to play the HighRoll game 
 *  @author       :  Joe Maiocco
 *  Date          :  2018-02-18
 *  Description   :  The class runs a game where a user tries to roll the highest possible value of a set of dice.
                     The program accepts arguments on the command line to intialize the game, then provides the 
                     following options:
                     1) Allows the user to roll all the dice.
                     2) Allows the user to roll a single die.
                     3) Allows the user to sum up all the dice values.
                     4) Allows the user to save his/her high score.
                     5) Allows the user to view his/her high score.
                     6) Allows the user to quit the game.                
 *  Notes         :  Utilizes both Die.java and DiceSet.java to run.
 *  Warnings      :  None
 *  Exceptions    :  IOException if user input is not applicable to the game options.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {
  
    public static void optionsDisplay() {
      System.out.println( "   Menu:" );
      System.out.println( "     Press the 'r' key to roll all your dice.");
      System.out.println( "     Press the 's' key to roll a single die." );
      System.out.println( "     Press the 'c' key to calculate your dice score total.");
      System.out.println( "     Press the 'h' key to save your score as the high score.");
      System.out.println( "     Press the 'd' key to display the current high score.");
      System.out.println( "     Press the 'q' key to quit the program." );
    }

    public static void spacesAfterMethod() {
      System.out.println( "" );
      System.out.println( "" );
    }

    public static void main( String args[] ) {
      int count = Integer.parseInt(args[0]);
      int sides = Integer.parseInt(args[1]);
      int highScore = 0;
      int currentDiceTotal = 0;
      DiceSet currentDiceSet = new DiceSet( count, sides );
      System.out.println( "\n   Welcome to HighRoll!!\n" );
      HighRoll.optionsDisplay();
  
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
               System.out.println("You didn't enter any text! Please do so here:");
            }
            System.out.println( "   You typed: " + inputLine );
            if( 'r' == inputLine.charAt(0) ) {
              currentDiceSet.roll();
              System.out.println( "You rolled " + currentDiceSet.toString() + "." );
            }
            if( 's' == inputLine.charAt(0) ) {
               System.out.println( "Enter the location number of the die you would like to roll: " );
               inputLine = input.readLine();
               int dieIndex = Integer.parseInt( inputLine ) - 1;
               currentDiceSet.rollIndividual( dieIndex );
               System.out.println( "You rolled a " + currentDiceSet.getIndividual( dieIndex ) + "." );
            }
            if( 'c' == inputLine.charAt(0) ) {
               currentDiceTotal = currentDiceSet.sum();
               System.out.println( "Your total score is " + currentDiceTotal + "." );
               if ( currentDiceTotal == ( count * sides ) ) {
                 System.out.println( "Wow, you rolled a perfect score!" );
               }
            }
            if( 'h' == inputLine.charAt(0) ) {
               highScore = currentDiceTotal;
               System.out.println( "Your high score has been saved." );
            }
            if( 'd' == inputLine.charAt(0) ) {
               System.out.println( "The current high score is " + highScore + ".");
            }
            if( 'q' == inputLine.charAt(0) ) {
               System.out.println( "Thanks for playing! See you again soon!" );
               break;
            }
            HighRoll.spacesAfterMethod();
            HighRoll.optionsDisplay();
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
        }
    }
}
