/** 
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides methods for CountTheDays.java program.
 *  @author       :  Joe Maiocco
 *  Date          :  2018-01-16
 *  Description   :  Provides methods for determining (1) whether or not a year is a leap year; 
 *                   (2) returns the number of days in a given month; (3) determines whether or 
 *                   not a date is valid; and (4) calculates the number of days between two valid
 *                   dates.
 *  Notes         :  None
 *  Warnings      :  None
 *  @exception    :  None
 *  @version      :  1.0.0
 **/

public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers.
   */
   private static final int SUNDAY          = 0;
   private static final int MONDAY          = SUNDAY    + 1;
   private static final int TUESDAY         = MONDAY    + 1;
   private static final int WEDNESDAY       = TUESDAY   + 1;
   private static final int THURSDAY        = WEDNESDAY + 1;
   private static final int FRIDAY          = THURSDAY  + 1;
   private static final int SATURDAY        = FRIDAY    + 1;
    
  /**
   * A listing of the months of the year, assigning numbers.
   */
   private static final int JANUARY         = 0;
   private static final int FEBRUARY        = JANUARY   + 1;
   private static final int MARCH           = FEBRUARY  + 1;
   private static final int APRIL           = MARCH     + 1;
   private static final int MAY             = APRIL     + 1;
   private static final int JUNE            = MAY       + 1;
   private static final int JULY            = JUNE      + 1;
   private static final int AUGUST          = JULY      + 1;
   private static final int SEPTEMBER       = AUGUST    + 1;
   private static final int OCTOBER         = SEPTEMBER + 1;
   private static final int NOVEMBER        = OCTOBER   + 1;
   private static final int DECEMBER        = NOVEMBER  + 1;

  /**
   * An array containing the number of days in each month, and a variable containing the length of the array.
   *  NOTE: this excludes leap years, so those will be handled as special cases
   */
   private static int[]    daysPerMonth      = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static final int MONTHS_PER_YEAR = daysPerMonth.length;
  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0;
   } 

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
    if ( 2 == month && true == CalendarStuff.isLeapYear( year ) ) {
     return daysPerMonth[ 1 ] + 1;
    } else {
        int monthIndex = (int) (month-1);
        long numberOfDays = (long) daysPerMonth[ monthIndex ];
        return numberOfDays; 
      }
    }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
    if (( year1 == year2 ) && ( month1 == month2 ) && (day1 == day2)) {
      return true;
    } else {
      return false;
    }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( year1 == year2 ) {
        if ( month1 == month2 ) {
          if ( day1 == day2) {
            return 0;
          } else if ( day1 < day2 ) {
             return -1;
          } else {
             return 1;
          }
        } else if ( month1 < month2 ) {
           return -1;
        } else {
           return 1;
        }
      } else if ( year1 < year2 ) {
         return -1;
      } else {
        return 1;
      }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
    if ( month < 1 || month > 12 ) {
      return false;
    } else if ( day < 1 || day > CalendarStuff.daysInMonth( month, year )) {
       return false;
    } else if (year < 0) {
       return false;
    } else {
       return true;
    } 
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         case JANUARY    : return "January";
         case FEBRUARY   : return "February";
         case MARCH      : return "March";
         case APRIL      : return "April";
         case MAY        : return "May";
         case JUNE       : return "June";
         case JULY       : return "July";
         case AUGUST     : return "August";
         case SEPTEMBER  : return "September";
         case OCTOBER    : return "October";
         case NOVEMBER   : return "November";
         case DECEMBER   : return "December";
         default         : throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
        case SUNDAY    : return "Sunday";
        case MONDAY    : return "Monday";
        case TUESDAY   : return "Tuesday";
        case WEDNESDAY : return "Wednesday";
        case THURSDAY  : return "Thursday";
        case FRIDAY    : return "Friday";
        case SATURDAY  : return "Saturday";
        default        : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long dayCount = 0;
     long countedMonths;
     long endDay;
     long endMonth;
     long endYear;
     long dayIndex;
     long monthIndex;
     long yearIndex;
     long i;
     if ( false == ( CalendarStuff.isValidDate( month1, day1, year1 ) || CalendarStuff.isValidDate( month2, day2, year2 ) )  ) { ///Checks if dates are valid.
      System.out.println("One of those is not a valid date.");
      return 0;
     }
     if ( 0 == CalendarStuff.compareDate( month1, day1, year1, month2, day2, year2 ) ) {                                         ///Returns when dates are equal.
      return dayCount;
     } 
     if (month1 == month2 && year1 == year2 ) {                                                                                  ///Returns when dates are in the same month.
      dayCount += Math.abs( day2 - day1 );
      return dayCount;
     } 
     if ( -1 == CalendarStuff.compareDate( month1, day1, year1, month2, day2, year2 ) ) {                                        ///Initializes if date1 < date2.
       dayIndex = day1; monthIndex = month1; yearIndex = year1; endDay = day2; endMonth = month2; endYear = year2;
     } 
     else {                                                                                                                      ///Initializes if date1 > date2.
       dayIndex = day2; monthIndex = month2; yearIndex = year2; endDay = day1; endMonth = month1; endYear = year1;
     }
     if ( endMonth == monthIndex + 1 && ( endYear == yearIndex + 1 || yearIndex == endYear ) ) {                                 ///Returns when dates are about a month apart.
      dayCount += CalendarStuff.daysInMonth( monthIndex, yearIndex ) - dayIndex;
      dayCount += endDay;
      return dayCount;
     }
     dayCount += CalendarStuff.daysInMonth( monthIndex - 1, yearIndex ) - dayIndex;                                                  ///Sums up days in the first month.
     for ( i = monthIndex; i < MONTHS_PER_YEAR; i++ ) {                                                                      ///Sums up days until end of the first year.
       dayCount += CalendarStuff.daysInMonth( monthIndex - 1 , yearIndex );
       monthIndex++;
     }
     dayCount += Math.abs( endYear - yearIndex - 1) * 365; 
     for ( i = 0; i < endMonth - 1; i++ ) {                                                                                      ///Sums up days in the final year until the last counted month.
       dayCount += CalendarStuff.daysInMonth( monthIndex - 1, endYear );
     }
     dayCount += endDay + 1; 
     for ( i = 0; i < ( endYear - yearIndex + 1 ); i++ ) {                                                                       ///Sums up extra leap days. Thanks to Patrick Utz for the code and idea!
        if ( CalendarStuff.isLeapYear( (yearIndex + i) ) ) {
          dayCount += 1;
        }
     }
     System.out.println(dayCount);
     return dayCount;
    }
}
