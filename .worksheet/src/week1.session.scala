package week1

object session {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(8); val res$0 = 
  1 + 2;System.out.println("""res0: Int(3) = """ + $show(res$0));$skip(55); 
  def abs(x: Double) = if (x >= 0)
    x
  else
    -x;System.out.println("""abs: (x: Double)Double""");$skip(14); 

  val x = 0;System.out.println("""x  : Int = """ + $show(x ));$skip(24); 
  def f(y: Int) = y + 1;System.out.println("""f: (y: Int)Int""");$skip(138); 

  val result = {
    val x = f(3);
    //use ';' if statements placed in one line
    //otherwise - it can be ommited
    x * x
  } + x;System.out.println("""result  : Int = """ + $show(result ));$skip(1277); 

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
      abs(guess * guess - x) < x * 0.001 //epsilon value
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
  };System.out.println("""sqrt: (x: Double)Double""");$skip(12); val res$1 = 

  sqrt(2);System.out.println("""res1: Double = """ + $show(res$1));$skip(10); val res$2 = 
  sqrt(4);System.out.println("""res2: Double = """ + $show(res$2));$skip(10); val res$3 = 
  sqrt(9);System.out.println("""res3: Double = """ + $show(res$3));$skip(308); 
  //sqrt(0.1e-20)
  //sqrt(1.0e20)
  //sqrt(1.0e50)

  /**
   * the function
   * that computes
   * the greatest common divisor of two numbers.
   * Here’s an implementation of 'gcd' using
   * Euclid’s algorithm.
   */
  def gcd(a: Int, b: Int): Int =
    if (b == 0)
      a
    else
      gcd(b, a % b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(16); val res$4 = 

  gcd(14, 21);System.out.println("""res4: Int = """ + $show(res$4));$skip(10); val res$5 = 
  14 % 21;System.out.println("""res5: Int(14) = """ + $show(res$5));$skip(725); 

  //Note: explisit type of recursive function needed
  //else, error trowing on compiling
  //Note: recursion depth and stack overflow
  /** non Tail recursive Implementation */
  /*def factorial(n: Int): Int =
    if (n == 0)
      1
    else
      n * factorial(n - 1)*/

  /** my Tail recursive Implementation */
  /*def factorial(n: Int): Int = {
    def nFactorial(x: Int) =
      x * factorial(x - 1)

    if (n == 0)
      1
    else
      nFactorial(n)
  }*/ //works as expected

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
  };System.out.println("""factorial: (n: Int)Int""");$skip(19); val res$6 = 
  
  factorial(1);System.out.println("""res6: Int = """ + $show(res$6));$skip(15); val res$7 = 
  factorial(2);System.out.println("""res7: Int = """ + $show(res$7));$skip(15); val res$8 = 
  factorial(3);System.out.println("""res8: Int = """ + $show(res$8));$skip(15); val res$9 = 
  factorial(4);System.out.println("""res9: Int = """ + $show(res$9));$skip(15); val res$10 = 
  factorial(5);System.out.println("""res10: Int = """ + $show(res$10))}

}
