package week5

object mapTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(325); 

  /**
   * Applying a finction to Elements of a List
   * A common operation is
   * to transform each element of a list &
   * then return the list of results.
   */
  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  };System.out.println("""scaleList: (xs: List[Double], factor: Double)List[Double]""");$skip(519); 

  /**
   * Map
   * This scheme can be generalized to
   * the method 'map'of the 'List' class
   * A simple way to define 'map' is:
   */
  /*abstract class List[T] {
    //...
    def map[U](f: T => U): List[U] = this match {
      case Nil => this //unchenged list
      case x :: xs => f(x) :: xs.map(f)
    }
    //...
  }*/

  /*actual 'map' implementation is
	tail-recursive*/
  def map2[T, U](xs: List[T], f: T => U): List[U] = xs match {
    case Nil => Nil //xs
    case y :: ys => f(y) :: map2(ys, f)
  };System.out.println("""map2: [T, U](xs: List[T], f: T => U)List[U]""");$skip(191); 

  def scaleList2(xs: List[Double], factor: Double): List[Double] = {
    //*xs map (x => x * factor)
    map2(xs, (x: Double) => x / factor)
    //map2(this, (x: Double) => x / factor)
  };System.out.println("""scaleList2: (xs: List[Double], factor: Double)List[Double]""");$skip(131); 

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => xs //Nil
    case y :: ys => y * y :: squareList(ys)
  };System.out.println("""squareList: (xs: List[Int])List[Int]""");$skip(107); 

  def squareList1(xs: List[Int]): List[Int] =
    //*xs map (x => x * x)
    map2(xs, (x: Int) => x * x);System.out.println("""squareList1: (xs: List[Int])List[Int]""");$skip(654); 

  def squareList2(xs: List[Int]): List[Int] = {
    /**
     * 'head' as accumulator
     * //@tailrec
     */
    def loop(head: List[Int] = Nil, tail: List[Int]): List[Int] = {
      //condition check:
      tail match {
        //base case - return end / final value
        case Nil => head
        //iteration - increment step / recursion
        case y :: ys => {
        	//append current element to previously affected / changed / modifed
          loop(head = head ::: (y * y :: Nil), tail = ys) //y * y :: squareList(ys)
        }
      }
    }

    loop(tail = xs) //Named Parameters
  };System.out.println("""squareList2: (xs: List[Int])List[Int]""");$skip(107); //works as expected /*possibly more time efficient*/
  def squareList3(xs: List[Int]): List[Int] =
    //*using predefined scala method
    xs map (x => x * x);System.out.println("""squareList3: (xs: List[Int])List[Int]""");$skip(306); 
    
	//benchmarking:
	/*scala.testing.Benchmark trait is
	predefined in the Scala standard library*/
  def timeNano[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  };System.out.println("""timeNano: [R](block: => R)R""");$skip(51); 

  val doubleList = List(1.0, 2.0, 3.0, 4.0, 5.0);System.out.println("""doubleList  : List[Double] = """ + $show(doubleList ));$skip(39); 
  val intList = List(-1, 2, -3, 4, -5);System.out.println("""intList  : List[Int] = """ + $show(intList ));$skip(29); val res$0 = 

  scaleList(doubleList, 2);System.out.println("""res0: List[Double] = """ + $show(res$0));$skip(28); val res$1 = 
  scaleList2(doubleList, 3);System.out.println("""res1: List[Double] = """ + $show(res$1));$skip(24); val res$2 = 

  squareList(intList);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(42); val res$3 = 
  timeNano {
    squareList1(intList)
  };System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(42); val res$4 = 
  timeNano {
    squareList2(intList)
  };System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(42); val res$5 = 
  timeNano {
    squareList3(intList)
  };System.out.println("""res5: List[Int] = """ + $show(res$5))}
}
