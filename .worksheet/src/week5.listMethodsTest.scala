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
  def removeAt3[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop (n + 1));System.out.println("""removeAt3: [T](n: Int, xs: List[T])List[T]""");$skip(3435); 

  def flatten1(xs: List[Any],
    remainder: List[Any] = Nil): List[Any] = {
    //may be must return : List[Any] ?
    //so list methods may be applyed ?
    //def matchTest(x: Any): Any = x match {
    /*
    pick first element,
    check if it is list, then
    extract / remove it from list,
    pass as new parameter for recursion call,
    save the rest of list as remainder parameter aka. accumulator
    each step remainder must decrease by list head
    until it is not Nil
    */
    /*
    List[Any] = List(List(1, 2), 3, List(4, List(5, 6)))
    List(List(x, xs = y) = z +: zs, x1, List(x2, List(x3, xs3 = y1) = z2 +: zs2 = y3) = z1 +: zs1)
      List(1, 2) ++ List(3, List(4, List(5, 6)))
	      List(1, 2) -- done
	        List(3, List(4, List(5, 6)))
	          List(3) ++ List(List(4, List(5, 6)))
	            List(3) -- done
	              List(List(4, List(5, 6)))
	                List(List(4)) ++  List(List(List(5, 6)))
	                  List(List(4))
	                    List(4) -- done
	                      List(List(List(5, 6)))
	                       List(List(5, 6))
	                         List(5, 6) -- done
		List(1, 2) ++ List(3) ++ List(4) ++ List(5, 6)
			List(1, 2, 3, 4, 5, 6)
    */
    def takeHead(zs: List[Any]): Any = {
      if (zs.isEmpty) {
        zs //== Nil
      } else {
        zs.head
      }
    }
    def takeTail(zs: List[Any]): Any = {
      if (zs.isEmpty) {
        zs //== Nil
      } else {
        zs.tail
      }
    }
    def isList(elem: Any): Boolean =
      elem match {
        case List(x) => true
        case x: List[Any] => true
        case x: Any => false
        //for exeptions
        case _ => false
      } //*works

    def unFoldList(ys: List[Any],
      remainder: List[Any] = Nil): List[Any] = {
      Nil
      /*ys match {
        /* order of 'case' matters */
        //zero length / empty list
        //pattern: (Nil)
        case Nil => ys
        case z :: Nil => ys ++ List("val")
        //last / one element / basic case -
        //must return primitive value or (not list)
        //but here List[Any] expected
        //pattern: (not list) :: (Nil)
        case z :: zs => List(z) ++ List("list") ++ unFoldList(zs)
        //something else:
        case _ => Nil
        //case List(x: List[Any]) => flatten1(x)//no effect on result
        /*do until got (not List) then prepend it to the rest*/
        //while '.head' in xs == (z :: zs) recursivly flatten1 parts of it
        //& prepend before the rest
        //pattern: (list) :: (list or Nil)
      }*/
    }
    //flatten1(xs: List[Any])
    /*for (element <- xs) {
      element match {*/
      xs match {
        //case Nil => print("{Nil}>")
        //case x +: xs => print("x +: xs>")
        //case List() => print("{List()}>")
        //case List(x1) => print("{List(" + x1 + ")}>")
        //case List(List(x)) => print("{List(List(" + x + "))}>")
        case Nil => remainder
        case x2 :: xs2 => flatten1(xs2, x2 :: remainder)
        case x3 => x3 :: remainder
        //*case x2: List[Any] => print("{" + flatten1(x2) + ":List[Any]}>")
        //case x3: Any => List(element)
        //case x3: Any => List(x3)
        //case x3: Any => x3 :: Nil
        //*case x3: Any => print("{" + x3 + ":Any}>")
        //*case _ => print("{?}>")
        case _ => Nil
      }
      //println("element: " + element)
    //*}
    
    //*unFoldList(Nil) // :: Nil
  };System.out.println("""flatten1: (xs: List[Any], remainder: List[Any])List[Any]""");$skip(456); 

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
  Nil ++ List("Not Nil");System.out.println("""res23: List[String] = """ + $show(res$23));$skip(25); val res$24 = 
  
  List(1) :: List(2);System.out.println("""res24: List[Any] = """ + $show(res$24));$skip(22); val res$25 = 
  List(1) ::: List(2);System.out.println("""res25: List[Int] = """ + $show(res$25));$skip(21); val res$26 = 
  List(1) ++ List(2);System.out.println("""res26: List[Int] = """ + $show(res$26));$skip(21); val res$27 = 
  List(1) :+ List(2);System.out.println("""res27: List[Any] = """ + $show(res$27));$skip(21); val res$28 = 
  List(1) +: List(2);System.out.println("""res28: List[Any] = """ + $show(res$28));$skip(90); 

  val complexList = List(
    List(1, 2),
    3,
    List(
      4,
      List(5, 6)));System.out.println("""complexList  : List[Any] = """ + $show(complexList ));$skip(140); ;
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
  flatten1(Nil);System.out.println("""res31: List[Any] = """ + $show(res$31));$skip(35); val res$32 = 
  List("a", "b", "c").zipWithIndex;System.out.println("""res32: List[(String, Int)] = """ + $show(res$32));$skip(42); val res$33 = 
  List("a", "b", "c") zip (Stream from 1);System.out.println("""res33: List[(String, Int)] = """ + $show(res$33));$skip(14); val res$34 = 
  complexList;System.out.println("""res34: List[Any] = """ + $show(res$34));$skip(19); val res$35 = 
  complexList.head;System.out.println("""res35: Any = """ + $show(res$35));$skip(19); val res$36 = 
  complexList.tail;System.out.println("""res36: List[Any] = """ + $show(res$36));$skip(28); val res$37 = 
  complexList.mkString("|");System.out.println("""res37: String = """ + $show(res$37));$skip(25); val res$38 = 
  complexList.toString();System.out.println("""res38: String = """ + $show(res$38));$skip(17); val res$39 = 

  complexList2;System.out.println("""res39: List[Any] = """ + $show(res$39));$skip(128); 

  //val patternSample = "z"
  //*val patternSample = List()
  //*val patternSample = List(1)
  val patternSample = List(1, 2);System.out.println("""patternSample  : List[Int] = """ + $show(patternSample ));$skip(408); val res$40 = 
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
  };System.out.println("""res40: String = """ + $show(res$40));$skip(97); val res$41 = 
  //power(2, 3) //2 * 2 * 2 * 2 = 16
  //power2(2, 0) //2 * 2 * 2 * 2 = 16
  1.isInstanceOf[Int];System.out.println("""res41: Boolean = """ + $show(res$41));$skip(28); val res$42 = 
  List(1).isInstanceOf[Int];System.out.println("""res42: Boolean = """ + $show(res$42));$skip(32); val res$43 = 
  List(1).isInstanceOf[List[_]];System.out.println("""res43: Boolean = """ + $show(res$43));$skip(38); val res$44 = 
  List(List(1)).isInstanceOf[List[_]];System.out.println("""res44: Boolean = """ + $show(res$44));$skip(44); val res$45 = 
  List(List(1)).isInstanceOf[List[List[_]]];System.out.println("""res45: Boolean = """ + $show(res$45));$skip(104); val res$46 = 
  //List(1).isInstanceOf[List[List[Any]]]
  //1.isInstanceOf[List[_]]
  List(1).isInstanceOf[List[Any]];System.out.println("""res46: Boolean = """ + $show(res$46))}
  //1.isInstanceOf[List[Any]]
}
