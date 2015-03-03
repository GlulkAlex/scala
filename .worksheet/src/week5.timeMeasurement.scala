package week5

object timeMeasurement {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  println("Welcome to the Scala worksheet");$skip(821); 
  /* Note:
  Executing the same piece of code
  (e.g. a method) multiple times
  in the same JVM instance
  might give
  very different performance results
  depending on
  whether the particular code was optimized
  in between the runs.
  Additionally,
  measuring the execution time
  of some piece of code
  may include
  the time during which
  the JIT compiler itself
  was performing the optimization,
  thus
  giving inconsistent results.*/
  //Such effects need to be taken into consideration when writing a 'benchmark'.
  
  /**
   * How to profile methods in Scala?
   * time Measurement:
   */
  def timeMillis[T](str: String)(thunk: => T): T = {
    print(str + "... ")
    val t1 = System.currentTimeMillis
    val x = thunk
    val t2 = System.currentTimeMillis
    println((t2 - t1) + " msecs")
    x
  };System.out.println("""timeMillis: [T](str: String)(thunk: => T)T""");$skip(302); 

  /** Usage example: */
  /*
  timeMillis("squareList2 time") {
    squareList2(intList)
  }*/

  def timeNano[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  };System.out.println("""timeNano: [R](block: => R)R""");$skip(161); val res$0 = 

  // Now wrap your method calls, for example change this...
  //val result = 1 to 1000 sum

  // ... into this
  /*val result = */ timeNano { 1 to 1000 sum };System.out.println("""res0: Int = """ + $show(res$0))}
}
