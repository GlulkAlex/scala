package week2

object testCurrying {
  /*Currying is
	a technique to
	transform function with
	'multiple parameters' to
	function with
	one parameter*/
  def multiply( x: Int,
                y: Int ) = x * y                  //> multiply: (x: Int, y: Int)Int

  ( multiply _ ).isInstanceOf[ Function2[ _, _, _ ] ] /*should be()*/
                                                  //> res0: Boolean = true

  val multiplyCurried = ( multiply _ ).curried    //> multiplyCurried  : Int => (Int => Int) = <function1>

  multiply( 4, 5 ) /*should be()*/                //> res1: Int = 20
  multiplyCurried( 3 )( 2 ) /*should be()*/       //> res2: Int = 6
  multiplyCurried( 3 )                            //> res3: Int => Int = <function1>

  /*Currying allows you to
  create specialized version of 'generalized function'*/
  def customFilter( f: Int => Boolean )( xs: List[ Int ] ) = {
    xs filter f
  }                                               //> customFilter: (f: Int => Boolean)(xs: List[Int])List[Int]

  def onlyEven( x: Int ) = x % 2 == 0             //> onlyEven: (x: Int)Boolean

  val xs = List( 12, 11, 5, 20, 3, 13, 2 )        //> xs  : List[Int] = List(12, 11, 5, 20, 3, 13, 2)
  customFilter( onlyEven )( xs ) /*should be()*/  //> res4: List[Int] = List(12, 20, 2)

  val onlyEvenFilter = customFilter( onlyEven ) _ //> onlyEvenFilter  : List[Int] => List[Int] = <function1>
  onlyEvenFilter( xs ) /*should be()*/            //> res5: List[Int] = List(12, 20, 2)
}