package week7

object streamsTest {
  /*'Stream' is recursive structure like / simular to 'List'*/

  /*Implimentaton:
  'Stream.empty' <=> correspond to 'Nil'
  'Stream.cons' <=> correspond to '::'
  */
  /*trait Stream1[ +A ] extends Seq[ A ] {
    def isEmpty: Boolean
    def head: A
    def tail: Stream[ A ]
    /*...*/
  }*/

  //class Stream2[+T] /*extends Stream1[T]*/{
  /*...*/
  /*def filter(p: T => Boolean): Stream2[T] =
      if (isEmpty) this
      else if (p(head))
       cons(head,
         /*delayed evaluation*/
         tail.filter(p))
      else tail.filter(p)*/
  //}

  /*object Stream3 {
    /*Note / notice that:
    'tl' call by name*/
    def cons[ T ]( hd: T, tl: => Stream1[ T ] ) = new Stream1[ T ] {
      def isEmpty = false
      def head = hd
      /*when 'tail' method being called then
      'tl' be dereferenced & evaluated*/
      def tail = tl
    }
    /*evaluated once, when defined*/
    val empty = new Stream[ Nothing ] {
      def isEmpty = true
      def head = throw new NoSuchElementException( "empty.head" )
      def tail = throw new NoSuchElementException( "empty.tail" )
    }
  }*/

  /* Streams are defined from
    a 'constant' 'Stream.empty' and
    a 'constructor' 'Stream.cons'.
  */
  val xs = Stream.cons( 1, Stream.cons( 2, Stream.empty ) )
                                                  //> xs  : Stream.Cons[Int] = Stream(1, ?)
  /*Streams can also be defined like
	the other collections by
	using the object 'Stream' as a 'factory'.*/
  Stream( 1, 2, 3 )                               //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)

  /*The '.toStream' method on a collection will
  turn the collection into a stream:*/
  ( 1 to 1000 ).toStream                          //> res1: scala.collection.immutable.Stream[Int] = Stream(1, ?)

  /*operator #:: which produces a
	stream.
	x #:: xs == Stream.cons(x, xs)
	#:: can be used in
	expressions as well as patterns.*/

  /** Stream Ranges
    * @param lo - lower bound
    * @param hi - high bound
    * @return Stream
    */
  def streamRange( lo: Int, hi: Int ): Stream[ Int ] = {
    print( lo + " " )

    if ( lo >= hi ) Stream.empty
    else {
      /*Stream
        .cons( lo, streamRange( lo + 1, hi ) )*/
      /*or*/
      lo #:: streamRange( lo + 1, hi )
    }
  }                                               //> streamRange: (lo: Int, hi: Int)Stream[Int]

  /*Compare to the same function that produces a list:
  both are isomorphic*/
  def listRange( lo: Int, hi: Int ): List[ Int ] =
    if ( lo >= hi ) Nil
    else lo :: listRange( lo + 1, hi )            //> listRange: (lo: Int, hi: Int)List[Int]

  /*'tail' on demand only
  so, until then
  'stream' is partialy constracted
  '?' represent unevaluated part of 'stream'*/
  streamRange( 1, 10 )                            //> 1 res2: Stream[Int] = Stream(1, ?)
  streamRange( 1, 10 ).tail                       //> 1 2 res3: scala.collection.immutable.Stream[Int] = Stream(2, ?)
  listRange( 1, 10 )                              //> res4: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  streamRange( 1, 10 )
    .take( 3 )
    .toList                                       //> 1 2 3 res5: List[Int] = List(1, 2, 3)

  Stream.Empty padTo ( 9, "0" )                   //> res6: scala.collection.immutable.Stream[String] = Stream(0, ?)
  ( Stream.Empty padTo ( 9, "0" ) ).toString()    //> res7: String = Stream(0, ?)
  ( Stream.Empty padTo ( 9, "0" ) ).mkString      //> res8: String = 000000000
  ( Stream.Empty padTo ( 9, "" ) ).mkString       //> res9: String = ""

  def timeNano[ R ]( block: => R ): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println( "Elapsed time: " + ( t1 - t0 ) + "ns" )
    result
  }                                               //> timeNano: [R](block: => R)R

  /** Functions and Methods Implementation
    * by Martin Odersky
    */
  def isPrime( n: Int ) = ( 2 until n ) forall ( d => n % d != 0 )
                                                  //> isPrime: (n: Int)Boolean
  /*Assuming
	'apply' is defined like this
	in 'Stream[T]':*/
  /*def apply( n: Int ): T =
    if ( n == 0 ) head
    else tail.apply( n - 1 )*/

  /*to ﬁnd the second prime number between 'from' & 'to'*/
  timeNano(
    ( ( 100 to 300 ) filter isPrime )( 1 )
  )                                               //> Elapsed time: 10194041ns
                                                  //| res10: Int = 103

  timeNano(
    ( ( 100 to 300 ) filter isPrime ).apply( 1 )
  )                                               //> Elapsed time: 1953740ns
                                                  //| res11: Int = 103

  timeNano(
    ( streamRange( 100, 300 ) filter isPrime ) apply 1
  )                                               //> 100 101 102 103 Elapsed time: 1405342ns
                                                  //| res12: Int = 103

  timeNano(
    ( ( 100 to 300 ).toStream filter isPrime )( 1 )
  )                                               //> Elapsed time: 723900ns
                                                  //| res13: Int = 103
  /*we can
  make the short-code effcient by
  using a trick:
    Avoid computing the tail of a sequence until
    it is needed
    for the evaluation result
    (which might be never)*/
  isPrime( 101 )                                  //> res14: Boolean = true
  isPrime( 1001 )                                 //> res15: Boolean = false
  1001 % 3                                        //> res16: Int(2) = 2
  1001 % 7                                        //> res17: Int(0) = 0
  isPrime( 1009 )                                 //> res18: Boolean = true
  isPrime( 1013 )                                 //> res19: Boolean = true
  ( 100 to 300 ).filter( isPrime( _ ) )           //> res20: scala.collection.immutable.IndexedSeq[Int] = Vector(101, 103, 107, 1
                                                  //| 09, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 1
                                                  //| 93, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 2
                                                  //| 77, 281, 283, 293)

  /*recursive alternative:
  not evaluate all values in range to check if
  condition is true,
  stops when / if second found*/
  def secondPrime( from: Int, to: Int ) = nthPrime( from, to, 2 )
                                                  //> secondPrime: (from: Int, to: Int)Int
  def nthPrime( from: Int, to: Int, n: Int ): Int =
    if ( from >= to ) throw new Error( "no prime" )
    else if ( isPrime( from ) )
      if ( n == 1 ) from
      else nthPrime( from + 1, to, n - 1 )
    else nthPrime( from + 1, to, n )              //> nthPrime: (from: Int, to: Int, n: Int)Int

  timeNano(
    secondPrime( 100, 300 )
  )                                               //> Elapsed time: 47939ns
                                                  //| res21: Int = 103
}