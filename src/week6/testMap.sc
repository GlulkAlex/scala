package week6

object testMap {
  val romanNumerals = Map( "I" -> 1, "V" -> 5, "X" -> 10 )
                                                  //> romanNumerals  : scala.collection.immutable.Map[String,Int] = Map(I -> 1, V -
                                                  //| > 5, X -> 10)
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

  val fruit = List( "apple", "pear", "orange", "pineapple" )
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple)

  fruit sortWith ( _.length < _.length )          //> res16: List[String] = List(pear, apple, orange, pineapple)
  fruit.sorted                                    //> res17: List[String] = List(apple, orange, pear, pineapple)
  fruit groupBy ( _.head )                        //> res18: scala.collection.immutable.Map[Char,List[String]] = Map(p -> List(pe
                                                  //| ar, pineapple), a -> List(apple), o -> List(orange))
  /*A polynomal as map from exponents to coefficients
  (x3 − 2x + 5) as map:*/
  Map( 0 -> 5, 1 -> -2, 3 -> 1 )                  //> res19: scala.collection.immutable.Map[Int,Int] = Map(0 -> 5, 1 -> -2, 3 -> 
                                                  //| 1)
}