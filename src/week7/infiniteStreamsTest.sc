package week7

object infiniteStreamsTest {
  /*You saw that
	all elements of a stream
	except the ﬁrst one are
	computed only when
	they are needed to
	produce a result.
	This opens up
	the possibility to
	deﬁne inﬁnite streams!
	
	For instance,
	here is
	the stream of all integers
	starting from
	a given number:*/
	/*without 'lazy' 'tail' in Stream definition
	this is recursive non terminated procedure*/
  def from1( n: Int ): Stream[ Int ] = n #:: from1( n + 1 )
                                                  //> from1: (n: Int)Stream[Int]
  /**bechMrking, proFiling*/
  def timeNano[ R ]( block: => R ): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println( "Elapsed time: " + ( t1 - t0 ) + "ns" )
    result
  }                                               //> timeNano: [R](block: => R)R
  
  /*The stream of all natural numbers:*/
  val natS = from1( 0 )                           //> natS  : Stream[Int] = Stream(0, ?)
  /*The stream of all multiples of 4:*/
  natS map ( _ * 4 )                              //> res0: scala.collection.immutable.Stream[Int] = Stream(0, ?)
  ( natS map ( _ * 4 ) ).take( 1 ).mkString       //> res1: String = 0
  ( natS map ( _ * 4 ) ).take( 2 ).mkString       //> res2: String = 04

  /*Exercise:
	Consider
	two ways to express
	the infinite stream of
	multiples of 'a' given number 'N':*/
  val xs = from1( 1 ) map ( _ * 2 )               //> xs  : scala.collection.immutable.Stream[Int] = Stream(2, ?)
  val ys = from1( 1 ) filter ( _ % 2 == 0 )       //> ys  : scala.collection.immutable.Stream[Int] = Stream(2, ?)
  /*Which of the two streams generates its results faster?*/
  /*'map' must be faster
  as 'from1( 1 )' produces shorter, then
  when using 'filter', stream of natural numbers*/
  timeNano(xs take 50).toList                     //> Elapsed time: 14763ns
                                                  //| res3: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 
                                                  //| 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66,
                                                  //|  68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100)
  timeNano(xs take 100).mkString(",")             //> Elapsed time: 14185ns
                                                  //| res4: String = 2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,4
                                                  //| 4,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,9
                                                  //| 4,96,98,100,102,104,106,108,110,112,114,116,118,120,122,124,126,128,130,132
                                                  //| ,134,136,138,140,142,144,146,148,150,152,154,156,158,160,162,164,166,168,17
                                                  //| 0,172,174,176,178,180,182,184,186,188,190,192,194,196,198,200
  timeNano(xs take 100000)                        //> Elapsed time: 10097ns
                                                  //| res5: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  timeNano(xs take 500000)                        //> Elapsed time: 9827ns
                                                  //| res6: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  timeNano(xs take 900000)                        //> Elapsed time: 15852ns
                                                  //| res7: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  timeNano(ys take 50).toList                     //> Elapsed time: 10796ns
                                                  //| res8: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 
                                                  //| 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66,
                                                  //|  68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100)
  timeNano(ys take 100).mkString(",")             //> Elapsed time: 14114ns
                                                  //| res9: String = 2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,4
                                                  //| 4,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,90,92,9
                                                  //| 4,96,98,100,102,104,106,108,110,112,114,116,118,120,122,124,126,128,130,132
                                                  //| ,134,136,138,140,142,144,146,148,150,152,154,156,158,160,162,164,166,168,17
                                                  //| 0,172,174,176,178,180,182,184,186,188,190,192,194,196,198,200
  timeNano(ys take 100000)                        //> Elapsed time: 10807ns
                                                  //| res10: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  timeNano(ys take 500000)                        //> Elapsed time: 9790ns
                                                  //| res11: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  timeNano(ys take 900000)                        //> Elapsed time: 9576ns
                                                  //| res12: scala.collection.immutable.Stream[Int] = Stream(2, ?)
}