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

  /**
   * 'p' as / is a predicate
   * boolean condition for elements of list
   * defines whether or not element be included in
   * the resulting output list
   */
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
  //<=> to ((y) => {y == x}) or y => y == x
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
  } //not work as expected                        //> compare2: [T](sample: T, criteria: T)Boolean

  def compare3[T](sample: T, criteria: T): Boolean = {
    return sample == criteria
  }                                               //> compare3: [T](sample: T, criteria: T)Boolean

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
        //? presumably 'y' is current element of the list in 'span' traversal on 'xs' ?
        val (first, rest) = xs span (y => y == x)
        first :: pack3(rest)
      }
    }
  }                                               //> pack3: [T](xs: List[T])List[List[T]]

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def encode2[T](xs: List[T]): List[(T, Int)] = {
  	//? 'x' as pattern matching on 'xs'
  	//replacing each element of 'xs' by 'pair'
  	pack3(xs) map (x => (x.head, x.length))
  }                                               //> encode2: [T](xs: List[T])List[(T, Int)]
  
  /**
   * TODO:
   * finish it
   */
  def pack2[T](xs: List[T]): List[List[T]] = {
    //@tailrec
    def loop(zs: List[T], sample: T): List[T] =
      if (zs.isEmpty ||
        !(zs.head == sample) //boolean condition,
        //comparasion with current leftmost 'head'
        ) {
        printf("zs return: %s; ", zs)
        printf("!zs.head return: %s; ", zs.head)
        zs //it must store all previous loop call
      } else { // 'isEmpty' = false, so, it has 'tail'
        print("recursion: ; ")
        printf("zs.head return: %s; ", zs.head)
        loop(zs.tail, zs.head)
      }

    //loop(this)*/

    //pack as 'loop' starter
    //return: first :: pack(rest)
    //as: first1 :: (first2 :: (first3 ... )) :: Nill
    //where: firstN = (x :: x :: ... :: Nil)
    xs match {
      case Nil => xs :: Nil //Nil //not same list as 'xs'
      case x :: xs1 => {
        Console.err.println("first loop call, sample is: " + x)
        return loop(xs, x) :: Nil
        /*xs1 match {
          case Nil => {
            //'x' is last element, no need to compare ? or imposible to do so
            //nothing with compare
            List(x :: Nil) //or xs :: Nil ?
          }
          case y :: ys1 =>
            //if (x == y) {
            //if (xs.head == xs.tail.head) {
            if (y == xs.head) {
              //'takeWhile' :: 'dropWhile'
              (x :: Nil) :: pack2(xs1)
            } else {
              (y :: Nil) :: pack2(xs1)
            }
        }*/
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
  def encode[T](xs: List[T]): List[(T, Int)] = { //list of pairs
    /* may be it must be tail recursive */
    //def iter(currElem: T) {
    def iter(currList: List[T],
      currentMatch: T,
      counter: Int = 0,
      pairList: List[(T, Int)] = Nil): List[(T, Int)] = {
      if (currList.isEmpty) { //has no 'tail' or Nil
        return /*currList*/ pairList ::: ((currentMatch, counter) :: Nil)
      } else if (!(currList.head == currentMatch)) {
        //sequense stops
        return iter(currList.tail,
          currList.head,
          1,
          pairList ::: ((currentMatch, counter) :: Nil));
      } else {
        //same 'currentMatch'
        //sequense continues
        return iter(currList.tail, currentMatch, counter + 1, pairList); //tail recursion
      }
    }

    xs match {
      case Nil => Nil //empty list
      case x :: xs1 => {
        iter(xs, x) //initialization
      }
    }
  } //work as expected                            //> encode: [T](xs: List[T])List[(T, Int)]

  def loop[T](yS: List[T], counter: Int = 0): Int = {
    yS match {
      case Nil => counter
      case z :: zS => {
        loop(zS, counter + 1)
      }
    }
  }//work as expected                             //> loop: [T](yS: List[T], counter: Int)Int

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
  //looks for prefix
  consecList takeWhile (x => x == "a")            //> res8: List[String] = List(a, a, a, a)
  //looks for suffix / postfix / ending
  consecList dropWhile (x => x == "a")            //> res9: List[String] = List(b, c, c, c, d, a, a)

  /*Inﬁx Notation
	Any method with a parameter can be used like
	an 'inﬁx operator'.
	It is therefore possible to write:
	r add s  'as' r.add(s)*/
  consecList.takeWhile(x => x == x)               //> res10: List[String] = List(a, a, a, a, b, c, c, c, d, a, a)
  //runs in single traversal
  consecList.span(x => x == x)                    //> res11: (List[String], List[String]) = (List(a, a, a, a, b, c, c, c, d, a, a
                                                  //| ),List())
  //runs in single traversal
  consecList.partition(x => x == x)               //> res12: (List[String], List[String]) = (List(a, a, a, a, b, c, c, c, d, a, a
                                                  //| ),List())
  pack(consecList)                                //> res13: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  println("Expected: ")                           //> Expected: 
  println("List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c', 'c'), L('d'), L('a', 'a'))")
                                                  //> List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c', 'c'), L('d'), L('a', 'a'))
                                                  //| 
  pack2(consecList)                               //> first loop call, sample is: a
                                                  //| recursion: ; zs.head return: a; recursion: ; zs.head return: a; recursion: 
                                                  //| ; zs.head return: a; recursion: ; zs.head return: a; zs return: List(b, c, 
                                                  //| c, c, d, a, a); !zs.head return: b; res14: List[List[String]] = List(List(b
                                                  //| , c, c, c, d, a, a))
  pack3(consecList)                               //> res15: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  encode(consecList)                              //> res16: List[(String, Int)] = List((a,4), (b,1), (c,3), (d,1), (a,2))
  println("Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1), ('a', 2))")
                                                  //> Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1), ('a', 2))
  compare1("a", "a")                              //> res17: Boolean = true
  compare1("a", "b")                              //> res18: Boolean = false
  compare1("a", 1)                                //> res19: Boolean = false
  compare2("a", "a")                              //> res20: Boolean = true
  compare2("a", "b")                              //> res21: Boolean = true
  compare2("a", 1)                                //> res22: Boolean = true
  compare3("a", "a")                              //> res23: Boolean = true
  compare3("a", "b")                              //> res24: Boolean = false
  compare3("a", 1)                                //> res25: Boolean = false
  (1 :: Nil) :: (2 :: Nil) :: (3 :: Nil) :: Nil   //> res26: List[List[Int]] = List(List(1), List(2), List(3))
  Nil.isEmpty                                     //> res27: Boolean = true
  Nil :: (1 :: Nil)                               //> res28: List[Any] = List(List(), 1)
  encode(Nil)                                     //> res29: List[(Nothing, Int)] = List()
  encode(1 :: 'a' :: Nil)                         //> res30: List[(AnyVal, Int)] = List((1,1), (a,1))
  encode(2 :: 2 :: 'a' :: 'a' :: 3 :: 3 :: 3 :: 'b' :: 7 :: 4 :: 4 :: 4 :: 4 :: 'c' :: 'c' :: Nil)
                                                  //> res31: List[(AnyVal, Int)] = List((2,2), (a,2), (3,3), (b,1), (7,1), (4,4),
                                                  //|  (c,2))
  ('x', 1) :: Nil                                 //> res32: List[(Char, Int)] = List((x,1))
  (('x', 1) :: Nil) :: Nil                        //> res33: List[List[(Char, Int)]] = List(List((x,1)))
  List(List(('x', 1)))                            //> res34: List[List[(Char, Int)]] = List(List((x,1)))
  List(List(('x', 1))).last                       //> res35: List[(Char, Int)] = List((x,1))
  List(List(('x', 1))).last.last                  //> res36: (Char, Int) = (x,1)

  val (name, value) = List(List(('x', 1))).last.last
                                                  //> name  : Char = x
                                                  //| value  : Int = 1
  //Returns all elements except last n ones.
  consecList.dropRight(1)                         //> res37: List[String] = List(a, a, a, a, b, c, c, c, d, a)
  consecList.dropRight(1) ::: List("x")           //> res38: List[String] = List(a, a, a, a, b, c, c, c, d, a, x)
  loop(consecList)                                //> res39: Int = 11
  encode2(consecList)                             //> res40: List[(String, Int)] = List((a,4), (b,1), (c,3), (d,1), (a,2))
}