package week4

/*
trait Expr {
  //what object / instance
  //are we dealing with ? actually 
  /* Classification methods: */
  def isNumber: Boolean
  def isSum: Boolean
  //possible extensions:
  def isProd: Boolean //for Product 
  def isVar: Boolean //for Variable

  /* Access methods / Getter */
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
  //possible extensions:
  def varName: String
}
* 
*/
//all subclasses of 'Expr' need to implement all it's methods
//previously: 5 * 3 = 15 methods
//currently / now 8 * 5 = 40 methods
//so, adding 2 new classes cost adding 40 - 15 = 25 new methods
//in general, it (methods number) tends to grow quadratically 
/*
class Number(n: Int) extends Expr {
  //trait methods implementation for current class 
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  //operands
  //not applicable to current class, so - exception
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}
class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}
* 
*/
//an evaluation function as follows.
//expected: eval(Sum(Number(1), Number(2)) = 3
/*
//warper / container needed
def eval(e: Expr): Int = {
  if (e.isNumber) 
    e.numValue
  else if (e.isSum) 
    eval(e.leftOp) + eval(e.rightOp)
  else //for future methods expansion / addition
    throw new Error("Unknown expression " + e)
}
* 
*/
//*Here’s a formulation of the eval method using type tests and casts:
/*
def eval(e: Expr): Int =
  if (e.isInstanceOf[Number])
  	e.asInstanceOf[Number].numValue
	else if (e.isInstanceOf[Sum])
		eval(e.asInstanceOf[Sum].leftOp) +
		eval(e.asInstanceOf[Sum].rightOp)
	else 
    throw new Error(”Unknown expression ” + e)
*/

/* So, 
 * what happens if you want to add new expression forms, say:
 */
//*class Prod(e1: Expr, e2: Expr) extends Expr // e1 * e2
//*class Var(x: String) extends Expr // Variable ‘x’
/* You need to 
 * add methods for classification and access to all classes
 * defined above.
 */
//*methods defined in class 'Any':
//*def isInstanceOf[T]: Boolean // checks whether this object’s type conforms to ‘T‘
//*def asInstanceOf[T]: T // treats this object as an instance of type ‘T‘
// throws ‘ClassCastException‘ if it isn’t.
//* These correspond to Java’s type tests and casts:
//*Scala              //*Java
//*x.isInstanceOf[T] //*x instanceof T
//*x.asInstanceOf[T] //*(T) x
/* But their use in Scala is discouraged, 
 * because there are better
 * alternatives. */

/**
 * Solution 1:
 *  Object-Oriented Decomposition
 *  For example,
 *  suppose that all you want to do is evaluate expressions.
 *  You could then define:
 */
/*trait Expr {
  def eval: Int
  //adding new methods
  def show: String //implementation need to be in all subClasses
  def simplify: String //or Expr ?
}
class Number(n: Int) extends Expr {
  def eval: Int = n
}
class Sum(e1: Expr, e2: Expr) extends Expr {
  def eval: Int = e1.eval + e2.eval
}*/
/*But what happens if you’d like to display expressions now?
You have to define new methods in all the subclasses*/

//warper / container needed
/*def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}*/

//object testExpr {
trait Expr {
  //case class Number(n: Int) extends Expr
  //case class Sum(e1: Expr, e2: Expr) extends Expr

  //recursive method needs - return type 
  def eval: Int = this match {
    //def eval = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
    //additional
    //type mismatch
    case Var(x) => 1 //Nothing //x //null
    case Prod(e1, e2) => e1.eval * e2.eval
  }
  //overload - not working -- ? wrong syntax ?
  /*override def eval: String = this match {
    //additional
    case Var(x) => x
  }*/
  //adding new methods
  def show: String = this match {
    case Number(n) => "" + n + ""
    //*case Sum(e1, e2) => "" + e1.eval + " + " + e2.eval + " = " + (e1.eval + e2.eval)
    case Sum(e1, e2) => e1.show + " + " + e2.show
    case Var(x) => x
    //sub cases based on operations priority 
    //so, parentheses needed 
    //*case Prod(lft, rgt) => lft.show + " * " + rgt.show
    case Prod(lft, rgt) => showMatched(lft) + /*{
      lft match {
        case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
        case Number(n) => "" + n + ""
        case Var(x) => x
      }
    } + */" * " + showMatched(rgt) /*{
      rgt match {
        case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
        case Number(n) => "" + n + ""
        case Var(x) => x
      }
    }*/
  }
  def showMatched(e: Expr): String = e match {
    case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
    case Number(n) => "" + n + ""
    case Var(x) => x
  }
  //def simplify: String //or Expr ?
  //*Number(1)
  //*val onePlus2 = Sum(Number(1), Number(2)).show

  //'main' mast be in object
  //def main(args: Array[String]) = {
  //println("num0 is: " + Sum(Number(1), Number(2)).show)
  //}
}

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
//additional
case class Prod(e1: Expr, e2: Expr) extends Expr // e1 * e2
case class Var(x: String) extends Expr // Variable ‘x’

/* 
 * Special syntax exists for procedures, 
 * i.e. functions that return the 'Unit' value ()
 * def f(ps) {stats} 'is equivalent / <=> to' def f(ps): Unit = {stats}. */
object testExpr {
  //*object testExpr extends Expr{
  //out of 'trait' scope
  val numbOne = Number(1)
  val onePlus2 = Sum(Number(1), Number(2))

  def main(args: Array[String]) = {
    //System.out.println(str)
    println("onePlus2 is: " + onePlus2)
    println("numbOne is: " + numbOne)
    println("numbOne.show is: " + numbOne.show)
    println("numbOne.eval is: " + numbOne.eval)
    println("onePlus2.eval is: " + onePlus2.eval)
    println("onePlus2.show is: " + onePlus2.show)
    println("Var('x') is: " + Var("x"))
    println("Var('x').eval is: " + Var("x").eval)
    /*Example:
    Sum(Prod(2, Var(”x”)), Var(”y”))
    should print as “2 * x + y”. But
    Prod(Sum(2, Var(”x”)), Var(”y”))
    should print as "(2 * x) + y".*/
    println("Sum(Prod(2, Var('x')), Var('y')) is: " +
      Sum(Prod(Number(2), Var("x")), Var("y")))
    println("Sum(Prod(2, Var('x')), Var('y')).show is: " +
      Sum(Prod(Number(2), Var("x")), Var("y")).show)
    println("Prod(Sum(3, Var('x')), Var('y')).show is: " +
      Prod(Sum(Number(3), Var("x")), Var("y")).show)
    println("Prod(Var('x'), Sum(7, Var('y')) ).show is: " +
      Prod(Var("x"), Sum(Number(7), Var("y"))).show)
  }
}