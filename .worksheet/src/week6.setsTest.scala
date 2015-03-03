package week6

object setsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(125); 
  //*println("Welcome to the Scala worksheet")
  val fruit = Set("apple", "banana", "pear");System.out.println("""fruit  : scala.collection.immutable.Set[String] = """ + $show(fruit ));$skip(25); 
  val s = (1 to 6).toSet;System.out.println("""s  : scala.collection.immutable.Set[Int] = """ + $show(s ));$skip(458); 

  /*Exercise
	Write a function
	which tests
	if a 'queen' placed in an indicated column 'col' is secure
	amongst the other placed queens.
	It is assumed that
	the new 'queen' is placed in
	the next availabale 'row'
	after the other placed queens
	(in other words:
	in 'row' == queens.length).*/
  def isSafe1(col: Int, queens: List[Int]): Boolean = {
    queens match {
      case curCol :: queen if (curCol == col) => false
      case _ => true
    }
  };System.out.println("""isSafe1: (col: Int, queens: List[Int])Boolean""");$skip(421); 

  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    /*using range with step -1*/
    val queensWithRow = (row - 1 to 0 by -1) zip queens

    queensWithRow forall {
      /*check for common columns & diagonals*/
      case (r, c) => {
        (col != c) && (math.abs(col - c) != row - r)
      }
    }
  };System.out.println("""isSafe: (col: Int, queens: List[Int])Boolean""");$skip(800); 

  /*The eight queens problem is to
  place eight queens on a chessboard
	so that
	no queen is threatened by another.
	 In other words,
	 there can't be two queens in
	 the same
	 row,
	column, or
	diagonal.
	Now
	develop a solution for
	a chessboard of any size, not just 8.*/
  def queens(n: Int) = {
    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) {
        Set(List())
      } else {
        //? 'for' is block itself, so why use '{}' ?
        for {
          /*Set[List[Int]] of partial / preceeding solutions*/
          queens <- placeQueens(k - 1)
          /*try all avalable column range*/
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens /*prepend current column to previous column list*/
      }
    }

    placeQueens(n)
  };System.out.println("""queens: (n: Int)Set[List[Int]]""");$skip(360); 

  def show(queens: List[Int]) = {
    val lines =
      for {
        /*List(2, 0, 3, 1),
        List(1, 3, 0, 2)*/
        col <- queens.reverse
      } yield Vector
        .fill(queens.length)(/*"[+]"*/'*')
        //*String replace(char oldChar, char newChar)
        .updated(col, /*"[Q]"*/'Q')
        .mkString

    "\n" + (lines mkString "\n")
  };System.out.println("""show: (queens: List[Int])String""");$skip(41); 

  val sB = new StringBuilder(8, "[*]");System.out.println("""sB  : StringBuilder = """ + $show(sB ));$skip(12); val res$0 = 

  sB.size;System.out.println("""res0: Int = """ + $show(res$0));$skip(12); val res$1 = 
  sB.length;System.out.println("""res1: Int = """ + $show(res$1));$skip(13); val res$2 = 
  sB.isEmpty;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(14); val res$3 = 
  sB.capacity;System.out.println("""res3: Int = """ + $show(res$3));$skip(22); val res$4 = 
  sB.appendAll("[+]");System.out.println("""res4: StringBuilder = """ + $show(res$4));$skip(11); val res$5 = 
  sB * (8);System.out.println("""res5: String = """ + $show(res$5));$skip(25); val res$6 = 
  sB.transform(x => '+');System.out.println("""res6: week6.setsTest.sB.type = """ + $show(res$6));$skip(23); 
  sB.setCharAt(0, 'X');$skip(5); val res$7 = 
  sB;System.out.println("""res7: StringBuilder = """ + $show(res$7));$skip(20); 
  sB.update(0, 'Q');$skip(5); val res$8 = 
  sB;System.out.println("""res8: StringBuilder = """ + $show(res$8));$skip(49); 

  /*String Interpolation*/
  val name = "James";System.out.println("""name  : String = """ + $show(name ));$skip(20); 
  val height = 1.9d;System.out.println("""height  : Double = """ + $show(height ));$skip(31); 
  
  println(s"Hello, $name");$skip(31); 
  println(s"1 + 1 = ${1 + 1}");$skip(85); 
  /*'f' interpolator is typesafe*/
  println(f"$name%s is $height%2.3f meters tall");$skip(12); val res$9 = 
  raw"a\nb";System.out.println("""res9: String = """ + $show(res$9));$skip(32); 
  
  val arr = (0 to 9).toArray;System.out.println("""arr  : Array[Int] = """ + $show(arr ));$skip(35); 
  val strRange = (0 to 9).toString;System.out.println("""strRange  : String = """ + $show(strRange ));$skip(39); 
  val strFromRange = (0 to 9).mkString;System.out.println("""strFromRange  : String = """ + $show(strFromRange ));$skip(32); val res$10 = 
  new Array[String](4).mkString;System.out.println("""res10: String = """ + $show(res$10));$skip(32); val res$11 = 
  new Array[String](4).toString;System.out.println("""res11: String = """ + $show(res$11));$skip(40); val res$12 = 
  Array.fill[String](4)("[o]").mkString;System.out.println("""res12: String = """ + $show(res$12));$skip(39); val res$13 = 
  
  strFromRange.replaceAll("d", "*");System.out.println("""res13: String = """ + $show(res$13));$skip(16); val res$14 = 
  strRange.size;System.out.println("""res14: Int = """ + $show(res$14));$skip(26); val res$15 = 
  strRange map (_ => "x");System.out.println("""res15: scala.collection.immutable.IndexedSeq[String] = """ + $show(res$15));$skip(30); val res$16 = 
  strFromRange map (_ => "+");System.out.println("""res16: scala.collection.immutable.IndexedSeq[String] = """ + $show(res$16));$skip(20); val res$17 = 
  strRange.length();System.out.println("""res17: Int = """ + $show(res$17));$skip(86); val res$18 = 
  
  //new String(Array("w"), 1, 4)
  String.copyValueOf(Array('[', 'S', ']'), 0, 3);System.out.println("""res18: String = """ + $show(res$18));$skip(89); val res$19 = 
  /*expected: List(0, 3, 1) -> List((2, 0), (1, 3), (0, 1))*/
  isSafe(0, List(0, 3, 1));System.out.println("""res19: Boolean = """ + $show(res$19));$skip(46); val res$20 = 
  
  /*! Cool !*/
  String.valueOf(queens(0));System.out.println("""res20: String = """ + $show(res$20));$skip(52); val res$21 = 
  //String.withName(queens(0))
  queens(0) map show;System.out.println("""res21: scala.collection.immutable.Set[String] = """ + $show(res$21));$skip(21); val res$22 = 
  queens(1) map show;System.out.println("""res22: scala.collection.immutable.Set[String] = """ + $show(res$22));$skip(48); val res$23 = 
  //chess board size 2 * 2
  queens(2) map show;System.out.println("""res23: scala.collection.immutable.Set[String] = """ + $show(res$23));$skip(21); val res$24 = 
  queens(3) map show;System.out.println("""res24: scala.collection.immutable.Set[String] = """ + $show(res$24));$skip(12); val res$25 = 
  queens(4);System.out.println("""res25: Set[List[Int]] = """ + $show(res$25));$skip(33); val res$26 = 
  queens(4) map (x => x.reverse);System.out.println("""res26: scala.collection.immutable.Set[List[Int]] = """ + $show(res$26));$skip(37); val res$27 = 
  (queens(4) map show) mkString "\n";System.out.println("""res27: String = """ + $show(res$27));$skip(44); val res$28 = 
  (queens(8) take 3 map show) mkString "\n";System.out.println("""res28: String = """ + $show(res$28));$skip(18); val res$29 = 

  s map (_ + 2);System.out.println("""res29: scala.collection.immutable.Set[Int] = """ + $show(res$29));$skip(41); val res$30 = 
  /*duplicates removed*/
  s map (_ / 2);System.out.println("""res30: scala.collection.immutable.Set[Int] = """ + $show(res$30));$skip(93); val res$31 = 
  //fruit filter (_.startsWith == "app")
  fruit filter ((x: String) => x.startsWith("app"));System.out.println("""res31: scala.collection.immutable.Set[String] = """ + $show(res$31));$skip(13); val res$32 = 
  s.nonEmpty;System.out.println("""res32: Boolean = """ + $show(res$32));$skip(15); val res$33 = 
  s contains 5;System.out.println("""res33: Boolean = """ + $show(res$33));$skip(15); val res$34 = 
  s contains 7;System.out.println("""res34: Boolean = """ + $show(res$34))}
}
