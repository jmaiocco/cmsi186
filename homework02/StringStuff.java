/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  Joe Maiocco
 *  Date          :  2017-01-25
 *  Description   :  This file provides several methods helpful for working with String type data.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {
  /**
   * An array of vowels, and an array of letters.
   */
   
   private static char[]  vowels  = { 'a', 'e', 'i', 'o', 'u', 'y' };
   private static char[]  letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
      int i;
      int j;
      s = s.toLowerCase();
      for ( i = 0; i < vowels.length; i++) {
         for ( j = 0; j < s.length(); j++ ) {
            if ( vowels[ i ] == s.charAt( j ) ) {
              return true;
            }
         }
      }
      return false;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
      String t = reverse( s );
      if ( true == t.equals( s ) ) {
         return true;
      } else {
         return false;
      }
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      int i;
      int j;
      String t = new String();
      String sLower = s.toLowerCase();
      for ( i = 0; i < s.length(); i++ ) {
         for ( j = 1; j < letters.length; j+=2 ) {
            if ( letters[ j ]  == sLower.charAt( i ) ) {
             t = t + s.charAt( i );
            }
         }
      }
      return t;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      int i;
      int j;
      String t = new String();
      String sLower = s.toLowerCase();
      for ( i = 0; i < s.length(); i++ ) {
         for ( j = 0; j < letters.length; j+=2 ) {
            if ( letters[ j ]  == sLower.charAt( i ) ) {
             t = t + s.charAt( i );
            }
         }
      }
      return t;
   }

  /**
  * Method to return the non-duplicate characters in a string. The method creates a set of the 
  * characters in the given string s, thus removing the duplicate characters. The set is converted
  * to an array, and then back into a string to be returned.
  *
  * @param s String containing the data to be parsed for non-duplicates.
  * @return  String containing the non-duplicate characters.
  */
  public static String noDupes( String s ) {
   String noDupesString = "";
   Set <String> characterSet = new LinkedHashSet <String> ();           
   for ( int i = 0; i < s.length(); i++ ) {
      characterSet.add( s.substring( i, i + 1 ) );
   }
   String[] characterArray = characterSet.toArray( new String[ 0 ] );
   for ( int j = 0; j < characterArray.length; j++ ) {
     noDupesString = noDupesString + characterArray[ j ];
   }
   return noDupesString;
  }


  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
     return noDupes( evensOnly( s ) );
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
     return noDupes( oddsOnly( s ) );
   }

  /**
   * Method to return the reverse of a string passed as an argument.
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      int i;
      String t = new String();
      for (i = s.length() - 1 ; i >= 0; i--) {
         t = t + s.charAt( i );
      }
      return t;
   }


  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }
}
