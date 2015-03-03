package week6

object forTranslationTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(361); 
  //*println("Welcome to the Scala worksheet")
  /*The syntax of for is closely related to
  the higher-order functions:
	'map', 'flatMap' and 'filter'.
	First of all,
	these functions can
	all be de?ned in terms of for:*/
  def mapFor[ T, U ]( xs: List[ T ], f: T => U ): List[ U ] =
    for ( x <- xs ) yield f( x );System.out.println("""mapFor: [T, U](xs: List[T], f: T => U)List[U]""");$skip(121); 

  def flatMapFor[ T, U ]( xs: List[ T ], f: T => Iterable[ U ] ): List[ U ] =
    for ( x <- xs; y <- f( x ) ) yield y;System.out.println("""flatMapFor: [T, U](xs: List[T], f: T => Iterable[U])List[U]""");$skip(108); 

  def filterFor[ T ]( xs: List[ T ], p: T => Boolean ): List[ T ] =
    for ( x <- xs if p( x ) ) yield x;System.out.println("""filterFor: [T](xs: List[T], p: T => Boolean)List[T]""");$skip(748); 

  /** Compilers rules:
    */
  /*1. A simple for-expression*/
  //*for (x <- e1) yield e2
  /*is translated to*/
  //*e1.map(x => e2)
  /*2. A for-expression*/
  //*for (x <- e1 if f; s) yield e2
  /*where 'f' is a '?lter' and
	's' is
	a (potentially empty) sequence of
	generators and ?lters,
	is translated to*/
  //*for (x <- e1.withFilter(x => f); s) yield e2
  /*(and the translation continues
	with the new expression)
	You can think of 'withFilter' as
	a variant of filter that
	does not produce an intermediate list, but instead
	?lters the following 'map' or
	'flatMap' function application*/

  /*Take the for-expression that
  computed 'pairs' whose sum is prime:*/
  def isPrime( n: Int ) = ( 2 until n ) forall ( d => n % d != 0 );System.out.println("""isPrime: (n: Int)Boolean""");$skip(92); val res$0 = 

  for {
    i <- 1 until 10
    j <- 1 until i
    if isPrime( i + j )
  } yield ( i, j );System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(179); val res$1 = 
  /*Applying the translation scheme to this expression gives:*/
  ( 1 until 10 ).flatMap( i =>
    ( 1 until i ).withFilter( j => isPrime( i + j ) )
      .map( j => ( i, j ) ) )
  /*This is almost exactly the expression which we came up with first!*/

  case class Book( title: String, authors: List[ String ] );System.out.println("""res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$1));$skip(854); 

  val books: List[ Book ] = List(
    /*Using: named fields parameters*/
    Book( title = "Structure and Interpretation of Computer Programs",
      authors = List( "Abelson, Harald", "Sussman, Gerald J." ) ),
    Book( title = "Introduction to Functional Programming",
      authors = List( "Bird, Richard", "Wadler, Phil" ) ),
    Book( title = "Effective Java",
      authors = List( "Bloch, Joshua" ) ),
    Book( title = "3rd book of / by Bloch, Joshua",
      authors = List( "Bloch, Joshua" ) ),
    Book( title = "Java Puzzlers",
      authors = List( "Bloch, Joshua", "Gafter, Neal" ) ),
    Book( title = "Programming in Scala",
      authors = List( "Odersky, Martin", "Spoon, Lex", "Venners, Bill" ) ) );System.out.println("""books  : List[week6.forTranslationTest.Book] = """ + $show(books ));$skip(53); 

  val vect1 = Vector( 1, 3, 5, 7, 11, 13, 17, 19 );System.out.println("""vect1  : scala.collection.immutable.Vector[Int] = """ + $show(vect1 ));$skip(47); 
  val set1 = Set( 1, 3, 5, 7, 11, 13, 17, 19 );System.out.println("""set1  : scala.collection.immutable.Set[Int] = """ + $show(set1 ));$skip(110); 
  //val list1 = List(1, 3, List(5, 7, 11, List(13, 17), 19))
  val list1 = List( 1, 3, 5, 7, 11, 13, 17, 19 );System.out.println("""list1  : List[Int] = """ + $show(list1 ));$skip(80); val res$2 = 

  for ( b <- books; a <- b.authors if a startsWith "Bird" )
    yield b.title;System.out.println("""res2: List[String] = """ + $show(res$2));$skip(149); val res$3 = 

  /*Applying the translation scheme:*/
  books.flatMap( b =>
    ( b.authors ).withFilter( a => a startsWith "Bird" )
      .map( a => b.title ) );System.out.println("""res3: List[String] = """ + $show(res$3));$skip(30); val res$4 = 

  vect1 map ( x => x * -1 );System.out.println("""res4: scala.collection.immutable.Vector[Int] = """ + $show(res$4));$skip(27); val res$5 = 
  set1 map ( x => x * -1 );System.out.println("""res5: scala.collection.immutable.Set[Int] = """ + $show(res$5));$skip(28); val res$6 = 
  list1 map ( x => x * -1 );System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(40); val res$7 = 
  mapFor( list1, ( x: Int ) => x * -1 );System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(116); val res$8 = 

  //*(1 to M) flatMap (x => (1 to N) map (y => (x, y)))
  vect1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) );System.out.println("""res8: scala.collection.immutable.Vector[(Int, Int)] = """ + $show(res$8));$skip(57); val res$9 = 
  set1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) );System.out.println("""res9: scala.collection.immutable.Set[(Int, Int)] = """ + $show(res$9));$skip(58); val res$10 = 
  list1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) );System.out.println("""res10: List[(Int, Int)] = """ + $show(res$10));$skip(70); val res$11 = 
  flatMapFor( list1, ( x: Int ) => ( 1 to 2 ) map ( y => ( x, y ) ) );System.out.println("""res11: List[(Int, Int)] = """ + $show(res$11));$skip(33); val res$12 = 

  vect1 filter ( x => x >= 7 );System.out.println("""res12: scala.collection.immutable.Vector[Int] = """ + $show(res$12));$skip(30); val res$13 = 
  set1 filter ( x => x >= 7 );System.out.println("""res13: scala.collection.immutable.Set[Int] = """ + $show(res$13));$skip(31); val res$14 = 
  list1 filter ( x => x >= 7 );System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(43); val res$15 = 
  filterFor( list1, ( x: Int ) => x >= 7 );System.out.println("""res15: List[Int] = """ + $show(res$15))}
}
