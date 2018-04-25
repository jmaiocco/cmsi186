/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  ChangeMaker.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Joe Maiocco
 * Date       :  2018-04-29
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  Use tester in GitHub repo
 * Warnings   :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {

 /**
  * Singleton constant representing an impossible tuple.
  */
  public static final Tuple IMPOSSIBLE = new Tuple( new int[0] );
  
  public static Tuple[][] table;  
  public static Tuple optimalSolution;

  public DynamicChangeMaker() {
  	super();
  }
 
  public static void checkForAcceptableData( int[] denominations, int amount ) throws IllegalArgumentException {
    for ( int i = 0; i < denominations.length; i++ ) {
      if ( denominations[ i ] <= 0 ) { 
        throw new IllegalArgumentException( "One of the given denominations is unacceptable." );
      }
    }
    if ( amount < 0 ) {
      throw new IllegalArgumentException( "Your given amount is unacceptable." );
    }
    for ( int denominationIndex1 = 0; denominationIndex1 < denominations.length - 1; denominationIndex1++ ) {
      for ( int denominationIndex2 = denominationIndex1 + 1; denominationIndex2 < denominations.length; denominationIndex2++ ) {
        if ( denominations[ denominationIndex1 ] == denominations[ denominationIndex2 ] ) {
          throw new IllegalArgumentException( "Duplicate denominations are not allowed." );
        }
      }
    }
  } 

  public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int amount ) {
  	try { checkForAcceptableData( denominations, amount ); }
    catch( IllegalArgumentException iae ) { System.out.println( "Unacceptable denominations or amount provided." ); } 
  	table = new Tuple[ denominations.length ][ amount + 1 ];
  	for ( int row = 0; row < denominations.length; row++ ) {
  	  for ( int column = 0; column < amount; column++ ) {
  	    table[ row ][ column ] = new Tuple( denominations.length );
  	  }
  	}
    for ( int row = 0; row < denominations.length; row++ ) {
      for ( int column = 0; column < amount; column++ ) {
        if ( column == 0 ) {
          table[ row ][ 0 ] = new Tuple( denominations.length );
        } else {
          if ( amount - denominations[ row ] < 0 ) {
            table[ row ][ column ] = IMPOSSIBLE;
            if ( !( table[ row ][ column - denominations[ row ] ].isImpossible() ) ) {
              table[ row ][ column ] = table[ row ][ column - denominations[ row ] ];
            }
            if ( row != 0 ) {
              if ( table[ row - 1 ][ column ].total() < table[ row ][ column ].total() ) {
                table[ row ][ column ] = table[ row - 1 ][ column ];
              } else {
              	continue;
              }
            } else if ( amount - denominations[ row ] >= 0 ) {
          	  table[ row ][ column ] = new Tuple( denominations.length );
          	  table[ row ][ column ].setElement( row, 1 );
          	  if ( !( table[ row ][ column -denominations[ row ] ].isImpossible() ) ) {
          	    table[ row ][ column ].add( table[ row ][ column - denominations[ row ] ] );
          	    if ( row != 0 ) {
          	  	  if ( table[ row - 1 ][ column ].total() < table[ row ][ column ].total() ) {
                    table[ row ][ column ] = table[ row - 1 ][ column ];
          	      } else {
          	  	    continue;
          	      }
          	    }
              }
            } 
          }  	
        }
      optimalSolution = table[ row ][ column ];
      }
    }
    return optimalSolution;
  }
}
