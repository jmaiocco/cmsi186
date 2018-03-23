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
import java.util.ArrayList;

public class SoccerSim {

  /**
   *  Class field definitions go here
   */
  public static final double FIELD_SIZE = 400.0;
  public static final double FRICTIONAL_PERCENTAGE = 0.99;
  public static final double MINIMUM_SPEED_FOR_REMOVAL = 0.00001;
  private static final double BALL_RADIUS = 0.308734;
  public static double[] argumentArray;
  public static Ball[] ballArray;
  public static Ball initialBall;
  public static double ballXSpeed, ballYSpeed, timeSlice;
  public static int noCollisionInteger;

  public SoccerSim() {
  	super();
  }

  public void handleInitialArguments( String args[] ) {
  	Clock tempClock = new Clock();
  	if ( args.length < 4 || args.length % 4 != 0 ) {
  	  System.out.println( "Please start over, with a correct number of arguments on the command line." );
  	  System.exit( -1 );
  	}
  	argumentArray = new double[args.length];
  	if ( args.length % 4 == 0 ) {
      for ( int i = 0; i < args.length; i++ ) {
  	    argumentArray[ i ] = Double.parseDouble( args[ i ] );
  	   }
  	   timeSlice = 1.0;
  	}
  	if ( args.length % 4 == 1 ) {
  	  for ( int i = 0; i < args.length - 1; i++ ) {
  	    argumentArray[ i ] = Double.parseDouble( args[ i ] );
  	   }
  	   tempClock.validateTimeSliceArg( args[ args.length - 1 ] );
  	   timeSlice = Double.parseDouble( args[ args.length - 1 ] );
  	}
  	ballArray = new Ball[ argumentArray.length / 4 + 1];
  	for ( int i = 0; i < args.length / 4 ; i += 4 ) {
  	  initialBall = new Ball( argumentArray[ i ], argumentArray[ i + 1 ], argumentArray[ i + 2 ], argumentArray[ i + 3 ] );
  	  ballArray[ i / 4 ] = initialBall;
  	}
  	Ball stationaryPole = new Ball( Math.floor( FIELD_SIZE/2 * Math.random() ), Math.floor( FIELD_SIZE/2 * Math.random() ), 0, 0 );
    ballArray[ ballArray.length - 1 ] = stationaryPole;	
  }

  public void updateBallMovements() {
  	for ( int i = 0; i < ballArray.length - 1; i++) {
  	  ballArray[ i ].moveBallHorizontally();
  	  ballArray[ i ].moveBallVertically();
  	  ballArray[ i ].applyXFriction( ballArray[ i ].getXVelocity() * timeSlice );
  	  ballArray[ i ].applyYFriction( ballArray[ i ].getYVelocity() * timeSlice );
  	}
  }

  public void reportBallMovements() {
  	System.out.println( "Current ball positions and velocities:" );
  	for ( int i = 0; i < ballArray.length; i++ ) {
  	  System.out.println( "Ball " + (i + 1) +  ": " + ballArray[ i ].toString() );
  	}
  	System.out.println( "" );
  }

  public void removeDeadBalls() {
    for ( int i = 0; i < ballArray.length - 2; i++ ) {
  	  if( ballArray[ i ].getXPosition() > FIELD_SIZE/2 || ballArray[ i ].getYPosition() > FIELD_SIZE/2 || ballArray[ i ].getXPosition() < -FIELD_SIZE/2 || ballArray[ i ].getYPosition() < -FIELD_SIZE/2 ) {
  	    ballArray[ i ] = null;
  	  } else if ( ballArray[ i ].getXVelocity() < MINIMUM_SPEED_FOR_REMOVAL && ballArray[ i ].getYVelocity() < MINIMUM_SPEED_FOR_REMOVAL ) {
  	  	ballArray[ i ] = null;
  	  }
  	}
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

  public void recognizeNoCollisionPossible() {
  	for ( int i = 0; i < ballArray.length - 1; i++ ) {
  	  if ( ballArray[ i ] == null ) {
        noCollisionInteger = -1;
  	   } else {
  	    noCollisionInteger = 1;
  	   }
  	}
  	if ( -1 == noCollisionInteger ) {
  	  System.out.println( "NO COLLISION IS POSSIBLE" );
  	  System.exit( 0 );
  	}
  } 

  public static void main( String args[] ) {
  	SoccerSim soccerSim = new SoccerSim();
  	Clock clock = new Clock();
  	soccerSim.handleInitialArguments( args );
  	for ( int k = 1; k < 4; k++ ) {
  	  System.out.println( "Current time: " + clock.toString() );
  	  soccerSim.reportBallMovements();
  	  soccerSim.updateBallMovements(); 
  	  soccerSim.removeDeadBalls();
  	  for ( int i = 0; i < ballArray.length - 1; i++ ) {
  	  	for ( int j = i + 1; j < ballArray.length - 1; j++ ) {
  	  	  if ( soccerSim.detectCollision( ballArray[ i ], ballArray[ j ] ) ) {
  	  	    System.out.println( "Collision detected at " + clock.toString() + " between "   );
  	  	    System.exit( 0 );
  	  	  }
  	  	}
  	  }	
  	  clock.tick();
  	  soccerSim.recognizeNoCollisionPossible();
  	}
  }
}
