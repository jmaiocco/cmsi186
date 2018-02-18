/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Joe Maiocco
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;
   private int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
    if ( count < 1 || sides < MINIMUM_SIDES) {
      throw new IllegalArgumentException();
    }
    this.count = count;
    this.sides = sides;
    ds = new Die[ count ];
    for ( int i = 0; i < count; i ++ ) {
      ds[ i ] = new Die( sides );
    }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
    int summedRolls = 0;
    for ( Die d: ds ) {
      summedRolls += d.getValue();
    }     
    return summedRolls;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
    for ( Die d: ds ) {
      d.roll();
    }
   }


  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
    if ( dieIndex > ds.length ) {
      throw new IllegalArgumentException();
    }
    return ds[ dieIndex ].roll();
  }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @throws IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
    if ( dieIndex > ds.length ) {
      throw new IllegalArgumentException();
    }
      return ds[ dieIndex ].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String result = "";
      for ( int i = 0; i < ds.length; i++ ) {
        result = result + "" + ds[ i ];
      }
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

   
  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet dsInput ) {
      if ( ds.length != dsInput.count ) {
        return false;
      }
      for ( int i = 0; i < ds.length; i++) {
        if ( ds[ i ].getValue() != dsInput.getIndividual( i ) ) {
          return false;
        }
      }
      return true;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
     System.out.println( "Hello world from the DiceSet class..." );
     DiceSet ds = null;
     try { ds = new DiceSet( 0, 4 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of dice!" ); }
     try { ds = new DiceSet( 1, 3 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of sides!" ); }
     try { ds = new DiceSet( 1, 4 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of dice or sides!" ); }
     System.out.println( "Testing for proper return values from  roll() method with " + ds.count + " dice of " + ds.sides + " sides each." );
     ds.roll();
     System.out.println( "First roll: " + ds );
     ds.roll();
     System.out.println( "Second roll: " + ds );
     ds.roll();
     System.out.println( "Third roll: " + ds );
     System.out.println( "Testing for proper return values from toString() method" );
     System.out.println( "The current set of dice is " + ds.toString() );
     System.out.println( "Testing for proper return values from sum() method" );
     System.out.println( "The total of the current dice roll is " + ds.sum() );
     System.out.println( "Testing for proper return values from rollIndividual() method" );
     ds.rollIndividual( 0 );
     System.out.println( "The new roll at index 0 is " + ds.getIndividual( 0 ) );
     System.out.println( "Testing for proper return values from isIdentical() method" );
     ///isIdentical tests here
     try { ds = new DiceSet( 4, 6 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of dice or sides!" ); }
     System.out.println( "Testing for proper return values from  roll() method with " + ds.count + " dice of " + ds.sides + " sides each." );
     ds.roll();
     System.out.println( "First roll: " + ds );
     ds.roll();
     System.out.println( "Second roll: " + ds );
     ds.roll();
     System.out.println( "Third roll: " + ds );
     System.out.println( "Testing for proper return values from toString() method" );
     System.out.println( "The current set of dice is " + ds.toString() );
     System.out.println( "Testing for proper return values from sum() method" );
     System.out.println( "The total of the current dice roll is " + ds.sum() );
     System.out.println( "Testing for proper return values from rollIndividual() method" );
     ds.rollIndividual( 3 );
     System.out.println( "The new roll at index 3 is " + ds.getIndividual( 3 ) );
     System.out.println( "Testing for proper return values from isIdentical() method" );
     ///isIdentical tests here
     try { ds = new DiceSet( 6, 10 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of dice or sides!" ); }
     System.out.println( "Testing for proper return values from  roll() method with " + ds.count + " dice of " + ds.sides + " sides each." );
     ds.roll();
     System.out.println( "First roll: " + ds );
     ds.roll();
     System.out.println( "Second roll: " + ds );
     ds.roll();
     System.out.println( "Third roll: " + ds );
     System.out.println( "Testing for proper return values from toString() method" );
     System.out.println( "The current set of dice is " + ds.toString() );
     System.out.println( "Testing for proper return values from sum() method" );
     System.out.println( "The total of the current dice roll is " + ds.sum() );
     System.out.println( "Testing for proper return values from rollIndividual() method" );
     ds.rollIndividual( 5 );
     System.out.println( "The new roll at index 5 is " + ds.getIndividual( 5 ) );
     System.out.println( "Testing for proper return values from isIdentical() method" );
     ///isIdentical tests here
     try { ds = new DiceSet( 50, 100 ); }
     catch ( IllegalArgumentException iae ) { System.out.println( "That's an invalid number of dice or sides!" ); }
     System.out.println( "Testing for proper return values from  roll() method with " + ds.count + " dice of " + ds.sides + " sides each." );
     ds.roll();
     System.out.println( "First roll: " + ds );
     ds.roll();
     System.out.println( "Second roll: " + ds );
     ds.roll();
     System.out.println( "Third roll: " + ds );
     System.out.println( "Testing for proper return values from toString() method" );
     System.out.println( "The current set of dice is " + ds.toString() );
     System.out.println( "Testing for proper return values from sum() method" );
     System.out.println( "The total of the current dice roll is " + ds.sum() );
     System.out.println( "Testing for proper return values from rollIndividual() method" );
     ds.rollIndividual( 33 );
     System.out.println( "The new roll at index 33 is " + ds.getIndividual( 33 ) );
   }

}
