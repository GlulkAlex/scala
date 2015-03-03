package week5
import math.Ordering
//import scala.util.Sorting

object implicitParametersTest {
  /*Currying
  transforms a function
  that takes multiple parameters into
  a chain of functions,
  each taking
  a single parameter.
  'Curried functions' are defined with
  multiple parameter lists, as follows:*/
  def strcat(s1: String)(s2: String) = s1 + s2    //> strcat: (s1: String)(s2: String)String
  /*Alternatively,
	you can also use
	the following syntax to
	define a curried function:*/
  //with anonamous function
  def strcat1(s1: String) = (s2: String) => s1 + s2
                                                  //> strcat1: (s1: String)String => String
  def strcat2(s1: String, s2: String) = s1 + s2   //> strcat2: (s1: String, s2: String)String
  /*Following is
	the syntax to
	call a curried function:*/
  strcat("foo")("bar")                            //> res0: String = foobar
  strcat1("foo")("bar")                           //> res1: String = foobar
  strcat2("foo", "bar")                           //> res2: String = foobar

  /**
   * Making Sort more General
   * Problem:
   * How to parameterize 'msort' so that
   * it can also be used for
   * lists with elements other than 'Int'?
   *
   * Idea:
   * Parameterize 'merge' with
   * the necessary 'comparison' function.
   */
  /*Parameterization of Sort
	The most flexible design is to
	make the function sort polymorphic and to
	pass the comparison operation as
	an additional parameter:*/
  //def merge[T](xs: List[T], ys: List[T]): List[T] =
  def merge[T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean): List[T] =
    (xs, ys) match {
      case (Nil, y :: ys1) =>
        ys
      case (x :: xs1, Nil) =>
        xs
      case (x :: xs1, y :: ys1) =>
        if (lt(x, y)) {
          x :: merge(xs1, ys)(lt)
        } else {
          y :: merge(xs, ys1)(lt)
        }
    }                                             //> merge: [T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean)List[T]

  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    //def msort[T](xs: List[T]): List[T] = {
    //def msort[T](xs: List[T], lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2 //fraction / reminder is ommited / trown away

