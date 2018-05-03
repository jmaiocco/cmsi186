/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Find the "nth" Fibonacci number given an argument, using BrobInt class
 * @author    :  Joe Maiocco
 * Date       :  2018-04-17
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Fibonacci {

   private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
   private static int    maxCount    = 0;
   private static int    working     = 15000;
   private static String end1        = "st";
   private static String end2        = "nd";
   private static String end3        = "rd";
   private static String endRest     = "th";
   private static String cardinality = "";

   private static final  int NO_CMD_LINE_ARGS = -1;
   private static final  int BAD_CMD_LINE_ARG = -2;

   public static final BrobInt ZERO       = new BrobInt(  "0" );   
   public static final BrobInt ONE        = new BrobInt(  "1" );
   public static BrobInt fibonacciBrobInt         = new BrobInt( "0" );
   public static BrobInt fibonacciBrobIntMinusOne = new BrobInt( "0" ); 
   public static BrobInt fibonacciBrobIntMinusTwo = new BrobInt( "0" );
   public static int fibonacciTracker = 0;

   public Fibonacci() {
      super();
   }

   public static void main( String[] args ) {
      System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( NO_CMD_LINE_ARGS );
      }
      try {
         maxCount = Integer.parseInt( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( BAD_CMD_LINE_ARG );
      }
      if( 2 == args.length ) {
         try {
            working = Integer.parseInt( args[1] );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
         }
      }
      int lastIndex = args[0].length() - 1;
      switch( args[0].charAt( lastIndex ) ) {
         case '1': cardinality = end1;
                   break;
         case '2': cardinality = end2;
                   break;
         case '3': cardinality = end3;
                   break;
         default : cardinality = endRest;
                   break;
      }
      System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " );
      if ( maxCount == 0 ) {
        System.out.println( "\n                 " + ZERO );
      } else if ( maxCount == 1 ) {
        System.out.println( "\n                 " + ONE );
      } else {
        fibonacciTracker = 1;
        fibonacciBrobIntMinusOne = ONE;
        fibonacciBrobIntMinusTwo = ZERO;
        if( maxCount > working ) {
          System.out.println( "\n                This may take me a while; please be patient!!\n\n" );
        }
        while ( fibonacciTracker < maxCount ) {
        fibonacciBrobInt = fibonacciBrobIntMinusOne.add( fibonacciBrobIntMinusTwo );
        fibonacciBrobIntMinusTwo = fibonacciBrobIntMinusOne;
        fibonacciBrobIntMinusOne = fibonacciBrobInt;
        fibonacciTracker++;
      }
      System.out.println( "\n                 " + fibonacciBrobInt );
      }
      System.exit( 0 );
   }
}
