/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  Joe Maiocco
 *  Date written  :  2018-03-01
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEGREES_PER_ROTATION = 360.0;
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double DEFAULT_EPSILON_VALUE      = 0.1;      // small value for double-precision comparisons
   private static double[] validatedClockArgs;
   private static double currentTimeSlice, secondAngleCheck, thirdAngleCheck;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     if( 0 == args.length ) {
        System.out.println( "Please start over and enter at least one argument on the command line." );
        System.exit( 1 );
      }
      Clock initialClock = new Clock();
      validatedClockArgs = new double[3];
      if ( args.length >= 1 ) {
        validatedClockArgs[ 0 ] = initialClock.validateAngleArg( args[ 0 ] );
        if ( args.length >= 2 ) {
          validatedClockArgs[ 1 ] = initialClock.validateTimeSliceArg( args[ 1 ] );
          validatedClockArgs[ 2 ] = DEFAULT_EPSILON_VALUE;
          if ( args.length == 3 ) {
            validatedClockArgs[ 2 ] = initialClock.validateEpsilonArg( args[ 2 ] );
          } 
        }
        currentTimeSlice = validatedClockArgs[ 1 ];
      } 
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   *                args[2] is the epsilon value; this is optional and defaults to 0.1        
   */       
   public static void main( String args[] ) {
      ClockSolver clockSolver = new ClockSolver();
      clockSolver.handleInitialArguments( args );
      Clock clock = new Clock();
      secondAngleCheck = Math.abs( ( DEGREES_PER_ROTATION - clock.getMinuteHandAngle() ) + clock.getHourHandAngle() ); 
      thirdAngleCheck  = Math.abs( ( DEGREES_PER_ROTATION - clock.getHourHandAngle() ) + clock.getMinuteHandAngle() );
      System.out.println( "\nYour simulation is running with\n an angle of " + validatedClockArgs[ 0 ] + " degrees \n and a time slice of " + validatedClockArgs[ 1 ] + " seconds." );
      System.out.println("\n\n");
      System.out.println( validatedClockArgs[ 0 ] );
      System.out.println( validatedClockArgs[ 1 ] );
      System.out.println( validatedClockArgs[ 2 ] );
      while( clock.getTotalSeconds() < SECONDS_PER_TWELVE_HOURS ) {
        if ( ( clock.getHandAngle() >= ( validatedClockArgs[ 0 ] - (validatedClockArgs[ 0 ] * validatedClockArgs[ 2 ] ) ) ) && ( clock.getHandAngle() <= ( validatedClockArgs[ 0 ] + ( validatedClockArgs[ 0 ] * validatedClockArgs[ 2 ] ) ) ) ) {
          System.out.print( "Found target angle of " + validatedClockArgs[ 0 ] + " degrees at: " + clock.toString() + "\n" ); 
        } else if ( clock.getHandAngle() > ( secondAngleCheck - ( secondAngleCheck * validatedClockArgs[ 2 ] ) ) && clock.getHandAngle() < ( secondAngleCheck + ( secondAngleCheck * validatedClockArgs[ 2 ] ) ) ) {
          System.out.print( "Found target angle of " + validatedClockArgs[ 0 ] + " degrees at: " + clock.toString() + "\n" );
        } else if ( clock.getHandAngle() > ( thirdAngleCheck - ( thirdAngleCheck * validatedClockArgs[ 2 ] ) ) && clock.getHandAngle() < ( thirdAngleCheck + ( thirdAngleCheck * validatedClockArgs[ 2 ] ) ) ) {
          System.out.print( "Found target angle of " + validatedClockArgs[ 0 ] + " degrees at: " + clock.toString() + "\n" );
        }
        clock.tick();
      }
      System.exit( 0 );
   }
}
