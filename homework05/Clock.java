/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Joe Maiocco
 *  Date written  :  2018-03-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1. Includes the following:
 *                      validateTimeSliceArg(), for checking the optional given time slice
 *                      tick(), for simulating the ticking of a clock
 *                      getTotalSeconds(), for keeping track of the seconds passed
 *                      toString(), for giving a string representation of the clock
 *                      main(), for running test on the preceding methods
 *  Notes         :  None 
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are unacceptable
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definitions go here
   */
   private static final double SECONDS_PER_TWELVE_HOURS = 43200;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static double timeSlice, userTimeSlice;
   double totalSeconds = 0;

  /**
   *  Constructor
   */
   public Clock() {
     totalSeconds = 0;
   }


  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main program's args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws IllegalArgumentException, NumberFormatException {
      if ( argValue.equals("") ){
        return DEFAULT_TIME_SLICE_IN_SECONDS;
      }
      double argDouble = Double.parseDouble(argValue);
      if ( !( argDouble > 0 ) ){ 
        throw new IllegalArgumentException( "Time slice is out of acceptable range" );
      } else if ( argDouble > 0 ) {
         return argDouble;
      } else {
        throw new NumberFormatException( "Time slice is not a double-precision number" );
      }
   }

  /**
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick( double timeSlice ) {
     if ( timeSlice <= 0 ) {
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
     }
     totalSeconds += timeSlice;
     return totalSeconds;
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
      System.out.println( "Testing tick() methods" );
      timeSlice = 20;
      while ( clock.getTotalSeconds() <= SECONDS_PER_TWELVE_HOURS ) {
        System.out.println( "Current time is: " + clock.toString() );
        clock.tick( timeSlice );
        System.out.println( "" );
      }
      System.out.println( "Tests for validateTimeSliceArg()" );
      try { clock.validateTimeSliceArg( "0" ); } 
      catch( IllegalArgumentException iae ) { System.out.println( "0 is unacceptable!" ); }
      try { clock.validateTimeSliceArg( "-1" ); } 
      catch( IllegalArgumentException iae ) { System.out.println( "-1 is unacceptable!" ); }
      try { clock.validateTimeSliceArg( "ABC" ); }
      catch( NumberFormatException nfe ) {  System.out.println( "ABC is unacceptable!" ); }
      System.out.println( "61 seconds should return 61: " + clock.validateTimeSliceArg( "61") );
      System.out.println( "1 second should return 1: " + clock.validateTimeSliceArg( "1" ) );
      System.out.println( "1800 seconds should return 1800: " + clock.validateTimeSliceArg( "1800" ) );
      System.out.println( "0.00001 seconds should return 0.00001: " + clock.validateTimeSliceArg( "0.00001" ) );
      System.out.println( "Empty seconds should return 1: " + clock.validateTimeSliceArg( "" ) );
      System.out.println( "" );
    }
  }     
