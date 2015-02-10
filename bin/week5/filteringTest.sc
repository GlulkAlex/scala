package week5

object filteringTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

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
  }                                               //> posElems: (xs: List[Int])List[Int]

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
  }                                               //> filter1: [T](xs: List[T])(p: T => Boolean)List[T]

  /*Using 'filter',
 'posElems' can be written more concisely*/
  def posElems2(xs: List[Int]): List[Int] =
    //xs filter1 (x => x > 0)
    filter1(xs)(x => x <= 0)                      //> posElems2: (xs: List[Int])List[Int]

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
  }                                               //> compare1: [T](sample: T, criteria: T)Boolean

  def compare2[T](sample: T, criteria: T): Boolean = {
    sample match {
      case criteria => true
      case _ => false
    }
  }                                               //> compare2: [T](sample: T, criteria: T)Boolean

  def pack[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => Nil //not same list as 'xs'
      case x :: xs1 => {
        //name for parameters - matters -- may not be same as in 'pattern'
        xs.takeWhile(z => z == x) :: pack(xs.dropWhile(z => z == x)) //*work as expected
      }
    }
  }                                               //> pack: [T](xs: List[T])List[List[T]]

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
  }                                               //> pack3: [T](xs: List[T])List[List[T]]

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
  }                                               //> pack2: [T](xs: List[T])List[List[T]]

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
  }                                               //> encode: [T](xs: List[T], currentMatch: T, elemCounter: Int)List[List[(T, In
                                                  //| t)]]

  val intList = List(-1, 2, -3, 4, -5)            //> intList  : List[Int] = List(-1, 2, -3, 4, -5)
  val consecList = List(
    "a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a")
                                                  //> consecList  : List[String] = List(a, a, a, a, b, c, c, c, d, a, a)

  posElems(intList)                               //> res0: List[Int] = List(2, 4)
  posElems2(intList)                              //> res1: List[Int] = List(-1, -3, -5)

  intList filterNot (x => x == -3)                //> res2: List[Int] = List(-1, 2, 4, -5)
  intList partition (x => x == -3)                //> res3: (List[Int], List[Int]) = (List(-3),List(-1, 2, 4, -5))
  intList takeWhile (x => x > -3)                 //> res4: List[Int] = List(-1, 2)
  intList dropWhile (x => x > -3)                 //> res5: List[Int] = List(-3, 4, -5)
  intList span (x => x > -3)                      //> res6: (List[Int], List[Int]) = (List(-1, 2),List(-3, 4, -5))
  consecList span (x => x == "a")                 //> res7: (List[String], List[String]) = (List(a, a, a, a),List(b, c, c, c, d, 
                                                  //| a, a))
  consecList takeWhile (x => x == "a")            //> res8: List[String] = List(a, a, a, a)
  consecList dropWhile (x => x == "a")            //> res9: List[String] = List(b, c, c, c, d, a, a)

  /*Inﬁx Notation
	Any method with a parameter can be used like
	an 'inﬁx operator'.
	It is therefore possible to write:
	r add s  'as' r.add(s)*/
  consecList.takeWhile(x => x == x)               //> res10: List[String] = List(a, a, a, a, b, c, c, c, d, a, a)
  consecList.span(x => x == x)                    //> res11: (List[String], List[String]) = (List(a, a, a, a, b, c, c, c, d, a, a
                                                  //| ),List())
  consecList.partition(x => x == x)               //> res12: (List[String], List[String]) = (List(a, a, a, a, b, c, c, c, d, a, a
                                                  //| ),List())
  pack(consecList)                                //> res13: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  println("Expected: " +
    "List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c'), L('d'), L('a', 'a'))")
                                                  //> Expected: List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c'), L('d'), L('a', '
                                                  //| a'))
	pack2(consecList)                         //> res14: List[List[String]] = List(List(a), List(a), List(c), List(c), List(a
                                                  //| ), List(a))
	pack3(consecList)                         //> res15: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  //encode(consecList)
  println("Expected: List(('a', 4), ('b', 1), ('c', 2), ('d', 1), ('a', 2))")
                                                  //> Expected: List(('a', 4), ('b', 1), ('c', 2), ('d', 1), ('a', 2))
  compare1("a", "a")                              //> res16: Boolean = true
  compare1("a", "b")                              //> res17: Boolean = false
  compare1("a", 1)                                //> res18: Boolean = false
  compare2("a", "a")                              //> res19: Boolean = true
  compare2("a", "b")                              //> res20: Boolean = true
  compare2("a", 1)                                //> res21: Boolean = true
}