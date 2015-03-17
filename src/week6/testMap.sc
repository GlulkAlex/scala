package week6
//import scala.Predef.exceptionWrapper

object testMap {
  val romanNumerals = Map( "I" -> 1, "V" -> 5, "X" -> 10 )
                                                  //> romanNumerals  : scala.collection.immutable.Map[String,Int] = Map(I -> 1, V 
                                                  //| -> 5, X -> 10)
  val capitalOfCountry = Map( "US" -> "Washington", "Switzerland" -> "Bern" )
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US -
                                                  //| > Washington, Switzerland -> Bern)
  capitalOfCountry( "US" )                        //> res0: String = Washington
  //capitalOfCountry("USSR") /*java.util.NoSuchElementException*/
  capitalOfCountry get "US"                       //> res1: Option[String] = Some(Washington)
  capitalOfCountry get "USSR"                     //> res2: Option[String] = None

  val countryOfCapital = capitalOfCountry map {
    case ( x, y ) => ( y, x )
  }                                               //> countryOfCapital  : scala.collection.immutable.Map[String,String] = Map(Wash
                                                  //| ington -> US, Bern -> Switzerland)

  def showCapital( country: String ) = capitalOfCountry
    .get( country ) match {
      case Some( capital ) => capital
      case None            => "missing data"
    }                                             //> showCapital: (country: String)String

  /* pattern matching cases alternative
  using 'combinator' methods of the 'Option' class*/
  def getMapValue( s: String, someMap: Map[ String, Any ] ): String =
    someMap
      .get( s )
      /*? no map for 'None', for Some[T] only ?*/
      .map( "Value found: " + _ )
      .getOrElse( "No value found" )              //> getMapValue: (s: String, someMap: Map[String,Any])String

  showCapital( "US" )                             //> res3: String = Washington
  showCapital( "USSR" )                           //> res4: String = missing data
  getMapValue( "US", capitalOfCountry )           //> res5: String = Value found: Washington
  getMapValue( "USSR", capitalOfCountry )         //> res6: String = No value found

  ( capitalOfCountry get "USSR" ).orNull          //> res7: String = null
  ( capitalOfCountry get "US" ).orNull            //> res8: String = Washington
  ( capitalOfCountry get "US" ).filter( x => x startsWith "Bird" )
                                                  //> res9: Option[String] = None
  ( capitalOfCountry get "US" ).filter( x => x startsWith "U" )
                                                  //> res10: Option[String] = None
  ( capitalOfCountry get "US" ).filter( x => x startsWith "US" )
                                                  //> res11: Option[String] = None
  ( capitalOfCountry get "USSR" ).getOrElse( "Nothing found" )
                                                  //> res12: String = Nothing found
  ( capitalOfCountry get "USSR" ).orElse( capitalOfCountry get "US" )
                                                  //> res13: Option[String] = Some(Washington)

  val cap1 = capitalOfCountry withDefaultValue "<unknown>"
                                                  //> cap1  : scala.collection.immutable.Map[String,String] = Map(US -> Washingto
                                                  //| n, Switzerland -> Bern)
  cap1 get "USSR"                                 //> res14: Option[String] = None
  cap1( "USSR" )                                  //> res15: String = <unknown>

  val fruit = List( "apple", "pear", "orange", "pineapple", "Apricot" )
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple, Apricot)

  fruit sortWith ( _.length < _.length )          //> res16: List[String] = List(pear, apple, orange, Apricot, pineapple)
  fruit.sorted                                    //> res17: List[String] = List(Apricot, apple, orange, pear, pineapple)
  fruit.sorted(Ordering[String].reverse)          //> res18: List[String] = List(pineapple, pear, orange, apple, Apricot)
  fruit groupBy ( _.head )                        //> res19: scala.collection.immutable.Map[Char,List[String]] = Map(A -> List(Ap
                                                  //| ricot), p -> List(pear, pineapple), a -> List(apple), o -> List(orange))
  /*A polynomal as map from exponents to coefficients
  (x3 − 2x + 5) as map:*/
  Map( 0 -> 5, 1 -> -2, 3 -> 1 )                  //> res20: scala.collection.immutable.Map[Int,Int] = Map(0 -> 5, 1 -> -2, 3 -> 
                                                  //| 1)
  /*duplicated values
  in 'keys' are
  dropped / ommited like in 'Set'
  only unique remains*/
  Map( 0 -> 5, 0 -> -2, 0 -> 5, 1 -> 5 )          //> res21: scala.collection.immutable.Map[Int,Int] = Map(0 -> 5, 1 -> 5)

  val fruitMap = fruit
    .zipWithIndex
    .map { case ( x, y ) => ( y, x ) }
    .toMap                                        //> fruitMap  : scala.collection.immutable.Map[Int,String] = Map(0 -> apple, 1 
                                                  //| -> pear, 2 -> orange, 3 -> pineapple, 4 -> Apricot)
  fruitMap.get( 1 )                               //> res22: Option[String] = Some(pear)
  fruit
    .zipWithIndex
    .head                                         //> res23: (String, Int) = (apple,0)
  fruit
    .zipWithIndex
    .take( 2 )
    .head
    .swap                                         //> res24: (Int, String) = (0,apple)
  fruit( 1 )                                      //> res25: String = pear
  /*exception: IndexOutOfBoundsException*/
  //intercept[java.util.InputMismatchException]{
  //intercept[java.lang.IndexOutOfBoundsException]{
  //fruit(5)
  //}
  /*not work: not have that method
  fruit
    .getOrElse( 1, "no z index found in collection" )*/
  fruitMap
    .getOrElse( 1, "no z index found in collection" )
                                                  //> res26: String = pear
  fruitMap
    .getOrElse( 5, "no z index found in collection" )
                                                  //> res27: String = no z index found in collection
}