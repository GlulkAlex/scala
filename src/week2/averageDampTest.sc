package week2

import math.abs

object exercise3 {
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4

  def isCloseEnough(guess: Double, next: Double) =
    abs((guess - next) / guess) < next * tolerance //epsilon value
                                                  //> isCloseEnough: (guess: Double, next: Double)Boolean
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
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double

	def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double

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
		)(1.0)                            //> sqrt: (x: Double)Double
	
  //test
  fixedPoint(x => 1 + x / 2)(1.0)                 //> f(guess) is = to 1.5
                                                  //| f(guess) is = to 1.75
                                                  //| f(guess) is = to 1.875
                                                  //| f(guess) is = to 1.9375
                                                  //| f(guess) is = to 1.96875
                                                  //| f(guess) is = to 1.984375
                                                  //| f(guess) is = to 1.9921875
                                                  //| f(guess) is = to 1.99609375
                                                  //| f(guess) is = to 1.998046875
                                                  //| f(guess) is = to 1.9990234375
                                                  //| f(guess) is = to 1.99951171875
                                                  //| f(guess) is = to 1.999755859375
                                                  //| res0: Double = 1.999755859375
  
  sqrt(2)                                         //> f(guess) is = to 1.5
                                                  //| f(guess) is = to 1.4166666666666665
                                                  //| f(guess) is = to 1.4142156862745097
                                                  //| f(guess) is = to 1.4142135623746899
                                                  //| res1: Double = 1.4142135623746899
}