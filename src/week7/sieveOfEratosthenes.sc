package week7

object sieveOfEratosthenes {
  /*The Sieve of Eratosthenes is
an ancient technique to
calculate prime numbers.

The idea is as follows:
-> Start with all integers from 2, the ﬁrst prime number.
-> Eliminate all multiples of 2.
-> The ﬁrst element of the resulting list is 3, a prime number.
-> Eliminate all multiples of 3.
-> Iterate forever. At each step, the ﬁrst number in the list is a
prime number and we eliminate all its multiples.*/
  def from1( n: Int ): Stream[ Int ] = n #:: from1( n + 1 )
                                                  //> from1: (n: Int)Stream[Int]
  /**function that implements this principle:*/
  def sieve( s: Stream[ Int ] ): Stream[ Int ] =
    /*s.head is presumed 'Prime' number for example == 2*/
    s.head #:: sieve( s.tail filter ( _ % s.head != 0 ) )
                                                  //> sieve: (s: Stream[Int])Stream[Int]
  val primes = sieve( from1( 2 ) )                //> primes  : Stream[Int] = Stream(2, ?)
  
  from1( 2 ).tail                                 //> res0: scala.collection.immutable.Stream[Int] = Stream(3, ?)
  from1( 2 ).tail.head                            //> res1: Int = 3
  /*To see
the list of the ﬁrst 'N' prime numbers,
you can write:*/
  ( primes take 9 ).toList                        //> res2: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23)
}