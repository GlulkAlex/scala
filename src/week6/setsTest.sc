package week6

object setsTest {
  //*println("Welcome to the Scala worksheet")
  val fruit = Set("apple", "banana", "pear")      //> fruit  : scala.collection.immutable.Set[String] = Set(apple, banana, pear)
  val s = (1 to 6).toSet                          //> s  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 3, 4)

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
  }                                               //> isSafe1: (col: Int, queens: List[Int])Boolean

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
  }                                               //> isSafe: (col: Int, queens: List[Int])Boolean

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
  }                                               //> queens: (n: Int)Set[List[Int]]

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
  }                                               //> show: (queens: List[Int])String

  val sB = new StringBuilder(8, "[*]")            //> sB  : StringBuilder = [*]

  sB.size                                         //> res0: Int = 3
  sB.length                                       //> res1: Int = 3
  sB.isEmpty                                      //> res2: Boolean = false
  sB.capacity                                     //> res3: Int = 11
  sB.appendAll("[+]")                             //> res4: StringBuilder = [*][+]
  sB * (8)                                        //> res5: String = [*][+][*][+][*][+][*][+][*][+][*][+][*][+][*][+]
  sB.transform(x => '+')                          //> res6: week6.setsTest.sB.type = ++++++
  sB.setCharAt(0, 'X')
  sB                                              //> res7: StringBuilder = X+++++
  sB.update(0, 'Q')
  sB                                              //> res8: StringBuilder = Q+++++

  /*String Interpolation*/
  val name = "James"                              //> name  : String = James
  val height = 1.9d                               //> height  : Double = 1.9
  
  println(s"Hello, $name")                        //> Hello, James
  println(s"1 + 1 = ${1 + 1}")                    //> 1 + 1 = 2
  /*'f' interpolator is typesafe*/
  println(f"$name%s is $height%2.3f meters tall") //> James is 1,900 meters tall
  raw"a\nb"                                       //> res9: String = a\nb
  
  val arr = (0 to 9).toArray                      //> arr  : Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val strRange = (0 to 9).toString                //> strRange  : String = Range(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
  val strFromRange = (0 to 9).mkString            //> strFromRange  : String = 0123456789
  new Array[String](4).mkString                   //> res10: String = nullnullnullnull
  new Array[String](4).toString                   //> res11: String = [Ljava.lang.String;@193948d
  Array.fill[String](4)("[o]").mkString           //> res12: String = [o][o][o][o]
  
  strFromRange.replaceAll("d", "*")               //> res13: String = 0123456789
  strRange.size                                   //> res14: Int = 35
  strRange map (_ => "x")                         //> res15: scala.collection.immutable.IndexedSeq[String] = Vector(x, x, x, x, x
                                                  //| , x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x
                                                  //| , x, x, x, x, x)
  strFromRange map (_ => "+")                     //> res16: scala.collection.immutable.IndexedSeq[String] = Vector(+, +, +, +, +
                                                  //| , +, +, +, +, +)
  strRange.length()                               //> res17: Int = 35
  
  //new String(Array("w"), 1, 4)
  String.copyValueOf(Array('[', 'S', ']'), 0, 3)  //> res18: String = [S]
  /*expected: List(0, 3, 1) -> List((2, 0), (1, 3), (0, 1))*/
  isSafe(0, List(0, 3, 1))                        //> res19: Boolean = false
  
  /*! Cool !*/
  String.valueOf(queens(0))                       //> res20: String = Set(List())
  //String.withName(queens(0))
  queens(0) map show                              //> res21: scala.collection.immutable.Set[String] = Set("
                                                  //| ")
  queens(1) map show                              //> res22: scala.collection.immutable.Set[String] = Set("
                                                  //| Q")
  //chess board size 2 * 2
  queens(2) map show                              //> res23: scala.collection.immutable.Set[String] = Set()
  queens(3) map show                              //> res24: scala.collection.immutable.Set[String] = Set()
  queens(4)                                       //> res25: Set[List[Int]] = Set(List(1, 3, 0, 2), List(2, 0, 3, 1))
  queens(4) map (x => x.reverse)                  //> res26: scala.collection.immutable.Set[List[Int]] = Set(List(2, 0, 3, 1), Li
                                                  //| st(1, 3, 0, 2))
  (queens(4) map show) mkString "\n"              //> res27: String = "
                                                  //| **Q*
                                                  //| Q***
                                                  //| ***Q
                                                  //| *Q**
                                                  //| 
                                                  //| *Q**
                                                  //| ***Q
                                                  //| Q***
                                                  //| **Q*"
  (queens(8) take 3 map show) mkString "\n"       //> res28: String = "
                                                  //| *****Q**
                                                  //| ***Q****
                                                  //| *Q******
                                                  //| *******Q
                                                  //| ****Q***
                                                  //| ******Q*
                                                  //| Q*******
                                                  //| **Q*****
                                                  //| 
                                                  //| ****Q***
                                                  //| ******Q*
                                                  //| *Q******
                                                  //| ***Q****
                                                  //| *******Q
                                                  //| Q*******
                                                  //| **Q*****
                                                  //| *****Q**
                                                  //| 
                                                  //| *****Q**
                                                  //| **Q*****
                                                  //| ******Q*
                                                  //| ***Q****
                                                  //| Q*******
                                                  //| *******Q
                                                  //| *Q******
                                                  //| ****Q***"

  s map (_ + 2)                                   //> res29: scala.collection.immutable.Set[Int] = Set(5, 6, 7, 3, 8, 4)
  /*duplicates removed*/
  s map (_ / 2)                                   //> res30: scala.collection.immutable.Set[Int] = Set(2, 0, 3, 1)
  //fruit filter (_.startsWith == "app")
  fruit filter ((x: String) => x.startsWith("app"))
                                                  //> res31: scala.collection.immutable.Set[String] = Set(apple)
  s.nonEmpty                                      //> res32: Boolean = true
  s contains 5                                    //> res33: Boolean = true
  s contains 7                                    //> res34: Boolean = false
}