    if (n == 0) { //xs.length = 0 or 1 so, it already sorted
      xs
    } else {
      val (fst, snd) = xs splitAt n //<=> ((xs take n), (xs drop n))

      //merge(msort(fst)(lt), msort(snd)(lt))
      merge(msort(fst)(lt), msort(snd)(lt))(lt)
      //merge(msort(fst), msort(snd)) (lt)
      //merge(msort(fst, lt), msort(snd, lt))
    }
  }                                               //> msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]
  //*def msort3[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
  def msort3[T](xs: List[T], lt: (T, T) => Boolean): List[T] = {
    def merge3(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, y :: ys1) =>
          ys
        case (x :: xs1, Nil) =>
          xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) {
            x :: merge(xs1, ys)(lt)
          } else {
            y :: merge(xs, ys1)(lt)
          }
      }

    val n = xs.length / 2 //fraction / reminder is ommited / trown away

    if (n == 0) { //xs.length = 0 or 1 so, it already sorted
      xs
    } else {
      val (fst, snd) = xs splitAt n //<=> ((xs take n), (xs drop n))

      //*merge(msort(fst)(lt), msort(snd)(lt))(lt)
      merge3(msort3(fst, lt), msort3(snd, lt))
    }
  }                                               //> msort3: [T](xs: List[T], lt: (T, T) => Boolean)List[T]

  /**Parametrization with Ordered*/
  //def msort2[T](xs: List[T])(ord: Ordering): List[T] = {
  def msort2[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    /*Nested Function
  	is called 'local' function*/
    //def merge2[T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean): List[T] =
    //def merge2[T](xs: List[T], ys: List[T]): List[T] =
    def merge2(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, y :: ys1) =>
          ys
        case (x :: xs1, Nil) =>
          xs
        case (x :: xs1, y :: ys1) =>
          /*def lt(x: T, y: T): Boolean
					Return true if x < y in the ordering.*/
          if (ord.lt(x, y)) {
            //*if (ord.lt(xs.head, ys.head)) {
            //x :: merge2(xs1, ys)(lt)
            x :: merge2(xs1, ys)
          } else {
            //y :: merge2(xs, ys1)(lt)
            y :: merge2(xs, ys1)
          }
      }

    val n = xs.length / 2 //fraction / reminder is ommited / trown away

    if (n == 0) { //xs.length = 0 or 1 so, it already sorted
      xs
    } else {
      val (fst, snd) = xs splitAt n //<=> ((xs take n), (xs drop n))

      //merge2(msort2(fst)(ord), msort2(snd)(ord))(ord)
      merge2(msort2(fst)(ord), msort2(snd)(ord))
    }
  }                                               //> msort2: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]

  /**
   * Aside:
   * Implicit Parameters
   *
   * Problem:
   * Passing around lt or ord values is cumbersome.
   * We can avoid this by
   * making 'ord' an 'implicit parameter'.
   */
  def msort4[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    def merge4(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, y :: ys1) =>
          ys
        case (x :: xs1, Nil) =>
          xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) {
            x :: merge4(xs1, ys)
          } else {
            y :: merge4(xs, ys1)
          }
      }
    val n = xs.length / 2 //fraction / reminder is ommited / trown away

    if (n == 0) { //xs.length = 0 or 1 so, it already sorted
      xs
    } else {
      val (fst, snd) = xs splitAt n //<=> ((xs take n), (xs drop n))
			/*live to compiler to figure out actual 'ord' type,
			from 'implicit' &
			insert it into the right place*/
      merge4(msort4(fst), msort4(snd))
    }
  }                                               //> msort4: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]

  val xs = List(-5, 6, 3, 2, 7)                   //> xs  : List[Int] = List(-5, 6, 3, 2, 7)
  val ys = List(4, 5, -1, 8, 9)                   //> ys  : List[Int] = List(4, 5, -1, 8, 9)
  val fruit = List("apple", "pear", "orange", "pineapple")
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple)
  val berry = List("tomato", "potato", "strawberry", "melon")
                                                  //> berry  : List[String] = List(tomato, potato, strawberry, melon)

  merge(xs, ys)((x: Int, y: Int) => x < y)        //> res3: List[Int] = List(-5, 4, 5, -1, 6, 3, 2, 7, 8, 9)
  //merge(xs, (x: Int, y: Int) => x < y)
  merge(fruit, berry)((x: String, y: String) => x.compareTo(y) < 0)
                                                  //> res4: List[String] = List(apple, pear, orange, pineapple, tomato, potato, s
                                                  //| trawberry, melon)
  /*Or, since parameter types can
  be inferred from the call 'merge()':
  that is the reason why a comarason function 'lt'
  placed as the last parameter
  it is allow for compiler to
  infer right type for this function &
  ommit explicid declaration in / when function call*/
  merge(xs, ys)((x, y) => x < y)                  //> res5: List[Int] = List(-5, 4, 5, -1, 6, 3, 2, 7, 8, 9)
  "strawberry".compareTo("melon")                 //> res6: Int = 6
  "melon".compareTo("strawberry")                 //> res7: Int = -6
  "melon".compareTo("melon")                      //> res8: Int = 0
  
  msort2(xs)(Ordering.Int)                        //> res9: List[Int] = List(-5, 2, 3, 6, 7)
  msort2(ys)(Ordering.Int)                        //> res10: List[Int] = List(-1, 4, 5, 8, 9)
  msort2(fruit)(Ordering.String)                  //> res11: List[String] = List(apple, orange, pear, pineapple)
  msort2(berry)(Ordering.String)                  //> res12: List[String] = List(melon, potato, strawberry, tomato)
  msort3(ys, (x: Int, y: Int) => x < y)           //> res13: List[Int] = List(-1, 4, 5, 8, 9)
  msort3(fruit,
    (x: String, y: String) =>
      x.compareTo(y) < 0)                         //> res14: List[String] = List(apple, orange, pear, pineapple)

  msort4(xs)                                      //> res15: List[Int] = List(-5, 2, 3, 6, 7)
  msort4(fruit)                                   //> res16: List[String] = List(apple, orange, pear, pineapple)
  /*The compiler will ﬁgure out
	the right 'implicit' to pass
	based on
	the demanded type.*/
}