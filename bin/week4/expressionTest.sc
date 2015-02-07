package week4
//import week4.Number
//it must see (sometimes with notisible lag)
//defenitions of all class types
//in the same package
object expressionTest {
  println("Welcome to the Scala worksheet")
  /**
   * Functions and Methods Implementation
   * by Martin Odersky
   */
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(lft, rgt) => show(lft) + " + " + show(rgt)
  }

  show(Sum(Number(7), Number(9)))

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
  }
  /*override def eval(e: Expr): String = e match {
    //additional
    case Var(x) => x
  }*/

  //eval(Var("x"))
}