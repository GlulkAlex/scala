package week6

object forTranslationTest {
  //*println("Welcome to the Scala worksheet")
  /*The syntax of for is closely related to
  the higher-order functions:
	'map', 'flatMap' and 'filter'.
	First of all,
	these functions can
	all be deﬁned in terms of for:*/
  def mapFor[ T, U ]( xs: List[ T ], f: T => U ): List[ U ] =
    for ( x <- xs ) yield f( x )                  //> mapFor: [T, U](xs: List[T], f: T => U)List[U]

  def flatMapFor[ T, U ]( xs: List[ T ], f: T => Iterable[ U ] ): List[ U ] =
    for ( x <- xs; y <- f( x ) ) yield y          //> flatMapFor: [T, U](xs: List[T], f: T => Iterable[U])List[U]

  def filterFor[ T ]( xs: List[ T ], p: T => Boolean ): List[ T ] =
    for ( x <- xs if p( x ) ) yield x             //> filterFor: [T](xs: List[T], p: T => Boolean)List[T]

  /** Compilers rules:
    */
  /*1. A simple for-expression*/
  //*for (x <- e1) yield e2
  /*is translated to*/
  //*e1.map(x => e2)
  /*2. A for-expression*/
  //*for (x <- e1 if f; s) yield e2
  /*where 'f' is a 'ﬁlter' and
	's' is
	a (potentially empty) sequence of
	generators and ﬁlters,
	is translated to*/
  //*for (x <- e1.withFilter(x => f); s) yield e2
  /*(and the translation continues
	with the new expression)
	You can think of 'withFilter' as
	a variant of filter that
	does not produce an intermediate list, but instead
	ﬁlters the following 'map' or
	'flatMap' function application*/

  /*Take the for-expression that
  computed 'pairs' whose sum is prime:*/
  def isPrime( n: Int ) = ( 2 until n ) forall ( d => n % d != 0 )
                                                  //> isPrime: (n: Int)Boolean

  for {
    i <- 1 until 10
    j <- 1 until i
    if isPrime( i + j )
  } yield ( i, j )                                //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), (
                                                  //| 9,4), (9,8))
  /*Applying the translation scheme to this expression gives:*/
  ( 1 until 10 ).flatMap( i =>
    ( 1 until i ).withFilter( j => isPrime( i + j ) )
      .map( j => ( i, j ) ) )                     //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,
                                                  //| 2), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), (
                                                  //| 9,4), (9,8))
  /*This is almost exactly the expression which we came up with first!*/

  case class Book( title: String, authors: List[ String ] )

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
      authors = List( "Odersky, Martin", "Spoon, Lex", "Venners, Bill" ) ) )
                                                  //> books  : List[week6.forTranslationTest.Book] = List(Book(Structure and Inte
                                                  //| rpretation of Computer Programs,List(Abelson, Harald, Sussman, Gerald J.)),
                                                  //|  Book(Introduction to Functional Programming,List(Bird, Richard, Wadler, Ph
                                                  //| il)), Book(Effective Java,List(Bloch, Joshua)), Book(3rd book of / by Bloch
                                                  //| , Joshua,List(Bloch, Joshua)), Book(Java Puzzlers,List(Bloch, Joshua, Gafte
                                                  //| r, Neal)), Book(Programming in Scala,List(Odersky, Martin, Spoon, Lex, Venn
                                                  //| ers, Bill)))

  val vect1 = Vector( 1, 3, 5, 7, 11, 13, 17, 19 )//> vect1  : scala.collection.immutable.Vector[Int] = Vector(1, 3, 5, 7, 11, 13
                                                  //| , 17, 19)
  val set1 = Set( 1, 3, 5, 7, 11, 13, 17, 19 )    //> set1  : scala.collection.immutable.Set[Int] = Set(5, 1, 13, 17, 7, 3, 11, 1
                                                  //| 9)
  //val list1 = List(1, 3, List(5, 7, 11, List(13, 17), 19))
  val list1 = List( 1, 3, 5, 7, 11, 13, 17, 19 )  //> list1  : List[Int] = List(1, 3, 5, 7, 11, 13, 17, 19)

  for ( b <- books; a <- b.authors if a startsWith "Bird" )
    yield b.title                                 //> res2: List[String] = List(Introduction to Functional Programming)

  /*Applying the translation scheme:*/
  books.flatMap( b =>
    ( b.authors ).withFilter( a => a startsWith "Bird" )
      .map( a => b.title ) )                      //> res3: List[String] = List(Introduction to Functional Programming)

  vect1 map ( x => x * -1 )                       //> res4: scala.collection.immutable.Vector[Int] = Vector(-1, -3, -5, -7, -11, 
                                                  //| -13, -17, -19)
  set1 map ( x => x * -1 )                        //> res5: scala.collection.immutable.Set[Int] = Set(-7, -3, -11, -19, -5, -1, -
                                                  //| 13, -17)
  list1 map ( x => x * -1 )                       //> res6: List[Int] = List(-1, -3, -5, -7, -11, -13, -17, -19)
  mapFor( list1, ( x: Int ) => x * -1 )           //> res7: List[Int] = List(-1, -3, -5, -7, -11, -13, -17, -19)

  //*(1 to M) flatMap (x => (1 to N) map (y => (x, y)))
  vect1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) )
                                                  //> res8: scala.collection.immutable.Vector[(Int, Int)] = Vector((1,1), (1,2), 
                                                  //| (3,1), (3,2), (5,1), (5,2), (7,1), (7,2), (11,1), (11,2), (13,1), (13,2), (
                                                  //| 17,1), (17,2), (19,1), (19,2))
  set1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) )
                                                  //> res9: scala.collection.immutable.Set[(Int, Int)] = Set((7,1), (17,2), (5,2)
                                                  //| , (11,1), (5,1), (3,1), (13,1), (13,2), (19,2), (1,1), (11,2), (17,1), (3,2
                                                  //| ), (19,1), (1,2), (7,2))
  list1 flatMap ( x => ( 1 to 2 ) map ( y => ( x, y ) ) )
                                                  //> res10: List[(Int, Int)] = List((1,1), (1,2), (3,1), (3,2), (5,1), (5,2), (7
                                                  //| ,1), (7,2), (11,1), (11,2), (13,1), (13,2), (17,1), (17,2), (19,1), (19,2))
                                                  //| 
  flatMapFor( list1, ( x: Int ) => ( 1 to 2 ) map ( y => ( x, y ) ) )
                                                  //> res11: List[(Int, Int)] = List((1,1), (1,2), (3,1), (3,2), (5,1), (5,2), (7
                                                  //| ,1), (7,2), (11,1), (11,2), (13,1), (13,2), (17,1), (17,2), (19,1), (19,2))
                                                  //| 

  vect1 filter ( x => x >= 7 )                    //> res12: scala.collection.immutable.Vector[Int] = Vector(7, 11, 13, 17, 19)
  set1 filter ( x => x >= 7 )                     //> res13: scala.collection.immutable.Set[Int] = Set(13, 17, 7, 11, 19)
  list1 filter ( x => x >= 7 )                    //> res14: List[Int] = List(7, 11, 13, 17, 19)
  filterFor( list1, ( x: Int ) => x >= 7 )        //> res15: List[Int] = List(7, 11, 13, 17, 19)
}