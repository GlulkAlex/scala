package week5

object mapTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /**
   * Applying a finction to Elements of a List
   * A common operation is
   * to transform each element of a list &
   * then return the list of results.
   */
  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }                                               //> scaleList: (xs: List[Double], factor: Double)List[Double]

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
  }                                               //> map2: [T, U](xs: List[T], f: T => U)List[U]

  def scaleList2(xs: List[Double], factor: Double): List[Double] = {
  	//*xs map (x => x * factor)
    map2(xs, (x: Double) => x / factor)
    //map2(this, (x: Double) => x / factor)
  }                                               //> scaleList2: (xs: List[Double], factor: Double)List[Double]

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => xs //Nil
    case y :: ys => y * y :: squareList(ys)
  }                                               //> squareList: (xs: List[Int])List[Int]

  def squareList1(xs: List[Int]): List[Int] =
    //*xs map (x => x * x)
    map2(xs, (x: Int) => x * x)                   //> squareList1: (xs: List[Int])List[Int]

  val doubleList = List(1.0, 2.0, 3.0, 4.0, 5.0)  //> doubleList  : List[Double] = List(1.0, 2.0, 3.0, 4.0, 5.0)
  val intList = List(-1, 2, -3, 4, -5)            //> intList  : List[Int] = List(-1, 2, -3, 4, -5)

  scaleList(doubleList, 2)                        //> res0: List[Double] = List(2.0, 4.0, 6.0, 8.0, 10.0)
  scaleList2(doubleList, 3)                       //> res1: List[Double] = List(0.3333333333333333, 0.6666666666666666, 1.0, 1.33
                                                  //| 33333333333333, 1.6666666666666667)
  
  squareList(intList)                             //> res2: List[Int] = List(1, 4, 9, 16, 25)
  squareList1(intList)                            //> res3: List[Int] = List(1, 4, 9, 16, 25)
}