package week3
import week3.Rational

object rationals {
  val x = new Rational(1, 3)                      //> x  : week3.Rational = 1/3
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3

  val y = new Rational(5, 7)                      //> y  : week3.Rational = 5/7
  x.add(y)                                        //> res2: week3.Rational = 22/21
  x.numer                                         //> res3: Int = 1
  x.denom                                         //> res4: Int = 3

  /*With the values
	x, y, z
	as given before
	(Rational(1,3), Rational(5,7), Rational(3,2)),
	what is the result of x - y- z ?
	*/
  val z = new Rational(3, 2)                      //> z  : week3.Rational = 3/2

  /**
   * outside Rational Class data structure
   * methods / functions for it
   */
  def addRational(r: Rational, s: Rational): Rational =
    new Rational(
      r.numer * s.denom + s.numer * r.denom,
      r.denom * s.denom)                          //> addRational: (r: week3.Rational, s: week3.Rational)week3.Rational

  def makeString(r: Rational) =
    r.numer + "/" + r.denom                       //> makeString: (r: week3.Rational)String

  makeString(addRational(new Rational(1, 2), new Rational(2, 3)))
                                                  //> res5: String = 7/6
  x.neg //evaluates to -x                         //> res6: week3.Rational = 1/-3
  x.subtract2Rs(y, z)                             //> res7: week3.Rational = -79/42
  x.sub(y).sub(z)                                 //> res8: week3.Rational = -79/42
  y.add(y) // expected: 10/7                      //> res9: week3.Rational = 10/7
  y.add(y).numer                                  //> res10: Int = 70
	//or
	y add y                                   //> res11: week3.Rational = 10/7
	(y + y).numer                             //> res12: Int = 70
	y.numer >= y.denom                        //> res13: Boolean = false
  y.add(y).denom                                  //> res14: Int = 49
  x.less(y)                                       //> res15: Boolean = true
  x.max(y)                                        //> res16: week3.Rational = 5/7

	//'IllegalArgumentException' when using 'require'
  /*
  val strengeR = new Rational(1, 0)
  strengeR.add(strengeR) // expected: !exception!
  */
  //'AssertionError'
  //assert(x.numer < 0)
  
  new Rational(3) //uses only one argument        //> res17: week3.Rational = 3/1
  new Rational(333, 777)                          //> res18: week3.Rational = 3/7
  new Rational(1333, 2777)                        //> res19: week3.Rational = 1333/2777

  /** Unit test */
  def main(args: Array[String]): Unit = {
    println("Negative value of " + x + " is: " + x.neg + " ? expected")
    println("Subtrution of " + x + " - " + y + " - " + z +
      " is: " + x.sub(y).sub(z) + " ? expected")
  }                                               //> main: (args: Array[String])Unit
}

/*
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
	8	|
	7	^
	6	&
	5	< >
	4	= !
	3	:
	2	+ -
	1	* / %
	0	(all other special characters)
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
}*/