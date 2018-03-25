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
import java.util.Arrays;

public class SoccerSim {

 /**
  *  Class field definitions go here
  */
  public static final double FIELD_SIZE = 400.0;
  public static final double FRICTIONAL_PERCENTAGE = 0.99;
  public static final double MINIMUM_SPEED_FOR_REMOVAL = 0.0834;
  private static final double BALL_RADIUS = 0.308734;
  public static ArrayList ballArrayList;
  public static double[] argumentArray;
  public static Ball[] ballArray, ballsAndPole;
  public static double timeSlice;
  public static int numberOfBallArguments;

 /**
  *  Constructor
  */
  public SoccerSim() {
  	super();
  }
 
 /**
  * Method to handle all the input arguments from the command line
  */
  public void handleInitialArguments( String args[] ) {
  	if ( args.length < 4 || ( args.length % 4 != 0 && args.length % 4 != 1 ) ) {
  	  System.out.println( "Please start over with a correct number of arguments on the command line." );  	  
  	  System.exit( -1 );
  	}
  	Clock tempClock = new Clock();
  	ballArrayList = new ArrayList<Ball>();
  	argumentArray = new double[args.length];
  	for ( int i = 0; i < args.length; i++ ) {
  	  argumentArray[ i ] = Double.parseDouble( args[ i ] );
  	}
  	if ( argumentArray.length % 4 == 0 ) {
  	  ballArray = new Ball[ argumentArray.length / 4 ];
  	  timeSlice = tempClock.validateTimeSliceArg( "" );
  	  for ( int i = 0; i < argumentArray.length; i += 4 ) {
  	    ballArray[ i / 4 ] = new Ball( argumentArray[ i ], argumentArray[ i + 1 ], argumentArray[ i + 2 ], argumentArray[ i + 3 ] );
  	    ballArrayList.add( i / 4, ballArray[ i / 4 ] );
   	  }
  	} 
  	if ( argumentArray.length % 4 == 1 ) {
  	  ballArray = new Ball[ ( argumentArray.length - 1) / 4 ];
  	  timeSlice = tempClock.validateTimeSliceArg( args[ args.length - 1 ] + "");
  	  for ( int i = 0; i < argumentArray.length - 1; i += 4 ) {
  	    ballArray[ i / 4 ] = new Ball( argumentArray[ i ], argumentArray[ i + 1 ], argumentArray[ i + 2 ], argumentArray[ i + 3 ] );
  	    ballArrayList.add( i / 4, ballArray[ i / 4 ] );
  	  }
  	}
  	Ball stationaryPole = new Ball( Math.floor( FIELD_SIZE/2 * Math.random() ), Math.floor( FIELD_SIZE/2 * Math.random() ), 0, 0 );
    ballsAndPole = Arrays.copyOf( ballArray, ballArray.length + 1 );
    ballsAndPole[ ballsAndPole.length - 1 ] = stationaryPole;
  }

 /**
  * Method to move each ball in the simulation 
  */
  public void updateBallMovements() {
  	for ( int i = 0; i < ballArray.length; i++) {
  	  ballsAndPole[ i ].moveBallHorizontally();
  	  ballsAndPole[ i ].moveBallVertically();
  	  ballsAndPole[ i ].applyXFriction( ballsAndPole[ i ].getXVelocity() * timeSlice );
  	  ballsAndPole[ i ].applyYFriction( ballsAndPole[ i ].getYVelocity() * timeSlice );
  	}
  }


 /**
  * Method to tell the user where balls are within the simulation
  */
  public void reportBallMovements() {
  	System.out.println( "Current ball positions and velocities:" );
  	for ( int i = 0; i < ballsAndPole.length - 1; i++ ) {
  	  System.out.println( "Ball " + (i + 1) +  ": " + ballsAndPole[ i ].toString() );
  	}
  	System.out.println( "Pole:   " + ballsAndPole[ ballsAndPole.length - 1  ].toString() );
  	System.out.println( "" );
  }

 /**
  * Method to remove balls from the simulation that have left the field or stopped moving
  */
  public void removeDeadBalls() {
    for ( int i = 0; i < ballsAndPole.length - 1; i++ ) {
  	  if( ballsAndPole[ i ].getXPosition() > FIELD_SIZE/2 || ballsAndPole[ i ].getYPosition() > FIELD_SIZE/2 || ballsAndPole[ i ].getXPosition() < -FIELD_SIZE/2 || ballsAndPole[ i ].getYPosition() < -FIELD_SIZE/2 ) {
  	    ballArrayList.remove( ballArrayList.size() - 1 );
  	  } else if ( ballArray[ i ].getXVelocity() < MINIMUM_SPEED_FOR_REMOVAL && ballsAndPole[ i ].getYVelocity() < MINIMUM_SPEED_FOR_REMOVAL ) {
  	    ballArrayList.remove( ballArrayList.size() - 1 );
  	  }
  	}
  }

 /**
  * Method to calculate whether two balls or one ball and the pole have collided
  */
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

 /**
  * Method to tell the user that no collisions are possible
  */
  public void recognizeNoCollisionPossible() {
    if ( ballArrayList.isEmpty() ) {
  	  System.out.println( "NO COLLISION IS POSSIBLE" );
  	  System.exit( 0 );
  	}
  } 

 /**
  * Main program
  */
  public static void main( String args[] ) {
  	SoccerSim soccerSim = new SoccerSim();
  	Clock clock = new Clock();
  	soccerSim.handleInitialArguments( args );
  	System.out.println( "\nNumber of objects on the field: " + ballsAndPole.length + "\n" );
  	while ( true ) {
  	  System.out.println( "Current time: " + clock.toString() );
  	  soccerSim.reportBallMovements();
  	  soccerSim.updateBallMovements(); 
  	  clock.tick( timeSlice );
  	  soccerSim.removeDeadBalls();
  	  soccerSim.recognizeNoCollisionPossible();
  	  for ( int i = 0; i < ballsAndPole.length; i++ ) {
  	  	for ( int j = i + 1; j < ballsAndPole.length; j++ ) {
  	  	  if ( soccerSim.detectCollision( ballsAndPole[ i ], ballsAndPole[ j ] ) ) {
  	  	  	if ( j == ballsAndPole.length - 1 ) {
  	  	  	  System.out.println( "Collision detected at time " + clock.toString() + " at position (" + ballsAndPole[ i ].getXPosition() + ", " + ballsAndPole[ i ].getYPosition() + ") between Ball " + ( i + 1 ) + " & Pole" );
  	  	  	} else {
  	  	  	  System.out.println( "Collision detected at time " + clock.toString() + " at position (" + ballsAndPole[ i ].getXPosition() + ", " + ballsAndPole[ i ].getYPosition() + ") between Balls " + ( i + 1 ) + " & " + ( j + 1 ) );
  	  	      System.exit( 0 );
  	  	  	}
  	  	  }
  	  	}
  	  }	
  	}
  }
}
