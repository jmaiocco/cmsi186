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
   private static final double BALL_RADIUS = 4.45;
   private double ballXPosition, ballYPosition, ballXSpeed, ballYSpeed, modifiedBallSpeed;

  /**
   *  Constructor
   */
   public Ball( double xPosition, double yPosition, double xSpeed, double ySpeed ) {
   	 if ( ( xPosition > FIELD_SIZE/2 ) || ( xPosition < -FIELD_SIZE/2 ) || ( yPosition > FIELD_SIZE/2 ) || ( yPosition < -FIELD_SIZE/2 ) ) ) {
   	   throw new IllegalArgumentException();
   	 }
   	 this.ballRadius    = BALL_RADIUS;
   	 this.ballXPosition = xPosition;
   	 this.ballYPosition = yPosition;
     this.ballXSpeed    = xSpeed;
     this.ballYSpeed    = ySpeed;
    } 

  /**
   *
   */
    public double moveBallHorizontally() {
      ballXPosition += ballXSpeed;
      return ballXPosition;
    }

  /**
   *
   */
    public double moveBallVertically() {
      ballYPosition += ballYSpeed;
      return ballYPosition;
    }

  /**
   *
   */
    public double applyFriction( double givenBallSpeed ) {
      modifiedBallSpeed = ( givenBallSpeed * FRICTION_COEFFICIENT ) ///*timeslice;
      return modifiedBallSpeed;
    }

  /**
   *
   */
    public double getXPosition() {
      return ballXPosition;
    }

  /**
   *
   */
    public double getYPosition() {
      return ballYPosition;
    }

  /**
   * Public Instance method that returns a String representation of THIS ball instance
   * @return String representation of this Die
   */
   public String toString() {
    return "Position: (" + ballXPosition + "," + ballYPosition + ") and Velocity: <" + ballXSpeed + "," + ballYSpeed + ">" ;
   }

  /**
   * Class-wide method that returns a String representation of THIS ball instance
   * @return String representation of this Ball
   */
   public static String toString( Ball b ) {
      return b.toString();
   }

  /**
   *
   */
   public static void main( String args[] ) {
   	Ball b1 = new Ball();
   } 

}
