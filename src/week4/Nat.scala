package week4

//so-called: Peano numbers
/**
 * Non-negative integers
 */
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat //throw exception if Zero
  //def successor: Nat
  def successor: Nat =  new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat //throw exception if less then Zero
}

//singleton 
object Zero extends Nat {
  //implementation of all abstract methods 
  def isZero = true
  def predecessor: Nat = throw new Error("0.predecessor")
  //def successor: Nat = new Succ(Zero)
  
  //0 + number = number
  def + (that: Nat): Nat = that
  def - (that: Nat): Nat = 
    if (that.isZero) 
      Zero //'this'
    else
      throw new Error("negative number") 

  override def toString = {
    "0"
  }  
}

//parameter 'n: Nat' is a predecessor
class Succ(n: Nat) extends Nat {
  //as non-negative, so
  def isZero = false
  //by definition of 'n'
  def predecessor: Nat = n 
  //same as for 'Zero' so, refactor it and
  //place at / in the base class
  //def successor: Nat =  new Succ(this)
  //'n' less then 'this' for 1: this - n = 1
  def + (that: Nat): Nat = /*this + that*/new Succ(n + that)
  def - (that: Nat): Nat = /*this - that*/
    if (that.isZero)
      this
    else  
      n - that.predecessor

  override def toString = {
    "(" + n + "+1)"
  }       
}

object nonNegativeIntegers {
  val num0 = Zero
  val num1 = new Succ(Zero)
  val num2 = new Succ(num1)
  
  //standalone applications *.scala files
  /** Unit test */
  //def main(args: Array[String]): Unit = {
  //or
  def main(args: Array[String]) = {
    println("num0 is: " + num0)
    println("num1 is: " + num1)
    println("num2 is: " + num2)
    println("num2.predecessor is: " + num2.predecessor)
    println("num2.successor is: " + num2.successor)
    println("num1 + num2 is: " + (num1 + num2))
    println("num2 - num1 is: " + (num2 - num1))
    println("num2 - num0 is: " + (num2 - num0))
    //println("num1 - num2 is: " + (num1 - num2))//exception
  } 
}