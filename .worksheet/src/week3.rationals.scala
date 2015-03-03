package week3
import week3.Rational

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  val x = new Rational(1, 3);System.out.println("""x  : week3.Rational = """ + $show(x ));$skip(10); val res$0 = 
  x.numer;System.out.println("""res0: Int = """ + $show(res$0));$skip(10); val res$1 = 
  x.denom;System.out.println("""res1: Int = """ + $show(res$1));$skip(31); 

  val y = new Rational(5, 7);System.out.println("""y  : week3.Rational = """ + $show(y ));$skip(11); val res$2 = 
  x.add(y);System.out.println("""res2: week3.Rational = """ + $show(res$2));$skip(10); val res$3 = 
  x.numer;System.out.println("""res3: Int = """ + $show(res$3));$skip(10); val res$4 = 
  x.denom;System.out.println("""res4: Int = """ + $show(res$4));$skip(163); 

  /*With the values
	x, y, z
	as given before
	(Rational(1,3), Rational(5,7), Rational(3,2)),
	what is the result of x - y- z ?
	*/
  val z = new Rational(3, 2);System.out.println("""z  : week3.Rational = """ + $show(z ));$skip(233); 

  /**
   * outside Rational Class data structure
   * methods / functions for it
   */
  def addRational(r: Rational, s: Rational): Rational =
    new Rational(
      r.numer * s.denom + s.numer * r.denom,
      r.denom * s.denom);System.out.println("""addRational: (r: week3.Rational, s: week3.Rational)week3.Rational""");$skip(62); 

  def makeString(r: Rational) =
    r.numer + "/" + r.denom;System.out.println("""makeString: (r: week3.Rational)String""");$skip(68); val res$5 = 

  makeString(addRational(new Rational(1, 2), new Rational(2, 3)));System.out.println("""res5: String = """ + $show(res$5));$skip(26); val res$6 = 
  x.neg;System.out.println("""res6: week3.Rational = """ + $show(res$6));$skip(22); val res$7 =  //evaluates to -x
  x.subtract2Rs(y, z);System.out.println("""res7: week3.Rational = """ + $show(res$7));$skip(18); val res$8 = 
  x.sub(y).sub(z);System.out.println("""res8: week3.Rational = """ + $show(res$8));$skip(29); val res$9 = 
  y.add(y);System.out.println("""res9: week3.Rational = """ + $show(res$9));$skip(17); val res$10 =  // expected: 10/7
  y.add(y).numer;System.out.println("""res10: Int = """ + $show(res$10));$skip(15); val res$11 = 
	//or
	y add y;System.out.println("""res11: week3.Rational = """ + $show(res$11));$skip(15); val res$12 = 
	(y + y).numer;System.out.println("""res12: Int = """ + $show(res$12));$skip(20); val res$13 = 
	y.numer >= y.denom;System.out.println("""res13: Boolean = """ + $show(res$13));$skip(17); val res$14 = 
  y.add(y).denom;System.out.println("""res14: Int = """ + $show(res$14));$skip(12); val res$15 = 
  x.less(y);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(11); val res$16 = 
  x.max(y);System.out.println("""res16: week3.Rational = """ + $show(res$16));$skip(239); val res$17 = 

	//'IllegalArgumentException' when using 'require'
  /*
  val strengeR = new Rational(1, 0)
  strengeR.add(strengeR) // expected: !exception!
  */
  //'AssertionError'
  //assert(x.numer < 0)
  
  new Rational(3);System.out.println("""res17: week3.Rational = """ + $show(res$17));$skip(25); val res$18 =  //uses only one argument
  new Rational(333, 777);System.out.println("""res18: week3.Rational = """ + $show(res$18));$skip(27); val res$19 = 
  new Rational(1333, 2777);System.out.println("""res19: week3.Rational = """ + $show(res$19));$skip(247); 

  /** Unit test */
  def main(args: Array[String]): Unit = {
    println("Negative value of " + x + " is: " + x.neg + " ? expected")
    println("Subtrution of " + x + " - " + y + " - " + z +
      " is: " + x.sub(y).sub(z) + " ? expected")
  };System.out.println("""main: (args: Array[String])Unit""")}
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
