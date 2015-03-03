//package week3
//import week3.Rational //for single class
import week3._ //wildcard for all in package

object scratch {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(148); val res$0 = 
  new week3.Rational(1, 2);System.out.println("""res0: week3.Rational = """ + $show(res$0));$skip(21); val res$1 = 
  new Rational(3, 4);System.out.println("""res1: week3.Rational = """ + $show(res$1));$skip(51); 
  
  def error(msg: String) = throw new Error(msg);System.out.println("""error: (msg: String)Nothing""");$skip(60); 
  
  //exception
  //error("exception test")
  val x = null;System.out.println("""x  : Null = """ + $show(x ));$skip(23); 
  val y: String = null;System.out.println("""y  : String = """ + $show(y ));$skip(135); val res$2 = 
  
  //incompatible with subtypes of AnyVal
  //val z: Int = null //ineligible for type conversion
  
  if (true)
  	1
  else
  	false;System.out.println("""res2: AnyVal = """ + $show(res$2))}
}
