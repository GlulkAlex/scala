package week6

object mapTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(138); 
  //*println("Welcome to the Scala worksheet")
  val romanNumerals = Map( "I" -> 1, "V" -> 5, "X" -> 10 );System.out.println("""romanNumerals  : scala#25.collection#2740.immutable#5830.Map#7869[String#242,Int#1092] = """ + $show(romanNumerals ));$skip(78); 
  val capitalOfCountry = Map( "US" -> "Washington", "Switzerland" -> "Bern" );System.out.println("""capitalOfCountry  : scala#25.collection#2740.immutable#5830.Map#7869[String#242,String#242] = """ + $show(capitalOfCountry ));$skip(27); val res$0 = 
  capitalOfCountry( "US" );System.out.println("""res0: String#242 = """ + $show(res$0));$skip(94); val res$1 = 
  //capitalOfCountry("USSR") /*java.util.NoSuchElementException*/
  capitalOfCountry get "US";System.out.println("""res1: Option#1923[String#242] = """ + $show(res$1));$skip(30); val res$2 = 
  capitalOfCountry get "USSR";System.out.println("""res2: Option#1923[String#242] = """ + $show(res$2));$skip(82); 
  val countryOfCapital = capitalOfCountry map {
    case ( x, y ) => ( y, x )
  };System.out.println("""countryOfCapital  : scala#25.collection#2740.immutable#5830.Map#7869[String#242,String#242] = """ + $show(countryOfCapital ));$skip(175); 

  def showCapital( country: String ) = capitalOfCountry
    .get( country ) match {
      case Some( capital ) => capital
      case None            => "missing data"
    };System.out.println("""showCapital: (country#3011272: String#2708139)String#242""");$skip(24); val res$3 = 

  showCapital( "US" );System.out.println("""res3: String#242 = """ + $show(res$3));$skip(24); val res$4 = 
  showCapital( "USSR" );System.out.println("""res4: String#242 = """ + $show(res$4));$skip(43); val res$5 = 

  ( capitalOfCountry get "USSR" ).orNull;System.out.println("""res5: String#242 = """ + $show(res$5));$skip(39); val res$6 = 
  ( capitalOfCountry get "US" ).orNull;System.out.println("""res6: String#242 = """ + $show(res$6));$skip(67); val res$7 = 
  ( capitalOfCountry get "US" ).filter( x => x startsWith "Bird" );System.out.println("""res7: Option#1923[String#242] = """ + $show(res$7));$skip(64); val res$8 = 
  ( capitalOfCountry get "US" ).filter( x => x startsWith "U" );System.out.println("""res8: Option#1923[String#242] = """ + $show(res$8));$skip(65); val res$9 = 
  ( capitalOfCountry get "US" ).filter( x => x startsWith "US" );System.out.println("""res9: Option#1923[String#242] = """ + $show(res$9));$skip(63); val res$10 = 
  ( capitalOfCountry get "USSR" ).getOrElse( "Nothing found" );System.out.println("""res10: String#242 = """ + $show(res$10));$skip(70); val res$11 = 
  ( capitalOfCountry get "USSR" ).orElse( capitalOfCountry get "US" );System.out.println("""res11: Option#1923[String#242] = """ + $show(res$11));$skip(62); 
  
  val cap1 = capitalOfCountry withDefaultValue "<unknown>";System.out.println("""cap1  : scala#25.collection#2740.immutable#5830.Map#7869[String#242,String#242] = """ + $show(cap1 ));$skip(18); val res$12 = 
  cap1 get "USSR";System.out.println("""res12: Option#1923[String#242] = """ + $show(res$12));$skip(18); val res$13 = 
  cap1 ( "USSR" );System.out.println("""res13: String#242 = """ + $show(res$13));$skip(65); 
  
  val fruit = List( "apple", "pear", "orange", "pineapple" );System.out.println("""fruit  : List#8601[String#242] = """ + $show(fruit ));$skip(43); val res$14 = 

  fruit sortWith ( _.length < _.length );System.out.println("""res14: List#8601[String#242] = """ + $show(res$14));$skip(15); val res$15 = 
  fruit.sorted;System.out.println("""res15: List#8601[String#242] = """ + $show(res$15));$skip(25); val res$16 = 
  fruit groupBy (_.head);System.out.println("""res16: scala#25.collection#2740.immutable#5830.Map#7869[Char#1749,List#8601[String#242]] = """ + $show(res$16));$skip(110); val res$17 = 
 /*A polynomal as map from exponents to coefficients
  (x3 ? 2x + 5) as map:*/
  Map(0 -> 5, 1 -> -2, 3 -> 1);System.out.println("""res17: scala#25.collection#2740.immutable#5830.Map#7869[Int#1092,Int#1092] = """ + $show(res$17))}
}
