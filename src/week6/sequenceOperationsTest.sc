package week6

object sequenceOperationsTest {
  //*println("Welcome to the Scala worksheet")
  /*Generally, the function value:
  { case p1 => e1 ... case pn => en }
  is equivalent to:
  x => x match { case p1 => e1 ... case pn => en }*/
  val xs = Array(0, 1, 2, 3)                      //> xs  : Array[Int] = Array(0, 1, 2, 3)
  val ys = Array(-8, -7, -6, -5)                  //> ys  : Array[Int] = Array(-8, -7, -6, -5)

  xs map (x => x * 2)                             //> res0: Array[Int] = Array(0, 2, 4, 6)

  val sS = "Hi, Scala!"                           //> sS  : String = Hi, Scala!
  sS filter (c => c.isUpper)                      //> res1: String = HS
  sS filter (_.isLower)                           //> res2: String = icala
  sS exists (_.isLower)                           //> res3: Boolean = true
  sS forall (_.isLower)                           //> res4: Boolean = false
  val pairs = xs zip sS                           //> pairs  : Array[(Int, Char)] = Array((0,H), (1,i), (2,,), (3, ))
  val pairs2 = sS zip xs                          //> pairs2  : scala.collection.immutable.IndexedSeq[(Char, Int)] = Vector((H,0),
                                                  //|  (i,1), (,,2), ( ,3))
  val pairs3 = xs zip ys                          //> pairs3  : Array[(Int, Int)] = Array((0,-8), (1,-7), (2,-6), (3,-5))
  val (array1, array2) = pairs3.unzip             //> array1  : Array[Int] = Array(0, 1, 2, 3)
                                                  //| array2  : Array[Int] = Array(-8, -7, -6, -5)
  sS flatMap (charS => List('|', charS))          //> res5: String = |H|i|,| |S|c|a|l|a|!

  xs.head                                         //> res6: Int = 0
  xs.tail                                         //> res7: Array[Int] = Array(1, 2, 3)
  sS.head                                         //> res8: Char = H
  sS.tail                                         //> res9: String = i, Scala!
  xs.sum                                          //> res10: Int = 6
  xs.product                                      //> res11: Int = 0
  sS.max                                          //> res12: Char = l
  sS.min                                          //> res13: Char =  
  ys.min                                          //> res14: Int = -8
  ys.max                                          //> res15: Int = -5

  //*to (inclusive), until (exclusive), by (to determine step value):
  val rR: Range = 1 until 5                       //> rR  : Range = Range(1, 2, 3, 4)
  val sR: Range = 1 to 5                          //> sR  : Range = Range(1, 2, 3, 4, 5)
  1 to 10 by 3                                    //> res16: scala.collection.immutable.Range = Range(1, 4, 7, 10)
  6 to 1 by -2                                    //> res17: scala.collection.immutable.Range = Range(6, 4, 2)

  /*Example: Combinations
  To list all combinations of numbers x and y where x is drawn from
  1..M and y is drawn from 1..N:*/
  val vector1 = (1 to 4) flatMap (x => (0 until 5) map (y => (x, y)))
                                                  //> vector1  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,0)
                                                  //| , (1,1), (1,2), (1,3), (1,4), (2,0), (2,1), (2,2), (2,3), (2,4), (3,0), (3,
                                                  //| 1), (3,2), (3,3), (3,4), (4,0), (4,1), (4,2), (4,3), (4,4))
  vector1.head                                    //> res18: (Int, Int) = (1,0)
  vector1.size                                    //> res19: Int = 20
  vector1(1)                                      //> res20: (Int, Int) = (1,1)

  /*Example: Scalar Product
  To compute the scalar product of two vectors:*/
  def scalarProduct1(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum      //> scalarProduct1: (xs: Vector[Double], ys: Vector[Double])Double

  /*An alternative way to write this is with
  a 'pattern matching' 'function value'.*/
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map { case (x, y) => x * y }.sum  //> scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double

  val vector2 = Vector(1.2, 2.3, 3.4, 4.5, 5.6)   //> vector2  : scala.collection.immutable.Vector[Double] = Vector(1.2, 2.3, 3.4
                                                  //| , 4.5, 5.6)
  val vector3 = Vector(6.7, 7.8, 8.9, 9.1, 0.1)   //> vector3  : scala.collection.immutable.Vector[Double] = Vector(6.7, 7.8, 8.9
                                                  //| , 9.1, 0.1)
  vector2 zip vector3                             //> res21: scala.collection.immutable.Vector[(Double, Double)] = Vector((1.2,6.
                                                  //| 7), (2.3,7.8), (3.4,8.9), (4.5,9.1), (5.6,0.1))
  scalarProduct1(vector2, vector3)                //> res22: Double = 97.75
  scalarProduct2(vector2, vector3)                //> res23: Double = 97.75
  1.2 * 6.7 + 2.3 * 7.8 +
  3.4 * 8.9 + 4.5 * 9.1 + 5.6 * 0.1               //> res24: Double = 97.75

  /*Excersise:
 A number 'n' is prime if
 the only 'divisors' of 'n' are '1' and 'n' itself.
  What is a high-level way to
  write a test for
  primality of numbers?
  For once, value conciseness over efficiency.*/
  def isPrime(n: Int): Boolean = {
    //1=t,2=t,3=t,4=f,5=t,6=f,7=t...
    !(delim(n) exists (_ == 0))
  }                                               //> isPrime: (n: Int)Boolean
  def delim(n: Int) = (2 until n).map { case x => n % x  }
                                                  //> delim: (n: Int)scala.collection.immutable.IndexedSeq[Int]
  
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def isPrime2(n: Int) = (2 until n) forall (d => n % d != 0)
                                                  //> isPrime2: (n: Int)Boolean
  
  delim(9)                                        //> res25: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 0, 1, 4, 3, 2
                                                  //| , 1)
  delim(11)                                       //> res26: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 3, 1, 5, 4
                                                  //| , 3, 2, 1)
  delim(2)                                        //> res27: scala.collection.immutable.IndexedSeq[Int] = Vector()
  delim(1)                                        //> res28: scala.collection.immutable.IndexedSeq[Int] = Vector()
  delim(0)                                        //> res29: scala.collection.immutable.IndexedSeq[Int] = Vector()
  isPrime(0)                                      //> res30: Boolean = true
  isPrime(1)                                      //> res31: Boolean = true
  isPrime(2)                                      //> res32: Boolean = true
  isPrime(3)                                      //> res33: Boolean = true
  isPrime(4)                                      //> res34: Boolean = false
  isPrime(5)                                      //> res35: Boolean = true
  isPrime(6)                                      //> res36: Boolean = false
  isPrime(7)                                      //> res37: Boolean = true
  isPrime(8)                                      //> res38: Boolean = false
  isPrime(9)                                      //> res39: Boolean = false
  isPrime(10)                                     //> res40: Boolean = false
  isPrime(11)                                     //> res41: Boolean = true
  isPrime(12)                                     //> res42: Boolean = false
  isPrime(13)                                     //> res43: Boolean = true
  isPrime(14)                                     //> res44: Boolean = false
  isPrime(15)                                     //> res45: Boolean = false
  isPrime(16)                                     //> res46: Boolean = false
  isPrime(17)                                     //> res47: Boolean = true
  isPrime(18)                                     //> res48: Boolean = false
  isPrime(19)                                     //> res49: Boolean = true
  isPrime2(19)                                    //> res50: Boolean = true
  isPrime2(20)                                    //> res51: Boolean = false
}