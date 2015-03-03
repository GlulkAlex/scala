package week6

object polynomals {
  /** Hint:
    * break all or most things to simplier function (modules / components)
    * that is a functional approach
    */
  class Poly( terms0: Map[ Int, Double ] ) {
    val terms1 = terms0 withDefaultValue 0.0 /*turns 'Map' from
    'partial' function (generate exception if 'key' not exist) into 'total' function*/

    /*axuliary constractor with 'repeated parameter'
    for variable lenght arguments list
    ? so 'bindings' is 'Seq[ ( Int, Double ) ]' ?*/
    def this( bindings: ( Int, Double )* ) = this( bindings.toMap )
    /*? with / through concatinate (-ion) ?*/
    def +( other1: Poly ) = new Poly( terms1 ++ ( other1.terms1 map adjust ) )

    def adjust( term1: ( Int, Double ) ): ( Int, Double ) = {
      val ( exp, coeff ) = term1

      //previously
      //without default value
      /*terms1 get exp match {
        case Some( coeff1 ) => exp -> ( coeff + coeff1 )
        case None           => exp -> coeff
      }*/

      /*? Map key -> value ?
      if 'coeff' exist 'terms1( exp )' returns 'coeff'
      else 'default value' '0.0'*/
      exp -> ( coeff + terms1( exp ) )
    }

    /** The '+' operation on 'Poly' used 'map' concatenation with '++'.
      * Design another version of '+' in terms of 'foldLeft':
      */
    /*parameters from 'add':
    on 'terms1' (as starting value & ? accumulator ?) do
    'addTerm operation' with 'other2.terms1' or
    with 'terms1' and 'other2.terms1' do 'addTerm operation'*/
    def addTerm( terms2: Map[ Int, Double ],
                 term2: ( Int, Double ) ): /*Poly*/ Map[ Int, Double ] = {
      val ( exp2, coeff2 ) = term2
      //*println( "terms2 is: " + terms2 )
      //*println( "( exp2, coeff2 ) is: " + "( " + exp2 + ", " + coeff2 + " )" )
      //*println( "terms2( " + exp2 + " ) is: " + terms2( exp2 ) )
      //*println( "terms2 get " + exp2 + " is: " + terms2.get( exp2 ) )
      /*return value*/
      //Map( exp2 -> ( coeff2 + terms2( exp2 ) ) )
      //Map( exp2 -> ( coeff2 + terms2.getOrElse(exp2, 0.0) ) )
      terms2 + ( exp2 -> ( coeff2 + terms2.getOrElse( exp2, 0.0 ) ) )
    } //*works

    /* foldLeft:
    ? accumulating total / overall result from
    z op x1(head) to (all previous result) op xN(tail)
    'terms1'(starting default) '.addTerm' 'this Poly.head' to
    (all previous result stored in 'terms1') '.addTerm' 'this Poly.tail' ?
    */
    def add( other2: Poly ) = new Poly( ( other2.terms1 foldLeft terms1 )( addTerm ) )
    /*def add2(other: Poly) = new Poly(
      (other.terms.foldLeft ((term2: Map[ Int, Double ]) => term2 withDefaultValue 0.0))
      (addTerm))*/

    /** Functions and Methods Implementation
      * by Martin Odersky
      */
    def addTerm2( terms3: Map[ Int, Double ],
                  term3: ( Int, Double ) ): Map[ Int, Double ] = {
                  val ( exp3, coeff3 ) = term3
                  
                  terms3 + ( exp3 -> ( coeff3 + terms3( exp3 ) ) )
                  }

    def plus( other3: Poly ) = new Poly(
      terms1 ++ ( other3.terms1 foldLeft terms1 )
      ( addTerm2 ) )

    override def toString =
      ( for ( ( exp, coeff ) <- terms1.toList.sorted.reverse )
        yield coeff + "x^" + exp ) mkString " + "
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(3563); 

  //benchmarking:
  /*scala.testing.Benchmark trait is
  predefined in the Scala standard library*/
  def timeNano[ R ]( block: => R ): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println( "Elapsed time: " + ( t1 - t0 ) + "ns" )
    result
  };System.out.println("""timeNano: [R](block: => R)R""");$skip(61); 

  val p1 = new Poly( Map( 1 -> 2.0, 3 -> 4.0, 5 -> 6.2 ) );System.out.println("""p1  : week6.polynomals.Poly = """ + $show(p1 ));$skip(49); 
  val p2 = new Poly( Map( 0 -> 3.0, 3 -> 7.0 ) );System.out.println("""p2  : week6.polynomals.Poly = """ + $show(p2 ));$skip(12); val res$0 = 

  p1 + p2;System.out.println("""res0: week6.polynomals.Poly = """ + $show(res$0));$skip(10); val res$1 = 
  p2 + p1;System.out.println("""res1: week6.polynomals.Poly = """ + $show(res$1));$skip(12); val res$2 = 
  p1 add p2;System.out.println("""res2: week6.polynomals.Poly = """ + $show(res$2));$skip(12); val res$3 = 
  p2 add p1;System.out.println("""res3: week6.polynomals.Poly = """ + $show(res$3));$skip(13); val res$4 = 
  p1 plus p2;System.out.println("""res4: week6.polynomals.Poly = """ + $show(res$4));$skip(13); val res$5 = 
  p2 plus p1;System.out.println("""res5: week6.polynomals.Poly = """ + $show(res$5));$skip(22); val res$6 = 
  timeNano( p1 + p2 );System.out.println("""res6: week6.polynomals.Poly = """ + $show(res$6));$skip(22); val res$7 = 
  timeNano( p2 + p1 );System.out.println("""res7: week6.polynomals.Poly = """ + $show(res$7));$skip(24); val res$8 = 
  timeNano( p1 add p2 );System.out.println("""res8: week6.polynomals.Poly = """ + $show(res$8));$skip(24); val res$9 = 
  timeNano( p2 add p1 );System.out.println("""res9: week6.polynomals.Poly = """ + $show(res$9));$skip(25); val res$10 = 
  timeNano( p1 plus p2 );System.out.println("""res10: week6.polynomals.Poly = """ + $show(res$10));$skip(25); val res$11 = 
  timeNano( p2 plus p1 );System.out.println("""res11: week6.polynomals.Poly = """ + $show(res$11));$skip(21); val res$12 = 
  p2.terms1.get( 3 );System.out.println("""res12: Option[Double] = """ + $show(res$12));$skip(21); val res$13 = 
  p2.terms1.get( 5 );System.out.println("""res13: Option[Double] = """ + $show(res$13));$skip(137); val res$14 = 
  //p2(5) //no such method defined on / in / for Poly
  /*default value test:
  coefficient for non existing exponent*/
  p1.terms1( 2 );System.out.println("""res14: Double = """ + $show(res$14));$skip(64); val res$15 = 

  ( term2: Map[ Int, Double ] ) => term2 withDefaultValue 0.0;System.out.println("""res15: Map[Int,Double] => scala.collection.immutable.Map[Int,Double] = """ + $show(res$15));$skip(87); 

  //public static void main(String[] args)
  def main( args: Array[ String ] ) {
  };System.out.println("""main: (args: Array[String])Unit""")}
}
