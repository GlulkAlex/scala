//package week3
//import week3.Rational //for single class
import week3._ //wildcard for all in package

object scratch {
  new week3.Rational(1, 2)                        //> res0: week3.Rational = 1/2
  new Rational(3, 4)                              //> res1: week3.Rational = 3/4
  
  def error(msg: String) = throw new Error(msg)   //> error: (msg: String)Nothing
  
  //exception
  //error("exception test")
  val x = null                                    //> x  : Null = null
  val y: String = null                            //> y  : String = null
  
  //incompatible with subtypes of AnyVal
  //val z: Int = null //ineligible for type conversion
  
  if (true)
  	1
  else
  	false                                     //> res2: AnyVal = 1
}