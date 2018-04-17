/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  Joe Maiocco
 * Date       :  2017-03-27
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 **/
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int   sign          = 0;         // "0" is positive, "1" is negative
   private StringBuilder reversed = new StringBuilder();        // the backwards version of the internal String representation
   private static int[] IntVersion   = null;      // Int array for storing the string values; uses the reversed string
   private StringBuilder stringBuilder = new StringBuilder();
  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      if ( value.equals( "" ) || value.equals( null ) ) {
        throw new IllegalArgumentException( "String was not passed to constructor" ); 
      }
      internalValue = value;
      StringBuilder stringBuilder = new StringBuilder( internalValue );
      if ( internalValue.charAt( 0 ) == '-' ) {
       sign = 1;
       stringBuilder.deleteCharAt( 0 );
     } else if ( internalValue.charAt( 0 ) == '+' ) {
       sign = 0;
       stringBuilder.deleteCharAt( 0 );
     } else {
       sign = 0;
     } 
      validateDigits( stringBuilder.toString() ); 
      IntVersion = new int[ stringBuilder.length() ];
      for ( int i = IntVersion.length - 1; i > -1; i-- ) {
        reversed.append( stringBuilder.charAt(i) + "" );
        IntVersion[ i ] = Integer.parseInt( stringBuilder.charAt(i) + "" );
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits( String value ) throws IllegalArgumentException {
     String validDigits = "0123456789";
       for ( int i = 0; i < stringBuilder.length(); i++ ) {
         for ( int j = 0; j < validDigits.length(); j++ ) {
           if ( stringBuilder.toString().charAt( j ) == validDigits.charAt( j ) ) {
             continue;
            } else if ( ( j == validDigits.length() - 1 ) && !( stringBuilder.toString().charAt( j ) == validDigits.charAt( j ) ) ) {
              throw new IllegalArgumentException();
            }
          }
        }
        return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
     return new BrobInt( new StringBuilder( this.stringBuilder ).reverse().toString() );
    }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
     return gint.reverser();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using Int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
    int addedInts = 0;
    int carry = 0;
    int shorterLength, longerLength;
    StringBuilder shorterInt = new StringBuilder();
    StringBuilder longerInt = new StringBuilder();
    StringBuilder addedIntStringBuilder = new StringBuilder();
    if ( this.sign == 0 && gint.sign == 1 ) {
      gint.sign = 0;
      this.subtract( gint );
    }
    if ( this.reversed.length() <= gint.reversed.length() ) {
      shorterLength = this.reversed.length();
      shorterInt = this.reversed;
      longerLength = gint.reversed.length();
      longerInt = gint.reversed;
    } else {
      shorterLength = gint.reversed.length();
      shorterInt = gint.reversed;
      longerLength = this.reversed.length();
      longerInt = this.reversed;
    }
    for ( int i = 0; i < shorterLength; i++ ) {
      addedInts = Integer.parseInt( this.reversed.charAt(i) + "" ) + Integer.parseInt( gint.reversed.charAt(i) + "" ) + carry;
      if ( addedInts > 9 ) {
        addedInts -= 10;
        carry = 1;
      } else {
        carry = 0;
      }
      addedIntStringBuilder.append( addedInts );
    }
    if ( carry == 1 ) {
      addedIntStringBuilder.append( carry );
    }
    for ( int j = shorterLength; j < longerLength; j++) {
      addedIntStringBuilder.append( longerInt.charAt(j) + "" );
    }
    if ( addedIntStringBuilder.charAt( addedIntStringBuilder.length() - 1 ) == '0' ) {
      addedIntStringBuilder.deleteCharAt( addedIntStringBuilder.length() - 1 );
    }
    if ( this.sign == 1 && gint.sign == 1 ) {
      addedIntStringBuilder.append( "-" );
    }
    String addedIntString = new String( addedIntStringBuilder.reverse().toString() );
    BrobInt newBrobInt = new BrobInt( addedIntString );
    return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using Ints
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
    int subtractedInts = 0;
    int carry = 0;
    int shorterLength = 0; 
    int longerLength = 0;
    StringBuilder shorterInt = new StringBuilder();
    StringBuilder longerInt = new StringBuilder();
    StringBuilder subtractedIntStringBuilder = new StringBuilder();
    if ( this.compareTo( gint ) == 0 ) {
      return ZERO;
    } else if ( this.sign == 1 && gint.sign == 1 && this.isGreaterThan( gint ) ) {
      StringBuilder positiveThis = new StringBuilder( this.toString() ).deleteCharAt( 0 );
      StringBuilder positiveGint = new StringBuilder( gint.toString() ).deleteCharAt( 0 );
      return new BrobInt( new BrobInt( positiveThis.toString() ).subtract( new BrobInt( positiveGint.toString() ) ).toString() );
    } else if ( this.sign == 1 && gint.sign == 1 && !( this.isGreaterThan( gint ) ) ) {
      StringBuilder positiveThis = new StringBuilder( this.toString() ).deleteCharAt( 0 );
      StringBuilder positiveGint = new StringBuilder( gint.toString() ).deleteCharAt( 0 );
      return new BrobInt( new StringBuilder( new BrobInt( positiveGint.toString() ).subtract( new BrobInt( positiveThis.toString() ) ).toString() ).insert( 0, "-" ).toString() );
    } else if ( this.sign == 0 && gint.sign == 1 ) {
      StringBuilder positiveGint = new StringBuilder( gint.toString() ).deleteCharAt( 0 );
      return new BrobInt( this.add( new BrobInt( positiveGint.toString() ) ).toString() );
    } else if ( this.sign == 1 && gint.sign == 0 ) {
      gint.sign = 1;
      return new BrobInt( this.add( gint ).toString() );
    }
    if ( this.reversed.length() <= gint.reversed.length() ) {
      shorterLength = this.reversed.length();
      shorterInt = this.reversed;
      longerLength = gint.reversed.length();
      longerInt = gint.reversed;
    } else if ( this.reversed.length() > gint.reversed.length() ) {
      shorterLength = gint.reversed.length();
      shorterInt = gint.reversed;
      longerLength = this.reversed.length();
      longerInt = this.reversed;
    }
    for ( int i = 0; i < shorterLength; i++ ) {
      subtractedInts = Integer.parseInt( longerInt.charAt(i) + "" ) - Integer.parseInt( shorterInt.charAt(i) + "" ) - carry;
      if ( subtractedInts < 0 ) {
        subtractedInts += 10;
        carry = 1;
      } else {
        carry = 0;
      }
      subtractedIntStringBuilder.append( subtractedInts );
    }
    for ( int j = shorterLength; j < longerLength; j++) {
      subtractedIntStringBuilder.append( longerInt.charAt(j) + "" );
    }
    if ( !( this.isGreaterThan( gint ) ) && this.reversed.length() < gint.reversed.length() ) {
      subtractedIntStringBuilder.append( "-" );
    }
    String subtractedIntString = new String( subtractedIntStringBuilder.reverse().toString() );
    BrobInt newBrobInt = new BrobInt( subtractedIntString );
    return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
     int i, j, k;
     int shorterLength = 0; 
     int longerLength = 0;
     int appendedOneCounter = 0;
     int[] intArray;
     BrobInt shorterBrobInt = new BrobInt( "0" );
     BrobInt longerBrobInt = new BrobInt( "0" ); 
     StringBuilder multipliedIntStringBuilder = new StringBuilder();
     BrobInt newBrobInt = new BrobInt( "0" );
     if ( this.reversed.length() <= gint.reversed.length() ) {
       shorterLength = this.reversed.length();
       shorterBrobInt = this;
       longerLength = gint.reversed.length();
       longerBrobInt = gint;
     } else if ( this.reversed.length() > gint.reversed.length() ) {
       shorterLength = gint.reversed.length();
       shorterBrobInt = gint;
       longerLength = this.reversed.length();
       longerBrobInt = this;
     }
     int numberOfInts = ( shorterLength / 9) + 1;
     intArray = new int[ numberOfInts ];
     
     if ( shorterLength < 10 ){
         intArray[0] = Integer.parseInt( shorterBrobInt.internalValue );
      } else {
         for ( j = 0; j < numberOfInts; j++ ){
           if ( j + 9 < longerLength ){
             intArray[j] = Integer.parseInt( shorterBrobInt.internalValue.substring(j*9, (j*9) + 9) );
           } else {
             intArray[j] = Integer.parseInt( shorterBrobInt.internalValue.substring(j*9, (j*9) + (shorterLength - j) ) );
           }
         }
      }
      for (  i = 0; i < numberOfInts; i++ ){
         for ( j = 0; j < intArray[i]; j++ ){
            newBrobInt = newBrobInt.add( new BrobInt( longerBrobInt.internalValue ) );
            ///System.out.println( newBrobInt );
         }
      }
      for ( i = 0; i < newBrobInt.internalValue.length(); i++ ) {
        if ( newBrobInt.internalValue.charAt( i ) == '1' ) {
          appendedOneCounter++;
        } else {
          break;
        }
      }
      multipliedIntStringBuilder = new StringBuilder( newBrobInt.toString() );
      if ( appendedOneCounter > 1 ) {
        multipliedIntStringBuilder.delete( 0, appendedOneCounter );
        multipliedIntStringBuilder.insert( 0, appendedOneCounter );
      }
      /*if ( this.sign != gint.sign ) {
       multipliedIntStringBuilder =  new StringBuilder( newBrobInt.toString() );
      }*/
      newBrobInt = new BrobInt( multipliedIntStringBuilder.toString() );
      return newBrobInt;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
     BrobInt divisor = gint;
     BrobInt dividend = this;
     BrobInt quotient = new BrobInt( "0" ); 
     if ( divisor.equals( ZERO ) ) {
      throw new IllegalArgumentException( "Cannot divide by zero" );
     } else if ( dividend.equals( divisor ) ) {
       return ONE;
     } else if ( divisor.isGreaterThan( dividend ) ) {
       return ZERO;
     } 
     int divisorLength = gint.reversed.length();


     //while (  ) {

     //}
     return ZERO;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      return (internalValue.compareTo( gint.toString() ));
   }

   public boolean isGreaterThan( BrobInt gint ) {
    if ( this.sign == 1 && gint.sign == 0 ) {
      return false;
    } else if ( this.sign == 0 && gint.sign == 1 ) {
      return true;
    } else if ( this.sign == 1 && gint.sign == 1 ) {
      if ( this.internalValue.length() < gint.internalValue.length() ) {
        return true;
      } else if ( this.internalValue.length() > gint.internalValue.length() ) {
        return false;
      } else {
        for ( int i = this.reversed.length() - 1; i > -1; i-- ) {
          if ( Integer.parseInt( this.reversed.charAt( i ) + "" ) > Integer.parseInt( gint.reversed.charAt( i ) + "" ) ) {
            return false;
          }
        }
        return true;
      }
    } else {
      if ( this.internalValue.length() < gint.internalValue.length() ) {
        return false;
      } else if ( this.internalValue.length() > gint.internalValue.length() ) {
        return true;
      } else {
        for ( int i = this.reversed.length() - 1; i > -1; i-- ) {
          if ( Integer.parseInt( this.reversed.charAt( i ) + "" ) > Integer.parseInt( gint.reversed.charAt( i ) + "" ) ) {
            return true;
          }
        }
        return false;
      }
    }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String IntVersionOutput = "";
      for( int i = 0; i < IntVersion.length; i++ ) {
         IntVersionOutput = IntVersionOutput.concat( Integer.toString( IntVersion[i] ) );
      }
      IntVersionOutput = new String( new StringBuffer( IntVersionOutput ).reverse() );
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its Ints
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
