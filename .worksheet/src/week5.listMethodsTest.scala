package week5

object listMethodsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(83); 
  println("Welcome to the Scala worksheet");$skip(158); 

  def last1[T](xs: List[T]): T = xs match {
    case List() => throw new Error("last of empty list")
    case List(x) => x
    case y :: ys => last1(ys)
  };System.out.println("""last1: [T](xs: List[T])T""");$skip(403); 
  
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
    		case List(z) => List(y)
    		case z :: zs => y :: init1(ys)
    	}
  };System.out.println("""init1: [T](xs: List[T])List[T]""");$skip(389); 

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
  };System.out.println("""init2: [T](xs: List[T])List[T]""");$skip(456); 
  
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
		    case z :: zs => z :: concat1(zs, ys)
  		};System.out.println("""concat1: [T](xs: List[T], ys: List[T])List[T]""");$skip(420); 

	def reverce1[T](xs: List[T]): List[T] =
		//first part of 'concat' 'xs' - checks firstly
    xs match {
	    case List() => xs //or Nil
	    //if xs not empty / Nil
	    //then swap '.head' with '.tail'
	    //& call recursion on the former '.tail'
	    //y == xs.head; ys == xs.tail
	    //complexity is xs * xs -- quadratic of input
	    case z :: zs => concat1(reverce1(zs), List(z))//reverce1(zs) ++ List(z)
  		};System.out.println("""reverce1: [T](xs: List[T])List[T]""");$skip(879); 
  		  
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
 					y :: removeAt1(n - 1, ys) }
 			/*if (n == 0 || xs == Nil) {
 				xs
 			} else {
 				if (xs == Nil) {
 					xs.head :: removeAt(n - 1, xs.tail)
 				}
 			}*/
 		}
 	};System.out.println("""removeAt1: [T](n: Int, xs: List[T])List[T]""");$skip(247); 
	//Usage example:
	//*removeAt(1, List('a', 'b', 'c', 'd'))  =>  List('a', 'c', 'd')

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
	def removeAt2[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop (n + 1));System.out.println("""removeAt2: [T](n: Int, xs: List[T])List[T]""");$skip(83); 
	def removeAt3[T](n: Int, xs: List[T]): List[T] = (xs take n) ++ (xs drop (n + 1));System.out.println("""removeAt3: [T](n: Int, xs: List[T])List[T]""");$skip(1625); 
	
 	def flatten1(xs: List[Any]): List[Any] = {
 		def matchTest(x: Any): Any = x match {
 			/* order of 'case' matters */
 			//zero length / empty list
 			//pattern: (Nil)
 			case List() => Nil //xs //or Nil
 			case List(y :: ys) => matchTest(y)
 			case y :: ys => List(matchTest(y), matchTest(ys))
 			case List(y) => y //(not list)
 			//case (y: Any) :: ys => y :: "(y: Any)" :: flatten1(ys)
 			//case (y: List[Any]) :: ys => flatten1(y) :: "(y: List[Any])" :: ys//flatten1(ys)
 			//case (z :: zs) :: ys => z :: flatten1(zs) :: "(zs: tail)" :: ys//flatten1(ys)
 			//case (z :: zs) :: ys => flatten1(z :: zs) :: "(head flatten)" :: ys//flatten1(ys)
 			//last / one element / basic case -
  	  //must return primitive value or (not list)
  	  //but here List[Any] expected
  	  //pattern: (not list) :: (Nil)
	    //case List(x: Any) => x :: "(not list)" :: Nil
	    //case List(x: List[Any]) => flatten1(x)//no effect on result
	    /*do until got (not List) then prepend it to the rest*/
	    //while '.head' in xs == (z :: zs) recursivly flatten1 parts of it
	    //& prepend before the rest
	    //pattern: (list) :: (list or Nil)
 			/*case (z :: zs) :: ys => flatten1(z :: flatten1(zs) :: flatten1(ys))
 			//pattern: (not list) :: (list or Nil)
 			case y :: ys => y :: flatten1(ys)*/
 			/*case y :: ys => {
 				y match {
 					case (z :: zs) => z :: flatten1(zs)
 					case List(z) => z :: Nil
 					case z: Any => z :: Nil
 					//anything else:
 					case _ => Nil
 				}
 			}*/
 			//something else:
 			case _ => "something else"// :: Nil
 		}
		//flatten1(xs: List[Any])
		matchTest(xs) :: Nil
 	};System.out.println("""flatten1: (xs: List[Any])List[Any]""");$skip(55); 
 	    
  val nums: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil;System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(49); 
  val nums2: List[Int] = 9 :: 8 :: 7 :: 6 :: Nil;System.out.println("""nums2  : List[Int] = """ + $show(nums2 ));$skip(15); val res$0 = 
  
  nums.last;System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 
  nums.init;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(18); val res$2 = 
  
  last1(nums);System.out.println("""res2: Int = """ + $show(res$2));$skip(14); val res$3 = 
  init1(nums);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(14); val res$4 = 
  init2(nums);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(23); val res$5 = 
  concat1(nums, nums2);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(18); val res$6 = 
  reverce1(nums2);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(41); val res$7 = 
  removeAt1(1, List('a', 'b', 'c', 'd'));System.out.println("""res7: List[Char] = """ + $show(res$7));$skip(41); val res$8 = 
  removeAt1(3, List('a', 'b', 'c', 'd'));System.out.println("""res8: List[Char] = """ + $show(res$8));$skip(41); val res$9 = 
  removeAt1(5, List('a', 'b', 'c', 'd'));System.out.println("""res9: List[Char] = """ + $show(res$9));$skip(41); val res$10 = 
  removeAt2(3, List('a', 'b', 'c', 'd'));System.out.println("""res10: List[Char] = """ + $show(res$10));$skip(34); val res$11 = 
  List('a', 'b', 'c', 'd') take 3;System.out.println("""res11: List[Char] = """ + $show(res$11));$skip(38); val res$12 = 
  List('a', 'b', 'c', 'd') drop 3 + 1;System.out.println("""res12: List[Char] = """ + $show(res$12));$skip(41); val res$13 = 
  removeAt3(3, List('a', 'b', 'c', 'd'));System.out.println("""res13: List[Char] = """ + $show(res$13));$skip(30); val res$14 = 
  List('a', 'b', 'c', 'd')(0);System.out.println("""res14: Char = """ + $show(res$14));$skip(44); val res$15 = 
  List('a', 'b', 'c', 'd') updated (0, 'A');System.out.println("""res15: List[Char] = """ + $show(res$15));$skip(58); val res$16 = 
  List('a', 'b', 'c', 'd') updated (0, 'A' :: 'a' :: Nil);System.out.println("""res16: List[Any] = """ + $show(res$16));$skip(13); val res$17 = 
  Nil :: Nil;System.out.println("""res17: List[scala.collection.immutable.Nil.type] = """ + $show(res$17));$skip(85); 
  val complexList = List(
  		List(1, 1),
  		2,
  		List(
  			3,
  			List(5, 8)));System.out.println("""complexList  : List[Any] = """ + $show(complexList ));$skip(244); val res$18 = 
  //example:
  //flatten(List(List(1, 1), 2, List(3, List(5, 8)))) =>
  //List[Any] = List(1, 1, 2, 3, 5, 8) -- '.heads' only ? - no complex objects included
  //or elements are primitive types only -- no 'List' allowed
  flatten1(complexList);System.out.println("""res18: List[Any] = """ + $show(res$18));$skip(13); val res$19 = 
	complexList;System.out.println("""res19: List[Any] = """ + $show(res$19));$skip(18); val res$20 = 
	complexList.head;System.out.println("""res20: Any = """ + $show(res$20));$skip(18); val res$21 = 
	complexList.tail;System.out.println("""res21: List[Any] = """ + $show(res$21));$skip(28); val res$22 = 
  complexList.mkString("|");System.out.println("""res22: String = """ + $show(res$22));$skip(25); val res$23 = 
  complexList.toString();System.out.println("""res23: String = """ + $show(res$23))}
}
