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
  }

  //benchmarking:
  /*scala.testing.Benchmark trait is
  predefined in the Scala standard library*/
  def timeNano[ R ]( block: => R ): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()

    println( "Elapsed time: " + ( t1 - t0 ) + "ns" )
    result
  }                                               //> timeNano: [R](block: => R)R

  val p1 = new Poly( Map( 1 -> 2.0, 3 -> 4.0, 5 -> 6.2 ) )
                                                  //> p1  : week6.polynomals.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Poly( Map( 0 -> 3.0, 3 -> 7.0 ) )  //> p2  : week6.polynomals.Poly = 7.0x^3 + 3.0x^0

  p1 + p2                                         //> res0: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p2 + p1                                         //> res1: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p1 add p2                                       //> res2: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p2 add p1                                       //> res3: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p1 plus p2                                      //> res4: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p2 plus p1                                      //> res5: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p1 + p2 )                             //> Elapsed time: 62822ns
                                                  //| res6: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p2 + p1 )                             //> Elapsed time: 65258ns
                                                  //| res7: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p1 add p2 )                           //> Elapsed time: 45224ns
                                                  //| res8: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p2 add p1 )                           //> Elapsed time: 54747ns
                                                  //| res9: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p1 plus p2 )                          //> Elapsed time: 69432ns
                                                  //| res10: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  timeNano( p2 plus p1 )                          //> Elapsed time: 76586ns
                                                  //| res11: week6.polynomals.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  p2.terms1.get( 3 )                              //> res12: Option[Double] = Some(7.0)
  p2.terms1.get( 5 )                              //> res13: Option[Double] = None
  //p2(5) //no such method defined on / in / for Poly
  /*default value test:
  coefficient for non existing exponent*/
  p1.terms1( 2 )                                  //> res14: Double = 0.0

  ( term2: Map[ Int, Double ] ) => term2 withDefaultValue 0.0
                                                  //> res15: Map[Int,Double] => scala.collection.immutable.Map[Int,Double] = <fun
                                                  //| ction1>

  //public static void main(String[] args)
  def main( args: Array[ String ] ) {
  }                                               //> main: (args: Array[String])Unit
}