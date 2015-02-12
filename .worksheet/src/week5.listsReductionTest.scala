package week5

object listsReductionTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(87); 
  println("Welcome to the Scala worksheet");$skip(202); 

  /*
	sum(List(x1, ..., xn)) = 0 + x1 + ... + xn
	product(List(x1, ..., xn)) = 1 * x1 * ... * xn
	*/
  def sum1(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum1(ys)
  };System.out.println("""sum1: (xs: List[Int])Int""");$skip(198); 

  /*'reduceLeft' can only apply to non empty lists,
  so '0' is default when xs == Nil
  as 'z' parameter in 'foldLeft' method*/
  def sum2(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y);System.out.println("""sum2: (xs: List[Int])Int""");$skip(71); 
  def product2(xs: List[Int]) = (1 :: xs) reduceLeft ((x, y) => x * y);System.out.println("""product2: (xs: List[Int])Int""");$skip(117); 
  /*every '_' represent new parameter, from left to right*/
  def sum3(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _);System.out.println("""sum3: (xs: List[Int])Int""");$skip(61); 
  def product3(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _);System.out.println("""product3: (xs: List[Int])Int""");$skip(112); 
  /*'z' = 0 is accumulator and default value if 'xs'== Nil*/
  def sum4(xs: List[Int]) = (xs foldLeft 0)(_ + _);System.out.println("""sum4: (xs: List[Int])Int""");$skip(57); 

  def product4(xs: List[Int]) = (xs foldLeft 1)(_ * _);System.out.println("""product4: (xs: List[Int])Int""");$skip(84); 
  def concat1[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys)(_ :: _);System.out.println("""concat1: [T](xs: List[T], ys: List[T])List[T]""");$skip(99); 

  def concat2[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldLeft ys)((xs, ys) => ys :: xs);System.out.println("""concat2: [T](xs: List[T], ys: List[T])List[T]""");$skip(99); 

  def concat3[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldLeft ys)((ys, xs) => xs :: ys);System.out.println("""concat3: [T](xs: List[T], ys: List[T])List[T]""");$skip(1357); 

  /*
	List(x1, ..., xn) reduceLeft op = (...(x1 op x2) op ... ) op xn
	(List(x1, ..., xn) foldLeft z)(op) = (...(z op x1) op ... ) op xn
	
	List(x1, ..., x{n-1}, xn) reduceRight op = x1 op ( ... (x{n-1} op xn) ... )
	(List(x1, ..., xn) foldRight acc)(op) = x1 op ( ... (xn op acc) ... )
	*/

  /*abstract class List[T] { /*...*/
    def reduceLeft(op: (T, T) => T): T = this match {
      case Nil => throw new Error("Nil.reduceLeft")
      case x :: xs => (xs foldLeft x)(op)
    }
    //'this' as object instance of class List[T] i.e. list
    def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
      case Nil => z
      case x :: xs => (xs foldLeft op(z, x))(op)
    }
    def reduceRight(op: (T, T) => T): T = this match {
      case Nil => throw new Error(?Nil.reduceRight?)
      case x :: Nil => x
      case x :: xs => op(x, xs.reduceRight(op))
    }
    def foldRight[U](z: U)(op: (T, U) => U): U = this match {
      case Nil => z
      case x :: xs => op(x, (xs foldRight z)(op))
    }
  }*/

  /*def concat4[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldLeft ys)((as, bs) => as :: bs)//error: '::' not a member of 'T'*/

  /**Reversing list at linear cost, using 'foldLeft':*/
  //def reverse[T](xs: List[T]): List[T] = (xs foldLeft z?)(op?)
  def reverse1[T](xs: List[T]): List[T] = (xs foldLeft List[T]())((xs, x) => x :: xs);System.out.println("""reverse1: [T](xs: List[T])List[T]""");$skip(786); 
  //def reverse2[T](xs: List[T]): List[T] = (xs foldLeft List[T]())(_ :: _)
  //Note: List[T]() needed / necessary for type inference
  //? () Unit / Block placeHolder for curring operator function ?
  /* 'Reverse' deduction
    computing output cases:
			1)reverse(Nil) == Nil
			  Nil
	      --> reverse(Nil)
	      --> (Nil foldLeft z?)(op)
	      --> z?
	      --> = List()
	    2)List(x)
        --> reverse(List(x))
        --> (List(x) foldLeft Nil)(op?)
        --> op?(Nil, x)
        --> op?(Nil, x) = List(x) = x :: List()
        so, using '::' operator with swapped operands
		*/

  /** using 'foldRight' implement / define : */
  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    //*(xs foldRight List[U]())(???)
    (xs foldRight List[U]())((y, ys) => f(y) :: ys);System.out.println("""mapFun: [T, U](xs: List[T], f: T => U)List[U]""");$skip(162); 

  /** using 'foldRight' implement / define : */
  def lengthFun[T](xs: List[T]): Int =
    //*(xs foldRight 0)(???)
    (xs foldRight 0)((xs, z: Int) => z + 1);System.out.println("""lengthFun: [T](xs: List[T])Int""");$skip(41); 

  val intList = List(-1, 2, -3, 4, -5);System.out.println("""intList  : List[Int] = """ + $show(intList ));$skip(84); 
  val consecList = List(
    "a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a");System.out.println("""consecList  : List[String] = """ + $show(consecList ));$skip(33); val res$0 = 

  concat1(intList, consecList);System.out.println("""res0: List[Any] = """ + $show(res$0));$skip(31); val res$1 = 
  concat2(intList, consecList);System.out.println("""res1: List[Any] = """ + $show(res$1));$skip(31); val res$2 = 
  concat3(intList, consecList);System.out.println("""res2: List[Any] = """ + $show(res$2));$skip(20); val res$3 = 
  reverse1(intList);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(16); val res$4 = 
  sum1(intList);System.out.println("""res4: Int = """ + $show(res$4));$skip(16); val res$5 = 
  sum2(intList);System.out.println("""res5: Int = """ + $show(res$5));$skip(20); val res$6 = 
  product2(intList);System.out.println("""res6: Int = """ + $show(res$6));$skip(16); val res$7 = 
  sum3(intList);System.out.println("""res7: Int = """ + $show(res$7));$skip(12); val res$8 = 
  sum3(Nil);System.out.println("""res8: Int = """ + $show(res$8));$skip(20); val res$9 = 
  product3(intList);System.out.println("""res9: Int = """ + $show(res$9));$skip(16); val res$10 = 
  sum4(intList);System.out.println("""res10: Int = """ + $show(res$10));$skip(20); val res$11 = 
  product4(intList);System.out.println("""res11: Int = """ + $show(res$11));$skip(21); val res$12 = 
  lengthFun(intList);System.out.println("""res12: Int = """ + $show(res$12));$skip(24); val res$13 = 
  lengthFun(consecList);System.out.println("""res13: Int = """ + $show(res$13));$skip(17); val res$14 = 
  lengthFun(Nil);System.out.println("""res14: Int = """ + $show(res$14));$skip(39); val res$15 = 
  mapFun(intList, ((x: Int) => x * x));System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(42); val res$16 = 
  consecList map ((x: String) => x + "1");System.out.println("""res16: List[String] = """ + $show(res$16));$skip(55); val res$17 = 
  mapFun(consecList, ((x: String) => x.toUpperCase()));System.out.println("""res17: List[String] = """ + $show(res$17))}
}
