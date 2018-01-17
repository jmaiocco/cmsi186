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
  public static boolean isLeapYear( long year ) {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }
  public static long daysInMonth( long month, long year ) {
    long numberOfDays = null;
    switch ( month ) {
      case '01': return isLeapYear( year ) ? numberOfDays = 29: numberOfDays = 28; 
      case '00':
      case '02':
      case '04':
      case '06':
      case '07':
      case '09':
      case '11': return numberOfDays = 31;
      case '03':
      case '05':
      case '08':
      case '10': return numberOfDays = 30;
    }
  }
  public static boolean isValidDate ( long month, long day, long year ) {
    
  }
  public static long daysBetween( long month0, long day0, long year0, long month1, long day1, long year1) {
    
  }
}
