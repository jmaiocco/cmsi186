/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Joe Maiocco
 *  Date          :  2018-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
    if ( nSides < MINIMUM_SIDES ) {
      throw new IllegalArgumentException();
    }
    this.sides = nSides;
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
    pips = (int) Math.ceil( sides * Math.random() );
    return pips;
   }

  /**
   *  Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
    return pips;
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return int  The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int sides ) {
    if ( sides < MINIMUM_SIDES ) {
      throw new IllegalArgumentException();
    }
    this.sides = sides;
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
    return "[" + pips + "]";
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      return d.toString();
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println( "Hello world from the Die class..." );
      Die d = null;
      try { d = new Die( 1 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      try { d = new Die( 2 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      try { d = new Die( 3 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      try { d = new Die( 4 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      try { d = new Die( 5 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      try { d = new Die( 6 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      try { d = new Die( 10 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      try { d = new Die( 12 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      try { d = new Die( 24 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      System.out.println( "Testing for proper return values from roll() method for a die with " + d.sides + " sides.");
      System.out.println( "First roll: " + d.roll() );
      System.out.println( "Second roll: " + d.roll() );
      System.out.println( "Third roll: " + d.roll() );
      System.out.println( "Fourth roll: " + d.roll() );
      System.out.println( "Fifth roll: " + d.roll() );
      System.out.println( "Sixth roll: " + d.roll() );
      System.out.println( "Testing for proper return values from toString() method" );
      System.out.println( "The value of the last roll was " + d.toString() );
      System.out.println( "Testing for proper results from setSides() method" );
      try { d.setSides( 1 ); }
      catch ( IllegalArgumentException iae ) { System.out.println( "That die does not have enough sides!" ); }
      d.setSides( 6 );
      System.out.println( "New number of sides is: " + d.sides );
      d.setSides( 7 );
      System.out.println( "New number of sides is: " + d.sides );
      d.setSides( 8 );
      System.out.println( "New number of sides is: " + d.sides );

   }
}
