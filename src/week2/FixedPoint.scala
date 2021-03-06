package week2

object FixedPoint {
  /* finding solution of equation (expression) by 
   * parameters selection
   * that satisfy chosen limits
   * by repetitive comparison results with expectation 
   * */
  val tolerance = 0.0001

  //or invariant point
  def abs(x: Double) =
    if (x >= 0)
      x
    else
      -x

  //def isGoodEnough(guess: Double) =
  def isCloseEnough(guess: Double, next: Double) =
    //rather then
    //abs(guess * guess - x) < 0.001 //epsilon value
    //use:
    //abs((x - y) / x) / x < tolerance
    abs((guess - next) / guess) < next * tolerance //epsilon value

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)

      /*caution: may not terminate
       * infinite execution as it not converge
       * */ 
      //println(next)//for visual value control
      if (isCloseEnough(guess, next))
        next
      else
        iterate(next)
    }
    //return value
    iterate(firstGuess)
  }
  
  /**
   * reduce the oscillation of initial function to convergence 
   * for example to values between 1 & 2        
   * */
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

  def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1.0)

  //Unit test //Class entry point  
  def main(args: Array[String]): Unit = {
    //45  
    println("sqrt(2) is: " +
      sqrt(2) +
      " 1.4142135623730951 expected")
    println("sqrt(4) is: " +
      sqrt(4) +
      " 2 expected")
    println("sqrt(9) is: " +
      sqrt(9) +
      " 3 expected")
    println("sqrtfixedPoint(x => 1 + x / 2)(1) is: " +
      fixedPoint(x => 1 + x / 2)(1) +
      " 2 expected")
  }
}