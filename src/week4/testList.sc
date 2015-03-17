//package week4

import scala.collection.immutable.List

object testList {
  val d = Nil                                     //> d  : scala.collection.immutable.Nil.type = List()
  val c = 3 :: d                                  //> c  : List[Int] = List(3)
  val b = 2 :: c                                  //> b  : List[Int] = List(2, 3)
  val a = 1 :: b                                  //> a  : List[Int] = List(1, 2, 3)

  /*a should be(List(, , ))*/
  a.tail /*should be()*/                          //> res0: List[Int] = List(2, 3)
  b.tail /*should be()*/                          //> res1: List[Int] = List(3)
  c.tail /*should be()*/                          //> res2: List[Int] = List()
}