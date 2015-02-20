package week5
//import scala.math.pow

object listMethodsTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def last1[T](xs: List[T]): T = xs match {
    case List() => throw new Error("last of empty list")
    case List(x) => x
    case y :: ys => last1(ys)
  }                                               //> last1: [T](xs: List[T])T

  /* Implement 'init' as
  an external function,
  analogous to last.*/
  //return: all elements but last one
  def init1[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    //last elem
    case List(x) => Nil
    //y == xs.head; ys == xs.tail
    case y :: ys => ys match {
      case List(z) => List(y) //? what this for ?
      case z :: zs => y :: init1(ys)
    }
  } //*works as expected                          //> init1: [T](xs: List[T])List[T]

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def init2[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    //last elem
    //actualy reduce list with more then 1 element to (elem :: Nil)
    //that is the goal
    case List(x) => Nil
    //y == xs.head; ys == xs.tail
    case y :: ys => y :: init2(ys)
  }                                               //> init2: [T](xs: List[T])List[T]

  /* concatenation:
  xs ::: ys = ys.:::(xs)
  prepend ys with xs */
  def concat1[T](xs: List[T], ys: List[T]): List[T] =
    //first part of 'concat' 'xs' - checks firstly
    xs match {
      case List() => ys
      //if xs not empty / Nil
      //then prepend it's '.head' to returning list
      //& call recursion on the rest
      //y == xs.head; ys == xs.tail
      //complexity is |xs| -- length of 'xs'
      /*prepend 'xs.head' then / to  'xs.tail' then / to all 'ys'*/
      case z :: zs => z :: concat1(zs, ys)
    }                                             //> concat1: [T](xs: List[T], ys: List[T])List[T]

  def reverce1[T](xs: List[T]): List[T] =
    //first part of 'concat' 'xs' - checks firstly
    xs match {
      case List() => xs //or Nil
      //if xs not empty / Nil
      //then swap '.head' with '.tail'
      //& call recursion on the former '.tail'
      //y == xs.head; ys == xs.tail
      //complexity is xs * xs -- quadratic of input
      case z :: zs => concat1(reverce1(zs), List(z)) //reverce1(zs) ++ List(z)
    }                                             //> reverce1: [T](xs: List[T])List[T]

  /*Remove the 'n-th' element of a list 'xs'.
	If 'n' is out of bounds,
	return 'xs' itself.*/
  //must be counter parameter
  //for state check --
  //is current list element is at 'n' index ?
  def removeAt1[T](n: Int, xs: List[T]): List[T] = {
    xs match {
      //zero length / empty list
      case List() => xs //or Nil
      //'n' > then length of list
      //case List() => xs
      //'n' == current '.head' index
      //make & return a new list without curren '.head'
      case y :: ys => if (n == 0) {
        ys //if 'n' = 0 index -- first element in list
      } else {
        //else
        //reduce list to it's '.head' that must stay as prepend part of list
        //& call recursion on '.tail' with 'n - 1'
        y :: removeAt1(n - 1, ys)
      }
      /*if (n == 0 || xs == Nil) {
 				xs
 			} else {
 				if (xs == Nil) {
 					xs.head :: removeAt(n - 1, xs.tail)
 				}
 			}*/
    }
  }                                               //> removeAt1: [T](n: Int, xs: List[T])List[T]
  //Usage example:
  //*removeAt(1, List('a', 'b', 'c', 'd'))  =>  List('a', 'c', 'd')

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def removeAt2[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop (n + 1))
                                                  //> removeAt2: [T](n: Int, xs: List[T])List[T]
  def removeAt3[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop (n + 1))
                                                  //> removeAt3: [T](n: Int, xs: List[T])List[T]

  def flatten1(xs: List[Any]): List[Any] = {
    xs match {
      //compaund value: List & {Nil | List}
      case (y :: ys) :: zs => flatten1(y :: ys) ++ flatten1(zs) //*works
      /*what about tail ?*/
      case y :: ys => y :: flatten1(ys) //*works
      //case Nil => xs //*works
      //simple value
      case y => xs //or y :: Nil //*works
      /*exception handler*/
      //*case _ => Nil
    }
  }//*work as expected                            //> flatten1: (xs: List[Any])List[Any]

  /*for positive order*/
  //power(2 , 0) error: not :Int
  def power(base: Int, order: Int): Int = {
    def iterator(iterOrder: Int = order, accum: Int = 1): Int = {
      //*def iterator(iterOrder: Int, accum: Int): Int = {
      if (iterOrder == 0) {
        accum
      } else if (iterOrder > 0) {
        iterator(iterOrder - 1, base * accum)
      } else {
        0
      }
    }
    iterator()
    //*iterator(order, 1)
  } //*works as expected                          //> power: (base: Int, order: Int)Int

  def power2(base: Int, order: Int): Int = {
    if (order == 0) {
      1
    } else if (order > 0) {
      base * power2(base, order - 1)
    } else { // base product themself zero times equal bace itself ? no = 1
      -1
    }
  } //*works as expected                          //> power2: (base: Int, order: Int)Int

  def fastExpotentiation(base: Int, order: Int): Int = {
    def isEven(n: Int): Boolean = {
      0 == n % 2 //4 % 2 //1 % 2
    }
    def square(x: Int) = x * x

    if (order == 0) {
      1
    } else if (isEven(order)) {
      square(fastExpotentiation(base, order / 2))
    } else {
      base * fastExpotentiation(base, order - 1)
    }
  }                                               //> fastExpotentiation: (base: Int, order: Int)Int
  //; test:
  fastExpotentiation(7, 9)                        //> res0: Int = 40353607
  //direct path or Fully Qualified Path Name works without 'import'
  scala.math.pow(7, 9)                            //> res1: Double = 4.0353607E7
  scala.math.pow(2, 2)                            //> res2: Double = 4.0

  val nums: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil   //> nums  : List[Int] = List(0, 1, 2, 3)
  val nums2: List[Int] = 9 :: 8 :: 7 :: 6 :: Nil  //> nums2  : List[Int] = List(9, 8, 7, 6)

  nums.last                                       //> res3: Int = 3
  nums.init                                       //> res4: List[Int] = List(0, 1, 2)

  last1(nums)                                     //> res5: Int = 3
  init1(nums)                                     //> res6: List[Int] = List(0, 1, 2)
  init2(nums)                                     //> res7: List[Int] = List(0, 1, 2)
  concat1(nums, nums2)                            //> res8: List[Int] = List(0, 1, 2, 3, 9, 8, 7, 6)
  reverce1(nums2)                                 //> res9: List[Int] = List(6, 7, 8, 9)
  removeAt1(1, List('a', 'b', 'c', 'd'))          //> res10: List[Char] = List(a, c, d)
  removeAt1(3, List('a', 'b', 'c', 'd'))          //> res11: List[Char] = List(a, b, c)
  removeAt1(5, List('a', 'b', 'c', 'd'))          //> res12: List[Char] = List(a, b, c, d)
  removeAt2(3, List('a', 'b', 'c', 'd'))          //> res13: List[Char] = List(a, b, c)
  List('a', 'b', 'c', 'd') take 1                 //> res14: List[Char] = List(a)
  List('a', 'b', 'c', 'd') drop 1                 //> res15: List[Char] = List(b, c, d)
  List('a', 'b', 'c', 'd') dropRight 1            //> res16: List[Char] = List(a, b, c)
  removeAt3(3, List('a', 'b', 'c', 'd'))          //> res17: List[Char] = List(a, b, c)
  List('a', 'b', 'c', 'd')(0)                     //> res18: Char = a
  List('a', 'b', 'c', 'd') updated (0, 'A')       //> res19: List[Char] = List(A, b, c, d)
  List('a', 'b', 'c', 'd') updated (0, 'A' :: 'a' :: Nil)
                                                  //> res20: List[Any] = List(List(A, a), b, c, d)
  Nil :: Nil                                      //> res21: List[scala.collection.immutable.Nil.type] = List(List())
  Nil :: List("Not Empty")                        //> res22: List[java.io.Serializable] = List(List(), Not Empty)
  Nil ++ List("Not Nil")                          //> res23: List[String] = List(Not Nil)

  List(1) :: List(2)                              //> res24: List[Any] = List(List(1), 2)
  List(1) ::: List(2)                             //> res25: List[Int] = List(1, 2)
  List(1) ++ List(2)                              //> res26: List[Int] = List(1, 2)
  List(1) :+ List(2)                              //> res27: List[Any] = List(1, List(2))
  List(1) +: List(2)                              //> res28: List[Any] = List(List(1), 2)

  val complexList = List(
    List(1, 2),
    3,
    Nil,
    List(
      4,
      List(5, 6)),
    Nil,
    List(
      List(7, 8),
      List(9)));                                  //> complexList  : List[Any] = List(List(1, 2), 3, List(), List(4, List(5, 6)),
                                                  //|  List(), List(List(7, 8), List(9)))
  val complexList2 = List(
    List(
      List(
        List(-1, -2),
        -3),
      List(
        -4,
        List(-5, -6))),
    -7)                                           //> complexList2  : List[Any] = List(List(List(List(-1, -2), -3), List(-4, List
                                                  //| (-5, -6))), -7)
  //example:
  //flatten(List(List(1, 1), 2, List(3, List(5, 8)))) =>
  //List[Any] = List(1, 1, 2, 3, 5, 8) -- '.heads' only ? - no complex objects included
  //or elements are primitive types only -- no 'List' allowed
  flatten1(complexList)                           //> res29: List[Any] = List(1, 2, 3, List(), 4, 5, 6, List(), 7, 8, 9)
  flatten1(complexList2)                          //> res30: List[Any] = List(-1, -2, -3, -4, -5, -6, -7)
  flatten1(Nil)                                   //> res31: List[Any] = List()
  // flattens a collection of collection into a single-level collection
  // err: no implicit view avalible for 'Any'
  //complexList.flatten
  // err: 'flatten' is not member of 'Any'
  //complexList.flatMap(i => i.flatten)
  complexList.flatMap {
    //case x :: xs => x.flatten
    //case List(x) => List(x).flatten
    //case x: List[Int] => "[List(Int)]"
    //case !List(x) => "[List]"
    case (y :: ys) :: xs => "[(y&ys)&xs]" //*works
    case x :: xs => "[x&xs]" //*works
    case Nil => "[Nil]" //*works
    case x => "[x]" //*works
    case x: Int => "[Int]"
    case List() => "[Nil]"
    case List(List(x)) => "[List(List)]"
    case List(x: Any) => "[List(Any)]"
    case List(x) => "[List]"
    case List(x: Int) => "[List(Int)]"
    case _ => "[undefined]"
  }                                               //> res32: List[Char] = List([, x, &, x, s, ], [, x, ], [, N, i, l, ], [, x, &,
                                                  //|  x, s, ], [, N, i, l, ], [, (, y, &, y, s, ), &, x, s, ])
  complexList takeWhile {
    //case List(x) => true
    //case List(x :: xs) => true
    case x :: xs => true //*works
    case _ => false
  }                                               //> res33: List[Any] = List(List(1, 2))
  List(List(1, 2)).flatten                        //> res34: List[Int] = List(1, 2)
  /*must be one type for 'flatten'
  no mixed elements*/
  //List(List(1, 2), 7).flatten
  //List(1, 2).flatten
  complexList filter {
    //*case x: Int => true
    case x :: xs => true //*works
    case List(x) => true
    case _ => false
  }                                               //> res35: List[Any] = List(List(1, 2), List(4, List(5, 6)), List(List(7, 8), L
                                                  //| ist(9)))

  List("a", "b", "c").zipWithIndex                //> res36: List[(String, Int)] = List((a,0), (b,1), (c,2))
  List("a", "b", "c") zip (Stream from 1)         //> res37: List[(String, Int)] = List((a,1), (b,2), (c,3))
  complexList                                     //> res38: List[Any] = List(List(1, 2), 3, List(), List(4, List(5, 6)), List(),
                                                  //|  List(List(7, 8), List(9)))
  complexList.head                                //> res39: Any = List(1, 2)
  complexList.tail                                //> res40: List[Any] = List(3, List(), List(4, List(5, 6)), List(), List(List(7
                                                  //| , 8), List(9)))
  complexList.mkString("|")                       //> res41: String = List(1, 2)|3|List()|List(4, List(5, 6))|List()|List(List(7,
                                                  //|  8), List(9))
  complexList.toString()                          //> res42: String = List(List(1, 2), 3, List(), List(4, List(5, 6)), List(), Li
                                                  //| st(List(7, 8), List(9)))

  complexList2                                    //> res43: List[Any] = List(List(List(List(-1, -2), -3), List(-4, List(-5, -6))
                                                  //| ), -7)

  //val patternSample = "z"
  //*val patternSample = List()
  //*val patternSample = List(1)
  val patternSample = List(1, 2)                  //> patternSample  : List[Int] = List(1, 2)
  //*val patternSample = List(List(1), 2)
  //*val patternSample = List(List(1))
  patternSample match {
    case Nil => "Nil"
    //*case List() => "List()"
    //case (y :: ys) +: xs => "(y +: ys) +: xs"
    case x => "x" //* for List(List(1))
    case x +: xs => "x +: xs"
    case List(x) => "List(x)" //* for List(List(1))
    case x: Any => "x: Any" //* for List(List(1))
    case _ => "undefined"
  }                                               //> res44: String = x
  //power(2, 3) //2 * 2 * 2 * 2 = 16
  //power2(2, 0) //2 * 2 * 2 * 2 = 16
  1.isInstanceOf[Int]                             //> res45: Boolean = true
  List(1).isInstanceOf[Int]                       //> res46: Boolean = false
  List(1).isInstanceOf[List[_]]                   //> res47: Boolean = true
  List(List(1)).isInstanceOf[List[_]]             //> res48: Boolean = true
  List(List(1)).isInstanceOf[List[List[_]]]       //> res49: Boolean = true
  //List(1).isInstanceOf[List[List[Any]]]
  //1.isInstanceOf[List[_]]
  List(1).isInstanceOf[List[Any]]                 //> res50: Boolean = true
  //1.isInstanceOf[List[Any]]
}