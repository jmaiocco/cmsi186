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
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final double EPSILON_VALUE              = 0.1;      // small value for double-precision comparisons

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
      initialClock.validateTimeSliceArg( args[ 0 ] );
      initialClock.validateAngleArg( args[ 1 ] );
     // you may want to consider using args[2] for an "angle window"
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver clockSolver = new ClockSolver();
      Clock clock = new Clock();
      double[] timeValues = new double[3];
      clockSolver.handleInitialArguments( args );
      while( clock.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ) {
        if ( Double.parseDouble( args[ 0 ] ) == clock.getHandAngle() ) {
          System.out.print( "Found target angle of " + args[ 0 ] + " at " + clock.toString() ); 
        }
        clock.tick();
      }
      System.exit( 0 );
   }
}
