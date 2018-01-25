/** 
 *  File name     :  CountTheDays.java
 *  Purpose       :  Counts the days between two dates given as arguments.
 *  @author       :  Joe Maiocco
 *  Date          :  2018-01-25
 *  Description   :  Uses methods from CalendarStuff.java to calculate the number of days between two dates.
 *  Notes         :  None
 *  Warnings      :  None
 *  @exception    :  None
 *  @version      :  1.0.0
 **/

public class CountTheDays {
  public static void main(String [] args){
     long month1 = Long.parseLong(args[0]);
     long day1   = Long.parseLong(args[1]);
     long year1  = Long.parseLong(args[2]);
     long month2 = Long.parseLong(args[3]);
     long day2   = Long.parseLong(args[4]);
     long year2  = Long.parseLong(args[5]);
     System.out.println(CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2));
   }
}
