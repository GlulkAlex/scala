package week6

object forExpressionsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(228); 
  //*println("Welcome to the Scala worksheet")
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def isPrime(n: Int) = (2 until n) forall (d => n % d != 0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(170); 
  /*pure functional approach --
  firstly,
  use appropriate data structure*/
  /*
      i: 2 3 4 4 5 6 6
      j: 1 2 1 3 2 1 5
  i + j: 3 5 5 7 7 7 11
  */
  val n = 7;System.out.println("""n  : Int = """ + $show(n ));$skip(161); 
  //*return: IndexedSeq[(Int, Int)]
  //*with default inheritance ? Vector()
  val xss /*: Seq[Int]*/ = (1 until n) map (i =>
    (1 until i) map (j => (i, j)));System.out.println("""xss  : scala.collection.immutable.IndexedSeq[scala.collection.immutable.IndexedSeq[(Int, Int)]] = """ + $show(xss ));$skip(167); val res$0 = 
  //(xss foldRight Seq[Int]())(_ ++ _)
  //(xss foldRight Seq[Int]())((y1, z1): IndexedSeq[(Int, Int)] => y1 ++ z1)
  (xss foldRight IndexedSeq[(Int, Int)]())(_ ++ _);System.out.println("""res0: IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(44); val res$1 = 
  (xss foldRight Seq[(Int, Int)]())(_ ++ _);System.out.println("""res1: Seq[(Int, Int)] = """ + $show(res$1));$skip(14); val res$2 = 
  xss.flatten;System.out.println("""res2: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$2));$skip(156); 

  /*Useful law:*/
  //*xs flatMap f = (xs map f).flatten
  /*xss rewriten as:*/
  val xss2 = (1 until n) flatMap (i =>
    (1 until i) map (j => (i, j)));System.out.println("""xss2  : scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(xss2 ));$skip(55); val res$3 = 
  xss2 filter (pair =>
    isPrime(pair._1 + pair._2))

  case class Person(name: String, age: Int);System.out.println("""res3: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$3));$skip(167); 
  var persons = List(
    Person("John", 30),
    Person("Jeck", 18),
    Person("Jimm", 21),
    Person("Jenifer", 42));System.out.println("""persons  : List[week6.forExpressionsTest.Person] = """ + $show(persons ));$skip(75); val res$4 = 
  //*to filter collection:
  for (p <- persons if p.age > 20) yield p.name;System.out.println("""res4: List[String] = """ + $show(res$4));$skip(62); val res$5 = 
  //*or:
  persons filter (p => p.age > 20) map (p => p.name);System.out.println("""res5: List[String] = """ + $show(res$5));$skip(219); val res$6 = 
  /*Note:
  in imperative languages 'for loop' used to
  produce side effects
  in Scala with 'yield' it return iterable collection*/
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j);System.out.println("""res6: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$6));$skip(689); 
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
  } yield (p1 * p2)).sum;System.out.println("""scalarProduct3: (xs3: List[Double], ys3: List[Double])Double""");$skip(46); 

  val list2 = List(1.2, 2.3, 3.4, 4.5, 5.6);System.out.println("""list2  : List[Double] = """ + $show(list2 ));$skip(44); 
  val list3 = List(6.7, 7.8, 8.9, 9.1, 0.1);System.out.println("""list3  : List[Double] = """ + $show(list3 ));$skip(18); val res$7 = 
  list2 zip list3;System.out.println("""res7: List[(Double, Double)] = """ + $show(res$7));$skip(190); val res$8 = 
  /*'sum' method applyed to hole collection
  not to single elements
  then itself 'foldLeft' to each element in collection*/
  (for {
    p1 <- list2
    p2 <- list3
  } yield p1 * p2).sum;System.out.println("""res8: Double = """ + $show(res$8));$skip(58); val res$9 = 
  for {
    (p1, p2) <- list2 zip list3
  } yield p1 * p2;System.out.println("""res9: List[Double] = """ + $show(res$9));$skip(31); val res$10 = 
  scalarProduct3(list2, list3);System.out.println("""res10: Double = """ + $show(res$10))}
}
