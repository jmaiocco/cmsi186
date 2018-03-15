/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main SoccerSim program 
 *  @author       :  Joe Maiocco
 *  Date written  :  2018-03-13
 *  Description   :  
 *  Notes         :  Default measurements are in ft
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are unacceptable 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {

  /**
   *  Class field definitions go here
   */
  public static final double FIELD_SIZE = 400.0;
  public static final double FRICTIONAL_PERCENTAGE = 0.99;
  private static final double BALL_RADIUS = 0.308734;
  public static double[] argumentArray;
  public static Ball[] ballArray;

  public SoccerSim() {
  	super();
  }

  public void handleInitialArguments( String args[] ) {
  	if ( args.length % 4 != 1 ) {
  	  System.out.println( "Please start over, with the correct number of arguments on the command line." );
  	  System.exit( -1 );
  	}
  	for ( int i = 0; i < args.length; i++ ) {
  	  argumentArray[ i ] = Double.parseDouble( args[ i ] );
  	}
  }

  public boolean detectCollision( Ball b1, Ball b2 ) {
  	if ( b1.getXPosition() == b2.getXPosition() && b1.getYPosition() == b2.getYPosition() ) {
  	  return true;	
  	} else if (  ) {
  	  return true;
  	} else {
  	  return false;
  	}
  }

  public static void main( String args[] ) {
  	Clock clock = new Clock();
  	clock.validateTimeSliceArg( args[ args.length - 1 ] );
  	for ( int i = 0; i < ( args.length - 1) / 4 ; i += 4 ) {
  	  ballArray[ i ] = new Ball( argumentArray[ i ], argumentArray[ i + 1 ], argumentArray[ i + 2 ], argumentArray[ i + 3 ] );
  	}
  	Ball stationaryPole = new Ball( Math.ceil( FIELD_SIZE * Math.random() ), Math.ceil( FIELD_SIZE * Math.random() ), 0, 0 );
  }
}
