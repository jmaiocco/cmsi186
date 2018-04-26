/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Dynamic programming practice in the form of an optimized change maker
 * @author    :  Joe Maiocco
 * Date       :  2018-04-24
 * Description:  The program takes in a collection of coins and uses a tuple-based table method in order
 *               to calculate the right amount of change using the smallest number of coins.
 * Notes      :  None
 * Warnings   :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {

 /**
  * Singleton constant representing an impossible tuple.
  */
  public static final Tuple IMPOSSIBLE = new Tuple( new int[0] );
  public static Tuple optimalSolution;


 /**
  *  Constructor.
  */
  public DynamicChangeMaker() {
  	super();
  }
  
 /**
  *  Method for checking the given arguments for validity.
  *  @param  denomiations int[]  given coin values to check
  *  @param  amount       int    given target change value to check
  */
  public static Tuple checkForAcceptableData( int[] denominations, int amount ) throws IllegalArgumentException {
    try {
      for ( int i = 0; i < denominations.length; i++ ) {
        if ( denominations[ i ] <= 0 ) { 
          System.out.println( "BAD DATA : One of the given denominations is impossible." );
          throw new IllegalArgumentException();
        }
      }
      if ( amount <= 0 ) {
        System.out.println( "BAD DATA : Your given amount is impossible." );
        throw new IllegalArgumentException();
      }
      for ( int denominationIndex1 = 0; denominationIndex1 < denominations.length; denominationIndex1++ ) {
        for ( int denominationIndex2 = denominationIndex1 + 1; denominationIndex2 < denominations.length; denominationIndex2++ ) {
          if ( denominations[ denominationIndex1 ] == denominations[ denominationIndex2 ] ) {
            System.out.println( "BAD DATA : Duplicate denominations are impossible." );
            throw new IllegalArgumentException();
          }
        }
      }
	}
    catch( IllegalArgumentException iae ) { return IMPOSSIBLE; }
    return new Tuple( denominations.length );
  } 

 /**
  *  Main program for making change with the optimal amount of coins.
  *  @param  denomiations      int[]  given coin values to manage
  *  @param  amount            int    given target change value to achieve
  *  @return  optimalSolution  Tuple  Best solution provided the current denominations and amount.
  */
  public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int amount ) {
  	int row = 0;
  	int column = 0;
  	checkForAcceptableData( denominations, amount );
  	Tuple[][] table = new Tuple[ denominations.length ][ amount + 1 ];

  	///Main algorithm

    for ( row = 0; row < denominations.length; row++ ) {
      for ( column = 0; column < amount + 1; column++ ) {

      	///Special case for the first column

        if ( column == 0 ) {

          table[ row ][ 0 ] = new Tuple( denominations.length );

        } else {

        	///Case where the current denomination cannot be taken out of the given amount

            if ( column < denominations[ row ] ) {

              table[ row ][ column ] = new Tuple( new int[ 0 ] );

              if ( ( column - denominations[ row ] >= 0 ) ) {

                if ( !( table[ row ][ column - denominations[ row ] ].isImpossible() ) ) {

                  table[ row ][ column ] = table[ row ][ column ].add( table[ row ][ column - denominations[ row ] ] );

                }
				if ( row != 0 ) {

				  if ( !( table[ row - 1 ][ column ].isImpossible() ) ) {

                    if ( ( table[ row - 1 ][ column ].total() < table[ row ][ column ].total() ) || ( table[ row ][ column ].isImpossible() ) ) {

              	      table[ row ][ column ] = table[ row - 1 ][ column ];

                    }
                  } 
                }
              }   
            } else {

              ///Case where the current denomination can be taken out of the given amount

			  table[row][column] = new Tuple(denominations.length);
          	  table[ row ][ column ].setElement( row, 1 );

          	  if ( column - denominations[ row ] >= 0 ) {

          	  	if ( table[ row ][ column - denominations[ row ] ].isImpossible() ) {

          	  	  table[ row ][ column ] = new Tuple( new int[ 0 ] );

          	  	} else {

          	  	  table[ row ][ column ] = table[ row ][ column ].add( table[ row ][ column - denominations[ row ] ] );

          	  	}
          	  }
          	  if ( row != 0 ) {

          	    if ( !( table[ row - 1 ][ column ].isImpossible() ) ) {

			      if ( ( table[ row ][ column ].isImpossible() ) || ( table[ row - 1 ][ column ].total() < table[ row ][ column ].total() ) ) {
         
                    table[ row ][ column ] = table[ row - 1 ][ column ];

                  }
                }  
          	  }
            }  	
          }
        }
      }
    System.out.println( table[ row - 1 ][ column - 1 ] );
    return table[ row - 1 ][ column - 1 ];
    }
}
