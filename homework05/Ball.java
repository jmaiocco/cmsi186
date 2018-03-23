/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim 
 *  @author       :  Joe Maiocco
 *  Date written  :  2018-03-13
 *  Description   :  
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are unacceptable 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Ball {

  /**
   *  Class field definitions go here
   */
   private static final double FIELD_SIZE = 400.0;
   private static final double FRICTIONAL_PERCENTAGE = 0.99;
   private static final double BALL_RADIUS = 0.308734;
   private double ballRadius, ballXPosition, ballYPosition, ballXSpeed, ballYSpeed, modifiedBallSpeed;

  /**
   *  Constructor
   */
   public Ball( double xPosition, double yPosition, double xSpeed, double ySpeed ) {
   	 if ( ( ( xPosition > FIELD_SIZE/2 ) || ( xPosition < -FIELD_SIZE/2 ) || ( yPosition > FIELD_SIZE/2 ) || ( yPosition < -FIELD_SIZE/2 ) ) ) {
   	   throw new IllegalArgumentException();
   	 }
   	 this.ballRadius    = BALL_RADIUS;
   	 this.ballXPosition = xPosition;
   	 this.ballYPosition = yPosition;
     this.ballXSpeed    = xSpeed;
     this.ballYSpeed    = ySpeed;
    } 

  /**
   *  Method to update a ball's horizontal position
   *  @return double-precision value of the ball's new x-position 
   */
    public double moveBallHorizontally() {
      ballXPosition += ballXSpeed;
      return ballXPosition;
    }

  /**
   *  Method to update a ball's vertical position
   *  @return double-precision value of the ball's new y-position 
   */
    public double moveBallVertically() {
      ballYPosition += ballYSpeed;
      return ballYPosition;
    }

  /**
   *  Method to reduce a ball's velocity using 
   *  @param  givenBallSpeed  double-precision value of the ball's original speed component  
   *  @return double-precision value of the ball's new speed component
   */
    public double applyFriction( double givenBallSpeed ) {
      modifiedBallSpeed = ( givenBallSpeed * FRICTIONAL_PERCENTAGE );
      return modifiedBallSpeed;
    }

  /**
   *  Method to retrieve a ball's current x-position
   *  @return double-precision value of the ball's current x-position
   */
    public double getXPosition() {
      return ballXPosition;
    }

  /**
   *  Method to retrieve a ball's current y-position
   *  @return double-precision value of the ball's current y-position
   */
    public double getYPosition() {
      return ballYPosition;
    }


   public double getXVelocity() {
   	 return ballXSpeed;
   }

   public double getYVelocity() {
     return ballYSpeed;
   }

  /**
   * Public Instance method that returns a String representation of THIS ball instance
   * @return String representation of this Ball
   */
   public String toString() {
    return "Position @ (" + ballXPosition + ", " + ballYPosition + ")\n        Velocity of <" + ballXSpeed + ", " + ballYSpeed + ">" ;
   }

  /**
   * Class-wide method that returns a String representation of THIS ball instance
   * @return String representation of this Ball
   */
   public static String toString( Ball b ) {
      return b.toString();
   }

  /**
   *  Main program
   */
   public static void main( String args[] ) {
     try { Ball b1 = new Ball( 0, 0, 0, 0 ); }
   	 catch( IllegalArgumentException iae ) { System.out.println( "Invalid starting position of (0,0)" ); }
   	 try { Ball b1 = new Ball( 250, 250, -1, 1 ); }
   	 catch( IllegalArgumentException iae ) { System.out.println( "Invalid starting position of (250,250)" ); }
   	 try { Ball b1 = new Ball( -250, -249, -2000, 2000 ); }
   	 catch( IllegalArgumentException iae ) { System.out.println( "Invalid starting position of (-250,-249)" ); }
   	 try { Ball b1 = new Ball( 251, 0, 1.7923, -3 ); }
   	 catch( IllegalArgumentException iae ) { System.out.println( "Invalid starting position of (251,0)" ); }
   	 try { Ball b2 = new Ball( 74, -148, -4, 2.75 ); }
   	 catch( IllegalArgumentException iae ) { System.out.println( "Invalid starting position of (74,-148)"); }
   	 Ball b2 = new Ball( 74, -148, -4, 2.75 );
   	 System.out.println( b2.toString() );
    }


}
