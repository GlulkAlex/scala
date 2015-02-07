package week5

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
    		case List(z) => List(y)
    		case z :: zs => y :: init1(ys)
    	}
  }                                               //> init1: [T](xs: List[T])List[T]

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
		    case z :: zs => z :: concat1(zs, ys)
  		}                                 //> concat1: [T](xs: List[T], ys: List[T])List[T]

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
  		}                                 //> reverce1: [T](xs: List[T])List[T]
  		  
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
 	}                                         //> removeAt1: [T](n: Int, xs: List[T])List[T]
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
 	}                                         //> flatten1: (xs: List[Any])List[Any]
 	    
  val nums: List[Int] = 0 :: 1 :: 2 :: 3 :: Nil   //> nums  : List[Int] = List(0, 1, 2, 3)
  val nums2: List[Int] = 9 :: 8 :: 7 :: 6 :: Nil  //> nums2  : List[Int] = List(9, 8, 7, 6)
  
  nums.last                                       //> res0: Int = 3
  nums.init                                       //> res1: List[Int] = List(0, 1, 2)
  
  last1(nums)                                     //> res2: Int = 3
  init1(nums)                                     //> res3: List[Int] = List(0, 1, 2)
  init2(nums)                                     //> res4: List[Int] = List(0, 1, 2)
  concat1(nums, nums2)                            //> res5: List[Int] = List(0, 1, 2, 3, 9, 8, 7, 6)
  reverce1(nums2)                                 //> res6: List[Int] = List(6, 7, 8, 9)
  removeAt1(1, List('a', 'b', 'c', 'd'))          //> res7: List[Char] = List(a, c, d)
  removeAt1(3, List('a', 'b', 'c', 'd'))          //> res8: List[Char] = List(a, b, c)
  removeAt1(5, List('a', 'b', 'c', 'd'))          //> res9: List[Char] = List(a, b, c, d)
  removeAt2(3, List('a', 'b', 'c', 'd'))          //> res10: List[Char] = List(a, b, c)
  List('a', 'b', 'c', 'd') take 3                 //> res11: List[Char] = List(a, b, c)
  List('a', 'b', 'c', 'd') drop 3 + 1             //> res12: List[Char] = List()
  removeAt3(3, List('a', 'b', 'c', 'd'))          //> res13: List[Char] = List(a, b, c)
  List('a', 'b', 'c', 'd')(0)                     //> res14: Char = a
  List('a', 'b', 'c', 'd') updated (0, 'A')       //> res15: List[Char] = List(A, b, c, d)
  List('a', 'b', 'c', 'd') updated (0, 'A' :: 'a' :: Nil)
                                                  //> res16: List[Any] = List(List(A, a), b, c, d)
  Nil :: Nil                                      //> res17: List[scala.collection.immutable.Nil.type] = List(List())
  val complexList = List(
  		List(1, 1),
  		2,
  		List(
  			3,
  			List(5, 8)))              //> complexList  : List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
  //example:
  //flatten(List(List(1, 1), 2, List(3, List(5, 8)))) =>
  //List[Any] = List(1, 1, 2, 3, 5, 8) -- '.heads' only ? - no complex objects included
  //or elements are primitive types only -- no 'List' allowed
  flatten1(complexList)                           //> res18: List[Any] = List(List(List(something else, List(something else, List
                                                  //| ())), List(something else, something else)))
	complexList                               //> res19: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
	complexList.head                          //> res20: Any = List(1, 1)
	complexList.tail                          //> res21: List[Any] = List(2, List(3, List(5, 8)))
  complexList.mkString("|")                       //> res22: String = List(1, 1)|2|List(3, List(5, 8))
  complexList.toString()                          //> res23: String = List(List(1, 1), 2, List(3, List(5, 8)))
}