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
  public static double ballXSpeed, ballYSpeed, userTimeSlice;

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
  	userTimeSlice = Double.parseDouble( args[ args.length - 1 ] );
  }

  public static boolean detectCollision( Ball b1, Ball b2 ) {
  	if ( b1.getXPosition() == b2.getXPosition() && b1.getYPosition() == b2.getYPosition() ) {
  	  return true;	
  	} else if ( b1.getXPosition() - BALL_RADIUS >= b2.getXPosition() && b1.getXPosition() + BALL_RADIUS <= b2.getXPosition() ) {
  	  return true;
  	} else if ( b1.getYPosition() - BALL_RADIUS >= b2.getYPosition() && b1.getYPosition() + BALL_RADIUS <= b2.getYPosition() ) {
  	  return true;
  	} else if ( b2.getXPosition() - BALL_RADIUS >= b1.getXPosition() && b2.getXPosition() + BALL_RADIUS <= b1.getXPosition() ) {
  	  return true;
  	} else if ( b2.getYPosition() - BALL_RADIUS >= b1.getXPosition() && b2.getYPosition() + BALL_RADIUS <= b1.getYPosition() ) {
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
  	Ball stationaryPole = new Ball( FIELD_SIZE * Math.random(), FIELD_SIZE * Math.random(), 0, 0 );
    ballArray[ ballArray.length + 1 ] = stationaryPole;
  	while ( true ) {
  	  if ( ballArray.length == 1 ) {
  	  	System.out.println( "NO COLLISION IS POSSIBLE" );
  	  	break;
  	  }
  	  for ( Ball b: ballArray ) {
  	    b.moveBallVertically();
  	    b.moveBallHorizontally();
  	    b.applyFriction( ballYSpeed * userTimeSlice );
  	    b.applyFriction( ballXSpeed * userTimeSlice );
  	  }
  	  System.out.println( "Current ball positions and velocities: \n" );
  	  for ( int i = 0; i < ballArray.length - 1; i++) {
  	    ballArray[ i ].toString();	
  	  } 
  	  for ( Ball b: ballArray ) {
  	    if( b.getXPosition() > FIELD_SIZE/2 || b.getYPosition() > FIELD_SIZE/2 || b.getXPosition() < -FIELD_SIZE/2 || b.getYPosition() < -FIELD_SIZE/2 ) {
  	  	  ///for removing balls that are out of bounds
  	  	}
  	  }
  	  for ( int i = 0; i < ballArray.length - 1; i++ ) {
  	  	for ( int j = i + 1; j < ballArray.length - 1; j++ ) {
  	  	  if ( detectCollision( ballArray[ i ], ballArray[ j ] ) ) {
  	  	    System.out.println( "Collision detected at " + clock.toString() + " between "   );
  	  	    break;
  	  	  }
  	  	}
  	  }	
  	  clock.tick();
  	}
  	System.exit( 0 );
  }
}
