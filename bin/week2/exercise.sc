package week2

object exercise {
  //Note:
  //Caution: in this Eclipse vertion
  //Sheets are works not as expected
  //differ from scala Read-Eval-Print-Loop console 
  //some bugs / glitches will expected 

  //println("Welcome to the Scala worksheet")

  //Write a tail-recursive version of sum.
  //def trSum(f: Int => Int)(a: Int, b: Int): Int = {
  //too many parentethies ?
  def trSum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int = {
      /*if (???) ???
      else loop(???, ???)*/
      if (a > b) //Ok.
        acc //Ok.
      else
        loop(a + 1, f(a) + acc) //a + 1 Ok.
    }

    //loop(???, ???)
    loop(a, 0) //Ok.

  } //> trSum: (f: Int => Int, a: Int, b: Int)Int

  trSum(x => x, 1, 9) //> res0: Int = 45

  /**/ def myTRsum(f: Int => Int, a: Int, b: Int): Int = {
    //def id(x: Int): Int = x
    def fSum(x: Int): Int =
      f(x) + myTRsum(x => x, x + 1, b) //acc ?

    if (a > b)
      0
    else
      fSum(a)
  } //Ok.                                         //> myTRsum: (f: Int => Int, a: Int, b: Int)Int

  myTRsum(x => x, 1, 9) //Ok.                     //> res1: Int = 45

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    //def id(x: Int): Int = x

    if (a > b)
      0
    else
      f(a) + sum(f, a + 1, b) //acc ?
  } //Ok.                                         //> sum: (f: Int => Int, a: Int, b: Int)Int

  sum(x => x, 1, 9) //Ok.                         //> res2: Int = 45

  /** Unit test */
  def main(args: Array[String]): Unit = {
    println("sum(x => x, 1, 9) is: " + sum(x => x, 1, 9))
  } //> main: (args: Array[String])Unit

}