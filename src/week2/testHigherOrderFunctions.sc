package week2

object testHigherOrderFunctions {
  /** Note:
    * watch for single comments
    * in sheet they must be on separate with code line
    * to allow show the evaluation results
    * or do not use them
    */
  /*Function returning another function:*/
  def addWithoutSyntaxSugar( x: Int ) = {
    new Function1[ Int, Int ]() {
      def apply( y: Int ): Int = x + y
    }
  }                                               //> addWithoutSyntaxSugar: (x: Int)Int => Int

  addWithoutSyntaxSugar( 1 ).
    isInstanceOf[ Function1[ Int, Int ] ] /*should be()*/
                                                  //> res0: Boolean = true

  addWithoutSyntaxSugar( 2 )( 3 ) /*should be()*/ //> res1: Int = 5

  def fiveAdder = addWithoutSyntaxSugar( 5 )      //> fiveAdder: => Int => Int

  fiveAdder( 5 ) /*should be()*/                  //> res2: Int = 10

  /*Function returning another function using
  an 'anonymous function':*/
  def addWithSyntaxSugar( x: Int ) = ( y: Int ) => x + y
                                                  //> addWithSyntaxSugar: (x: Int)Int => Int

  addWithSyntaxSugar( 1 ).isInstanceOf[ Function1[ Int, Int ] ] /*should be()*/
                                                  //> res3: Boolean = true
  addWithSyntaxSugar( 2 )( 3 ) /*should be()*/    //> res4: Int = 5

  def fiveAdder2 = addWithSyntaxSugar( 5 )        //> fiveAdder2: => Int => Int

  fiveAdder2( 5 ) /*should be()*/                 //> res5: Int = 10

  /*Function taking another function as parameter.
  Helps in / when 'composing functions'.*/

  /*Hint:
	a 'map method'
	applies the function to each element of a list*/
  def makeUpper( xs: List[ String ] ) = xs map { _.toUpperCase }
                                                  //> makeUpper: (xs: List[String])List[String]

  def makeWhatEverYouLike( xs: List[ String ],
                           sideEffect: String => String ) = {
    xs map sideEffect
  }                                               //> makeWhatEverYouLike: (xs: List[String], sideEffect: String => String)List[S
                                                  //| tring]

  makeUpper( List( "abc", "xyz", "123" ) ) /*should be()*/
                                                  //> res6: List[String] = List(ABC, XYZ, 123)

  makeWhatEverYouLike( List( "ABC", "XYZ", "123" ),
    {
      x => x.toLowerCase
    } ) /*should be()*/                           //> res7: List[String] = List(abc, xyz, 123)

  //using it inline
  List( "Scala", "Erlang", "Clojure" ) map { _.length } /*should be()*/
                                                  //> res8: List[Int] = List(5, 6, 7)
}