package week5
//import scala.math.pow

object listMethodsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(108); 
  println("Welcome to the Scala worksheet");$skip(159); 

  def last1[T](xs: List[T]): T = xs match {
    case List() => throw new Error("last of empty list")
    case List(x) => x
    case y :: ys => last1(ys)
  };System.out.println("""last1: [T](xs: List[T])T""");$skip(442); 

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
  };System.out.println("""init1: [T](xs: List[T])List[T]""");$skip(390);  //*works as expected

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
  };System.out.println("""init2: [T](xs: List[T])List[T]""");$skip(532); 

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
    };System.out.println("""concat1: [T](xs: List[T], ys: List[T])List[T]""");$skip(432); 

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
    };System.out.println("""reverce1: [T](xs: List[T])List[T]""");$skip(919); 

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
  };System.out.println("""removeAt1: [T](n: Int, xs: List[T])List[T]""");$skip(251); 
  //Usage example:
  //*removeAt(1, List('a', 'b', 'c', 'd'))  =>  List('a', 'c', 'd')

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def removeAt2[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop (n + 1));System.out.println("""removeAt2: [T](n: Int, xs: List[T])List[T]""");$skip(84); 
  def removeAt3[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop (n + 1));System.out.println("""removeAt3: [T](n: Int, xs: List[T])List[T]""");$skip(431); 

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
  };System.out.println("""flatten1: (xs: List[Any])List[Any]""");$skip(456); //*work as expected

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
  };System.out.println("""power: (base: Int, order: Int)Int""");$skip(258);  //*works as expected

  def power2(base: Int, order: Int): Int = {
    if (order == 0) {
      1
    } else if (order > 0) {
      base * power2(base, order - 1)
    } else { // base product themself zero times equal bace itself ? no = 1
      -1
    }
  };System.out.println("""power2: (base: Int, order: Int)Int""");$skip(351);  //*works as expected

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
  };System.out.println("""fastExpotentiation: (base: Int, order: Int)Int""");$skip(39); val res$0 = 
  //; test:
  fastExpotentiation(7, 9);System.out.println("""res0: Int = """ + $show(res$0));$skip(91); val res$1 = 
  //direct path or Fully Qualified Path Name works without 'import'
  scala.math.pow(7, 9);System.out.println("""res1: Double = """ + $show(res$1));$skip(23); val res$2 = 
  scala.math.pow(2, 2);System.out.println("""res2: Double = """ + $show(res$2));$skip(50); 

  val nums: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil;System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(49); 
  val nums2: List[Int] = 9 :: 8 :: 7 :: 6 :: Nil;System.out.println("""nums2  : List[Int] = """ + $show(nums2 ));$skip(14); val res$3 = 

  nums.last;System.out.println("""res3: Int = """ + $show(res$3));$skip(12); val res$4 = 
  nums.init;System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(16); val res$5 = 

  last1(nums);System.out.println("""res5: Int = """ + $show(res$5));$skip(14); val res$6 = 
  init1(nums);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(14); val res$7 = 
  init2(nums);System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(23); val res$8 = 
  concat1(nums, nums2);System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(18); val res$9 = 
  reverce1(nums2);System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(41); val res$10 = 
  removeAt1(1, List('a', 'b', 'c', 'd'));System.out.println("""res10: List[Char] = """ + $show(res$10));$skip(41); val res$11 = 
  removeAt1(3, List('a', 'b', 'c', 'd'));System.out.println("""res11: List[Char] = """ + $show(res$11));$skip(41); val res$12 = 
  removeAt1(5, List('a', 'b', 'c', 'd'));System.out.println("""res12: List[Char] = """ + $show(res$12));$skip(41); val res$13 = 
  removeAt2(3, List('a', 'b', 'c', 'd'));System.out.println("""res13: List[Char] = """ + $show(res$13));$skip(34); val res$14 = 
  List('a', 'b', 'c', 'd') take 1;System.out.println("""res14: List[Char] = """ + $show(res$14));$skip(34); val res$15 = 
  List('a', 'b', 'c', 'd') drop 1;System.out.println("""res15: List[Char] = """ + $show(res$15));$skip(39); val res$16 = 
  List('a', 'b', 'c', 'd') dropRight 1;System.out.println("""res16: List[Char] = """ + $show(res$16));$skip(41); val res$17 = 
  removeAt3(3, List('a', 'b', 'c', 'd'));System.out.println("""res17: List[Char] = """ + $show(res$17));$skip(30); val res$18 = 
  List('a', 'b', 'c', 'd')(0);System.out.println("""res18: Char = """ + $show(res$18));$skip(44); val res$19 = 
  List('a', 'b', 'c', 'd') updated (0, 'A');System.out.println("""res19: List[Char] = """ + $show(res$19));$skip(58); val res$20 = 
  List('a', 'b', 'c', 'd') updated (0, 'A' :: 'a' :: Nil);System.out.println("""res20: List[Any] = """ + $show(res$20));$skip(13); val res$21 = 
  Nil :: Nil;System.out.println("""res21: List[scala.collection.immutable.Nil.type] = """ + $show(res$21));$skip(27); val res$22 = 
  Nil :: List("Not Empty");System.out.println("""res22: List[java.io.Serializable] = """ + $show(res$22));$skip(25); val res$23 = 
  Nil ++ List("Not Nil");System.out.println("""res23: List[String] = """ + $show(res$23));$skip(23); val res$24 = 

  List(1) :: List(2);System.out.println("""res24: List[Any] = """ + $show(res$24));$skip(22); val res$25 = 
  List(1) ::: List(2);System.out.println("""res25: List[Int] = """ + $show(res$25));$skip(21); val res$26 = 
  List(1) ++ List(2);System.out.println("""res26: List[Int] = """ + $show(res$26));$skip(21); val res$27 = 
  List(1) :+ List(2);System.out.println("""res27: List[Any] = """ + $show(res$27));$skip(21); val res$28 = 
  List(1) +: List(2);System.out.println("""res28: List[Any] = """ + $show(res$28));$skip(152); 

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
      List(9)));System.out.println("""complexList  : List[Any] = """ + $show(complexList ));$skip(140); ;
  val complexList2 = List(
    List(
      List(
        List(-1, -2),
        -3),
      List(
        -4,
        List(-5, -6))),
    -7);System.out.println("""complexList2  : List[Any] = """ + $show(complexList2 ));$skip(244); val res$29 = 
  //example:
  //flatten(List(List(1, 1), 2, List(3, List(5, 8)))) =>
  //List[Any] = List(1, 1, 2, 3, 5, 8) -- '.heads' only ? - no complex objects included
  //or elements are primitive types only -- no 'List' allowed
  flatten1(complexList);System.out.println("""res29: List[Any] = """ + $show(res$29));$skip(25); val res$30 = 
  flatten1(complexList2);System.out.println("""res30: List[Any] = """ + $show(res$30));$skip(16); val res$31 = 
  flatten1(Nil);System.out.println("""res31: List[Any] = """ + $show(res$31));$skip(865); val res$32 = 
  // flattens a collection of collection into a single-level collection
  // err: no implicit view avalible for 'Any'
  //complexList.flatten
  // err: 'flatten' is not member of 'Any'
  //complexList.flatMap(i => i.flatten)
  /*xs flatMap f = (xs map f).flatten*/
  //complexList flatMap ((i1: List[Any]) => i1)
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
  };System.out.println("""res32: List[Char] = """ + $show(res$32));$skip(144); val res$33 = 
  complexList takeWhile {
    //case List(x) => true
    //case List(x :: xs) => true
    case x :: xs => true //*works
    case _ => false
  };System.out.println("""res33: List[Any] = """ + $show(res$33));$skip(27); val res$34 = 
  List(List(1, 2)).flatten;System.out.println("""res34: List[Int] = """ + $show(res$34));$skip(245); val res$35 = 
  /*must be one type for 'flatten'
  no mixed elements*/
  //List(List(1, 2), 7).flatten
  //List(1, 2).flatten
  complexList filter {
    //*case x: Int => true
    case x :: xs => true //*works
    case List(x) => true
    case _ => false
  };System.out.println("""res35: List[Any] = """ + $show(res$35));$skip(37); val res$36 = 

  List("a", "b", "c").zipWithIndex;System.out.println("""res36: List[(String, Int)] = """ + $show(res$36));$skip(42); val res$37 = 
  List("a", "b", "c") zip (Stream from 1);System.out.println("""res37: List[(String, Int)] = """ + $show(res$37));$skip(14); val res$38 = 
  complexList;System.out.println("""res38: List[Any] = """ + $show(res$38));$skip(19); val res$39 = 
  complexList.head;System.out.println("""res39: Any = """ + $show(res$39));$skip(19); val res$40 = 
  complexList.tail;System.out.println("""res40: List[Any] = """ + $show(res$40));$skip(28); val res$41 = 
  complexList.mkString("|");System.out.println("""res41: String = """ + $show(res$41));$skip(25); val res$42 = 
  complexList.toString();System.out.println("""res42: String = """ + $show(res$42));$skip(17); val res$43 = 

  complexList2;System.out.println("""res43: List[Any] = """ + $show(res$43));$skip(128); 

  //val patternSample = "z"
  //*val patternSample = List()
  //*val patternSample = List(1)
  val patternSample = List(1, 2);System.out.println("""patternSample  : List[Int] = """ + $show(patternSample ));$skip(408); val res$44 = 
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
  };System.out.println("""res44: String = """ + $show(res$44));$skip(97); val res$45 = 
  //power(2, 3) //2 * 2 * 2 * 2 = 16
  //power2(2, 0) //2 * 2 * 2 * 2 = 16
  1.isInstanceOf[Int];System.out.println("""res45: Boolean = """ + $show(res$45));$skip(28); val res$46 = 
  List(1).isInstanceOf[Int];System.out.println("""res46: Boolean = """ + $show(res$46));$skip(32); val res$47 = 
  List(1).isInstanceOf[List[_]];System.out.println("""res47: Boolean = """ + $show(res$47));$skip(38); val res$48 = 
  List(List(1)).isInstanceOf[List[_]];System.out.println("""res48: Boolean = """ + $show(res$48));$skip(44); val res$49 = 
  List(List(1)).isInstanceOf[List[List[_]]];System.out.println("""res49: Boolean = """ + $show(res$49));$skip(104); val res$50 = 
  //List(1).isInstanceOf[List[List[Any]]]
  //1.isInstanceOf[List[_]]
  List(1).isInstanceOf[List[Any]];System.out.println("""res50: Boolean = """ + $show(res$50))}
  //1.isInstanceOf[List[Any]]
}
