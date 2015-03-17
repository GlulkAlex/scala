package week2

object lambda {
  def lambda = { x: Int => x + 1 }                //> lambda: => Int => Int
  def lambda2 = ( x: Int ) => x + 1               //> lambda2: => Int => Int
  val lambda3 = ( x: Int ) => x + 1               //> lambda3  : Int => Int = <function1>

  val lambda4 = new Function1[ Int, Int ] {
    def apply( v1: Int ): Int = v1 + 1
  }                                               //> lambda4  : Int => Int = <function1>

  def lambda5( x: Int ) = x + 1                   //> lambda5: (x: Int)Int

  val result = lambda( 3 )                        //> result  : Int = 4
  val `result1andhalf` = lambda.apply( 3 )        //> result1andhalf  : Int = 4

  val result2 = lambda2( 3 )                      //> result2  : Int = 4
  val result3 = lambda3( 3 )                      //> result3  : Int = 4
  val result4 = lambda4( 3 )                      //> result4  : Int = 4
  val result5 = lambda5( 3 )                      //> result5  : Int = 4
}