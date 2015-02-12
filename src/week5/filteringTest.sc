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

  def pack2[T](xs: List[T]): List[List[T]] = {
    /**
     * to iterate through single list
     */
    //annotaton@tailrec
    def loop(inputList: List[T] = Nil,
      outputList: List[List[T]] = Nil,
      sequenceList: List[T] = Nil,
      sample: T = None.asInstanceOf[T]): List[List[T]] =
      if (inputList.isEmpty) { //boolean condition
        /*processed list is empty or it is last iteration*/
        /*final return value*/
        (sequenceList :: outputList.drop(1)).reverse //it must store all previous loop call
      } else if (!(inputList.head == sample)) { /*boolean condition,
        comparasion with current leftmost 'head'*/
        //*printf("sample:%s;", sample)
        if (sample == None) { //first step
          //*printf("sample:%s;", sample == None)
          loop(inputList.tail,
            (inputList.head :: Nil) :: Nil,
            inputList.head :: Nil, //reset 'sequenceList'
            inputList.head)
        } else { //not first step
          //*printf("zs return: %s; ", zs)
          //*printf("!zs.head return: %s; ", zs.head)
          /*new 'sample' - new list element */
          loop(inputList.tail,
            (inputList.head :: Nil) :: sequenceList :: outputList.drop(1),
            inputList.head :: Nil, //reset 'sequenceList'
            inputList.head)
        }
      } else { // 'isEmpty' = false, so, it has 'tail'
        //*print("recursion: ; ")
        //*printf("zs.head return: %s; ", zs.head)
        /* 'outputList' postfix 'sample' */
        //*loop(inputList.tail, outputList ::: sample :: Nil, inputList.head)
        /* as elements are the same as 'sample' they may be
        prepending as head of 'outputList'
        then final resulting list must be reversed*/
        //outputList not changed until condition true
        loop(inputList.tail,
          outputList,
          sample :: sequenceList,
          inputList.head)
      }

    loop(inputList = xs)
    //pack as 'loop' starter
    //return: first :: pack(rest)
    //as: first1 :: (first2 :: (first3 ... )) :: Nill
    //where: firstN = (x :: x :: ... :: Nil)
  } //work as expected                            //> pack2: [T](xs: List[T])List[List[T]]

  def pack4[T](xs: List[T]): List[List[T]] = {
    //annotaton@tailrec
    def loop(inputList: List[T] = Nil,
      outputList: List[List[T]] = Nil,
      sample: T = None.asInstanceOf[T]/*Empty parameter*/
      ): List[List[T]] = {
	      if (inputList.isEmpty) { //boolean condition
	        /*processed list is empty or it is last iteration*/
	        /*final return value*/
	        outputList
	      } else if (!(inputList.head == sample)) { /*boolean condition,
	        comparasion with current leftmost 'head'*/
	          //List(List(a, a, a, a), List(b))
	          /*new 'sample' - new list element */
	          loop(inputList.tail,
	            outputList ::: ((inputList.head :: Nil) :: Nil), //store element
	            inputList.head)
	      } else { // 'isEmpty' = false, so, it has 'tail'
	        /* 'outputList' postfix 'sample' */
	        //List(List(a, a, a, a), List(b), List(c, c, c))
	        loop(inputList.tail,
	          outputList.dropRight(1) ::: ((sample :: outputList.last) :: Nil), //store element
	          inputList.head)
	      }
	    }
	    loop(inputList = xs) //initialization
  }//work as expected & better then 'pack2'       //> pack4: [T](xs: List[T])List[List[T]]

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
  } //work as expected                            //> loop: [T](yS: List[T], counter: Int)Int

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
  pack3(consecList)                               //> res14: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  encode(consecList)                              //> res15: List[(String, Int)] = List((a,4), (b,1), (c,3), (d,1), (a,2))
  println("Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1), ('a', 2))")
                                                  //> Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1), ('a', 2))
  compare1("a", "a")                              //> res16: Boolean = true
  compare1("a", "b")                              //> res17: Boolean = false
  compare1("a", 1)                                //> res18: Boolean = false
  compare2("a", "a")                              //> res19: Boolean = true
  compare2("a", "b")                              //> res20: Boolean = true
  compare2("a", 1)                                //> res21: Boolean = true
  compare3("a", "a")                              //> res22: Boolean = true
  compare3("a", "b")                              //> res23: Boolean = false
  compare3("a", 1)                                //> res24: Boolean = false
  (1 :: Nil) :: (2 :: Nil) :: (3 :: Nil) :: Nil   //> res25: List[List[Int]] = List(List(1), List(2), List(3))
  //*Nil.isEmpty
  Nil :: (1 :: Nil)                               //> res26: List[Any] = List(List(), 1)
  encode(Nil)                                     //> res27: List[(Nothing, Int)] = List()
  encode(1 :: 'a' :: Nil)                         //> res28: List[(AnyVal, Int)] = List((1,1), (a,1))
  encode(2 :: 2 :: 'a' :: 'a' :: 3 :: 3 :: 3 :: 'b' :: 7 :: 4 :: 4 :: 4 :: 4 :: 'c' :: 'c' :: Nil)
                                                  //> res29: List[(AnyVal, Int)] = List((2,2), (a,2), (3,3), (b,1), (7,1), (4,4),
                                                  //|  (c,2))
  ('x', 1) :: Nil                                 //> res30: List[(Char, Int)] = List((x,1))
  (('x', 1) :: Nil) :: Nil                        //> res31: List[List[(Char, Int)]] = List(List((x,1)))
  //*List(List(('x', 1)))
  //*List(List(('x', 1))).last
  //*List(List(('x', 1))).last.last

  val (name, value) = List(List(('x', 1))).last.last
                                                  //> name  : Char = x
                                                  //| value  : Int = 1
  //Returns all elements except last 'n' ones.
  consecList.dropRight(1)                         //> res32: List[String] = List(a, a, a, a, b, c, c, c, d, a)
  consecList.dropRight(1) ::: List("x")           //> res33: List[String] = List(a, a, a, a, b, c, c, c, d, a, x)
  consecList.drop(1)                              //> res34: List[String] = List(a, a, a, b, c, c, c, d, a, a)
  //*loop(consecList)
  encode2(consecList)                             //> res35: List[(String, Int)] = List((a,4), (b,1), (c,3), (d,1), (a,2))
  //*consecList.reverse
  pack2(consecList)                               //> res36: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  pack2(Nil)                                      //> res37: List[List[Nothing]] = List(List())
  //*pack2(consecList).last
  //*val lastZ = "z" :: pack2(consecList).last
  //*pack2(consecList).dropRight(1) ::: (lastZ :: Nil)
  pack4(consecList)                               //> res38: List[List[String]] = List(List(a, a, a, a), List(b), List(c, c, c), 
                                                  //| List(d), List(a, a))
  pack4(Nil)                                      //> res39: List[List[Nothing]] = List()
}