package week7

object lazyTest {
  /** Lazy as --
    * do things as late as possible &
    * never do it twice
    */
  /*lazy evaluation is
  powerful principle because it
  avoids
  unnesesery & repeated computation*/
  lazy val x = expr                               //> x: => Unit
  /*like 'def' before it 1st time evaluated, then
  resulu stored & reused if needed
  not reevaluated as in 'def'*/
  def x1 = expr                                   //> x1: => Unit
  
  def expr = {
    /*'print' as side effect of evaluation*/
    /*'val' evaluated immediately,
    so, 'x' in output
    then evaluation goes to expression
    writen after definitions
    namely, to 'z'*/
    val x = { print( "x" ); 1 }
    lazy val y = { print( "y" ); 2 }

      def z = { print( "z" ); 3 }
    
    /*'print' works only in definitons
    once value defined then no 'print' output
    except if expression use a 'def' method
    which is evaluated every time when called / invoked*/
    z + y + x + z + y + x
    println
    println( "end of 'expr' evaliation" )
    println( "x = " + x )
    println( "y = " + y )
    println( "z = " + z )
  }                                               //> expr: => Unit
  /*when called
  firstly
  evaluated all definitions*/
  expr                                            //> xzyz
                                                  //| end of 'expr' evaliation
                                                  //| x = 1
                                                  //| y = 2
                                                  //| zz = 3
  /*Using a lazy value for 'tail',
  'Stream.cons' can be implemented more effciently:*/
  /*def cons[ T ]( hd: T,
                 tl: => Stream[ T ] ) = new Stream[ T ] {
    def head = hd
    /*storing the result of
    the first evaluation of 'tail' and
    re-using the 'stored result' instead of
    recomputing 'tail'*/
    lazy val tail = tl
    //...
  }*/
  /*test expression evaluation using
  the substitution model*/
}