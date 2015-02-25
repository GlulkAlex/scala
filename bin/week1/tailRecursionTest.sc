package week1

object session {
  //*println("Welcome to the Scala worksheet")
  1 + 2                                           //> res0: Int(3) = 3
  def abs1(x: Double) = if (x >= 0)
    x
  else
    -x                                            //> abs1: (x: Double)Double

  val x = 0                                       //> x  : Int = 0
  def f(y: Int) = y + 1                           //> f: (y: Int)Int

  val result = {
    val x = f(3);
    //use ';' if statements placed in one line
    //otherwise - it can be ommited
    x * x
  } + x                                           //> result  : Int = 16

  /** Calculates the square root of parameter x */
  def sqrt(x: Double) = {
    //in that representation (inside braces)
    //x - never changes, so one can ommit it
    //that is a trick how to pass parameter
    //inside nested expressions using
    //they's outer scope
    //def sqrtIter(guess: Double, x: Double): Double =
    def sqrtIter(guess: Double): Double =
      //if (isGoodEnough(guess, x))
      if (isGoodEnough(guess))
        guess
      else
        //sqrtIter(improve(guess, x), x)
        sqrtIter(improve(guess))

    /**
     * The isGoodEnough test is
     * not very precise for small numbers and
     * can lead to
     * non-termination for very large numbers.
     * Explain why.
     */
    //def isGoodEnough(guess: Double, x: Double) =
    def isGoodEnough(guess: Double) =
      //abs(guess * guess - x) < 0.001 //epsilon value
      abs1(guess * guess - x) < x * 0.001 //epsilon value
    //or:
    //abs(guess * guess - x) / x < 0.001
    //Design a different version of 'isGoodEnough'
    //that does not have these problems.

    //def improve(guess: Double, x: Double) =
    def improve(guess: Double) =
      (guess + x / guess) / 2

    //last expression in the block
    //return value
    //sqrtIter(1.0, x)
    sqrtIter(1.0)
  }                                               //> sqrt: (x: Double)Double

  sqrt(2)                                         //> res1: Double = 1.4142156862745097
  sqrt(4)                                         //> res2: Double = 2.000609756097561
  sqrt(9)                                         //> res3: Double = 3.00009155413138
  //sqrt(0.1e-20)
  //sqrt(1.0e20)
  //sqrt(1.0e50)

  /**
   * the function
   * that computes
   * the greatest common divisor of two numbers.
   * Here's an implementation of 'gcd' using
   * Euclid's algorithm.
   */
  def gcd(a: Int, b: Int): Int =
    if (b == 0)
      a
    else
      gcd(b, a % b)                               //> gcd: (a: Int, b: Int)Int

  gcd(14, 21)                                     //> res4: Int = 7
  14 % 21                                         //> res5: Int(14) = 14
  (7 * 2) % (7 * 3)                               //> res6: Int = 14
  21 % 14                                         //> res7: Int(7) = 7

  /*Note:
  explisit type of recursive function needed
  else,
  error trowing on compiling*/
  //Note: recursion depth and stack overflow
  /** non Tail recursive Implementation */
  def factorial0(n: Int): Int =
    if (n == 0)
      1
    else
      n * factorial0(n - 1)                       //> factorial0: (n: Int)Int

  /** my Tail recursive Implementation */
  def factorial1(n: Int): Int = {
    def nFactorial(x: Int) =
      x * factorial1(x - 1)

    if (n == 0)
      1
    else
      nFactorial(n)
  } //works as expected                           //> factorial1: (n: Int)Int

  /**
   * Tail recursive Implementation
   * by Martin Odersky
   */
  def factorial(n: Int): Int = {
    def loop(acc: Int, n: Int): Int =
      if (n == 0)
        acc
      else
        loop(acc * n, n - 1)

    loop(1, n)
  }                                               //> factorial: (n: Int)Int

  factorial(1)                                    //> res8: Int = 1
  factorial(2)                                    //> res9: Int = 2
  factorial(3)                                    //> res10: Int = 6
  factorial(4)                                    //> res11: Int = 24
  factorial0(5)                                   //> res12: Int = 120
  factorial(5)                                    //> res13: Int = 120
  factorial1(5)                                   //> res14: Int = 120

}