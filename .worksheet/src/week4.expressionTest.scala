package week4
//import week4.Number
//it must see (sometimes with notisible lag)
//defenitions of all class types
//in the same package
object expressionTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(203); 
  println("Welcome to the Scala worksheet");$skip(210); 
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(lft, rgt) => show(lft) + " + " + show(rgt)
  };System.out.println("""show: (e: week4.Expr)String""");$skip(35); val res$0 = 

  show(Sum(Number(7), Number(9)));System.out.println("""res0: String = """ + $show(res$0));$skip(373); 

  /*Example:
		Sum(Prod(2, Var(”x”)), Var(”y”))
		should print as “2 * x + y”. But
		Prod(Sum(2, Var(”x”)), Var(”y”))
		should print as "(2 * x) + y".*/
  def improvedShow(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(lft, rgt) => show(lft) + " + " + show(rgt)
    case Var(x) => x
    case Prod(lft, rgt) => show(lft) + " * " + show(rgt)
  };System.out.println("""improvedShow: (e: week4.Expr)String""")}
  /*override def eval(e: Expr): String = e match {
    //additional
    case Var(x) => x
  }*/

  //eval(Var("x"))
}
