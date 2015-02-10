package week5

object filteringTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 
  println("Welcome to the Scala worksheet");$skip(327); 

  /**
   * Filtering
   * Another common operation on lists is
   * the selection of all elements
   * satisfying a given condition:
   */
  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => xs
    case y :: ys =>
      if (y > 0) {
        y :: posElems(ys)
      } else {
        posElems(ys)
      }
  };System.out.println("""posElems: (xs: List[Int])List[Int]""");$skip(501); 

  /**
   * Filter
   * the pattern is generalized by
   * the method 'filter' of the 'List' class:
   */
  /*abstract class List[T] {
    //...
    def filter(p: T => Boolean): List[T] = this match {
      case Nil => this
      case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
    }
  }*/

  def filter1[T](xs: List[T])(p: T => Boolean): List[T] = xs match {
    case Nil => Nil //this
    case y :: ys => if (p(y)) {
      y :: ys.filter(p)
    } else {
      xs.filter(p)
    }
  };System.out.println("""filter1: [T](xs: List[T])(p: T => Boolean)List[T]""");$skip(169); 

  /*Using 'filter',
 'posElems' can be written more concisely*/
  def posElems2(xs: List[Int]): List[Int] =
    //xs filter1 (x => x > 0)
    filter1(xs)(x => x <= 0);System.out.println("""posElems2: (xs: List[Int])List[Int]""");$skip(355); 

  /*function 'pack' that
  packs consecutive duplicates of list elements
  into sublists
		pack(List("a", "a", "a", "b", "c", "c", "a"))
	should give:
		List(List("a", "a", "a"), List("b"), List("c", "c"), List("a")).
	*/
  def compare1[T](sample: T, criteria: T): Boolean = {
    if (sample == criteria) {
      true
    } else {
      false
    }
  };System.out.println("""compare1: [T](sample: T, criteria: T)Boolean""");$skip(136); 

  def compare2[T](sample: T, criteria: T): Boolean = {
    sample match {
      case criteria => true
      case _ => false
    }
  };System.out.println("""compare2: [T](sample: T, criteria: T)Boolean""");$skip(316); 

  def pack[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => Nil //not same list as 'xs'
      case x :: xs1 => {
        //name for parameters - matters -- may not be same as in 'pattern'
        xs.takeWhile(z => z == x) :: pack(xs.dropWhile(z => z == x)) //*work as expected
      }
    }
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(351); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def pack3[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => Nil
      case x :: xs1 => {
      	//Note: 'span' is faster then 'takeWhile' 'dropWhile' together
      val (first, rest) = xs span (y => y == x)
        first :: pack(rest)
      }
    }
  };System.out.println("""pack3: [T](xs: List[T])List[List[T]]""");$skip(922); 

  def pack2[T](xs: List[T]): List[List[T]] = {
    /*@tailrec
    def loop(xs: List[A]): List[A] =
      if (
      	xs.isEmpty ||
      	!p(xs.head) //boolean condition,
      	//comparasion with current leftmost 'head'
      	) {
      	xs }
      else {// 'isEmpty' = false, so, it has 'tail'
      	loop(xs.tail)
      	}

    loop(this)*/
    xs match {
      case Nil => xs :: Nil //Nil //not same list as 'xs'
      case x :: xs1 => {
        xs1 match {
          case Nil => {
            //'x' is last element, no need to compare ? or imposible to do so
            List(x :: Nil)
          }
          case y :: ys1 =>
            //if (x == y) {
            //if (xs.head == xs.tail.head) {
            if (y == xs.head) {
              //'takeWhile' :: 'dropWhile'
              (x :: Nil) :: pack2(ys1)
            } else {
              (y :: Nil) :: pack2(ys1)
            }
        }
      }
    }
  };System.out.println("""pack2: [T](xs: List[T])List[List[T]]""");$skip(2217); 

  /*Using 'pack'
  write a function 'encode' that produce
	the run-length encoding of list
	The idea is to
	'encode' 'n' consecutive duplicates of an element 'x' as
	a pair of (x, n):
		encode(List('a', 'a', 'a', 'b', 'c', 'c', 'a'))
	should give:
		List(('a', 3), ('b', 1), ('c', 2), ('a', 1)).*/
  def encode[T](xs: List[T],
    currentMatch: T,
    elemCounter: Int = 1): List[List[Tuple2[T, Int]]] = {
    //def counter(elemCounter: Int): Int = elemCounter// + 1
    var counter = elemCounter

    def checkRepeat(currElem: T, currentMatch: T /*, currList: List[T]*/ ) = {
      if (elemCounter > 0) { //exist / defined
        if (currElem == currentMatch) {
          //keep counting
          counter += 1;
        } else {
          /*return val (label, value) = pair =
            "(" + currentMatch + ", " + counter + ")";*/
          (currElem, counter)
          //currentMatch = currElem; //undefined;//reset
          counter = 1; //reset
        }
      } else { //not exist / undefined
        //initialize
        //currentMatch = currElem;
        iter(List(currElem) /*.tail*/ , currElem, 1)
      }
    }

    /* may be it must be tail recursive */
    //def iter(currElem: T) {
    def iter(currList: List[T], currentMatch: T, counter: Int) = {
      //if (currList.head ne/*!==*/ Nil) { //exist
      //*console.log('index = ' + index);
      //checkRepeat(currList.head, currList.head);
      /*if (index < someArray.lenght) {
					console.log('index incrementation');
				}*/
      //} else {
      //*console.log('index out of bound');
      //return ;
      //}

      //iter(currList.tail); //tail recursion
    }

    //xs, xs.head - may be = Nil or 'exception'
    //iter(xs, xs.head); //first step / initialization
    //return //List[List[Tuple2[T, Int]];

    xs match {
      case Nil => Nil //empty list
      case x :: xs1 => {
        xs1 match {
          case Nil =>
            List((x, 1) :: Nil)
          case y :: ys1 =>
            if (x == y) {
              List((y, (elemCounter + 1)) :: Nil) /*++
                encode(ys1, (elemCounter + 1))*/
            } else {
              List((y, 1) :: Nil) //++ encode(ys1)
            }

        }
      }
    }
  };System.out.println("""encode: [T](xs: List[T], currentMatch: T, elemCounter: Int)List[List[(T, Int)]]""");$skip(41); 

  val intList = List(-1, 2, -3, 4, -5);System.out.println("""intList  : List[Int] = """ + $show(intList ));$skip(84); 
  val consecList = List(
    "a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a");System.out.println("""consecList  : List[String] = """ + $show(consecList ));$skip(22); val res$0 = 

  posElems(intList);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(21); val res$1 = 
  posElems2(intList);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(37); val res$2 = 

  intList filterNot (x => x == -3);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(35); val res$3 = 
  intList partition (x => x == -3);System.out.println("""res3: (List[Int], List[Int]) = """ + $show(res$3));$skip(34); val res$4 = 
  intList takeWhile (x => x > -3);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(34); val res$5 = 
  intList dropWhile (x => x > -3);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(29); val res$6 = 
  intList span (x => x > -3);System.out.println("""res6: (List[Int], List[Int]) = """ + $show(res$6));$skip(34); val res$7 = 
  consecList span (x => x == "a");System.out.println("""res7: (List[String], List[String]) = """ + $show(res$7));$skip(39); val res$8 = 
  consecList takeWhile (x => x == "a");System.out.println("""res8: List[String] = """ + $show(res$8));$skip(39); val res$9 = 
  consecList dropWhile (x => x == "a");System.out.println("""res9: List[String] = """ + $show(res$9));$skip(185); val res$10 = 

  /*In?x Notation
	Any method with a parameter can be used like
	an 'in?x operator'.
	It is therefore possible to write:
	r add s  'as' r.add(s)*/
  consecList.takeWhile(x => x == x);System.out.println("""res10: List[String] = """ + $show(res$10));$skip(31); val res$11 = 
  consecList.span(x => x == x);System.out.println("""res11: (List[String], List[String]) = """ + $show(res$11));$skip(36); val res$12 = 
  consecList.partition(x => x == x);System.out.println("""res12: (List[String], List[String]) = """ + $show(res$12));$skip(19); val res$13 = 
  pack(consecList);System.out.println("""res13: List[List[String]] = """ + $show(res$13));$skip(102); 
  println("Expected: " +
    "List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c'), L('d'), L('a', 'a'))");$skip(19); val res$14 = 
	pack2(consecList);System.out.println("""res14: List[List[String]] = """ + $show(res$14));$skip(19); val res$15 = 
	pack3(consecList);System.out.println("""res15: List[List[String]] = """ + $show(res$15));$skip(101); 
  //encode(consecList)
  println("Expected: List(('a', 4), ('b', 1), ('c', 2), ('d', 1), ('a', 2))");$skip(21); val res$16 = 
  compare1("a", "a");System.out.println("""res16: Boolean = """ + $show(res$16));$skip(21); val res$17 = 
  compare1("a", "b");System.out.println("""res17: Boolean = """ + $show(res$17));$skip(19); val res$18 = 
  compare1("a", 1);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(21); val res$19 = 
  compare2("a", "a");System.out.println("""res19: Boolean = """ + $show(res$19));$skip(21); val res$20 = 
  compare2("a", "b");System.out.println("""res20: Boolean = """ + $show(res$20));$skip(19); val res$21 = 
  compare2("a", 1);System.out.println("""res21: Boolean = """ + $show(res$21))}
}
