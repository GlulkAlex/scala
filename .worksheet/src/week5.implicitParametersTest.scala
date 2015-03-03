package week5
import math.Ordering
//import scala.util.Sorting

object implicitParametersTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(359); 
  /*Currying
  transforms a function
  that takes multiple parameters into
  a chain of functions,
  each taking
  a single parameter.
  'Curried functions' are defined with
  multiple parameter lists, as follows:*/
  def strcat(s1: String)(s2: String) = s1 + s2;System.out.println("""strcat: (s1: String)(s2: String)String""");$skip(172); 
  /*Alternatively,
	you can also use
	the following syntax to
	define a curried function:*/
  //with anonamous function
  def strcat1(s1: String) = (s2: String) => s1 + s2;System.out.println("""strcat1: (s1: String)String => String""");$skip(48); 
  def strcat2(s1: String, s2: String) = s1 + s2;System.out.println("""strcat2: (s1: String, s2: String)String""");$skip(83); val res$0 = 
  /*Following is
	the syntax to
	call a curried function:*/
  strcat("foo")("bar");System.out.println("""res0: String = """ + $show(res$0));$skip(24); val res$1 = 
  strcat1("foo")("bar");System.out.println("""res1: String = """ + $show(res$1));$skip(24); val res$2 = 
  strcat2("foo", "bar");System.out.println("""res2: String = """ + $show(res$2));$skip(818); 

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
    };System.out.println("""merge: [T](xs: List[T], ys: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(600); 

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
  };System.out.println("""msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(810); 
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
  };System.out.println("""msort3: [T](xs: List[T], lt: (T, T) => Boolean)List[T]""");$skip(1240); 

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
  };System.out.println("""msort2: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]""");$skip(984); 

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
  };System.out.println("""msort4: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]""");$skip(34); 

  val xs = List(-5, 6, 3, 2, 7);System.out.println("""xs  : List[Int] = """ + $show(xs ));$skip(32); 
  val ys = List(4, 5, -1, 8, 9);System.out.println("""ys  : List[Int] = """ + $show(ys ));$skip(59); 
  val fruit = List("apple", "pear", "orange", "pineapple");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(62); 
  val berry = List("tomato", "potato", "strawberry", "melon");System.out.println("""berry  : List[String] = """ + $show(berry ));$skip(45); val res$3 = 

  merge(xs, ys)((x: Int, y: Int) => x < y);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(109); val res$4 = 
  //merge(xs, (x: Int, y: Int) => x < y)
  merge(fruit, berry)((x: String, y: String) => x.compareTo(y) < 0);System.out.println("""res4: List[String] = """ + $show(res$4));$skip(312); val res$5 = 
  /*Or, since parameter types can
  be inferred from the call 'merge()':
  that is the reason why a comarason function 'lt'
  placed as the last parameter
  it is allow for compiler to
  infer right type for this function &
  ommit explicid declaration in / when function call*/
  merge(xs, ys)((x, y) => x < y);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(34); val res$6 = 
  "strawberry".compareTo("melon");System.out.println("""res6: Int = """ + $show(res$6));$skip(34); val res$7 = 
  "melon".compareTo("strawberry");System.out.println("""res7: Int = """ + $show(res$7));$skip(29); val res$8 = 
  "melon".compareTo("melon");System.out.println("""res8: Int = """ + $show(res$8));$skip(31); val res$9 = 
  
  msort2(xs)(Ordering.Int);System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(27); val res$10 = 
  msort2(ys)(Ordering.Int);System.out.println("""res10: List[Int] = """ + $show(res$10));$skip(33); val res$11 = 
  msort2(fruit)(Ordering.String);System.out.println("""res11: List[String] = """ + $show(res$11));$skip(33); val res$12 = 
  msort2(berry)(Ordering.String);System.out.println("""res12: List[String] = """ + $show(res$12));$skip(40); val res$13 = 
  msort3(ys, (x: Int, y: Int) => x < y);System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(72); val res$14 = 
  msort3(fruit,
    (x: String, y: String) =>
      x.compareTo(y) < 0);System.out.println("""res14: List[String] = """ + $show(res$14));$skip(15); val res$15 = 

  msort4(xs);System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(16); val res$16 = 
  msort4(fruit);System.out.println("""res16: List[String] = """ + $show(res$16))}
  /*The compiler will ?gure out
	the right 'implicit' to pass
	based on
	the demanded type.*/
}
