package week2

object curryingTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(220); 
  //*println("Welcome to the Scala worksheet")
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) {
      0
    } else {
      f(a) + product(f)(a + 1, b)
    }
  };System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(217); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def product2(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) {
      1
    } else {
      f(a) * product2(f)(a + 1, b)
    }
  };System.out.println("""product2: (f: Int => Int)(a: Int, b: Int)Int""");$skip(122); 

  def fact(f: Int => Int)(n: Int): Int = {
    if (n == 0) {
      1
    } else {
      f(n) * fact(f)(n - 1)
    }
  };System.out.println("""fact: (f: Int => Int)(n: Int)Int""");$skip(129); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def fact2(n: Int): Int = product2(x => x)(1, n);System.out.println("""fact2: (n: Int)Int""");$skip(306); 

  /*general function,
  which generalizes both sum and product*/
  def gFun(f: Int => Int)(
    a: Int,
    b: Int,
    baseCaseVal: Int,
    operation: (Int, Int) => Int): Int = {
    if (a > b) {
      baseCaseVal
    } else {
      operation(f(a), gFun(f)(a + 1, b, baseCaseVal, operation))
    }
  };System.out.println("""gFun: (f: Int => Int)(a: Int, b: Int, baseCaseVal: Int, operation: (Int, Int) => Int)Int""");$skip(235); 

  def gFun2(f: Int => Int)(operation: (Int, Int) => Int)
	  (baseCaseVal: Int)(a: Int, b: Int): Int = {
	    if (a > b) {
	      baseCaseVal
	    } else {
	      operation(f(a), gFun2(f)(operation)(baseCaseVal)(a + 1, b))
	    }
  };System.out.println("""gFun2: (f: Int => Int)(operation: (Int, Int) => Int)(baseCaseVal: Int)(a: Int, b: Int)Int""");$skip(326); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) {
      zero
    } else {
      /*combine has type Tuple2 or Pair*/
      combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
    }
  };System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int)Int""");$skip(94); 

  def product3(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b);System.out.println("""product3: (f: Int => Int)(a: Int, b: Int)Int""");$skip(36); 

  var cur1 = product(x => x + x)_;System.out.println("""cur1  : (Int, Int) => Int = """ + $show(cur1 ));$skip(28); val res$0 = 
  product(x => x + x)(0, 9);System.out.println("""res0: Int = """ + $show(res$0));$skip(13); val res$1 = 
  cur1(0, 9);System.out.println("""res1: Int = """ + $show(res$1));$skip(72); val res$2 = 
  1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 + 7 + 8 + 8 + 9 + 9;System.out.println("""res2: Int = """ + $show(res$2));$skip(29); val res$3 = 
  product2(x => x * x)(3, 4);System.out.println("""res3: Int = """ + $show(res$3));$skip(16); val res$4 = 
  3 * 3 * 4 * 4;System.out.println("""res4: Int = """ + $show(res$4));$skip(29); val res$5 = 
  product3(x => x * x)(3, 4);System.out.println("""res5: Int = """ + $show(res$5));$skip(20); val res$6 = 

  fact(x => x)(3);System.out.println("""res6: Int = """ + $show(res$6));$skip(18); val res$7 = 
  fact(x => x)(4);System.out.println("""res7: Int = """ + $show(res$7));$skip(18); val res$8 = 
  fact(x => x)(5);System.out.println("""res8: Int = """ + $show(res$8));$skip(11); val res$9 = 
  fact2(5);System.out.println("""res9: Int = """ + $show(res$9));$skip(51); val res$10 = 

  gFun(x => x * x)(3, 4, 1, (x1, y1) => x1 * y1);System.out.println("""res10: Int = """ + $show(res$10));$skip(50); val res$11 = 
  gFun2(x => x * x)((x1, y1) => x1 * y1)(1)(3, 4);System.out.println("""res11: Int = """ + $show(res$11))}

}
