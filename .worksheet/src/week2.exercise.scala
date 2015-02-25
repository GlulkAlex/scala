package week2

object exercise {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(672); 
  //Note:
  //Caution: in this Eclipse vertion
  //Sheets are works not as expected
  //differ from scala Read-Eval-Print-Loop console
  //some bugs / glitches will expected

  //println("Welcome to the Scala worksheet")

  /*Write a tail-recursive version of sum.*/
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

  };System.out.println("""trSum: (f: Int => Int, a: Int, b: Int)Int""");$skip(24); val res$0 = 

  trSum(x => x, 1, 9);System.out.println("""res0: Int = """ + $show(res$0));$skip(226); 

  /**/
  def myTRsum(f: Int => Int, a: Int, b: Int): Int = {
    //def id(x: Int): Int = x
    def fSum(x: Int): Int =
      f(x) + myTRsum(x => x, x + 1, b) //acc ?

    if (a > b)
      0
    else
      fSum(a)
  };System.out.println("""myTRsum: (f: Int => Int, a: Int, b: Int)Int""");$skip(32); val res$1 =  //Ok.

  myTRsum(x => x, 1, 9);System.out.println("""res1: Int = """ + $show(res$1));$skip(164);  //Ok.

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    //def id(x: Int): Int = x

    if (a > b)
      0
    else
      f(a) + sum(f, a + 1, b) //acc ?
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(28); val res$2 =  //Ok.

  sum(x => x, 1, 9);System.out.println("""res2: Int = """ + $show(res$2));$skip(125);  //Ok.

  /** Unit test */
  def main(args: Array[String]): Unit = {
    println("sum(x => x, 1, 9) is: " + sum(x => x, 1, 9))
  };System.out.println("""main: (args: Array[String])Unit""")}

}
