package week2

object curryingTest {
  //*println("Welcome to the Scala worksheet")
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) {
      0
    } else {
      f(a) + product(f)(a + 1, b)
    }
  }                                               //> product: (f: Int => Int)(a: Int, b: Int)Int

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
  }                                               //> product2: (f: Int => Int)(a: Int, b: Int)Int

  def fact(f: Int => Int)(n: Int): Int = {
    if (n == 0) {
      1
    } else {
      f(n) * fact(f)(n - 1)
    }
  }                                               //> fact: (f: Int => Int)(n: Int)Int

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def fact2(n: Int): Int = product2(x => x)(1, n) //> fact2: (n: Int)Int

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
  }                                               //> gFun: (f: Int => Int)(a: Int, b: Int, baseCaseVal: Int, operation: (Int, Int
                                                  //| ) => Int)Int

  def gFun2(f: Int => Int)(operation: (Int, Int) => Int)
	  (baseCaseVal: Int)(a: Int, b: Int): Int = {
	    if (a > b) {
	      baseCaseVal
	    } else {
	      operation(f(a), gFun2(f)(operation)(baseCaseVal)(a + 1, b))
	    }
  }                                               //> gFun2: (f: Int => Int)(operation: (Int, Int) => Int)(baseCaseVal: Int)(a: I
                                                  //| nt, b: Int)Int

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
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b
                                                  //| : Int)Int

  def product3(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> product3: (f: Int => Int)(a: Int, b: Int)Int

  var cur1 = product(x => x + x)_                 //> cur1  : (Int, Int) => Int = <function2>
  product(x => x + x)(0, 9)                       //> res0: Int = 90
  cur1(0, 9)                                      //> res1: Int = 90
  1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 + 7 + 8 + 8 + 9 + 9
                                                  //> res2: Int = 90
  product2(x => x * x)(3, 4)                      //> res3: Int = 144
  3 * 3 * 4 * 4                                   //> res4: Int = 144
  product3(x => x * x)(3, 4)                      //> res5: Int = 144

  fact(x => x)(3)                                 //> res6: Int = 6
  fact(x => x)(4)                                 //> res7: Int = 24
  fact(x => x)(5)                                 //> res8: Int = 120
  fact2(5)                                        //> res9: Int = 120

  gFun(x => x * x)(3, 4, 1, (x1, y1) => x1 * y1)  //> res10: Int = 144
  gFun2(x => x * x)((x1, y1) => x1 * y1)(1)(3, 4) //> res11: Int = 144

}