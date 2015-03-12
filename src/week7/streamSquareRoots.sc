package week7

object streamSquareRoots {
  /*Our previous algorithm
	for square roots
	always used a 'isGoodEnough' test to
	tell when to
	terminate the iteration.
	With 'streams' we can now
	express the concept of a 'converging sequence' without
	having to worry about
	when to terminate it:*/
  def sqrtStream( x: Double ): Stream[ Double ] = {
      /*with 'averageDamp'
    averaging successful results, for
    narrowing interval span iteration
    converges by averaging successive values*/
      def improve( guess: Double ) = ( guess + x / guess ) / 2
    /*no non-terminating recursion as ( guesses map improve ) operand is lazy
    tail of Stream
    and evaluates when needed*/
    lazy val guesses: Stream[ Double ] = 1 #:: ( guesses map improve )

    guesses
  }                                               //> sqrtStream: (x: Double)Stream[Double]
  /*Termination
  We can add 'isGoodEnough' later.*/
  def isGoodEnough( guess: Double,
                    x: Double,
                    tolerance: Double ) =
    /*using 'epsilon' value*/
    math
      .abs( ( guess * guess - x ) / x ) < x * tolerance
                                                  //> isGoodEnough: (guess: Double, x: Double, tolerance: Double)Boolean

  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  
  /*work without termination condition like 'isGoodEnough'*/
  (sqrtStream( 4 ) take 9).toList                 //> res0: List[Double] = List(1.0, 2.5, 2.05, 2.000609756097561, 2.000000092922
                                                  //| 2947, 2.000000000000002, 2.0, 2.0, 2.0)
  (sqrtStream( 2 ) take 9).toList                 //> res1: List[Double] = List(1.0, 1.5, 1.4166666666666665, 1.4142156862745097,
                                                  //|  1.4142135623746899, 1.414213562373095, 1.414213562373095, 1.41421356237309
                                                  //| 5, 1.414213562373095)
  sqrtStream( 4 ) filter ( isGoodEnough( _, 4, tolerance ) )
                                                  //> res2: scala.collection.immutable.Stream[Double] = Stream(2.0000000929222947
                                                  //| , ?)
  sqrtStream( 2 ) filter ( isGoodEnough( _, 2, tolerance ) )
                                                  //> res3: scala.collection.immutable.Stream[Double] = Stream(1.4142156862745097
                                                  //| , ?)
}