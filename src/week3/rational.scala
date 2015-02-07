package week3

class Rational(x: Int, y: Int) {
  //not evaluated in 'sheet'
  //only class instantinations evaluated ?

  /**
   * in Class methods implementation
   */
  // test (parameters / conditions) when class initiolazed
  /* 'require' is used to
  enforce a "precondition"
  on the caller of a function.
  */
  //require(y > 0, "denominator must be positive")
  require(y != 0, "denominator must be non zero")

  /** Adding an "auxiliary constructor" to the class 'Rational'. */
  // like in case of values by default ?
  def this(x: Int) = this(x, 1) //calls the "primary constructor" of the class

  /**
   * the function
   * that computes
   * the greatest common divisor of two numbers.
   * Here’s an implementation of 'gcd' using
   * Euclid’s algorithm.
   */
  //private members;
  //can only be accessed from inside the 'Rational' class.
  private def gcd(a: Int, b: Int): Int =
    if (b == 0)
      a
    else
      gcd(b, a % b)
  //1st possible implementation
  private val g = gcd(x, y)

  //def numer = x / g //x
  //def denom = y / g //y

  //2nd possible implementation
  /* can be advantageous if
  it is expected that
  the functions 'numer' and
  'denom' are called infrequently */
  //def numer = x / gcd(x, y)
  //def denom = y / gcd(x, y)

  //3d possible implementation
  /* can be advantageous if
  it is expected that
  the functions 'numer' and
  'denom' are called often. */
  //val numer = x / gcd(x, y)
  //val denom = y / gcd(x, y)

  def numer = x
  def denom = y

  def less(that: Rational) =
    //concise notation
    numer * that.denom < that.numer * denom
  // or full notation
  //this.numer * that.denom < that.numer * this.denom

  //'this' as self reference
  /* inside of a class,
  the name 'this' represents
  the 'object'
  on which
  the current method is executed. */
  def max(that: Rational) =
    if (this.less(that))
      that
    else
      this

  //'that' ?
  //add 'that' to 'itself' (class member - object / instance of class)
  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  
  /* Precedence Rules (0 - top important, 8 - most less)
  (all letters)
  8 |
  7 ^
  6 &
  5 < >
  4 = !
  3 :
  2 + -
  1 * / %
  0 (all other special characters)
  */
  
  //Operators for Rationals
  //A more natural deﬁnition:
  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  // 'neg' evaluates to -x
  def neg: Rational = {
    new Rational(
      -numer,
      denom)
  }

  /**
   * a method 'subtract2Rs'
   * to subtract two rational numbers from one.
   */
  def subtract2Rs(firstR: Rational, secondR: Rational) =
    add(firstR.neg).add(secondR.neg)

  /**
   * a method 'sub'
   * to subtract a rational numbers.
   * Using: DRY - do not repeat yourself
   */
  def sub(that: Rational) =
    add(that.neg)

  /**
   * Remark:
   * the modifier 'override' declares
   * that 'toString' redefines
   * a method that
   * already exists (in the class java.lang.Object).
   */
  override def toString = {
    //numer + "/" + denom
    //numer / gcd(x, y) + "/" + denom / gcd(x, y)
    //or
    //more elegant and fast and less evaluations require
    val g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }

}