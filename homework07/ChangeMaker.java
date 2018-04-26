/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Joe Maiocco
 * Date       :  2018-04-24
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  Use tester in GitHub repo
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
  *  @param
  *
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
  *  
  *  @param
  *  @return  optimalSolution  Best solution provided the current denominations and amount.
  */
  public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int amount ) {
  	int row = 0;
  	int column = 0;
  	checkForAcceptableData( denominations, amount );
  	Tuple[][] table = new Tuple[ denominations.length ][ amount + 1 ];


    for ( row = 0; row < denominations.length; row++ ) {
      for ( column = 0; column < amount + 1; column++ ) {
        if ( column == 0 ) {

          table[ row ][ 0 ] = new Tuple( denominations.length );

        } else {
            if ( ( column - denominations[ row ] ) < 0 ) {

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

					if ( ( table[ row - 1 ][ column ].total() < table[ row ][ column ].total() ) || ( table[ row ][ column ].isImpossible() ) ) {
         
                      table[ row ][ column ] = table[ row - 1 ][ column ];

                    }
                  }  
          	  }
            }  	
      }
    }
  }
  return table[ denominations.length - 1 ][ amount ];
}
}
