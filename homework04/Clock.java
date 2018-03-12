/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Joe Maiocco
 *  Date written  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definitions go here
   */
   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double DEFAULT_EPSILON_VALUE = 0.1;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00833334;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static double currentTimeSlice, userTimeSlice;
   private static double minuteHandAngle = 0;
   private static double hourHandAngle = 0;
   double totalSeconds = 0;

  /**
   *  Constructor
   */
   public Clock() {
     totalSeconds = 0;
     currentTimeSlice = userTimeSlice;
   }

/**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  IllegalArgumentException
   */
   public double validateAngleArg( String argValue ) {
      double argDouble = Double.parseDouble( argValue );
      if ( argDouble <= 0 || argDouble >= MAXIMUM_DEGREE_VALUE ) {
        throw new IllegalArgumentException();
      }
      ///double userAngleSlice = argDouble;
      return argDouble;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      if ( argValue == "" || argValue == null ) {
        return DEFAULT_TIME_SLICE_IN_SECONDS;
      }
      double argDouble = Double.parseDouble( argValue );
      if ( argDouble <= 0 || argDouble > 1800 ) {
        return INVALID_ARGUMENT_VALUE;
      } else {
        userTimeSlice = argDouble;
        return userTimeSlice;
      }
   }

 /**
  *  Method to validate the optional epsilon slice argument
  *  @param  argValue  String from the main programs args[2] input
  *  @return double-precision value of the argument or 0.1 if no epsilon is given
  *  @throws IllegalArgumentException
  */
  public double validateEpsilonArg( String argValue ) {
    if ( argValue == "" || argValue == null ) {
      return DEFAULT_EPSILON_VALUE;
    }
    double argDouble = Double.parseDouble( argValue );
    if ( argDouble < 0 || argDouble > 3.0 ) {
      throw new IllegalArgumentException();
    } 
     else {
      return argDouble;
    }
  } 

  /**
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
     totalSeconds += currentTimeSlice;
     return totalSeconds;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
     hourHandAngle = ( totalSeconds * HOUR_HAND_DEGREES_PER_SECOND ) % 360;
     return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
     minuteHandAngle = ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) % 360;
     return minuteHandAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
     getHourHandAngle();
     getMinuteHandAngle();
     return Math.abs( minuteHandAngle - hourHandAngle );
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   *  Note: method for calculating Hours:Minutes:Seconds from https://stackoverflow.com/questions/8999638/getting-data-in-seconds-want-to-calculate-hours-minutes-seconds
   */
   public String toString() {
      int hours = (int) totalSeconds / 3600;
      int minutes = (int) (totalSeconds / 60) % 60;
      int seconds = (int) totalSeconds % 60; 
      return hours + ":" + minutes + ":" + seconds;
    }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "New clock created: " + clock.toString() );
      System.out.println( "Testing tick() and hand methods" );
      currentTimeSlice = 10;
      while ( clock.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ) {
        System.out.println( "Current time is: " + clock.toString() );
        System.out.println( "Current minute hand at " + clock.getMinuteHandAngle() );
        System.out.println( "Current hour hand at " + clock.getHourHandAngle() );
        System.out.println( "Current angle between the hands is " + clock.getHandAngle() );
        clock.tick();
        System.out.println( "" );
      }
      System.out.println( "Tests for validateAngleArg()" );
      try { clock.validateAngleArg( "0" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 0 degrees" ); }
      try { clock.validateAngleArg( "360" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 360 degrees" ); }
      try { clock.validateAngleArg( "-90" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for -90 degrees" ); }
      try { clock.validateAngleArg( "0.0001"); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 0.0001 degrees" ); }
      try { clock.validateAngleArg( "359.9999" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 359.9999" ); }
      try { clock.validateAngleArg( "45" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 45 degrees" ); }
      try { clock.validateAngleArg( "ABC" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for ABC degrees" ); }
      try { clock.validateAngleArg( "12C" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for 12C degrees" ); }
      System.out.println( "" );
      System.out.println( "Tests for validateTimeSliceArg()" );
      System.out.println( "0 seconds should return -1: " + clock.validateTimeSliceArg( "0" ) );
      System.out.println( "61 seconds should return 61: " + clock.validateTimeSliceArg( "61" ) );
      System.out.println( "1 second should return 1: " + clock.validateTimeSliceArg( "1" ) );
      System.out.println( "1800 seconds should return 1800: " + clock.validateTimeSliceArg( "1800" ) );
      System.out.println( "0.00001 seconds should return 0.00001: " + clock.validateTimeSliceArg( "0.00001" ) );
      System.out.println( "Empty seconds should return 60: " + clock.validateTimeSliceArg( "60" ) );
      System.out.println( "" );
      System.out.println( "Tests for validateEpsilonArg()" );
      try { clock.validateEpsilonArg( "0" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 0" ); }
      try { clock.validateEpsilonArg( "-1" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of -1" ); }
      try { clock.validateEpsilonArg( "0.0001" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 0.001" ); }
      try { clock.validateEpsilonArg( "2.99999" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 2.99999" ); }
      try { clock.validateEpsilonArg( "3" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 3" ); }
      try { clock.validateEpsilonArg( "3.00001" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 3.00001" ); }
      try { clock.validateEpsilonArg( "ABC" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of ABC" ); }
      try { clock.validateEpsilonArg( "1B3" ); }
      catch( IllegalArgumentException iae ) { System.out.println( "Exception thrown for an epsilon of 1B3" ); }
    }
  }      
