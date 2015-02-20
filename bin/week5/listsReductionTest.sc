package week5

object listsReductionTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /*
	sum(List(x1, ..., xn)) = 0 + x1 + ... + xn
	product(List(x1, ..., xn)) = 1 * x1 * ... * xn
	*/
  def sum1(xs: List[Int]): Int = xs match {
    case Nil => 0
    case y :: ys => y + sum1(ys)
  }                                               //> sum1: (xs: List[Int])Int

  /*'reduceLeft' can only apply to non empty lists,
  so '0' is default when xs == Nil
  as 'z' parameter in 'foldLeft' method*/
  def sum2(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
                                                  //> sum2: (xs: List[Int])Int
  def product2(xs: List[Int]) = (1 :: xs) reduceLeft ((x, y) => x * y)
                                                  //> product2: (xs: List[Int])Int
  /*every '_' represent new parameter, from left to right*/
  def sum3(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
                                                  //> sum3: (xs: List[Int])Int
  def product3(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)
                                                  //> product3: (xs: List[Int])Int
  /*'z' = 0 is accumulator and default value if 'xs'== Nil*/
  def sum4(xs: List[Int]) = (xs foldLeft 0)(_ + _)//> sum4: (xs: List[Int])Int

  def product4(xs: List[Int]) = (xs foldLeft 1)(_ * _)
                                                  //> product4: (xs: List[Int])Int
  /*concatenation or '++' or ':::'*/
  def concat1[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldRight ys)(_ :: _)                     //> concat1: [T](xs: List[T], ys: List[T])List[T]

  def concat2[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldLeft ys)((xs, ys) => ys :: xs)        //> concat2: [T](xs: List[T], ys: List[T])List[T]

  def concat3[T](xs: List[T], ys: List[T]): List[T] =
    (xs foldLeft ys)((ys, xs) => xs :: ys)        //> concat3: [T](xs: List[T], ys: List[T])List[T]

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
      case Nil => throw new Error(ŏNil.reduceRightŏ)
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
  /* black magic here:
  'foldLeft' takes 'List[T]()' as parameter of type 'B'
  applyed on list & presumebly 'B' is list subtype,
  but still -
  how parameter passed without indentifier ?
  only as type with block placeholder '()'
  type of 'op' is equal / correspond, match, meet, fit, 'conform'
  of type List[T] - first function parameter & host / holder of 'foldLeft' method */
  /* ? 'inheritance' 'binding' ?*/
  /* ? referenced entity ? */
  /* ?  type constructors, which take type parameters and yield types ? */
  /* ? InfixType ? */
  /* ? Non-Value Types: Method Types ? */
  //def reverse[T](xs: List[T]): List[T] = (xs foldLeft z?)(op?)
  def reverse1[T](xs: List[T]): List[T] = (xs foldLeft List[T]())((xs, x) => x :: xs)
                                                  //> reverse1: [T](xs: List[T])List[T]
  //def reverse2[T](xs: List[T]): List[T] = (xs foldLeft List[T]())(_ :: _)
  /*Note: List[T]() needed / necessary for type inference*/
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

  def foldRight2[T, U](z: U, xs: List[T])(op: (T, U) => U): U = xs match {
    case Nil => z
    case y :: ys => op(y, (foldRight2(z, ys))(op))
  }                                               //> foldRight2: [T, U](z: U, xs: List[T])(op: (T, U) => U)U

  /*no type inference / deduction for 'op' parameters*/
  def foldRight3[T, U](z: U, xs: List[T], op: (T, U) => U): U = xs match {
    case Nil => z
    case y :: ys => op(y, foldRight3(z, ys, op))
  }                                               //> foldRight3: [T, U](z: U, xs: List[T], op: (T, U) => U)U

  def foldLeft2[T, U](z: U, xs: List[T])(op: (U, T) => U): U = xs match {
    case Nil => z
    //case x :: xs => (xs foldLeft op(z, x))(op)
    case y :: ys => foldLeft2(op(z, y), ys)(op)
  }                                               //> foldLeft2: [T, U](z: U, xs: List[T])(op: (U, T) => U)U

  /* from 'foldLeft2': ys is 'U', y is 'T' & 'U' is list from
  ? 'List[T]()' ? and 'y :: ys'
  ? is 'List[T]()' bind / passed to 'ys' indentifier as its type ?*/
  def reverse2[T](xs: List[T]): List[T] = foldLeft2(List[T](), xs)((ys, y) => y :: ys)
                                                  //> reverse2: [T](xs: List[T])List[T]
  /*? anonymus indentifiers ?
  after test: 'List[T]()' - stands for empty list 'List()' for / of type 'T'*/
  def reverse3[T](xs: List[T]): List[T] = foldLeft2(List[T](), List[T]())((ys, y) => y :: ys)
                                                  //> reverse3: [T](xs: List[T])List[T]
  /** using 'foldRight' implement / define: */
  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    //*(xs foldRight List[U]())(???)
    (xs foldRight List[U]())((y, ys) => f(y) :: ys)
                                                  //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

  /** using 'foldRight' implement / define : */
  def lengthFun[T](xs: List[T]): Int =
    //*(xs foldRight 0)(???)
    (xs foldRight 0)((xs, z: Int) => z + 1)       //> lengthFun: [T](xs: List[T])Int

  val intList = List(-1, 2, -3, 4, -5)            //> intList  : List[Int] = List(-1, 2, -3, 4, -5)
  val consecList = List(
    "a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a")
                                                  //> consecList  : List[String] = List(a, a, a, a, b, c, c, c, d, a, a)

  concat1(intList, consecList)                    //> res0: List[Any] = List(-1, 2, -3, 4, -5, a, a, a, a, b, c, c, c, d, a, a)
  concat2(intList, consecList)                    //> res1: List[Any] = List(-5, 4, -3, 2, -1, a, a, a, a, b, c, c, c, d, a, a)
  concat3(intList, consecList)                    //> res2: List[Any] = List(-5, 4, -3, 2, -1, a, a, a, a, b, c, c, c, d, a, a)
  reverse1(intList)                               //> res3: List[Int] = List(-5, 4, -3, 2, -1)
  sum1(intList)                                   //> res4: Int = -3
  sum2(intList)                                   //> res5: Int = -3
  product2(intList)                               //> res6: Int = -120
  sum3(intList)                                   //> res7: Int = -3
  sum3(Nil)                                       //> res8: Int = 0
  product3(intList)                               //> res9: Int = -120
  sum4(intList)                                   //> res10: Int = -3
  product4(intList)                               //> res11: Int = -120
  lengthFun(intList)                              //> res12: Int = 5
  lengthFun(consecList)                           //> res13: Int = 11
  lengthFun(Nil)                                  //> res14: Int = 0
  mapFun(intList, ((x: Int) => x * x))            //> res15: List[Int] = List(1, 4, 9, 16, 25)
  consecList map ((x: String) => x + "1")         //> res16: List[String] = List(a1, a1, a1, a1, b1, c1, c1, c1, d1, a1, a1)
  mapFun(consecList, ((x: String) => x.toUpperCase()))
                                                  //> res17: List[String] = List(A, A, A, A, B, C, C, C, D, A, A)
  val isNull = null                               //> isNull  : Null = null
  val symbolLiteral = 'symbolLiteral              //> symbolLiteral  : Symbol = 'symbolLiteral
  (consecList foldRight 0)((x, z) => z + 1)       //> res18: Int = 11
  foldRight2(0, consecList)((x, z) => z + 1)      //> res19: Int = 11
  foldRight3(0, consecList, (x: String, z: Int) => z + 1)
                                                  //> res20: Int = 11
  foldLeft2(0, intList)(_ + _)                    //> res21: Int = -3
  reverse2(intList)                               //> res22: List[Int] = List(-5, 4, -3, 2, -1)
  reverse2(consecList)                            //> res23: List[String] = List(a, a, d, c, c, c, b, a, a, a, a)
  reverse3(consecList)                            //> res24: List[String] = List()
}