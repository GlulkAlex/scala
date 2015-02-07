package week2

import math.abs

object exercise3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(120); 

  def isCloseEnough(guess: Double, next: Double) =
    abs((guess - next) / guess) < next * tolerance;System.out.println("""isCloseEnough: (guess: Double, next: Double)Boolean""");$skip(622);  //epsilon value
    //or,
    //but this equvalent vertion causes infinit loop
    //so actual computation is different
    //abs(guess * guess - next) < next * tolerance //epsilon value
  /**
   * f - function as parameter,
   * firstGuess - initial value
   */
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)

      //caution: may not terminate
      println("f(guess) is = to " + next)//for vizual value control

      if (isCloseEnough(guess, next))
        next
      else
        iterate(next)
    }
    //return value
    iterate(firstGuess)
  };System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(68); 

	def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2;System.out.println("""averageDamp: (f: Double => Double)(x: Double)Double""");$skip(370); 

	//not converge - oscilate infinetly over 1.0 and 2.0
	//def sqrt(x: Double) = fixedPoint((y => x/y))(1.0)
	//averaging successful results, for narrowing interval span
	//iteration converges by averaging successive values
	//def sqrt(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)
	//or
	def sqrt(x: Double) =
		fixedPoint(
			averageDamp(
				y => x/y
			)
		)(1.0);System.out.println("""sqrt: (x: Double)Double""");$skip(45); val res$0 = 
	
  //test
  fixedPoint(x => 1 + x / 2)(1.0);System.out.println("""res0: Double = """ + $show(res$0));$skip(13); val res$1 = 
  
  sqrt(2);System.out.println("""res1: Double = """ + $show(res$1))}
}
