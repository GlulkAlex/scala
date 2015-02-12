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
  };System.out.println("""posElems: (xs: List[Int])List[Int]""");$skip(668); 

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
  };System.out.println("""filter1: [T](xs: List[T])(p: T => Boolean)List[T]""");$skip(169); 

  /*Using 'filter',
 'posElems' can be written more concisely*/
  def posElems2(xs: List[Int]): List[Int] =
    //xs filter1 (x => x > 0)
    filter1(xs)(x => x <= 0);System.out.println("""posElems2: (xs: List[Int])List[Int]""");$skip(399); 

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
  };System.out.println("""compare1: [T](sample: T, criteria: T)Boolean""");$skip(159); 

  def compare2[T](sample: T, criteria: T): Boolean = {
    sample match {
      case criteria => true
      case _ => false
    }
  };System.out.println("""compare2: [T](sample: T, criteria: T)Boolean""");$skip(91);  //not work as expected

  def compare3[T](sample: T, criteria: T): Boolean = {
    return sample == criteria
  };System.out.println("""compare3: [T](sample: T, criteria: T)Boolean""");$skip(316); 

  def pack[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => Nil //not same list as 'xs'
      case x :: xs1 => {
        //name for parameters - matters -- may not be same as in 'pattern'
        xs.takeWhile(z => z == x) :: pack(xs.dropWhile(z => z == x)) //*work as expected
      }
    }
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(444); 

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
  };System.out.println("""pack3: [T](xs: List[T])List[List[T]]""");$skip(264); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def encode2[T](xs: List[T]): List[(T, Int)] = {
    //? 'x' as pattern matching on 'xs'
    //replacing each element of 'xs' by 'pair'
    pack3(xs) map (x => (x.head, x.length))
  };System.out.println("""encode2: [T](xs: List[T])List[(T, Int)]""");$skip(2128); 

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
  };System.out.println("""pack2: [T](xs: List[T])List[List[T]]""");$skip(1167);  //work as expected

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
  };System.out.println("""pack4: [T](xs: List[T])List[List[T]]""");$skip(1240); //work as expected & better then 'pack2'

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
  };System.out.println("""encode: [T](xs: List[T])List[(T, Int)]""");$skip(188);  //work as expected

  def loop[T](yS: List[T], counter: Int = 0): Int = {
    yS match {
      case Nil => counter
      case z :: zS => {
        loop(zS, counter + 1)
      }
    }
  };System.out.println("""loop: [T](yS: List[T], counter: Int)Int""");$skip(41);  //work as expected

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
  consecList span (x => x == "a");System.out.println("""res7: (List[String], List[String]) = """ + $show(res$7));$skip(60); val res$8 = 
  //looks for prefix
  consecList takeWhile (x => x == "a");System.out.println("""res8: List[String] = """ + $show(res$8));$skip(79); val res$9 = 
  //looks for suffix / postfix / ending
  consecList dropWhile (x => x == "a");System.out.println("""res9: List[String] = """ + $show(res$9));$skip(185); val res$10 = 

  /*In?x Notation
	Any method with a parameter can be used like
	an 'in?x operator'.
	It is therefore possible to write:
	r add s  'as' r.add(s)*/
  consecList.takeWhile(x => x == x);System.out.println("""res10: List[String] = """ + $show(res$10));$skip(60); val res$11 = 
  //runs in single traversal
  consecList.span(x => x == x);System.out.println("""res11: (List[String], List[String]) = """ + $show(res$11));$skip(65); val res$12 = 
  //runs in single traversal
  consecList.partition(x => x == x);System.out.println("""res12: (List[String], List[String]) = """ + $show(res$12));$skip(19); val res$13 = 
  pack(consecList);System.out.println("""res13: List[List[String]] = """ + $show(res$13));$skip(24); 
  println("Expected: ");$skip(88); 
  println("List(L('a', 'a', 'a', 'a'), L('b'), L('c', 'c', 'c'), L('d'), L('a', 'a'))");$skip(20); val res$14 = 
  pack3(consecList);System.out.println("""res14: List[List[String]] = """ + $show(res$14));$skip(21); val res$15 = 
  encode(consecList);System.out.println("""res15: List[(String, Int)] = """ + $show(res$15));$skip(78); 
  println("Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1), ('a', 2))");$skip(21); val res$16 = 
  compare1("a", "a");System.out.println("""res16: Boolean = """ + $show(res$16));$skip(21); val res$17 = 
  compare1("a", "b");System.out.println("""res17: Boolean = """ + $show(res$17));$skip(19); val res$18 = 
  compare1("a", 1);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(21); val res$19 = 
  compare2("a", "a");System.out.println("""res19: Boolean = """ + $show(res$19));$skip(21); val res$20 = 
  compare2("a", "b");System.out.println("""res20: Boolean = """ + $show(res$20));$skip(19); val res$21 = 
  compare2("a", 1);System.out.println("""res21: Boolean = """ + $show(res$21));$skip(21); val res$22 = 
  compare3("a", "a");System.out.println("""res22: Boolean = """ + $show(res$22));$skip(21); val res$23 = 
  compare3("a", "b");System.out.println("""res23: Boolean = """ + $show(res$23));$skip(19); val res$24 = 
  compare3("a", 1);System.out.println("""res24: Boolean = """ + $show(res$24));$skip(48); val res$25 = 
  (1 :: Nil) :: (2 :: Nil) :: (3 :: Nil) :: Nil;System.out.println("""res25: List[List[Int]] = """ + $show(res$25));$skip(37); val res$26 = 
  //*Nil.isEmpty
  Nil :: (1 :: Nil);System.out.println("""res26: List[Any] = """ + $show(res$26));$skip(14); val res$27 = 
  encode(Nil);System.out.println("""res27: List[(Nothing, Int)] = """ + $show(res$27));$skip(26); val res$28 = 
  encode(1 :: 'a' :: Nil);System.out.println("""res28: List[(AnyVal, Int)] = """ + $show(res$28));$skip(99); val res$29 = 
  encode(2 :: 2 :: 'a' :: 'a' :: 3 :: 3 :: 3 :: 'b' :: 7 :: 4 :: 4 :: 4 :: 4 :: 'c' :: 'c' :: Nil);System.out.println("""res29: List[(AnyVal, Int)] = """ + $show(res$29));$skip(18); val res$30 = 
  ('x', 1) :: Nil;System.out.println("""res30: List[(Char, Int)] = """ + $show(res$30));$skip(27); val res$31 = 
  (('x', 1) :: Nil) :: Nil;System.out.println("""res31: List[List[(Char, Int)]] = """ + $show(res$31));$skip(148); 
  //*List(List(('x', 1)))
  //*List(List(('x', 1))).last
  //*List(List(('x', 1))).last.last

  val (name, value) = List(List(('x', 1))).last.last;System.out.println("""name  : Char = """ + $show(name ));System.out.println("""value  : Int = """ + $show(value ));$skip(73); val res$32 = 
  //Returns all elements except last 'n' ones.
  consecList.dropRight(1);System.out.println("""res32: List[String] = """ + $show(res$32));$skip(40); val res$33 = 
  consecList.dropRight(1) ::: List("x");System.out.println("""res33: List[String] = """ + $show(res$33));$skip(21); val res$34 = 
  consecList.drop(1);System.out.println("""res34: List[String] = """ + $show(res$34));$skip(44); val res$35 = 
  //*loop(consecList)
  encode2(consecList);System.out.println("""res35: List[(String, Int)] = """ + $show(res$35));$skip(44); val res$36 = 
  //*consecList.reverse
  pack2(consecList);System.out.println("""res36: List[List[String]] = """ + $show(res$36));$skip(13); val res$37 = 
  pack2(Nil);System.out.println("""res37: List[List[Nothing]] = """ + $show(res$37));$skip(150); val res$38 = 
  //*pack2(consecList).last
  //*val lastZ = "z" :: pack2(consecList).last
  //*pack2(consecList).dropRight(1) ::: (lastZ :: Nil)
  pack4(consecList);System.out.println("""res38: List[List[String]] = """ + $show(res$38));$skip(13); val res$39 = 
  pack4(Nil);System.out.println("""res39: List[List[Nothing]] = """ + $show(res$39))}
}
