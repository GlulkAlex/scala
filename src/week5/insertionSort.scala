//package week4
//permanently hidden by trait definition in package.week4.List.scala
//import scala.List

package week5

object insertionSort {
  //def insert[+T]( head: T, tail: List[T]): List[T] = {
  /*def insert( head: Int, tail: List[Int]): List[Int] = {
      if (tail.isEmpty)
        //do nothing
        //return original list unchanged   
      else if (head > tail.head)
        //swap heads
        List(tail.head :: tail)
      else
        //do nothing
        //return original list unchanged 
  }*/
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    //insert an element to an empty list
    //so, elem is '.head'
    //complexity proportional to
    // N
    case List() => x :: Nil 
    //complexity proportional to
    // N * N
    case y :: ys => if ( x <= y ) 
        x :: xs
      else
        y :: insert( x, ys )      
  }

  def inSort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    //list pattern
    //stands for head & tail value respectively     
    case y :: ys => insert(y, inSort(ys))
  }

  val nums = 0 :: (1 :: (2 :: (3 :: Nil)))

  def main(args: Array[String]) = {
    //'::' method prepends '.head' element to existing list
    println("Nil.::(3).::(2).::(1).::(0) is: " + Nil.::(3).::(2).::(1).::(0))
    //pattern matching
    println("(0)::(1)::(2)::(3)::Nil is: " + (0) :: (1) :: (2) :: (3) :: Nil)
    println("0::1::2::3::Nil is: " + 0 :: 1 :: 2 :: 3 :: Nil)
    //right associativity for ':'
    //so, parenthesis are redundant  
    println("0::(1::(2::(3::Nil))) is: " + 0 :: (1 :: (2 :: (3 :: Nil))))
    println("List(0, 1, 2, 3) is: " + List(0, 1, 2, 3))
    println("empty List is: " + Nil)
    println("empty List is: " + List())
    println("0::1::2::3::Nil .isEmpty is: " + nums.tail.tail.tail.tail.isEmpty)
    println("inSort() of 7::3::9::2::Nil is: " + inSort(7::3::9::2::Nil))
  }
}