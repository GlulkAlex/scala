package week6

object forExpressionsTest {
  //*println("Welcome to the Scala worksheet")
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def isPrime(n: Int) = (2 until n) forall (d => n % d != 0)
                                                  //> isPrime: (n: Int)Boolean
  /*pure functional approach --
  firstly,
  use appropriate data structure*/
  /*
      i: 2 3 4 4 5 6 6
      j: 1 2 1 3 2 1 5
  i + j: 3 5 5 7 7 7 11
  */
  val n = 7                                       //> n  : Int = 7
  //*return: IndexedSeq[(Int, Int)]
  //*with default inheritance ? Vector()
  val xss /*: Seq[Int]*/ = (1 until n) map (i =>
    (1 until i) map (j => (i, j)))                //> xss  : scala.collection.immutable.IndexedSeq[scala.collection.immutable.Inde
                                                  //| xedSeq[(Int, Int)]] = Vector(Vector(), Vector((2,1)), Vector((3,1), (3,2)), 
                                                  //| Vector((4,1), (4,2), (4,3)), Vector((5,1), (5,2), (5,3), (5,4)), Vector((6,1
                                                  //| ), (6,2), (6,3), (6,4), (6,5)))
  //(xss foldRight Seq[Int]())(_ ++ _)
  //(xss foldRight Seq[Int]())((y1, z1): IndexedSeq[(Int, Int)] => y1 ++ z1)
  (xss foldRight IndexedSeq[(Int, Int)]())(_ ++ _)//> res0: IndexedSeq[(Int, Int)] = Vector((2,1), (3,1), (3,2), (4,1), (4,2), (4,
                                                  //| 3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,3), (6,4), (6,5))
  (xss foldRight Seq[(Int, Int)]())(_ ++ _)       //> res1: Seq[(Int, Int)] = Vector((2,1), (3,1), (3,2), (4,1), (4,2), (4,3), (5,
                                                  //| 1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,3), (6,4), (6,5))
  xss.flatten                                     //> res2: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,1
                                                  //| ), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,
                                                  //| 3), (6,4), (6,5))

  /*Useful law:*/
  //*xs flatMap f = (xs map f).flatten
  /*xss rewriten as:*/
  val xss2 = (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j)))                //> xss2  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3
                                                  //| ,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (
                                                  //| 6,3), (6,4), (6,5))
  xss2 filter (pair =>
    isPrime(pair._1 + pair._2))                   //> res3: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))

  case class Person(name: String, age: Int)
  var persons = List(
    Person("John", 30),
    Person("Jeck", 18),
    Person("Jimm", 21),
    Person("Jenifer", 42))                        //> persons  : List[week6.forExpressionsTest.Person] = List(Person(John,30), Pe
                                                  //| rson(Jeck,18), Person(Jimm,21), Person(Jenifer,42))
  //*to filter collection:
  for (p <- persons if p.age > 20) yield p.name   //> res4: List[String] = List(John, Jimm, Jenifer)
  //*or:
  persons filter (p => p.age > 20) map (p => p.name)
                                                  //> res5: List[String] = List(John, Jimm, Jenifer)
  /*Note:
  in imperative languages 'for loop' used to
  produce side effects
  in Scala with 'yield' it return iterable collection*/
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)                                  //> res6: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5))
  /*
  Translation Rules:
    A for-expression looks like
    a traditional for loop but
    works differently internally:
      for (x <- e1) yield e2 is translated to:
        e1.map(x => e2)
      for (x <- e1 if f) yield e2 is translated to:
        for (x <- e1.filter(x => f)) yield e2
      for (x <- e1; y <- e2) yield e3 is translated to:
        e1.flatMap(x => for (y <- e2) yield e3)
  */
  /**
   * Write a version of 'scalarProduct' (see last session)
   * (xs zip ys).map { case (x, y) => x * y }.sum
   * that makes use of a 'for':
   */
  def scalarProduct3(xs3: List[Double],
    ys3: List[Double]): Double = (for {
    (p1, p2) <- (xs3 zip ys3)
  } yield (p1 * p2)).sum                          //> scalarProduct3: (xs3: List[Double], ys3: List[Double])Double

  val list2 = List(1.2, 2.3, 3.4, 4.5, 5.6)       //> list2  : List[Double] = List(1.2, 2.3, 3.4, 4.5, 5.6)
  val list3 = List(6.7, 7.8, 8.9, 9.1, 0.1)       //> list3  : List[Double] = List(6.7, 7.8, 8.9, 9.1, 0.1)
  list2 zip list3                                 //> res7: List[(Double, Double)] = List((1.2,6.7), (2.3,7.8), (3.4,8.9), (4.5,9
                                                  //| .1), (5.6,0.1))
  /*'sum' method applyed to hole collection
  not to single elements
  then itself 'foldLeft' to each element in collection*/
  (for {
    p1 <- list2
    p2 <- list3
  } yield p1 * p2).sum                            //> res8: Double = 554.1999999999999
  for {
    (p1, p2) <- list2 zip list3
  } yield p1 * p2                                 //> res9: List[Double] = List(8.04, 17.939999999999998, 30.26, 40.9499999999999
                                                  //| 96, 0.5599999999999999)
  scalarProduct3(list2, list3)                    //> res10: Double = 97.75
}