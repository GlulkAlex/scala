package week2

object closure {
  /*Meet 'closure'.
	A 'closure' is a function,
	whose return value depends on
	the value of
	one or more variables
	declared outside this function.*/
  /*Consider the following piece of code with
  anonymous function:*/
  val multiplier = ( i: Int ) => i * 10           //> multiplier  : Int => Int = <function1>
  /*Here
	the only variable used in
	the function body, 'i * 10',
	is 'i', which
	is defined as
	a 'parameter' to the function.*/
  var incrementer = 1                             //> incrementer  : Int = 1

  def closure = {
    x: Int => x + incrementer
  }                                               //> closure: => Int => Int

  closure                                         //> res0: Int => Int = <function1>

  val result1 = closure( 10 )                     //> result1  : Int = 11
  //result1 should be()

  incrementer = 2

  val result2 = closure( 10 )                     //> result2  : Int = 12

  /*We can
  take that closure and
  throw into a 'method' and
  it will still hold the environment*/
  def summation( x: Int,
                 y: Int => Int ) = y( x )         //> summation: (x: Int, y: Int => Int)Int

  /*var*/ incrementer = 3
  /*def closure = (x: Int) => x + incrementer*/

  val result = summation( 10, closure )           //> result  : Int = 13
  //*result should be()

  incrementer = 4
  val result3 = summation( 10, closure )          //> result3  : Int = 14
  //*result2 should be()


}