package week6

object sequenceOperationsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(269); 
  //*println("Welcome to the Scala worksheet")
  /*Generally, the function value:
  { case p1 => e1 ... case pn => en }
  is equivalent to:
  x => x match { case p1 => e1 ... case pn => en }*/
  val xs = Array(0, 1, 2, 3);System.out.println("""xs  : Array[Int] = """ + $show(xs ));$skip(33); 
  val ys = Array(-8, -7, -6, -5);System.out.println("""ys  : Array[Int] = """ + $show(ys ));$skip(24); val res$0 = 

  xs map (x => x * 2);System.out.println("""res0: Array[Int] = """ + $show(res$0));$skip(26); 

  val sS = "Hi, Scala!";System.out.println("""sS  : String = """ + $show(sS ));$skip(29); val res$1 = 
  sS filter (c => c.isUpper);System.out.println("""res1: String = """ + $show(res$1));$skip(24); val res$2 = 
  sS filter (_.isLower);System.out.println("""res2: String = """ + $show(res$2));$skip(24); val res$3 = 
  sS exists (_.isLower);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(24); val res$4 = 
  sS forall (_.isLower);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(24); 
  val pairs = xs zip sS;System.out.println("""pairs  : Array[(Int, Char)] = """ + $show(pairs ));$skip(25); 
  val pairs2 = sS zip xs;System.out.println("""pairs2  : scala.collection.immutable.IndexedSeq[(Char, Int)] = """ + $show(pairs2 ));$skip(25); 
  val pairs3 = xs zip ys;System.out.println("""pairs3  : Array[(Int, Int)] = """ + $show(pairs3 ));$skip(38); 
  val (array1, array2) = pairs3.unzip;System.out.println("""array1  : Array[Int] = """ + $show(array1 ));System.out.println("""array2  : Array[Int] = """ + $show(array2 ));$skip(41); val res$5 = 
  sS flatMap (charS => List('|', charS));System.out.println("""res5: String = """ + $show(res$5));$skip(12); val res$6 = 

  xs.head;System.out.println("""res6: Int = """ + $show(res$6));$skip(10); val res$7 = 
  xs.tail;System.out.println("""res7: Array[Int] = """ + $show(res$7));$skip(10); val res$8 = 
  sS.head;System.out.println("""res8: Char = """ + $show(res$8));$skip(10); val res$9 = 
  sS.tail;System.out.println("""res9: String = """ + $show(res$9));$skip(9); val res$10 = 
  xs.sum;System.out.println("""res10: Int = """ + $show(res$10));$skip(13); val res$11 = 
  xs.product;System.out.println("""res11: Int = """ + $show(res$11));$skip(9); val res$12 = 
  sS.max;System.out.println("""res12: Char = """ + $show(res$12));$skip(9); val res$13 = 
  sS.min;System.out.println("""res13: Char = """ + $show(res$13));$skip(9); val res$14 = 
  ys.min;System.out.println("""res14: Int = """ + $show(res$14));$skip(9); val res$15 = 
  ys.max;System.out.println("""res15: Int = """ + $show(res$15));$skip(100); 

  //*to (inclusive), until (exclusive), by (to determine step value):
  val rR: Range = 1 until 5;System.out.println("""rR  : Range = """ + $show(rR ));$skip(25); 
  val sR: Range = 1 to 5;System.out.println("""sR  : Range = """ + $show(sR ));$skip(15); val res$16 = 
  1 to 10 by 3;System.out.println("""res16: scala.collection.immutable.Range = """ + $show(res$16));$skip(15); val res$17 = 
  6 to 1 by -2;System.out.println("""res17: scala.collection.immutable.Range = """ + $show(res$17));$skip(201); 

  /*Example: Combinations
  To list all combinations of numbers x and y where x is drawn from
  1..M and y is drawn from 1..N:*/
  val vector1 = (1 to 4) flatMap (x => (0 until 5) map (y => (x, y)));System.out.println("""vector1  : scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(vector1 ));$skip(15); val res$18 = 
  vector1.head;System.out.println("""res18: (Int, Int) = """ + $show(res$18));$skip(15); val res$19 = 
  vector1.size;System.out.println("""res19: Int = """ + $show(res$19));$skip(13); val res$20 = 
  vector1(1);System.out.println("""res20: (Int, Int) = """ + $show(res$20));$skip(196); 

  /*Example: Scalar Product
  To compute the scalar product of two vectors:*/
  def scalarProduct1(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum;System.out.println("""scalarProduct1: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(210); 

  /*An alternative way to write this is with
  a 'pattern matching' 'function value'.*/
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map { case (x, y) => x * y }.sum;System.out.println("""scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(50); 

  val vector2 = Vector(1.2, 2.3, 3.4, 4.5, 5.6);System.out.println("""vector2  : scala.collection.immutable.Vector[Double] = """ + $show(vector2 ));$skip(48); 
  val vector3 = Vector(6.7, 7.8, 8.9, 9.1, 0.1);System.out.println("""vector3  : scala.collection.immutable.Vector[Double] = """ + $show(vector3 ));$skip(22); val res$21 = 
  vector2 zip vector3;System.out.println("""res21: scala.collection.immutable.Vector[(Double, Double)] = """ + $show(res$21));$skip(35); val res$22 = 
  scalarProduct1(vector2, vector3);System.out.println("""res22: Double = """ + $show(res$22));$skip(35); val res$23 = 
  scalarProduct2(vector2, vector3);System.out.println("""res23: Double = """ + $show(res$23));$skip(62); val res$24 = 
  1.2 * 6.7 + 2.3 * 7.8 +
  3.4 * 8.9 + 4.5 * 9.1 + 5.6 * 0.1;System.out.println("""res24: Double = """ + $show(res$24));$skip(325); 

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
  };System.out.println("""isPrime: (n: Int)Boolean""");$skip(59); 
  def delim(n: Int) = (2 until n).map { case x => n % x  };System.out.println("""delim: (n: Int)scala.collection.immutable.IndexedSeq[Int]""");$skip(142); 
  
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def isPrime2(n: Int) = (2 until n) forall (d => n % d != 0);System.out.println("""isPrime2: (n: Int)Boolean""");$skip(15); val res$25 = 
  
  delim(9);System.out.println("""res25: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$25));$skip(12); val res$26 = 
  delim(11);System.out.println("""res26: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$26));$skip(11); val res$27 = 
  delim(2);System.out.println("""res27: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$27));$skip(11); val res$28 = 
  delim(1);System.out.println("""res28: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$28));$skip(11); val res$29 = 
  delim(0);System.out.println("""res29: scala.collection.immutable.IndexedSeq[Int] = """ + $show(res$29));$skip(13); val res$30 = 
  isPrime(0);System.out.println("""res30: Boolean = """ + $show(res$30));$skip(13); val res$31 = 
  isPrime(1);System.out.println("""res31: Boolean = """ + $show(res$31));$skip(13); val res$32 = 
  isPrime(2);System.out.println("""res32: Boolean = """ + $show(res$32));$skip(13); val res$33 = 
  isPrime(3);System.out.println("""res33: Boolean = """ + $show(res$33));$skip(13); val res$34 = 
  isPrime(4);System.out.println("""res34: Boolean = """ + $show(res$34));$skip(13); val res$35 = 
  isPrime(5);System.out.println("""res35: Boolean = """ + $show(res$35));$skip(13); val res$36 = 
  isPrime(6);System.out.println("""res36: Boolean = """ + $show(res$36));$skip(13); val res$37 = 
  isPrime(7);System.out.println("""res37: Boolean = """ + $show(res$37));$skip(13); val res$38 = 
  isPrime(8);System.out.println("""res38: Boolean = """ + $show(res$38));$skip(13); val res$39 = 
  isPrime(9);System.out.println("""res39: Boolean = """ + $show(res$39));$skip(14); val res$40 = 
  isPrime(10);System.out.println("""res40: Boolean = """ + $show(res$40));$skip(14); val res$41 = 
  isPrime(11);System.out.println("""res41: Boolean = """ + $show(res$41));$skip(14); val res$42 = 
  isPrime(12);System.out.println("""res42: Boolean = """ + $show(res$42));$skip(14); val res$43 = 
  isPrime(13);System.out.println("""res43: Boolean = """ + $show(res$43));$skip(14); val res$44 = 
  isPrime(14);System.out.println("""res44: Boolean = """ + $show(res$44));$skip(14); val res$45 = 
  isPrime(15);System.out.println("""res45: Boolean = """ + $show(res$45));$skip(14); val res$46 = 
  isPrime(16);System.out.println("""res46: Boolean = """ + $show(res$46));$skip(14); val res$47 = 
  isPrime(17);System.out.println("""res47: Boolean = """ + $show(res$47));$skip(14); val res$48 = 
  isPrime(18);System.out.println("""res48: Boolean = """ + $show(res$48));$skip(14); val res$49 = 
  isPrime(19);System.out.println("""res49: Boolean = """ + $show(res$49));$skip(15); val res$50 = 
  isPrime2(19);System.out.println("""res50: Boolean = """ + $show(res$50));$skip(15); val res$51 = 
  isPrime2(20);System.out.println("""res51: Boolean = """ + $show(res$51))}
}
